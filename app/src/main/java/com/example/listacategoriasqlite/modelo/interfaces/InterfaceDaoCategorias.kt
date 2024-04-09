package com.example.listacategoriasqlite.modelo.interfaces

import com.example.listacategoriasqlite.modelo.entidades.Categoria

interface InterfaceDaoCategorias: InterfaceDao {
    //CRUD
    //crear
    fun addCategoria (ca: Categoria)
    //leer Todas las categorias
    fun getCategorias(): MutableList<Categoria>
    //actualizar
    fun updateCategoria(caAnt: Categoria, caNue: Categoria)
    //borrar
    fun deleteCategoria (ca: Categoria)
}