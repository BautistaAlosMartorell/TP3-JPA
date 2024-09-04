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
@Table(name="articulo")
public class Articulo implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="cantidad")
    private int cantidad;

    @Column(name="denominacion")
    private String denominacion;

    @Column(name="precio")
    private int precio;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="articulo_categoria",
            joinColumns= @JoinColumn(name="articulo_id"),
            inverseJoinColumns = @JoinColumn(name="categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<Categoria>();

}
