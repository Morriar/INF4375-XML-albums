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

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

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

    @Override
    public String toString() {
        return title + " - " + artist + " (" + year + ") " + price + "$";
    }

    public void writeXML(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeStartElement("title");
        writer.writeCharacters(title);
        writer.writeEndElement();
        writer.writeStartElement("artist");
        writer.writeCharacters(artist);
        writer.writeEndElement();
        writer.writeStartElement("year");
        writer.writeCharacters(year.toString());
        writer.writeEndElement();
        writer.writeStartElement("price");
        writer.writeCharacters(price.toString());
        writer.writeEndElement();
        writer.writeStartElement("instock");
        writer.writeCharacters(inStock.toString());
        writer.writeEndElement();
    }
}
