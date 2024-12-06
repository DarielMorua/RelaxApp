package com.example.relaxapp.views.aboutus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response

class AboutUsViewModel : ViewModel() {

    private val aboutUsRepository = AboutUsRepository
    fun getAboutUsInfo() = liveData {
        val response: Response<List<AboutUsInfo>> = aboutUsRepository.getAboutUsInfo()
        if (response.isSuccessful) {
            val aboutUsInfo = response.body()
            emit(aboutUsInfo)
        } else {
            emit(null)
        }
    }


    private fun parseJson(json: String): AboutUsInfo {
        // Parseamos el JSON como un arreglo de objetos
        val jsonArray = JSONArray(json)

        // Accedemos al primer objeto del arreglo
        val jsonObject = jsonArray.getJSONObject(0)

        // Extraemos los valores del objeto y los asignamos a AboutUsInfo
        return AboutUsInfo(
            dariel = jsonObject.getString("dariel"),
            juvey = jsonObject.getString("juvey"),
            materia = jsonObject.getString("materia"),
            descripcion = jsonObject.getString("descripcion"),
            linkdariel = jsonObject.getString("linkdariel"),
            linkjuvey = jsonObject.getString("linkjuvey")
        )
    }
}
