package it.chutien.allwaylocaldb.repository

import it.chutien.allwaylocaldb.room.dao.DogDao
import it.chutien.allwaylocaldb.room.model.Dog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

/**
 * Created by ChuTien on ${1/25/2017}.
 */
class DBRepositoryImpl(
    val dogDao: DogDao
) : DBRepository {
    override fun deleteAllRoom() {
        GlobalScope.async {
            dogDao.deleteAll()
        }
    }

    override suspend fun getSizeRom(): Int {
        return GlobalScope.async {
            dogDao.getSize()
        }.await()
    }


    override fun insertByRom(dog: Dog) {
        GlobalScope.async {
            dogDao.insert(dog)
        }

    }

    override suspend fun getByRom(): ArrayList<Dog> {
        return GlobalScope.async {
            ArrayList(dogDao.getAll())
        }.await()

    }



}