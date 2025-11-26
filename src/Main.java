import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        double left = 0.0;
        double right = 4.0;
        double[] values = {0.0, 1.0, 4.0, 9.0, 16.0};

        TabulatedFunction f = new TabulatedFunction(left, right, values);

        System.out.println("Точки сразу после конструктора");
        for (int i = 0; i < f.getPointsCount(); i++) {
            FunctionPoint p = f.getPoint(i);
            System.out.println("Точка " + i + ": (" + p.getX() + "; " + p.getY() + ")");
        }

        System.out.println("Значения функции (в том числе вне области)");
        for (double x = -1.0; x <= 5.0; x += 1.0) {
            double y = f.getFunctionValue(x);
            System.out.println("x = " + x + ", f(x) = " + y);
        }

        System.out.println("Изменение точки с индексом 2");
        f.setPointY(2, 100.0);
        System.out.println("Новая точка 2: (" + f.getPointX(2) + "; " + f.getPointY(2) + ")");

        System.out.println("Добавление новой точки");
        FunctionPoint newPoint = new FunctionPoint(2.5, 6.25);
        f.addPoint(newPoint);
        for (int i = 0; i < f.getPointsCount(); i++) {
            FunctionPoint p = f.getPoint(i);
            System.out.println("Точка " + i + ": (" + p.getX() + "; " + p.getY() + ")");
        }

        System.out.println("Удаление точки с индексом 1");
        f.deletePoint(1);
        for (int i = 0; i < f.getPointsCount(); i++) {
            FunctionPoint p = f.getPoint(i);
            System.out.println("Точка " + i + ": (" + p.getX() + "; " + p.getY() + ")");
        }
    }
}
