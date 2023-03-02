package com.mycompany.myapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class aass {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String apiurl = "https://dapi.kakao.com/v2/local/search/keyword.json" + "?radius=2000";
		String a = sc.nextLine();
		String query = a + "도서관";
		String key = "KakaoAK dacb438d04968f33fd5fcc9f9a1c6cd2";
		URL url = new URL(apiurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		System.out.println(conn.getContent());
	}
}
