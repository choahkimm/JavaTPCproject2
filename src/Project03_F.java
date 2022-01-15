import kr.inflearn.ExcelVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Project03_F {
    public static void main(String[] args) {
        // Naver Book Open API 사용하기
        // 엑셀 데이터--HttpURLConnection---Naver Search API 연동
        // 도서 정보를 입력하여 ISBN, Image 검출하기

        // 키보드로부터 책의 정보를 입력 받아서 OpenAPI 활용..
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("책제목 : ");
            String title = br.readLine();
            System.out.print("저자 : ");
            String author = br.readLine();
            System.out.print("출판사 : ");
            String company = br.readLine();

            ExcelVO vo = new ExcelVO(title, author, company); // 3개의 정보를 vo로 묶음
            getIsbnImage(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getIsbnImage(ExcelVO vo){
        try{
            String openApi="https://openapi.naver.com/v1/search/book_adv.xml?d_titl="
                    + URLEncoder.encode(vo.getTitle(), "UTF-8")
                    + "&d_auth="+URLEncoder.encode(vo.getAuthor(), "UTF-8")
                    + "&d_publ="+URLEncoder.encode(vo.getCompany(), "UTF-8");
            URL url = new URL(openApi);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", "1d4qjxioZUZ_NMhRq6pd");
            con.setRequestProperty("X-Naver-Client-Secret", "moHLrQFa7S");
            int responseCode= con.getResponseCode();

            BufferedReader br1; // 서버에서 날라오는 XML 을 읽어와야 하니까
            if(responseCode==200){ // 성공적 응답
                br1 = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            }else {
                br1 = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine=br1.readLine())!=null){ // null이 아니면 끝까지 읽으면 됨
                response.append(inputLine);
            }
            br1.close();
            //System.out.println(response.toString());
            // isbn, image
            Document doc=Jsoup.parse(response.toString());
            //System.out.println(doc.toString());
            Element total=doc.select("total").first();
            //System.out.println(total.text());
            if(!(total.text().equals("0"))) {
                Element isbn=doc.select("isbn").first();
                String isbnStr=isbn.text();
                //System.out.println(isbnStr);
                String isbn_find=isbnStr.split(" ")[1];
                vo.setIsbn(isbn_find);
                //------------------------------------- 여기까지 isbn 넣는것
                //Element img=doc.select("img").first();
                //System.out.println(img.toString());
                String imgDoc=doc.toString();
                String imgTag=imgDoc.substring(imgDoc.indexOf("<img>")+5);
                //System.out.println(imgTag); // http://~~~~~~~~~~~~~~
                String imgURL=imgTag.substring(0, imgTag.indexOf("?"));
                System.out.println(imgURL);
                String fileName=imgURL.substring(imgURL.lastIndexOf("/")+1);
                System.out.println(fileName);
                vo.setImgurl(fileName);

                System.out.println(vo);
            }else {
                System.out.println("검색데이터가 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
