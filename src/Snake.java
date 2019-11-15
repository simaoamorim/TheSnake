import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class Snake {
    private static final int INIT_CAPACITY = 3;
    private ArrayList<Point> body = new ArrayList<>(INIT_CAPACITY);
    public enum Direction{NORTH, SOUTH, EAST, WEST;
        public static Direction getRandom() {
            return values()[new Random().nextInt(values().length)];
        }
    }
//    private Direction prevDirection;

    Snake(Grid grid) {
        for (int i = 0; i < INIT_CAPACITY; i++) {
            body.add(new Point());
            body.get(i).setLocation(grid.getXCount()/2,grid.getYCount()/2);
        }
    }

    void move(Direction direction) {
        System.out.println("Moving snake...");
        System.out.printf("Snake size: %d\n", body.size());
        for (int i = body.size()-1; i > 0; i--) {
            System.out.printf("Setting point %d from (%f,%f) to (%f,%f)\n", i, body.get(i).getX(), body.get(i).getY(), body.get(i-1).getX(), body.get(i-1).getY());
            body.get(i).setLocation(body.get(i-1).getLocation());
        }
        System.out.println("Setting next location");
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
        System.out.println("Snake moved!");
    }

    ArrayList<Point> getBody() {return body;}

    void reset(Grid grid) {
        body.clear();
        for (int i = 0; i < INIT_CAPACITY; i++) {
            body.add(new Point());
            body.get(i).setLocation(grid.getXCount()/2,grid.getYCount()/2);
        }
    }

}
