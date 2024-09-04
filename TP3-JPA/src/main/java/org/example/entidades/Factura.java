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
@Table(name="Factura")
public class Factura implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="fecha")
    private String fecha;

    @Column(name="numero")
    private int numero;

    @Column(name="precio")
    private int precio;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name="fk_cliente")
    private Cliente cliente;

    //@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    //private List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();

    @OneToMany(mappedBy="factura",cascade=CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();
}