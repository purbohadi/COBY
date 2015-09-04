import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LongestPalindrome {

    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new FileInputStream(
		"inputLongestPalindrome.txt"));

	int T = sc.nextInt();
	sc.nextLine();
	for (int test_case = 0; test_case < T; test_case++) {
	    while (sc.hasNextLine()) {
		String line = sc.nextLine();
		if (line.equals("")) {
		    System.out.println("#" + (test_case + 1) + " " + 0);
		} else {
		    System.out.println("#" + (test_case + 1) + " "
			    + countLPS(line));
		}
		break;
	    }
	}

    }

    public static int countLPS(String input) {

	char[] str = input.toCharArray();
	int j;
	int strLength = input.length();
	int[][] L = new int[strLength][strLength];

	for (int i = 0; i < strLength; i++) {// initialize L array
	    L[i][i] = 1;
	}

	for (int cl = 2; cl <= strLength; cl++) {
	    for (int i = 0; i < strLength - cl + 1; i++) {
		j = i + cl - 1;
		if (str[i] == str[j] && cl == 2) {
		    L[i][j] = 2;
		} else if (str[i] == str[j]) {
		    L[i][j] = L[i + 1][j - 1] + 2;
		} else {
		    L[i][j] = maximum(L[i][j - 1], L[i + 1][j]);
		}
	    }
	}

	return L[0][strLength - 1];
    }

    public static int maximum(int x, int y) {
	return x > y ? x : y;
    }

}
