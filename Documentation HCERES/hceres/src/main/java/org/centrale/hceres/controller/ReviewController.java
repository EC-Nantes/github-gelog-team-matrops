package org.centrale.hceres.controller;
import org.centrale.hceres.items.ReviewingJournalArticles;
import org.centrale.hceres.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    @Autowired
    ReviewService reviewService;


    @GetMapping("Api/Reviews")
    public Iterable<ReviewingJournalArticles> getReviews() {
        return reviewService.getReviews();
    }


    @GetMapping("Api/Review/{id}")
    public ReviewingJournalArticles getReview(@PathVariable("id") final Integer id) {
        Optional<ReviewingJournalArticles> review = reviewService.getReview(id);
        if(review.isPresent()) {
            return review.get();
        } else {
            return null;
        }
    }

    @PostMapping(value = "Api/AddReview")
    public ReviewingJournalArticles createReview(@RequestBody Map<String, Object> request) {
        return reviewService.saveReview(request);
    }
}
