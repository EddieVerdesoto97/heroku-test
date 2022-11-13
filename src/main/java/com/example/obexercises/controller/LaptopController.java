package com.example.obexercises.controller;

import com.example.obexercises.entities.Laptop;
import com.example.obexercises.repositories.LaptopRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    @ApiOperation("Buscar todas las laptops existentes")
    public List<Laptop> findAll(){

        return laptopRepository.findAll();

    }

    @GetMapping("/api/laptops/{id}")
    @ApiOperation("Buscar una laptop por id")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){

        Optional<Laptop> laptopOpt = laptopRepository.findById(id);
        if (laptopOpt.isPresent()){
            return ResponseEntity.ok(laptopOpt.get());
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping("/api/laptops")
    @ApiOperation("Crear una nueva laptop")
    public Laptop create(@RequestBody Laptop laptop){

        return laptopRepository.save(laptop);

    }

    @PutMapping("/api/laptops")
    @ApiOperation("Actualizar la información de una laptop")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){

        if (laptop.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        if (!laptopRepository.existsById(laptop.getId())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(laptopRepository.save(laptop));
    }

    @DeleteMapping("/api/laptops/{id}")
    @ApiOperation("Eliminar una laptop por id")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){

        if (!laptopRepository.existsById(id)){

            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/laptops")
    @ApiOperation("Eliminar todas las laptop")
    public ResponseEntity<Laptop> deleteAll(){

        Long count = laptopRepository.count();
        if (count == 0){
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteAll();
        return  ResponseEntity.noContent().build();

    }

}
