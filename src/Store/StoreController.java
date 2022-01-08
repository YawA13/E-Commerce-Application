package Store;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreController implements ActionListener {
    private Store model;
    private StoreFrame view;

    public StoreController(Customer customer)
    {
        model = new Store();
        model.setCustomer(customer);
        view = new StoreFrame(this);
        model.addView(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
