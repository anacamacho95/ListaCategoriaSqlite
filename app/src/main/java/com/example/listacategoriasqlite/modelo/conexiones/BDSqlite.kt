package com.example.listacategoriasqlite.modelo.conexiones

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDCatSqlite (private val context: Context){
    val conexion= BDSqlite(context)

    fun borrarArchivos() {
        val sql1="DROP TABLE Categoria"
        val sql2="DROP TABLE Tarea"
        val sql3="DROP TABLE Item"
        val db=conexion.getWritableDatabase();
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        conexion.close();
    }

}
class BDSqlite(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    val TABLA_CATEGORIA = "CREATE TABLE Categoria (" +
            "idCategoria INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT);"

    val TABLA_TAREA = "CREATE TABLE Tarea (" +
            "idTarea INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idCategoriaTarea INTEGER NOT NULL," +
            "nombre TEXT," +
            "FOREIGN KEY(idCategoriaTarea) REFERENCES Categoria(idCategoria));"

    val TABLA_ITEM= "CREATE TABLE Item (" +
            "idItem INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idTareaItem INTEGER NOT NULL," +
            "accion TEXT," +
            "activo INTEGER," +
            "FOREIGN KEY(idTareaItem) REFERENCES Tarea(idTarea));"


    val SQL_DELETE_ENTRIES=""

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLA_CATEGORIA)
        db.execSQL(TABLA_TAREA)
        db.execSQL(TABLA_ITEM)

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "categorias.db"
    }
}