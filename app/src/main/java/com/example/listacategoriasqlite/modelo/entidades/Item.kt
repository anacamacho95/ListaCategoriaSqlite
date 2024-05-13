package com.example.listacategoriasqlite.modelo.entidades

import java.io.Serializable

class Item (var accion: String, var activo: Boolean): Serializable {
    var idItem = 0

    override fun toString(): String {
        return "Item(accion='$accion', activo=$activo)"
    }


}