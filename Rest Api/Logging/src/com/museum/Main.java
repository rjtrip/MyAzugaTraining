/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */
package  com.museum;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static org.junit.Assert.*;
import static org.testng.AssertJUnit.assertEquals;
// import com.javatpoint.logic.*;
import org.junit.*;
import org.testng.annotations.Test;

public class Main {

    public final static Logger logger = Logger.getLogger(Main.class);
    private static final Class<?> JSON_ARRAY = JSONArray.class;

    private static final Class<?> JSON_OBJECT = JSONObject.class;

    private static final String srcPath = "/Users/azuga/documents/training/day6/museum testy1/files/Museum.json";


    private static final String destPath = "/Users/azuga/Downloads/MUSEUM_LOG/testfinal2.csv";





    public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchFieldException, IllegalAccessException, JSONException {

        /*
         *
         *   Read the input from srcPath
         */
//        logger.trace("This is a Trace");
//        logger.debug("This is a Debug");
//
//        logger.warn("This is a Warn");
//        logger.error("This is an Error");
//        logger.fatal("This is a Fatal");


        List<LinkedHashMap<String, String>> flatJson = parseJson(new File(srcPath));

        /*
         *
         *  writing data to file with separator destPath
         */

        writeToFile(getCSV(flatJson, ","), destPath);

    }

    /*
     *    parsejson :  it takes  inputs as file --. return json string
     *
     */
    @SuppressWarnings("deprecation")
    private static List<LinkedHashMap<String, String>> parseJson(File file) throws IOException, NoSuchFieldException, IllegalAccessException, JSONException {
        logger.info("Parse Json method with File arg is called ");
        List<LinkedHashMap<String, String>> flatJson = null;
        String json = "";

        if(file!=null || file.exists()){
            json = FileUtils.readFileToString(file);
            logger.info("File collected in method passed to Parse json as string");
            flatJson = parseJson(json);
        }
        else{
            logger.error("File not found");
        }
        logger.debug(flatJson);

        return flatJson;
    }

    /*
     *    parsejson :  it takes  inputs as json --. return  List<LinkedHashMap>
     *
     */
    private static List<LinkedHashMap<String, String>> parseJson(String json) throws NoSuchFieldException, IllegalAccessException, JSONException {
        List<LinkedHashMap<String, String>> flatJson = null;
        logger.info("Parse Json method  with String arg is called ");
        logger.info("String json used as arg in printJson method");
        JSONObject jsonObject =  printJSONObject(json);
        flatJson = new ArrayList<LinkedHashMap<String, String>>();
//        if(jsonObject.){
            logger.info("String json used as arg in printJson method");
            flatJson.add(parse(jsonObject));


        logger.debug(flatJson);

        return flatJson;
    }


    public static JSONObject printJSONObject(String json) throws NoSuchFieldException, IllegalAccessException {
        JSONObject jsonObject = new JSONObject();
        logger.info("Parse Json Object method  with String arg is called ");

            Field changeMap = jsonObject.getClass().getDeclaredField("map");
            changeMap.setAccessible(true);
            changeMap.set(jsonObject, new LinkedHashMap<>());
            changeMap.setAccessible(false);


        logger.info("Json string  converted to String ");
        logger.debug(jsonObject);


        return jsonObject;

    }



    private static LinkedHashMap<String, String> parse(JSONObject jsonObject) throws JSONException {
        LinkedHashMap<String, String> flatJson = new LinkedHashMap<String, String>();
        logger.info("Parse  method  with JSON object arg is called ");

            flatten(jsonObject, flatJson, "");

        logger.debug(flatJson);
        logger.info("Json object parsed  to flattening method");

        return flatJson;
    }

    /*
     *   handleAsArray : take input as json , parse to json array
     *
     *
     */

