package it.chutien.allwaylocaldb.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.mooveit.library.Fakeit
import it.chutien.allwaylocaldb.realm.model.DogRealmObject
import it.chutien.allwaylocaldb.repository.DBRepository
import it.chutien.allwaylocaldb.room.model.Dog
import java.time.OffsetDateTime
import java.util.*

/**
 * Created by ChuTien on ${1/25/2017}.
 */
class MainActivityViewModel(
    val repository: DBRepository
) : ViewModel() {

    fun insertRealmDb(): String {
        val dogRealmObject = DogRealmObject(name = Fakeit.name().firstName(), date = Calendar.getInstance().time)
        repository.insertByRealm(dogRealmObject)
        return dogRealmObject.name.toString()
    }

    fun getRealmDb(): MutableList<DogRealmObject> {
        return repository.getByRealm()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertRoomDb(): String {
        val dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        repository.insertByRom(dog)
        return dog.name
    }

    fun getRoomDb(): List<Dog> {
        return repository.getByRom()
    }


}