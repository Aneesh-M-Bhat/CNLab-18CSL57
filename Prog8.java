import java.util.Scanner;
class Prog8 {
    public static void bellmanFord(int v,int source,int e, int edgeMat[][]){
        int distanceFromSource[]= new int [v+1];
        for(int i=1;i<=v;i++)distanceFromSource[i] = 999;
        distanceFromSource[source]=0;
        for(int i=1;i<=v;i++){
            for(int j=1;j<=e;j++){
                int from = edgeMat[j][0];
                int to = edgeMat[j][1];
                int dist = edgeMat[j][2];
                if(distanceFromSource[to]>distanceFromSource[from]+dist){
                    distanceFromSource[to] = distanceFromSource[from] + dist;
                } 
            }
        }
        for(int i=1;i<=v;i++){
            System.out.println("Distance from node "+source+" to node "+i+" is "+distanceFromSource[i]);
        }
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the no. of vertices and edges");
        int v = s.nextInt();
        int e = s.nextInt();
        int edgeMat[][] = new int [e+1][3];
        System.out.println("Enter various edges as [from,to,distance]");
        for(int i=1;i<=e;i++){
            edgeMat[i][0] = s.nextInt();
            edgeMat[i][1] = s.nextInt();
            edgeMat[i][2] = s.nextInt();
        }
        System.out.println("Enter source vertex");
        int source = s.nextInt();
        bellmanFord(v,source,e,edgeMat);
        s.close();
    }
}
