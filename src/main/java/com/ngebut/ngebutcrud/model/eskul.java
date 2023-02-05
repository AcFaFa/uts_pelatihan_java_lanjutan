package com.ngebut.ngebutcrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class eskul {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(length = 100,nullable = false)
    private String nama;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="eskul_siswa",
            joinColumns = {@JoinColumn(name = "id_eskul")},
            inverseJoinColumns = {@JoinColumn(name="id_siswa")}
    )
    private Set<siswa> siswaSet=new HashSet<>();

}
