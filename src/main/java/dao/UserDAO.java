package dao;

import java.sql.*;
import java.util.*;
import model.User;
import util.DBConnection;

public class UserDAO {

    public boolean register(User user) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO users(name, email, password, role, phone) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getPhone());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public User login(String email, String password) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                u.setPhone(rs.getString("phone"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map<String, String>> getAllFood() {
        List<Map<String, String>> foodList = new ArrayList<>();
        String query = "SELECT f.id, f.food_name, f.quantity, f.location, f.expiry, u.name as donor_name, u.phone, f.donor_id " +
                       "FROM food_listings f JOIN users u ON f.donor_id = u.id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, String> food = new HashMap<>();
                food.put("id", String.valueOf(rs.getInt("id")));
                food.put("food_name", rs.getString("food_name"));
                food.put("quantity", rs.getString("quantity"));
                food.put("location", rs.getString("location"));
                food.put("expiry", String.valueOf(rs.getTimestamp("expiry")));
                food.put("donor_name", rs.getString("donor_name"));
                food.put("phone", rs.getString("phone"));
                food.put("donor_id", String.valueOf(rs.getInt("donor_id")));
                foodList.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodList;
    }

    public boolean deleteFood(int id) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "DELETE FROM food_listings WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateFood(int id, String name, String qty, String loc) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "UPDATE food_listings SET food_name=?, quantity=?, location=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, qty);
            ps.setString(3, loc);
            ps.setInt(4, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
} 