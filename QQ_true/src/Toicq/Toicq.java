package Toicq;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Toicq extends JFrame implements ActionListener, MouseListener
{
    private String name;                         //网名
    private InetAddress destip;                  //目标主机名或IP地址
    private int destport;                        //目标主机的接收端口
    private JTextArea text_receiver;             //显示对话内容的文本区
    private JTextField text_sender, text_port;   //发送内容文本行和发送端口文本行
    protected JPopupMenu popupmenu;

    //构造方法，name指定网名，receiveport指定本机接收端口；
    //host指定目标主机名或IP地址，destport指定目标主机的接收端口
    public Toicq(String name, int receiveport,  String host, int destport) throws Exception
    {
        super(name+"  "+InetAddress.getLocalHost().toString()+" : "+receiveport);
        this.setBounds(320,240,560,240);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //以下在框架内容窗格中部添加显示对话内容的文本区
        this.text_receiver = new JTextArea();
        this.text_receiver.setEditable(false);
        this.text_receiver.setFont(new Font("宋体", Font.BOLD, 20));
        this.getContentPane().add(new JScrollPane(this.text_receiver));//滚动面板

        //以下工具栏，有发送内容、端口文本行和发送按钮
        JToolBar toolbar = new JToolBar();
        this.getContentPane().add(toolbar,"South");
        toolbar.add(this.text_sender=new JTextField(30));  //发送内容文本行
        JButton button = new JButton("发送");
        toolbar.add(button);
        button.addActionListener(this);
        toolbar.add(new JLabel("端口"));
        toolbar.add(this.text_port=new JTextField());      //发送端口文本行
        this.text_port.setHorizontalAlignment(JTextField.CENTER); //设置水平对齐方式为居中

        this.text_receiver.addMouseListener(this);
        this.popupmenu = new JPopupMenu();                 //快捷菜单对象
        String[] menuitems_cut={"复制","粘贴"};
        JMenuItem[] popmenuitem = new JMenuItem[menuitems_cut.length];
        for(int i=0; i<popmenuitem.length; i++)
        {
            popmenuitem[i]=new JMenuItem(menuitems_cut[i]);
            this.popupmenu.add(popmenuitem[i]);            //快捷菜单添加菜单项
            popmenuitem[i].addActionListener(this);
        }
        //popmenuitem[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));//设置快捷键Ctrl+X
        popmenuitem[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));//设置快捷键Ctrl+C
        popmenuitem[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));//设置快捷键Ctrl+V

        this.text_receiver.add(this.popupmenu);
        toolbar.add(this.popupmenu);


        this.setVisible(true);

        //以下获得自己网名、目标主机的IP地址和接收端口
        this.name = name;
        this.destip=InetAddress.getByName(host); //目标主机名或IP地址
        this.destport=destport;                  //目标主机的接收端口

        //以下接收数据报包，解压缩获得包裹内容，将字节序列转换成字符串显示在文本区中
        byte[] data = new byte[512];             //声明字节数组存储数据报包内容
        DatagramPacket packet=new DatagramPacket(data,data.length);//创建接收数据报包
        DatagramSocket datasocket=new DatagramSocket(receiveport); //创建接收Socket
        while(datasocket!=null)
        {
            datasocket.receive(packet);          //接收数据报包到packet
            //下句由packet包中字节数组构造字符串，使用默认字符集GBK
            this.text_receiver.append(new String(packet.getData(),0,packet.getLength())+"\r\n");
        }

    }

    public void actionPerformed(ActionEvent event)  //单击"发送"按钮
    {
        if(event.getActionCommand().equals("发送"))
        {
            //以下将字符串转换成字节数组，使用默认字符集GBK；再发送
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            byte[] data=(name+" 说："+this.text_sender.getText()+" "+dateFormat.format(date)).getBytes();
            try
            {
                DatagramPacket packet=new DatagramPacket(data, data.length, destip, destport);
                DatagramSocket datasocket = new DatagramSocket();    //绑定一个可用端口用于发送
                datasocket.send(packet);                             //发送数据报包

                this.text_port.setText(datasocket.getLocalPort()+"");//显示本机发送端口
                this.text_receiver.append("我说："+this.text_sender.getText()+" "+dateFormat.format(date)+"\n");
                this.text_sender.setText("");
            }
            catch(SocketException ex)            //Socket异常
            {
                JOptionPane.showMessageDialog(this, "IP地址或端口错误。");
                System.out.println(ex.getClass().getName());
            }
            catch(IOException ex)                //包含Socket异常
            {
                JOptionPane.showMessageDialog(this, "IP地址或端口错误，发送错误。");
                System.out.println(ex.getClass().getName());
//                ex.printStackTrace();
            }
        }
        if (event.getSource() instanceof JMenuItem)
        {
            switch(event.getActionCommand())
            {
                //case "剪切": this.text_receiver.cut();   break;     //将选中文本剪切送系统剪贴板
                case "复制": this.text_receiver.copy();  break;     //将选中文本复制送系统剪贴板
                case "粘贴": this.text_receiver.paste(); break;     //将剪贴板的文本粘贴在光标当前位置
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton()==3)
        {
            this.popupmenu.show(this.text_receiver,e.getX(),e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public static void main(String[] args) throws Exception
    {   //指定网名、本机接收端口、目标主机IP地址和接收端口
        new Toicq("玉公主", 20001, "127.0.0.1", 20002);
//        Date da=new Date();
//        System.out.println(da);
    }
}
