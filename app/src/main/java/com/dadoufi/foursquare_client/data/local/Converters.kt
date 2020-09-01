package com.dadoufi.foursquare_client.data.local

import androidx.room.TypeConverter
import com.dadoufi.foursquare_client.data.model.Contact
import com.google.gson.Gson


object Converters {

    @TypeConverter
    @JvmStatic
    fun toListOfStrings(splitStringList: String): List<String> =
        splitStringList.split(",")


    @TypeConverter
    @JvmStatic
    fun fromListOfStrings(listOfString: List<String>): String = listOfString.joinToString(",")

    @TypeConverter
    @JvmStatic
    fun contactToGson(value: Contact): String = Gson().toJson(value)

    @TypeConverter
    @JvmStatic
    fun gsonToContact(value: String): Contact = Gson().fromJson(value, Contact::class.java)
}