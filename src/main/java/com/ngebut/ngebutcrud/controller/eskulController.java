package com.ngebut.ngebutcrud.controller;

import com.ngebut.ngebutcrud.dto.guruDto;
import com.ngebut.ngebutcrud.model.guru;
import com.ngebut.ngebutcrud.repository.guruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ngebut.ngebutcrud.dto.eskulDto;
import com.ngebut.ngebutcrud.model.eskul;
@RestController
@RequestMapping("/eskul")
public class eskulController {
    @Autowired
    com.ngebut.ngebutcrud.repository.eskulRepository eskulRepository;

    @GetMapping("find-all")
    public Object findAll(){
        return eskulRepository.findAll();
    }
    @PostMapping("/")
    public Object create(@RequestBody eskulDto dto){
        eskul Eskul = new eskul();
        Eskul.setNama(dto.getNama());
        return ResponseEntity.ok(eskulRepository.save(Eskul));
    }
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") Integer id,
                         @RequestBody eskulDto dto) {
        eskul Eskul = eskulRepository.findById(id).orElse(null);
        if(Eskul == null) {
            return ResponseEntity.badRequest().body("id tidak ditemukan!");
        }
        Eskul.setNama(dto.getNama());

        return  ResponseEntity.ok(eskulRepository.save(Eskul));
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Integer id) {
        eskul Eskul = eskulRepository.findById(id).orElse(null);
        if (Eskul == null) {
            return ResponseEntity.badRequest().body("id tidak tersedia");
        }

        eskulRepository.delete(Eskul);
        return null;
    }
}
