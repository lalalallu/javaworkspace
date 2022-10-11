package Master;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;


public class MyMatrix extends JFrame implements ActionListener, CaretListener {
    public JTextField juzheng1, juzheng2, juzheng3, ch1, ch2, ch3;
    public int n = 0;
    public MyTable[] myTables = new MyTable[10];
    protected JPanel jPanel;
    private JButton jButton, addMat, timeMat;
    private Socket socket;
    private DataInputStream dataInputStream;

    public MyMatrix() {
        super("矩阵");
        this.setBounds(0, 0, 1000, 1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.jPanel = new JPanel();
        this.getContentPane().add(this.jPanel, "North");
        this.jPanel.add(this.jButton = new JButton("增加矩阵"));

        this.jPanel.add(new JLabel("       加法: 矩阵"));
        this.jPanel.add(this.juzheng1 = new JTextField(3));   //文本框1
        this.juzheng1.addCaretListener(this);
        this.jPanel.add(new JLabel("=矩阵"));
        this.jPanel.add(this.juzheng2 = new JTextField(3));//文本框2
        this.jPanel.add(this.addMat = new JButton("+"));
        this.addMat.addActionListener(this);
        this.jPanel.add(new JLabel("矩阵"));
        this.jPanel.add(this.juzheng3 = new JTextField(3)); //文本框3
        this.jButton.addActionListener(this);
        this.juzheng2.addCaretListener(this);

        this.jPanel.add(new JLabel("       乘法： 矩阵"));
        this.jPanel.add(this.ch1 = new JTextField(3));
        this.jPanel.add(new JLabel("=矩阵"));
        this.jPanel.add(this.ch2 = new JTextField(3));
        this.jPanel.add(this.timeMat = new JButton("*"));
        this.jPanel.add(this.ch3 = new JTextField(3));
        this.timeMat.addActionListener(this);
        this.ch1.addCaretListener(this);
        this.ch2.addCaretListener(this);
        this.setVisible(true);
        try{
            this.socket=new Socket("127.0.0.1",3000);
            dataInputStream=new DataInputStream(socket.getInputStream());
            JOptionPane.showMessageDialog(this,"222");
            while(true) {
                try {
                    int row=dataInputStream.readInt();
                    int col=dataInputStream.readInt();
                    this.myTables[this.n] = new MyTable(row,col);
                    this.myTables[this.n].setTitle(this.n + "");
                    this.n++;
                }catch (EOFException ex){
                    break;
                }
            }
        }catch (IOException e){
            JOptionPane.showMessageDialog(this,"流错误");
        }
    }

    public static void main(String[] args) {
        new MyMatrix();
    }
    @Override
    public void caretUpdate(CaretEvent e) {
        if (e.getSource() == this.juzheng1)
            this.juzheng2.setText(this.juzheng1.getText());
        if (e.getSource() == this.juzheng2)
            this.juzheng1.setText(this.juzheng2.getText());
        if (e.getSource() == this.ch1)
            this.ch2.setText(this.ch1.getText());
        if (e.getSource() == this.ch2)
            this.ch1.setText(this.ch2.getText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+":
                addMat();
                break;
            case "增加矩阵": {
                this.myTables[this.n] = new MyTable();
                this.myTables[this.n].setTitle(this.n + "");
                this.n++;
            }
            break;
            case "*":
                timeMat();
                break;
            default:
                break;
        }
    }

    public void timeMat() {
        try {
            int t1 = Integer.parseInt(this.ch1.getText());
            int t2 = Integer.parseInt(this.ch3.getText());   //获取表格
            if (this.myTables[t1].defaultTableModel.getColumnCount() == this.myTables[t2].defaultTableModel.getRowCount()) {
//当1的列与2的行相等
                int timeRow=this.myTables[t1].defaultTableModel.getRowCount();         //1的行
                int timeCol=this.myTables[t2].defaultTableModel.getColumnCount();     //2的列
                int len = this.myTables[t1].defaultTableModel.getColumnCount();        //相等值
                DefaultTableModel table = new DefaultTableModel(timeRow, len);             //暂存一表格
                for (int i = 0; i < timeRow; i++)
                    for (int j = 0; j < len; j++)
                        table.setValueAt(Integer.parseInt(this.myTables[t1].defaultTableModel.getValueAt(i, j).toString()), i, j);
                this.myTables[t1].defaultTableModel.setColumnCount(timeCol);     //表格一更改列
                for (int i = 0; i < timeRow; i++)
                    for (int j = 0; j <timeCol; j++) {
                        int sum = 0;
                        for (int k = 0; k < len; k++)
                            sum += Integer.parseInt(table.getValueAt(i, k).toString()) * Integer.parseInt(this.myTables[t2].defaultTableModel.getValueAt(k, j).toString());
                        this.myTables[t1].defaultTableModel.setValueAt(sum, i, j);
                    }
                JOptionPane.showMessageDialog(this, "乘好了！！！！");
            } else
                JOptionPane.showMessageDialog(this, "矩阵不能相乘");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "有非法字符");
        }
    }

