package factorymethod;

import entity.Category;
import entity.Rating;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RatingServiceTest {
    IService<Rating> ratingService;

    @BeforeAll
    void setUp() {
        ratingService = ServiceFactory.getService("RatingService");
        Rating.ratings.add(new Rating(1, 2d, "Teste"));
    }

    @Test
    void shouldReturnRatingList() {
        ArrayList<Rating> ratings = ratingService.getAll();

        assertTrue(ratings instanceof ArrayList<Rating>);
    }
}
