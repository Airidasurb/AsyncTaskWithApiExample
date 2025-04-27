package com.example.asynctaskwithapiexample.utilities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class EcbXmlDataParser {

    public static String getExchangeRates() {
        try {
            URL url = new URL(Constants.ECB_DAILY_RATES_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream stream = connection.getInputStream();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stream);

            NodeList cubeNodes = doc.getElementsByTagName("Cube");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cubeNodes.getLength(); i++) {
                Element cube = (Element) cubeNodes.item(i);
                if (cube.hasAttribute("currency") && cube.hasAttribute("rate")) {
                    String currency = cube.getAttribute("currency");
                    String rate = cube.getAttribute("rate");
                    sb.append(currency).append(": ").append(rate).append("\n");
                }
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Nepavyko nuskaityti duomenų :(";
        }
    }
}
