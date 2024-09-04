package org.example.entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
public class Cliente implements Serializable {

    @Serial
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni", unique = true)
    private int dni;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="fk_domicilio")
    private Domicilio domicilio;

    @OneToMany(mappedBy="cliente")
    private List<Factura> facturas = new ArrayList<Factura>();

}
