package com.ankita.newsapplication.database

import androidx.room.TypeConverter
import com.ankita.newsapplication.database.data.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name, name)
    }
}