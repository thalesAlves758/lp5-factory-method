package factorymethod;

import entity.Rating;

import java.util.ArrayList;

public class RatingService implements IService<Rating> {
    public ArrayList<Rating> getAll() {
        return Rating.ratings;
    }

    public Rating getById(int id) {
        return Rating.ratings.stream().filter(rating -> rating.getId() == id).findFirst().orElse(null);
    }

    public void create(Rating object) {
        object.setId(Rating.ratings.size() + 1);
        Rating.ratings.add(object);
    }

    public void update(Rating object) throws Exception {
        Rating foundRating = this.getById(object.getId());

        if(foundRating == null) {
            throw new Exception("Could not find this rating");
        }

        int index = Rating.ratings.indexOf(foundRating);

        Rating.ratings.set(index, object);
    }

    public void delete(Rating object) throws Exception {
        Rating foundRating = this.getById(object.getId());

        if(foundRating == null) {
            throw new Exception("Could not find this rating");
        }

        Rating.ratings.remove(foundRating);
    }
}
