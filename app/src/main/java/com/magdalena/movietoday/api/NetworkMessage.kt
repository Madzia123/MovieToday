package com.magdalena.movietoday.api

import okhttp3.ResponseBody
import org.json.JSONObject

object NetworkMessage {
    fun getErrorMessage(errorBody: ResponseBody?): String {
        val jsonObject = JSONObject(errorBody?.string())
        return jsonObject.getString("status_message")
    }
}