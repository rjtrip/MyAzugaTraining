

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.FileOutputStream;
import java.io.*;
//import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;



public class chart{
    public static void main(String[] args) throws Exception {
        var url = "GET https://www.balldontlie.io/api/v1/players" ;

        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());


        PrintWriter pw3 = new PrintWriter(new File("/Users/azuga/desktop/apiCallmethod2/chartsdata.json"));


        //PrintWriter pw3 = new PrintWriter(new File("/Users/azuga/documents/training/day6/museum testy1/files/MuseumTest.json"));
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            var url2 = "https://www.balldontlie.io/api/v1/players/" + i;
            var request3 = HttpRequest.newBuilder().GET().uri(URI.create(url2)).build();
            var client3 = HttpClient.newBuilder().build();
            var response3 = client3.send(request3, HttpResponse.BodyHandlers.ofString());
            // sb.append(response3.body().replaceAll("\\[]", "null"));
            pw3.write(response3.body());
        }
        System.out.println(pw3.toString());
        pw3.close();
    }
}