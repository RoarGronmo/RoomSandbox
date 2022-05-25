package no.norva24.roomsandbox.datalayer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import no.norva24.roomsandbox.datalayer.localdata.dao.AppDao
import no.norva24.roomsandbox.datalayer.localdata.entities.OrderEntity
import no.norva24.roomsandbox.datalayer.localdata.entities.OrderLineEntity

@Database(
    entities = [
        OrderEntity::class,
        OrderLineEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase:RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object{

        @Volatile private var instance: AppDatabase?=null

        fun getInstance(context: Context):AppDatabase = instance
            ?: synchronized(this){
                instance?:buildDatabase(context).also{instance = it}
            }

        private fun buildDatabase(context: Context):AppDatabase{
            return Room.databaseBuilder(context, AppDatabase::class.java, "RoomSandbox")
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                }).build()
        }
    }


}