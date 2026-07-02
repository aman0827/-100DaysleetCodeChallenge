import java.util.*;

class Solution {

    static class State {
        int row, col, health;

        State(int row, int col, int health) {
            this.row = row;
            this.col = col;
            this.health = health;
        }
    }

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {

        int m = grid.size();
        int n = grid.get(0).size();

        int[][] directions = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
        };

        int startHealth = health - grid.get(0).get(0);

        if (startHealth <= 0) {
            return false;
        }

        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(0, 0, startHealth));

        boolean[][][] visited = new boolean[m][n][health + 1];
        visited[0][0][startHealth] = true;

        while (!queue.isEmpty()) {

            State current = queue.poll();

            int row = current.row;
            int col = current.col;
            int currHealth = current.health;

            if (row == m - 1 && col == n - 1) {
                return true;
            }

            for (int[] dir : directions) {

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n) {
                    continue;
                }

                int nextHealth = currHealth - grid.get(newRow).get(newCol);

                if (nextHealth <= 0) {
                    continue;
                }

                if (visited[newRow][newCol][nextHealth]) {
                    continue;
                }

                visited[newRow][newCol][nextHealth] = true;
                queue.offer(new State(newRow, newCol, nextHealth));
            }
        }

        return false;
    }
}