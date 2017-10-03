package patrick.dao;

import org.springframework.beans.factory.annotation.Autowired;
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
        String sql = "SELECT * FROM RESIDENT WHERE RES_ID = ?";
        Connection connection = null;
        Resident resident = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                resident = new Resident(
                        rs.getInt("RES_ID"),
                        rs.getString("NAME"),
                        rs.getString("ADDRESS")
                );
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resident == null) {
            return new Resident(0000, "Not found", "Not found");
        } else {
            return resident;
        }
    }

}
