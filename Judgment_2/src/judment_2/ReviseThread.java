package judment_2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReviseThread extends Thread
{
    protected ArrayList<Judgment.SSC> list;
    protected Judgment.SSC ssc;
    public JTextField t1;
    protected DefaultTableModel tableModel;
    protected int i;
    ReviseThread(Judgment.SSC ssc , JTextField t1,ArrayList<Judgment.SSC> list,DefaultTableModel tableModel,int i)
    {
        this.t1=t1;
        this.ssc=ssc;
        this.list=list;
        this.tableModel=tableModel;
        this.i=i;
    }
    public void run()
    {
        String numb="";
        try {
            ssc.reader = new InputStreamReader(ssc.socket.getInputStream());
            ssc.bufreader = new BufferedReader(ssc.reader);
            numb=ssc.bufreader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        tableModel.setValueAt(numb,this.tableModel.getRowCount()-3, i+1);
    }
}
