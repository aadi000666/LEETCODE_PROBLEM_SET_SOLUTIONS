
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int a = n - 2;
        while (a >= 0 && nums[a] >= nums[a + 1]){
            a--;
        }
        if (a >= 0) {
            int j = n - 1;
            while (nums[j] <= nums[a]) {
                j--;
            }
            int temp = nums[a];
            nums[a] = nums[j];
            nums[j] = temp;
        }
        int left = a + 1;
        int right = n - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
