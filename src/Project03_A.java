import java.io.FileInputStream;

public class Project03_A {
    // Excel 파일 Reading 하기 - Excel API
    // *** MVN repository 사이트에서 Apache POI, Apache Commons Codec, Apache Commons Collection 다운받기
    public static void main(String[] args) {
        String fileName="bookList.xls";
        List<ExcelVO> data=new ArrayList<ExcelVO>();

        try(FileInputStream fis=new FileInputStream(fileName)) {
            HSSFWorkbook workbook=new HSSFWorkbook(fis);
            HSSFSheet sheet=workbook.getSheetAt(0);
            Iterator<Row> rows=sheet.rowIterator(); // raw의 값을 하나씩 가져오기 위해서
            rows.next();
            String[] imsi=new String[5]; // 임시 배열 (Excel에서 바로 가져온..)
            while(rows.hasNext()) { // hasNext -> row가 있는가? t/f
                HSSFRow row=(HSSFRow) rows.next(); // 첫번째 줄은 실제 뽑을 데이터가 아니기 때문에
                Iterator<Cell> cells=row.cellIterator();
                int i=0;
                while(cells.hasNext()) { // cell이 있냐?
                    HSSFCell cell=(HSSFCell) cells.next();
                    imsi[i]=cell.toString();
                    i++;
                }
                // 방금 String 배열에 저장된 애들을 VO 구조로 한번에 묶음
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
