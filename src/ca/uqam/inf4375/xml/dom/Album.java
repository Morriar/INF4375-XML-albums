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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Album {

    String title;
    String artist;
    Integer year;
    Double price;
    Boolean inStock;

    public Album() {
    }

    public Album(String title, String artist, Integer year, Double price, Boolean inStock) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.price = price;
        this.inStock = inStock;
    }

    public Album(Element element) {
        this.artist = element.getElementsByTagName("artist").item(0).getTextContent();
        this.title = element.getElementsByTagName("title").item(0).getTextContent();
        this.year = Integer.parseInt(element.getElementsByTagName("year").item(0).getTextContent());
        this.price = Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent());
        this.inStock = Boolean.parseBoolean(element.getElementsByTagName("instock").item(0).getTextContent());
    }

    @Override
    public String toString() {
        return title + " - " + artist + " (" + year + ") " + price + "$";
    }

    public Element toXML(Document document) {
        Element etitle = document.createElement("title");
        etitle.setTextContent(title);
        Element eartist = document.createElement("artist");
        eartist.setTextContent(artist);
        Element einstock = document.createElement("instock");
        einstock.setTextContent(inStock.toString());
        Element eprice = document.createElement("price");
        eprice.setTextContent(price.toString());
        Element eyear = document.createElement("year");
        eyear.setTextContent(year.toString());

        Element element = document.createElement("cd");
        element.appendChild(etitle);
        element.appendChild(eartist);
        element.appendChild(eyear);
        element.appendChild(eprice);
        element.appendChild(einstock);

        return element;
    }
}
