import com.mongodb.*;

/**
 * Created by Vera_Sidarovich on 8/18/2014.
 */
public class Homework2 {
    public static void main(String... args) throws Exception {
        Mongo mongo = new Mongo("localhost", 27017);
        DB school = mongo.getDB("school");
        DBCollection students = school.getCollection("students");

        //    db.students.aggregate( {$match : {"_id": new NumberLong(0)}},{$unwind : "$scores"},{$match : {"scores.type": "homework"}},{$sort: {"scores.score": 1}},{$limit: 1})

        DBObject unwindObject = new BasicDBObject("$unwind", "$scores");
        DBObject matchScoresObject = new BasicDBObject("$match", new BasicDBObject("scores.type", "homework"));
        DBObject sortObject = new BasicDBObject("$sort", new BasicDBObject("scores.score", 1));
        DBObject limitObject = new BasicDBObject("$limit", 1);

        DBCursor result = students.find();


        while (result.hasNext()) {
            DBObject studentObject = result.next();
            Object idValue = studentObject.get("_id");

            DBObject matchObject = new BasicDBObject("$match", new BasicDBObject("_id", idValue));

            AggregationOutput output = students.aggregate(matchObject, unwindObject, matchScoresObject, sortObject, limitObject);
            Iterable<DBObject> results = output.results();


            for (DBObject homework : results) {
                BasicDBObject pullObject = new BasicDBObject("$pull", new BasicDBObject("scores", homework.get("scores")));
                students.update(studentObject, pullObject);
            }
        }
    }
}
