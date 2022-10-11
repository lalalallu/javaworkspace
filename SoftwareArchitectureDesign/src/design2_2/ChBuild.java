package design2_2;

import javax.swing.*;
import java.awt.*;

/**
 * @author lalalallu
 */
public class ChBuild extends MyFrameBuilder
{

    /**
     * build button
     */
    @Override
    public void buildButton() {
        myFrame.setButton(new JButton("按钮"));
    }

    /**
     * build text
     */
    @Override
    public void buildText() {
        myFrame.setText(new TextField("文本框",8));
    }

    /**
     * build label
     */
    @Override
    public void buildLabel() {
        myFrame.setLabel(new JLabel("标签"));
    }

    /**
     *
     */
    @Override
    public void show()
    {
        JFrame window = new JFrame("ch");
        window.setLocationRelativeTo(null);
        window.setSize(500,100);
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel panel=new JPanel(new FlowLayout());
        window.add(panel);
        panel.add(myFrame.getButton());
        panel.add(myFrame.getLabel());
        panel.add(myFrame.getText());
    }
}
