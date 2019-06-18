package it.chutien.allwaylocaldb.di

import android.content.Context
import android.text.Editable
import androidx.room.Room
import com.commonsware.cwac.saferoom.SafeHelperFactory
import it.chutien.allwaylocaldb.repository.DBRepository
import it.chutien.allwaylocaldb.repository.DBRepositoryImpl
import it.chutien.allwaylocaldb.room.db.RoomDb
import it.chutien.allwaylocaldb.room.migration.RoomMigration
import it.chutien.allwaylocaldb.ui.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by ChuTien on ${1/25/2017}.
 */
val viewModelModule = module(override = true) {
    single { createDatabaseRoomName() }
    single { createRoom(get(), get()) }
    single { createDogDao(get()) }




    single<DBRepository> { DBRepositoryImpl(get()) }

    viewModel { MainActivityViewModel(get()) }
}


//
//fun createRealmMyMigration(): RealmMigration {
//    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//}
//
//fun createRealmMySchemaModule(): RealmSchema {
//    TODO("not implemented")
//}
//chrome://inspect/#devices
fun createDatabaseRoomName() = "room_db"

fun createRoom(dbRoomName: String, context: Context): RoomDb {
    //SafeRoom
//    val factory = SafeHelperFactory.fromUser(Editable.Factory.getInstance().newEditable("key_encrypt_db"))
    return  Room
        .databaseBuilder(context, RoomDb::class.java, dbRoomName)
    .addMigrations(RoomMigration(1, 2)) // multi migration here
//        .openHelperFactory(factory)
        .build()

}

fun createDogDao(roomDb: RoomDb) = roomDb.dogDao()

