package patrick.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import patrick.model.Resident;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ResidentDAO {

    DataSource dataSource;

    @Autowired
    public ResidentDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Resident findResidentById(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Resident resident = jdbcTemplate.queryForObject(
                "SELECT * FROM resident WHERE res_id = ?",
                new Object[]{1234},
                (resultSet, i) -> {
                    Resident resident1 = new Resident();
                    resident1.setName(resultSet.getString("name"));
                    resident1.setAddress(resultSet.getString("address"));
                    return resident1;
                }
        );
        return resident;
    }

}
