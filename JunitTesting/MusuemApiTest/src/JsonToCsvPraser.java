/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */

import java.io.File;

import java.io.IOException;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonToCsvPraser {

    private static final Class<?> JSON_ARRAY = JSONArray.class;

    private static final Class<?> JSON_OBJECT = JSONObject.class;



    public static final String srcPath = "/Users/azuga/documents/training/day6/museum testy1/files/Museum.json";

    public static final String destPath = "/Users/azuga/Downloads/museum testy1/museum_csvv.csv";

    public static void main(String[] args) {
        /*
         *
         *   Read the input from srcPath
         */

        List<LinkedHashMap<String, String>> flatJson = parseJson(new File(""));

        /*
         *
         *  writing data to file with separator ,
         */

        writeToFile(getCSV(flatJson, ","), destPath);
    }

    /*
     *    parsejson :  it takes  inputs as file --. return json string
     *
     */
    @SuppressWarnings("deprecation")
    public static List<LinkedHashMap<String, String>> parseJson(File file) {
        List<LinkedHashMap<String, String>> flatJson = null;
        String json = "";

        try {
            json = FileUtils.readFileToString(file);
            flatJson = parseJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //System.out.println(flatJson);

        return flatJson;
    }

    /*
     *    parsejson :  it takes  inputs as json --. return  List<LinkedHashMap>
     *
     */
    private static List<LinkedHashMap<String, String>> parseJson(String json) {
        List<LinkedHashMap<String, String>> flatJson = null;

        try {
            JSONObject jsonObject = new JSONObject(json);
            flatJson = new ArrayList<LinkedHashMap<String, String>>();
            flatJson.add(parse(jsonObject));
        } catch (JSONException je) {

            flatJson = handleAsArray(json);
        }
        //System.out.println(flatJson);

        return flatJson;
    }


    private static LinkedHashMap<String, String> parse(JSONObject jsonObject) throws JSONException {
        LinkedHashMap<String, String> flatJson = new LinkedHashMap<String, String>();
        flatten(jsonObject, flatJson, "");
        //System.out.println(flatJson);

        return flatJson;
    }

    /*
     *   handleAsArray : take input as json , parse to json array
     *
     *
     */

    private static List<LinkedHashMap<String, String>> handleAsArray(String json) {
        List<LinkedHashMap<String, String>> flatJson = null;

        try {
            JSONArray jsonArray = new JSONArray(json);
            flatJson = parse(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();

        }
        //System.out.println(flatJson);

        return flatJson;
    }

    /*
     *
     *
     *   flatten -- take the input jsonObject and we take keys verify the given object or array or normal.
     *
     *   Recursion Logic is used internal.
     */

    private static void flatten(JSONObject obj, LinkedHashMap<String, String> flatJson, String prefix) throws JSONException {
        Iterator<?> iterator = obj.keys();
        String _prefix = prefix != "" ? prefix + "." : "";
        System.out.println(_prefix);

        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            System.out.println(key);

            if (obj.get(key).getClass() == JSON_OBJECT) {
                JSONObject jsonObject = (JSONObject) obj.get(key);
                flatten(jsonObject, flatJson, _prefix + key);
            } else if (obj.get(key).getClass() == JSON_ARRAY) {
                JSONArray jsonArray = (JSONArray) obj.get(key);

                if (jsonArray.length() < 1) {
                    continue;
                }

                flatten(jsonArray, flatJson, _prefix + key);
            } else {
                String value = obj.get(key).toString();
                /*
                 * remove these checks
                 */
                String updateValue = value != "" && value != null && value.length() > 0 ? value : "null";

                flatJson.put(_prefix + key, updateValue);
            }
        }

    }

    /*
     *
     *    flatten -->  read the arry obj and check the json arry or object or normal value
     *
     *    Recursion Logic is used internal
     *
     */
    private static void flatten(JSONArray obj, LinkedHashMap<String, String> flatJson, String prefix) throws JSONException {
        int length = obj.length();

        for (int i = 0; i < length; i++) {
            if (obj.get(i).getClass() == JSON_ARRAY) {
                JSONArray jsonArray = (JSONArray) obj.get(i);

                // jsonArray is empty
                if (jsonArray.length() < 1) {
                    continue;
                }

                flatten(jsonArray, flatJson, prefix + "[" + i + "]");
            } else if (obj.get(i).getClass() == JSON_OBJECT) {
                JSONObject jsonObject = (JSONObject) obj.get(i);
                flatten(jsonObject, flatJson, prefix + "[" + (i + 1) + "]");
            } else {
                String value = obj.get(i).toString();
                String updateValue = value != "" && value != null && value.length() > 0 ? value : "null";
                // if (value != null) {
                flatJson.put(prefix + "[" + (i + 1) + "]", updateValue);
                // }
            }
        }
    }

    /*
     *
     *
     *   parse : take input as json array add object to Map object
     */

    private static List<LinkedHashMap<String, String>> parse(JSONArray jsonArray) throws JSONException {
        JSONObject jsonObject = null;
        List<LinkedHashMap<String, String>> flatJson = new ArrayList<LinkedHashMap<String, String>>();
        int length = jsonArray.length();

        for (int i = 0; i < length; i++) {
            jsonObject = jsonArray.getJSONObject(i);
            LinkedHashMap<String, String> stringLinkedHashMap = parse(jsonObject);
            flatJson.add(stringLinkedHashMap);
        }

        return flatJson;
    }
    /*
     *
     *   writeToFile: take input as csvString and fileName and write data to file.
     */
    @SuppressWarnings("deprecation")
    public static void writeToFile(String csvString, String fileName) {
        try {
            FileUtils.write(new File(fileName), csvString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *
     * Preparaing the data.
     */
    public static String getCSV(List<LinkedHashMap<String, String>> flatJson, String separator) {
        Set<String> headers = collectHeaders(flatJson);
        String csvString = StringUtils.join(headers.toArray(), separator) + "\n";

        for (LinkedHashMap<String, String> LinkedHashMap : flatJson) {
            csvString = csvString + getSeperatedColumns(headers, LinkedHashMap, separator) + "\n";
        }

        return csvString;
    }

    /*
     *
     *  Collection the header from  flat json.
     */

    private static Set<String> collectHeaders(List<LinkedHashMap<String, String>> flatJson) {
        Set<String> headers = new LinkedHashSet<String>();

        for (LinkedHashMap<String, String> LinkedHashMap : flatJson) {
            headers.addAll(LinkedHashMap.keySet());
        }

        return headers;
    }

    /*
     *
     *  it called by getCsv to prepare the data .
     */

    private static String getSeperatedColumns(Set<String> headers, LinkedHashMap<String, String> LinkedHashMap,
                                              String separator) {
        List<String> items = new ArrayList<String>();
        for (String header : headers) {
            String value = LinkedHashMap.get(header) == null ? ""
                    : LinkedHashMap.get(header).replaceAll("[\\,\\;\\r\\n\\t\\s]+", " ");
            items.add(value);
        }

        return StringUtils.join(items.toArray(), separator);
    }

}
