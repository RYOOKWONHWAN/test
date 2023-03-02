package com.mycompany.myapp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.Scanner;

public class zxc {
	private static String getTagValue(String tag, Element eElement) {
		NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nList.item(0);
		if (nValue == null)
			return null;

		return nValue.getNodeValue();
	}

	public static void main(String[] args) {
		try {
			Scanner sc= new Scanner(System.in); // 입력
			String a=sc.nextLine();

			String encode="";
			String category="도서";
			String book=URLEncoder.encode(category,"UTF-8"); // 도서 카테고리 UTF-8로 인코딩 
			encode = URLEncoder.encode("9788983921994","UTF-8"); // 검색하고자하는 키워드 UTF-8로 인코딩
			
			//api 키
			String key= "4bdd93877b64c8f0a612cd9f463d77ba31b0d0739e1854d27ce5773838d31c2f";
			// api url
//			String url="https://www.nl.go.kr/NL/search/openApi/search.do?key="+key+"&apiType=xml&srchTarget=total&kwd="+encode+"&pageSize=200&pageNum=1&sort=&category="+book;
			String url = "https://www.nl.go.kr/seoji/SearchApi.do?cert_key="+key+"&result_style=xml&page_no=1&page_size=10&isbn="+encode;
			
			System.out.println(url);
			//DOM 파서는 XML을 파싱 하여 메모리 상에 XML 구조에 대응하는 객체의 트리를 유지한다.
			//DOM 을 사용해서 XML 파싱
			Document documentInfo= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
			documentInfo.getDocumentElement().normalize();
			//NodeList 를 통해서 e 라는 이름을 가진 요소를 가져온다.
			NodeList aList=documentInfo.getElementsByTagName("e");
			//e 의 개수 확인
			System.out.println(aList.getLength());
			//e라는 이름을 가진 모든 요소의 정보를 가져오기위해 반복문 사용
			for (int temp=0;temp<aList.getLength();temp++) {
				// nNode는 NodeList aList 에 저장된 아이템이고 temp 의 값은 인덱스 값이다.
				// ex>aList.itme(1)
				Node nNode=aList.item(temp); 
				// nNode의 타입이 특정 타입의 노드로 HTML 문서의 tag를 사용해 작성된 node임을 확인하고
				// 맞다면 원하는 정보를 저장하고있는 태그의 이름을 통해서 정보를 가져온다.
				if(nNode.getNodeType()==Node.ELEMENT_NODE) {
					Element eElement=(Element) nNode;
					System.out.println(getTagValue("EA_ADD_CODE", eElement));
					System.out.println(getTagValue("TITLE", eElement));
					System.out.println(getTagValue("AUTHOR", eElement));
					System.out.println(getTagValue("EA_ISBN", eElement));
					System.out.println("----------------------------------------------");
					
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		
		
		
		
	}
}
