package com.example.vinyls.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.vinyls.R
import com.example.vinyls.databinding.FragmentAlbumCreateBinding
import com.example.vinyls.viewmodels.AlbumCreateViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_album_create.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.*


class FragmentAlbumCreate : Fragment() {
    private var _binding: FragmentAlbumCreateBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlbumCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumCreateBinding.inflate(inflater, container, false)
        val view = binding.root
        //viewModelAdapter = AlbumsAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var date = binding.etReleaseDateCreateAlbum

        date.addTextChangedListener(object : TextWatcher {
            private var current = ""
            private val ddmmyyyy = "DDMMYYYY"
            private val cal: Calendar = Calendar.getInstance()
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString() != current) {
                    var clean = s.toString().replace("[^\\d.]".toRegex(), "")
                    val cleanC = current.replace("[^\\d.]".toRegex(), "")
                    val cl = clean.length
                    var sel = cl
                    var i = 2
                    while (i <= cl && i < 6) {
                        sel++
                        i += 2
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean == cleanC) sel--
                    if (clean.length < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length)
                    } else {
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        var day = clean.substring(0, 2).toInt()
                        var mon = clean.substring(2, 4).toInt()
                        var year = clean.substring(4, 8).toInt()
                        if (mon > 12) mon = 12
                        cal.set(Calendar.MONTH, mon - 1)
                        year = if (year < 1900) 1900 else if (year > 2100) 2100 else year
                        cal.set(Calendar.YEAR, year)
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012
                        day = if (day > cal.getActualMaximum(Calendar.DATE)) cal.getActualMaximum(
                            Calendar.DATE
                        ) else day
                        clean = String.format("%02d%02d%02d", day, mon, year)
                    }
                    clean = String.format(
                        "%s/%s/%s", clean.substring(0, 2),
                        clean.substring(2, 4),
                        clean.substring(4, 8)
                    )
                    sel = if (sel < 0) 0 else sel
                    current = clean
                    date.setText(current)
                    date.setSelection(if (sel < current.length) sel else current.length)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var i:Int=0
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(this, AlbumCreateViewModel.Factory(activity.application)).get(
            AlbumCreateViewModel::class.java)

        createButton()

    }

    private fun createButton() {
        binding.btnSendAlbum.setOnClickListener {
            sendDataToServer()
        }
    }

    private fun sendDataToServer() {
        var i:Int=0
        if(validateForm()){
            var strAlbum = "{\n \"name\": \"" +
                    binding.etNameCreateAlbum.text.toString() +
                    "\",\n  \"cover\":\"" +
                    binding.etCoverCreateAlbum.text.toString() +
                    "\",\n  \"releaseDate\": \"" +
                    binding.etReleaseDateCreateAlbum.text.toString() +
                    "\",\n  \"description\": \"" +
                    binding.etDescriptionCreateAlbum.text.toString() +
                    "\",\n  \"genre\": \"" +
                    getGenre() +
                    "\",\n  \"recordLabel\": \"" +
                    getRecordLabel() +
                    "\"\n}"
            Log.i("data Captured",strAlbum)

            lifecycleScope.launch{
                val idAlbumCreated = async { viewModel.createAlbumFromNetwork(JSONObject(strAlbum)) }
                i = idAlbumCreated.await()
            }

            Snackbar.make(binding.root, "Datos enviados exitosamente.", Snackbar.LENGTH_LONG)
                .setAction("¿Salir?"){
                activity?.finish()
            }.show()
            
        }


        

    }

    private fun validateForm(): Boolean {
        var isValid = true

        with(binding){
            if(etNameCreateAlbum.text.toString().isEmpty()){
                isValid = false
                tiNameCreateAlbum.error = "Campo requerido"
            }else{
                tiNameCreateAlbum.error = null
            }
            if(tbGenre.checkedButtonId==-1){
                isValid = false
                tvGenreCreateAlbum.error = "Campo requerido"
            }else{
                tvGenreCreateAlbum.error = null
            }
            if(rgRecordLabelCreateAlbum.checkedRadioButtonId==-1){
                isValid = false
                tvRecordLabelCreateAlbum.error = "Campo requerido"
            }else{
                tvGenreCreateAlbum.error = null
            }
            if(etReleaseDateCreateAlbum.text.toString().matches(".*[A-Z].*".toRegex()) || etReleaseDateCreateAlbum.text.toString().matches(".*[a-z].*".toRegex())){
               isValid = false
                tiReleaseDateCreateAlbum.error = "Datos incorrectos de fecha"
            }
            else{
                tiReleaseDateCreateAlbum.error = null
            }
            if(etDescriptionCreateAlbum.text.toString().isEmpty()){
                isValid = false
                tiDescriptionCreateAlbum.error = "Campo requerido"
            }else{
                tiDescriptionCreateAlbum.error = null
            }

            if(etCoverCreateAlbum.text.toString().isEmpty()){
                isValid = false
                tiCoverCreateAlbum.error = "Campo requerido"
            }else{
                tiCoverCreateAlbum.error = null
            }
            if(etReleaseDateCreateAlbum.text.toString().isEmpty()){
                isValid = false
                tiReleaseDateCreateAlbum.error = "Campo requerido"
            }else{
                tiReleaseDateCreateAlbum.error = null
            }
            




        }

        return isValid

    }

    private fun getGenre(): String {
        with(binding){
            return when(tbGenre.checkedButtonId){
                R.id.btnSalsa -> btnSalsa.text.toString()
                R.id.btnRock -> btnRock.text.toString()
                else -> btnFolk.text.toString()
            }
        }

    }

    private fun getRecordLabel(): String {
        with(binding){
            return when(rgRecordLabelCreateAlbum.checkedRadioButtonId){
                R.id.btnSonyMusicLabel -> btnSonyMusicLabel.text.toString()
                R.id.btnEmi -> btnEmi.text.toString()
                R.id.btnDiscosFuentes -> btnDiscosFuentes.text.toString()
                R.id.btnElektra -> btnElektra.text.toString()
                else -> btnFaniaRecords.text.toString()

            }
        }

    }
}