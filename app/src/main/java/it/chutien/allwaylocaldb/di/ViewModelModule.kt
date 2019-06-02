package it.chutien.allwaylocaldb.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmMigration
import io.realm.RealmSchema
import it.chutien.allwaylocaldb.repository.DBRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import it.chutien.allwaylocaldb.ui.MainActivityViewModel
import it.chutien.allwaylocaldb.repository.DBRepositoryImpl
import it.chutien.allwaylocaldb.room.db.RoomDb

/**
 * Created by ChuTien on ${1/25/2017}.
 */
val viewModelModule = module(override = true) {
    single { createDatabaseRealmName() }
    single { createDatabaseRoomName() }
//    single { createRealmMySchemaModule() }
//    single { createRealmMyMigration() }
    single { createRealm(get(),get()) }
    single { createRoom(get(), get()) }
    single { createDogDao(get()) }

    single<DBRepository> { DBRepositoryImpl(get(),get()) }

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

fun createDatabaseRealmName() = "realm_db"
fun createDatabaseRoomName() = "room_db"

fun createRealm(
    dbRealmName: String,
    context: Context
//    myRealmSchema: RealmSchema,
//    myMigrationRealm: RealmMigration
): Realm {
    Realm.init(context)
    val config = RealmConfiguration.Builder()
        .name(dbRealmName)
        .schemaVersion(1)
        .build()
    return Realm.getInstance(config)

}


fun createRoom(dbRoomName: String, context: Context) =  Room.databaseBuilder(context, RoomDb::class.java, dbRoomName).build()

fun createDogDao(roomDb: RoomDb) = roomDb.dogDao()

