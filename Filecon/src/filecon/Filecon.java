package filecon;

import java.io.*;
import java.util.Scanner;

public class Filecon
{
    // 定义学生模型
    String[] number = new String[5];
    String[] name = new String[5];
    float[] grade = new float[5];
    //float[] sum = new float[5];
    File file;
    public static void main(String[] args) throws Exception {
        Filecon stud = new Filecon();
        stud.file = new File("E://stud.txt");
        if (!stud.file.exists())
        {
            stud.input();
            stud.output();
        }
        Scanner inp = new Scanner(System.in);
        while (true)
        {
            System.out.println("1.插入");
            System.out.println("2.删除");
            System.out.println("3.查找");
            System.out.println("0.退出");
            int str = inp.nextInt();
            switch (str) {
                case 1:
                    stud.insert();break;
                case 2:
                    stud.del();break;
                case 3:
                    stud.search();break;
            }
            if (str==0)
            {
                break;
            }
        }
    }

    int searchNo(String stuNo) throws IOException {
        //获取键盘录入对象,获取查询
        BufferedReader in = new BufferedReader(new FileReader(file));

        //进行文件读取
        String row = null;
        int i = 1;
        while ((row = in.readLine()) != null) {
            String[] rowArray = row.split(" "); //注意中英文的逗号不同，根据实际需要写
            String no = rowArray[0];
            if (stuNo.equals(no)) {
                //记得关流
                in.close();
                return i;
            }
            i++;
        }
        //如果读完了还没找到，就说明不存在
        in.close();
        return -1;
    }

    int  search() throws IOException {
        //获取键盘录入对象,获取查询的球员姓名
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生学号 ：");
        String stuNo = sc.next();

        BufferedReader in = new BufferedReader(new FileReader(file));

        //进行文件读取
        String row = null;
        int i=1;
        while ((row = in.readLine()) != null) {
            String[] rowArray = row.split(" "); //注意中英文的逗号不同，根据实际需要写
            String no = rowArray[0];
            String name = rowArray[1];
            String grade= rowArray[2];

            if (stuNo.equals(no)) {

                System.out.println(no +" "+ name +" "+ "成绩为:" +grade);
                //记得关流
                in.close();
                return i;
            }
            i++;
        }
        //如果读完了还没找到，就说明不存在
        System.out.println("没有该学生~~~");
        in.close();
        return -1;
    }
    void insert() throws IOException {
        int lineno;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入学号：");
        String nano=br.readLine();
        lineno = Integer.parseInt(nano);
        System.out.print("请输入姓名：");
        String nametem;
        nametem = br.readLine();
        System.out.print("请输入操作系统成绩：");
        int gradetem;
        gradetem = Integer.parseInt(br.readLine());
        System.out.println();
        int k=1;
        for(int i=0;i<lineno;i++)
        {
            if (this.searchNo(Integer.toString(lineno-k))!=-1)
            {
                lineno=this.searchNo(Integer.toString(lineno-k));
                break;
            }
            k++;
        }

        // 临时文件
        File outFile = File.createTempFile("name", ".tmp");
        // 输入
        FileInputStream fis = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
        // 输出
        FileOutputStream fos = new FileOutputStream(outFile);
        PrintWriter out = new PrintWriter(fos);
        // 保存一行数据
        String thisLine;
        // 行号从1开始
        int i = 1;
        while ((thisLine = in.readLine()) != null) {
            // 如果行号等于目标行，则输出要插入的数据
            if (i == lineno+1) {
                out.println(nano+" "+nametem+" "+gradetem);
            }
            // 输出读取到的数据
            out.println(thisLine);
            // 行号增加
            i++;
        }
        out.flush();
        out.close();
        in.close();
        // 删除原始文件
        file.delete();
        // 把临时文件改名为原文件名
        outFile.renameTo(file);
    }


    void del() throws IOException {
        int lineno=this.search();
        // 临时文件
        File outFile = File.createTempFile("name", ".tmp");
        // 输入
        FileInputStream fis = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
        // 输出
        FileOutputStream fos = new FileOutputStream(outFile);
        PrintWriter out = new PrintWriter(fos);
        // 保存一行数据
        String thisLine;
        // 行号从1开始
        int i = 1;
        while ((thisLine = in.readLine()) != null) {

            int k=0;
            if (i == lineno)
            {
                k=1;
            }
            if (k!=1)
            {
                // 输出读取到的数据
                out.println(thisLine);
            }
            i++;// 行号增加
        }
        out.flush();
        out.close();
        in.close();
        // 删除原始文件
        file.delete();
        // 把临时文件改名为原文件名
        outFile.renameTo(file);
    }
    // 输入学号、姓名、成绩
    void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 录入状态标识
        boolean isRecord = true;
        while (isRecord) {
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.print("请输入学号：");
                    number[i] = br.readLine();
                    System.out.print("请输入姓名：");
                    name[i] = br.readLine();
                    System.out.print("请输入操作系统成绩：");
                    grade[i] = Integer.parseInt(br.readLine());
                    System.out.println();
                }
                isRecord = false;
            } catch (NumberFormatException e) {
                System.out.println("请输入一个数字！");
            }
        }
    }

    // 输出文件
    void output() throws IOException {
        //File file = new File("E://stud.txt");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("No " + "Name " + "grade");
        bw.newLine();
        for (int i = 0; i < 5; i++) {
            bw.write(number[i]);
            bw.write(" " + name[i]);
            bw.write(" " + grade[i]);
            bw.newLine();
        }
        bw.close();
    }
}
