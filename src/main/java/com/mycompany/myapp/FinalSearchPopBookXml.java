package com.mycompany.myapp;
import java.io.BufferedInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Scanner;

public class FinalSearchPopBookXml {
   private static String getTagValue(String tag, Element eElement) {
      NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
      Node nValue = (Node) nList.item(0);
      if (nValue == null)
         return null;

      return nValue.getNodeValue();
   }//getTagValue()

   public static void main(String[] args) {
      try {
         //검색조건 입력
         Scanner sc = new Scanner(System.in);
         System.out.print("입력 (남성0, 여성1) :");
         String a = sc.nextLine();

         String encode="";
         encode = URLEncoder.encode(a,"UTF-8");
         
         //정보나루 데이터 url
         StringBuffer popularBook_url = new StringBuffer();
         
         String key= "e06a89dc1b90dde990758b54fbfefba79260b91c0563b868e9107df6a568a960";
         popularBook_url.append("http://data4library.kr/api/recommandList");
         popularBook_url.append("?authKey="+key); //인증키
         popularBook_url.append("&isbn13="+"9788983922571;9788983921475;9788983921994");
         
         
         
//         popularBook_url.append("&startDt="+"2023-01-01");
//         popularBook_url.append("&endDt="+"2023-02-27");
//         popularBook_url.append("&gender="+encode); //남성0, 여성1
         
         System.out.println(popularBook_url);
         
         //xml파일(url) 파싱
         URL url = new URL(popularBook_url.toString());
         BufferedInputStream xmldata = new BufferedInputStream(url.openStream());
         Document documentInfo= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmldata);

         //xml 데이터 읽기
         NodeList aList = documentInfo.getElementsByTagName("doc");
         System.out.println(aList.getLength());
         for (int i=0; i<aList.getLength(); i++) {
            Node nNode = aList.item(i);
            if(nNode.getNodeType()==Node.ELEMENT_NODE) {
               Element eElement=(Element) nNode;
               
               //HashMap -> json
               HashMap<String, String> map = new HashMap<String, String>();
               map.put("no", getTagValue("no", eElement));
               map.put("bookname", getTagValue("bookname", eElement));
               map.put("authors", getTagValue("authors", eElement));
               map.put("publisher", getTagValue("publisher", eElement));
               map.put("publication_year", getTagValue("publication_year", eElement));
               map.put("isbn", getTagValue("isbn13", eElement));
               map.put("bookImageURL", getTagValue("bookImageURL", eElement));
               
               JSONObject popularBook =  new JSONObject(map);
               System.out.println(popularBook);

               
               //변수에 담기
//               String bookname = getTagValue("bookname", eElement);
//               String authors = getTagValue("authors", eElement);
//               String publisher = getTagValue("publisher", eElement);
//               String publication_year = getTagValue("publication_year", eElement);
//               String isbn = getTagValue("isbn13", eElement);
//               String bookImageURL = getTagValue("bookImageURL", eElement);
//
//               System.out.println(bookname);
            }
         }
      }catch(Exception e) {
         e.printStackTrace();
      }   
      
   }//main
}//class