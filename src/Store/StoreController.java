package Store;

public class StoreController {
    private Store model;
    private StoreFrame view;

    public StoreController(Customer customer)
    {
        model = new Store();
        model.setCustomer(customer);
        view = new StoreFrame(this);
        model.addView(view);
    }
}
