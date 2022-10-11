import java.util.Scanner;

public class text
{
    public static void main(String[] args)
    {
        int n,m;
        Scanner x=new Scanner(System.in);
        n=x.nextInt();
        Scanner y=new Scanner(System.in);
        m=y.nextInt();
//        n=5;
//        m=5;
        int [][] mat=new int[n][m];
        int left=0,top=0;
        int bottom= mat.length-1,right=mat[0].length-1;
        int tem=1,sum=n*m;
        while (sum<=n*m&&sum>0)
        {
            for(int i=left;i<=right;i++)//左到右
            {
                mat[top][i]=tem;
                tem++;
                sum--;
            }
            top++;
            if (sum==0)
            {
                break;
            }
            for(int i=top;i<=bottom;i++)//上到下
            {
                mat[i][right]=tem;
                tem++;
                sum--;
            }
            right--;
            if (sum==0)
            {
                break;
            }
            for (int i=right;i>=left;i--)//右到左
            {
                mat[bottom][i]=tem;
                tem++;
                sum--;
            }
            bottom--;
            if (sum==0)
            {
                break;
            }
            for (int i=bottom;i>=top;i--)//下到上
            {
                mat[i][left]=tem;
                tem++;
                sum--;
            }
            left++;
            if (sum==0)
            {
                break;
            }
        }
        for(int i=0;i<n;i++)
        {
            for (int j=0;j<m;j++)
            {
                System.out.print(String.format("%4d",mat[i][j]));
                if (j==m-1)
                {
                    System.out.println("\n");
                }
            }
        }

    }
}
