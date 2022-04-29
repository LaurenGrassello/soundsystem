package com.laurenodalen.lookify.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.laurenodalen.lookify.models.Playlist;
@Repository 
public interface PlaylistRepository extends CrudRepository<Playlist, Long>{
	List<Playlist> findAll();
	List<Playlist> findTop10ByOrderByRatingDesc();
    List<Playlist> findByName(String name);
    @Query(value="SELECT * FROM playlists WHERE name=(:searchString)", nativeQuery=true)
    List<Playlist> searchname(@Param("searchString")String searchString);
}