package uz.bookshop.entity.VM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FamousBooksClassVM {

    private long id;

    private String name;

    private long buy;

    private long comment;

    private long view;
}