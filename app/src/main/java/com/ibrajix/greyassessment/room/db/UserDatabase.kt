package com.ibrajix.greyassessment.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ibrajix.greyassessment.room.dao.UserDao
import com.ibrajix.greyassessment.room.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
  abstract  fun userDao() : UserDao
}