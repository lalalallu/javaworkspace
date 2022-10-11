package judment_2;

import java.io.IOException;

public class Re extends Referee
{

    Re(String string, int port, String host) throws IOException
    {
        super(string, port, host);
    }

    public static void main(String[] args) throws IOException {
        new Re("杜甫",1003,"127.0.0.1");
    }
}
