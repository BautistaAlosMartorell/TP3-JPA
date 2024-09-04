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
@Table(name="detalle_factura")
public class DetalleFactura implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="cantidad")
    private int cantidad;

    @Column(name="subtotal")
    private int subtotal;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="fk_factura")
    private Factura factura;



}