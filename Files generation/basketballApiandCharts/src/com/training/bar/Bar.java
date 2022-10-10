package  com.training.bar;
/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */

import com.training.Interface;
import org.jfree.chart.ChartFactory;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.logging.FileHandler;
import java.util.logging.Level;



//import org.apache.log4j.Logger;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.SimpleFormatter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Class bar is used to show Bar chart of data recieved from api call
 */

public class Bar extends JFrame implements Interface {
    private static final long serialVersionUID = 1L;
    public final static Logger logger = Logger.getLogger(Bar.class);

    public static List<String> lines = new ArrayList<String>();
    public static List<String> title = new ArrayList<String>();
    public static List<Integer> price = new ArrayList<Integer>();

    /**
     Bar constuctor is used to set the tile,Axis etc of the chart
     * @param title : Title is used to provide the title of bar chart
     */
    public Bar(String title) {

        super(title);
        logger.info("In Constructor");
        // Create dataset
        CategoryDataset dataset = createDataset();
        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Score Home Team", //Chart Title
                "Home_Team_city", // Category axis
                "Home_Team_Score", // Value axis
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    /**
     * Create dataset is used to cretae the dataset for the bar graph
     * @return: Dataset is returned after getting created
     */
    public CategoryDataset createDataset() {
        logger.info("In create Dataset Method");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {

            for (int i = 0; i < 10; i++) {
                dataset.addValue(price.get(i), title.get(i), "");
                logger.debug(price.get(i) + " " + title.get(i));
            }
        }
        catch (Exception e) {
            logger.error("Error " + e.getMessage());
        }
        logger.debug(dataset);
        return dataset;
    }

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        logger.info("In main Method");
        Bar bar =new Bar(" ");
        bar.methodmeaning();


        String[] data;
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/azuga/desktop/apiCallmethod2/src//baskletballdataChartcsv.csv"))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
                logger.debug(currentLine);
               // System.out.println(currentLine);
            }
        } catch (IOException e) {
            logger.error("Error "+e.getMessage());
        }
        if(lines.size()<100 )
            logger.warn("Too less data to generate chart");

            for (int i = 1; i < lines.size(); i++) {
                data = lines.get(i).split(",");

                Integer pr = Integer.parseInt(data[9].replaceAll(" ", ""));
                title.add(data[4]);
                price.add(pr);
            }


        Bar chart = new Bar("Bar chart");
        chart.pack();
        // RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible(true);

    }


    /**
     *Method meanining is interface used to show the role of the class
     */
    public void methodmeaning() {
        System.out.println("Print bar char contents");
    }
}
