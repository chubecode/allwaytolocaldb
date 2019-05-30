package it.chutien.allwaylocaldb.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

/**
 * Created by ChuTien on ${1/25/2017}.
 */
@Entity(tableName = "room_dog")
class Dog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String,
    var date: OffsetDateTime
)