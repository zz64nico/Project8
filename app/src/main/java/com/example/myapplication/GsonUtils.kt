package com.example.myapplication

import android.text.TextUtils
import com.google.gson.*
import java.io.Reader
import java.lang.reflect.Type

/**
 * json处理类
 */
object GsonUtils {
    private var sGson: Gson? = null
    private val sParser = JsonParser()
    val instance: Gson?
        get() {
            if (sGson == null) {
                sGson = GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create()
            }
            return sGson
        }

    fun <T> fromJson(json: Reader?, type: Type?): T? {
        try {
            return instance!!.fromJson(json, type)
        } catch (e: Exception) {
        }
        return null
    }

    fun <T> parseObject(json: String?, type: Type?): T? {
        if (TextUtils.isEmpty(json)) {
            return null
        }
        try {
            return instance!!.fromJson<Any>(json, type) as T
        } catch (e: Exception) {
        }
        return null
    }

    fun parseJson(o: Any?): String? {
        return if (o == null) {
            null
        } else try {
            instance!!.toJson(o)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * convert string to JsonObject
     *
     * @param content
     * @return
     */
    fun parseJsonObject(content: String?): JsonObject? {
        return if (TextUtils.isEmpty(content)) {
            null
        } else sParser.parse(content).asJsonObject
    }

    fun parseJsonArray(content: String?): JsonArray? {
        return if (TextUtils.isEmpty(content)) {
            null
        } else sParser.parse(content).asJsonArray
    }
}