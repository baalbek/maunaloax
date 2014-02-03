package maunaloax.models;

import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import maunaloax.domain.ChartWindowsDressingContext;
import maunaloax.domain.MongoDBResult;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public interface ChartWindowDressingModel {
    public static int MONGO_FIBONACCI = 1;
    public static int MONGO_LEVELS = 2;
    public static int ACTIVE = 1;
    public static int INACTIVE = 2;
    public static int BOTH = 3;
    List<DBObject> fetchComments(ObjectId id);
    WriteResult addComment(ObjectId id, String comment);
    WriteResult updateCoord(ChartWindowsDressingContext ctx);
    MongoDBResult save(ChartWindowsDressingContext ctx);
    List<DBObject> fetch(ChartWindowsDressingContext ctx);
    boolean isCloud();
    void setCloud(boolean value);
    String getMongodbHost();
    void setMongodbHost(String value);
}


