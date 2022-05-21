package ru.bstu.it32.nasypalov.lab5;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class XMLParser {
    private String filepath;
    private File xmlFile;
    private Prop p;

    public XMLParser() {
        p = new Prop();
        filepath = p.getData();
        xmlFile = new File(filepath);
    }

    public void parseXML() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                String tag = "";

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes)
                        throws SAXException {
                    if (qName.length() > 0)
                        tag = qName;
                    else
                        tag = "";
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (tag.length() > 0) {
                        System.out.println(tag + ": " + new String(ch, start, length));
                    }

                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    tag = "";
                }

            };

            saxParser.parse(filepath, handler);
        } catch (SAXException | ParserConfigurationException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeXML(String Brand, String Model, String Color, String Plate, String FirstName, String SecondName, String MiddleName) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            Element rootElem = doc.getDocumentElement();
            int idd = doc.getElementsByTagName("Car").getLength() + 1;
            rootElem.appendChild(getCar(doc, idd, Brand, Model, Color, Plate, FirstName, SecondName, MiddleName));
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File("./Data/Data.xml"));
            transformer.transform(source, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Node getCar(Document doc, Integer id, String Brand, String Model, String Color, String Plate, String FirstName, String SecondName, String MiddleName) {
        Element car = doc.createElement("Car");
        Element owner = doc.createElement("Owner");
        car.appendChild(getCarElements(doc, "Id", id.toString()));
        car.appendChild(getCarElements(doc, "Brand", Brand));
        car.appendChild(getCarElements(doc, "Model", Model));
        car.appendChild(getCarElements(doc, "Color", Color));
        car.appendChild(getCarElements(doc, "Plate", Plate));

        car.appendChild(owner);

        owner.appendChild(getCarElements(doc, "FirstName", FirstName));
        owner.appendChild(getCarElements(doc, "SecondName", SecondName));
        owner.appendChild(getCarElements(doc, "MiddleName", MiddleName));

        return car;
    }

    public Node getCarElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    public void editXML(Integer id, String Brand, String Model, String Color, String Plate, String FirstName, String SecondName, String MiddleName) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            updateElement(doc, id, Brand, Model, Color, Plate, FirstName, SecondName, MiddleName);
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File("./Data/Data.xml"));
            transformer.transform(source, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateElement(Document doc, Integer id, String Brand, String Model, String Color, String Plate, String FirstName, String SecondName, String MiddleName) {
        NodeList cars = doc.getElementsByTagName("Car");
        Element car;
        // проходим по каждому элементу Language
        for (int i = 0; i < cars.getLength(); i++) {
            car = (Element) cars.item(i);

            if (car.getElementsByTagName("Id").item(0).getFirstChild().getTextContent().equals(id.toString())) {
                Node node = car.getElementsByTagName("Brand").item(0).getFirstChild();
                node.setNodeValue(Brand);

                node = car.getElementsByTagName("Model").item(0).getFirstChild();
                node.setNodeValue(Model);

                node = car.getElementsByTagName("Color").item(0).getFirstChild();
                node.setNodeValue(Color);

                node = car.getElementsByTagName("Plate").item(0).getFirstChild();
                node.setNodeValue(Plate);

                node = car.getElementsByTagName("FirstName").item(0).getFirstChild();
                node.setNodeValue(FirstName);

                node = car.getElementsByTagName("SecondName").item(0).getFirstChild();
                node.setNodeValue(SecondName);

                node = car.getElementsByTagName("MiddleName").item(0).getFirstChild();
                node.setNodeValue(MiddleName);
                break;
            }

        }
    }

    public void findXML(String... params) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            findElement(doc, params);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void findElement(Document doc, String... params) {
        NodeList cars = doc.getElementsByTagName("Car");
        NodeList nodeList;
        NodeList temp = null;
        Element car;
        boolean check = false;
        for (int i = 0; i < cars.getLength(); i++) {
            car = (Element) cars.item(i);
            nodeList = car.getChildNodes();
            for (int j = 0; j < nodeList.getLength(); j++) {
                for (int k = 0; k < params.length; k++) {
                    if (nodeList.item(j).getTextContent().trim().equals(params[k])) {
                        temp = nodeList;
                        check = true;
                        break;
                    }
                }
            }
            assert temp != null;
            if (check){
                for (int l = 0; l < temp.getLength(); l++){
                    if (!temp.item(l).getNodeName().trim().equals("#text")){
                        System.out.println((temp.item(l).getNodeName().trim()) + ": " + temp.item(l).getTextContent());
                    }
                }
            }
            check = false;
        }

    }

    public void deleteXML(Integer Id){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            deleteElement(doc, Id);
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File("./Data/Data.xml"));
            transformer.transform(source, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteElement(Document doc, Integer id){
        NodeList cars = doc.getElementsByTagName("Car");
        NodeList nodeList;
        NodeList temp = null;
        Element car;
        for (int i = 0; i < cars.getLength(); i++) {
            car = (Element) cars.item(i);
            nodeList = car.getChildNodes();
            for (int j = 0; j < nodeList.getLength(); j++) {
                    if (nodeList.item(j).getTextContent().trim().equals(id.toString())) {
                        nodeList.item(j).getParentNode().getParentNode().removeChild(cars.item(i));
                        break;
                    }

            }

        }
    }

    public void convertToSQL(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            String url = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", p.getHost(), p.getPort(), p.getDb());
            Connection con = DriverManager.getConnection(url, p.getUser(), p.getPassword());
            Statement statement = con.createStatement();

            String[] temp;
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            //doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Car");
            String t;
            for (int i = 0; i < nodeList.getLength(); i++){
                if (nodeList.item(i).getTextContent().replaceAll("\\s+","").trim().matches("[\\w]+")){
                    t = nodeList.item(i).getTextContent().replaceAll("\\s+"," ").trim();
                    temp = t.split(" ");

                    try {
                        statement.execute(String.format("insert into cars values(NULL, '%s', '%s', '%s', '%s', '%s', '%s', '%s')", temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }

            con.close();
            statement.close();
        } catch (SQLException | SAXException| IOException| ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }

}

