import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
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


public class PieChart extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;

    public PieChart(String title,PieDataset dataset) {
        super(title);

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Crypto Global Exchange Rate Chart",
                dataset,
                true,
                true,
                false);

        //Format Label
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "crypto {0} : ({1})", new DecimalFormat("00.0000000000000000"), new DecimalFormat("00%"));
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }


    public PieChart(String appTitle,CategoryDataset dataset) {
        super(appTitle);

        //Create chart
        JFreeChart chart=ChartFactory.createBarChart(
                "Crypto Global Exchange", //Chart Title
                "CryptoCurrency", // Category axis
                "Prices", // Value axis
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false
        );

        ChartPanel panel=new ChartPanel(chart);
        setContentPane(panel);
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        String url = "https://api.coingecko.com/api/v3/global";
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String str = null;
        if (response.statusCode() == 200) {
            System.out.println("connected to server..✦..Fetching the data.✦..✦..");
            str = response.body();

        }
        String[] str1 = str.split("\"market_cap_percentage\"");
        System.out.println(str1[1]);
        String rem = str1[1].replace(":{","").replace("}}","");
        Map<String,Double> map = new HashMap<>();
        String[] str2= rem.split(",");
        Double d ;
        for (int i=0;i< str2.length;i++) {
            if(!(str2[i].charAt(str2[i].length()-1)=='}')) {
                String[] s = str2[i].split(":");
                d = Double.parseDouble(s[1]);
                System.out.println(s[0] + ":::::" + d);
                map.put(s[0], d);
            }
            else {
                System.out.println("wrong input");
                break;
            }
        }
        DefaultPieDataset defaultPieDataset = new DefaultPieDataset();
        map.forEach((s, aDouble) -> defaultPieDataset.setValue(s,aDouble));
        SwingUtilities.invokeLater(() -> {
            PieChart example = new PieChart("Pie Chart", defaultPieDataset);
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        System.out.println(str);

    }

}