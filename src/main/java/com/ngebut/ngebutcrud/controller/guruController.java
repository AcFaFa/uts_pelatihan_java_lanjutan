package com.ngebut.ngebutcrud.controller;

import com.ngebut.ngebutcrud.repository.guruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ngebut.ngebutcrud.dto.guruDto;
import com.ngebut.ngebutcrud.model.guru;
@RestController
@RequestMapping("/guru")
public class guruController {
    @Autowired
    guruRepository guruRepository;

    @GetMapping("find-all")
    public Object findAll(){
        return guruRepository.findAll();
    }
    @PostMapping("/")
    public Object create(@RequestBody guruDto dto){
        guru Guru = new guru();
        Guru.setNama(dto.getNama());
        Guru.setAlamat(dto.getAlamat());
        Guru.setNo_hp(dto.getNo_hp());
        return ResponseEntity.ok(guruRepository.save(Guru));
    }
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") Integer id,
                         @RequestBody guruDto dto) {
        guru Guru = guruRepository.findById(id).orElse(null);
        if(Guru == null) {
            return ResponseEntity.badRequest().body("id tidak ditemukan!");
        }
        Guru.setNama(dto.getNama());

        return  ResponseEntity.ok(guruRepository.save(Guru));
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Integer id) {
        guru Guru = guruRepository.findById(id).orElse(null);
        if (Guru == null) {
            return ResponseEntity.badRequest().body("id tidak tersedia");
        }

        guruRepository.delete(Guru);
        return null;
    }
}
