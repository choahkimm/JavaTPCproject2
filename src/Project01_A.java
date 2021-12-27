import com.google.gson.Gson;
import kr.inflearn.BookDTO;

public class Project01_A {
    public static void main(String[] args) {
        // Object(BookDTO) => JSON(String)
        BookDTO dto = new BookDTO("자바", 21000, "에이콘", 670);
        // Gson 이라는 API 사용
        Gson g = new Gson();
    }
}
