package Master;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class master extends JFrame implements ActionListener {
    private TextField row,col;
    private JButton jButton;
    private Socket socket;
    private DataOutputStream dataOutputStream;
    public master() {
        super("客户端");

        this.setSize(700,200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel jPanel=new JPanel();
        this.getContentPane().add(jPanel,"North");
        jPanel.add(this.col=new TextField(5));
        jPanel.add(this.row=new TextField(5));
        jPanel.add(this.jButton=new JButton("发送行列数"));
        this.jButton.addActionListener(this);
        this.setVisible(true);

        try {
            this.socket=new ServerSocket(3000).accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand()=="发送行列数")
            try {

                this.dataOutputStream=new DataOutputStream(this.socket.getOutputStream());
                JOptionPane.showMessageDialog(this,"daozhe");
                int rowCount=Integer.parseInt(row.getText());
                int colCount=Integer.parseInt(col.getText());

                this.dataOutputStream.writeInt(rowCount);
                this.dataOutputStream.writeInt(colCount);

                //            dataOutputStream.close();
                //            socket.close();
                JOptionPane.showMessageDialog(this,"发出去了");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,"流错误");
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(this,"不能生成这样的矩阵哦");
            }
    }

    public static void main(String[] args) {
        new master();
    }
}
