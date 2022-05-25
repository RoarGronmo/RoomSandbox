package no.norva24.roomsandbox.datalayer.localdata.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey

@Entity(tableName = "orders", primaryKeys = ["frmNo","ordNo"])
data class OrderEntity (
     var frmNo:Int,
     var ordNo:Int,
     var nm:String?=null,
)
