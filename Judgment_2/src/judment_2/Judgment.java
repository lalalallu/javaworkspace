package judment_2;

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
        this.setSize(700,200);
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
        String[] strings={"发送","接收","计算得分","结束","排序","打开","保存"};
        button=new JButton[7];
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
            Socket socket=new ServerSocket(port).accept();
            ssc=new SSC(socket);
            list.add(ssc);
            port++;
            new Thread(ssc).start();
        }

    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == this.button[0])
        {
            this.tableModel.setValueAt(this.t1.getText(),this.tableModel.getRowCount()-2,0);
            this.tableModel.setRowCount(this.tableModel.getRowCount()+1);
            for (int i=0;i<list.size();i++)
            {
                new SendThread(list.get(i),t1,list).start();
            }
//            for (int i=0;i<list.size();i++)
//            {
//                try
//                {
//                    list.get(i).cout = new PrintWriter(list.get(i).socket.getOutputStream(), true);
//                    list.get(i).cout.println(this.t1.getText());
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//            }
        }
        if (event.getSource() == this.button[1])
        {
            for (int i = 0; i < list.size(); i++)
            {
                new ReviseThread(list.get(i),t1,list,tableModel,i).start();
//                String numb="";
//                try
//                {
//                    new ReviseThread(list.get(i),t1,list,tableModel).start();
//                    //numb=list.get(i).bufreader.readLine();
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                this.tableModel.setValueAt(numb,this.tableModel.getRowCount()-3, i+1);
            }
        }
        if (event.getSource() == this.button[2])
        {
            for (int i=0;i<this.tableModel.getRowCount()-2;i++)
            {
                double sum=0;
                for (int j=1;j<this.tableModel.getColumnCount()-1;j++)
                {
                    if (this.tableModel.getValueAt(i,j)!=null)
                    {
                        sum+=Double.parseDouble(this.tableModel.getValueAt(i,j).toString());
                    }
                }
                this.tableModel.setValueAt(sum/(this.tableModel.getColumnCount()-2),i,this.tableModel.getColumnCount()-1);
            }
        }
        if (event.getSource() == this.button[4])
        {
            String[][] strings=new String[this.tableModel.getRowCount()-2][this.tableModel.getColumnCount()];
             for (int i=0;i<this.tableModel.getRowCount()-2;i++)
             {
                 for (int j=0;j<this.tableModel.getColumnCount();j++)
                 {
                     strings[i][j]=this.tableModel.getValueAt(i,j).toString();
                 }
             }
            for (int i=0;i<this.tableModel.getRowCount()-3;i++)//直接排序
            {
                int max=i;
                for (int j=i+1;j< this.tableModel.getRowCount()-2;j++)
                {
                    if (Double.parseDouble(strings[j][this.tableModel.getColumnCount()-1])>Double.parseDouble(strings[max][this.tableModel.getColumnCount()-1]))
                    {
                        max=j;
                    }
                }
                if (max!=i)
                {
                    String[] strings1=new String[this.tableModel.getColumnCount()];
                    for (int k=0;k<this.tableModel.getColumnCount();k++)
                    {
                        strings1[k]=strings[max][k];
                    }
                    for (int k=0;k<this.tableModel.getColumnCount();k++)
                    {
                        strings[max][k]=strings[i][k];
                    }
                    for (int k=0;k<this.tableModel.getColumnCount();k++)
                    {
                        strings[i][k]=strings1[k];
                    }
                }
            }
            for (int i=0;i<this.tableModel.getRowCount()-2;i++)
            {
                for (int j=0;j<this.tableModel.getColumnCount();j++)
                {
                    this.tableModel.setValueAt(strings[i][j],i,j);
                }
            }
        }
        if (event.getSource() == this.button[5])
        {
            JFileChooser jfc=new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
            jfc.showOpenDialog(this);
            try
            {
                InputStream in = new FileInputStream(jfc.getSelectedFile());
                ObjectInputStream objectInputStream = new ObjectInputStream(in);
                this.tableModel.setRowCount(objectInputStream.readInt());
                this.tableModel.setColumnCount(objectInputStream.readInt());
                String[] titles=new String[this.tableModel.getColumnCount()];
                for (int i=0;i<this.tableModel.getColumnCount();i++)
                {
                    titles[i]=objectInputStream.readObject().toString();
                }
                Judgment.this.tableModel.setColumnIdentifiers(titles);
                for (int i=0;i<this.tableModel.getRowCount();i++)
                {
                    for (int j=0;j<this.tableModel.getColumnCount();j++)
                    {
                         this.tableModel.setValueAt(objectInputStream.readObject(),i,j);
                    }
                }
                objectInputStream.close();
                in.close();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (FileNotFoundException ex)
            {
                JOptionPane.showMessageDialog(null, "\""+jfc.getSelectedFile().toString()+"\"文件不存在.");
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(null, "读取文件时数据错误");
            }
        }
        if (event.getSource() == this.button[3]||event.getSource() == this.button[6])
        {
            JFileChooser jfc=new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
            jfc.showSaveDialog(this);
            int rows=this.tableModel.getRowCount();
            int columns=this.tableModel.getColumnCount();
            int n=0;
            try
            {
                OutputStream o = new FileOutputStream(jfc.getSelectedFile());
                ObjectOutputStream out = new ObjectOutputStream(o); //数据字节输出流，以文件字节流作为数据源
                out.writeInt(rows);                         //写入表格模型行数，对象流也可写入int整数
                out.writeInt(columns);                      //写入表格模型列数
                for (int i=0;i<columns;i++)
                {
                    out.writeObject(this.tableModel.getColumnName(i));
                }
                for(int i=0; i<rows; i++)                      //循环，写入表格模型每行、列单元格对象
                {
                    for(int j=0; j<columns; j++)
                    {
                        Object obj=this.tableModel.getValueAt(i,j); //获得表格指定单元格的对象，父类对象obj引用子类实例
                        out.writeObject(obj);               //写入一个对象，若obj==null，也写入
                        n++;
                    }
                }
                out.close();                                 //关闭对象流
                o.close();                                    //关闭文件流
                JOptionPane.showMessageDialog(null, "写入\""+jfc.getSelectedFile().toString()+"\"文件，"+rows+"行，"+columns+"列，"+n+"个对象。");
            }
            catch(FileNotFoundException ex)          //文件不存在异常，如文件路径错误、文件名是null或""
            {
                JOptionPane.showMessageDialog(null, "\""+jfc.getSelectedFile().toString()+"\"文件不存在。");
            }
            catch(IOException ex)
            {
                JOptionPane.showMessageDialog(null, "写入文件时数据错误");
            }
        }

    }

    protected class SSC extends Thread
    {
        public PrintWriter cout;
        public Socket socket;
        public Reader reader;
        public BufferedReader bufreader;
        SSC(Socket socket) throws IOException
        {
            this.socket=socket;
            this.reader=new InputStreamReader(this.socket.getInputStream());
            this.bufreader=new BufferedReader(this.reader);
            this.setname();
        }

        private void setname() throws IOException
        {
            String name=this.bufreader.readLine();
            boolean has=true;
            title2=new String[Judgment.this.tableModel.getColumnCount()];
            for (int i=0;i<Judgment.this.tableModel.getColumnCount();i++)
            {
                title2[i]=Judgment.this.tableModel.getColumnName(i);
                if (name.equals(Judgment.this.tableModel.getColumnName(i)))
                {
                    has=false;
                }
            }

            title=new String[title2.length];
            System.arraycopy(title2, 0, title, 0, title.length);

            if (has)
            {
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
        }
        public void run()
        {
            JOptionPane.showMessageDialog(null,"连接成功！");
        }
    }


    public static void main(String[] args) throws IOException
    {
        new Judgment(1002);
    }
}
