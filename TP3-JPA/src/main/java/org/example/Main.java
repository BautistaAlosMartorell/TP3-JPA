package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.example.entidades.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("en marcha");

        try {
            // Persistir una nueva entidad Cliente
            entityManager.getTransaction().begin();

            Domicilio domicilio1 = Domicilio.builder()
                    .nombre_calle("San Lorenzo")
                    .numero(444)
                    .build();

            Domicilio domicilio2 = Domicilio.builder()
                    .nombre_calle("España")
                    .numero(934)
                    .build();


            Cliente cliente = Cliente.builder()
                    .nombre("Bautista")
                    .apellido("Alós")
                    .dni(45718877)
                    .domicilio(domicilio1)
                    .build();

            Cliente cliente2 = Cliente.builder()
                    .nombre("Juan")
                    .apellido("Fernandez")
                    .dni(45717912)
                    .domicilio(domicilio2)
                    .build();

            Categoria categoria1 = Categoria.builder()
                    .denominacion("Libreria")
                    .build();

            Categoria categoria2 = Categoria.builder()
                    .denominacion("Limpieza")
                    .build();

            Articulo articulo1 = Articulo.builder()
                    .cantidad(10)
                    .denominacion("Lapiz")
                    .categorias(List.of(categoria1))
                    .precio(500)
                    .build();

            Articulo articulo2 = Articulo.builder()
                    .cantidad(20)
                    .denominacion("Lapicera")
                    .precio(1500)
                    .categorias(List.of(categoria1))
                    .build();

            Articulo articulo3 = Articulo.builder()
                    .cantidad(30)
                    .denominacion("Detergente")
                    .precio(2000)
                    .categorias(List.of(categoria2))
                    .build();
            Articulo articulo4 = Articulo.builder()
                    .cantidad(50)
                    .denominacion("Jabón")
                    .precio(1000)
                    .categorias(List.of(categoria2))
                    .build();

            DetalleFactura detalleFactura=DetalleFactura.builder()
                    .cantidad(5)
                    .subtotal(2500)
                    .build();

            DetalleFactura detalleFactura1=DetalleFactura.builder()
                    .cantidad(1)
                    .subtotal(1500)
                    .build();

            DetalleFactura detalleFactura2=DetalleFactura.builder()
                    .cantidad(1)
                    .subtotal(2500)
                    .build();

            DetalleFactura detalleFactura3=DetalleFactura.builder()
                    .cantidad(3)
                    .subtotal(3000)
                    .build();

            Factura factura = Factura.builder()
                    .fecha("04/09/2024")
                    .numero(01342)
                    .precio(4000)
                    .cliente(cliente)
                    .detalles(List.of(detalleFactura, detalleFactura1))

                    .build();

            Factura factura1 = Factura.builder()
                    .fecha("03/09/2024")
                    .numero(01234)
                    .precio(2500)
                    .cliente(cliente2)
                    .detalles(List.of(detalleFactura2,detalleFactura3))

                    .build();

            detalleFactura.setFactura(factura);
            detalleFactura1.setFactura(factura);
            categoria1.setArticulos(List.of(articulo1,articulo2));
            detalleFactura2.setFactura(factura1);
            detalleFactura3.setFactura(factura1);
            categoria2.setArticulos(List.of(articulo3,articulo4));



// Añadir los detalles a la factura
            factura.setDetalles(List.of(detalleFactura, detalleFactura1));
            System.out.println(cliente.getNombre());

            entityManager.persist(cliente);
            entityManager.persist(cliente2);
            entityManager.persist(articulo1);
            entityManager.persist(articulo2);
            entityManager.persist(articulo3);
            entityManager.persist(articulo4);
            entityManager.persist(detalleFactura);
            entityManager.persist(detalleFactura1);
            entityManager.persist(detalleFactura2);
            entityManager.persist(detalleFactura3);
            entityManager.persist(factura);
            entityManager.persist(factura1);
            entityManager.persist(categoria1);
            entityManager.persist(categoria2);
            entityManager.persist(domicilio1);
            entityManager.persist(domicilio2);


            entityManager.flush();

            entityManager.getTransaction().commit();

            // Consultar y mostrar la entidad persistida
            Cliente retrievedCliente = entityManager.find(Cliente.class, cliente.getId());
            System.out.println("Cliente 1: Nombre: " + retrievedCliente.getNombre() + ", Apellido: " + retrievedCliente.getApellido() + ", DNI: " + retrievedCliente.getDni() + ", Domicilio: " + retrievedCliente.getDomicilio().getNombre_calle() + " " + retrievedCliente.getDomicilio().getNumero());

            Cliente retrievedCliente1 = entityManager.find(Cliente.class, cliente2.getId());
            System.out.println("Cliente 2: Nombre: " + retrievedCliente1.getNombre() + ", Apellido: " + retrievedCliente1.getApellido() + ", DNI: " + retrievedCliente1.getDni() + ", Domicilio: " + retrievedCliente1.getDomicilio().getNombre_calle() + " " + retrievedCliente1.getDomicilio().getNumero());

            Articulo retrievedArticulo1 = entityManager.find(Articulo.class, articulo1.getId());
            System.out.println("Articulo 1: Denominacion: " + retrievedArticulo1.getDenominacion() + ", Cantidad: " + retrievedArticulo1.getCantidad() + ", Precio: " + retrievedArticulo1.getPrecio() + ", Categorías: " + retrievedArticulo1.getCategorias().stream().map(Categoria::getDenominacion).toList());

            Articulo retrievedArticulo2 = entityManager.find(Articulo.class, articulo2.getId());
            System.out.println("Articulo 2: Denominacion: " + retrievedArticulo2.getDenominacion() + ", Cantidad: " + retrievedArticulo2.getCantidad() + ", Precio: " + retrievedArticulo2.getPrecio() + ", Categorías: " + retrievedArticulo2.getCategorias().stream().map(Categoria::getDenominacion).toList());

            Articulo retrievedArticulo3 = entityManager.find(Articulo.class, articulo3.getId());
            System.out.println("Articulo 3: Denominacion: " + retrievedArticulo3.getDenominacion() + ", Cantidad: " + retrievedArticulo3.getCantidad() + ", Precio: " + retrievedArticulo3.getPrecio() + ", Categorías: " + retrievedArticulo3.getCategorias().stream().map(Categoria::getDenominacion).toList());

            Articulo retrievedArticulo4 = entityManager.find(Articulo.class, articulo4.getId());
            System.out.println("Articulo 4: Denominacion: " + retrievedArticulo4.getDenominacion() + ", Cantidad: " + retrievedArticulo4.getCantidad() + ", Precio: " + retrievedArticulo4.getPrecio() + ", Categorías: " + retrievedArticulo4.getCategorias().stream().map(Categoria::getDenominacion).toList());

            Categoria retrievedCategoria = entityManager.find(Categoria.class, categoria1.getId());
            System.out.println("Categoria 1: Denominacion: " + retrievedCategoria.getDenominacion() + ", Articulos: " + retrievedCategoria.getArticulos().stream().map(Articulo::getDenominacion).toList());

            Categoria retrievedCategoria1 = entityManager.find(Categoria.class, categoria2.getId());
            System.out.println("Categoria 2: Denominacion: " + retrievedCategoria1.getDenominacion() + ", Articulos: " + retrievedCategoria1.getArticulos().stream().map(Articulo::getDenominacion).toList());

            DetalleFactura retrievedDF1 = entityManager.find(DetalleFactura.class, detalleFactura1.getId());
            System.out.println("DetalleFactura 1: Cantidad: " + retrievedDF1.getCantidad() + ", Subtotal: " + retrievedDF1.getSubtotal() + ", Factura: " + (retrievedDF1.getFactura() != null ? retrievedDF1.getFactura().getNumero() : "No Asociada"));

            DetalleFactura retrievedDF2 = entityManager.find(DetalleFactura.class, detalleFactura.getId());
            System.out.println("DetalleFactura 2: Cantidad: " + retrievedDF2.getCantidad() + ", Subtotal: " + retrievedDF2.getSubtotal() + ", Factura: " + (retrievedDF2.getFactura() != null ? retrievedDF2.getFactura().getNumero() : "No Asociada"));

            DetalleFactura retrievedDF3 = entityManager.find(DetalleFactura.class, detalleFactura2.getId());
            System.out.println("DetalleFactura 3: Cantidad: " + retrievedDF3.getCantidad() + ", Subtotal: " + retrievedDF3.getSubtotal() + ", Factura: " + (retrievedDF3.getFactura() != null ? retrievedDF3.getFactura().getNumero() : "No Asociada"));

            DetalleFactura retrievedDF4 = entityManager.find(DetalleFactura.class, detalleFactura3.getId());
            System.out.println("DetalleFactura 4: Cantidad: " + retrievedDF4.getCantidad() + ", Subtotal: " + retrievedDF4.getSubtotal() + ", Factura: " + (retrievedDF4.getFactura() != null ? retrievedDF4.getFactura().getNumero() : "No Asociada"));

            Domicilio retrievedDomicilio = entityManager.find(Domicilio.class, domicilio1.getId());
            System.out.println("Domicilio 1: Calle: " + retrievedDomicilio.getNombre_calle() + ", Numero: " + retrievedDomicilio.getNumero());

            Domicilio retrievedDomicilio1 = entityManager.find(Domicilio.class, domicilio2.getId());
            System.out.println("Domicilio 2: Calle: " + retrievedDomicilio1.getNombre_calle() + ", Numero: " + retrievedDomicilio1.getNumero());

            Factura retrievedFactura = entityManager.find(Factura.class, factura.getId());
            System.out.println("Factura 1: Numero: " + retrievedFactura.getNumero() + ", Fecha: " + retrievedFactura.getFecha() + ", Precio: " + retrievedFactura.getPrecio() + ", Cliente: " + retrievedFactura.getCliente().getNombre() + " " + retrievedFactura.getCliente().getApellido() + ", Detalles: " + retrievedFactura.getDetalles().stream().map(detalle -> "Cantidad: " + detalle.getCantidad() + ", Subtotal: " + detalle.getSubtotal()).toList());

            Factura retrievedFactura1 = entityManager.find(Factura.class, factura1.getId());
            System.out.println("Factura 2: Numero: " + retrievedFactura1.getNumero() + ", Fecha: " + retrievedFactura1.getFecha() + ", Precio: " + retrievedFactura1.getPrecio() + ", Cliente: " + retrievedFactura1.getCliente().getNombre() + " " + retrievedFactura1.getCliente().getApellido() + ", Detalles: " + retrievedFactura1.getDetalles().stream().map(detalle -> "Cantidad: " + detalle.getCantidad() + ", Subtotal: " + detalle.getSubtotal()).toList());



        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar");
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
