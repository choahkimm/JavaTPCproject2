import org.json.JSONArray;
import org.json.JSONObject;

public class Project01_B {
    public static void main(String[] args) {
        // JSON - Java (org.json) jar 설치하기
        JSONArray students = new JSONArray();
        JSONObject student = new JSONObject();
        student.put("name","홍길동");
        student.put("phone","010-2222-3333");
        student.put("address","서울");
        System.out.println(student); //{"address":"서울","phone":"010-2222-3333","name":"홍길동"}
        students.put(student); // 홍길동을 students 에 넣고

        student = new JSONObject();
        student.put("name","김길동");
        student.put("phone","010-1222-3333");
        student.put("address","광주");

        students.put(student);// 김길동도 students에 넣으면 [{} {}] 이런 형태가 될 것

        JSONObject object = new JSONObject();
        object.put("students", students);
        System.out.println(object.toString(2));

    }
}
