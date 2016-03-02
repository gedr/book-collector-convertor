/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.gedr.ebooks;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.gedr.ebooks.parsers.CatalogXmlParser;
import javax.xml.parsers.*;
import org.xml.sax.*;


/**
 *
 * @author egafarov
 */
public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) {
        int quarter = 2;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2016);
        c.set(Calendar.MONTH, Calendar.JANUARY + (quarter - 1) * 3);
        System.out.println(c.getTime());
        System.exit(0);

        
        try (InputStream is = new FileInputStream("/tmp/english.xml")) {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(new CatalogXmlParser());
            xmlReader.parse(new InputSource(is));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

    }
    
}
