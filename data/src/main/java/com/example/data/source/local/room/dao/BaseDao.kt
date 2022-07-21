package com.example.data.source.local.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.Completable

/**
 * Provides the methods needed for inserting, updating & deleting an entity in a Room Dao interface.
 * <br></br>
 * Make your Room Dao interface extends this BaseInterface setting the generic type as the entity
 * class. If you need to specify custom attributes on the Dao methods, just override
 * them in your Dao.
 *
 * @param <T> the entity class.
 * @author Abdallah Abdelazim
</T> */
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(objList: List<T>?): LongArray?

    @Delete
    fun delete(objList: List<T>?): Int
}