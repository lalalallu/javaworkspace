package judment_2;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SendThread extends Thread
{
    protected ArrayList<Judgment.SSC> list;
    protected Judgment.SSC ssc;
    public JTextField t1;
    SendThread(Judgment.SSC ssc , JTextField t1,ArrayList<Judgment.SSC> list)
    {
        this.t1=t1;
        this.ssc=ssc;
        this.list=list;
    }
    public void run()
    {
        try {
            ssc.cout = new PrintWriter(ssc.socket.getOutputStream(), true);
            ssc.cout.println(this.t1.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
