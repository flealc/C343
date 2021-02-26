import java.util.Arrays;

public class CookieMonster {
    private int[][] cookies, cache;
    private int size;

    CookieMonster(int[][] cookies, int size) {
        this.cookies = cookies;
        this.cache = new int[size][size];
        this.size = size;
    }

    // Given two ints (that represent row and column indices), this method will return
    // the greatest int between the values at (r+1, c), (r+1, c-1), and (r+1, c+1).
    int maxBottom3(int r, int c) {
        this.cache=this.cookies;
        int under = c;
        int right = c + 1;
        int left = c - 1;
        int bigOne = this.cache[r+1][under];

        if (c == 0) {
            if (this.cache[r+1][right] > this.cache[r+1][under]) {
                return this.cache[r+1][right];
            } else return this.cache[r+1][under];
        } else if (c == size - 1) {
            if (this.cache[r+1][left] > this.cache[r+1][under]) {
                return this.cache[r+1][left];
            } else return this.cache[r+1][under];
        } else {

            if (this.cache[r + 1][left] + this.cache[r][c] > bigOne) {
                bigOne = this.cache[r + 1][left];
            }
            if (this.cache[r + 1][right] + this.cache[r][c] > bigOne) {
                bigOne = this.cache[r + 1][right];
            }

            return bigOne;
        }
    }

    // Using a bottom-up (tabular) dynamic programming approach, this method returns the "max amount of
    // cookies".
    // Specifics:
    //      - Your search area will be an NxN matrix. (this.cookies)
    //      - Start from the last row of your matrix and build up.
    //      - Do not change matrix cookies; instead, use this.cache as your tabulation playground.
    //      - Your answer will be in the the first row, middle column (N/2).
    // As always, please read Lab 6's canvas post for more information (in particular, how to build) ^.^

    int findBest() {

        for (int i = size-2; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                this.cache[i][j] = this.cache[i][j] + maxBottom3(i, j);
            }

        }
                // this will help you debug - will print out the tabulation of your algo :)
                System.out.println(Arrays.deepToString(cache).replace("], ", "]\n"));
                return this.cache[0][size /2];

        }
    }
