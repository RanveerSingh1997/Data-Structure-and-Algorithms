package arrays;

import java.util.HashMap;

public class TwoSum {
    public  int[] twoSum(int[] nums, int target){
        HashMap<Integer,Integer> complements = new HashMap<>();
        for(int num: nums){
            int newValue = target - num;
            if(complements.containsKey(newValue)) {
                var complementValue = complements.get(newValue);
                return new int[]{num, complementValue};
            }else {
              complements.put(num,newValue);
            }
        }
        return new int[] {};
    }
}