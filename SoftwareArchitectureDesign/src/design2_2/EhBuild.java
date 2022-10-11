package design2_2;

import javax.swing.*;
import java.awt.*;

/**
 * @author lalalallu
 */

public class EhBuild extends MyFrameBuilder
{

    /**
     * build button
     */
    @Override
    public void buildButton() {
        myFrame.setButton(new JButton("button"));
    }

    /**
     * build text
     */
    @Override
    public void buildText() {
        myFrame.setText(new TextField("text-field",8));
    }

    /**
     * build label
     */
    @Override
    public void buildLabel() {
        myFrame.setLabel(new JLabel("label"));
    }

    /**
     *
     */
    @Override
    public void show()
    {
        JFrame window = new JFrame("eh");
        window.setLocationRelativeTo(null);
        window.setSize(500,100);
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel panel=new JPanel(new FlowLayout());
        window.add(panel);
        panel.add(myFrame.getText());
        panel.add(myFrame.getLabel());
        panel.add(myFrame.getButton());
    }
}
