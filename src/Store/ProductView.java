package Store;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View for each Product within StoreView
 */
public class ProductView extends JPanel {
    /**
     * Add button
     */
    private JButton addBtn;

    /**
     * Remove btn
     */
    private JButton removeBtn;

    /**
     * Constructor for ProductView
     *
     * @param product           Product, The product associated with the ProductView
     */
    public ProductView(Product product)
    {

        String productImg = product.getImgUrl();
        String productName = product.getName();
        String productPrice = String.valueOf(product.getPrice());

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel image = new JLabel(new ImageIcon(getClass().getResource(productImg)));
        JLabel titleText = new JLabel(productName);
        JLabel priceText = new JLabel("$"+productPrice);

        JPanel buttonPanel = new JPanel();
        addBtn = new JButton("+");
        removeBtn = new JButton("-");

        addBtn.setActionCommand("add");
        removeBtn.setActionCommand("remove");

        addBtn.putClientProperty("product",product);
        removeBtn.putClientProperty("product",product);

        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);

        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleText.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceText.setAlignmentX(Component.CENTER_ALIGNMENT);

        titleText.setFont(new Font("Serif", Font.PLAIN, 18));
        titleText.setForeground(Color.WHITE);
        priceText.setFont(new Font("Serif", Font.PLAIN, 18));
        priceText.setForeground(Color.WHITE);

        buttonPanel.setBackground(new Color(0,0,0,0));
        this.setBackground(new Color(0,153,153));

        this.add(image);
        this.add(titleText);
        this.add(priceText);

        buttonPanel.add(addBtn);
        buttonPanel.add(removeBtn);
        this.add(buttonPanel);
    }

    /**
     * Sets the controller both buttons will call when clicked
     *
     * @param controller            ActionListener, Controller of buttons
     */
    public void setButtonController(ActionListener controller)
    {
        addBtn.addActionListener(controller);
        removeBtn.addActionListener(controller);
    }

    /**
     * Sets the enable value for the add button
     *
     * @param enable            boolean, true will allow button to be clickable;
     */
    public void setAddBtnEnable(boolean enable)
    {
        addBtn.setEnabled(enable);
    }

    /**
     * Sets the enable value for the remove button
     *
     * @param enable            boolean, true will allow button to be clickable;
     */
    public void setRemoveBtnEnable(boolean enable)
    {
        removeBtn.setEnabled(enable);
    }

}
