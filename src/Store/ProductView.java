package Store;

import javax.swing.*;
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
        this.setBackground(Color.GREEN);

        JLabel image = new JLabel(new ImageIcon(getClass().getResource(productImg)));
        JLabel titleText = new JLabel(productName);
        JLabel priceText = new JLabel(productPrice);

        JPanel buttonPanel = new JPanel();
        addBtn = new JButton("+");
        removeBtn = new JButton("-");

        addBtn.setActionCommand("add");
        removeBtn.setActionCommand("remove");

        addBtn.putClientProperty("product",product);
        removeBtn.putClientProperty("product",product);

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
