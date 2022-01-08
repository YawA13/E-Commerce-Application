package Store;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.List;

public class StoreFrame extends JFrame implements StoreView {

    private StoreController controller;
    private JPanel mainPanel;

    public StoreFrame(StoreController controller)
    {
        super("Baseball Exclusives");
        this.controller = controller;
        mainPanel = new JPanel(new WrapLayout());
        JScrollPane scrollPane = new JScrollPane(mainPanel);

        /*** todo use for dual panel
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
            JPanel productView = new JPanel();
            productView.setLayout(new BoxLayout(productView, BoxLayout.Y_AXIS));
            productView.setBackground(Color.GREEN);
            String productImg = product.getImgUrl();
            String productName = product.getName();
            String productPrice = String.valueOf(product.getPrice());

            JLabel image = new JLabel(new ImageIcon(getClass().getResource(productImg)));
            JLabel titleText = new JLabel(productName);
            JLabel priceText = new JLabel(productPrice);

            JPanel buttonPanel = new JPanel();
            JButton addBtn = new JButton("+");
            JButton removeBtn = new JButton("-");

            addBtn.setActionCommand("add");
            removeBtn.setActionCommand("remove");

            addBtn.addActionListener(controller);
            removeBtn.addActionListener(controller);

            addBtn.putClientProperty("product",product);
            removeBtn.putClientProperty("product",product);

            productView.add(image);
            productView.add(titleText);
            productView.add(priceText);

            buttonPanel.add(addBtn);
            buttonPanel.add(removeBtn);
            productView.add(buttonPanel);
            mainPanel.add(productView);
        }

        this.validate();
    }
}
