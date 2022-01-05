package practice;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class URLClass {
    public static void main(String[] args) throws IOException {
        // URL 클래스
        String str ="http://www.naver.com";
        URL url = new URL(str); // URL 객체 생성

        System.out.println("프로토콜: " + url.getProtocol());
        System.out.println("호스트: " + url.getHost());
        System.out.println("포트: " + url.getPort()); // 실제 포트 번호는 80. -1는 제대로 접속함을 알려줌
        System.out.println("파일: " + url.getFile());

        InputStream ins = url.openStream(); // openStream 메서드를 통해 해당 url을 불러오고, InputStream에 넣는다.
        BufferedReader br = null;

        br = new BufferedReader(new InputStreamReader(ins)); // ins의 inputstream 내용을 buffer에 저장

        while((str=br.readLine())!=null){
            System.out.println(str);
        }
        br.close();
        ins.close();
        System.out.println("-------------------------");

        // [1] openConnection() 메서드
        // 처음 선언한 URL 객체를 다른 클래스의 객체로 바꾸어서 더 다양한 메소드 사용하기 => openConnection()

        // 웹문서의 이미지파일 주소를 복사해서 url 객체로 반환하려함.
        // 그런데 url 클래스로는 우리가 원하는 기능을 전부 할 수 없으므로 이를 URLConnection 객체로 되돌릴 필요가 있다.
        URL url2 = new URL(" https://i.imgur.com/CEftia8.jpg");
        URLConnection conn = url2.openConnection();// 그래서 openConnection 메서드를 호출하고 이를 URLConnection 타입의 변수 conn에 저장

        System.out.println("to String(): "+ conn.toString());
        System.out.println("문서 사이즈: " + conn.getContentLength());
        System.out.println("문서 타입: " + conn.getContentType());
        System.out.println("접속 날짜: " + conn.getDate());

        System.out.println("-------------------------");

        //[2]openStream() 메서드
        // url 주소를 InputStream에 넣어주는 것
        String str2 = "http://bluewings.me/files/attach/images/8474/ccd395f97ca571a800de91e46f5970f0.png";
        URL url3 = new URL(str2);
        InputStream in =url3.openStream();
        BufferedInputStream bi = new BufferedInputStream(in);

        FileOutputStream fo = new FileOutputStream("img.jpg");

        byte buff[]=new byte[1024];
        int imgData=0;
        int cnt=0;
        URLConnection conn2 = url3.openConnection();
        int size= conn2.getContentLength();
        System.out.println("이미지 크기: "+size);

        while( (imgData=bi.read(buff))!=1){
            fo.write(buff,0,imgData); //image의 바이트 수가 imgData에 들어가 있다?
            fo.flush();
            cnt += imgData;
            System.out.println((cnt*100)/size +"%");
        }
        in.close();
        bi.close();
        fo.close();
    }
}
