package uz.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.bookshop.entity.Users;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByLogin(String login);

    List<Users> findAllByCreatedAtAfter(Date date);

    @Modifying
    @Query(value = "SELECT extract(year  from users.created_at), extract(month from users.created_at), COUNT(*)  " +
            "FROM users " +
            "GROUP BY extract(month from users.created_at) , extract(year  from users.created_at);", nativeQuery = true)
    ArrayList<ArrayList<Integer>> findAllByMonth();

    @Query(value =
            "select res2.users_id as user, sum(res2.price) as total, sum(res2.bought) as count, comment from " +
                    "(select res.users_id, sum(res.price) as price, count(res.books_id) as bought, res.var as comment " +
                    "from (select us.price, c.users_id, count(c.users_id) as var, us.books_id " +
                    "      from users_purchases as us " +
                    "               join comments c on us.users_id = c.users_id " +
                    "      group by c.users_id, us.id) as res " +
                    "group by res.users_id, res.var, res.books_id) as res2 " +
                    "group by res2.users_id, comment " +
                    "order by total desc, count, comment limit 10", nativeQuery = true)
    List<Object> active();
}
