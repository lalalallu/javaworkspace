package design2_2;

public class Controler
{
    public MyFrame build(MyFrameBuilder fb)
    {
        MyFrame myFrame;
        fb.buildButton();
        fb.buildText();
        fb.buildLabel();
        myFrame=fb.creatMyFrame();
        fb.show();
        return myFrame;
    }
}
