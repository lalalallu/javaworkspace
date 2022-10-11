import java.util.HashMap;
import java.util.Map;

/**
 * @author lalalallu
 */
public class Main {
    public static void main(String[] args) {
        String s="pwwkew";
        Solution solution=new Solution();
        int a=solution.lengthOfLongestSubstring(s);
        System.out.println(a);
    }
}

class Solution {
    public int lengthOfLongestSubstring(String s)
    {
        int max=0;
        Map<Character, Integer> map=new HashMap<>();
        int start=0;
        int end;
        for (end=0;end<s.length();end++)
        {
            if (map.containsKey(s.charAt(end)))
            {
                start=Math.max(map.get(s.charAt(end))+1,start);
            }

            map.put(s.charAt(end),end);
            max=Math.max(max, end - start+1);
        }

        return max;
    }
}