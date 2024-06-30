package cl.talentodigital.alkewallet.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>

    @Insert
    fun insert(usuario: User)

    @Update
    fun update(usuario: User)

    @Delete
    fun delete(usuario: User)

}