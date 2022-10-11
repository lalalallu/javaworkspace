import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gather extends JFrame implements ActionListener
{
    private static String[] str={"+","-","*"};
    protected DefaultListModel<Integer> listmodel1,listmodel2,listmodel3;
    protected JList<Integer> jlist1,jlist2,jlist3;
    protected Integer[] integers1={1,23,5};
    protected Integer[] integers2={1,23,5};
    protected Integer[] integers3={1,23,5};
    protected TextField text1,text2,text3;
    protected JTextArea a1,a2,a3;
    protected JPanel p1,p2,p3;
    protected JComboBox<String> cb;
    protected JButton b1,b2,b3;
    public Gather()
    {
        super("集合运算");
        //this.setLayout(new FlowLayout());
        this.setLayout(new GridLayout(2,1));
        this.setSize(600,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.p1=new JPanel();
        p1.setLayout(new FlowLayout());
        this.listmodel1=new DefaultListModel<>();
        if (integers1!=null)
        {
            for (int i=0;i<integers1.length;i++)
            {
                this.listmodel1.addElement(integers1[i]);
            }
        }
        this.jlist1=new JList<>(this.listmodel1);
        //p1.add(jlist1);
        p1.add( new JScrollPane(a1=new JTextArea(6,12)));
        this.a1.setEditable(false);
        for (int i=0;i<integers1.length;i++)
        {
            this.a1.append(""+listmodel1.getElementAt(i)+'\n');
        }

        p1.add(cb=new JComboBox<String>(str));
        this.cb.addActionListener(this);

        this.listmodel2=new DefaultListModel<>();
        if (integers2!=null)
        {
            for (int i=0;i<integers2.length;i++)
            {
                this.listmodel2.addElement(integers2[i]);
            }
        }
        this.jlist2=new JList<>(this.listmodel2);
        //p1.add(jlist2);
        p1.add(new JScrollPane(a2=new JTextArea(6,12)));
        this.a2.setEditable(false);
        for (int i=0;i<integers2.length;i++)
        {
            this.a2.append(""+listmodel2.getElementAt(i)+'\n');
        }
        p1.add(b1=new JButton("="));
        this.b1.addActionListener(this);
        this.listmodel3=new DefaultListModel<>();
        if (integers3!=null)
        {
            for (int i=0;i<integers3.length;i++)
            {
                this.listmodel3.addElement(integers3[i]);
            }
        }
        this.jlist3=new JList<>(this.listmodel3);
        //p1.add(jlist3);
        p1.add(new JScrollPane(a3=new JTextArea(6,12)));
        this.a3.setEditable(false);
        for (int i=0;i<integers3.length;i++)
        {
            this.a3.append(""+listmodel3.getElementAt(i)+'\n');
        }
        this.add(p1);
        this.p2=new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(new Label("int"));
        p2.add(text1=new TextField("123"));
        p2.add(b2=new JButton("add"));
        this.b2.addActionListener(this);
        p2.add(new Label("int"));
        p2.add(text2=new  TextField("123"));
        p2.add(b3=new JButton("add"));
        p2.add(new Label("                "));
        p2.add(new Label("                "));
        this.b3.addActionListener(this);
        this.add(p2);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() instanceof JButton)
        {
            if(event.getSource() == b1)
            {
                if (cb.getSelectedItem() == "+")
                {
                    this.add();
                }
                if (cb.getSelectedItem() == "-")
                {
                    this.minus();
                }
                if(cb.getSelectedItem() == "*")
                {
                    this.intersect();
                }
            }
            try {
                if (event.getSource() == b2) {
                    if (this.text1.getText() != null)
                    {
                        int z=1;
                        Integer i1=Integer.parseInt(this.text1.getText());
                        for (int i=0;i<listmodel1.getSize();i++)
                        {
                            if(i1==listmodel1.getElementAt(i))
                            {
                                z=0;
                                break;
                            }
                        }
                        if (z==1)
                        {
                            this.listmodel1.addElement(Integer.parseInt(this.text1.getText()));
                            this.a1.append(this.text1.getText() + '\n');
                        }
                    }
                }
                if (event.getSource() == b3)
                {
                    if (this.text2.getText() != null)
                    {
                        int z=1;
                        Integer i1=Integer.parseInt(this.text2.getText());
                        for (int i=0;i<listmodel2.getSize();i++)
                        {
                            if(i1==listmodel2.getElementAt(i))
                            {
                                z=0;
                                break;
                            }
                        }
                        if (z==1) {
                            this.listmodel2.addElement(Integer.parseInt(this.text2.getText()));
                            this.a2.append(this.text2.getText() + '\n');
                        }
                    }
                }
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(p2, "格式错误！");
            }
        }
        if(event.getSource() instanceof JComboBox)
        {
            if (event.getSource()==cb)
            {
                if (cb.getSelectedItem() == "+")
                {
                    this.add();
                }
                if (cb.getSelectedItem() == "-")
                {
                    this.minus();
                }
                if(cb.getSelectedItem() == "*")
                {
                    this.intersect();
                }
            }
        }
    }

    public void add()//并
    {
        this.listmodel3.removeAllElements();
        this.a3.setText("");
        for (int i = 0; i < listmodel1.getSize(); i++)
        {
            listmodel3.addElement(listmodel1.getElementAt(i));
            this.a3.append("" + listmodel3.getElementAt(i) + '\n');
        }
        for (int j = 0; j < listmodel2.getSize(); j++)
        {
            int z=1;
            for (int i = 0; i < listmodel3.getSize(); i++)
            {
                if(listmodel3.getElementAt(i)==listmodel2.getElementAt(j))
                {
                    z=0;
                    break;
                }
            }
            if (z==1)
            {
                listmodel3.addElement(listmodel2.getElementAt(j));
                this.a3.append("" + listmodel2.getElementAt(j) + '\n');
            }
        }
    }

    public void minus()//差
    {
        this.listmodel3.removeAllElements();
        this.a3.setText("");
        for (int i = 0; i < listmodel1.getSize(); i++)
        {
            listmodel3.addElement(listmodel1.getElementAt(i));
        }
        for (int j = 0; j < listmodel2.getSize(); j++)
        {
            for (int i = 0; i < listmodel3.getSize(); i++)
            {
                if (listmodel3.getElementAt(i) == listmodel2.getElementAt(j))
                {
                    listmodel3.removeElementAt(i);
                }
            }
        }
        for (int i = 0; i < listmodel3.getSize(); i++)
        {
            this.a3.append("" + listmodel3.getElementAt(i) + '\n');
        }
    }

    public void intersect()//交
    {
        this.listmodel3.removeAllElements();
        this.a3.setText("");
        for (int j = 0; j < listmodel1.getSize(); j++)
        {
            for (int i = 0; i < listmodel2.getSize(); i++)
            {
                if (listmodel1.getElementAt(j) == listmodel2.getElementAt(i))
                {
                    listmodel3.addElement(listmodel1.getElementAt(j));
                }
            }
        }
        for (int i = 0; i < listmodel3.getSize(); i++)
        {
            this.a3.append("" + listmodel3.getElementAt(i) + '\n');
        }
    }
}
class Gather_ex
{
    public static void main(String[] args)
    {
        new Gather();
    }
}
