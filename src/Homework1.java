/**
 * Created by Vera_Sidarovich on 10/23/2014.
 */
*/
public class Homework1 {
    public static void main(String... args) throws Exception {
        Mongo mongo = new Mongo("localhost", 27017);
        DB school = mongo.getDB("students");
        DBCollection students = school.getCollection("grades");


        DBObject groupFields = new BasicDBObject("_id", "$student_id");
        DBObject group = new BasicDBObject("$group", groupFields);

        // db.grades.aggregate({$group : {"_id": "$student_id"}})
        Iterable<DBObject> result = students.aggregate(group).results();

        for (DBObject studentObject : result) {

            Object idValue = studentObject.get("_id");

            BasicDBObject firstCond = new BasicDBObject("student_id", idValue);
            BasicDBObject secondCond = new BasicDBObject("type", "homework");

            ArrayList<BasicDBObject> objectsArray = new ArrayList<BasicDBObject>();
            objectsArray.add(firstCond);
            objectsArray.add(secondCond);

            DBObject matchObject = new BasicDBObject("$and", objectsArray);

            //db.grades.find({$and: [{"student_id":1}, {"type":"homework"}]}).sort({"score":1}).limit(1)
            DBObject object = students.find(matchObject).sort(new BasicDBObject("score", 1)).limit(1).next();

            students.remove(object);

        }


    }
}
