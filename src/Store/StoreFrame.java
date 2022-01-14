package Store;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Concrete view of Store
 */
public class StoreFrame extends JFrame implements StoreView {

    /**
     * Controller the Store model and buttons will use
     */
    private StoreController controller;

    /**
     * The outer panel that all productViews will be added to
     */
    private JPanel productPanel;

    /**
     *  Hashmap of product keys paired to its associated productView
     */
    private HashMap<Product,ProductView> productViews;

    /**
     * The dropdown menu to sort the products
     */
    private JComboBox<String> sortBtn;

    /**
     * Constructor for StoreFrame
     *
     * @param controller                StoreController, Controller the Store model and buttons will use
     */
    public StoreFrame(StoreController controller)
    {
        super("Welcome");
        this.controller = controller;
        this.productViews = new HashMap<>();

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        //JPanel topPanel = new JPanel(new GridLayout(2,1));
        JLabel titleText = new JLabel("Baseball Exclusives Store", SwingConstants.CENTER);
        titleText.setFont(new Font("Serif", Font.PLAIN, 18));
        titleText.setForeground(Color.WHITE);

        String [] sortTitles = {"Best Match","Price Low To High","Price High To Low","A-Z","Z-A"};
        sortBtn = new JComboBox<>(sortTitles);
        sortBtn.addActionListener(controller);
        sortBtn.setActionCommand("sort");

        JLabel sortTitle = new JLabel("Sort",SwingConstants.RIGHT);
        sortTitle.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0), 10));
        sortTitle.setForeground(Color.WHITE);
        sortTitle.setFont(new Font("Serif", Font.PLAIN, 16));

        topPanel.add(titleText,BorderLayout.PAGE_START);
        topPanel.add(sortTitle,BorderLayout.CENTER);
        topPanel.add(sortBtn,BorderLayout.LINE_END);

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

        mainPanel.setBackground(new Color(0,103,103));
        topPanel.setBackground(new Color(0,0,0,0));
        bottomBtnPanel.setBackground(new Color(0,0,0,0));
        productPanel.setBackground(new Color(0,0,0,0));

        mainPanel.add(topPanel,BorderLayout.PAGE_START);
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
        this.setPreferredSize(new Dimension(600,400));
        this.setVisible(true);

    }

    /**
     * Add ProductView of each inventory products to product panel
     *
     * @param inventory             ProductStockCollection, collection of products in Store inventory
     * @param cart                  ProductStockCollection, collection of products in Customer shopping cart
     */
    public void addProductsToGUI(ProductStockCollection inventory, ProductStockCollection cart)
    {
        productPanel.removeAll();
        for(Product product:inventory.getAllProducts())
        {
            ProductView productView = new ProductView(product);
            productView.setButtonController(controller);

            if(inventory.getProductStock(product)<1)
            {
                productView.setAddBtnEnable(false);
            }

            if (!cart.contains(product))
            {
                productView.setRemoveBtnEnable(false);
            }

            productViews.put(product,productView);
            productPanel.add(productView);
        }

        this.validate();
    }

    /**
     * Update the store and a specific productView after the specified product is added or removed to customer shopping cart
     *
     * @param product               Product, the product added or removed to shopping cart
     * @param addEnable             boolean, true if add button should be enabled; false otherwise
     * @param removeEnable          boolean, true if remove button should be enabled; false otherwise
     */
    public void updateCustomerCart(Product product, boolean addEnable, boolean removeEnable)
    {
        ProductView productView = productViews.get(product);
        setAddBtnEnable(productView,addEnable);
        setRemoveBtnEnable(productView, removeEnable);

    }

    /**
     * Sets the enable value for the add button of the specified productView
     *
     * @param productView               ProductView, the product view that will be affected
     * @param enable                    boolean, true will allow button to be clickable;
     */
    private void setAddBtnEnable(ProductView productView, Boolean enable)
    {
        productView.setAddBtnEnable(enable);
    }

    /**
     * Sets the enable value for the remove button of the specified productView
     *
     * @param productView               ProductView, the product view that will be affected
     * @param enable                    boolean, true will allow button to be clickable;
     */
    private void setRemoveBtnEnable(ProductView productView, Boolean enable)
    {
        productView.setRemoveBtnEnable(enable);
    }

    /**
     * Displays the customer cart in a dialog box
     *
     * @param cart                  String, the Customer's cart in text-form
     */
    @Override
    public void displayCustomerCart(String cart)
    {
        JOptionPane.showMessageDialog(this,
                cart,
                "Current Shopping Cart",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Display dialog box with a checkout success message and calls checkoutSuccessful method on controller
     */
    @Override
    public void checkoutSuccessful() {
        JOptionPane.showMessageDialog(this,
                "You have successfully purchased your items. Thank you for your purchase, Please come visit again",
                "Checkout Successful",
                JOptionPane.INFORMATION_MESSAGE);
        controller.checkoutSuccessful();
    }

    /**
     * Display a dialog box with a checkout failed message
     *
     * @param message               String, the error message of why the checkout failed
     */
    @Override
    public void checkoutFailed(String message) {

        JOptionPane.showMessageDialog(this,
                "Checkout failed. "+message,
                "Checkout Failed",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Gets the sort item the user choose
     *
     * @return                  String, the sort option
     */
    public String getSortSelectedItem()
    {
        return (String) sortBtn.getSelectedItem();
    }
}
