import java.util.*;
import java.io.*;
import java.lang.*;
public class USACO{

  //BRONZE

  private static String toString(int[][] pasture){
    String output = "";
    for(int i = 0; i < pasture.length; i++){
      for (int j = 0; j < pasture[i].length; j++){
        output += pasture[i][j] + "  ";
      }
      output += "\n";
    }
    return output;
  }


  private static int[][] stomp(int[][] board, int R, int C, int E){
    int row = R - 1;
    int col = C - 1;
    int max = 0;

    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){

        if(board[row + i][col + j] > max) max = board[row + i][col + j];
      }
    }
    //find the max elevation then subtract the depth of digging
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        //if elevation is too lpw doesnt dig, only digs to max - elevation
        if(board[row + i][col + j] > max - E){
          board[row + i][col + j] = max - E;
        }
      }
    }


    return board;

  }

  private static int[][] depths(int[][] board, int E){
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[i].length; j++){
        if(E < board[i][j]){
          board[i][j] = 0;
        } else {
          board[i][j] = E - board[i][j];
        }
      }
    }
    return board;
  }

  private static int volume(int[][] board, int E){
    int counter = 0;
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[i].length; j++){
        counter += board[i][j];
      }
    }
    return counter * 72 * 72;
  }





  public static int bronze(String fileName) throws FileNotFoundException{
    File f = new File(fileName);
    Scanner s = new Scanner(f);
    int rows = 0;
    int cols = 0;
    int moves = 0;
    int elevation = 0;
    //pasture and N just have to be itialize to prevent compiling error, but will be changed
    //on first instance of while loop
    int[][] pasture = {{0,0}};
    int[][] N = {{1,1}};
    int lines = 0;
    while(s.hasNext()){
      if(lines == 0){
        //use the first line of the file to initialize all of teh variables
        rows = s.nextInt();
        cols = s.nextInt();
        elevation = s.nextInt();
        moves = s.nextInt();
        N = new int[moves][3];
        pasture = new int[rows][cols];
      } else if(lines > 0 && lines < rows + 1){
        //then for the next r lines we can go through the pasture
        for(int i = 0; i < cols; i++){
          pasture[lines - 1][i] = s.nextInt();
        }
      }
      //lastly put the instructions into our 2D array
      else if(lines > rows && lines < rows + moves + 1){
        for(int i = 0; i < 3; i++){
          N[lines - (rows + 1)][i] = s.nextInt();

        }
      }

      //add 1 + lines
      lines ++;

    }
    /*System.out.println(N.length);
    for(int i = 0; i < N.length; i++){
    for(int j = 0; j < N[0].length; j++ ){
    System.out.print(N[i][j]);
  }
}
*/

//now go through the list N of commands

for(int i = 0; i < N.length; i++){

  stomp(pasture, N[i][0], N[i][1], N[i][2]);

}
//System.out.println(toString(pasture));
depths(pasture, elevation);
return volume(pasture, elevation);
}



//Silver
public static int silver(String filename) throws FileNotFoundException{
    File f = new File(filename);
    Scanner s = new Scanner(f);
    boolean[][] land;
    int N = Integer.parseInt(s.next());
    int M = Integer.parseInt(s.next());
    int T = Integer.parseInt(s.next());
    land = new boolean[N][M];
    int[][] moves = new int[N][M];
    //copies the grass and trees into the land array
    for(int r = 0; r < N; r++){
      String word = s.next();
      for(int c = 0; c < word.length(); c++){
        System.out.println(word);
        //fill the land array
        land[r][c] =
        (word.charAt(c) != '*');
    }
  }
  int R1 = Integer.parseInt(s.next()) - 1;
  int C1 = Integer.parseInt(s.next()) - 1;
  int R2 = Integer.parseInt(s.next()) - 1;
  int C2 = Integer.parseInt(s.next()) - 1;

  moves[R1][C1] = 1;
  int counter = (R1+C1)%2;
  for (int t = 0; t < T; t++) {
    for (int r = 0; r < N; r++) {
      for (int c = (r + counter)%2; c < M; c += 2) {
        if (land[r][c]) {
          if (r > 0){
             moves[r-1][c] += moves[r][c];
          }
          if (c > 0){
            moves[r][c-1] += moves[r][c];
          }
          if (r < N-1){
            moves[r+1][c] += moves[r][c];
          }
          if (c < M-1){
            moves[r][c+1] += moves[r][c];
          }
        }
        moves[r][c] = 0;
      }
    }
    counter = (counter + 1)%2;
  }
  return moves[R2][C2];
}

}
