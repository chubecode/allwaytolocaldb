package it.chutien.allwaylocaldb.repository

import it.chutien.allwaylocaldb.room.model.Dog

/**
 * Created by ChuTien on ${1/25/2017}.
 */
interface DBRepository {

    fun insertByRom(dog: Dog)

    suspend fun getByRom(): ArrayList<Dog>

    suspend fun getSizeRom(): Int

    fun deleteAllRoom()

}