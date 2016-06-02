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
package ca.uqam.inf4375.xml.dom;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        String xmlPath = "resources/catalog.xml";

        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = documentFactory.newDocumentBuilder();
        Document document = parser.parse(xmlPath);

        printAlbums(document);

        // Prepare the new Album we want to write in XML
        Album album = new Album("Californication", "Red Hot Chili Peppers", 1999, 14.99, true);

        // Translate it to a new XML Element
        Element newElement = album.toXML(document);

        // Write it in the catalog
        Element catalog = (Element) document.getElementsByTagName("catalog").item(0);
        catalog.appendChild(newElement);

        printAlbums(document);
    }

    // Print all album elements found in the catalog `document`.
    public static void printAlbums(Document document) {
        NodeList cds = document.getElementsByTagName("cd");
        System.out.println("This catalog contains " + cds.getLength() + " albums");
        for (int i = 0; i < cds.getLength(); i++) {
            Element cd = (Element) cds.item(i);
            Album album = new Album(cd);
            System.out.println(" * " + album);

        }
    }
}
