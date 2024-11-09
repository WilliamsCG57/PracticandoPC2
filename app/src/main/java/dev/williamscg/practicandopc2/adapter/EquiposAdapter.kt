package dev.williamscg.practicandopc2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
//import com.google.firebase.firestore.core.View
import dev.williamscg.practicandopc2.R
import dev.williamscg.practicandopc2.model.EquiposModel

class EquiposAdapter(private val lstEquipos: List<EquiposModel>) :
    RecyclerView.Adapter<EquiposAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivEquipo = itemView.findViewById<ImageView>(R.id.ivEquipos)
        val tvNombreEquipo = itemView.findViewById<TextView>(R.id.tvNombre)
        val tvAnioFundacion = itemView.findViewById<TextView>(R.id.tvAnio)
        val tvTitulosGanados = itemView.findViewById<TextView>(R.id.tvTitulos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_equipos, parent, false))
    }

    override fun getItemCount(): Int {
        return lstEquipos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemEquipo = lstEquipos[position]
        holder.tvNombreEquipo.text = itemEquipo.nombre
        holder.tvAnioFundacion.text = itemEquipo.anio.toString()
        holder.tvTitulosGanados.text = itemEquipo.titulos.toString()
        Picasso.get().load(itemEquipo.imageUrl).into(holder.ivEquipo)
    }
}