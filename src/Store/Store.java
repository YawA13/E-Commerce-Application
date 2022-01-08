package Store;

import General.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void setInventory(ProductStockCollection inventory) {
        this.inventory = inventory;
    }

    public void setInventory() {

        Connection connection = DatabaseConnection.getConnection();

        try
        {
            PreparedStatement statement = connection.prepareStatement("select * from products");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {

                int productId = resultSet.getInt("id");
                String productName = resultSet.getString("name");
                double productPrice = resultSet.getDouble("price");
                String productImg = resultSet.getString("img");
                int productStock = resultSet.getInt("stock");

                Product product = new Product(productId,productName,productPrice,productImg);
                inventory.add(product, productStock);
            }
            updateGUIProducts();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        DatabaseConnection.closeConnection();
    }

    public void updateGUIProducts()
    {
        for (StoreView v:views)
        {
            v.addProductsToGUI(inventory.getAllProducts());
        }
    }
}
