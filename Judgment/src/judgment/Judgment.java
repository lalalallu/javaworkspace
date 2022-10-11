package judgment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Judgment extends JFrame implements ActionListener
{
    protected DefaultTableModel tableModel;
    protected JPanel p1,p2;
    protected JButton[] button;
    public JTextField t1;
    protected int count=0;
    protected String[] title={"参赛选手/裁判","选手得分"};
    protected String[] title2;
    protected SSC ssc;
    protected ArrayList<SSC> list;
    Judgment(int port) throws IOException
    {
        super("裁判长");
        //this.setLayout(new GridLayout(2,1));
        this.p1=new JPanel(new BorderLayout());
        this.add(p1);
        this.setSize(600,200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.tableModel=new DefaultTableModel(title,2);
        JTable table=new JTable(this.tableModel);
        //p1.add(table);
        p1.add(new JScrollPane(table));
        p2=new JPanel();
        p1.add(p2,"South");
        JLabel label=new JLabel("选手名");
        p2.add(label);
        t1=new JTextField(10);
        p2.add(t1);
        String[] strings={"发送","计算得分","结束","排序","打开","保存"};
        button=new JButton[6];
        for (int i=0;i<strings.length;i++)
        {
            button[i]=new JButton(strings[i]);
            button[i].addActionListener(this);
            p2.add(button[i]);
        }
        this.setVisible(true);
        list=new ArrayList<>();
        while (true)
        {
            count++;
            Socket socket=new ServerSocket(port).accept();
            ssc=new SSC(socket,count);
            list.add(ssc);
            port++;
        }

    }

    private void getnum()
    {
        for (int i=0;i<list.size();i++)
        {
            try {
                String number = list.get(i).bufreader.readLine();
                Judgment.this.tableModel.setValueAt(number, Judgment.this.tableModel.getRowCount() - 2, i + 1);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == this.button[0])
        {
            this.tableModel.setValueAt(this.t1.getText(),this.tableModel.getRowCount()-2,0);
            this.tableModel.setRowCount(this.tableModel.getRowCount()+1);
            int i=0;
            while (true)
            {
                try
                {
                    list.get(i).cout = new PrintWriter(list.get(i).socket.getOutputStream(), true);
                    list.get(i++).cout.println(this.t1.getText());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void set()
    {

    }

    private class SSC implements ActionListener
    {
        public PrintWriter cout;
        public Socket socket;
        public Reader reader;
        public BufferedReader bufreader;
        SSC(Socket socket,int count) throws IOException
        {
            this.socket=socket;
            this.reader=new InputStreamReader(this.socket.getInputStream());
            this.bufreader=new BufferedReader(reader);
            this.setname();
            //String number = list.get(count).bufreader.readLine();
            //Judgment.this.tableModel.setValueAt(number, Judgment.this.tableModel.getRowCount() - 2, count + 1);
        }

        private void setname() throws IOException {
            String name=this.bufreader.readLine();
            title2=new String[title.length+1];
            System.arraycopy(title, 0, title2, 0, title.length);
            String temp=name;
            title2[title2.length-1]=temp;
            temp=title2[title2.length-2];
            title2[title2.length-2]=title2[title2.length-1];
            title2[title2.length-1]=temp;
            title=new String[title2.length];
            System.arraycopy(title2, 0, title, 0, title.length);
            Judgment.this.tableModel.setColumnIdentifiers(title);

        }
        SSC()
        {

        }
        @Override
        public void actionPerformed(ActionEvent event)
        {

        }
    }



    public static void main(String[] args) throws IOException
    {
        new Judgment(1002);
    }
}
