package maunaloax.views.chart;

import javafx.geometry.Point2D;
import oahu.exceptions.NotImplementedException;
import oahux.chart.IBoundaryRuler;
import org.apache.log4j.Logger;


/**
 * Created with IntelliJ IDEA.
 * User: rcs
 * Date: 3/29/13
 * Time: 6:30 PM
 */
public class DefaultVRuler implements IBoundaryRuler<Double> {
    private Logger log = Logger.getLogger(getClass().getPackage().getName());
    private final Point2D ul;
    private final Point2D lr;
    private final double ppx;
    private final double maxValue;

    public static DefaultVRuler createDummy() {
        return new DefaultVRuler(new Point2D(0,0),
                                 new Point2D(200,200),
                                 0.005,
                                 100.0);
    }
    public DefaultVRuler(Point2D ul, Point2D lr, double ppx, double maxValue) {
        this.ppx = ppx;
        this.ul = ul;
        this.lr = lr;
        this.maxValue = maxValue;
    }

    @Override
    public double calcPix(Double valx) {

        //double valx = ((Double)value).doubleValue();
        double diff = maxValue - valx;

        double result = ul.getY() + (ppx * diff);

        if (log.isDebugEnabled()) {
            log.debug(String.format("Ppx: %.2f, maxValue: %.2f, value: %.2f, diff: %.2f, result: %.2f",
                    ppx,maxValue,valx,diff,result));
        }

        return result;
    }

    @Override
    public Double calcValue(double pix) {
        double pxv = 1.0 / ppx;
        double v = pxv * (pix - ul.getY());

        return (maxValue - v);
    }

    @Override
    public double snapTo(double pix) {
        throw new NotImplementedException();
    }

    @Override
    public Point2D getLowerRight() {
        return lr;
    }

    @Override
    public Point2D getUpperLeft() {
        return ul;
    }

    @Override
    public String toString() {
        return "Upper left: " + ul.toString() + ", lower right: " + lr.toString();
    }

}