    public void addMat() {
        try {
            int t1 = Integer.parseInt(this.juzheng1.getText());
            int t2 = Integer.parseInt(this.juzheng3.getText());
            JOptionPane.showMessageDialog(this, "到这了");
            if (this.myTables[t1].defaultTableModel.getRowCount() == this.myTables[t2].defaultTableModel.getRowCount() && this.myTables[t1].defaultTableModel.getColumnCount() == this.myTables[t2].defaultTableModel.getColumnCount()) {
                for (int i = 0; i < this.myTables[t1].defaultTableModel.getRowCount(); i++)
                    for (int j = 0; j < this.myTables[t2].defaultTableModel.getColumnCount(); j++) {
                        String s = this.myTables[t1].defaultTableModel.getValueAt(i, j).toString();
                        int temp = Integer.parseInt(s);
                        String s1 = this.myTables[t2].defaultTableModel.getValueAt(i, j).toString();
                        int temp1 = Integer.parseInt(s1);
                        this.myTables[t1].defaultTableModel.setValueAt(temp + temp1, i, j);
                    }
                JOptionPane.showMessageDialog(this, "加好了呀！！");
            } else JOptionPane.showMessageDialog(this, "两个矩阵并不能相加，因为行列数不相等");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "输入矩阵格式有误，应输入1-10的数字");
        }
    }

    public class MyTable extends JFrame implements ActionListener ,Runnable{
        private static final long serialVersionUID = -4255519853901704630L;
        public JPanel jPanel;
        public JButton[] jButtons,jButtons2;
        public TextField fileName, data,rowCou,colCou;
        public DefaultTableModel defaultTableModel;
        int col=0,row=0,subcol=0,subrow=0,side=0,rowSide=0;

         MyTable(int row,int col){
            this();
            this.rowCou.setText(row+"");
            this.colCou.setText(col+"");
        }

        MyTable() {
            this.setSize(1000, 500);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setLayout(new FlowLayout());

            this.jPanel = new JPanel();
            this.getContentPane().add(this.jPanel=new JPanel());    //添加jpanel
            this.jPanel.add(new JLabel("生成矩阵阵行数"));
            this.jPanel.add(this.rowCou = new TextField(3));       //阶数
            this.jPanel.add(new JLabel("生成矩阵阵列数"));
            this.jPanel.add(this.colCou = new TextField(3));       //阶数
            String[] buttonName = {"生成随机矩阵", "增加一行","增加一列","转置"};
            this.jButtons = new JButton[4];
            for (int i = 0; i < 4; i++) {
                this.jButtons[i] = new JButton(buttonName[i]);
                this.jPanel.add(this.jButtons[i]);
                this.jButtons[i].addActionListener(this);
            }
            this.jPanel.add(new JLabel("文件地址"));
            this.jPanel.add(this.fileName=new TextField(6));
            String name []={"保存","读取上一次保存的","增加数"};
            this.jButtons2=new JButton[3];
            for (int i = 0; i < 3; i++) {
                this.jButtons2[i] = new JButton(name[i]);
                this.jPanel.add(this.jButtons2[i]);
                this.jButtons2[i].addActionListener(this);
            }
            this.jPanel.add(this.data=new TextField(5));
            JTable jTable = new JTable(this.defaultTableModel = new DefaultTableModel());
            this.getContentPane().add(new JScrollPane(jTable));
            this.setVisible(true);
        }

        public void run(){
            DefaultTableModel table=new DefaultTableModel();
            table.setRowCount(defaultTableModel.getRowCount());
            table.setColumnCount(defaultTableModel.getColumnCount());
            for (int i=0;i<defaultTableModel.getRowCount();i++)
                for (int j=0;j<defaultTableModel.getColumnCount();j++) {
                    table.setValueAt(defaultTableModel.getValueAt(i, j), i, j);
                    defaultTableModel.setValueAt(0, i, j);
                }
            for (int i=0;i<defaultTableModel.getRowCount();i++) {
                try {
                    Thread.sleep(1000);
                    for (int j = 0; j < defaultTableModel.getColumnCount(); j++){
                        this.defaultTableModel.setValueAt(table.getValueAt(i,j),i,j);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "生成随机矩阵":
                    random();
                    break;
                case "增加一行":
                    addrow();
                    break;
                case "增加一列":
                    addcol();
                    break;
                case "保存":
                    submitTable();
                    break;
                case "读取上一次保存的":
                    readFrom();
                    break;
                case "转置":
                    transmove();
                    break;
                case "增加数":
                    addTata();
                    break;
                default:
                    break;
            }
        }

        public void random() {
            try {
                if (Integer.parseInt(this.rowCou.getText()) <= 0 || Integer.parseInt(this.colCou.getText()) > 8) {
                    JOptionPane.showMessageDialog(this, "抱歉，我们并不能生成这个阶数的矩阵,请尝试生成1-8阶矩阵吧！");
                    return;
                }
                this.row = Integer.parseInt(this.rowCou.getText());
                this.col=Integer.parseInt(this.colCou.getText());
                this.defaultTableModel.setRowCount(row);
                this.defaultTableModel.setColumnCount(col);
                for (int i = 0; i < this.row; i++)
                    for (int j = 0; j < this.col;j++)
                        this.defaultTableModel.setValueAt((int) (Math.random() * 100), i, j);
                new Thread(this).start();
                JOptionPane.showMessageDialog(this, "生成成功");
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(this, "抱歉，您的输入不能转换为整数");
            }
        }

        public void addrow(){
            this.defaultTableModel.setRowCount(row+1);//设置比row多一行
            for (int j=0;j<this.col;j++)
                this.defaultTableModel.setValueAt((int) (Math.random() * 100), row,j );   //行坐标刚好为row
            row++;
        }

        public void addTata() {
            try {
                int d=Integer.parseInt(this.data.getText());
                //行改变则重置
                if (this.defaultTableModel.getRowCount()!=(rowSide+1)||side==col) {
                    rowSide=row;
                    side=0;
                    this.defaultTableModel.setRowCount(++row);
                }
                this.defaultTableModel.setValueAt(d,rowSide,side);
                side++;                                                //列边界右边移
                for (int i=side;i<col;i++)
                    this.defaultTableModel.setValueAt(0,rowSide,i);
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this,"抱歉，输入的不是1-30000的数字哦");
            }
        }

        public void addcol(){
            this.defaultTableModel.setColumnCount(col+1);
            for (int j=0;j<this.row;j++)
                this.defaultTableModel.setValueAt((int) (Math.random() * 100),j,col );
            col++;
        }

        public void submitTable() {
            try {
                if (this.row == 0) {
                    JOptionPane.showMessageDialog(this, "抱歉，还没有输入矩阵哦");
                    return;
                }
                OutputStream outputStream = new FileOutputStream(this.fileName.getText());
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                for (int i = 0; i < this.row; i++)
                    for (int j = 0; j < this.col; j++)
                        try {
                            int x = (Integer) (this.defaultTableModel.getValueAt(i, j));
                            dataOutputStream.writeInt(x);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "抱歉，矩阵有不合法的元素");
                        }
                dataOutputStream.close();
                outputStream.close();
                JOptionPane.showMessageDialog(this, "保存成功");
                        this.subcol=this.col;
                        this.subrow=this.row;
            } catch (FileNotFoundException fileNotFoundException) {
                JOptionPane.showMessageDialog(this, "文件不存在");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "文件写入错误");
            }
        }

        protected void readFrom() {
            try {
                InputStream inputStream = new FileInputStream(this.fileName.getText());
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                this.defaultTableModel.setRowCount(this.subrow);
                this.defaultTableModel.setColumnCount(this.subcol);
                for (int i = 0; i < this.subrow; i++)
                    for (int j = 0; j < this.subcol; j++)
                        try {
                            this.defaultTableModel.setValueAt(dataInputStream.readInt(), i, j);
                        } catch (EOFException e) {
                            break;
                        }
                row=subrow;
                        col=subcol;
                dataInputStream.close();
                inputStream.close();
                new Thread(this).start();
                JOptionPane.showMessageDialog(this, "读取成功");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, this.fileName + "文件不存在");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "文件读取错误");
            }
        }

        public void transmove() {
            if (this.row == 0) {
                JOptionPane.showMessageDialog(this, "抱歉，没有矩阵，不能转置");
                return;
            }
            try {
                DefaultTableModel table=new DefaultTableModel(row,col);
                for (int i=0;i<row;i++)
                    for (int j=0;j<col;j++)
                        table.setValueAt(defaultTableModel.getValueAt(i,j),i,j);
                this.defaultTableModel.setRowCount(col);
                this.defaultTableModel.setColumnCount(row);
                for(int i=0;i<row;i++)
                    for (int j=0;j<col;j++)
                        this.defaultTableModel.setValueAt(table.getValueAt(i,j),j,i);
                int t=row;
                row=col;
                col=t;
                new Thread(this).start();
                JOptionPane.showMessageDialog(this, "转置成功");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "抱歉，有非法整数，不能转置");
            }
        }
    }
}