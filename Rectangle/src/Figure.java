import Point.Point;

public abstract class Figure
{
    public Point point1;
    protected Figure(Point point1)
    {
        this.point1=point1;
    }
    protected Figure(){}
    public String toString()
    {
        return this.point1==null?"":this.point1.toString();
    }

}
