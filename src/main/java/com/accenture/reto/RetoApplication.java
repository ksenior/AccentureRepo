package com.accenture.reto;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.accenture.reto.album_ms.model.Permission;
import com.accenture.reto.album_ms.repository.PermissionRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class RetoApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(RetoApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner permissionData(PermissionRepository repo) {
        return args -> { 
        	List<String> names = Arrays.asList("Lectura", "Escritura");
        	names.forEach(name -> repo.save(new Permission(name)));
        };
    }

}
