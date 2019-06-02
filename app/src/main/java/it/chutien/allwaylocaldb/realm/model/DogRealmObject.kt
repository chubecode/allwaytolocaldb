package it.chutien.allwaylocaldb.realm.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by ChuTien on ${1/25/2017}.
 */
open class DogRealmObject(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var name: String? = null,
    var date: Date? = null
//    var age: Int = 9
) : RealmObject()