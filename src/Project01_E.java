import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Project01_E {
    // 지도 이미지 생성 메서드 ->  위도 경도 정보 받아서 이미지 파일 생성하는 것이 목표
    //  static map = 요청된 URL 매개변수를 기반으로 웹 페이지에 표시할 수 있는 이미지로 지도를 반환
    public static void map_service(String point_x, String point_y,  String address) {
        String URL_STATICMAP = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";
        try {
            String pos = URLEncoder.encode(point_x + " " + point_y, "UTF-8");
            String url = URL_STATICMAP;
            url += "center=" + point_x + "," + point_y;
            url += "&level=16&w=700&h=500";
            url += "&markers=type:t|size:mid|pos:"+pos+"|label:"+URLEncoder.encode(address, "UTF-8");
            URL fullUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)fullUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", ""); //id pw 입력하는 방식
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", "");
            int responseCode = conn.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                InputStream is = conn.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // 랜덤한 이름으로 파일 생성
                String tempname = Long.valueOf(new Date().getTime()).toString();
                File f = new File(tempname + ".jpg"); // pathname에 해당되는 파일의 File 객체를 생성한다.
                f.createNewFile(); // 주어진 이름의 파일이 없으면 새로 생성한다.
                OutputStream outputStream = new FileOutputStream(f); // OutStream : 데이터 출력할 때
                while ((read =is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read); // 버퍼의 내용을 출력한다.
                }
                is.close();
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void main(String[] args) {

        String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
        String client_id="vj0posxnc8 ";
        String client_secret="LS9zckeXilq5ZTpwT4A5IYHeHh6zxIa0YzPDFPVU";
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("주소를 입력하세요: ");
            String address=io.readLine(); //입력한 주소를 받음
            String addr= URLEncoder.encode(address, "UTF-8"); //입력한 주소를 인코딩
            String reqUrl = apiURL + addr;

            URL url=new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID",client_id);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY",client_secret);

            BufferedReader br;
            int responseCode = conn.getResponseCode(); // 200
            if(responseCode==200){
                br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            }else {
                br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }

            String line;
            StringBuffer response = new StringBuffer(); // JSON

            // 추가된 부분
            String x=""; String y=""; String z=""; // 경도, 위도, 주소

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
                // 추가된 부분
                x= (String) temp.get("x");
                y= (String) temp.get("y");
                z= (String) temp.get("roadAddress");
            }
            // 추가된 부분
            map_service(x,y,z);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
