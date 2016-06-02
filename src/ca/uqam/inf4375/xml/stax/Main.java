/*
 * Copyright 2016 Alexandre Terrasa <alexandre@moz-code.org>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.uqam.inf4375.xml.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class Main {

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException, IOException {
        String xmlPath = "resources/catalog.xml";

        List<Album> catalog = readCatalog(xmlPath);
        printAlbums(catalog);

        // Prepare the new Album we want to write in XML
        Album album = new Album("Californication", "Red Hot Chili Peppers", 1999, 14.99, true);

        // Add it to the catalog
        catalog.add(album);
        printAlbums(catalog);

        // Write the catalog to XML
        writeCatalog(catalog, "out.xml");
    }

    public static void printAlbums(List<Album> catalog) {
        System.out.println("This catalog contains " + catalog.size() + " albums");
        for (Album album : catalog) {
            System.out.println(" * " + album);

        }
    }

    public static List<Album> readCatalog(String xmlPath) throws XMLStreamException, FileNotFoundException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(xmlPath,
                new FileInputStream(xmlPath));

        List<Album> catalog = new ArrayList<>();
        int eventType;
        Album album = null;
        String tagContent = null;
        while (reader.hasNext()) {
            eventType = reader.next();

            switch (eventType) {
                case XMLStreamConstants.START_ELEMENT:
                    if (reader.getLocalName().equals("cd")) {
                        album = new Album();
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    tagContent = reader.getText().trim();
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    switch (reader.getLocalName()) {
                        case "title":
                            album.title = tagContent;
                            break;
                        case "artist":
                            album.artist = tagContent;
                            break;
                        case "year":
                            album.year = Integer.parseInt(tagContent);
                            break;
                        case "price":
                            album.price = Double.parseDouble(tagContent);
                            break;
                        case "instock":
                            album.inStock = Boolean.parseBoolean(tagContent);
                            break;
                        case "cd":
                            catalog.add(album);
                            break;
                    }
            }
        }
        return catalog;
    }

    public static void writeCatalog(List<Album> catalog, String outXml) throws XMLStreamException, IOException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(
                new FileWriter(outXml));

        writer.writeStartDocument();
        writer.writeStartElement("cds");
        for (Album album : catalog) {
            album.writeXML(writer);
        }
        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
        writer.close();
    }
}
