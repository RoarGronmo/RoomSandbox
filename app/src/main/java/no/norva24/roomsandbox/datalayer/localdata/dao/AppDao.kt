package no.norva24.roomsandbox.datalayer.localdata.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import no.norva24.roomsandbox.datalayer.localdata.entities.OrderEntity
import no.norva24.roomsandbox.datalayer.localdata.entities.OrderLineEntity

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReplaceOrder(orderEntity: OrderEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReplaceOrderLine(orderLineEntity: OrderLineEntity)



}