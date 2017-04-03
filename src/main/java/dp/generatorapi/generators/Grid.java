package dp.generatorapi.generators;

import com.opencsv.CSVWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Grid {

    public static List<String> rowFromMap(final List<String> keys, final Map<String, String> data) {
        final List<String> row = new ArrayList<>();
        for (String key : keys) {
            if (data.containsKey(key)) {
                row.add(data.get(key));
            } else {
                row.add("");
            }
        }
        return row;
    }

    public static List<String> rowFromPair(final String cell1, final String cell2) {
        final List<String> row = new ArrayList<>();
        row.add(cell1);
        row.add(cell2);
        return row;
    }

    public static String gridToCsv(List<List<String>> grid) {
        try (final CharArrayWriter os = new CharArrayWriter();
             final CSVWriter writer = new CSVWriter(os)) {
            grid.forEach((List<String> row) -> writer.writeNext(row.toArray(new String[row.size()])));
            writer.flush();
            return os.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String gridToXLS(List<List<String>> grid) {
        final Workbook wb = new HSSFWorkbook();
        final Sheet sheet = wb.createSheet("data");
        for (int row = 0; row < grid.size(); row++) {
            final Row sheetRow = sheet.createRow(row);
            final List<String> column = grid.get(row);
            for (int col = 0; col < column.size(); col++) {
                sheetRow.createCell(col).setCellValue(column.get(col));
            }
        }
        try (final ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            wb.write(os);
            return os.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
