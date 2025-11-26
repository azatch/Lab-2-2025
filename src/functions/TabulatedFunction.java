package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int pointsCount;

    public TabulatedFunction(double leftX,
                             double rightX,
                             int pointsCount) {
        this.pointsCount = pointsCount;
        this.points = new FunctionPoint[pointsCount];

        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + step * i;
            double y = 0.0;
            points[i] = new FunctionPoint(x, y);
        }
    }

    public TabulatedFunction(double leftX,
                             double rightX,
                             double[] values) {
        this.pointsCount = values.length;
        this.points = new FunctionPoint[pointsCount];

        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + step * i;
            double y = values[i];
            points[i] = new FunctionPoint(x, y);
        }
    }

    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[pointsCount - 1].getX();
    }

    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }

        for (int i = 0; i < pointsCount; i++) {
            if (points[i].getX() == x) {
                return points[i].getY();
            }
        }

        int i = 0;
        while (!(points[i].getX() < x && x < points[i + 1].getX())) {
            i++;
        }

        double x1 = points[i].getX();
        double y1 = points[i].getY();
        double x2 = points[i + 1].getX();
        double y2 = points[i + 1].getY();

        double y = y1 + (x - x1) * (y2 - y1) / (x2 - x1);
        return y;
    }

    public int getPointsCount() {
        return pointsCount;
    }

    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }

    public void setPoint(int index,
                         FunctionPoint point) {
        double newX = point.getX();

        if (index > 0) {
            double leftX = points[index - 1].getX();
            if (newX <= leftX) {
                return;
            }
        }

        if (index < pointsCount - 1) {
            double rightX = points[index + 1].getX();
            if (newX >= rightX) {
                return;
            }
        }

        points[index] = new FunctionPoint(point);
    }

    public double getPointX(int index) {
        return points[index].getX();
    }

    public void setPointX(int index, double x) {
        if (index > 0) {
            double leftX = points[index - 1].getX();
            if (x <= leftX) {
                return;
            }
        }

        if (index < pointsCount - 1) {
            double rightX = points[index + 1].getX();
            if (x >= rightX) {
                return;
            }
        }

        points[index].setX(x);
    }

    public double getPointY(int index) {
        return points[index].getY();
    }

    public void setPointY(int index, double y) {
        points[index].setY(y);
    }

    public void deletePoint(int index) {
        if (pointsCount <= 1) {
            return;
        }

        if (index < pointsCount - 1) {
            System.arraycopy(points,
                    index + 1,
                    points,
                    index,
                    pointsCount - index - 1);
        }

        pointsCount--;
        points[pointsCount] = null;
    }

    public void addPoint(FunctionPoint point) {
        if (pointsCount == points.length) {
            return;
        }

        double x = point.getX();

        int insertIndex = 0;
        while (insertIndex < pointsCount && points[insertIndex].getX() < x) {
            insertIndex++;
        }

        if (insertIndex < pointsCount) {
            System.arraycopy(points,
                    insertIndex,
                    points,
                    insertIndex + 1,
                    pointsCount - insertIndex);
        }

        points[insertIndex] = new FunctionPoint(point);
        pointsCount++;
    }
}
