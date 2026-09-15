import java.util.ArrayList;
import java.util.List;


/// Connect Four is a popular game played on a 7x6 grid.
/// Two players take turns dropping colored discs into the grid.
/// The first player to get four discs in a row (vertically, horizontally or diagonally) wins.

public class DesignConnectFour {

      Grid[][] grids;
     List<Grid> playerOne;
     List<Grid> playerTwo;

     DesignConnectFour(int n,int m,int target){
         this.grids=new Grid[n][m];
         this.playerOne=new ArrayList<>();
         this.playerTwo=new ArrayList<>();
     }

    enum GridColor{
        RED,
        YELLOW,
        GREY,
    }

    static class Grid {
        GridColor gridColor;

        Grid(GridColor gridColor){
            this.gridColor=gridColor;
        }
    }

    public boolean checkPlayerOneGridValidation(){
         return false;
    }
    public boolean checkPlayerTwoGridValidation(){
         return false;
    }

    public void setPlayerOne(int[][] index){

    }

    public void setPlayerTwp(int[][] index){

    }

}
