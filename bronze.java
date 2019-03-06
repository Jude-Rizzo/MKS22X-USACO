import java.util.*;
public class bronze{


  public int[][] stomp(int[][] board, int R, int C){
    row = R - 1;
    col = C - 1;
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        
      }
    }
  }

  public static int[][] reader(int rows, int cols, String[] args){
    //find length
    int[][] ans = new int[rows][cols];
    int j = 4;
    for(int n = j; n < rows * cols + 4; n++){
      ans[(n-4)%rows][(n-4)%cols] = Integer.parseInt(args[n]);
    }
    return ans;
  }




  public static void main(String[] args){
    int r = Integer.parseInt(args[0]);
    int c = Integer.parseInt(args[1]);
    int E = Integer.parseInt(args[2]);
    int N = Integer.parseInt(args[3]);
    int[][] startingBoard = reader(r, c, args);
}

}
