package com.example.listacategoriasqlite.modelo.entidades

import java.io.Serializable

class Categoria ( var nombre: String): Serializable {
    var idCategoria = 0

    //Una categoria tiene muchas tareas
    var tareas : MutableList<Tarea> = mutableListOf()

    override fun toString(): String {
        return "Categoria(nombre='$nombre')"
    }

}