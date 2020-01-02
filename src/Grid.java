import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Grid extends JComponent implements KeyListener {
    private int cellSize = 10;
    private static final double margin = 1.0;
    private double XCount = 50.0;
    private double YCount = 50.0;
    private Snake snake;
    private boolean firstIteration = true;
    private Snake.Direction nextDirection;
    private GUI gui;

    Grid(GUI gui) {
        this.gui = gui;
        snake = new Snake(this);
        addKeyListener(this);
        setFocusable(true);
        this.setPreferredSize(
                new Dimension(
                        (int)((XCount *cellSize)+(2*margin)),
                        (int)((YCount *cellSize)+(2*margin))
                )
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double cellWidth = cellSize;
        double cellHeight = cellSize;
        System.out.println(String.format("Cell size: %dx%d", (int)cellWidth, (int)cellHeight));
        for (Point fillCell : snake.getBody()) {
            int cellX = (int)(margin + (fillCell.x * cellWidth));
            int cellY = (int)(margin + (fillCell.y * cellHeight));
            g.setColor(Color.BLACK);
            g.fillRect(cellX, cellY, (int)cellWidth, (int)cellHeight);
        }
        double _width = (XCount *cellWidth);
        double _height = (YCount *cellHeight);
        g.drawRect( (int) margin, (int) margin, (int) _width, (int)_height);
        for (double x = margin; x <= _width; x += cellWidth) {
            g.drawLine((int) x, (int) margin, (int) x, (int) (_height+margin));
        }
        for (double y = margin; y <= _height; y += cellHeight) {
            g.drawLine((int) margin, (int) y, (int) (_width+margin), (int) y);
        }
    }

    void iteration() {
        if (firstIteration) {
            nextDirection = Snake.Direction.getRandom();
            firstIteration = false;
            System.out.printf("First direction: %s\n", nextDirection.toString());
        }
        snake.move(nextDirection);
        repaint();
        requestFocus();
    }

    void setCellSize(int size) {
        cellSize = size;
        this.setPreferredSize(
                new Dimension(
                        (int)((XCount *cellSize)+(2*margin)),
                        (int)((YCount *cellSize)+(2*margin))
                )
        );
    }

    int getXCount() {return (int) XCount;}

    int getYCount() {return (int) YCount;}

    /*void setXCount(int count) {XCount = count;}*/

    /*void setYCount(int count) {YCount = count;}*/

    void reset() {
        snake.reset(this);
        repaint();
        firstIteration = true;
    }

    // KeyListener methods

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_LEFT: {// Left arrow
                System.out.println("Left arrow clicked");
                nextDirection = Snake.Direction.WEST;
                break;
            }
            case KeyEvent.VK_UP: {// Up arrow
                System.out.println("Up arrow clicked");
                nextDirection = Snake.Direction.NORTH;
                break;
            }
            case KeyEvent.VK_RIGHT: {// Right arrow
                System.out.println("Right arrow clicked");
                nextDirection = Snake.Direction.EAST;
                break;
            }
            case KeyEvent.VK_DOWN: {// Down arrow
                System.out.println("Down arrow clicked");
                nextDirection = Snake.Direction.SOUTH;
                break;
            }
            case KeyEvent.VK_ESCAPE: {
                gui.startButton.doClick();
                break;
            }
            case KeyEvent.VK_R: {
                if (gui.resetButton.isEnabled()) {
                    gui.resetButton.doClick();
                }
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}