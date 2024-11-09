package dev.williamscg.practicandopc2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import dev.williamscg.practicandopc2.model.EquiposModel

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etUrl = findViewById<EditText>(R.id.etUrl)
        val etNombreEquipo = findViewById<EditText>(R.id.etNombreEquipo)
        val etAnio = findViewById<EditText>(R.id.etAnio)
        val etTitulos = findViewById<EditText>(R.id.etTitulos)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val db = FirebaseFirestore.getInstance()


        btnGuardar.setOnClickListener {
            val nombreEquipo = etNombreEquipo.text.toString()
            val anioFundacion = etAnio.text.toString()
            val titulosGanados = etTitulos.text.toString()
            val urlImagen = etUrl.text.toString()

            // Validación de campos vacíos
            if (nombreEquipo.isEmpty() || anioFundacion.isEmpty() || titulosGanados.isEmpty() || urlImagen.isEmpty()) {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Por favor, completa todos los campos",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Conversión segura de titulosGanados a entero
            val equipo = hashMapOf(
                "nombre" to nombreEquipo,
                "anio" to anioFundacion,
                //"titulos" to (titulosGanados.toIntOrNull() ?: 0),
                "titulos" to titulosGanados,
                "imageUrl" to urlImagen
            )

            // Guardar en Firestore
            db.collection("equipos")
                .add(equipo)
                .addOnSuccessListener {
                    Snackbar.make(
                        findViewById(android.R.id.content),
                        "Equipo registrado con éxito",
                        Snackbar.LENGTH_SHORT
                    ).show()

                    // Redirigir a la actividad de Listado
                    startActivity(Intent(this, ListadoActivity::class.java))
                    finish()
                }
                .addOnFailureListener { error ->
                    Snackbar.make(
                        findViewById(android.R.id.content),
                        "Error al registrar el equipo: ${error.message}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
        }
    }
}




