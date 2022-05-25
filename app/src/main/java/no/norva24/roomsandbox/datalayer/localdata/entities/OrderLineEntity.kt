package no.norva24.roomsandbox.datalayer.localdata.entities

import androidx.room.Entity

@Entity(tableName = "orderLines", primaryKeys = ["frmNo","ordNo","lnNo"])
data class OrderLineEntity (
    var frmNo:Int,
    var ordNo:Int,
    var lnNo:Int,
    var cfDelDt:Int?=null,
    var description:String?=null,
)