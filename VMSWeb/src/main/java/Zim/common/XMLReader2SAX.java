//package Zim.common;
//
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;
//
//import org.xml.sax.Attributes;
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;
//import org.xml.sax.helpers.DefaultHandler;
//
///**
// * Created by Laxton-Joe on 2017/7/11.
// */
//public class XMLReader2SAX extends DefaultHandler {
//    public XMLReader2SAX() {
//        super();
//    }
//
//    java.util.Stack tags = new java.util.Stack();
//
//    public void characters(char ch[], int start, int length)
//            throws SAXException {
//        String tag = (String) tags.peek();
//        if (tag.equals("NO")) {
//            System.out.print("车牌号码：" + new String(ch, start, length));
//        }
//        if (tag.equals("ADDR")) {
//            System.out.println("地址:" + new String(ch, start, length));
//        }
//    }
//
//    public void startElement(String uri, String localName, String qName,
//                             Attributes attrs) {
//        tags.push(qName);
//    }
//}
