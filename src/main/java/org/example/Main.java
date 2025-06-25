package org.example;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    public static String pluralize(double quantidade, String singular, String plural) {
        int inteiro = (int) Math.round(quantidade);
        return (inteiro == 1 ? singular : plural);

}
    public static void main(String[] args) {

        try {
            String apiKey = "74bd8b92291a9b1a04b3dc31";
            String url_str = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url_str))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonElement root = JsonParser.parseString(response.body());
            JsonObject jsonobj = root.getAsJsonObject();

            String req_result = jsonobj.get("result").getAsString();
            System.out.println("Resultado da requisição: " + req_result);

            JsonObject conversionRates = jsonobj.getAsJsonObject("conversion_rates");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Bem vindo(a) ao Conversor de Moedas =]");

                System.out.println("1- Dólar =>> Peso argentino");
                System.out.println("2- Peso argentino =>> Dólar");
                System.out.println("3- Dólar =>> Real brasileiro");
                System.out.println("4- Real brasileiro =>> Dólar");
                System.out.println("5- Dólar =>> Peso Colombiano");
                System.out.println("6- Peso Colombiano =>> Dólar");
                System.out.println("7- Peso chileno =>> Dólar");
                System.out.println("8 - Dólar  =>> Peso chileno");
                System.out.println("9- boliviano =>> Dólar");
                System.out.println("10- Dólar =>> boliviano ");
                System.out.println("11- Sair");

                int valorDigitado = scanner.nextInt();

                if (valorDigitado == 11) {
                    System.out.println("Você se desconectou. Até a próxima!");
                    break;
                }

                if (valorDigitado < 1 || valorDigitado > 11) {
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
                }

                System.out.println("Digite o valor que deseja converter");
                double conversor = scanner.nextDouble();

                switch (valorDigitado) {
                    case 1:
                        // Dolar para  Peso Argentino (ARS)
                        double valorARS = conversionRates.get("ARS").getAsDouble();
                        double usdParaArs = conversor * valorARS;

                        System.out.println(conversor + " " + pluralize(conversor, "Dólar", "Dólares")
                                + " = " + usdParaArs + " " + pluralize(usdParaArs, "Peso argentino", "Pesos argentinos"));

                      //  System.out.println(conversor + " USD = " + usdParaArs + " ARS (Pesos Argentinos)");

                        break;

                    case 2:
                        // do PESO ARGENTINO PARA cotação DOLAR (USD)
                        JsonObject argentinaParaDolar = jsonobj.getAsJsonObject("conversion_rates");
                        double cotacaoARS = argentinaParaDolar.get("ARS").getAsDouble();
                        double arsParaUsd = conversor / cotacaoARS;

                        System.out.println(conversor + " " + pluralize(conversor, "Peso argentino", "Pesos argentinos")
                                + " = " + arsParaUsd + " " + pluralize(arsParaUsd, "Dólar", "Dólares"));

                      //  System.out.println(conversor + " ARS = " + arsParaUsd + " USD (Dólar)");
                        break;

                    case 3:
                        // DOLAR PARA cotação do Brasil (BRL)
                        JsonObject dolarParaReal = jsonobj.getAsJsonObject("conversion_rates");
                        double valorUSD = dolarParaReal.get("BRL").getAsDouble();
                        double usdParaBrl = conversor * valorUSD;

                        System.out.println(conversor + " " + pluralize(conversor, "Dólar", "Dólares")
                                + " = " + usdParaBrl + " " + pluralize(usdParaBrl, "Real brasileiro", "Reais brasileiros"));


                        // System.out.println(conversor + " USD = " + usdParaBrl + " BRL (Moeda Brasil)");

                        break;

                    case 4:
                        // brasil(brl) para dolar (usd)
                        JsonObject brasilParaDolar = jsonobj.getAsJsonObject("conversion_rates");
                        double cotacaoBRL = brasilParaDolar.get("BRL").getAsDouble();
                        double brlParaUsd = conversor / cotacaoBRL;

                        System.out.println(conversor + " " + pluralize(conversor, "Real brasileiro", "Reais brasileiros")
                                + " = " + brlParaUsd + " " + pluralize(brlParaUsd, "Dólar", "Dólares"));

                        // System.out.println(conversor + " BRL = " + brlParaUsd + " USD (Dólar)");

                        break;

                    case 5:
                        // dolar para peso colambiano (COP)
                        JsonObject dolarParaColombia = jsonobj.getAsJsonObject("conversion_rates");
                        double cotacaoUSD = dolarParaColombia.get("COP").getAsDouble();
                        double usdParaColombia = conversor * cotacaoUSD;

                        System.out.println(conversor + " " + pluralize(conversor, "Dólar", "Dólares")
                                + " = " + usdParaColombia + " " + pluralize(usdParaColombia, "Peso colombiano", "Pesos colombianos"));

                        //System.out.println(conversor + " USD = " + usdParaColombia + " COP (Peso colombiano)");

                        break;

                    case 6:
                        // peso colombiano (cop) para dolar (USD)
                        JsonObject ColombiaParaDolar = jsonobj.getAsJsonObject("conversion_rates");
                        double cotacaoCOP = ColombiaParaDolar.get("COP").getAsDouble();
                        double copParaUsd = conversor / cotacaoCOP;

                        System.out.println(conversor + " " + pluralize(conversor, "Peso colombiano", "Pesos colombianos")
                                + " = " + copParaUsd + " " + pluralize(copParaUsd, "Dólar", "Dólares"));

                        // System.out.println(conversor + " COP = " + copParaUsd + " USD (DÓLAR)");

                        break;

                    case 7:
                        //  Peso chileno =>> Dólar");              ------          clp  para USD
                        JsonObject chileParaDolar = jsonobj.getAsJsonObject("conversion_rates");
                        double cotacaoCLP = chileParaDolar.get("CLP").getAsDouble();
                        double clpParaUsd = conversor / cotacaoCLP;

                        System.out.println(conversor + " " + pluralize(conversor, "Peso chileno", "Pesos chilenos")
                                + " = " + clpParaUsd + " " + pluralize(clpParaUsd, "Dólar", "Dólares"));

                        //System.out.println(conversor + " CLP = " + clpParaUsd + " USD (DÓLAR)");

                        break;
                    // "8 - Dólar  =>> Peso chileno");              ------          usd  para clp

                    case 8:
                        // "8 - Dólar =>> Peso chileno"
                        JsonObject dolarParaChile = jsonobj.getAsJsonObject("conversion_rates");
                        double paraCLP = dolarParaChile.get("CLP").getAsDouble(); // pegar a cotação de CLP
                        double usdParaChile = conversor * paraCLP; // multiplicar para converter USD em CLP
                        System.out.println(conversor + " " + pluralize(conversor, "Dólar", "Dólares")
                                + " = " + usdParaChile + " " + pluralize(usdParaChile, "Peso chileno", "Pesos chilenos"));

                        // System.out.println(conversor + " USD = " + usdParaChile + " CLP (Peso Chileno)");

                        break;

                    case 9:
                        //    9- boliviano =>> Dólar    ------          bob  para USD
                        JsonObject boliviaParaDolar = jsonobj.getAsJsonObject("conversion_rates");
                        double cotacaoBOB = boliviaParaDolar.get("BOB").getAsDouble();
                        double bobParaUsd = conversor / cotacaoBOB;

                        System.out.println(conversor + " " + pluralize(conversor, "Boliviano", "Bolivianos")
                                + " = " + bobParaUsd + " " + pluralize(bobParaUsd, "Dólar", "Dólares"));

                        // System.out.println(conversor + " BOB = " + bobParaUsd + " USD (DÓLAR)");

                        break;
                    case 10:
                        //   10- Dólar =>> boliviano ");              ------         usd  para bob
                        JsonObject dolarParaBolivia = jsonobj.getAsJsonObject("conversion_rates");
                        double paraBOB = dolarParaBolivia.get("BOB").getAsDouble(); // pegar a cotação de CLP
                        double usdParaBolivia = conversor * paraBOB; // multiplicar para converter USD em CLP

                        System.out.println(conversor + " " + pluralize(conversor, "Dólar", "Dólares")
                                + " = " + usdParaBolivia + " " + pluralize(usdParaBolivia, "Boliviano", "Bolivianos"));

                        //System.out.println(conversor + " USD = " + usdParaBolivia + " BOB (Boliviano)");

                        break;
                }
            }
        }
        catch (
                Exception e) {
            e.printStackTrace();
        }
    }
}

