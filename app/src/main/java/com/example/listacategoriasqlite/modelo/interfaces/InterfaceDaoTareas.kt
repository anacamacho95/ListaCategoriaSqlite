package com.example.listacategoriasqlite.modelo.interfaces

import com.example.listacategoriasqlite.modelo.entidades.Categoria
import com.example.listacategoriasqlite.modelo.entidades.Item
import com.example.listacategoriasqlite.modelo.entidades.Tarea

interface InterfaceDaoTareas :InterfaceDaoConexion{
    //CRUD TAREAS
    //crear
    fun addTarea (ta: Tarea)
    //leer Todas las tareas
    fun getTareas(id: Int): MutableList<Tarea>
    //obtener el objeto Tarea
    fun getTarea (nombre: String): Tarea
    //actualizar
    fun updateNombreTarea(ta: Tarea)
    //borrar
    fun deleteTarea (ta: Tarea)

    //CRUD ITEMS
    //crear
    fun addItem (ite: Item)
    //leer Todos los items
    fun getItems(id: Int): MutableList<Item>
    //obtener el objeto Item
    fun getItem (nombre: String): Item
    //actualizar item
    fun updateItem(ite: Item)
    //borrar
    fun deleteItem (ite: Item)

    //NºItems
    fun getNItems(id: Int): Int

}