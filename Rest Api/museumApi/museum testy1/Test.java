/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.File;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class Test {

    public static String getCSV(List<LinkedHashMap<String, String>> flatJson) {
        // Use the default separator
        return getCSV(flatJson, ",");
    }

    /**
     * Convert the given List of String keys-values as a CSV String.
     *
     * @param flatJson   The List of key-value pairs generated from the JSON String
     * @param separator  The separator can be: ',', ';' or '\t'
     *
     * @return The generated CSV string
     */
    public static String getCSV(List<LinkedHashMap<String, String>> flatJson, String separator) {
        Set<String> headers = collectHeaders(flatJson);
        String csvString = StringUtils.join(headers.toArray(), separator) + "\n";

        for (LinkedHashMap<String, String> LinkedHashMap : flatJson) {
            csvString = csvString + getSeperatedColumns(headers, LinkedHashMap, separator) + "\n";
        }

        return csvString;
    }

    /**
     * Write the given CSV string to the given file.
     *
     * @param csvString  The csv string to write into the file
     * @param fileName   The file to write (included the path)
     */
    public static void writeToFile(String csvString, String fileName) {
        try {
            FileUtils.write(new File(fileName), csvString);
        } catch (IOException e) {
            //  LOGGER.error("CSVWriter#writeToFile(csvString, fileName) IOException: ", e);
        }
    }

    /**
     * Write the given CSV from a flat json to the given file.
     *
     * @param flatJson
     * @param separator
     * @param fileName
     * @param headers
     */
    public static void writeLargeFile(List<LinkedHashMap<String, String>> flatJson, String separator, String fileName, Set<String> headers){
        String csvString;
        csvString = StringUtils.join(headers.toArray(), separator) + "\n";
        File file = new File(fileName);

        try {
            // ISO8859_1 char code to Latin alphabet
            FileUtils.write(file, csvString, "ISO8859_1");

            for (LinkedHashMap<String, String> LinkedHashMap : flatJson) {
                csvString = "";
                csvString = getSeperatedColumns(headers, LinkedHashMap, separator) + "\n";
                Files.write(Paths.get(fileName), csvString.getBytes("ISO8859_1"), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            // LOGGER.error("CSVWriter#writeLargeFile(flatJson, separator, fileName, headers) IOException: ", e);
        }
    }

    /**
     * Get separated comlumns used a separator (comma, semi column, tab).
     *
     * @param headers The CSV headers
     * @param LinkedHashMap     LinkedHashMap of key-value pairs contains the header and the value
     *
     * @return a string composed of columns separated by a specific separator.
     */
    private static String getSeperatedColumns(Set<String> headers, LinkedHashMap<String, String> LinkedHashMap, String separator) {
        List<String> items = new ArrayList<String>();
        for (String header : headers) {
            String value = LinkedHashMap.get(header) == null ? "" : LinkedHashMap.get(header).replaceAll("[\\,\\;\\r\\n\\t\\s]+", " ");
            items.add(value);
        }

        return StringUtils.join(items.toArray(), separator);
    }

    /**
     * Get the CSV header.
     *
     * @param flatJson
     *
     * @return a Set of headers
     */
    private static Set<String> collectHeaders(List<LinkedHashMap<String, String>> flatJson) {
        Set<String> headers = new LinkedHashSet<String>();

        for (LinkedHashMap<String, String> LinkedHashMap : flatJson) {
            headers.addAll(LinkedHashMap.keySet());
        }

        return headers;
    }

    /**
     * Get the CSV ordered header
     *
     * @param flatJson
     *
     * @return a Set of ordered headers
     */
    public static Set<String> collectOrderedHeaders(List<LinkedHashMap<String, String>> flatJson) {
        Set<String> headers = new TreeSet<String>();
        for (LinkedHashMap<String, String> LinkedHashMap : flatJson) {
            headers.addAll(LinkedHashMap.keySet());
        }
        return headers;
    }




    /**
         * The JSONObject type
         */
        private static final Class<?> JSON_OBJECT = JSONObject.class;
        /**
         * The JSONArray type
         */
        private static final Class<?> JSON_ARRAY = JSONArray.class;
        /**
         * The class Logger
         */
        /**
         * Parse the JSON content at the given URI using the default
         * character encoding UTF-8
         *
         * @param uri
         * @return
         */
        public static List<LinkedHashMap<String, String>> parseJson(URI uri) {
            return parseJson(uri, "UTF-8");
        }

        /**
         * Parse the JSON content at the given URI using the specified
         * character encoding
         *
         * @param uri
         * @return
         */
        public static List<LinkedHashMap<String, String>> parseJson(URI uri, String encoding) {
            List<LinkedHashMap<String, String>> flatJson = null;
            String json = "";

            try {
                json = IOUtils.toString(uri, encoding);
                flatJson = parseJson(json);
            } catch (IOException e) {
                // LOGGER.error("JsonFlattener#ParseJson(uri, encoding) IOException: ", e);
            } catch (Exception ex) {
                // LOGGER.error("JsonFlattener#ParseJson(uri, encoding) Exception: ", ex);
            }

            return flatJson;
        }

        /**
         * Parse the JSON file using the default character encoding UTF-8
         *
         * @param file
         * @return
         */
        public static List<LinkedHashMap<String, String>> parseJson(File file) {
            return parseJson(file, "UTF-8");
        }

        /**
         * Parse the JSON file using the specified character encoding
         *
         * @param file
         * @return
         */
        public static List<LinkedHashMap<String, String>> parseJson(File file, String encoding) {
            List<LinkedHashMap<String, String>> flatJson = null;
            String json = "";

            try {
                json = FileUtils.readFileToString(file, encoding);
                flatJson = parseJson(json);
            } catch (IOException e) {
                //LOGGER.error("JsonFlattener#ParseJson(file, encoding) IOException: ", e);
            } catch (Exception ex) {
                //LOGGER.error("JsonFlattener#ParseJson(file, encoding) Exception: ", ex);
            }

            return flatJson;
        }

        /**
         * Parse the JSON String
         *
         * @param json
         * @return
         * @throws Exception
         */
        public static List<LinkedHashMap<String, String>> parseJson(String json) {
            List<LinkedHashMap<String, String>> flatJson = null;

            try {
                JSONObject jsonObject = new JSONObject(json);
                flatJson = new ArrayList<LinkedHashMap<String, String>>();
                flatJson.add(parse(jsonObject));
            } catch (JSONException je) {
                //  LOGGER.info("Handle the JSON String as JSON Array");
                flatJson = handleAsArray(json);
            }

            return flatJson;
        }

        /**
         * Parse a JSON Object
         *
         * @param jsonObject
         * @return
         */
        public static LinkedHashMap<String, String> parse(JSONObject jsonObject) throws JSONException {
            LinkedHashMap<String, String> flatJson = new LinkedHashMap<String, String>();
            flatten(jsonObject, flatJson, "");

            return flatJson;
        }

        /**
         * Parse a JSON Array
         *
         * @param jsonArray
         * @return
         */
        public static List<LinkedHashMap<String, String>> parse(JSONArray jsonArray) throws JSONException {
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

        /**
         * Handle the JSON String as Array
         *
         * @param json
         * @return
         * @throws Exception
         */
        private static List<LinkedHashMap<String, String>> handleAsArray(String json) {
            List<LinkedHashMap<String, String>> flatJson = null;

            try {
                JSONArray jsonArray = new JSONArray(json);
                flatJson = parse(jsonArray);
            } catch (Exception e) {
                // throw new Exception("Json might be malformed");
                //  LOGGER.error("JSON might be malformed, Please verify that your JSON is valid");
            }

            return flatJson;
        }

        /**
         * Flatten the given JSON Object
         *
         * This method will convert the JSON object to a LinkedHashMap of
         * String keys and values
         *
         * @param obj
         * @param flatJson
         * @param prefix
         */
        private static void flatten(JSONObject obj, LinkedHashMap<String, String> flatJson, String prefix) throws JSONException {
            Iterator<?> iterator = obj.keys();
            String _prefix = prefix != "" ? prefix + "." : "";

            while (iterator.hasNext()) {
                String key = iterator.next().toString();

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


                    //  if (value != null && !value.equals("null")) {
                    flatJson.put(_prefix + key, value);
                    // }
                }
            }

        }

        /**
         * Flatten the given JSON Array
         *
         * @param obj
         * @param flatJson
         * @param prefix
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

                    //  if (value != null) {
                    flatJson.put(prefix + "[" + (i + 1) + "]", value);
                    //}
                }
            }
        }




    public static void main(String[] args) {

        List<LinkedHashMap<String, String>> flatJson = Test.parseJson(
                new File("/Users/azuga/downloads/jsontocsv/src/files/Museum.json"), "UTF-8");
        // Using ';' as separator
        Test.writeToFile(Test.getCSV(flatJson, ","),
                "/Users/azuga/downloads/jsontocsv/src/files/test2.csv");
    }
}

