package com.ngebut.ngebutcrud.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class mataPelajaran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Integer id;

    @Column(length = 100,nullable = false)
    String nama;

    @OneToOne
    @JoinColumn(name="id_pengajar")
    guru pengajar;

}
