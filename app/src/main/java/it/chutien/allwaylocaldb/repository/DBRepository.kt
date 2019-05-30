package it.chutien.allwaylocaldb.repository

import io.realm.RealmList
import it.chutien.allwaylocaldb.realm.model.DogRealmObject
import it.chutien.allwaylocaldb.room.model.Dog

/**
 * Created by ChuTien on ${1/25/2017}.
 */
interface DBRepository {

    fun insertByRom(dog: Dog)

    fun getByRom(): List<Dog>

    fun insertByRealm(dogRealmObject: DogRealmObject)

    fun getByRealm(): MutableList<DogRealmObject>

}