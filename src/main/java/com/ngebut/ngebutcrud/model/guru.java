package com.ngebut.ngebutcrud.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class guru {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Integer id;

    @Column(length = 100,nullable = false)
    String nama;

    @Column(length = 200,nullable = false)
    String alamat;

    @Column(length = 15,nullable = false)
    String no_hp;

}
