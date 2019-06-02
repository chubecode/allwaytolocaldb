package it.chutien.allwaylocaldb.room.dao

import androidx.room.*
import it.chutien.allwaylocaldb.room.model.Dog

/**
 * Created by ChuTien on ${1/25/2017}.
 */
@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dog: Dog)

    @Delete
    fun delete(dog: Dog)

    @Query("DELETE FROM room_dog")
    fun deleteAll()

    @Query("SELECT * FROM room_dog")
    fun getAll(): List<Dog>

    @Query("SELECT COUNT(*) FROM room_dog")
    fun getSize(): Int


}