import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, int target, int start, List<Integer> curr, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (nums[i] > target) break;
            if (i > start && nums[i] == nums[i - 1]) continue;

            curr.add(nums[i]);
            dfs(nums, target - nums[i], i + 1, curr, res);
            curr.remove(curr.size() - 1);
        }
    }
}