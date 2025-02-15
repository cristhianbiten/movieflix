package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieAlternativeDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

    @Autowired
    private MovieService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        MovieDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> findReviewByMovie(@PathVariable Long id) {
        List<ReviewDTO> dto = service.findReviewByMovie(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<MovieAlternativeDTO>> findAll(@RequestParam(value = "genreId", defaultValue = "0") Long genreId,
                                                             Pageable pageable) {
        Page<MovieAlternativeDTO> list = service.findAllPaged(genreId, pageable);
        return ResponseEntity.ok().body(list);
    }


}
