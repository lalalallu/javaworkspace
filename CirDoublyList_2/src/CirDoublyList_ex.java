public class CirDoublyList_ex
{
    public static void main(String args[])
    {
        Integer values[] = {70, 20, 80, 70, 20, 60};

        CirDoublyList<Integer> list1 = new CirDoublyList<Integer>(values);
        System.out.println("list1:" + list1.toString());
        Integer v2[] = { 20,80};
        CirDoublyList<Integer> pattern=new CirDoublyList<>(v2);
        System.out.println("pattern:"+pattern);
        System.out.println("first:"+list1.search(pattern));
    }
}
