/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */

package com.test.java;
import com.main.java.Pipes;

import org.junit.Test;


import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;

import java.io.File;

/**
 * Implementing Junit testing for Pipes.java class.
 */
public class PipesTest {
    public static final Logger logger=Logger.getLogger(PipesTest.class); // creating a reference of Logger class.
    Pipes pipe = new Pipes();

    @Test
    public void validateFileNull() {

        assertNotNull("/Users/azuga/documents/training/Day4/data.txt ");

    }

    /*    @Test
        public void validateFileExistence() {

            File inputFile = new File("/Users/azuga/documents/training/Day4/data.txt ");
            assertTrue("InputFile is Avalaible", inputFile.exists());
        }
    @Test
    public void validateSFileReadability() {
        File inputFile = new File("/Users/azuga/documents/training/Day4/data.txt ");

        assertTrue("Avalaible Read the data", inputFile.canRead());
    }*/
    @Test
    public void validateSourceDestinationPathNull() {
        assertNotNull("/Users/azuga/documents/training/Day4/data.txt ");

    }


    /**
     * Testing for catsorthead combination of pipe command.
     */
    @Test
    public void test_cat_sort_head(){
        logger.info(" catsorthead test");
        String expected = "A\n" +
                "B\n" +
                "C\n" +
                "D";
        String actual = pipe.pipes("cat /Users/azuga/documents/training/Day4/data.txt | sort | head -4");
        logger.info("asserting actual output with expected");
        assertEquals(expected,actual);   // comparing expected output with actual one
    }

    /**
     * Testing for catwc combination of pipe command
     */
    @Test
    public void test_cat_wc(){
        logger.info(" catwc test");
        String expected = "7\t7\t15";
        String actual = pipe.pipes("cat /Users/azuga/documents/training/Day4/data.txt | wc");
        logger.info("asserting actual output with expected ");
        assertEquals(expected,actual); //comparing expected output with actual one.
    }

    /**
     * Testing for cattail combination for pipe command..
     */
    @Test
    public void test_cat_tail(){
        logger.info("cattail test");
        String expected = "D\n" +
                "E\n" +
                "F\n" +
                "G";
        String actual = pipe.pipes("cat /Users/azuga/documents/training/Day4/data.txt | tail -4");

        assertEquals(expected,actual);  //asserting expected output with actual one.
    }

    /**
     * Testing for catheadtail combination of pipe command.
     */
    @Test
    public void test_cat_head_tail(){
        logger.info("catheadtail test");
        String expected = "C\n" +
                "D\n" +
                "E";
        String actual = pipe.pipes("cat /Users/azuga/documents/training/Day4/data.txt | head -5 | tail -3");
        logger.info("Invoking assertEqual method for expected and actual output");
        assertEquals(expected,actual);
    }

    /**
     * Testing for negative test case of pipettes.java class.
     */
//    @Test
//    public void Testing_pathnotexist(){
//        String expected=null;
//        String actual=pipe.pipes("cat /Users/azuga/documents/training/Day4/data.txt | sort | head -4");
//        logger.info("Invoking assertEqual method for expected and actual output");
//        assertEquals(expected,actual);
//    }

    /**
     * Testing for cat-sort-head-tail-wc comnbination of pipe command.
     */
    @Test
    public void cat_sort_head_tail_wc(){
        logger.info(" cat-sort-head-tail-wc test");
        String expected="3\t3\t5";
        String actual=pipe.pipes("cat /Users/azuga/documents/training/Day4/data.txt | sort | head -4 | tail -3 | wc");
        logger.info("Invoking assertEqual method for expected and actual output");
        assertEquals(expected,actual);  //comparing expected output with actual one.
    }



}