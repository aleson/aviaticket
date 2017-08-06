package calcmod;

import java.util.*;


public class DijkstrasAlg {

    private int numStartVer;//start versh number
    private int numEndVer;//end versh number
    private static final long INF = Long.MAX_VALUE / 10; //infinity
    private int arr[][]=new int[100][100];

    public void dijkstra(Graph g, int numStartVer, long[] prio, int[] pred) {
        numStartVer = 0;
        numEndVer=5;
        Arrays.fill(pred, -1);
        Arrays.fill(prio, INF);
        prio[numStartVer] = 0;
        Queue<QItem> q = new PriorityQueue<QItem>();
        q.add(new QItem(0, numStartVer));
        while (!q.isEmpty()) {
            QItem cur = q.poll();
            if (cur.prio != prio[cur.v]) {
                continue;
            }
            for (Edge e : g.nodeEdges[cur.v]) {
                long nprio = prio[cur.v] + e.cost;
                if (prio[e.t] > nprio) {
                    prio[e.t] = nprio;
                    pred[e.t] = cur.v;
                    q.add(new QItem(nprio, e.t));
                }
            }
        }
    }

    public class QItem implements Comparable<QItem> {
        long prio;
        int v;

        public QItem(long prio, int v) {
            this.prio = prio;
            this.v = v;
        }
        public int compareTo(QItem q) {
            return prio < q.prio ? -1 : prio > q.prio ? 1 : 0;
        }
    }

    public class Edge {
        public int s, t, cost;

        public Edge(int s, int t, int cost) {
            this.s = s;
            this.t = t;
            this.cost = cost;
        }
    }

    public class Graph {
        public int n;
        public List<Edge>[] nodeEdges;

        public Graph(int n) {
            this.n = n; //конструктор
            nodeEdges = new List[n];
            for (int i = 0; i < n; i++) {
                nodeEdges[i] = new ArrayList<Edge>();
            }
        }
        void addEdge(int s, int t, int cost) {
            nodeEdges[s].add(new Edge(s, t, cost));
        }
    }

    //this method is start algorithm(get matrix) and return Versh's list (path)
    public List<Integer> run(int[][] arr,int numStartVer,int numEndVer) {
        if (numEndVer==numStartVer) throw new IllegalArgumentException("Beginning city=ending city!");
        if(numStartVer>numEndVer){
            int tmp=numStartVer;
            numStartVer=numEndVer;
            numEndVer=tmp;
        }
        this.numStartVer=numStartVer;

        arr=balanceMatrix(arr,0,numStartVer);//start versh go to beginning at the matrix
        arr=balanceMatrix(arr,numEndVer,arr.length-1);//end versh go to ending at the matrix
        this.numEndVer=arr.length-1;//max counter strings and columns at matrix
        int n = arr.length;
        this.arr=arr;
        Graph g = new Graph(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != 0) {
                    g.addEdge(i, j, arr[i][j]);
                }
            }
        }
        long[] path = new long[g.n];
        int[] pred = new int[g.n];
        dijkstra(g, numStartVer, path, pred);
        return printPath(pred, numEndVer);
    }

    //method is printing path
    public List<Integer> printPath(int[] map, int point) {
        List<Integer> list=new ArrayList<Integer>();
        StringBuilder result = new StringBuilder();
        while (true) {
            result.insert(0,point+1);
            point = map[point];
            if (point < 0) {
                break;
            }
        }
        String str=String.valueOf(result);//StringBuilder copy to String
        char [] charstr=str.toCharArray();
        for(int i=0;i<charstr.length;i++)
        {
            if(i==0) list.add(numStartVer+1);
            else list.add(Integer.parseInt(String.valueOf(charstr[i])));
        }
        return list;
    }

    //Balance-matrix mechanism for simplification algorithm
    public int[][] balanceMatrix(int[][] matrix,int beginVer, int changedVer){
        if(beginVer==changedVer) return matrix;//for time economy
        int[][] arr=matrix;//copy of matrix
        int tmp;//temporary variable
        for(int i=0;i<arr.length  ;i++){
            if(changedVer!=0 && beginVer!=0) {
                tmp = arr[i][beginVer - 1];
                arr[i][beginVer - 1] = arr[i][changedVer - 1];
                arr[i][changedVer - 1] = tmp;
            }
        }
        for(int j=0;j<arr.length;j++){
            if(changedVer!=0 && beginVer!=0) {
                tmp = arr[beginVer - 1][j];
                arr[beginVer - 1][j] = arr[changedVer - 1][j];
                arr[changedVer - 1][j] = tmp;
            }
        }
        return arr;
    }
}
