package judment_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Referee extends JFrame implements ActionListener
{
    private Socket socket;
    private JTextField text,text2;
    private JButton button;
    public PrintWriter cout;
    Referee(String string,int port,String host)throws IOException
    {
        super("裁判员"+"-"+string);
        this.setSize(500,80);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel=new JPanel();
        panel.setLayout(new FlowLayout());
        this.add(panel);
        JLabel label=new JLabel("选手名");
        panel.add(label);
        text=new JTextField(10);
        text.setEditable(false);
        panel.add(text);
        JLabel label2=new JLabel("得分");
        panel.add(label2);
        text2=new JTextField(10);
        panel.add(text2);
        button=new JButton("发送");
        button.addActionListener(this);
        panel.add(button);
        this.setVisible(true);
        this.socket=new Socket(host,port);
        //this.socket=socket;
        this.cout=new PrintWriter(this.socket.getOutputStream(), true);
        this.cout.println(string);

        Reader reader=new InputStreamReader(this.socket.getInputStream());
        BufferedReader bufreader=new BufferedReader(reader);
        while (true)
        {
            try
            {
                String name=bufreader.readLine();
                this.text.setText(name);
            }
            catch (EOFException e)
            {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        new Referee("李白",1002,"127.0.0.1");
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == this.button)
        {
            try
            {
                this.cout = new PrintWriter(this.socket.getOutputStream(), true);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            this.cout.println(this.text2.getText());
        }
    }
}
