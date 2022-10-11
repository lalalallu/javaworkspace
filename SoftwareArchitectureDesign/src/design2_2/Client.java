package design2_2;

/**
 * @author lalalallu
 */
public class Client
{
    public static void main(String[] args) {
        Controler controler=new Controler();
        MyFrameBuilder bf;
        bf=new ChBuild();
        MyFrame myFrame= controler.build(bf);
        bf=new EhBuild();
        MyFrame myFrame2= controler.build(bf);
    }
}
