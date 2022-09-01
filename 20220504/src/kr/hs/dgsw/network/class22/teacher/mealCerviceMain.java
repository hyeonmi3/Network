package kr.hs.dgsw.network.class22.teacher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URL;

public class mealCerviceMain {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal=Calendar.getInstance();
		
		BufferedReader br=null;
		
		// open neis �޽� ���� ��������
		String newUrls = "https://open.neis.go.kr/hub/mealServiceDietInfo?"		// �⺻ �ּ�
				+ "ATPT_OFCDC_SC_CODE=D10&"										// �뱸������ �ڵ�
				+ "SD_SCHUL_CODE=7240454&"										// �뱸����Ʈ����̽��Ͱ� �ڵ�
				+ "TYPE=json&"													// ��� Ÿ��
				+ "MLSV_YMD=" + sdf.format(cal.getTime());						// ������ �Ĵ� ��¥(����)
		
		URL url = null;
		StringBuffer sb=new StringBuffer();
		
		try {
			url=new URL(newUrls);
			System.out.println(newUrls);
			
			// InputStreamReader: InputStream(byte)�� Reader(char)�� ����
			br=new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			String msg="";
			while((msg=br.readLine())!=null) {
				sb.append(msg);
			}
		} catch(Exception e) {
			
		}
		
        try {
        	
			JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
	        
	        JSONArray jsonArray = (JSONArray) jsonObject.get("mealServiceDietInfo");

	        // �޽� ����
	        JSONObject row = (JSONObject) jsonArray.get(1);
	        JSONArray row_array = (JSONArray) row.get("row");
	        
	        for (int i = 0; i < row_array.size(); i++) {
	            JSONObject out = (JSONObject) row_array.get(i);
	            System.out.println(out.get("MMEAL_SC_NM"));
	            for (String value : ((String) out.get("DDISH_NM")).split("<br/>")) {
	                System.out.println("  - " + value);
	            }

	        }
	        
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
	}
}