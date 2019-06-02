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
    override suspend fun getSizeRom(): Int {
        return GlobalScope.async {
            dogDao.getSize()
        }.await()
    }

    override fun getSizeRealm(): Int {
        return realm.where(DogRealmObject::class.java).count().toInt()
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

    override fun insertByRealm(dogRealmObject: DogRealmObject) {
        realm.executeTransaction { realm -> realm.insert(dogRealmObject) }
    }

    override fun getByRealm(): ArrayList<DogRealmObject> {
        val realmResults = realm.where(DogRealmObject::class.java).findAll()
        realmResults.subList(0, realmResults.size)
        return ArrayList(realm.copyFromRealm(realmResults))
    }


}