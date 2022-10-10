
/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class JsonToCsvPraserTest {

    List<LinkedHashMap<String, String>> flatJson;
    String srcPath;
    String destPath;

    @Before
    public void setUp() throws Exception {
         srcPath = "/Users/azuga/documents/training/day6/museum testy1/files/Museum.json";

         destPath = "/Users/azuga/Downloads/museum testy1/museum_csvv.csv";

        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("objectID", "1");
        hashMap.put("isHighlight", "false");
        hashMap.put("accessionNumber", "1979.486.1");
        hashMap.put("isPublicDomain", "false");
        flatJson = new ArrayList<>();
        flatJson.add(hashMap);

    }

    @Test
    public void validateSourceDestinationPath() {

        assertNotNull(JsonToCsvPraser.srcPath);

        File inputFile = new File(JsonToCsvPraser.srcPath);
        assertTrue("InputFile is Avalaible", inputFile.exists());

        assertTrue("Avalaible Read the data", inputFile.canRead());

        assertNotNull(JsonToCsvPraser.destPath);

    }

    @Test
    public void validateThrowFileNotFoundException() throws IOException {
        File file = new File(srcPath);
        file.exists();
        JsonToCsvPraser.parseJson(file);
    }

    @Test
    public void validateParseJsonAndWriteTOFile() {

        List<LinkedHashMap<String, String>> parseJson = JsonToCsvPraser.parseJson(new File(JsonToCsvPraser.srcPath));

        assertNotNull("Parsed Json cannot return null", parseJson);

        assertTrue("Should return size greter than zero", parseJson.size() > 0);

        String flatCSVString = JsonToCsvPraser.getCSV(parseJson, ",");

        assertNotNull(flatCSVString);

        JsonToCsvPraser.writeToFile(flatCSVString, JsonToCsvPraser.destPath);

        File destFile = new File(JsonToCsvPraser.destPath);

        assertTrue("CSV Created Successfully ", destFile.exists());

        assertTrue("CSV Created Successfully ", destFile.canRead());

    }

    @Test
    public void validateGetCSVWithTestData() {
        String csJson = JsonToCsvPraser.getCSV(flatJson, ",");

        assertNotNull("Should not be null ", csJson);

    }

    @Test
    public void validateWriteToFileWithTestData() {

        String flatCSVString = JsonToCsvPraser.getCSV(flatJson, ",");

        assertNotNull(flatCSVString);

        JsonToCsvPraser.writeToFile(flatCSVString, destPath);

    }

    @Test
    public void validateWriteToFileThrowsIOException() throws IOException {
        String flatCSVString = null;
        JsonToCsvPraser.writeToFile(flatCSVString, JsonToCsvPraser.destPath);
    }

}
