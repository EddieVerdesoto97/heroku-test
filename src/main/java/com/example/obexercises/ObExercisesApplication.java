package com.example.obexercises;

import com.example.obexercises.entities.Laptop;
import com.example.obexercises.repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObExercisesApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObExercisesApplication.class, args);

		LaptopRepository repository = context.getBean(LaptopRepository.class);

		Laptop laptop = new Laptop(null,"Asus","Asus Rog Zephyrus14",1450.45d);

		repository.save(laptop);






	}

}
