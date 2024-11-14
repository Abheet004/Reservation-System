// import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;


 class ReservationSystemUI {

    public void start(){
        JFrame fr = new JFrame("Reservation System");
        fr.setSize(400, 400);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLayout(null);
        fr.setLocationRelativeTo(null); 

        JButton b1 = new JButton("SignUp");
        b1.setBounds(90, 10, 200, 40);
        JButton b2 = new JButton("LogIn");
        b2.setBounds(90, 60, 200, 40);

        fr.add(b1);
        fr.add(b2);
        fr.setVisible(true);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignUp(fr);
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLogIn(fr);
            }
        });
    }

    public void openSignUp (JFrame fr){
        fr.setVisible(true);
        JFrame frame_sign = new JFrame("Reservation System");
        frame_sign.setSize(400, 400);
        frame_sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_sign.setLayout(null);
        frame_sign.setLocationRelativeTo(null);
        JLabel labelUsername, labelPhoneNumber, labelPassword, labelAge;
        JTextField textUsername, textPhoneNumber, textAge;
        JPasswordField passwordField;
        JButton submitButton, backButton;

        labelUsername = new JLabel("Username:");
        labelUsername.setBounds(50, 30, 100, 30);
        frame_sign.add(labelUsername);
        
        textUsername = new JTextField();
        textUsername.setBounds(150, 30, 150, 30);
        frame_sign.add(textUsername);
        
        // Phone Number label and text field
        labelPhoneNumber = new JLabel("Phone Number:");
        labelPhoneNumber.setBounds(50, 70, 100, 30);
        frame_sign.add(labelPhoneNumber);
        
        textPhoneNumber = new JTextField();
        textPhoneNumber.setBounds(150, 70, 150, 30);
        frame_sign.add(textPhoneNumber);
        
        // Password label and password field
        labelPassword = new JLabel("Password:");
        labelPassword.setBounds(50, 110, 100, 30);
        frame_sign.add(labelPassword);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 110, 150, 30);
        frame_sign.add(passwordField);
        
        // Age label and text field
        labelAge = new JLabel("Age:");
        labelAge.setBounds(50, 150, 100, 30);
        frame_sign.add(labelAge);
        
        textAge = new JTextField();
        textAge.setBounds(150, 150, 150, 30);
        frame_sign.add(textAge);
        
        // Submit button
        submitButton = new JButton("Submit");
        submitButton.setBounds(150, 200, 100, 30);
        frame_sign.add(submitButton);
        backButton = new JButton("Back");
        backButton.setBounds(150, 300, 100, 30);
        frame_sign.add(backButton);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textUsername.getText();
                String phoneNumber = textPhoneNumber.getText();
                String password = new String(passwordField.getPassword());
                String sage=textAge.getText();
                textAge.setText("");
                textUsername.setText("");
                textPhoneNumber.setText("");
                passwordField.setText("");

                if (username.isEmpty() || phoneNumber.isEmpty() || password.isEmpty() || sage.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields are required.");
                    return;
                }
                int age = Integer.parseInt(sage);
                // Call the method to save the data in the database
                if(saveToDatabase(username, phoneNumber, password, age)){
                    JOptionPane.showMessageDialog(null,"User Registered Successfully!!");
                    frame_sign.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "User already exists please choose another");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame_sign.setVisible(false);
                fr.setVisible(true);
            }
        });
        
        
        // Set visibility
        frame_sign.setVisible(true);
    }

    private static boolean saveToDatabase(String username, String phoneNumber, String password, int age) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Load JDBC driver (Change for your database)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the connection (Change the URL, username, and password)
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abheet", "root", "root");
            
            // SQL query to insert user data
            String sql = "INSERT INTO users (username, phone_number, password, age) VALUES (?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, phoneNumber);
            pstmt.setString(3, password);
            pstmt.setInt(4, age);
            
            // Execute the query
            pstmt.executeUpdate();
            return true;
            
           
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            return false;
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    

    public void openLogIn(JFrame fr) {
    fr.setVisible(false); // Hide the current frame
    
    JFrame frame_login = new JFrame("Login - Reservation System");
    frame_login.setSize(400, 400);
    frame_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame_login.setLayout(null);
    frame_login.setLocationRelativeTo(null);
    
    JLabel labelUsername = new JLabel("Username:");
    labelUsername.setBounds(50, 50, 100, 30);
    frame_login.add(labelUsername);
    
    JTextField textUsername = new JTextField();
    textUsername.setBounds(150, 50, 150, 30);
    frame_login.add(textUsername);
    
    JLabel labelPassword = new JLabel("Password:");
    labelPassword.setBounds(50, 100, 100, 30);
    frame_login.add(labelPassword);
    
    JPasswordField passwordField = new JPasswordField();
    passwordField.setBounds(150, 100, 150, 30);
    frame_login.add(passwordField);
    
    JButton loginButton = new JButton("Login");
    loginButton.setBounds(150, 150, 100, 30);
    frame_login.add(loginButton);
    JButton backButton = new JButton("Back");
    backButton.setBounds(150, 200, 100, 30);
    frame_login.add(backButton);
    
    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame_login.setVisible(false);
            fr.setVisible(true);
        }
    });
    loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = textUsername.getText();
            String password = new String(passwordField.getPassword());
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required.");
                return;
            }
            int userId=verifyLogin(username, password);
            if (userId != 0) {
                JOptionPane.showMessageDialog(frame_login, "Login successful!  USER ID:"+userId);
               
                frame_login.setVisible(false);
                start_main(fr);
            } else {
                JOptionPane.showMessageDialog(frame_login, "Invalid username or password. Please try again.");
            }
        }
    });

    frame_login.setVisible(true);
}

