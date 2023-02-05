package com.ngebut.ngebutcrud.controller;

import com.ngebut.ngebutcrud.dto.guruDto;
import com.ngebut.ngebutcrud.model.guru;
import com.ngebut.ngebutcrud.repository.guruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ngebut.ngebutcrud.dto.mataPelajaranDto;
import com.ngebut.ngebutcrud.model.mataPelajaran;
import com.ngebut.ngebutcrud.repository.mataPelajaranRepository;
import com.ngebut.ngebutcrud.repository.guruRepository;
@RestController
@RequestMapping("/mataPelajaran")
public class mataPelajaranController {

    @Autowired
    mataPelajaranRepository mataPelajaranRepository;

    @Autowired
    guruRepository guruRepository;


    @GetMapping("find-all")
    public Object findAll(){
        return mataPelajaranRepository.findAll();
    }
    @PostMapping("/")
    public Object create(@RequestBody mataPelajaranDto dto){
        mataPelajaran mata = new mataPelajaran();
        mata.setNama(dto.getNama());

        if(guruRepository.findById(dto.getId_pengajar()).isPresent()){
            mata.setPengajar(guruRepository.findById(dto.getId_pengajar()).orElse(null));
            return ResponseEntity.ok(mataPelajaranRepository.save(mata));
        }else{
            return ResponseEntity.badRequest().body("Id Guru tidak ditemukan!");
        }

    }
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") Integer id,
                         @RequestBody mataPelajaranDto dto) {
        mataPelajaran mata = mataPelajaranRepository.findById(id).orElse(null);
        if(mata == null) {
            return ResponseEntity.badRequest().body("id tidak ditemukan!");
        }
        mata.setNama(dto.getNama());

        return  ResponseEntity.ok(mataPelajaranRepository.save(mata));
    }

//    @DeleteMapping("/{id}")
//    public Object delete(@PathVariable("id") Integer id) {
//        mataPelajaran mata = mataPelajaranRepository.findById(id).orElse(null);
//        if (mata == null) {
//            return ResponseEntity.badRequest().body("id tidak tersedia");
//        }
//
//        mataPelajaranRepository.delete(mata);
//        return null;
//    }
}
