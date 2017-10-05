package patrick.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import patrick.model.Resident;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResidentDAO {

    DataSource dataSource;
    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ResidentDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate =  new NamedParameterJdbcTemplate(dataSource);
    }

    // Classic ? placeholder examples
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

    // Named parameter example
    public void updateResidentName(String oldName, String newName) {
        String sql = "UPDATE resident SET name = :new_name WHERE name = :old_name";
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("new_name", newName);
        namedParameters.put("old_name", oldName);
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    public List<Map<String, Object>> findAllResidents() {
        return jdbcTemplate.queryForList("SELECT * FROM resident");
    }

}
