package factorymethod;

import entity.Rating;
import entity.WishList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WishListServiceTest {
    IService<WishList> wishListService;

    @BeforeEach
    void setUp() {
        wishListService = ServiceFactory.getService("WishListService");
        WishList.wishLists = new ArrayList<>();
        WishList.wishLists.add(new WishList(1, "Teste", "Texto descritivo"));
    }

    @Test
    void shouldReturnWishListsList() {
        ArrayList<WishList> wishLists = wishListService.getAll();

        assertTrue(wishLists instanceof ArrayList<WishList>);
    }
}
