package com.example.android.sunshine.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context

@Database(entities = [WeatherEntry::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class SunshineDatabase : RoomDatabase() {

    abstract fun weatherDao() : WeatherDao

    companion object {
        val LOCK = Object()
        @Volatile var instance: SunshineDatabase? = null

        fun getInstance(context: Context) : SunshineDatabase? {
            if (instance == null) {
                synchronized(LOCK) {
                    instance = Room.databaseBuilder(context.applicationContext,
                            SunshineDatabase::class.java, "weather").build()
                }
            }
            return instance
        }
    }
}