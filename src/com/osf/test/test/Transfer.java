package com.osf.test.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import com.google.gson.Gson;

// 네이버 기계번역 (Papago SMT) API 예제
public class Transfer {

    public static void main(String[] args) {
        String clientId = "dXW3zTfKEESoMLS3r5T1";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "c3SB4MVU7F";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode("안녕하세요. 오늘 기분은 어떻습니까?", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "source=ko&target=ja&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            //이 부분 몰라도 되지만 모르면 직접 서버를 개발 할 수 없다.
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            Gson gson = new Gson();
            Map<String, Map<String, Map<String,String>>> result = gson.fromJson(response.toString(), Map.class);
//            Map<String,Object> result = gson.fromJson(response.toString(), Map.class);
//            Map<String,Object> mMap = ( Map<String,Object>)result.get("message");
//            Map<String,Object> rMap = ( Map<String,Object>)mMap.get("result");
//            System.out.println(result); //데이터 타입이 StringBuffer라서
            System.out.println(result.get("message").get("result").get("translatedText"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
