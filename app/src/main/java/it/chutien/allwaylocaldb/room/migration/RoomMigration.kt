package it.chutien.allwaylocaldb.room.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class RoomMigration(startVersion: Int, endVersion: Int) : Migration(startVersion, endVersion) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE room_dog "
                + " ADD COLUMN age INTEGER DEFAULT 0 NOT NULL")

    }


}