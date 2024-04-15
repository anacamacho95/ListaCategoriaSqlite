package com.example.listacategoriasqlite.modelo.daos.tareas

import com.example.listacategoriasqlite.modelo.conexiones.BDSqlite
import com.example.listacategoriasqlite.modelo.entidades.Categoria
import com.example.listacategoriasqlite.modelo.entidades.Item
import com.example.listacategoriasqlite.modelo.entidades.Tarea
import com.example.listacategoriasqlite.modelo.interfaces.InterfaceDaoTareas

class DaoTareasSqlite:InterfaceDaoTareas {
    override fun addTarea(ca: Categoria, ta: Tarea) {
        TODO("Not yet implemented")
    }

    override fun getTareas(ca: Categoria): MutableList<Tarea> {
        TODO("Not yet implemented")
    }

    override fun updateNombreTarea(ca: Categoria, taAnt: Tarea, taNue: Tarea) {
        TODO("Not yet implemented")
    }

    override fun deleteTarea(ca: Categoria, ta: Tarea) {
        TODO("Not yet implemented")
    }

    override fun addItem(ca: Categoria, ta: Tarea, ite: Item) {
        TODO("Not yet implemented")
    }

    override fun getItems(ca: Categoria, ta: Tarea): MutableList<Item> {
        TODO("Not yet implemented")
    }

    override fun updateItem(ca: Categoria, ta: Tarea, iteAnt: Item, iteNue: Item) {
        TODO("Not yet implemented")
    }

    override fun deleteItem(ca: Categoria, ta: Tarea, ite: Item) {
        TODO("Not yet implemented")
    }

    override fun createConexion(con: BDSqlite) {
        TODO("Not yet implemented")
    }
}