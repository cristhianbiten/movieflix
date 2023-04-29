package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        Optional<Movie> obj = repository.findById(id);
        Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
        return new MovieDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findReviewByMovie(Long id) {
        List<Review> reviewList = reviewRepository.findReviewByMovieId(id);
        return reviewList.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());

    }

    public Page<MovieDTO> findAllPaged(Long genreId, Pageable pageable) {
        Optional<Genre> genre = (genreId == 0) ? null : genreRepository.findById(genreId);
        Genre genreResult = genre != null ? genre.orElse(null) : null;
        Page<Movie> page = repository.find(genreResult,  pageable);
        return page.map(x -> new MovieDTO(x));
    }
}
