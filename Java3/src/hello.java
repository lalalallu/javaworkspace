public class hello {
    public static void main(String[] args)
    {
        //System.out.println("hello world!");\
        if(args.length==0)
        {
            System.out.println("Hello!");
        }
        else
        {
            for (int i=0;i< args.length;i++)
            {
                System.out.println(args[i]);
            }
        }
    }
}