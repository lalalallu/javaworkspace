package judment_2;

import java.io.IOException;

public class Re3 extends Referee
{

    Re3(String string, int port, String host) throws IOException
    {
        super(string, port, host);
    }

    public static void main(String[] args) throws IOException {
        new Re("包青天",1005,"127.0.0.1");
    }
}
