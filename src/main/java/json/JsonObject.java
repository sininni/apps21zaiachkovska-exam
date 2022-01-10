package json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private List<JsonPair> pairs = new ArrayList<JsonPair>();


    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair pair : jsonPairs) {
            pairs.add(pair);
        }
    }

    public JsonObject(List<JsonPair> jsonPairs) {
        for (JsonPair pair : jsonPairs) {
            pairs.add(pair);
        }
    }

    @Override
    public String toJson() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');
        if (!pairs.isEmpty()) {
            for (int i = 0; i < pairs.size(); i++) {
                stringBuilder.append(pairs.get(i).toString());
                if (i < pairs.size() - 1) {
                    stringBuilder.append(", ");
                }

            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public void add(JsonPair jsonPair) {
        pairs.add(jsonPair);
    }

    public Json find(String name) {
        for (JsonPair pair : pairs) {
            if (pair.key.equals(name)) {
                return pair.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        List<JsonPair> newPairs = new ArrayList<JsonPair>();

        if (!pairs.isEmpty()) {
            for (int i = 0; i < pairs.size(); i++) {
                for (int j = 0; j < names.length; j++) {
                    if (pairs.get(i).key.equals(names[j])) {
                        newPairs.add(pairs.get(i));
                    }
                }
            }
        }
        return new JsonObject(newPairs);
    }
}
