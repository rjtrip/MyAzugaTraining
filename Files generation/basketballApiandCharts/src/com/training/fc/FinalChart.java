package  com.training.fc;
/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */
import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;


import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse.PushPromiseHandler;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.opencsv.CSVWriter;
import org.json.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;


/**
 * Final chart constructor is used to set attributes of Pie chart
 */
public class FinalChart extends JFrame{
    public final static Logger logger = Logger.getLogger(FinalChart.class);
    public static List<String> lines = new ArrayList<String>();
    public static List<String> home_team = new ArrayList<String>();
    public static List<Integer> score = new ArrayList<Integer>();
    public FinalChart (String title){
        super(title);
        logger.info("In constructor");
        // Create dataset
        PieDataset dataset = createDataset();
        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Score Home Team ",
                dataset,
                true,
                true,
                false);
        //Format Label
        try {
            PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                    " {0} score : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
            ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);
            // Create Panel
            ChartPanel panel = new ChartPanel(chart);
            setContentPane(panel);
        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
        }
    }

    /**
     * Create Dataset method is used to create the dataset of the Pie chart
     * @return Dataset: Returns the dataset created
     */
    public PieDataset createDataset() {
        logger.info("In create dataset method");
        DefaultPieDataset dataset=new DefaultPieDataset();
        try {
            for(int i=0;i<10;i++) {
                dataset.setValue(home_team.get(i),score.get(i));
            }
        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
        }
        return dataset;
    }
    public static void main(String[] args)  throws IOException, InterruptedException, JSONException{
        logger.info("In main method");
        FinalChart bar =new FinalChart(" ");
        bar.methodmeaning();

        SwingUtilities.invokeLater(() -> {
            FinalChart example = new FinalChart("Pie Chart");

            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });


        String[] data;
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/azuga/desktop/apiCallmethod2/src//baskletballdataChartcsv.csv"))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
            }
        } catch (IOException e) {
            logger.error("Error "+e.getMessage());
        }
        if(lines.size()<100)
            logger.warn("Too less data to generate chart");

            for (int i = 1; i < lines.size(); i++) {
                data = lines.get(i).split(",");
//            System.out.println(lines.get(i));
//            System.out.println(data[1]+","+data[5]);
                // Integer rn = Integer.parseInt(data[8].replaceAll(" ",""));
                try {
                    int pr = Integer.parseInt(data[9].replaceAll(" ", ""));
                    score.add(pr);
                    home_team.add(data[4]);
                } catch (NumberFormatException e) {
                    logger.error("Error " + e.getMessage());
                }

            }



    }

    /**
     * Method meaning is intefcae method used to provide the role of chart
     */
    public void methodmeaning() {
        System.out.println("Print pie chart contents");
    }
}