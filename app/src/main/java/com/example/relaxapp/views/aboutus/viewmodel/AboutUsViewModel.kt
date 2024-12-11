package com.example.relaxapp.views.aboutus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.relaxapp.views.aboutus.models.AboutUsInfo
import com.example.relaxapp.views.aboutus.models.AboutUsRepository
import org.json.JSONArray
import retrofit2.Response

class AboutUsViewModel : ViewModel() {

    private val aboutUsRepository = AboutUsRepository
    fun getAboutUsInfo() = liveData {
        val response: Response<List<AboutUsInfo>> = AboutUsRepository.getAboutUsInfo()
        if (response.isSuccessful) {
            val aboutUsInfo = response.body()
            emit(aboutUsInfo)
        } else {
            emit(null)
        }
    }


    private fun parseJson(json: String): AboutUsInfo {
        val jsonArray = JSONArray(json)

        val jsonObject = jsonArray.getJSONObject(0)

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
