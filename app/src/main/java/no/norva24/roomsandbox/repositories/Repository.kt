package no.norva24.roomsandbox.repositories

import android.content.Context
import no.norva24.roomsandbox.datalayer.database.AppDatabase
import no.norva24.roomsandbox.datalayer.localdata.entities.OrderEntity
import no.norva24.roomsandbox.datalayer.localdata.entities.OrderLineEntity

class Repository private constructor(
    context: Context
){
    val applicationContext: Context = context.applicationContext

    val appDatabase: AppDatabase = AppDatabase.getInstance(applicationContext)

    fun destroy() {
        instance = null
    }

    companion object {
        @Volatile private var instance : Repository?=null

        fun getInstance(context: Context) = instance?.let {
            it
        } ?: synchronized(this){
            instance ?:Repository(context).also {
                instance = it
            }
        }

        fun destroy() = instance?.destroy()


        suspend fun insertReplaceOrder(orderEntity: OrderEntity) = instance?.insertReplaceOrder(orderEntity = orderEntity)
        suspend fun insertReplaceOrderLine(orderLineEntity: OrderLineEntity) = instance?.insertReplaceOrderLine(orderLineEntity = orderLineEntity)


    }

    suspend fun insertReplaceOrder(orderEntity: OrderEntity) = appDatabase.appDao().insertReplaceOrder(orderEntity = orderEntity)
    suspend fun insertReplaceOrderLine(orderLineEntity: OrderLineEntity) = appDatabase.appDao().insertReplaceOrderLine(orderLineEntity = orderLineEntity)
}