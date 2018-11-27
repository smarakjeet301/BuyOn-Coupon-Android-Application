package onlineshopping.buyon.com.buyon;

public class DataModelList {

    int image,discount;
    String redirect_link;

    public int getImage() {
        return image;
    }

    public int getDiscount() {
        return discount;
    }

    public String getRedirect_link() {
        return redirect_link;
    }

    public DataModelList(int image, int discount, String redirect_link) {

        this.image = image;
        this.discount = discount;
        this.redirect_link = redirect_link;
    }
}
