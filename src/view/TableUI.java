package view;

import model.dto.UserResponseDto;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class TableUI {
    private static final CellStyle CENTER = new CellStyle(CellStyle.HorizontalAlign.center);
    public static void getTableUI(List<UserResponseDto> userResponseDtos){
        Table table = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER
        , ShownBorders.ALL);
        String columNames[] = {"UUID","USERNAME","EMAIL","IS_DELETED"};
        for(String col: columNames){
            table.addCell(col, CENTER);
        }
//        table.setColumnWidth(0,20,25);
        //
        for(UserResponseDto user: userResponseDtos){
            table.addCell(user.uuid(),CENTER);
            table.addCell(user.userName(), CENTER);
            table.addCell(user.email(),CENTER);
            table.addCell(user.isDeleted().toString(),CENTER);
        }
        System.out.println(table.render());
    }
}
