package maunaloax.views.chart;

import oahux.chart.IDateBoundaryRuler;
import oahux.chart.IRuler;
import org.apache.log4j.Logger;
import java.time.Instant;
import java.time.LocalDate;

import java.time.ZoneId;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: rcs
 * Date: 3/29/13
 * Time: 6:31 PM
 */
public class DefaultDateRuler implements IDateBoundaryRuler {
    private Logger log = Logger.getLogger(getClass().getPackage().getName());

    private final double x0;
    private final double ppx;
    private final LocalDate start;
    private final LocalDate end;
    private final int snapUnit;

    public static DefaultDateRuler createDummy() {
        return new DefaultDateRuler(0,
                                    new Date(2010-1900,0,1),
                                    new Date(2020-1900,11,1),
                                    0.002,
                                    10);
    }
    public DefaultDateRuler(double x0, Date start, Date end, double ppx, int snapUnit) {
        this.x0 = x0;
        this.ppx = ppx;
        this.start = date2loc(start);
        this.end = date2loc(end);
        this.snapUnit = snapUnit;
    }

    //region Interface Methods
    @Override
    public double calcPix(Object value) {
        LocalDate valuex = null;
        if (value instanceof Date) {
            valuex = ((Date)value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        else {
            valuex = (LocalDate)value;
        }
        int daysElapsed =  100; //===>>> Days.daysBetween(start,valuex).getDays();

        double result = x0 + (ppx * daysElapsed);

        if (log.isDebugEnabled()) {
            log.debug(String.format("(calcPix) ppx: %.4f, daysElapsed: %d, result: %.4f", ppx, daysElapsed, result));

        }

        return result;
    }


    @Override
    public Object calcValue(double pix) {

        int adjSec = calcAdjustedSections(pix);

        LocalDate dm = start.plusDays(adjSec);

        //return dm.toDate();
        return dm;
    }


    @Override
    public double snapTo(double pix) {
        switch (snapUnit) {
            case IRuler.SNAP_UNIT_DAY:
                return calculateSnapToDay(pix);
            case IRuler.SNAP_UNIT_WEEK:
                return calculateSnapToWeek(pix);
            default:
               return pix;
        }
    }
    @Override
    public Date getStartDate() {
        return loc2date(start);
    }


    @Override
    public Date getEndDate() {
        return loc2date(end);
    }
    //endregion Interface Methods

    //region Private methods

    private double calculateSnapToWeek(double pix) {
        LocalDate curDay = (LocalDate)calcValue(pix);
        LocalDate nearestFriday = calcNearestFriday(curDay);
        if (log.isDebugEnabled()) {
            log.debug(String.format("(calculateSnapToWeek) pix: %.4f, curDay: %s, nearest friday: %s", pix, curDay, nearestFriday));
        }
        return calcPix(nearestFriday);
    }

    private LocalDate calcNearestFriday(LocalDate curDay) {
        return null; //===>>> curDay.withDayOfWeek(DateTimeConstants.FRIDAY);
    }

    private double calculateSnapToDay(double pix) {
        int adjustedSections = calcAdjustedSections(pix);

        double result = (adjustedSections  * ppx) + x0;

        if (log.isDebugEnabled()) {
            log.debug(String.format("x0: %.4f, ppx: %.4f, pix: %.2f, result: %.4f", x0, ppx, pix, result));
        }
        return result;
    }

    private int calcAdjustedSections(double pix) {
        double actualPix = pix - x0;

        double dblSections = actualPix / ppx;

        int intSections = (int)dblSections;

        double remainder = dblSections - intSections;

        int result =  remainder >= 0.5 ? intSections + 1 : intSections;

        if (log.isDebugEnabled()) {
            log.debug(String.format("(calcAdjustedSections) pix: %.4f, act. pix: %.4f, sections: %d, remainder: %.4f, result: %d",
                    pix,
                    actualPix,
                    intSections,
                    remainder,
                    result));
        }
        return result;
    }


    private Date loc2date(LocalDate loc) {
        Instant instant = loc.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
    private LocalDate date2loc(Date dx) {
        return dx.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    //endregion Private methods

}
