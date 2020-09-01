package com.dadoufi.foursquare_client.data.local

import androidx.room.TypeConverter

class ListStringConverter {

    @TypeConverter
    fun toListOfStrings(splitStringList: String): List<String> {
        return splitStringList.split(",")
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>): String {
        return listOfString.joinToString(",")
    }
}