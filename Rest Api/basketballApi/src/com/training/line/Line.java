package  com.training.line;
/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */

import javax.swing.*;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Line extends JFrame {
    public final static Logger logger = Logger.getLogger(Line.class);
    public static List<String> lines = new ArrayList<>();
    public static List<String> title = new ArrayList<>();
    public static List<Integer> price = new ArrayList<>();

    private static final long serialVersionUID = 1L;

    /**
     * Line chart constructor is used to set attributes of Line chart
     */
    Line(String title) {
        super(title);
        logger.info("In Constructor");
        // Create dataset
        DefaultCategoryDataset dataset = createDataset();
        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Score Home Team", // Chart title
                "Home_Team_city", // Category axis
                "Home_Team_Score",
                dataset
        );

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }
    /**
     * Create Dataset method is used to create the dataset of the Pie chart
     * @return Dataset: Returns the dataset created
     */
    private DefaultCategoryDataset createDataset() {
        logger.info("In create dataset method");

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            for (int i = 0; i < 10; i++) {
                dataset.addValue(price.get(i), " ", title.get(i));
            }
        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
        }
        return dataset;


    }

    public static void main(String[] args) {
        logger.info("In main method");
        String[] data;
        Line bar =new Line(" ");
        bar.methodmeaning();
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/azuga/desktop/apiCallmethod2/src//baskletballdataChartcsv.csv"))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
              //  System.out.println(currentLine);
            }
        } catch (IOException e) {
            logger.error("Error "+e.getMessage());
        }
        if(lines.size()<10)
            logger.warn("Too less data to generate chart");

            for (int i = 1; i < lines.size(); i++) {
                data = lines.get(i).split(",");
                // System.out.println(lines.get(i));
                // System.out.println(data[4] + "," + data[9]);
                // Integer rn = Integer.parseInt(data[8].replaceAll(" ",""));
                try {
                    Integer pr = Integer.parseInt(data[9].replaceAll(" ", ""));
                    title.add(data[4]);
                    price.add(pr);
                } catch (NumberFormatException e) {
                    logger.error("Error " + e.getMessage());
                }
            }

        Line chart = new Line("Line chart");
        chart.pack();
        // RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible(true);
    }

    /**
     * Method meaning is intefcae method used to provide the role of chart
     */
    public void methodmeaning() {
        System.out.println("Print linechar contents");
    }
}