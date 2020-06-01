package load;

import java.util.List;

public class Test {
	public static void main(String[] args) {
        try {
             
            String excelFilePath = "\\dulieucanhan.xlsx";
             
            List<Student> listBooks = new ReadFromExcelFile().readBooksFromExcelFile(excelFilePath);
            StudentDAO studentDAO = new StudentDAO();
            studentDAO.insertListBooks(listBooks);
             
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
