package factorymethod;

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

    @Test
    void shouldUpdateRating() {
        int index = 0;
        Rating ratingToUpdate = Rating.ratings.get(index);
        String oldComment = ratingToUpdate.getComment();
        ratingToUpdate.setComment("Teste 2");

        try {
            ratingService.update(ratingToUpdate);
            assertEquals(ratingToUpdate, Rating.ratings.get(index));
            assertNotEquals(oldComment, Rating.ratings.get(index).getComment());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void shouldThrowAnExceptionOnDelete() {
        Rating rating = new Rating(999, 2d, "Teste");

        try {
            ratingService.delete(rating);
            fail();
        } catch (Exception e) {
            assertEquals("Could not find this rating", e.getMessage());
        }
    }

    @Test
    void shouldDeleteRating() {
        int oldLength = Rating.ratings.size();
        Rating ratingToRemove = Rating.ratings.get(0);

        try {
            ratingService.delete(ratingToRemove);
            assertEquals(oldLength - 1, Rating.ratings.size());
        } catch (Exception e) {
            fail();
        }
    }
}
