import java.util.HashMap;
import java.util.Map;

/**
 * @author lalalallu
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(19/10);
        System.out.println(9%10);
    }
}
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map=new HashMap<>();
        for (int i=0;i< nums.length;i++)
        {
            if (map.containsKey(target-nums[i]))
            {
                return new int[] {map.get(target-nums[i]),i};
            }
            else
            {
                map.put(nums[i],i);
            }
        }

        return null;
    }
}
