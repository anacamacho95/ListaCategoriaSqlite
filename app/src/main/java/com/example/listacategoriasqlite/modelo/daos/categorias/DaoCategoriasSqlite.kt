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


    override fun getCategoria(nombre: String): Categoria {

        val query = "SELECT * FROM Categoria WHERE nombre = ?"
        val selectionArgs = arrayOf(nombre)

        val db = conexion.readableDatabase
        val cursor = db.rawQuery(query, selectionArgs)

        if (cursor.moveToFirst()) {
            val categoria = Categoria(
                cursor.getString(1)
            )
            categoria.idCategoria=cursor.getInt(0)
            cursor.close()
            return categoria
        } else {
            cursor.close()
            return Categoria("Categoría no encontrada")
        }
    }

    override fun updateCategoria(ca: Categoria) {
        val db = conexion.writableDatabase
        val values = ContentValues().apply {
            put("nombre", ca.nombre)
        }
        val selection = "idCategoria = ?"
        val selectionArgs = arrayOf(ca.idCategoria.toString())
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