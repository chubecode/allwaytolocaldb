package it.chutien.allwaylocaldb.repository

import io.realm.Realm
import io.realm.RealmList
import it.chutien.allwaylocaldb.realm.model.DogRealmObject
import it.chutien.allwaylocaldb.room.dao.DogDao
import it.chutien.allwaylocaldb.room.model.Dog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

/**
 * Created by ChuTien on ${1/25/2017}.
 */
class DBRepositoryImpl(
    val dogDao: DogDao,
    val realm: Realm
) : DBRepository {
    override fun insertByRom(dog: Dog) {
        GlobalScope.async {
            dogDao.insert(dog)
        }
    }

    override fun getByRom(): List<Dog> {
        var finalList: List<Dog> = ArrayList()
        GlobalScope.async {
            finalList = dogDao.getAll()
        }
        return finalList
    }

    override fun insertByRealm(dogRealmObject: DogRealmObject) {
        realm.executeTransaction(object : Realm.Transaction {
            override fun execute(realm: Realm) {
                realm.insert(dogRealmObject)
            }
        })
    }

    override fun getByRealm(): MutableList<DogRealmObject> {
        val realmResults = realm.where(DogRealmObject::class.java).findAll()
        val finalList = RealmList<DogRealmObject>()
        finalList.addAll(realmResults.subList(0, realmResults.size))
        return realm.copyFromRealm(realmResults)
    }

}