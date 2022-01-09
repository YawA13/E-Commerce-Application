package Store;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashMap;

public class StoreFrame extends JFrame implements StoreView {

    private StoreController controller;
    private JPanel productPanel;
    private HashMap<Product,ProductView> productViews;

    public StoreFrame(StoreController controller)
    {
        super("Welcome");
        this.controller = controller;
        this.productViews = new HashMap<>();

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleText = new JLabel("Baseball Exclusives Store", SwingConstants.CENTER);
        titleText.setFont(new Font("Serif", Font.PLAIN, 18));

        JPanel bottomBtnPanel = new JPanel(new GridLayout(1,2));
        JButton viewCartBtn = new JButton("View Cart");
        viewCartBtn.setActionCommand("view cart");
        viewCartBtn.addActionListener(controller);

        JButton checkoutBtn = new JButton("Checkout");
        checkoutBtn.setActionCommand("checkout");
        checkoutBtn.addActionListener(controller);

        bottomBtnPanel.add(viewCartBtn);
        bottomBtnPanel.add(checkoutBtn);

        productPanel = new JPanel(new WrapLayout());
        JScrollPane scrollPane = new JScrollPane(mainPanel);

        mainPanel.add(titleText,BorderLayout.PAGE_START);
        mainPanel.add(bottomBtnPanel, BorderLayout.PAGE_END);
        mainPanel.add(productPanel, BorderLayout.CENTER);

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
        this.setSize(900,400);
        this.setPreferredSize(new Dimension(600,300));
        this.setVisible(true);

    }

    public void addProductsToGUI(Collection<Product> products)
    {
        for(Product product:products)
        {
            ProductView productView = new ProductView(product);
            productView.setButtonController(controller);
            productView.setRemoveBtnEnable(false);

            productViews.put(product,productView);
            productPanel.add(productView);
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

    @Override
    public void displayCustomerCart(String cart)
    {
        JOptionPane.showMessageDialog(this,
                cart,
                "Current Shopping Cart",
                JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void checkoutSuccessful() {
        JOptionPane.showMessageDialog(this,
                "You have successfully purchased your items",
                "Checkout Successful",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void checkoutFailed() {

        JOptionPane.showMessageDialog(this,
                "Checkout failed. Please try again",
                "Checkout Failed",
                JOptionPane.ERROR_MESSAGE);
    }
}
