package com.example.listacategoriasqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listacategoriasqlite.modelo.conexiones.BDSqlite
import com.example.listacategoriasqlite.modelo.entidades.Categoria
import com.example.listacategoriasqlite.modelo.interfaces.InterfaceDaoCategorias

class MainActivity : AppCompatActivity() {
    lateinit var categoria: Categoria
    lateinit var daoCategorias: InterfaceDaoCategorias
    lateinit var conexion: BDSqlite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}