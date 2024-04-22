package com.file.conversor.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import java.util.List;

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

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;
}
