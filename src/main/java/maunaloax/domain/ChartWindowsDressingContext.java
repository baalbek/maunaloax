package maunaloax.domain;

import com.mongodb.DBObject;
import maunaloax.models.ChartWindowDressingModel;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: rcs
 * Date: 28.01.14
 * Time: 14:19
 */
public class ChartWindowsDressingContext {
    private final int collection;
    private final String ticker;
    private final long loc;
    private int activeSwitch = ChartWindowDressingModel.ACTIVE;
    private DBObject p1;
    private DBObject p2;
    private Date fromDate;
    private Date toDate;
    private double value;

    public ChartWindowsDressingContext(int collection,
                                       String ticker,
                                       long loc) {
        this.collection = collection;
        this.ticker = ticker;
        this.loc = loc;
    }

    public int getActiveSwitch() {
        return activeSwitch;
    }

    public void setActiveSwitch(int activeSwitch) {
        this.activeSwitch = activeSwitch;
    }

    public DBObject getP1() {
        return p1;
    }

    public void setP1(DBObject p1) {
        this.p1 = p1;
    }

    public DBObject getP2() {
        return p2;
    }

    public void setP2(DBObject p2) {
        this.p2 = p2;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getCollection() {
        return collection;
    }

    public String getTicker() {
        return ticker;
    }

    public long getLoc() {
        return loc;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
