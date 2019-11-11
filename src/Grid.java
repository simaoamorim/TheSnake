import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Grid extends JComponent {
    private java.util.List<Point> snakeCells;
    private int cellSize = 10;
    private static final double margin = 1.0;
    private double XCount = 50.0;
    private double YCount = 50.0;

    Grid() {
        snakeCells = new ArrayList<>();
        this.setPreferredSize(new Dimension((int)((XCount *cellSize)+(2*margin)), (int)((YCount *cellSize)+(2*margin))));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double cellWidth = cellSize;
        double cellHeight = cellSize;
        System.out.println(String.format("Cell size: %dx%d", (int)cellWidth, (int)cellHeight));
        for (Point fillCell : snakeCells) {
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

    void setCellSize(int size) {
        cellSize = size;
        this.setPreferredSize(new Dimension((int)((XCount *cellSize)+(2*margin)), (int)((YCount *cellSize)+(2*margin))));
    }

    int getXCount() {return (int) XCount;}

    int getYCount() {return (int) YCount;}

    void setXCount(int count) {XCount = count;}

    void setYCount(int count) {YCount = count;}

    void setSnakeCells(List<Point> list) {
        snakeCells.clear(); snakeCells.addAll(list);}

    void appendSnakeCell(Point point) {
        snakeCells.add(point);}
}