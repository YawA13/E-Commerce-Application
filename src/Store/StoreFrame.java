package Store;

import javax.swing.*;
import java.awt.*;

public class StoreFrame extends JFrame implements StoreView {

    private StoreController controller;

    public StoreFrame(StoreController controller)
    {
        super("Baseball Exclusives");
        this.controller = controller;
        JPanel mainPanel = new JPanel(new GridBagLayout());

        /*** todo use for dual panel
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

        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setPreferredSize(new Dimension(600,300));
        this.setVisible(true);
    }

    public void addProductsToGUI()
    {
        /**
        JPanel productView = new JPanel(new BoxLayout(this, BoxLayout.Y_AXIS));
        for(Product product:)
        {

            String productImg = product.getImgUrl();
            String productName = product.getName();
            String productPrice = String.valueOf(product.getPrice());

            JLabel image = new JLabel(new ImageIcon(getClass().getResource(productImg)));
            JLabel titleText = new JLabel(productName);
            JLabel priceText = new JLabel(productPrice);

            JPanel buttonPanel = new JPanel();
            JButton addBtn = new JButton("+");
            JButton removeBtn = new JButton("-");




            productView.add(image);
            productView.add(titleText);
            productView.add(priceText);

            buttonPanel.add(addBtn);
            buttonPanel.add(removeBtn);
            productView.add(buttonPanel);

        }
         **/
    }
}
