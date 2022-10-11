import Point.Point;

public abstract class ClosedFigure extends Figure implements Comparable<ClosedFigure>
{
    protected String shape;
    protected ClosedFigure(String shape,Point point1)
    {
        super(point1);
        this.shape=shape;
    }
    protected ClosedFigure()
    {
        this("",new Point());
    }
//    public abstract double perimeter();//周长
   public abstract double area();//面积

    @Override
    public int compareTo(ClosedFigure o)
    {
        return 0;
    }
    public static ClosedFigure min (ClosedFigure[] cfigs)
    {
        ClosedFigure min=cfigs[0];
        for (int i=0;i<cfigs.length;i++)
        {
//            if (i==cfigs.length-1)
//            {
//                break;
//            }
            if(min.compareTo(cfigs[i])==1)
            {
                min=cfigs[i];
            }
        }
        return min;
    }
}
