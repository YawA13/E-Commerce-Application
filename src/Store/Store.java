package Store;

import Registration.RegistrationView;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private Customer customer;
    private List<StoreView> views;
    private ProductStockCollection inventory;

    public Store()
    {
        customer = null;
        views = new ArrayList<>();
        inventory = new ProductStockMap();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addView(StoreView newView) {
        views.add(newView);
    }

    public void removeView(StoreView oldView) {
        for (int i = 0; i < views.size(); i++) {
            if (views.get(i).equals(oldView)) {
                views.remove(i);
                break;
            }
        }
    }

}
