package com.ngebut.ngebutcrud.controller;

import com.ngebut.ngebutcrud.dto.guruDto;
import com.ngebut.ngebutcrud.model.guru;
import com.ngebut.ngebutcrud.repository.guruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ngebut.ngebutcrud.repository.kelasRepository;
import com.ngebut.ngebutcrud.model.kelas;
import java.util.List;
import com.ngebut.ngebutcrud.dto.kelasDto;
@RestController
@RequestMapping("/kelas")
public class kelasController {

    kelasRepository kelasRepository;

    guruRepository guruRepository;

    @Autowired
    public kelasController(com.ngebut.ngebutcrud.repository.kelasRepository kelasRepository, com.ngebut.ngebutcrud.repository.guruRepository guruRepository) {
        this.kelasRepository = kelasRepository;
        this.guruRepository = guruRepository;
    }

    @GetMapping("find-all")
    public List<kelas> findAll(){
        return kelasRepository.findAll();
    }
    @PostMapping("/")
    public Object create(@RequestBody kelasDto dto){
        kelas kes = new kelas();
        kes.setNama(dto.getNama());

        if(kelasRepository.findById(dto.getIdWali()).isPresent()){
            kes.setWali(guruRepository.findById(dto.getIdWali()).orElse(null));
            return ResponseEntity.ok(kelasRepository.save(kes));
        }else{
            return ResponseEntity.badRequest().body("Id Kelas tidak ditemukan!");
        }
    }
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") Integer id,
                         @RequestBody kelasDto dto) {
        kelas kes = kelasRepository.findById(id).orElse(null);
        if(kes == null) {
            return ResponseEntity.badRequest().body("id tidak ditemukan!");
        }
        kes.setNama(dto.getNama());
        kes.setWali(guruRepository.findById(dto.getIdWali()).orElse(null));

        if (guruRepository.findById(dto.getIdWali()).isPresent()) {
            kes.setWali(guruRepository.findById(dto.getIdWali()).orElse(null));
            return ResponseEntity.ok(kelasRepository.save(kes));
        } else {
            return ResponseEntity.badRequest().body("Id Kelas Tidak Ada!");
        }
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
//        kelas kes = kelasRepository.findById(id).orElse(null);
//        if (kes == null) {
//            return ResponseEntity.badRequest().body("id tidak tersedia");
//        }
//
//        kelasRepository.delete(kes);
//        return null;
//    }
}
