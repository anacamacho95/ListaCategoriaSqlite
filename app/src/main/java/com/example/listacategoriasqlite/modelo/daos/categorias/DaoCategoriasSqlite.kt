package com.example.listacategoriasqlite.modelo.daos.categorias

import android.content.ContentValues
import com.example.listacategoriasqlite.modelo.conexiones.BDCatSqlite
import com.example.listacategoriasqlite.modelo.conexiones.BDSqlite
import com.example.listacategoriasqlite.modelo.entidades.Categoria
import com.example.listacategoriasqlite.modelo.entidades.Item
import com.example.listacategoriasqlite.modelo.entidades.Tarea
import com.example.listacategoriasqlite.modelo.interfaces.InterfaceDaoCategorias

class DaoCategoriasSqlite: InterfaceDaoCategorias {
    lateinit var conexion: BDSqlite

    override fun createConexion(con: BDCatSqlite) {
        conexion = (con as BDCatSqlite).conexion
    }
    override fun addCategoria(ca: Categoria) {
        val db = conexion.writableDatabase
        val values = ContentValues()
        values.put("nombre", ca.nombre)
        /*
        val values = ContentValues().apply {
            put("nombre", ca.nombre)
        }
        */
        try{
            db.insert("Categoria", null, values)
        }catch(e:Exception){
            val n=0
        }

        conexion.close()
    }

    override fun getCategorias(): MutableList<Categoria> {
        val lista = mutableListOf<Categoria>()
        val query = "SELECT * FROM Categoria"
        val db=conexion.readableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val categoria = Categoria(
                    cursor.getString(1)
                )
                categoria.idCategoria=cursor.getInt(0)

                lista.add(categoria)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    override fun getCategoria(nombre: String): Categoria? {
        var categoria: Categoria? = null

        val query = "SELECT * FROM Categoria WHERE nombre = ?"
        val selectionArgs = arrayOf(nombre)

        val db = conexion.readableDatabase
        val cursor = db.rawQuery(query, selectionArgs)

        if (cursor.moveToFirst()) {
            categoria = Categoria(nombre)
            categoria.idCategoria = cursor.getInt(0) // obtenemos el id de la categoría

            val queryTareas = "SELECT * FROM Tarea WHERE idCategoriaTarea = ?"
            val selectionArgsTareas = arrayOf(categoria!!.idCategoria.toString())

            val cursorTareas = db.rawQuery(queryTareas, selectionArgsTareas)

            if (cursorTareas.moveToFirst()) {
                do {
                    val tarea = Tarea(cursorTareas.getString(1)) // nombre de la tarea
                    tarea.idTarea = cursorTareas.getInt(0) // id de la tarea

                    val queryItems = "SELECT * FROM Item WHERE idTareaItem = ?"
                    val selectionArgsItems = arrayOf(tarea.idTarea.toString())

                    val cursorItems = db.rawQuery(queryItems, selectionArgsItems)

                    if (cursorItems.moveToFirst()) {
                        do {
                            val item = Item(cursorItems.getString(1), cursorItems.getInt(2) == 1) // accion e activo del item
                            item.idItem = cursorItems.getInt(0) // id del item
                            tarea.items.add(item)
                        } while (cursorItems.moveToNext())
                    }

                    cursorItems.close()

                    categoria.tareas.add(tarea)
                } while (cursorTareas.moveToNext())
            }

            cursorTareas.close()
        }

        cursor.close()

        return categoria
    }

    override fun updateCategoria(caAnt: Categoria, caNue: Categoria) {
        val db = conexion.writableDatabase
        val values = ContentValues().apply {
            put("nombre", caNue.nombre)
        }
        val selection = "idCategoria = ?"
        val selectionArgs = arrayOf(caAnt.idCategoria.toString())
        db.update("Categoria", values, selection, selectionArgs)
        conexion.close()
    }

    override fun deleteCategoria(ca: Categoria) {
        val db = conexion.writableDatabase
        val selection = "idCategoria = ?"
        val selectionArgs = arrayOf(ca.idCategoria.toString())
        db.delete("Categoria", selection, selectionArgs)
        conexion.close()
    }

}