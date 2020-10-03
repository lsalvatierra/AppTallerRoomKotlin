package qboinstitute.com.apptallerroomkotlin.adapters


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import qboinstitute.com.apptallerroomkotlin.R
import qboinstitute.com.apptallerroomkotlin.db.entity.TarjetaEntity
import qboinstitute.com.apptallerroomkotlin.ui.TarjetaDialogFragment
import qboinstitute.com.apptallerroomkotlin.viewmodel.TarjetaDialogViewModel


class TarjetaAdapter(private val context: Context) : RecyclerView.Adapter<TarjetaAdapter.ViewHolder>() {

    private var listatarjeta: List<TarjetaEntity>
    private val viewModel: TarjetaDialogViewModel

    init {
        listatarjeta = ArrayList()
        viewModel = ViewModelProvider((context as AppCompatActivity))
            .get(TarjetaDialogViewModel::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarjeta, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarjeta = listatarjeta[position]
        holder.tvtitulo.text = tarjeta.titulo
        holder.tvcontenido.text = tarjeta.contenido
        if (tarjeta.importe) {
            holder.ivimportante.setImageResource(R.drawable.ic_star)
        } else {
            holder.ivimportante.setImageResource(R.drawable.ic_star_border)
        }
        /*holder.ivdelete.setOnClickListener {
            viewModel.eliminarPorID(tarjeta.id)
        }*/
        when (tarjeta.color) {
            "Amarillo" -> holder.contenedor.setCardBackgroundColor(Color.YELLOW)
            "Verde" -> holder.contenedor.setCardBackgroundColor(Color.GREEN)
            "Rojo" -> holder.contenedor.setCardBackgroundColor(Color.RED)
        }

        /*holder.contenedor.setOnClickListener {
            val parametro = Bundle()
            parametro.putInt("idtarjeta", tarjeta.id)
            val fragmentManager: FragmentManager = (context as AppCompatActivity).supportFragmentManager
            val dialogFragment = TarjetaDialogFragment()
            dialogFragment.arguments = parametro
            dialogFragment.show(fragmentManager, "NuevaTarjetaDialogFragment")
        }*/


    }

    fun setListaTarjetas(lstnuevastarjetas: List<TarjetaEntity>) {
        listatarjeta = lstnuevastarjetas
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listatarjeta.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvtitulo: TextView = itemView.findViewById(R.id.tvtitulo)
        var tvcontenido: TextView = itemView.findViewById(R.id.tvcontenido)
        var ivimportante: ImageView = itemView.findViewById(R.id.ivimportante)
        var ivdelete: ImageView = itemView.findViewById(R.id.ivdelete)
        var contenedor: CardView = itemView.findViewById(R.id.contenedor)

    }


}