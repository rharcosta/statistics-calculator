package calculadoraestatistica;

import java.util.Arrays;
import java.util.List;

public class Calculos {

    public void Calculos() {

    }

    // Funções auxiliares para os cálculos na tela
    public double variancia(List<String> valores, List<String> frequencias, double media) {
        int totalDados = valores.size();

        double totalFrequencia = 0;
        double varParcial = 0;
        for (int i = 0; i < totalDados; i++) {
            varParcial += (Math.pow((Double.parseDouble(valores.get(i)) - media), 2) * (Double.parseDouble(frequencias.get(i))));
            totalFrequencia += Double.parseDouble(frequencias.get(i));
        }

        double variancia = (varParcial / (totalFrequencia - 1));
        return variancia;
    }

    public double media(List<String> valores, List<String> frequencias) {
        int totalDados = valores.size();

        double totalFrequencia = 0;
        double totalSoma = 0;
        for (int i = 0; i < totalDados; i++) {
            totalSoma += Double.parseDouble(valores.get(i)) * Double.parseDouble(frequencias.get(i));
            totalFrequencia += Double.parseDouble(frequencias.get(i));
        }

        double media = totalSoma / totalFrequencia;
        return media;
    }

    // A princípio, só retornará uma moda (mas pode haver casos com mais de uma...)
    public double moda(List<String> valores, List<String> frequencias) {
        int totalDados = valores.size();

        double maiorFreq = Double.parseDouble(frequencias.get(0));
        int posMaiorFreq = 0;
        for (int i = 0; i < totalDados; i++) {
            if (maiorFreq < Double.parseDouble(frequencias.get(i))) {
                maiorFreq = Double.parseDouble(frequencias.get(i));
                posMaiorFreq = i;
            }
        }

        double moda = Double.parseDouble(valores.get(posMaiorFreq));
        return moda;
    }

    public double desvioPadrao(double variancia) {
        double desvioPadrao = Math.sqrt(variancia);
        return desvioPadrao;
    }

    public double mediana(List<String> valores, List<String> frequencias) {
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
        Arrays.sort(inputList);
        int meio = totalFrequencia / 2;

        double mediana = 0;
        // 2 CASOS
        // -> Par
        if (totalFrequencia % 2 == 0) {

            mediana = (inputList[meio] + inputList[meio - 1]) / 2;
        } // -> Ímpar
        else {
            mediana = inputList[meio];
        }

        return mediana;
    }

    public double coefVar(double desvio, double media) {
        double coefVar = (desvio / media) * 100;
        return coefVar;

    }

    public String classificacao(double coefVar) {
        String resultado = "";
        if (coefVar >= 50) {
            resultado = "Alta Dispersão dos Dados!";
        } else if (coefVar < 50) {
            resultado = "Baixa Dispersão dos Dados!";
        }
        return resultado;
    }

    public double quartile(int num, List<String> valores, List<String> frequencias) {
        double quartile = 0;

        // 1˚: Ordeno a lista
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
        Arrays.sort(inputList);

        int auxPos = 0;
        switch (num) {
            case 1:
                // Q1/4 = arredondar 0.25*(N+1)
                auxPos = (int) Math.round(0.25 * (totalFrequencia));
                quartile = inputList[auxPos - 1];
                break;

            case 2:
                // Caso PAR
                if (totalFrequencia % 2 == 0) {
                    // Q2/4 = média dos itens na posição (N/2) e (N/2)+1
                    quartile = (inputList[(totalFrequencia / 2) - 1] + inputList[totalFrequencia / 2]) / 2;
                } // Caso ÍMPAR
                else {
                    // Q2/4 = o item na posição (N+1)/2
                    quartile = inputList[(totalFrequencia / 2)];

                }
                break;
            case 3:
                // Q3/4 = arredondar(0.75)*(N+1)
                auxPos = (int) Math.round(0.75 * (totalFrequencia));
                quartile = inputList[auxPos - 1];
                break;

            default:
                quartile = Double.MIN_VALUE;
                System.out.println("SHOULD NOT HAPPEN! ERROR QUARTILE VALUE ENTERED!");
                break;
        }

        return quartile;
    }

}
