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
                model.customerCheckout();
                break;
            case "sort":
                sortProducts();
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

    public void CheckoutSuccessful()
    {
        view.dispose();
        System.exit(0);
        // TODO: 2022-01-09 go back to main screen or end program or send to order page
    }

    private void sortProducts()
    {
        switch (view.getSortSelectedItem())
        {
            case "Best Match":
                model.setInventory("select * from products order by productId");
                break;
            case "Price Low To High":
                model.setInventory("select * from products order by price");
                break;
            case "Price High To Low":
                model.setInventory("select * from products order by price desc");
                break;
            case "A-Z":
                model.setInventory("select * from products order by name");
                break;
            case "Z-A":
                model.setInventory("select * from products order by name desc");
                break;
            default:
                break;
        }
    }
}
