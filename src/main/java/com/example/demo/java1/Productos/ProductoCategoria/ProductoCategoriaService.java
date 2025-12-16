package com.example.demo.java1.Productos.ProductoCategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ProductoCategoriaService {

    @Autowired
    private ProductoCategoriaRepository repository;

    public void asignarCategoria(int idProducto, int idCategoria) {
        repository.insert(idProducto, idCategoria);
    }

    public void eliminarCategoriasDeProductos(List<Integer> productos) {
        repository.eliminarPorProductos(productos);
    }
    public List<Map<String, Object>> obtenerProductosPorCategoria() {

        List<Map<String, Object>> rows = repository.findProductosPorCategoria();

        Map<Integer, Map<String, Object>> categorias = new LinkedHashMap<>();

        for (Map<String, Object> row : rows) {

            Integer idCategoria = ((Number) row.get("id_categoria")).intValue();

            if (!categorias.containsKey(idCategoria)) {

                Map<String, Object> categoria = new HashMap<>();
                categoria.put("idCategoria", idCategoria);
                categoria.put("nombre", row.get("categoria"));
                categoria.put("productos", new ArrayList<>());

                categorias.put(idCategoria, categoria);
            }

            Map<String, Object> producto = new HashMap<>();
            producto.put("id_Producto", row.get("id_producto"));
            producto.put("nombre", row.get("producto"));
            producto.put("descripcion", row.get("descripcion"));
            producto.put("precio", row.get("precio"));
            producto.put("imagen", row.get("imagen"));

            ((List<Object>) categorias.get(idCategoria).get("productos")).add(producto);
        }

        return new ArrayList<>(categorias.values());
    }
}

