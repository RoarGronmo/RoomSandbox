package no.norva24.roomsandbox.repositories

import android.content.Context
import no.norva24.roomsandbox.datalayer.database.AppDatabase

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


        


    }
}