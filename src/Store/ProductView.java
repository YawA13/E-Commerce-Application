package Store;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProductView extends JPanel {
    private JButton addBtn;
    private JButton removeBtn;

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

    public void setButtonController(ActionListener controller)
    {
        addBtn.addActionListener(controller);
        removeBtn.addActionListener(controller);
    }

    public void setAddBtnEnable(boolean enable)
    {
        addBtn.setEnabled(enable);
    }

    public void setRemoveBtnEnable(boolean enable)
    {
        removeBtn.setEnabled(enable);
    }

}
