package org.example.entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
@Table(name="domicilio")
public class Domicilio implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre_calle")
    private String nombre_calle;

    @Column(name="numero")
    private int numero;
    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;



}