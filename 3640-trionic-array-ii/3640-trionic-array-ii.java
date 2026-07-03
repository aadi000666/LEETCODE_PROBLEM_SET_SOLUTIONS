class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        
        // Use Long.MIN_VALUE to represent invalid/unreachable states
        long[] dp0 = new long[n]; // Max sum ending at i for Phase 1 (strictly increasing)
        long[] dp1 = new long[n]; // Max sum ending at i for Phase 2 (strictly decreasing)
        long[] dp2 = new long[n]; // Max sum ending at i for Phase 3 (strictly increasing)
        
        java.util.Arrays.fill(dp0, Long.MIN_VALUE);
        java.util.Arrays.fill(dp1, Long.MIN_VALUE);
        java.util.Arrays.fill(dp2, Long.MIN_VALUE);
        
        long maxSum = Long.MIN_VALUE;
        
        for (int i = 1; i < n; i++) {
            // Case 1: Elements are strictly increasing
            if (nums[i] > nums[i - 1]) {
                // Phase 1: Start a new increasing segment of length 2, or extend an existing one
                dp0[i] = (long) nums[i] + nums[i - 1];
                if (dp0[i - 1] != Long.MIN_VALUE) {
                    dp0[i] = Math.max(dp0[i], dp0[i - 1] + nums[i]);
                }
                
                // Phase 3: Transition from a completed Phase 2, or extend an existing Phase 3
                if (dp1[i - 1] != Long.MIN_VALUE) {
                    dp2[i] = Math.max(dp2[i], dp1[i - 1] + nums[i]);
                }
                if (dp2[i - 1] != Long.MIN_VALUE) {
                    dp2[i] = Math.max(dp2[i], dp2[i - 1] + nums[i]);
                }
            }
            
            // Case 2: Elements are strictly decreasing
            if (nums[i] < nums[i - 1]) {
                // Phase 2: Transition from a completed Phase 1, or extend an existing Phase 2
                if (dp0[i - 1] != Long.MIN_VALUE) {
                    dp1[i] = Math.max(dp1[i], dp0[i - 1] + nums[i]);
                }
                if (dp1[i - 1] != Long.MIN_VALUE) {
                    dp1[i] = Math.max(dp1[i], dp1[i - 1] + nums[i]);
                }
            }
            
            // Track the maximum completed trionic subarray sum found so far
            if (dp2[i] != Long.MIN_VALUE) {
                maxSum = Math.max(maxSum, dp2[i]);
            }
        }
        
        return maxSum;
    }
}