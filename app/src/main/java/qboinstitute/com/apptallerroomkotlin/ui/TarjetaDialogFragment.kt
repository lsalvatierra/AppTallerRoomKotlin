package qboinstitute.com.apptallerroomkotlin.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import qboinstitute.com.apptallerroomkotlin.R
import qboinstitute.com.apptallerroomkotlin.db.entity.TarjetaEntity
import qboinstitute.com.apptallerroomkotlin.viewmodel.TarjetaDialogViewModel


class TarjetaDialogFragment : DialogFragment() {

    //private var idtarjeta = 0
    private lateinit var mViewModel: TarjetaDialogViewModel
    private lateinit var ettitulo: EditText
    private lateinit var etcontenido:EditText
    private lateinit var rgcolor: RadioGroup
    private lateinit var rbtnamarillo: RadioButton
    private lateinit var rbtnrojo:RadioButton
    private lateinit var rbtnverde:RadioButton
    private lateinit var swimportante: Switch


    companion object {
        fun newInstance() = TarjetaDialogFragment()
    }


    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tarjeta_dialog_fragment, container, false)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*if(arguments != null){
            idtarjeta = arguments!!.getInt("idtarjeta");
        }
         */
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mViewModel = ViewModelProvider(activity!!).get(
            TarjetaDialogViewModel::class.java
        )
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.tarjeta_dialog_fragment, null)
        ettitulo = view.findViewById(R.id.ettitulo)
        etcontenido = view.findViewById(R.id.etcontenido)
        rgcolor = view.findViewById(R.id.rgcolor)
        rbtnverde = view.findViewById(R.id.rbtnverde)
        rbtnrojo = view.findViewById(R.id.rbtnrojo)
        rbtnamarillo = view.findViewById(R.id.rbtnamarillo)
        swimportante = view.findViewById(R.id.swimportante)

        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setTitle("Nueva tarjeta")
        builder.setMessage("Introduzca los datos de la tarjeta.")
            .setPositiveButton("Guardar", DialogInterface.OnClickListener { dialog, which ->
                val titulo: String = ettitulo.text.toString()
                val contenido: String = etcontenido.text.toString()
                var color = "Amarillo"
                when (rgcolor.checkedRadioButtonId) {
                    R.id.rbtnrojo -> color = "Rojo"
                    R.id.rbtnverde -> color = "Verde"
                }
                val esimportante: Boolean = swimportante.isChecked
                mViewModel.insertar(
                    TarjetaEntity(
                        0, titulo, contenido, esimportante, color!!
                    )
                )
                /*
                    if (idtarjeta > 0) {
                        mViewModel.actualizar(
                            TarjetaEntity(
                                idtarjeta, titulo, contenido, esimportante, color!!
                            )
                        )
                    } else {
                        mViewModel.insertar(
                            TarjetaEntity(
                                0, titulo, contenido, esimportante, color!!
                            )
                        )
                    }
                 */

                dialog.dismiss()
            })
            .setNegativeButton("Cancelar",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        /*
        if (idtarjeta > 0) {
                    mViewModel.obtenerTarjeta(idtarjeta)
                .observe(activity!!,
                    { tarjetaEntity ->
                        tarjetaEntity?.let {
                            CargarInfoTarjeta(it)
                        }
                    })
        }
         */

        builder.setView(view)
        return builder.create()
    }

    private fun CargarInfoTarjeta(tarjetaEntity: TarjetaEntity) {
        etcontenido.setText(tarjetaEntity.contenido)
        ettitulo.setText(tarjetaEntity.titulo)
        swimportante.isChecked = tarjetaEntity.importe
        when (tarjetaEntity.color) {
            "Amarillo" -> rbtnamarillo.isChecked = true
            "Rojo" -> rbtnrojo.isChecked = true
            "Verde" -> rbtnverde.isChecked = true
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}