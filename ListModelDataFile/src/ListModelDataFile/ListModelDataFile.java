package ListModelDataFile;

import javax.swing.*;
import java.io.*;

public class ListModelDataFile
{
    public static void readFrom(String filename, JComboBox<Integer> combox)
    {
        try
        {
            InputStream in = new FileInputStream(filename);   //文件字节输入流
            DataInputStream da = new DataInputStream(in); //数据字节输入流
            combox.removeAllItems();
            while (true)
                try
                {
                    combox.addItem(da.readInt());
                }
                catch (EOFException eof)
                {
                    break;
                }
            da.close();
            in.close();
        }
        catch(FileNotFoundException ex)          //若文件不存在，则忽略文件
        {
            if(!filename.equals(""))
                JOptionPane.showMessageDialog(null, "\""+filename+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "读取文件时数据错误");
        }
    }
}
