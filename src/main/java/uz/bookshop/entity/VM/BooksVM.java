package uz.bookshop.entity.VM;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.bookshop.entity.Author;

import java.sql.Date;

@Data
@AllArgsConstructor
public class BooksVM {

    private long id;

    private String name;

    private Author author;

    private Date createdAt;

    private long viewCount;

    private String image;

    private float price;
}
