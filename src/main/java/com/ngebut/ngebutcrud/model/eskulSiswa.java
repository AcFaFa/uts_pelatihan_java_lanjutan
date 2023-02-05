package com.ngebut.ngebutcrud.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class eskulSiswa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_siswa", referencedColumnName = "id")
    private eskul eskul;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_eskul", referencedColumnName = "id")
    private siswa siswa;

}
