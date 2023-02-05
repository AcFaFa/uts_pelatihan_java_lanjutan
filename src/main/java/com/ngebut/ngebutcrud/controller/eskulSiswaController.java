package com.ngebut.ngebutcrud.controller;

import com.ngebut.ngebutcrud.dto.eskulDto;
import com.ngebut.ngebutcrud.model.eskul;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ngebut.ngebutcrud.repository.eskulSiswaRepository;
import com.ngebut.ngebutcrud.repository.eskulRepository;
import com.ngebut.ngebutcrud.repository.siswaRepository;
@RestController
@RequestMapping("/eskulSiswa")
public class eskulSiswaController {
    @Autowired
    eskulSiswaRepository eskulSiswaRepository;

    @Autowired
    siswaRepository siswaRepository;

    @Autowired
    eskulRepository eskulRepository;
    @GetMapping("/find-all")
    public Object findAll() {
        return eskulSiswaRepository.findAll();
    }






}
