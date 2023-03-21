package factorymethod;

import entity.WishList;

import java.util.ArrayList;

public class WishListService implements IService<WishList> {
    public ArrayList<WishList> getAll() {
        return WishList.wishLists;
    }

    public WishList getById(int id) {
        return WishList.wishLists.stream().filter(wishList -> wishList.getId() == id).findFirst().orElse(null);
    }

    public void create(WishList object) {
        object.setId(WishList.wishLists.size() + 1);
        WishList.wishLists.add(object);
    }

    public void update(WishList object) throws Exception {
        WishList foundWishList = this.getById(object.getId());

        if(foundWishList == null) {
            throw new Exception("Could not find this wish list");
        }

        int index = WishList.wishLists.indexOf(foundWishList);

        WishList.wishLists.set(index, object);
    }

    public void delete(WishList object) throws Exception {
        WishList foundWishList = this.getById(object.getId());

        if(foundWishList == null) {
            throw new Exception("Could not find this wish list");
        }

        WishList.wishLists.remove(foundWishList);
    }
}
