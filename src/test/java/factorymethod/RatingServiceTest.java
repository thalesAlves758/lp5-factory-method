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

    @Test
    void shouldReturnSpecifiedRating() {
        Rating rating = ratingService.getById(1);

        assertEquals(rating, Rating.ratings.stream().findFirst().get());
    }

    @Test
    void shouldReturnNull() {
        Rating rating = ratingService.getById(999);

        assertEquals(null, rating);
    }

    @Test
    void shouldCreateRating() {
        Rating rating = new Rating(null, 2d, "Teste");
        int oldLength = Rating.ratings.size();
        ratingService.create(rating);

        assertTrue(Rating.ratings.contains(rating));
        assertEquals(oldLength + 1, Rating.ratings.size());
    }

    @Test
    void shouldThrowAnExceptionOnUpdate() {
        Rating rating = new Rating(999, 2d, "Teste");

        try {
            ratingService.update(rating);
            fail();
        } catch (Exception e) {
            assertEquals("Could not find this rating", e.getMessage());
        }
    }
}
