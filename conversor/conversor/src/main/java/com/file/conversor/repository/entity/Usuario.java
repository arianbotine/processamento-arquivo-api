package com.file.conversor.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name ="usuario")
public class Usuario {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;
}
