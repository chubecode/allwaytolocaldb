package it.chutien.allwaylocaldb.realm.migration

import io.realm.DynamicRealm
import io.realm.RealmMigration


class RealmMigrations : RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema

        if (oldVersion == 1L) {
            val dogSchema = schema.get("DogRealmObject")
            dogSchema?.addField("age", Int::class.java)
        }
        //More migration here...
    }
}