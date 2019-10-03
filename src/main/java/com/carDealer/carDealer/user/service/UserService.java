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


        RoleDocument userRole = roleRepository.getByRole("ROLE_USER");

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

    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userDocument -> modelMapper.map(userDocument, User.class))
                .collect(Collectors.toList());
    }

    public User getByEmail(String email) {
        UserDocument foundUser = userRepository.getByEmail(email);
        return modelMapper.map(foundUser, User.class);
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
        if (userRepository.getByEmail("admin@admin.pl") == null) {

            RoleDocument userRole = roleRepository.getByRole("ROLE_ADMIN");

            UserDocument user = new UserDocument();
            user.setEmail("admin@admin.pl");
            user.setLastName("Admin");
            user.setPassword(bCryptPasswordEncoder.encode("pass"));
            user.setRoles(new HashSet<>(Arrays.asList(userRole)));
            userRepository.save(user);
        }
    }

}
