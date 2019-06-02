package it.chutien.allwaylocaldb.repository

import io.realm.RealmList
import it.chutien.allwaylocaldb.realm.model.DogRealmObject
import it.chutien.allwaylocaldb.room.model.Dog

/**
 * Created by ChuTien on ${1/25/2017}.
 */
interface DBRepository {

    fun insertByRom(dog: Dog)

    suspend fun getByRom(): ArrayList<Dog>

    suspend fun getSizeRom(): Int

    fun insertByRealm(dogRealmObject: DogRealmObject)

    fun getByRealm(): ArrayList<DogRealmObject>

    fun getSizeRealm(): Int

}