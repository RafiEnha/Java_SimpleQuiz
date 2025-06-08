import java.sql.*;
import java.util.*;

public class DatabaseManager {
    public static List<Question> getQuestions(String type, String topic) {
        List<Question> list = new ArrayList<>();
        String url = "jdbc:postgresql://localhost:5432/simple_quiz";
        String user = "postgres";
        String password = "postgres";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement stmt = conn.prepareStatement("SELECT question, answer FROM questions WHERE topic = ? AND type = ?");
            stmt.setString(1, topic);
            stmt.setString(2, type);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Question(rs.getString("question"), rs.getString("answer")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String[]> getOptions(String type, String topic) {
        List<String[]> optionsList = new ArrayList<>();
        String url = "jdbc:postgresql://localhost:5432/simple_quiz";
        String user = "postgres";
        String password = "postgres";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement stmt = conn.prepareStatement("SELECT option1, option2, option3, option4 FROM questions WHERE topic = ? AND type = ?");
            stmt.setString(1, topic);
            stmt.setString(2, type);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String[] options = {
                    rs.getString("option1"),
                    rs.getString("option2"),
                    rs.getString("option3"),
                    rs.getString("option4")
                };
                optionsList.add(options);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optionsList;
    }
}