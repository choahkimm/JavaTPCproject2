import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kr.inflearn.BookDTO;

import java.util.ArrayList;
import java.util.List;

public class Project01_A {
    public static void main(String[] args) {
        // O1. bject(BookDTO) => JSON(String)
        BookDTO dto = new BookDTO("자바", 21000, "에이콘", 670);
        // Gson 이라는 API 사용
        Gson g = new Gson();
        String json = g.toJson(dto);
        System.out.println(json); // {"title":"자바","price":21000,"company":"에이콘","page":670}

        // 02. JSON(String) => Object(BookDTO)
        BookDTO dto1 = g.fromJson(json, BookDTO.class);
        System.out.println(dto1); // BookDTO{title='자바', price=21000, company='에이콘', page=670}
        System.out.println(dto1.getTitle());

        // Object(List<BookDTO>) => JSON(String) : [{ },{ }...]
        List<BookDTO> lst = new ArrayList<BookDTO>();
        lst.add(new BookDTO("자바1",20000, "가나출판사1", 302));
        lst.add(new BookDTO("자바2",22000, "가나출판사2", 370));
        lst.add(new BookDTO("자바3",12000, "가나출판사3", 350));
        String litJson = g.toJson(lst);
        System.out.println(litJson);

        // JSON(String) => Object(List<BookDTO>)   ***typeToken 이용!!!!
        List<BookDTO> lit1 = g.fromJson(litJson, new TypeToken<List<BookDTO>>(){}.getType());
        for(BookDTO vo : lit1){
            System.out.println(vo);
        }
    }
}
