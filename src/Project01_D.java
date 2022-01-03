import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Project01_D {
    public static void main(String[] args) {

        String apiURL = "";
        String client_id="";
        String client_secret="";
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("주소를 입력하세요: ");
            String address=io.readLine(); //입력한 주소를 받음
            String addr= URLEncoder.encode(address, "UTF-8"); //입력한 주소를 인코딩
            String reqUrl=apiURL + addr;

            // 이 객체가 유효한 주소인지 아닌지
            URL url=new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID",client_id);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY",client_secret);

            //curl -G "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode" \
            //    --data-urlencode "query={주소}" \
            //    --data-urlencode "coordinate={검색_중심_좌표}" \
            //    -H "X-NCP-APIGW-API-KEY-ID: {애플리케이션 등록 시 발급받은 client id값}" \
            //    -H "X-NCP-APIGW-API-KEY: {애플리케이션 등록 시 발급받은 client secret값}" -v

            // 정상적으로 요청했으니 응답이 정상적인지 체크
            BufferedReader br;
            int responseCode=conn.getResponseCode(); // 200
            if(responseCode==200){
                br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            }else {
                br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }

            // 한 라인씩 읽기
            String line;
            StringBuffer response = new StringBuffer();
            while((line=br.readLine())!=null) {
                response.append(line);
            }
            br.close();

            JSONTokener tokener=new JSONTokener(response.toString());
            JSONObject object=new JSONObject(tokener);
            System.out.println(object.toString());

            JSONArray arr=object.getJSONArray("addresses");
            for(int i=0;i<arr.length();i++){
                JSONObject temp=(JSONObject) arr.get(i);
                System.out.println("address:" +temp.get("roadAddress"));
                System.out.println("jibunAddress:" +temp.get("jibunAddress"));
                System.out.println("경도" +temp.get("x"));
                System.out.println("위도" +temp.get("y"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
