import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public String makeLargestSpecial(String s) {
        if (s.length() <= 2) {
            return s;
        }

        List<String> substrings = new ArrayList<>();
        int count = 0;
        int i = 0;

        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == '1') {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                substrings.add("1" + makeLargestSpecial(s.substring(i + 1, j)) + "0");
                i = j + 1;
            }
        }

        Collections.sort(substrings, Collections.reverseOrder());

        StringBuilder result = new StringBuilder();
        for (String sub : substrings) {
            result.append(sub);
        }

        return result.toString();
    }
}