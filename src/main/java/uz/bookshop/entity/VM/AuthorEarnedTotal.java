package uz.bookshop.entity.VM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEarnedTotal {

     private long id;

     private String name;

     private double earnedTotal;
}
