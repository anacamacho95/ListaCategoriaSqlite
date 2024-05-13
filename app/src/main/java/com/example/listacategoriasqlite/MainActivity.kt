package com.example.listacategoriasqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.listacategoriasqlite.modelo.conexiones.BDCatSqlite
import com.example.listacategoriasqlite.modelo.conexiones.BDSqlite
import com.example.listacategoriasqlite.modelo.daos.categorias.DaoCategoriasSqlite
import com.example.listacategoriasqlite.modelo.daos.tareas.DaoTareasSqlite
import com.example.listacategoriasqlite.modelo.entidades.Categoria
import com.example.listacategoriasqlite.modelo.entidades.Item
import com.example.listacategoriasqlite.modelo.entidades.Tarea
import com.example.listacategoriasqlite.modelo.interfaces.InterfaceDaoCategorias
import com.example.listacategoriasqlite.modelo.interfaces.InterfaceDaoTareas

class MainActivity : AppCompatActivity() {

    lateinit var daoCategoria: InterfaceDaoCategorias
    lateinit var daoTarea: InterfaceDaoTareas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val conexion= BDCatSqlite(this)

        daoCategoria = DaoCategoriasSqlite()
        daoTarea = DaoTareasSqlite()
        daoTarea.createConexion(conexion)
        daoCategoria.createConexion(conexion)

       // conexion.borrarArchivos()
        Log.d("pruebas", " -- Datos previos eliminados --")

        pruebas()

    }

    fun pruebas(){

        Log.d("pruebas", " *** Añado categorias: hogar y viajes ***")
        var hogar = Categoria("Hogar")
        daoCategoria.addCategoria(hogar)
        var viajes = Categoria("Viajes")
        daoCategoria.addCategoria(viajes)

        Log.d("pruebas", " *** Veo categorias añadidas ***")
        val muestraCategorias1: List<Categoria> = daoCategoria.getCategorias()
        muestraCategorias1.forEach {
            Log.d("pruebas", it.nombre)
        }

        Log.d("pruebas", " *** Obtengo Categoria Hogar *** ")
        val obtHogar: Categoria = daoCategoria.getCategoria("Hogar")
        Log.d("pruebas", obtHogar.toString())

        Log.d("pruebas", " *** Añado tareas a la categoria Hogar *** ")
        var cocina = Tarea("Cocina")
        daoTarea.addTarea(cocina)
        var aseo = Tarea( "Aseo")
        daoTarea.addTarea(aseo)
        val tareasHogar1: List<Tarea> = daoTarea.getTareas(daoCategoria.getCategoria("Hogar").idCategoria)
        tareasHogar1.forEach {
            Log.d("pruebas", it.nombre)
        }

        Log.d("pruebas", " *** Obtengo Tarea Cocina *** ")
        val obtCocina: Tarea = daoTarea.getTarea("Cocina")
        Log.d("pruebas", obtCocina.toString())

        Log.d("pruebas", " *** Añado items a la tarea Cocina *** ")
        var coc1 = Item("Hacer canelones", false)
        daoTarea.addItem(coc1)
        val itemsCocina: List<Item> = daoTarea.getItems(daoTarea.getTarea("Cocina").idTarea)
        itemsCocina.forEach{
            Log.d("pruebas", it.accion)
        }
        Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(daoTarea.getTarea("Cocina").idTarea))

        Log.d("pruebas", " *** Obtengo Item Hacer Canelones *** ")
        val obtCanelones: Item = daoTarea.getItem("Hacer canelones")
        Log.d("pruebas", obtCanelones.toString())

        Log.d("pruebas", " *** Añado tareas a la categoria Viajes *** ")
        var playa = Tarea( "Playas")
        daoTarea.addTarea(playa)
        var mont = Tarea( "Montañas")
        daoTarea.addTarea(mont)
        val tareasViaje: List<Tarea> = daoTarea.getTareas(daoCategoria.getCategoria("Viajes").idCategoria)
        tareasViaje.forEach {
            Log.d("pruebas", it.nombre)
        }

        Log.d("pruebas", " *** Muestro todas las categorias con sus tareas e items *** ")
        val muestraCategorias2: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias2) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(categoria.idCategoria)
            for (tarea in tareas) {
                Log.d("pruebas", "Tarea: ${tarea.nombre}")

                val items: List<Item> = daoTarea.getItems(tarea.idTarea)
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
                Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(tarea.idTarea))
            }
        }

        Log.d("pruebas", " *** Actualizo item de cocina (Canelones por lavavajillas) y Actualizo nombre de tarea Aseo por Habitación *** ")
        var actualizoItem = daoTarea.getItem("Hacer canelones")
        actualizoItem.accion="Poner lavavajillas"
        daoTarea.updateItem(actualizoItem)

        var actualizoTarea = daoTarea.getTarea("Aseo")
        actualizoTarea.nombre="Habitación"
        daoTarea.updateNombreTarea(actualizoTarea)

        val tareasHogar2: List<Tarea> = daoTarea.getTareas(daoCategoria.getCategoria("Hogar").idCategoria)
        for (tarea in tareasHogar2) {
            Log.d("pruebas", "Tarea: ${tarea.nombre}")
            val items: List<Item> = daoTarea.getItems(tarea.idTarea)
            for (item in items) {
                Log.d("pruebas", "- ${item.accion}")
            }
            Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(tarea.idTarea))
        }

        Log.d("pruebas", " *** Actualizo nombre de categoria Hogar por Casa *** ")
        var actualizoCategoria= daoCategoria.getCategoria("Hogar")
        actualizoCategoria.nombre="Casa"
        daoCategoria.updateCategoria(actualizoCategoria)

        val muestraCategorias3: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias3) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(categoria.idCategoria)
            for (tarea in tareas) {
                Log.d("pruebas", "Tarea: ${tarea.nombre}")

                val items: List<Item> = daoTarea.getItems(tarea.idTarea)
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
                Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(tarea.idTarea))
            }
        }

        Log.d("pruebas", " *** Elimino item (lavavajillas) de la tarea Cocina *** ")
        daoTarea.deleteItem(daoTarea.getItem("Poner lavavajillas"))
        val muestraCategorias4: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias4) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(categoria.idCategoria)
            for (tarea in tareas) {
                Log.d("pruebas", "Tarea: ${tarea.nombre}")

                val items: List<Item> = daoTarea.getItems(tarea.idTarea)
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
                Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(tarea.idTarea))
            }
        }

        Log.d("pruebas", " *** Elimino tarea (Cocina) de la Categoria Casa *** ")
        daoTarea.deleteTarea(daoTarea.getTarea("Cocina"))
        val muestraCategorias5: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias5) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(categoria.idCategoria)
            for (tarea in tareas) {
                Log.d("pruebas", "Tarea: ${tarea.nombre}")

                val items: List<Item> = daoTarea.getItems(tarea.idTarea)
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
                Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(tarea.idTarea))
            }
        }

        Log.d("pruebas", " *** Elimino categoria Casa *** ")
        daoCategoria.deleteCategoria(daoCategoria.getCategoria("Casa"))
        val muestraCategorias6: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias6) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(categoria.idCategoria)
            for (tarea in tareas) {
                Log.d("pruebas", "Tarea: ${tarea.nombre}")

                val items: List<Item> = daoTarea.getItems(tarea.idTarea)
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
                Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(tarea.idTarea))
            }
        }
    }
}