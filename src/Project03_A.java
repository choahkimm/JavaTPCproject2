import kr.inflearn.ExcelVO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Project03_A {
    // Excel 파일 Reading 하기 - Excel API
    // *** MVN repository 사이트에서 Apache POI, Apache Commons Codec, Apache Commons Collection 다운받기
    public static void main(String[] args) {
        String fileName="bookList.xls"; // todo : xlsx 파일이었어서 오류나는듯
        List<ExcelVO> data=new ArrayList<ExcelVO>();

        try(FileInputStream fis=new FileInputStream(fileName)) {
            HSSFWorkbook workbook=new HSSFWorkbook(fis); // 워크북을 만들어서 저장
            HSSFSheet sheet=workbook.getSheetAt(0); // 첫번째 시트를 가져온다
            Iterator<Row> rows=sheet.rowIterator(); // raw의 값을 하나씩 가져오기 위해서
            rows.next(); // 첫번째 줄은 실제 뽑을 데이터가 아니기 때문에, 뒤로 한칸 이동

            String[] imsi=new String[5]; // 임시 배열 (Excel에서 바로 가져온..)

            while(rows.hasNext()) { // hasNext -> row가 있는가? t/f
                HSSFRow row=(HSSFRow) rows.next();
                Iterator<Cell> cells=row.cellIterator();
                int i=0;
                while(cells.hasNext()) { // cell이 있냐?
                    HSSFCell cell=(HSSFCell) cells.next();
                    imsi[i]=cell.toString();
                    i++;
                }
                // 방금 String 배열에 저장된 애들을 VO 구조로 한번에 묶음 ( VO로 묶고 -> LIST로 담고)
                ExcelVO vo=new ExcelVO(imsi[0],imsi[1],imsi[2],imsi[3],imsi[4]);
                data.add(vo);
            }
            showExcelData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showExcelData(List<ExcelVO> data) {
        for(ExcelVO vo : data) { // vo에 담았던 데이터들 뿌리기
            System.out.println(vo);
        }
    }
}
