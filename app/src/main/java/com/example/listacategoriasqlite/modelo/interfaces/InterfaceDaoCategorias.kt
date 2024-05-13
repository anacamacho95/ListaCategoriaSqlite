package com.example.listacategoriasqlite.modelo.interfaces

import com.example.listacategoriasqlite.modelo.entidades.Categoria

interface InterfaceDaoCategorias: InterfaceDaoConexion {
    //CRUD
    //crear
    fun addCategoria (ca: Categoria)
    //leer Todas las categorias
    fun getCategorias(): MutableList<Categoria>
    //obtener el objeto Categoria
    fun getCategoria (nombre: String): Categoria
    //actualizar
    fun updateCategoria(ca: Categoria)
    //borrar
    fun deleteCategoria (ca: Categoria)
}