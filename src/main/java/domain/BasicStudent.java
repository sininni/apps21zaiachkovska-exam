package domain;

import json.*;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by Andrii_Rodionov on 1/5/2017.
 */
public class BasicStudent implements Jsonable {

    protected String name;
    protected String surname;
    protected Integer year;
    private Object[] vars = new Object[3];

    public BasicStudent() {
    }

    public BasicStudent(String name, String surname, Integer year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        vars[0] = this.name; vars[1] = this.surname; vars[2] = this.year;
        System.out.println(Arrays.toString(vars));
    }

    private Json toJson(String object) {
        return new JsonString(object);
    }

    private Json toJson(Number object) {
        return new JsonNumber(object);
    }

    private Json toJson (Object obj) {
        if (obj instanceof String) {
            return toJson(obj.toString());
        }
        else if (obj instanceof Number) {
            return  toJson((Number) obj);
        }
        return null;
    }

    @Override
    public JsonObject toJsonObject() {

        JsonObject jsonObject = new JsonObject();
        Field[] fields = this.getClass().getDeclaredFields();

        System.out.println(fields.length);

        for (int i = 0; i < vars.length; i++) {
            jsonObject.add(new JsonPair(fields[i].getName(), toJson(vars[i])));
        }
        return jsonObject;
    }
}
