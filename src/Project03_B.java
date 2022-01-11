import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Project03_B {
    public static void main(String[] args) {
        // Excel에 이미지 저장하기
        // 기존의 엑셀 파일을 읽어오는 게 아니라, 가상의 엑셀 파일에 이미지를 저장하는 것.

        try {
            // 1. 가상의 엑셀 만들기 (workbook)
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("My Sample Excel"); //sheet명
            InputStream is = new FileInputStream("background.jpg");
            byte[] bytes = IOUtils.toByteArray(is);
            int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            is.close();

            // 2. 사진 위치 설정
            CreationHelper helper = wb.getCreationHelper();
            Drawing drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(1);
            anchor.setRow1(2);
            anchor.setCol2(2);
            anchor.setRow2(3);

            Picture pict = drawing.createPicture(anchor, pictureIdx);
            // anchor 위치만 잡았기 때문에 사진 크기 조정 필요

            Cell cell = sheet.createRow(2).createCell(1);
            int w = 20* 256; // excel 설정상 높이 1/256 크기로 되기 때문에 256을 곱해야 함
            sheet.setColumnWidth(1, w);
            short h = 120 * 20;
            cell.getRow().setHeight(h);

            // 3. Excel 파일에 저장
            FileOutputStream fileOut = new FileOutputStream("myFile.xls");
            wb.write(fileOut);
            fileOut.close();

            System.out.println("이미지 생성 성공");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
