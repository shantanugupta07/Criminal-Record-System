import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class MainFrame extends JFrame {
    private JTextField nameField, ageField, crimeField, dateField, searchField;
    private JTextArea displayArea;
    private String username;

    public MainFrame(String username) {
        this.username = username;
        setTitle("Criminal Record System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);

        panel.add(new JLabel("Crime:"));
        crimeField = new JTextField();
        panel.add(crimeField);

        panel.add(new JLabel("Date:"));
        dateField = new JTextField();
        panel.add(dateField);

        JButton addButton = new JButton("Add Record");
        addButton.addActionListener(e -> addRecord());
        panel.add(addButton);

        JButton deleteButton = new JButton("Delete Record");
        deleteButton.addActionListener(e -> deleteRecord());
        panel.add(deleteButton);

        panel.add(new JLabel("Search by Name:"));
        searchField = new JTextField();
        panel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchRecord());
        panel.add(searchButton);

        JButton displayButton = new JButton("Display Records");
        displayButton.addActionListener(e -> displayRecords());
        panel.add(displayButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addRecord() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String crime = crimeField.getText();
        String date = dateField.getText();

        try (Connection connection = DatabaseHelper.getConnection()) {
            String query = "INSERT INTO records (name, age, crime, date, user_id) VALUES (?, ?, ?, ?, (SELECT id FROM users WHERE username = ?))";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, crime);
            statement.setString(4, date);
            statement.setString(5, username);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record added", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding record", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteRecord() {
        String name = searchField.getText();
        try (Connection connection = DatabaseHelper.getConnection()) {
            String query = "DELETE FROM records WHERE name = ? AND user_id = (SELECT id FROM users WHERE username = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, username);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Record deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Record not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting record", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchRecord() {
        String name = searchField.getText();
        try (Connection connection = DatabaseHelper.getConnection()) {
            String query = "SELECT * FROM records WHERE name LIKE ? AND user_id = (SELECT id FROM users WHERE username = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, username);
            ResultSet resultSet = statement.executeQuery();
            displayArea.setText("");
            while (resultSet.next()) {
                displayArea.append("ID: " + resultSet.getInt("id") + ", Name: " + resultSet.getString("name") + ", Age: " + resultSet.getInt("age") + ", Crime: " + resultSet.getString("crime") + ", Date: " + resultSet.getString("date") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching records", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayRecords() {
        try (Connection connection = DatabaseHelper.getConnection()) {
            String query = "SELECT * FROM records WHERE user_id = (SELECT id FROM users WHERE username = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            displayArea.setText("");
            while (resultSet.next()) {
                displayArea.append("ID: " + resultSet.getInt("id") + ", Name: " + resultSet.getString("name") + ", Age: " + resultSet.getInt("age") + ", Crime: " + resultSet.getString("crime") + ", Date: " + resultSet.getString("date") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error displaying records", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
