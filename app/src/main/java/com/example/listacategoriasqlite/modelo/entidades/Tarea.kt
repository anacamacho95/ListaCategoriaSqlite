package com.example.listacategoriasqlite.modelo.entidades

import java.io.Serializable

class Tarea (var nombre: String ): Serializable {
    var idTarea = 0

    //una tarea tiene un conjunto de items
    var items: MutableList<Item> = mutableListOf()

    override fun toString(): String {
        return "Tarea(nombre='$nombre')"
    }


}