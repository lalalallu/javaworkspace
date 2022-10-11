package judment_2;

import java.io.IOException;

public class Re2 extends Referee
{

    Re2(String string, int port, String host) throws IOException
    {
        super(string, port, host);
    }

    public static void main(String[] args) throws IOException {
        new Re("朱元璋",1004,"127.0.0.1");
    }
}