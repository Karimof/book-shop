package uz.bookshop.entity.VM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBooksVM {

    private String name;

    private float price;
}
