package com.example.listacategoriasqlite.modelo.entidades

import java.io.Serializable

class Tarea (var nombre: String ): Serializable {

    //una tarea tiene un conjunto de items
    var items: MutableList<Item> = mutableListOf()
    var nItems: Int = items.size
    var idTarea = 0
}