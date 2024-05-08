package com.example.listacategoriasqlite.modelo.interfaces

import com.example.listacategoriasqlite.modelo.conexiones.BDCatSqlite

interface InterfaceDaoConexion {
    fun createConexion (con: BDCatSqlite)

}