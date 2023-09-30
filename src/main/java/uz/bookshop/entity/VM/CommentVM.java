package uz.bookshop.entity.VM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVM {

    private Long bookId;

    private String content;
}