private int verifyLogin(String username, String password) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int userID=0;

    try {
        // Load JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Establish the connection
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abheet", "root", "root");
        
        // SQL query to check if the username and password exist
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        
        // Execute query
        rs = pstmt.executeQuery();

        if (rs.next()) {
            userID = rs.getInt("user_id");
        }
        return userID;//returns true if data matched else return false 
        
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        return 0;
        
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}



    public void start_main(JFrame fr) {
        fr.setVisible(false);
        JFrame frame = new JFrame("Reservation System");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null); 

        JButton b1 = new JButton("Make a Reservation");
        b1.setBounds(90, 10, 200, 40);
        JButton b2 = new JButton("View all Reservation");
        b2.setBounds(90, 60, 200, 40);
        JButton b3 = new JButton("Cancel a Reservation");
        b3.setBounds(90, 110, 200, 40);
        JButton b4 = new JButton("View a Reservation");
        b4.setBounds(90, 160, 200, 40);

        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.setVisible(true);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openReservationPage(frame);
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openViewReservation(frame);
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCancelReservation(frame);
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openViewaReservation(frame);
            }
        });
    }
    void openReservationPage(JFrame frame){
        frame.setVisible(false);

        JFrame frame_1 = new JFrame("Make a Resevation");
        frame_1.setSize(400, 400);
        frame_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_1.setLayout(null);
        frame_1.setLocationRelativeTo(null); 

        JLabel UIDLabel = new JLabel("User ID:");
        UIDLabel.setBounds(50, 20, 100, 30);
        JTextField UIDField = new JTextField();
        UIDField.setBounds(150, 20, 200, 30);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 50, 100, 30);
        JTextField nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 30);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(50, 100, 100, 30);
        JTextField dateField = new JTextField();
        dateField.setBounds(150, 100, 200, 30);

        JLabel peopleLabel = new JLabel("Number of People:");
        peopleLabel.setBounds(50, 150, 120, 30);
        JTextField peopleField = new JTextField();
        peopleField.setBounds(180, 150, 170, 30);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBounds(50, 250, 120, 40);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(200, 250, 120, 40);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame_1.setVisible(false); 
                frame.setVisible(true);         
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String date = dateField.getText();
                int numPeople = Integer.parseInt(peopleField.getText());
                int loggedinID = Integer.parseInt(UIDField.getText());

                if (name.isEmpty() || date.isEmpty() || numPeople==0) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    int regID=makeReservation(name, date, numPeople, loggedinID);
                    if (regID != 0) {
                        JOptionPane.showMessageDialog(null, "Reservation Details:\n" +
                                "Reservation made with ID:"+regID+"\n"+
                                "Name: " + name + "\n" +
                                "Date: " + date + "\n" +
                                "Number of People: " + numPeople, "Reservation Successful", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                nameField.setText("");
                peopleField.setText("");
                dateField.setText("");
            }
        });

        frame_1.add(UIDLabel);
        frame_1.add(nameLabel);
        frame_1.add(nameField);
        frame_1.add(UIDField);
        frame_1.add(dateLabel);
        frame_1.add(dateField);
        frame_1.add(peopleLabel);
        frame_1.add(peopleField);
        frame_1.add(goBackButton);
        frame_1.add(submitButton);
       
        frame_1.setVisible(true);
    }
    private int makeReservation(String name, String date, int numpeople, int loggedinID){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int reg_id = 0;

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abheet", "root", "root");

            // Insert reservation data
            String sql = "INSERT INTO reservations (user_id, name, date, number_of_guests) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, loggedinID);
            pstmt.setString(2, name);
            pstmt.setDate(3, Date.valueOf(date));
            pstmt.setInt(4, numpeople);
            pstmt.executeUpdate();

            String selectSql = "SELECT LAST_INSERT_ID()";
            pstmt = conn.prepareStatement(selectSql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                reg_id = rs.getInt(1);  // Get the last inserted reservation_id
            }
        
            return reg_id;

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            return 0;

        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    void openViewReservation(JFrame frame){
        frame.setVisible(false);
        JFrame reservationListFrame = new JFrame("Reservation List");
        reservationListFrame.setSize(600, 400);
        reservationListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reservationListFrame.setLayout(null);
        reservationListFrame.setLocationRelativeTo(null); 

        JLabel UIDLabel = new JLabel("User ID:");
        UIDLabel.setBounds(50, 20, 50, 30);
        JTextField UIDField = new JTextField();
        UIDField.setBounds(150, 20, 200, 30);

        JButton viewb = new JButton("View");
        viewb.setBounds(400, 20, 100, 30);

        JTextArea reservationTextArea = new JTextArea();
        reservationTextArea.setBounds(50, 50, 500, 200);

        viewb.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
            int loggedinID;

            try {
                loggedinID = Integer.parseInt(UIDField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid User ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // Load JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Connect to the database
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abheet", "root", "root");

                // Query to fetch reservations for the logged-in user
                String sql = "SELECT reservation_id, name, date, number_of_guests FROM reservations WHERE user_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, loggedinID);

                rs = pstmt.executeQuery();

                while (rs.next()) {
                    int reservationId = rs.getInt("reservation_id");
                    String name = rs.getString("name");
                    Date date = rs.getDate("date");
                    int numberOfGuests = rs.getInt("number_of_guests");

                    sb.append("RESERVATION ID: ").append(reservationId)
                      .append(", NAME: ").append(name)
                      .append(", DATE: ").append(date)
                      .append(", NUMBER OF GUESTS: ").append(numberOfGuests)
                      .append("\n");
                }

                reservationTextArea.setText(sb.toString());
                reservationTextArea.setEditable(false);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
        });
            

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBounds(240, 300, 120, 40);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservationListFrame.setVisible(false); 
                frame.setVisible(true);             
            }
        });
        reservationListFrame.add(goBackButton);
        reservationListFrame.add(reservationTextArea);
        reservationListFrame.setVisible(true);
        reservationListFrame.add(viewb);
        reservationListFrame.add(UIDField);
        reservationListFrame.add(UIDLabel);
    }

    void openCancelReservation(JFrame frame){
        frame.setVisible(false);

        JFrame cancelFrame = new JFrame("Cancel Reservation");
        cancelFrame.setSize(400, 300);
        cancelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cancelFrame.setLayout(null);

        JLabel UIDLabel = new JLabel("User ID:");
        UIDLabel.setBounds(50, 20, 100, 30);
        JTextField UIDField = new JTextField();
        UIDField.setBounds(150, 20, 200, 30);

        JLabel idLabel = new JLabel("Enter Reservation ID:");
        idLabel.setBounds(50, 50, 150, 30);
        JTextField idField = new JTextField();
        idField.setBounds(200, 50, 100, 30);

        JButton cancelButton = new JButton("Cancel Reservation");
        cancelButton.setBounds(120, 150, 150, 40);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBounds(120, 200, 150, 40);

         cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int loggedinID=Integer.parseInt(UIDField.getText());
                int id=Integer.parseInt(idField.getText());
                if(cancelReservation(id,loggedinID)){
                    JOptionPane.showMessageDialog(null,"Reservation Canceled","RESERVATION CANCELED", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"Reservation not found", "RESERVATION NOT FOUND", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelFrame.setVisible(false); 
                frame.setVisible(true);    
            }
        });

        cancelFrame.add(cancelButton);
        cancelFrame.add(idLabel);
        cancelFrame.add(idField);
        cancelFrame.add(UIDField);
        cancelFrame.add(UIDLabel);
        cancelFrame.add(goBackButton);
        cancelFrame.setVisible(true);
        cancelFrame.setLocationRelativeTo(null); 

    }
    private boolean cancelReservation(int id,int loggedinID){
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abheet", "root", "root");

            // SQL query to delete the reservation
            String sql = "DELETE FROM reservations WHERE reservation_id = ? AND user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, loggedinID);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Return true if deletion was successful

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            return false;

        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    void openViewaReservation(JFrame frame){
        frame.setVisible(false);

        JFrame cancelFrame = new JFrame("View a Reservation");
        cancelFrame.setSize(400, 300);
        cancelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cancelFrame.setLayout(null);

        JLabel UIDLabel = new JLabel("User ID:");
        UIDLabel.setBounds(50, 20, 100, 30);
        JTextField UIDField = new JTextField();
        UIDField.setBounds(150, 20, 200, 30);

        JLabel idLabel = new JLabel("Enter Reservation ID:");
        idLabel.setBounds(50, 50, 150, 30);
        JTextField idField = new JTextField();
        idField.setBounds(200, 50, 100, 30);

        JButton cancelButton = new JButton("View Reservation");
        cancelButton.setBounds(120, 150, 150, 40);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBounds(120, 200, 150, 40);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=Integer.parseInt(idField.getText());
                int loggedinID = Integer.parseInt(UIDField.getText());
                String detail=getReservationById(id,loggedinID);
                if (detail == null) {
                    JOptionPane.showMessageDialog(null, "No Reservation Found", "VIEW RESERVATION", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Display the reservation details if found
                    JOptionPane.showMessageDialog(null, detail, "Reservation Details", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelFrame.setVisible(false); 
                frame.setVisible(true);    
            }
        });

        cancelFrame.add(cancelButton);
        cancelFrame.add(idLabel);
        cancelFrame.add(idField);
        cancelFrame.add(UIDField);
        cancelFrame.add(UIDLabel);
        cancelFrame.add(goBackButton);
        cancelFrame.setVisible(true);
        cancelFrame.setLocationRelativeTo(null);         

    }
    private String getReservationById(int id,int loggedinID){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abheet", "root", "root");

            // SQL query to get the reservation details
            String sql = "SELECT * FROM reservations WHERE reservation_id = ? AND user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, loggedinID);

            rs = pstmt.executeQuery();

            // Check if reservation exists and return details
            if (rs.next()) {
                int ID = rs.getInt("reservation_id");
                String name = rs.getString("name");
                Date date = rs.getDate("date");
                int guests = rs.getInt("number_of_guests");

                return "Reservation ID: " + ID + "\n" +
                       "Name: " + name + "\n" +
                       "Date: " + date + "\n" +
                       "Number of Guests: " + guests;
            } else {
                return null; // Reservation not found
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            return null;

        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        ReservationSystemUI obj = new ReservationSystemUI();
        obj.start();
    }
}