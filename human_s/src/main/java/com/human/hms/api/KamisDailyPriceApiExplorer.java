package com.human.hms.api;

import java.io.BufferedReader;
import java.io.IOException;

/* Java 1.8 샘플 코드 */

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

public class KamisDailyPriceApiExplorer {
    public static <T extends Object> T getApiJsonData( String serviceKey, String kamisId, String srcUrl, 
    												   String categoryCode, String regDate,
    												   Class<T> vo) throws IOException, URISyntaxException {
        StringBuilder urlBuilder = new StringBuilder(srcUrl); /*URL*/
        urlBuilder.append("&" + URLEncoder.encode("p_cert_key","UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("p_cert_id","UTF-8") + "=" + kamisId); /*요청자 아이디*/
        urlBuilder.append("&" + URLEncoder.encode("p_returntype","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*XML/JSON 여부*/
        urlBuilder.append("&" + URLEncoder.encode("p_item_category_code","UTF-8") + "=" + URLEncoder.encode(categoryCode, "UTF-8")); /*부류코드*/
        urlBuilder.append("&" + URLEncoder.encode("p_regday","UTF-8") + "=" + URLEncoder.encode(regDate, "UTF-8")); /*조사일자*/
        
        //공공데이터 요청
        URL url = new URI(urlBuilder.toString()).toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        conn.setRequestMethod("GET"); //클라이언트가 서버에 보내는 데이터 형식 지정
        conn.setRequestProperty("Content-type", "application/json"); //클라이언트가 서버로부터 수신하고 싶은 데이터 형식 지정
        conn.setRequestProperty("Accept", "application/json");

        //302리다이렉션 오류로 인함
        if (conn.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) { // 302 리다이렉션 확인
            String newUrl = conn.getHeaderField("Location"); // 새 위치 확인
            conn = (HttpURLConnection) new URL(newUrl).openConnection(); // 새 위치로 연결
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
        }
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
    	//HTTP 상태코드가 2xx이면 요청이 성공적으로 처리되었음을 나타냄
        //3xx : 클라이언트가 요청을 완료하기 위해 추가 작업이 필요하다는 것을 나타냄
        //4xx : 클라이언트의 요청에 오류가 있음을 나타냄
        //5xx : 서버에서 요청을 처리하는 중 오류가 발생했음을 나타냄
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        
        rd.close();
        conn.disconnect();
        
        //서버에서 받은 데이터를 Jackson API를 이용해서 자바DTO에 세팅하기
        ObjectMapper objectMapper = new ObjectMapper();
        T data = objectMapper.readValue(sb.toString(), vo);
        
        return data;
    }
}