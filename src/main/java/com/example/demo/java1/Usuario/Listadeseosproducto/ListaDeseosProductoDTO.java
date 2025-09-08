package com.example.demo.java1.Usuario.Listadeseosproducto;

public class ListaDeseosProductoDTO {

    private Integer idLista;
    private Integer idProducto;

    public ListaDeseosProductoDTO() {}

    public ListaDeseosProductoDTO(Integer idLista, Integer idProducto) {
        this.idLista = idLista;
        this.idProducto = idProducto;
    }

    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
}
