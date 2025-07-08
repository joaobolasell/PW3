package com.jalfredo.desafio2_v2.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jalfredo.desafio2_v2.entity.Cafe;

import java.util.List;

@Dao
public interface CafeDAO {


    @Query("SELECT * FROM Cafe")
    public List<Cafe> getallCafes();

    @Query("SELECT * FROM Cafe WHERE nome = :name")
    public List<Cafe> getCafeByName(String name);

    @Insert(onConflict = REPLACE)
    public void insert(Cafe cafe);

    @Update
    public void update(Cafe cafe);

    @Delete
    public void delete(Cafe cafe);

}
