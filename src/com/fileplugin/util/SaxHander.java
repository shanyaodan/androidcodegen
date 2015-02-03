package com.fileplugin.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxHander extends DefaultHandler{  
    private List<IdNamePair> map = new ArrayList<IdNamePair>();  
  
      
    @Override  
    public void startDocument() throws SAXException {  
        super.startDocument();  
        map.clear();  
    }  
  
    
    
    @Override  
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {  
        super.startElement(uri, localName, qName, attributes);  
//      System.out.println("-------------------------------------");  
  
        String tempid = attributes.getValue("android:id");  
        String id = null;  
        if (tempid != null) {  
            String[] ss = tempid.split("/");  
            if (ss != null && ss.length == 2) {  
  
                id = ss[1];  
            }  
        }  
        if (id != null) {  
            map.add(new IdNamePair(id, qName));  
  
        }  
//      System.out.println(id);  
//      System.out.println(qName);  
  
    }  
  
    public List<IdNamePair> getRes() {  
        return map;  
  
    }  
}  