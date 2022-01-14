package Store;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for Store
 */
public class StoreController implements ActionListener {
    /**
     * The model
     */
    private Store model;

    /**
     * The View
     */
    private StoreFrame view;

    /**
     * Constructor for StoreController
     *
     * @param customer              Customer, the customer using the store
     */
    public StoreController(Customer customer)
    {
        model = new Store();
        model.setCustomer(customer);
        view = new StoreFrame(this);
        model.addView(view);
        updateModelProducts();
    }

    /**
     * Determine what action occurs and call the appropriate method
     *
     * @param e                     ActionEvent, event from add button, remove button, view cart button, checkout button,
     *                              or sort dropdown menu
     */
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

    /**
     * Tells the model to update its inventory
     */
    private void updateModelProducts()
    {
        model.setInventory();
    }

    /**
     * Get the product within the add or remove button client property
     *
     * @param e                 ActionEvent, event from acd button click or remove button click
     * @return                  Product, the product associated with the button
     */
    private Product getProductFromButton(ActionEvent e)
    {
        Product product = (Product) ((JButton) e.getSource()).getClientProperty("product");
        return product;
    }

    /**
     * When checkout is successful, the frame is closed and closes the application
     */
    public void checkoutSuccessful()
    {
        view.dispose();
        System.exit(0);
        // TODO: 2022-01-09 go back to main screen or end program or send to order page
    }

    /**
     * Determine which sort was selected and sets the model inventory with the appropriate sql statement
     */
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
