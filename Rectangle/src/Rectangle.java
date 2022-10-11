import Point.Point;

public class Rectangle extends ClosedFigure
{
    protected int length,width;
    public Rectangle(Point point1,int length,int width)//左上角点，长，宽
    {
        super("矩形",point1);
        this.length=length;
        this.width=width;
    }
    public Rectangle(Point point1,Point point2)//左上角点，右下角点
    {
//        super("矩形",point1);
//        if((point2.x- point1.x)>=(point1.y- point2.y))
//        {
//            this.length=point2.x- point1.x;
//            this.width=point1.y- point2.y;
//        }
//        else
//        {
//            this.length=point1.y- point2.y;
//            this.width=point2.x- point1.x;
//        }
        this(point1,(point2.x- point1.x),(point1.y- point2.y));
    }
    public Rectangle(int x1,int y1,int x2,int y2)//左上 、右下 点坐标
    {
//        super("矩形",new Point(x1,y1));
//        if((x2-x1)>=(y1-y2))
//        {
//            this.length=x2-x1;
//            this.width=y1-y2;
//        }
//        else
//        {
//            this.length=y1-y2;
//            this.width=x2-x1;
//        }
        this(new Point(x1,y1),x2-x1,y1-y2);
    }
    public Rectangle()
    {
//
        this(new Point(0,0),0,0);
    }
    public Rectangle(Rectangle rec)//拷贝构造
    {
//        this.shape=new String(rec.shape);
//        this.point1=rec.point1;
//        //this.point1.y=rec.point1.y;
//        this.length= rec.length;
//        this.width=rec.width;
        this(new Point(rec.point1),rec.length,rec.width);
    }
    public String toString()
    {
        return "此图形为："+this.shape+"  "+"左上角点为："+this.point1.toString()+"  "+"长度为："+
                this.length+"  "+"宽度为："+this.width;
    }
    public double perimeter()
    {
        return (this.width+this.length)*2;
    }//周长
    public double area()
    {
        return this.length*width;
    }//面积

    @Override
    public int compareTo(ClosedFigure o)
    {
        return (this.area()-o.area())>=0?1:0;
    }
}
class Rec_out
{
    public static void main(String[] args)
    {
        Rectangle r1=new Rectangle();
        System.out.println(r1);
        Point p1=new Point(3,7);
        Point p2=new Point(5,6);
        int len=10;
        int wid=9;
        Rectangle r2=new Rectangle(p2,len,wid);
        System.out.println(r2);
        Rectangle r3=new Rectangle(p1,p2);
        System.out.println(r3);
        Rectangle r4=new Rectangle(r1);
        System.out.println(r4);
        int x1=4,y1=5,x2=7,y2=1;
        Rectangle r5=new Rectangle(x1,y1,x2,y2);
        System.out.println(r5);
        System.out.println(r1.compareTo(r4));
        Rectangle[] cfigs=new Rectangle[3];
        cfigs[0]=r2;
        cfigs[1]=r3;
        cfigs[2]=r5;
        System.out.println(Rectangle.min(cfigs));
    }
}
