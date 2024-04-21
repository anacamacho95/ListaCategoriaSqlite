package com.example.listacategoriasqlite.modelo.interfaces

import com.example.listacategoriasqlite.modelo.conexiones.BDCatSqlite
import com.example.listacategoriasqlite.modelo.conexiones.BDSqlite

interface InterfaceDao {
    fun createConexion (con: BDCatSqlite)

}