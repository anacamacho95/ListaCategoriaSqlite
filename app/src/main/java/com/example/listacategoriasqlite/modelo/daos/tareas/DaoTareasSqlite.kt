package com.example.listacategoriasqlite.modelo.daos.tareas

import android.content.ContentValues
import com.example.listacategoriasqlite.modelo.conexiones.BDCatSqlite
import com.example.listacategoriasqlite.modelo.conexiones.BDSqlite
import com.example.listacategoriasqlite.modelo.entidades.Categoria
import com.example.listacategoriasqlite.modelo.entidades.Item
import com.example.listacategoriasqlite.modelo.entidades.Tarea
import com.example.listacategoriasqlite.modelo.interfaces.InterfaceDaoTareas

class DaoTareasSqlite:InterfaceDaoTareas {

    lateinit var conexion: BDSqlite

    override fun createConexion(con: BDCatSqlite) {
        conexion = (con as BDCatSqlite).conexion
    }

    override fun addTarea(ca: Categoria, ta: Tarea) {
        val db = conexion.writableDatabase
        val values = ContentValues().apply {
            put("nombre", ta.nombre)
            put("idCategoriaTarea", ca.idCategoria)
        }
        db.insert("Tarea", null, values)
        conexion.close()
    }

    override fun getTareas(ca: Categoria): MutableList<Tarea> {
        val listaTareas = mutableListOf<Tarea>()

        val queryTareas = "SELECT * FROM Tarea WHERE idCategoriaTarea = ?"
        val selectionArgsTareas = arrayOf(ca.idCategoria.toString())

        val db = conexion.readableDatabase
        val cursorTareas = db.rawQuery(queryTareas, selectionArgsTareas)

        if (cursorTareas.moveToFirst()) {
            do {
                val tarea = Tarea(cursorTareas.getString(1)) // nombre de la tarea
                tarea.idTarea = cursorTareas.getInt(0) // id de la tarea
                listaTareas.add(tarea)
            } while (cursorTareas.moveToNext())
        }

        cursorTareas.close()
        return listaTareas
    }

    override fun getTarea(ta: Tarea): Tarea? {
        var tarea: Tarea? = null

        val query = "SELECT * FROM Tarea WHERE idTarea = ?"
        val selectionArgs = arrayOf(ta.idTarea.toString())

        val db = conexion.readableDatabase
        val cursor = db.rawQuery(query, selectionArgs)

        if (cursor.moveToFirst()) {
            tarea = Tarea(cursor.getString(2)) // El índice 2 corresponde a la columna 'nombre' en la tabla 'Tarea'
            tarea.idTarea = cursor.getInt(0) // El índice 0 corresponde a la columna 'idTarea'
        }

        cursor.close()

        return tarea    }

    override fun updateNombreTarea(ca: Categoria, taAnt: Tarea, taNue: Tarea) {
        val db = conexion.writableDatabase
        val values = ContentValues().apply {
            put("nombre", taNue.nombre)
        }
        val selection = "idTarea = ? AND idCategoriaTarea = ?"
        val selectionArgs = arrayOf(taAnt.idTarea.toString(), ca.idCategoria.toString())
        db.update("Tarea", values, selection, selectionArgs)
        conexion.close()
    }

    override fun deleteTarea(ca: Categoria, ta: Tarea) {
        val db = conexion.writableDatabase
        val selection = "idTarea = ? AND idCategoriaTarea = ?"
        val selectionArgs = arrayOf(ta.idTarea.toString(), ca.idCategoria.toString())
        db.delete("Tarea", selection, selectionArgs)
        conexion.close()
    }

    override fun getNItems(ca: Categoria, ta: Tarea): Int {
        var nItems = 0

        val queryItems = "SELECT COUNT(*) FROM Item WHERE idTareaItem = ?"
        val selectionArgsItems = arrayOf(ta.idTarea.toString())

        val db = conexion.readableDatabase
        val cursor = db.rawQuery(queryItems, selectionArgsItems)

        if (cursor.moveToFirst()) {
            nItems = cursor.getInt(0)
        }
        cursor.close()
        return nItems
    }

    override fun addItem(ca: Categoria, ta: Tarea, ite: Item) {
        val db = conexion.writableDatabase
        val values = ContentValues().apply {
            put("accion", ite.accion)
            put("activo", if (ite.activo) 1 else 0)
            put("idTareaItem", ta.idTarea)
        }
        db.insert("Item", null, values)
        conexion.close()
    }

    override fun getItems(ca: Categoria, ta: Tarea): MutableList<Item> {
        val listaItems = mutableListOf<Item>()

        val queryItems = "SELECT * FROM Item WHERE idTareaItem = ?"
        val selectionArgsItems = arrayOf(ta.idTarea.toString())

        val db = conexion.readableDatabase
        val cursorItems = db.rawQuery(queryItems, selectionArgsItems)

        if (cursorItems.moveToFirst()) {
            do {
                val item = Item(cursorItems.getString(1), cursorItems.getInt(2) == 1) // accion e activo del item
                item.idItem = cursorItems.getInt(0) // id del item
                listaItems.add(item)
            } while (cursorItems.moveToNext())
        }

        cursorItems.close()
        return listaItems
    }

    override fun getItem(it: Item): Item? {
        var item: Item? = null

        val query = "SELECT * FROM Item WHERE idItem = ?"
        val selectionArgs = arrayOf(it.idItem.toString())

        val db = conexion.readableDatabase
        val cursor = db.rawQuery(query, selectionArgs)

        if (cursor.moveToFirst()) {
            item = Item(cursor.getString(2),
                cursor.getInt(3) == 1) // Los índices 2 y 3 corresponden a las columnas 'accion' y 'activo'
            item.idItem = cursor.getInt(0) // El índice 0 corresponde a la columna 'idItem'
        }

        cursor.close()

        return item
    }

    override fun updateItem(ca: Categoria, ta: Tarea, iteAnt: Item, iteNue: Item) {
        val db = conexion.writableDatabase
        val values = ContentValues().apply {
            put("accion", iteNue.accion)
            put("activo", if (iteNue.activo) 1 else 0)
        }
        val selection = "idItem = ? AND idTareaItem = ?"
        val selectionArgs = arrayOf(iteAnt.idItem.toString(), ta.idTarea.toString())
        db.update("Item", values, selection, selectionArgs)
        conexion.close()
    }

    override fun deleteItem(ca: Categoria, ta: Tarea, ite: Item) {
        val db = conexion.writableDatabase
        val selection = "idItem = ? AND idTareaItem = ?"
        val selectionArgs = arrayOf(ite.idItem.toString(), ta.idTarea.toString())
        db.delete("Item", selection, selectionArgs)
        conexion.close()
    }

    /*
    En el método addItem y updateItem, estamos usando un valor entero (1 o 0) para el campo activo,
    ya que SQLite no tiene un tipo de datos booleano. Cuando guardamos el valor en la base de datos,
    convertimos true a 1 y false a 0. Al leer el valor de la base de datos, convertimos
    1 a true y 0 a false.
    */
}