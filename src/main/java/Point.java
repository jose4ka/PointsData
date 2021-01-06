public class Point {

    private enum Position{
        LD,
        RD,
        LU,
        RU
    }

    private boolean isFirstPoint = false;
    private int x, y;
    private Position position;
    private Point parentPoint;
    private Point lD, rD, lU, rU;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    /*
    При добавлении нового элемента, мы проверяем каждую ветку на null,
    если ветка равна null - значит добавляем в неё элемент.
    Иначе - в элемент метки добавляем новый элемент
     */
    public void addPoint(Point p){
        p.parentPoint = this;

        if (p.x > x && p.y > y){
            if (rU == null){
                p.position = Position.RU;
                rU = p;
            }
            else {
                rU.addPoint(p);
            }
        }
        else if(p.x < x && p.y < y){
            if (lD == null){
                p.position = Position.LD;
                lD = p;
            }
            else {
                lD.addPoint(p);
            }
        }
        else if(p.x < x && p.y > y){
            if (lU == null){
                p.position = Position.LU;
                lU = p;
            }
            else {
                lU.addPoint(p);
            }
        }
        else if(p.x > x && p.y < y){
            if (rD == null){
                p.position = Position.RD;
                rD = p;
            }
            else {
                rD.addPoint(p);
            }
        }
    }

    /*
    Проверяем каждую ветку на наличие нужного элемента
     */
    public Point find(int x, int y){

        if (this.x == x && this.y == y){
            return this;
        }
        else {
            if (lD != null && lD.find(x, y) != null){
                return lD.find(x, y);
            }
            if (rD != null && rD.find(x, y) != null){
                return rD.find(x, y);
            }
            if (lU != null && lU.find(x, y) != null){
                return lU.find(x, y);
            }
            if (rU != null && rU.find(x, y) != null){
                return rU.find(x, y);
            }
        }
        return null;
    }


    /*
    Проверяет только текущую точку и дочерние точки!
    Нельзя посмотреть точки которые находятся выше!
     */
    public boolean isContains(Point p){
        if (this.x == p.x && this.y == p.y){
            return true;
        }
        else {
            if (lD != null){
                return lD.isContains(p);
            }
            if (rD != null){
                return rD.isContains(p);
            }
            if (lU != null){
                return lU.isContains(p);
            }
            if (rU != null){
                return rU.isContains(p);
            }
        }

        return false;
    }

    /*
    Когда мы удаляем какой-то элемент, мы в родительский добавляем
    все дочерние элементы текущей ветки, и удаляем ссылку на текущий элемент
    из родительского
     */
    public void remove(Point pointToDelete){
        if (this.x == pointToDelete.x && this.y == pointToDelete.y){

            readChildPoints(new PointsReader() {
                @Override
                public void onPointReaded(Point point) {
                    parentPoint.addPoint(point);
                }
            });

            switch (position){
                case LD:
                    parentPoint.lD = null;
                    break;
                case LU:
                    parentPoint.lU = null;
                    break;
                case RD:
                    parentPoint.rD = null;
                    break;
                case RU:
                    parentPoint.rU = null;
                    break;
            }
        }
        else {
            if (lD != null){
                lD.remove(pointToDelete);
            }
            if (rD != null){
                rD.remove(pointToDelete);
            }
            if (lU != null){
                lU.remove(pointToDelete);
            }
            if (rU != null){
                rU.remove(pointToDelete);
            }
        }
    }

    //Выводим в консоль все имеющиеся точки
    public void showPoints(){
        System.out.println(toString());
        if (lD != null){
            lD.showPoints();
        }
        if (rD != null){
            rD.showPoints();
        }
        if (lU != null){
            lU.showPoints();
        }
        if (rU != null){
            rU.showPoints();
        }
    }

    /*
    Через интерфейс возвращаем каждую точку
     */
    public void readPoints(PointsReader pointsReader){
        pointsReader.onPointReaded(this);
        if (lD != null){
            lD.readPoints(pointsReader);
        }
        if (rD != null){
            rD.readPoints(pointsReader);
        }
        if (lU != null){
            lU.readPoints(pointsReader);
        }
        if (rU != null){
            rU.readPoints(pointsReader);
        }
    }

    public void readChildPoints(PointsReader pointsReader){

        if (lD != null){
            pointsReader.onPointReaded(lD);
            lD.readPoints(pointsReader);
        }
        if (rD != null){
            pointsReader.onPointReaded(rD);
            rD.readPoints(pointsReader);
        }
        if (lU != null){
            pointsReader.onPointReaded(lU);
            lU.readPoints(pointsReader);
        }
        if (rU != null){
            pointsReader.onPointReaded(rU);
            rU.readPoints(pointsReader);
        }
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /*
    Интерфейс для чтения элементов
     */
    public interface PointsReader{
        void onPointReaded(Point point);
    }

    public boolean isFirstPoint() {
        return isFirstPoint;
    }

    public void setFirstPoint(boolean firstPoint) {
        isFirstPoint = firstPoint;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Point getParentPoint() {
        return parentPoint;
    }

    public void setParentPoint(Point parentPoint) {
        this.parentPoint = parentPoint;
    }

    public Point getlD() {
        return lD;
    }

    public void setlD(Point lD) {
        this.lD = lD;
    }

    public Point getrD() {
        return rD;
    }

    public void setrD(Point rD) {
        this.rD = rD;
    }

    public Point getlU() {
        return lU;
    }

    public void setlU(Point lU) {
        this.lU = lU;
    }

    public Point getrU() {
        return rU;
    }

    public void setrU(Point rU) {
        this.rU = rU;
    }
}
