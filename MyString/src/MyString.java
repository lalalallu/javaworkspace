public final class MyString implements Comparable<MyString>,java.io.Serializable
{
    private char[] value;
    public MyString()
    {
        this.value=new char[0];
    }
    public MyString(int n)
    {
        this.value=new char[n];
    }
    public MyString(String s)
    {
        this.value=new char[s.length()];
        for (int i=0;i<this.value.length;i++)
        {
            this.value[i]=s.charAt(i);
        }
    }
    public MyString(char[] value,int i,int n)
    {
        if(i>=0 && n>=0 && i+n<=value.length)
        {
            this.value=new char[n];
            for (int j=0;j<n;j++)
            {
                this.value[j]=value[i+1];
            }
        }
    }
    public MyString(char[] value)
    {
        this(value,0,value.length);
    }
    public MyString(MyString s)
    {
        this(s.value);
    }
    public int length()
    {
        return this.value.length;
    }
    public String toString()
    {
        return new String(this.value);
    }
    public char charAt(int i)
    {
        if (i>=0&&i<this.value.length)
        {
            return this.value[i];
        }
        throw new StringIndexOutOfBoundsException(i);
    }
    public int compareTo(MyString s)
    {
        for (int i=0;i<this.value.length&&i<s.value.length;i++)
        {
            if(this.value[i]!=s.value[i])
            {
                return this.value[i]-s.value[i];
            }
        }
        return this.value.length-s.value.length;
    }
    public MyString trim()
    {
        int i=0,j=0;
        String s="";
        MyString tem=new MyString(s.length());
        //char[] c=new char[this.value.length];;
        while (i<this.value.length)
        {
            if (this.charAt(i)!=' ')
            {
                //c[j++]= this.charAt(i);
                //tem.value[j]=s.charAt(j);
                s+= this.charAt(i);
            }
            i++;
        }
        return new MyString(s);
    }
    public int compareToIgnoreCase(MyString s)
    {
        for (int i=0;i<this.value.length&&i<s.value.length;i++)
        {
            char tem1=this.value[i];
            char tem2=s.value[i];
            if(tem1>='A'&&tem1<='Z')
            {
                tem1=(char)(tem1+32);
            }
            if(tem2>='A'&&tem2<='Z')
            {
                tem2=(char)(tem2+32);
            }
            if(tem1!=tem2)
            {
                return tem1-tem2;
            }
        }
        return this.value.length-s.value.length;
    }
}
class MyString_ex
{
    public static void main(String[] args)
    {
        MyString s=new MyString("efdsaf adf awf");
        MyString t1=new MyString("ABcD");
        MyString t2=new MyString("ABBD");
        int i= t1.compareToIgnoreCase(t2);
        System.out.println(s.trim());
        System.out.println(i);
    }
}
