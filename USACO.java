import java.util.*;
import java.io.*;
import java.lang.*;
public class USACO{

  //BRONZE
  private static int[][] stomp(int[][] board, int R, int C, int E){
    int row = R - 1;
    int col = C - 1;
    int max = 0;

    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        if(board[R + i][C + j] > max) max = board[R + i][C + j];
      }
    }
    //find the max elevation then subtract the depth of digging
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        //if elevation is too lpw doesnt dig, only digs to max - elevation
        if(board[R + i][C + j] > max - E){
          board[R + i][C + j] = max - E;
        }
      }
    } return board;
  }

  private static int[][] depths(int[][] board, int E){
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[i].length; j++){
        if(E > board[i][j]){
           board[i][j] = 0;
         } else {
        board[i][j]-=E;
        }
      }
    } return board;
  }

  private static int volume(int[][] board){
    int counter = 0;
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[i].length; j++){
        counter += board[i][j];
      }
    }
    return counter * 72 * 72;
  }





  public static void bronze(String fileName) throws FileNotFoundException{
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
    while(s.hasNextLine()){
      if(lines == 0){
        //use the first line of the file to initialize all of teh variables
        rows = s.nextInt();
        cols = s.nextInt();
        moves = s.nextInt();
        elevation = s.nextInt();
        N = new int[moves][3];
        pasture = new int[rows][cols];
      } else if(lines > 0 && lines < rows + 1){
        //then for the next r lines we can go through the pasture
        for(int i = 0; i < cols; i++){
          pasture[lines - 1][i] = s.nextInt();
        }
      }
      //lastly put the instructions into our 2D array
      else if(lines > rows){
        for(int i = 0; i < 3; i++){
          N[lines - (rows + 2)][i] = s.nextInt();
        }
      }

      //add 1 + lines
      lines ++;

    }

    //now go through the list N of commands
    for(int i = 0; i < N.length; i++){
      stomp(pasture, N[i][0], N[i][1], N[i][2]);
    }
}

}
