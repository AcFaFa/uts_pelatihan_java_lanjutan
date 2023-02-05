package com.ngebut.ngebutcrud.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class siswa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Integer id;

    @Column(length = 20,nullable = false)
    String nis;

    @Column(length = 100,nullable = false)
    String nama;

    @Column(length = 100,nullable = false)
    String alamat;
    @Column(length = 1,nullable = false)
    Character jenis_kelamin;

    @ManyToOne
    @JoinColumn(name="id_kelas")
    kelas kes;
}
