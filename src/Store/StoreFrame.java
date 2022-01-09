package Store;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashMap;

public class StoreFrame extends JFrame implements StoreView {

    private StoreController controller;
    private JPanel mainPanel;
    private HashMap<Product,ProductView> productViews;

    public StoreFrame(StoreController controller)
    {
        super("Baseball Exclusives");
        this.controller = controller;
        this.productViews = new HashMap<>();

        mainPanel = new JPanel(new WrapLayout());
        JScrollPane scrollPane = new JScrollPane(mainPanel);

        /***
        todo use for dual panel
        mainPanel = new JPanel(new GridBagLayout());
        JPanel productPanel = new JPanel();
        JPanel cartPanel = new JPanel();
        productPanel.setBackground(Color.RED);
        cartPanel.setBackground(Color.GREEN);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 0.75;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(productPanel,gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.25;
        mainPanel.add(cartPanel,gbc);
        **/

        this.add(scrollPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setPreferredSize(new Dimension(600,300));
        this.setVisible(true);

    }

    public void addProductsToGUI(Collection<Product> products)
    {
        for(Product product:products)
        {
            String productImg = product.getImgUrl();
            String productName = product.getName();
            String productPrice = String.valueOf(product.getPrice());

            ProductView productView = new ProductView(productImg,productName,productPrice);
            productView.setButtonController(controller);
            productView.setRemoveBtnEnable(false);

            productViews.put(product,productView);
            mainPanel.add(productView);
        }

        this.validate();
    }

    public void updateCustomerCart(Product product, boolean addEnable, boolean removeEnable)
    {
        ProductView productView = productViews.get(product);
        setAddBtnEnable(productView,addEnable);
        setRemoveBtnEnable(productView, removeEnable);

    }

    private void setAddBtnEnable(ProductView productView, Boolean enable)
    {
        productView.setAddBtnEnable(enable);
    }

    private void setRemoveBtnEnable(ProductView productView, Boolean enable)
    {
        productView.setRemoveBtnEnable(enable);
    }
}
