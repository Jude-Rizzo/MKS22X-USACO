import java.util.*;
import java.io.*;
public class bronze{


/*  public int[][] stomp(int[][] board, int R, int C){
    int row = R - 1;
    int col = C - 1;
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){

      }
    }
  }
  */




  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
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
}

}
