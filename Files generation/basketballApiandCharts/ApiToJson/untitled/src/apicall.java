
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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.opencsv.CSVWriter;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;





class apicall{


    public static void main(String[] args) throws IOException, InterruptedException, JSONException {

        var url = "https://www.balldontlie.io/api/v1/players" ;

        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());


        PrintWriter pw3 = new PrintWriter(new File("/Users/azuga/desktop/apiCallmethod2/src/baskletballdata.json"));


        //PrintWriter pw3 = new PrintWriter(new File("/Users/azuga/documents/training/day6/museum testy1/files/MuseumTest.json"));
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            var url2 = "https://www.balldontlie.io/api/v1/players/" + i;
            var request3 = HttpRequest.newBuilder().GET().uri(URI.create(url2)).build();
            var client3 = HttpClient.newBuilder().build();
            var response3  = client3.send(request3, HttpResponse.BodyHandlers.ofString());
            // sb.append(response3.body().replaceAll("\\[]", "null"));
            pw3.write(response3.body());
//            JSONArray heights = new JSONArray(response3.body());
//            for(int j=0;j<heights.length();j++) {
//                JSONObject height = new JSONObject(j);
//                int h = height.getInt("height_feet");
//                System.out.println(h);
//            }
        }
        //System.out.println(pw3.toString());

        pw3.close();


    }


    }