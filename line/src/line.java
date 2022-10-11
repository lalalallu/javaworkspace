import point.point;
public class line
{
    public point p1,p2;
    public line(point p1,point p2)
    {
        this.p1=p1;
        this.p2=p2;
    }
    public double length()
    {
        int a=p1.x+ p2.x;
        int b=p1.y+p2.y;
        return Math.sqrt(a*a+b*b);
    }
    public String toString()
    {
        return "起点"+p1.toString()+"终点"+p2.toString()+"，长度"+String.format("%1.2f",length());
    }
}
class line_ex
{
    public static void main(String[] args)
    {
        point p1=new point(),p2=new point(40,30);
        System.out.println(new line(p1,p2));
    }
}