import java.util.Calendar;
import java.util.Random;

public class Program implements Point.PointsReader {

    long startTime = Calendar.getInstance().getTimeInMillis();

    public void run(){

        Point p = new Point(0,0);
        p.setFirstPoint(true);

        int x = 0;
        int y = 0;

        for (int i = 0; i < 5; i++) {
            x = new Random().nextInt(200) * (new Random().nextBoolean() ? 1 : -1);
            y = new Random().nextInt(200) * (new Random().nextBoolean() ? 1 : -1);
            p.addPoint(new Point(x, y));

        }
        p.showPoints();

        System.out.println("FINDED");
        p.find(x, y).setX(22);
        x = 22;
        p.find(x, y).setY(222);
        y = 222;
        System.out.println("CHANGED");
        p.showPoints();
        System.out.println("X: "+x+" Y: "+y);
        p.remove(p.find(x, y));
        System.out.println("DELETED");
        p.showPoints();

    }

    @Override
    public void onPointReaded(Point point) {
        System.out.println("Readed: "+point.toString());
        System.out.println("Time "+ (Calendar.getInstance().getTimeInMillis() - startTime));
    }

}
