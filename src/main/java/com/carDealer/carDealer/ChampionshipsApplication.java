package com.carDealer.carDealer;

import com.carDealer.carDealer.user.model.RoleDocument;
import com.carDealer.carDealer.user.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChampionshipsApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {
		return args -> {
			RoleDocument adminRole = roleRepository.getByRole("ADMIN");
			if (adminRole == null) {
				RoleDocument newAdminRole = new RoleDocument();
				newAdminRole.setRole("ADMIN");
				roleRepository.save(newAdminRole);
			}

			RoleDocument userRole = roleRepository.getByRole("USER");
			if (userRole == null) {
				RoleDocument newUserRole = new RoleDocument();
				newUserRole.setRole("USER");
				roleRepository.save(newUserRole);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ChampionshipsApplication.class, args);
	}

}
