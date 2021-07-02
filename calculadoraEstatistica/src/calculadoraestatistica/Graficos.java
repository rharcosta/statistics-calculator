package calculadoraestatistica;

import java.io.*;
import java.util.List;
import org.jfree.chart.*;
import org.jfree.data.statistics.*;
import org.jfree.chart.plot.PlotOrientation;

public class Graficos {

    private int imgWidth;
    private int imgHeight;

    public Graficos(int width, int height) {
        this.imgWidth = width;
        this.imgHeight = height;
    }

    public void createHistogram(List<String> valores, List<String> frequencias) {
        
            int totalDados = valores.size();

            int totalFrequencia = 0;
            for (int i = 0; i < totalDados; i++) {
                totalFrequencia += Double.parseDouble(frequencias.get(i));
            }
            double[] inputList = new double[totalFrequencia];

            int aux = 0;
            int posRef = 0;
            // Gero lista de entradas (PODERIA SER GERAL)
            for (int i = 0; i < totalDados; i++) {
                aux = Integer.parseInt(frequencias.get(i));
                // Repito o número, na ordem em que aparece, de acordo com sua frequência
                for (int j = 0; j < aux; j++) {
                    inputList[posRef] = Double.parseDouble(valores.get(i));
                    posRef++;
                }
            }

            HistogramDataset dataset = new HistogramDataset();
            dataset.setType(HistogramType.RELATIVE_FREQUENCY);
            dataset.addSeries("Histograma", inputList, totalFrequencia);
            String plotTitle = "Histograma";
            String xaxis = "Valores";
            String yaxis = "Frequências";
            PlotOrientation orientation = PlotOrientation.VERTICAL;
            boolean show = false;
            boolean toolTips = false;
            boolean urls = false;
            JFreeChart chart = ChartFactory.createHistogram(plotTitle, xaxis, yaxis,
                    dataset, orientation, show, toolTips, urls);

            try {
                ChartUtilities.saveChartAsPNG(new File("histograma.PNG"), chart, this.imgWidth, this.imgHeight);
            } catch (IOException e) {
            }
         
    }

}
