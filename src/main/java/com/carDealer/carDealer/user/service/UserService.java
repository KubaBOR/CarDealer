package com.carDealer.carDealer.user.service;

import com.carDealer.carDealer.user.dto.User;
import com.carDealer.carDealer.user.dto.UserFormData;
import com.carDealer.carDealer.user.model.RoleDocument;
import com.carDealer.carDealer.user.model.UserDocument;
import com.carDealer.carDealer.user.repository.RoleRepository;
import com.carDealer.carDealer.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }


    public String addUser(UserFormData formData) {


        RoleDocument userRole = roleRepository.getByRole("ADMIN");

        UserDocument userToSave = new UserDocument(
                formData.getFirstName(),
                formData.getLastName(),
                formData.getEmail(),
                bCryptPasswordEncoder.encode(formData.getPassword()),
                new HashSet<>(Arrays.asList(userRole))
        );

        return userRepository.save(userToSave).getId();
    }

    public boolean isEmailTaken(String email) {

        Optional<UserDocument> isTaken = userRepository.findAll()
                .stream()
                .filter(userDocument -> email.contains(userDocument.getEmail()))
                .findAny();

        return isTaken.isPresent();
    }


/*        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));

        UserDocument userToSave = modelMapper.map(user, UserDocument.class);
//        userToSave.setRoles(new HashSet<>(Arrays.asList(userRole)));

        return userRepository.save(userToSave).getId();*/


    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userDocument -> modelMapper.map(userDocument, User.class))
                .collect(Collectors.toList());
    }

    public User getByEmail(String email) {
        UserDocument foundUser = userRepository.getByEmail(email);
        return modelMapper.map(foundUser, User.class);
    }

    /**
     * @param user regex checks if username is email address
     * @return true if regex is email address
     */
    private boolean validateUsername(User user) {
        String username = user.getEmail();

        if (!username.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
                "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\" +
                "x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(" +
                "?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|" +
                "[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-" +
                "\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            return false;
        }

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDocument userFound = userRepository.getByEmail(email);
        if (userFound != null) {
            List<GrantedAuthority> authorities = getUserAuthority(userFound.getRoles());
            return buildUserForAuthentication(userFound, authorities);
        } else {
            throw new UsernameNotFoundException("User does not exist.");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<RoleDocument> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(UserDocument user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @PostConstruct
    public void createSampleUser() {
        if (userRepository.count() < 1) {

            RoleDocument userRole = roleRepository.getByRole("ADMIN");

            UserDocument user = new UserDocument();
            user.setEmail("test@gmail.com");
            user.setLastName("Joshua");
            user.setPassword(bCryptPasswordEncoder.encode("pass"));
            user.setRoles(new HashSet<>(Arrays.asList(userRole)));
            userRepository.save(user);
        }
    }

}
