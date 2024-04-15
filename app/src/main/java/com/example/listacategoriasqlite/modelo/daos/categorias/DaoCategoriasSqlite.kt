package com.example.listacategoriasqlite.modelo.daos.categorias

import android.content.ContentValues
import com.example.listacategoriasqlite.modelo.conexiones.BDCatSqlite
import com.example.listacategoriasqlite.modelo.conexiones.BDSqlite
import com.example.listacategoriasqlite.modelo.entidades.Categoria
import com.example.listacategoriasqlite.modelo.interfaces.InterfaceDaoCategorias

class DaoCategoriasSqlite: InterfaceDaoCategorias {
    lateinit var conexion: BDSqlite

    override fun createConexion(con: BDSqlite) {
        conexion = (con as BDCatSqlite).conexion
    }
    override fun addCategoria(ca: Categoria) {
        val db = conexion.writableDatabase
        val values = ContentValues()
        values.put("nombre", ca.nombre)
        db.insert("categoria", null, values)
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
                    cursor.getString(2)
                )
                categoria.idCategoria=cursor.getInt(1)

                lista.add(categoria)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }

    override fun getCategoria(nombre: String):Categoria? {
        val query = "SELECT * FROM Categoria WHERE nombre = ?"
        val selectionArgs = arrayOf(nombre)
        val db=conexion.readableDatabase
        val cursor = db.rawQuery(query, selectionArgs)

        if (cursor.moveToFirst()) {
            val categoria = Categoria(
                cursor.getString(2)
            )
            categoria.idCategoria=cursor.getInt(1)
            cursor.close()
            return categoria
        } else {
            cursor.close()
            return null
        }
    }

    override fun updateCategoria(caAnt: Categoria, caNue: Categoria) {
        TODO("Not yet implemented")
    }

    override fun deleteCategoria(ca: Categoria) {
        TODO("Not yet implemented")
    }

}