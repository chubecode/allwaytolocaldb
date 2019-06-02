package it.chutien.allwaylocaldb.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime
import java.util.*

/**
 * Created by ChuTien on ${1/25/2017}.
 */
@Entity(tableName = "room_dog")
class Dog(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    var name: String,
    var date: OffsetDateTime
)