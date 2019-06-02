package it.chutien.allwaylocaldb.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.mooveit.library.Fakeit
import it.chutien.allwaylocaldb.objectbox.DogBox
import it.chutien.allwaylocaldb.realm.model.DogRealmObject
import it.chutien.allwaylocaldb.repository.DBRepository
import it.chutien.allwaylocaldb.room.model.Dog
import java.time.OffsetDateTime
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by ChuTien on ${1/25/2017}.
 */
class MainActivityViewModel(
    val repository: DBRepository
) : ViewModel() {

    fun insertBoxDb() {
        var dogBox = DogBox(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByBox(dogBox)
        dogBox = DogBox(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByBox(dogBox)
        dogBox = DogBox(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByBox(dogBox)
        dogBox = DogBox(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByBox(dogBox)
        dogBox = DogBox(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByBox(dogBox)
        dogBox = DogBox(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByBox(dogBox)
        dogBox = DogBox(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByBox(dogBox)
        dogBox = DogBox(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByBox(dogBox)
        dogBox = DogBox(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByBox(dogBox)
        dogBox = DogBox(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByBox(dogBox)

    }

    fun getBoxDb(): ArrayList<DogBox> {
        return repository.getByBox()
    }

    fun deleteRoomDb() {
        repository.deleteAllRoom()
    }

    fun deleteRealmDb() {
        repository.deleteAllRealm()
    }

    fun deleteBoxDb() {
        repository.deleteAllBox()
    }

    fun insertRealmDb() {
        var dogRealmObject = DogRealmObject(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByRealm(dogRealmObject)
        dogRealmObject = DogRealmObject(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByRealm(dogRealmObject)
        dogRealmObject = DogRealmObject(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByRealm(dogRealmObject)
        dogRealmObject = DogRealmObject(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByRealm(dogRealmObject)
        dogRealmObject = DogRealmObject(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByRealm(dogRealmObject)
        dogRealmObject = DogRealmObject(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByRealm(dogRealmObject)
        dogRealmObject = DogRealmObject(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByRealm(dogRealmObject)
        dogRealmObject = DogRealmObject(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByRealm(dogRealmObject)
        dogRealmObject = DogRealmObject(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByRealm(dogRealmObject)
        dogRealmObject = DogRealmObject(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByRealm(dogRealmObject)

    }

    fun getRealmDb(): ArrayList<DogRealmObject> {
        return repository.getByRealm()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertRoomDb() {
        var dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        repository.insertByRom(dog)
    }

    suspend fun getRoomDb(): ArrayList<Dog> {
        return repository.getByRom()
    }


    suspend fun getSizeRoom(): Int {
        return repository.getSizeRom()
    }

    fun getSizeRealm(): Int {
        return repository.getSizeRealm()
    }

    fun getSizeBox(): Int {
        return repository.getSizeObjectBox()
    }


}