    private static List<LinkedHashMap<String, String>> handleAsArray(String json) throws JSONException {
        List<LinkedHashMap<String, String>> flatJson = null;
        logger.info("Handle as array method  with String arg is called ");

        if(!json.isEmpty()){
            JSONArray jsonArray = new JSONArray(json);
            logger.info("Parse method called with json Array as  arg");
            flatJson = parse(jsonArray);

        } else{
            logger.error("Error occurred . Unable to find json string");

        }
        logger.debug(flatJson);
        logger.info("Handle as array used to handled multiple array objects");

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
        logger.info("Flatten method  with JSONObject arg is called ");
        String _prefix = prefix != "" ? prefix + "." : "";

        while (iterator.hasNext()) {
            String key = iterator.next().toString();

            if (obj.get(key).getClass() == JSON_OBJECT) {
                JSONObject jsonObject = (JSONObject) obj.get(key);
                logger.debug(obj.get(key).toString());
                flatten(jsonObject, flatJson, _prefix + key);
                logger.debug("In flatten json object is value");
            } else if (obj.get(key).getClass() == JSON_ARRAY) {
                JSONArray jsonArray = (JSONArray) obj.get(key);
                logger.debug(obj.get(key).toString());
                logger.debug("In flatten json array is value");

                if (jsonArray.length() < 1) {
                    continue;
                }
                logger.info("Flatten method called with json Array as  arg");
                flatten(jsonArray, flatJson, _prefix + key);
            } else {
                logger.debug("In flatten string value");
                String value = obj.get(key).toString();
                logger.debug(obj.get(key).toString());


                String updateValue = value != "" && value != null && value.length() > 0 ? value : "null";
                logger.info("Flatten method flattened the json object");

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
        logger.debug(obj.length());
        logger.info("Flatten method  with JSONArray arg is called ");
        if(length<100)
            logger.warn("Too less data");
        logger.info("flatten method used to flatten Json array");



        for (int i = 0; i < length; i++) {
            if(!flatJson.isEmpty()){
                if (obj.get(i).getClass() == JSON_ARRAY) {
                    JSONArray jsonArray = null;
                    logger.debug("In flatten json array is value");
                   if((JSONArray) obj.get(i)!=null){
                        jsonArray = (JSONArray) obj.get(i);
                        logger.debug(obj.get(i).toString());
                    } else{
                        logger.error("Error occured as unable to get json object");
                    }

                    // jsonArray is empty
                    if (jsonArray.length() < 1) {
                        continue;
                    }

                    flatten(jsonArray, flatJson, prefix + "[" + i + "]");
                } else if (obj.get(i).getClass() == JSON_OBJECT) {
                    logger.debug("In flatten json array is called as  value");
                    JSONObject jsonObject = (JSONObject) obj.get(i);
                    logger.debug(obj.get(i).toString());
                    flatten(jsonObject, flatJson, prefix + "[" + (i + 1) + "]");
                } else {
                    logger.debug("In flatten string value");
                    String value = obj.get(i).toString();
                    logger.debug(obj.get(i).toString());
                    String updateValue = value != "" && value != null && value.length() > 0 ? value : "null";
                    // if (value != null) {
                    flatJson.put(prefix + "[" + (i + 1) + "]", updateValue);
                    // }
                }
            } else{
                logger.error("Error occured. Json file is invalid");
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
        logger.info("Parse method  with JSONArray arg is called ");
        List<LinkedHashMap<String, String>> flatJson = new ArrayList<LinkedHashMap<String, String>>();
        int length = jsonArray.length();

        for (int i = 0; i < length; i++) {
            JSONObject obj  = null;
            if(jsonArray.getJSONObject(i)!=null){
                obj = jsonArray.getJSONObject(i);
                logger.debug(obj.toString());
            } else{
                logger.error("Error occured.Unable to get json object");
            }
            new JSONObject(obj);
            LinkedHashMap<String, String> stringLinkedHashMap = parse(jsonObject);
            flatJson.add(stringLinkedHashMap);

        }
        logger.info("Json array parsed to list");

        // logger.debug(flatJson);
        return flatJson;
    }
    /*
     *
     *   writeToFile: take input as csvString and fileName and write data to file.
     */
    @SuppressWarnings("deprecation")
    private static void writeToFile(String csvString, String fileName) throws IOException {
        logger.info("Write to File method  with String arg is called ");
        if(!fileName.isEmpty()){
            FileUtils.write(new File(fileName), csvString);
        } else {
            logger.error("Error occured.Unable to write csv to file" );
        }
        logger.info("File written to Csv String");

    }

    /*
     *
     * Preparaing the data.
     */
    private static String getCSV(List<LinkedHashMap<String, String>> flatJson, String separator) {
        Set<String> headers = collectHeaders(flatJson);
        logger.info("get CSV method  with String arg is called ");
        String csvString = StringUtils.join(headers.toArray(), separator) + "\n";

        if(!flatJson.isEmpty()) {
            for (LinkedHashMap<String, String> LinkedHashMap : flatJson) {
                csvString = csvString + getSeperatedColumns(headers, LinkedHashMap, separator) + "\n";

            }
        } else{
            logger.error("Error occured. Unable to convert to csv");
        }
       // logger.debug(csvString);
        logger.info("Csv fetched from the Flattened json");

        return csvString;
    }

    /*
     *
     *  Collection the header from  flat json.
     */

    private static Set<String> collectHeaders(List<LinkedHashMap<String, String>> flatJson) {
        Set<String> headers = new LinkedHashSet<String>();
        logger.info("Collect headers method  with String arg is called ");

        if(!(flatJson.isEmpty())){
            for (LinkedHashMap<String, String> LinkedHashMap : flatJson) {
                headers.addAll(LinkedHashMap.keySet());


            }
        } else{
            logger.error("Error occured.Unable to collect headers");
        }
        logger.info("List of headers collected");
        return headers;
    }

    /*
     *
     *  it called by getCSV to prepare the data .
     */

    private static String getSeperatedColumns(Set<String> headers, LinkedHashMap<String, String> LinkedHashMap,
                                              String separator) {
        List<String> items = new ArrayList<String>();
        logger.info("Get separtaed Columns method  with String arg is called ");

        if(headers.size()>=1){
            for (String header : headers) {
                String value = LinkedHashMap.get(header) == null ? ""
                        : LinkedHashMap.get(header).replaceAll("[\\,\\;\\r\\n\\t\\s]+", " ");
                items.add(value);
            }
        } else{
            logger.error("Error occured . Unable tp separate columns");
        }
        logger.debug(items);
        logger.info("Headers set  is used top get separated columns");

        return StringUtils.join(items.toArray(), separator);
    }

}
