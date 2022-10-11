public class RMB
{
    double x;
    public RMB(double x)
    {
        this.x=x;
    }
    //public final static String big_num ="零壹贰叁肆伍陆柒捌玖";
    //public final static String unit ="分角元拾佰仟万拾佰仟亿拾佰仟亿";
    public static String toString(double x)
    {
        String big_num ="零壹贰叁肆伍陆柒捌玖";
        String unit ="分角元拾佰仟万拾佰仟亿拾佰仟亿";
        if (x==0)
        {
            return "零元整";
        }
        String tem=String.valueOf((long) (x*100));//最后两位是分角
        String bigNum="";
        for (int i=0;i<tem.length();i++)
        {
            int a=tem.charAt(i)-'0';//减去0
            bigNum=bigNum+big_num.charAt(a);
        }
        int length=bigNum.length();//insert（）方法，该方法是在索引的前面添加字符串
        StringBuffer new_bigNum = new StringBuffer(bigNum);
        new_bigNum.append(unit.charAt(0));//尾插入分
        for(int i=length-1;i>0;i--)
        {
            new_bigNum.insert(i,unit.charAt(length-i));
        }
        bigNum=new_bigNum.toString();
        for(int i=0;i<bigNum.length();i++)
        {
            bigNum = bigNum.replaceAll("零零角零分|零角零分|零整", "整");
            bigNum = bigNum.replaceAll("零仟|零佰|零拾|零角|零零", "零");
            bigNum = bigNum.replaceAll("零亿|亿万", "亿零");
            bigNum = bigNum.replaceAll("零万", "万零");
            bigNum = bigNum.replaceAll("零元", "元零");
            bigNum = bigNum.replaceAll("零分", "");
        }
        System.out.println(new_bigNum);
        return bigNum;
    }
}
class RMB_ex
{
    public static void main(String[] args)
    {
        double x=101000.03;
        RMB r1=new RMB(x);
        System.out.println(r1.toString(r1.x));
    }
}
