package com.example.actividad2

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.Toast
import com.example.actividad2.data.Table
import com.example.actividad2.presentation.adapter.AdapterTareas
import com.example.actividad2.domain.DataDbHelper
import com.example.actividad2.data.items.Tareas
import kotlinx.android.synthetic.main.activity_formulario_tarea.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.formulario_eliminado.view.*

class MainActivity : AppCompatActivity() {


    private var db: SQLiteDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val helper = DataDbHelper(this)
        db = helper.writableDatabase



        val button = findViewById<FloatingActionButton>(R.id.bFormulario)
        button.setOnClickListener {
            /*val intent1 = Intent(this@MainActivity, FormularioTareaActivity::class.java)
            startActivity(intent1)*/

            val view: View = layoutInflater.inflate(R.layout.activity_formulario_tarea, null)
            val builder = AlertDialog.Builder(this).setView(view)
            val showDialog = builder.show()

            view.bFormularioEnviar.setOnClickListener {
                val nombreTarea = view.etNombreProblema.text.toString()
                val lugarTarea = view.etLugarTarea.text.toString()
                val personaTarea = view.etPersonaTarea.text.toString()
                val descripcionTarea = view.etDescripcionTarea.text.toString()
                val fechaTarea = view.etFechaTarea.text.toString()


                if (!nombreTarea.isEmpty() && !lugarTarea.isEmpty() && !personaTarea.isEmpty()
                    && !descripcionTarea.isEmpty() && !fechaTarea.isEmpty()
                ) {
                    insertar(nombreTarea,lugarTarea,personaTarea,descripcionTarea,fechaTarea)
                    Toast.makeText(this@MainActivity, "se ha guardado los datos", Toast.LENGTH_LONG).show()
                    inflater()
                } else {
                    Toast.makeText(this@MainActivity, "Rellene los campos", Toast.LENGTH_LONG).show()
                }


                showDialog.dismiss()
            }
            view.buttonCancel.setOnClickListener {

                showDialog.dismiss()
            }
        }

     val buttonEliminar=findViewById<FloatingActionButton>(R.id.bEliminar)
        buttonEliminar.setOnClickListener{
            val view: View = layoutInflater.inflate(R.layout.formulario_eliminado, null)
            val builder = AlertDialog.Builder(this).setView(view)
            val showDialog = builder.show()

            view.bEliminarT.setOnClickListener{
                val nombreTarea = view.etEliminar.text.toString()
                if (!nombreTarea.isEmpty()){

                    Toast.makeText(applicationContext,"Se eliminio el dato ",Toast.LENGTH_LONG).show()
                }
                showDialog.dismiss()
                inflater()
            }
            view.cancelar.setOnClickListener {
                showDialog.dismiss()
            }


        }
        inflater()
        val simpleItemTouchHelper = object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, p1: Int) {
             moverItem(viewHolder.itemView.tag as Long)
            }

        }
    }
    fun moverItem(id:Long){
        db!!.delete(Table.items.TABLE_NAME,Table.items.ID + "=" +id ,null)
    }

    /*fun agregar() {

        listTareas.add(
            Tareas
                (

                "Problemas con el inicio",
                "Av.Velazquez" + "\n" + "Málaga",
                "Mario Torrija",
                "Al iniciar la aplicación en un movil xiaomi la aplucacoón por una extraña razón se cierra automaticamente nada mas abrirla",
                "27/6/2019"
            )
        )

        db!!.insert(listTareas)
        listTareas.add(
            Tareas
                (

                "Problemas base de datos",
                "Calle Mauricio Moro Pareto " + "\n" + "Málaga",
                "Andrea Esteban",
                "No se quedan guardados los pedidos y estan intercambiados unos precios con otros",
                "27/6/2019"

            )
        )
         listTareas.add(
            Tareas
                (

                "Usuario erroneo",
                "Calle Obispo Hurtado" + "\n" + "Granada",
                "Paco Rodriguez",
                "Tengo un usuario creado con la aplicacón y no se guarda dicho usuario por extrañas razones",
                "27/6/2019"
            )
        )
        db!!.insert(listTareas)
        listTareas.add(
            Tareas
                (
                "La aplicación me parece racista",
                "Avenida Andalucia" + "\n" + "Jaen",
                "Ricardo Milos",
                "Simplemente me parece racista porque las letras son blancas y no negras ",
                "27/6/2019"

            )
        )
        db!!.insert(listTareas)
        listTareas.add(
            Tareas(

                "Fallo de conexión",
                ": Calle Medina" + "\n" + "Jerez de la frontera,Cadiz",
                "Camarón de la isla",
                "Fallo al hacer conexión con la base de datos",
                "27/6/2019"
            )
        )
        db!!.insert(listTareas)
    }*/

    fun inflater() {
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycleViewTareas.layoutManager = layoutManager
        val adapter = items()?.let { AdapterTareas(this@MainActivity, it) }
        recycleViewTareas.adapter = adapter

    }
    fun insertar(nombre: String, lugar: String, usuario: String, descripcion: String, fecha: String) {
        val values = ContentValues()
        values.put(Table.items.COLUMN_NOMBRE_TAREA, nombre)
        values.put(Table.items.COLUMN_LUGAR, lugar)
        values.put(Table.items.COLUMN_USUARIO, usuario)
        values.put(Table.items.COLUMN_DESCRIPCION, descripcion)
        values.put(Table.items.COLUMN_FECHA, fecha)
        db!!.insert(Table.items.TABLE_NAME, null, values)
    }

    fun items(): Cursor? {
        return db!!.query(Table.items.TABLE_NAME,null,null,
            null,null,null,null)
    }


}
