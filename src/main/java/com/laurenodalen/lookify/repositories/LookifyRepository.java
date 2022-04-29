package com.laurenodalen.lookify.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.laurenodalen.lookify.models.Song;

@Repository 
public interface LookifyRepository extends CrudRepository<Song, Long>{
	List<Song> findAll();
	List<Song> findTop10ByOrderByRatingDesc();
    List<Song> findByArtist(String artist);
    
    @Query(value="SELECT * FROM songs WHERE artist=(:searchString)", nativeQuery=true)
    List<Song> searchartist(@Param("searchString")String searchString);
    
    @Query(value="SELECT * FROM songs WHERE title=(:searchString)", nativeQuery=true)
    List<Song> searchsong(@Param("searchString")String searchString);
}
