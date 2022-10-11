

public class BinaryTree_ex
{
    public static void main(String[] args)
    {
//        String [] tem6={"A","B","D","N",null,null,null,"u",null,null,"C","E",null,null,"F",null,null};
//        String [] tem6={"A","B","D","N",null,null,"G",null,null,null,"C",null,null};
//        String [] tem6={"A","B","D",null,"G",null,null,null,"C","E",null,null,"F",null,null};
//        String [] tem6={"A","B","D",null,null,null,null};
        String[] tem={"a","b","c","d","e"};
        String[] tem2={"a","b","c","d","e","f","g","h","i"};
        String[] tem3={"a","b","c","d","e","f","g","h","i","j","k","l"};
        Integer[] tem4={1,2,3,4,5,6,7,8,9,10};
        Integer[] tem5={54,18,81,12,36,76,99,6,13,20,40,57,80,87,100};
//        String [] tem6={"A","B","D","N",null,null,"G",null,null,"u",null,null,"C","E",null,null,"F",null,null};
//        System.out.println(BinaryTree.createBinaryTree(tem6).toGenListString());
//        System.out.println(BinaryTree.createComplete(tem).toGenListString());
//            System.out.println(BinaryTree.createComplete(tem2).toGenListString());
//            System.out.println(BinaryTree.createComplete(tem3).toGenListString());
//        BinaryTree<String> b1=BinaryTree.createComplete(tem);
//        BinaryTree<String> b2=new BinaryTree<>(b1);
//        System.out.println(b2.toGenListString());
//        b1=BinaryTree.createComplete(tem2);
//        System.out.println(b1.toGenListString());
//        System.out.println(b2.toGenListString());
//            BinaryTree<String> b3=new BinaryTree<>();
//            b3=b3.createComplete2(tem);
//            System.out.println(b3.toGenListString());
//            b3=b3.createComplete2(tem2);
//            System.out.println(b3.toGenListString());
//            b3=b3.createComplete2(tem3);
//            System.out.println(b3.toGenListString());
        //b3.isSorted(b3);
        //System.out.println(b3.isSorted(b3));
        //BinaryTree<Integer> b4=BinaryTree.createComplete(tem5);
        //        String [] tem6={"A","B","D","N",null,null,"G",null,null,"u",null,null,"C","E",null,null,"F",null,null};
//          String [] tem6={"A","B","D","N",null,null,null,"u",null,null,"C","E",null,null,"F",null,null};
//        String [] tem6={"A","B","D","N",null,null,"G",null,null,null,"C",null,null};
        String [] tem6={"A","B","D",null,"G",null,null,null,"C","E",null,null,"F",null,null};
//        String [] tem6={"A","B","D",null,null,null,null};
        System.out.println(BinaryTree.createBinaryTree(tem6).toGenListString());
        BinaryTree<String> b5=new BinaryTree<>(tem6);
//        System.out.println(b5.toGenListString());
//        System.out.println(b4.toGenListString());
//        System.out.println(b4.isSorted(b4));
//        System.out.println(BinaryTree.isComplete(b5));

        System.out.println(BinaryTree.isComplete2(b5));
//        System.out.println(BinaryTree.createBinaryTree(tem6).toGenListString());
    }
}
