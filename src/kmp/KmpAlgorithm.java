package kmp;

public class KmpAlgorithm {
    public static void algorithm(String text, String[] patterns) {
        for (String pat : patterns) {
            Integer[] lps = preprocessPattern(pat);
            String[] p = pat.split("");
            String[] s = text.split("");
            int pointer = 0;
            int index = 0;
            while (index < s.length) {
                if (s[index].equalsIgnoreCase(p[pointer])) {
                    pointer++;
                    index++;
                }
                if (pointer == p.length) {
                    System.out.println(pat + " found at: " + (index - pointer));
                    pointer = lps[pointer - 1];
                } else if (index < s.length && !p[pointer].equalsIgnoreCase(s[index])) {
                    if (pointer != 0) {
                        pointer = lps[pointer - 1];
                    } else {
                        index++;
                    }
                }
            }
        }
    }

    public static Integer[] preprocessPattern(String pat) {
        String[] s = pat.split("");
        Integer[] res = new Integer[s.length];
        res[0] = 0;
        int i = 0, j = 1;
        while (j < s.length) {
            if (s[i].equals(s[j])) {
                i++;
                res[j++] = i;
            } else {
                if (i != 0) {
                    i = res[i - 1];
                } else {
                    res[j++] = i;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("Testing KMP algorithm");
        String text = "patternalgotipatternalgorithm";
        String[] patterns = { "pattern", "algorithm" };
        algorithm(text, patterns);
    }
}
