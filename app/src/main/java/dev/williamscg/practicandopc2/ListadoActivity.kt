package dev.williamscg.practicandopc2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import dev.williamscg.practicandopc2.adapter.EquiposAdapter
import dev.williamscg.practicandopc2.model.EquiposModel

class ListadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)

        val db = FirebaseFirestore.getInstance()
        val rvEquipos = findViewById<RecyclerView>(R.id.rvEquipos)
        val btnNuevo = findViewById<Button>(R.id.btnNuevo)

        rvEquipos.layoutManager = LinearLayoutManager(this)

        btnNuevo.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        db.collection("equipos")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.e("Firestore Error", error.message.toString())
                    return@addSnapshotListener
                }

                val lstEquipos = value!!.documents.map { document ->
                    EquiposModel(
                        document["nombre"].toString(),
                        document["anio"].toString(),
                        document["titulos"].toString(),
                        document["imageUrl"].toString()
                    )
                }

                rvEquipos.adapter = EquiposAdapter(lstEquipos)
            }
    }

}


