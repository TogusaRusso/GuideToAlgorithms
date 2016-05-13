import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.Bag;
import java.util.Arrays;

public class BaseballElimination {
    private int N;
    private int[] w, l, r;
    private int[][] g;
    private String[] teams;
    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        In in = new In(filename);
        N = in.readInt();
        w = new int[N];
        l = new int[N];
        r = new int[N];
        g = new int[N][N];
        teams = new String[N];
        for (int i = 0; i < N; i++) {
            teams[i] = in.readString();
            w[i] = in.readInt();
            l[i] = in.readInt();
            r[i] = in.readInt();
            for (int j = 0; j < N; j++)
                g[i][j] = in.readInt();
        }
    }
    private int teamByName(String team) {
        for (int i = 0; i < N; i++) {
            if (teams[i].equals(team))
                return i;
        }
        throw new IllegalArgumentException(team + " not in teams");
    }
    private String teamByNumber(int i) {
        return teams[i];
    }    
    // number of teams
    public int numberOfTeams() {
        return N;
    }
    // all teams
    public Iterable<String> teams() {
        return Arrays.asList(teams);
    }
    // number of wins for given team
    public int wins(String team) {
        return w[teamByName(team)];
    }
    // number of losses for given team
    public int losses(String team) {
        return l[teamByName(team)];
    }
    // number of remaining games for given team
    public int remaining(String team) {
        return r[teamByName(team)];
    }
    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        return g[teamByName(team1)][teamByName(team2)];
    }
    // is given team eliminated?
    public boolean isEliminated(String team) {
        return certificateOfElimination(team) != null;
    }
    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        Bag<String> R = new Bag<String>();
        int x = teamByName(team);
        for (int i = 0; i < N; i++) {
            if (w[x] + r[x] < w[i])
                R.add(teamByNumber(i));
        }
        if (!R.isEmpty())
            return R;
        FlowNetwork G = new FlowNetwork(N * N + N + 1);
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (i != x && j != x) {
                    //Edge from S to i-j = g[i][j]
                    G.addEdge(new FlowEdge(0, N * i + j, g[i][j]));
                    //Edge from i-j to i = INFINITY
                    G.addEdge(new FlowEdge(N * i + j, N * N + i, Double.POSITIVE_INFINITY));
                    //Edge from i-j to j = INFINITY
                    G.addEdge(new FlowEdge(N * i + j, N * N + j, Double.POSITIVE_INFINITY));
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (i != x) {
                int maxWins = w[x] + r[x] - w[i];
                //Edge from i to T = w[x] + r[x] - w[i]
                G.addEdge(new FlowEdge(N * N + i, N * N + N, maxWins));
            }
        }
        //StdOut.println(G);
        FordFulkerson fF = new FordFulkerson(G, 0, N * N + N);
        for (int i = 0; i < N; i++) {
            if (fF.inCut(N * N + i))
                R.add(teamByNumber(i));
        }
        if (!R.isEmpty())
            return R;
        return null;
    }
    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}