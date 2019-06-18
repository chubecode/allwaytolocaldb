package it.chutien.allwaylocaldb.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.mooveit.library.Fakeit
import it.chutien.allwaylocaldb.repository.DBRepository
import it.chutien.allwaylocaldb.room.model.Dog
import it.chutien.allwaylocaldb.utils.EncryptionUtil
import java.time.OffsetDateTime

/**
 * Created by ChuTien on ${1/25/2017}.
 */
class MainActivityViewModel(
    val repository: DBRepository
) : ViewModel() {


    fun deleteRoomDb() {
        repository.deleteAllRoom()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun insertRoomDb(key: String) {
        var dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        dog.encrypt(key)
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        dog.encrypt(key)
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        dog.encrypt(key)
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        dog.encrypt(key)
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        dog.encrypt(key)
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        dog.encrypt(key)
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        dog.encrypt(key)
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        dog.encrypt(key)
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        dog.encrypt(key)
        repository.insertByRom(dog)
        dog = Dog(name = Fakeit.name().firstName(), date = OffsetDateTime.now())
        dog.encrypt(key)
        repository.insertByRom(dog)
    }

    suspend fun getRoomDb(): ArrayList<Dog> {
        return repository.getByRom()
    }


    suspend fun getSizeRoom(): Int {
        return repository.getSizeRom()
    }


}

private fun Dog.encrypt(key: String) {
//    this.name = AESUtil.encrypt(name)
    this.name = EncryptionUtil.encrypt(name)
}


