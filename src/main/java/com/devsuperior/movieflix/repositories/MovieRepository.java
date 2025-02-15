package com.devsuperior.movieflix.repositories;


import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT DISTINCT obj FROM Movie obj WHERE :genreResult IS NULL OR obj.genre = :genreResult ORDER BY obj.title")
    Page<Movie> find(Genre genreResult, Pageable pageable);

}
