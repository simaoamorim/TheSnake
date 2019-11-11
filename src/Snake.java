import java.awt.*;
import java.util.ArrayList;

public class Snake {
    private static final int INIT_CAPACITY = 3;
    private ArrayList<Point> body = new ArrayList<>(INIT_CAPACITY);
    public enum Direction{NORTH, SOUTH, EAST, WEST}

    Snake(Grid grid) {
        for (int i = 0; i < INIT_CAPACITY; i++) {
            body.add(new Point());
            body.get(i).setLocation(grid.getXCount()/2,grid.getYCount()/2);
        }
    }

    private void move(Direction direction) {
        for (int i = body.size()-1; i > 0; i--) {
            body.get(i).setLocation(body.get(i-1).getLocation());
        }
        switch (direction) {
            case NORTH: {
                body.get(0).setLocation(body.get(0).getX(),body.get(0).getY()-1);
                break;
            }
            case SOUTH: {
                body.get(0).setLocation(body.get(0).getX(),body.get(0).getY()+1);
                break;
            }
            case EAST: {
                body.get(0).setLocation(body.get(0).getX()+1,body.get(0).getY());
                break;
            }
            case WEST: {
                body.get(0).setLocation(body.get(0).getX()-1,body.get(0).getY());
                break;
            }
        }
    }

    private void transferToGUI(Grid grid) {grid.setSnakeCells(body);}

}
