package com.example.listacategoriasqlite.modelo.interfaces

import com.example.listacategoriasqlite.modelo.entidades.Categoria
import com.example.listacategoriasqlite.modelo.entidades.Item
import com.example.listacategoriasqlite.modelo.entidades.Tarea

interface InterfaceDaoTareas :InterfaceDaoConexion{
    //CRUD TAREAS
    //crear
    fun addTarea (ca: Categoria, ta: Tarea)
    //leer Todas las tareas
    fun getTareas(ca: Categoria): MutableList<Tarea>
    //obtener el objeto Tarea
    fun getTarea (ta: Tarea): Tarea?
    //actualizar
    fun updateNombreTarea(ca: Categoria, taAnt: Tarea, taNue: Tarea)
    //borrar
    fun deleteTarea (ca: Categoria, ta: Tarea)

    //CRUD ITEMS
    //crear
    fun addItem (ca: Categoria, ta: Tarea, ite: Item)
    //leer Todos los items
    fun getItems(ca: Categoria, ta: Tarea): MutableList<Item>
    //obtener el objeto Item
    fun getItem (it: Item): Item?
    //actualizar item
    fun updateItem(ca: Categoria, ta: Tarea,iteAnt: Item, iteNue: Item)
    //borrar
    fun deleteItem (ca: Categoria ,ta: Tarea, ite: Item)

    //NºItems
    fun getNItems(ca: Categoria, ta: Tarea): Int

}