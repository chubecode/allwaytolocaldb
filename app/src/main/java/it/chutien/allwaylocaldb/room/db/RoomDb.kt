package it.chutien.allwaylocaldb.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import it.chutien.allwaylocaldb.room.dao.DogDao
import it.chutien.allwaylocaldb.room.model.Dog

/**
 * Created by ChuTien on ${1/25/2017}.
 */
@Database(entities = [Dog::class],version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class RoomDb : RoomDatabase() {
    abstract fun dogDao(): DogDao
}