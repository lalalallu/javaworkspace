package TableModelObjectFile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class TableModelObjectFile
{
    public static int writeTo(String filename, DefaultTableModel tablemodel)
    {
        int rows=tablemodel.getRowCount();
        int columns=tablemodel.getColumnCount();
        int n=0;
        try
        {
            OutputStream o = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(o); //数据字节输出流，以文件字节流作为数据源
            out.writeInt(rows);                         //写入表格模型行数，对象流也可写入int整数
            out.writeInt(columns);                      //写入表格模型列数
            for(int i=0; i<rows; i++)                      //循环，写入表格模型每行、列单元格对象
            {
                for(int j=0; j<columns; j++)
                {
                    Object obj=tablemodel.getValueAt(i,j); //获得表格指定单元格的对象，父类对象obj引用子类实例
                    out.writeObject(obj);               //写入一个对象，若obj==null，也写入
                    n++;
                }
            }
            out.close();                                 //关闭对象流
            o.close();                                    //关闭文件流
            JOptionPane.showMessageDialog(null, "写入\""+filename+"\"文件，"+rows+"行，"+columns+"列，"+n+"个对象。");
        }
        catch(FileNotFoundException ex)          //文件不存在异常，如文件路径错误、文件名是null或""
        {
            JOptionPane.showMessageDialog(null, "\""+filename+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "写入文件时数据错误");
        }
        return n;
    }
}
