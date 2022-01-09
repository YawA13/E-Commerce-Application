package Store;

import javax.swing.*;
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
        updateModelProducts();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand())
        {
            case "add":
                model.customerAddProduct(getProductFromButton(e));
                break;
            case "remove":
               model.customerRemoveProduct(getProductFromButton(e));
                break;
            case "view cart":
                model.getCustomerCartInText();
                break;
            case "checkout":
                //model.
                break;
            default:
                break;
        }

    }


    public void updateModelProducts()
    {
        model.setInventory();
    }

    private Product getProductFromButton(ActionEvent e)
    {
        Product product = (Product) ((JButton) e.getSource()).getClientProperty("product");
        return product;
    }
}
