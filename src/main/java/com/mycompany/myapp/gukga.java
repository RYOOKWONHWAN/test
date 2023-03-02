package com.mycompany.myapp;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class gukga {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String lat = "";
		String lng = "";
		String a = sc.nextLine();

		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		String key = "4bdd93877b64c8f0a612cd9f463d77ba31b0d0739e1854d27ce5773838d31c2f";
		headers.set("Authorization", key);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		String encode = null;

		try {
			encode = URLEncoder.encode(a, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(encode);

		String rawurl = "https://www.nl.go.kr/NL/search/openApi/search.do?key="+key+"&apiType=json&srchTarget=total&kwd="+encode+"&pageSize=50&pageNum=1&sort=&" ;
		System.out.println(rawurl);
		URI uri = null;
		try {
			uri = new URI(rawurl);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(uri);
		ResponseEntity<String> res = rest.exchange(uri, HttpMethod.GET, entity, String.class);
		JSONParser jsonParser = new JSONParser();
		JSONObject body = null;
		try {
			body = (JSONObject) jsonParser.parse(res.getBody().toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray docu = (JSONArray) body.get("result");
		System.out.println(docu);
		System.out.println(docu.size());
		for (int i = 0; i < docu.size(); i++) {
			JSONObject asd = (JSONObject) docu.get(i);
			System.out.println(asd.get("titleInfo"));
		}

	}
}
