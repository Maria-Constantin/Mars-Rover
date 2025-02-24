/* DESCRIPTION
 *
 * Plateau - grid with x,y coordinates = 2d array
 *  - assume bottom left is 0,0
 * 2 rovers
 *
 * CONTROLS
 * L - spin 90deg left - not moving
 * R - spin 90deg right - not moving
 * M - move forward - point to the same direction
 *
 * INPUTS
 * 1st line = upper right coordinates
 * 2 rovers - each has 2 lines
 *   1st line = the rover's position + initial facing direction
 *   2nd line = series of instructions on how to explore the plateau
 *   - sequentially - finish first rover before second rover
 *
 * OUTPUT
 * final coordinate of the rovers + facing direction
 * */

/* STEPS
 * 1. get grid size + make sure its valid
 * 2. Get no. of rovers - assume 2 only each time
 * 3. Get rover inputs (initial position + direction + instructions) + make sure they're valid
 * 4. Define rover class - constructor, move left, move right, move forward, get position on grid
 *      + make sure moves don't go outside grid bounds
 * 5. Create rover object given the inputs
 * 6. For each instruction, move the rover on the grid
 * 7. Output position of rover
 *
 * */

import java.util.Scanner;

class Rover{
    private int posX;
    private int posY;
    private char direction;

    // constructor
    public Rover(int posX, int posY, char direction) {
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
    }

    // turning directions
    public void turnLeft(){
        switch(direction){
            case 'N': direction = 'W'; break;
            case 'S': direction = 'E'; break;
            case 'W': direction = 'S'; break;
            case 'E': direction = 'N'; break;
        }
    }

    public void turnRight(){
        switch(direction){
            case 'N': direction = 'E'; break;
            case 'S': direction = 'W'; break;
            case 'W': direction = 'N'; break;
            case 'E': direction = 'S'; break;
        }
    }

    // moving forward
    public void moveForward(int plateauX, int plateauY){
        switch(direction){
            case 'N': if(posY + 1 <= plateauY){posY++;} break;
            case 'S': if(posY - 1 >= 0){posY--;} break;
            case 'W': if(posX - 1 >= 0){posX--;} break;
            case 'E': if(posX + 1 <= plateauX){posX++;} break;
        }
    }

    // print rover position on grid
    public String getPosition(){
        return posX + " " + posY + " " + direction;
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // size of plateau
        int x = in.nextInt();
        int y = in.nextInt();

        // check for valid plateau size
        if(x <= 0 || y <= 0){
            System.out.println("Plateau size cannot be zero.");
            return;
        }

        // no. of rovers - assume always 2
        int rovers = 2;

        for (int i = 0; i < rovers; i++){
            // rover position
            int posX = in.nextInt();
            int posY = in.nextInt();

            // check rover is within bounds
            if(posX < 0 || posX > x || posY < 0 || posY > y){
                System.out.println("Rover outside plateau bounds.");
                return;
            }

            // starting facing direction + make it uppercase
            char direction = in.next().toUpperCase().charAt(0);

            // check for valid direction
            if (direction != 'N' && direction != 'S' && direction != 'W' && direction != 'E') {
                System.out.println("Please insert a valid direction.");
                return;
            }

            // string of instructions + make it uppercase
            String instructions = in.next().toUpperCase();

            // separate instructions
            char[] instruction = instructions.toCharArray();

            // check all moves are valid
            for(char c: instruction){
                if(c != 'L' && c != 'R' && c != 'M'){
                    System.out.println("Instructions are invalid, please insert L, R, M instructions only.");
                    break;
                }
            }

            // create rover object
            Rover r = new Rover(posX, posY, direction);

            // for every instruction, move rover accordingly
            for(char move: instruction){
                switch(move){
                    case 'L': r.turnLeft(); break;
                    case 'R': r.turnRight(); break;
                    case 'M': r.moveForward(x, y); break;
                }
            }

            // get the rover position on grid
            System.out.println(r.getPosition());
        }
        in.close();
    }
}
