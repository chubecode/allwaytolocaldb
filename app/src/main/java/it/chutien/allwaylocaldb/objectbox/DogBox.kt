package it.chutien.allwaylocaldb.objectbox

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.util.*

@Entity
data class DogBox(
    @Id var id: Long = 0,
    var name: String,
    var date: Date
//    var age: Int = 9
)