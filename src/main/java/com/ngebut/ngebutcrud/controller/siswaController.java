package com.ngebut.ngebutcrud.controller;

import com.ngebut.ngebutcrud.dto.kelasDto;
import com.ngebut.ngebutcrud.model.kelas;
import com.ngebut.ngebutcrud.repository.guruRepository;
import com.ngebut.ngebutcrud.repository.mataPelajaranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ngebut.ngebutcrud.repository.siswaRepository;
import com.ngebut.ngebutcrud.repository.kelasRepository;
import com.ngebut.ngebutcrud.model.siswa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ngebut.ngebutcrud.dto.siswaDto;
import com.ngebut.ngebutcrud.model.siswa;
@RestController
@RequestMapping("/siswa")
public class siswaController {

    siswaRepository siswaRepository;
    kelasRepository kelasRepository;

    @Autowired
    public siswaController(com.ngebut.ngebutcrud.repository.siswaRepository siswaRepository, com.ngebut.ngebutcrud.repository.kelasRepository kelasRepository) {
        this.siswaRepository = siswaRepository;
        this.kelasRepository = kelasRepository;
    }




    @GetMapping("/find-all")
    public List<siswa> findAll() {
        return siswaRepository.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody siswaDto dto) {
        siswa sis = new siswa();
        sis.setNis(dto.getNis());
        sis.setNama(dto.getNama());
        sis.setJenis_kelamin(dto.getJenis_kelamin());
        sis.setAlamat(dto.getAlamat());
        if (kelasRepository.findById(dto.getIdKelas()).isPresent()) {
            sis.setKes(kelasRepository.findById(dto.getIdKelas()).orElse(null));
            return ResponseEntity.ok(siswaRepository.save(sis));
        } else {
            return ResponseEntity.badRequest().body("Id Kelas Tidak Ada!");
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Integer id,
                                         @RequestBody siswaDto dto) {
        siswa sis = siswaRepository.findById(id).orElse(null);
        if(sis == null) {
            return ResponseEntity.badRequest().body("id tidak ditemukan!");
        }
        sis.setNis(dto.getNis());
        sis.setNama(dto.getNama());
        sis.setJenis_kelamin(dto.getJenis_kelamin());
        sis.setAlamat(dto.getAlamat());
        sis.setKes(kelasRepository.findById(dto.getIdKelas()).orElse(null));

        if (kelasRepository.findById(dto.getIdKelas()).isPresent()) {
            sis.setKes(kelasRepository.findById(dto.getIdKelas()).orElse(null));
            return ResponseEntity.ok(siswaRepository.save(sis));
        } else {
            return ResponseEntity.badRequest().body("Id Siswa Tidak Ada!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        siswa sis = siswaRepository.findById(id).orElse(null);
        if (sis == null) {
            return ResponseEntity.badRequest().body("id tidak tersedia");
        }

        siswaRepository.delete(sis);
        return null;
    }
    @GetMapping("/find-all-eskul-siswa")
    public List<Map<String, Object>> findAllEskulSiswa() {
        List<Map<String, Object>> eskulSiswas = new ArrayList<>();
        siswaRepository.findAll().forEach(siswa -> {
            Map<String, Object> eskulSiswa = new HashMap<>();
            eskulSiswa.put("siswa", siswa);
            eskulSiswas.add(eskulSiswa);
        });
        return eskulSiswas;
    }
}
