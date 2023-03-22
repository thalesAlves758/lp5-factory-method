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

    @Test
    void shouldReturnSpecifiedWishList() {
        WishList wishList = wishListService.getById(1);

        assertEquals(wishList, WishList.wishLists.stream().findFirst().get());
    }

    @Test
    void shouldReturnNull() {
        WishList wishList = wishListService.getById(999);

        assertEquals(null, wishList);
    }

    @Test
    void shouldCreateWishList() {
        WishList wishList = new WishList(null, "Teste", "Texto descritivo");
        int oldLength = WishList.wishLists.size();
        wishListService.create(wishList);

        assertTrue(WishList.wishLists.contains(wishList));
        assertEquals(oldLength + 1, WishList.wishLists.size());
    }

    @Test
    void shouldThrowAnExceptionOnUpdate() {
        WishList wishList = new WishList(999, "Teste", "Texto descritivo");

        try {
            wishListService.update(wishList);
            fail();
        } catch (Exception e) {
            assertEquals("Could not find this wish list", e.getMessage());
        }
    }

    @Test
    void shouldUpdateWishList() {
        int index = 0;
        WishList wishListToUpdate = WishList.wishLists.get(index);
        String oldTitle = wishListToUpdate.getTitle();
        wishListToUpdate.setTitle("Teste 2");

        try {
            wishListService.update(wishListToUpdate);
            assertEquals(wishListToUpdate, WishList.wishLists.get(index));
            assertNotEquals(oldTitle, WishList.wishLists.get(index).getTitle());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void shouldThrowAnExceptionOnDelete() {
        WishList wishList = new WishList(999, "Teste", "Teste");

        try {
            wishListService.delete(wishList);
            fail();
        } catch (Exception e) {
            assertEquals("Could not find this wish list", e.getMessage());
        }
    }

    @Test
    void shouldDeleteWishList() {
        int oldLength = WishList.wishLists.size();
        WishList wishListToRemove = WishList.wishLists.get(0);

        try {
            wishListService.delete(wishListToRemove);
            assertEquals(oldLength - 1, WishList.wishLists.size());
        } catch (Exception e) {
            fail();
        }
    }
}
