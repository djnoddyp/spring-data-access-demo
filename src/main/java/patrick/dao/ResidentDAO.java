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
    JdbcTemplate jdbcTemplate;

    @Autowired
    public ResidentDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Resident findResidentById(int id) {
        Resident resident = jdbcTemplate.queryForObject(
                "SELECT * FROM resident WHERE res_id = ?",
                new Object[]{id},
                (resultSet, i) -> {
                    Resident resident1 = new Resident();
                    resident1.setName(resultSet.getString("name"));
                    resident1.setAddress(resultSet.getString("address"));
                    return resident1;
                }
        );
        return resident;
    }

    public void saveResident(Resident resident) {
        jdbcTemplate.update(
                "INSERT INTO resident (res_id, name, address) VALUES (?, ?, ?)",
                resident.getId(),
                resident.getName(),
                resident.getAddress());
    }

}
