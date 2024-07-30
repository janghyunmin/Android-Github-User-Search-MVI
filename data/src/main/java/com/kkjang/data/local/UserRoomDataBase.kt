package com.kkjang.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kkjang.data.local.dao.BookmarkDao
import com.kkjang.data.local.dao.RemoteKeyDao
import com.kkjang.data.local.dao.UserDao
import com.kkjang.data.model.entity.BookmarkEntity
import com.kkjang.data.model.entity.BookmarkUserEntity
import com.kkjang.data.model.entity.RemoteKeyEntity
import com.kkjang.data.model.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        BookmarkUserEntity::class,
        BookmarkEntity::class,
        RemoteKeyEntity::class
    ],
    version = 1,
    exportSchema = false
)
internal abstract class UserRoomDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userBookmarkDao(): BookmarkDao
    abstract fun remoteKeyDao(): RemoteKeyDao

    companion object {
        private const val DB_NAME = "user.db"
        fun createUserRoomDataBase(context: Context): UserRoomDataBase {
            return Room.databaseBuilder(
                context,
                UserRoomDataBase::class.java,
                DB_NAME
            ).build()
        }
    }
}