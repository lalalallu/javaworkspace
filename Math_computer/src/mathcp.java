import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mathcp extends JFrame implements ActionListener
{
    protected TextField text1,text2,text3;
    private static final String[] str={"+","-","*","/","%"};
    protected JComboBox<String> cb;
    protected JButton b1;
    public mathcp()
    {
        super("整数算数运算器");
        this.setLayout(new FlowLayout());
        this.setSize(500,100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(text1=new TextField(10));
        this.add(cb=new JComboBox<String>(str));
        this.cb.addActionListener(this);
        this.add(text2=new TextField(10));
        this.add(b1=new JButton("="));
        this.b1.addActionListener(this);
        this.add(text3=new TextField(10));
        this.text3.setEditable(false);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() instanceof JButton)
        {
            if(event.getSource() == b1)
            {
//                if (cb.getSelectedItem() == "+")
//                {
//                    this.add();
//                }
//                if (cb.getSelectedItem() == "-")
//                {
//                    this.minus();
//                }
//                if(cb.getSelectedItem() == "*")
//                {
//                    this.plus();
//                }
//                if(cb.getSelectedItem() == "/")
//                {
//                    this.divide();
//                }
//                if(cb.getSelectedItem() == "%")
//                {
//                    this.remainder();
//                }
                switch ((String)cb.getSelectedItem())
                {
                    case "+":this.add();break;
                    case "-":this.minus();break;
                    case "*":this.plus();break;
                    case "/":this.divide();break;
                    case "%":this.remainder();break;
                }
           }
        }
        if(event.getSource() instanceof JComboBox)
        {
//            if (event.getSource()==cb)
//            {
//                if (cb.getSelectedItem() == "+")
//                {
//                    this.add();
//                }
//                if (cb.getSelectedItem() == "-")
//                {
//                    this.minus();
//                }
//                if(cb.getSelectedItem() == "*")
//                {
//                    this.plus();
//                }
//                if(cb.getSelectedItem() == "/")
//                {
//                    this.divide();
//                }
//                if(cb.getSelectedItem() == "%")
//                {
//                    this.remainder();
//                }
//            }
            switch ((String)cb.getSelectedItem())
            {
                case "+":this.add();break;
                case "-":this.minus();break;
                case "*":this.plus();break;
                case "/":this.divide();break;
                case "%":this.remainder();break;
            }
        }
    }
    public void add()
    {
        try
        {
            if (text1.getText()==null||text2.getText()==null)
            {
                throw new NumberFormatException();
            }
            double d1=Double.parseDouble(text1.getText());
            double d2=Double.parseDouble(text2.getText());
            text3.setText(d1+d2+"");
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "内容异常！");
        }
    }
    public void minus()
    {
        try {
            if (text1.getText() == null || text2.getText() == null) {
                throw new NumberFormatException();
            }
            double d1 = Double.parseDouble(text1.getText());
            double d2 = Double.parseDouble(text2.getText());
            text3.setText(d1 - d2 + "");
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "内容异常！");
        }
    }
    public void plus()
    {
        try {
            if (text1.getText() == null || text2.getText() == null) {
                throw new NumberFormatException();
            }
            double d1 = Double.parseDouble(text1.getText());
            double d2 = Double.parseDouble(text2.getText());
            text3.setText(d1 * d2 + "");
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "内容异常！");
        }
    }
    public void divide()
    {
        try {
            if (text1.getText() == null || text2.getText() == null) {
                throw new NumberFormatException();
            }
            double d1 = Double.parseDouble(text1.getText());
            try {
                double d2 = Double.parseDouble(text2.getText());
                if (d2 == 0) {
                    throw new NumberFormatException();
                }
                text3.setText(d1 / d2 + "");
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "除数为0！");
            }
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "内容异常！");
        }
    }
    public void remainder()
    {
        try {
            if (text1.getText() == null || text2.getText() == null) {
                throw new NumberFormatException();
            }
            double d1 = Double.parseDouble(text1.getText());
            try {
                double d2 = Double.parseDouble(text2.getText());
                if (d2 == 0) {
                    throw new NumberFormatException();
                }
                text3.setText(d1 % d2 + "");
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "除数为0！");
            }
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "内容异常！");
        }
    }
}

class mathcp_ex
{
    public static void main(String[] args) {
        new mathcp();
    }
}
