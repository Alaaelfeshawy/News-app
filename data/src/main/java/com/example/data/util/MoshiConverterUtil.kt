package com.example.data.util
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.IOException
import java.lang.reflect.Type
import javax.inject.Inject

class MoshiConverterUtil @Inject constructor(private val moshi: Moshi) {
    /*public String userModelToJson(UserModel userModel){
        JsonAdapter<UserModel> jsonAdapter = moshi.adapter(UserModel.class);
        return jsonAdapter.toJson(userModel);
    }

    public UserModel jsonToUserModel(String json){
        JsonAdapter<UserModel> jsonAdapter = moshi.adapter(UserModel.class);
        try {
            return  jsonAdapter.fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }*/
    fun stringListToJson(strList: List<String>): String {
        val strListType: Type = Types.newParameterizedType(
            MutableList::class.java, String::class.java
        )
        val jsonAdapter = moshi.adapter<List<String>>(strListType)
        return jsonAdapter.toJson(strList)
    }

    fun jsonToStringList(json: String?): List<String>? {
        val strListType: Type = Types.newParameterizedType(
            MutableList::class.java, String::class.java
        )
        val jsonAdapter = moshi.adapter<List<String>>(strListType)
        var strList: List<String>? = null
        try {
            strList = jsonAdapter.fromJson(json!!)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return strList
    }
}