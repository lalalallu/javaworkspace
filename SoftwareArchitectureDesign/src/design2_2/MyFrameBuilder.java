package design2_2;

/**
 * @author lalalallu
 */
abstract class MyFrameBuilder
{
    protected MyFrame myFrame =new MyFrame();

    /**
     * build button
     */
    public abstract void buildButton();

    /**
     * build text
     */
    public abstract void buildText();

    /**
     * build label
     */
    public abstract void buildLabel();

    public MyFrame creatMyFrame()
    {
        return myFrame;
    }

    public abstract void show();

}
