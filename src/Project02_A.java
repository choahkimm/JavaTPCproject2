import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Project02_A {
    // Jsoup API를 이용하여 웹페이지를 Crawling 하기
    // Jsoup : Java HTML Parser
    // 크롤링할 URL이 있어야 하고, 어떤 특징이 있는지 사전에 분석을 해야함 - 크롬 개발자 도구 활용


    //1. https://mvnrepository.com/artifact/org.jsoup/jsoup  에서 Jsoup Java HTML Parser API 다운
    public static void main(String[] args) {
        // 파싱할 URL 복사
        Document doc =null;
        try{
            doc = Jsoup.connect("https://sports.news.naver.com/kfootball/index").get();
        }catch (Exception e){
            e.printStackTrace();
        }
        Elements element = doc.select("div.home_news");
        String title = element.select("h2").text().substring(0,4);
        System.out.println("===============");
        System.out.println(title);
        System.out.println("===============");

        for(Element el : element.select("li")){
            System.out.println(el.text());
        }
        System.out.println("===============");
    }
}
