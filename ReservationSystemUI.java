import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class Reservation {
    private int id;
    private String name;
    private String date;
    private int numberOfGuests;

    public Reservation(int id, String name, String date, int numberOfGuests) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.numberOfGuests = numberOfGuests;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }
}


 class ReservationSystem {
    private List<Reservation> reservations = new ArrayList<>();
    private int nextId = 1;

    public Reservation makeReservation(String name, String date, int numberOfGuests) {
        Reservation reservation = new Reservation(nextId++, name, date, numberOfGuests);
        reservations.add(reservation);
        return reservation;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Reservation getReservationById(int id) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        return null;
    }

    public boolean cancelReservation(int id) {
        Reservation reservation = getReservationById(id);
        if (reservation != null) {
            reservations.remove(reservation);
            return true;
        }
        return false;
    }
}


 class ReservationSystemUI {
    private ReservationSystem reservationSystem = new ReservationSystem();

    public void start() {
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

                if (name.isEmpty() || date.isEmpty() || numPeople==0) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Reservation reservation = reservationSystem.makeReservation(name, date, numPeople);

                    JOptionPane.showMessageDialog(null, "Reservation Details:\n" +
                            "Reservation made with ID:"+reservation.getId()+"\n"+
                            "Name: " + name + "\n" +
                            "Date: " + date + "\n" +
                            "Number of People: " + numPeople, "Reservation Successful", JOptionPane.INFORMATION_MESSAGE);
                }
                nameField.setText("");
                peopleField.setText("");
                dateField.setText("");
            }
        });

        frame_1.add(nameLabel);
        frame_1.add(nameField);
        frame_1.add(dateLabel);
        frame_1.add(dateField);
        frame_1.add(peopleLabel);
        frame_1.add(peopleField);
        frame_1.add(goBackButton);
        frame_1.add(submitButton);
       
        frame_1.setVisible(true);
    }

    void openViewReservation(JFrame frame){
        frame.setVisible(false);

        JFrame reservationListFrame = new JFrame("Reservation List");
        reservationListFrame.setSize(400, 400);
        reservationListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reservationListFrame.setLayout(null);
        reservationListFrame.setLocationRelativeTo(null); 

        JTextArea reservationTextArea = new JTextArea();
        reservationTextArea.setBounds(50, 50, 300, 200);

            StringBuilder sb = new StringBuilder();
            for (Reservation r:reservationSystem.getReservations()) {
                sb.append(r.getId() + " - " + r.getName() + " - " + r.getDate() + " - " + r.getNumberOfGuests()+"\n");
            }
            reservationTextArea.setText(sb.toString());
        
        reservationTextArea.setEditable(false);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBounds(140, 300, 120, 40);

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
    }

    void openCancelReservation(JFrame frame){
        frame.setVisible(false);

        JFrame cancelFrame = new JFrame("Cancel Reservation");
        cancelFrame.setSize(400, 300);
        cancelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cancelFrame.setLayout(null);

      
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
                int id=Integer.parseInt(idField.getText());
                if(reservationSystem.cancelReservation(id)){
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
        cancelFrame.add(goBackButton);
        cancelFrame.setVisible(true);
        cancelFrame.setLocationRelativeTo(null); 

    }

    void openViewaReservation(JFrame frame){
        frame.setVisible(false);

        JFrame cancelFrame = new JFrame("View a Reservation");
        cancelFrame.setSize(400, 300);
        cancelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cancelFrame.setLayout(null);

        
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
                if(reservationSystem.getReservationById(id)==null){
                    JOptionPane.showMessageDialog(null,"No Reservation Found","VIEW RESERVATION", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Reservation Details:\n" +
                    "Reservation made with ID:"+reservationSystem.getReservationById(id).getId()+"\n"+
                    "Name: " + reservationSystem.getReservationById(id).getName() + "\n" +
                    "Date: " + reservationSystem.getReservationById(id).getDate() + "\n" +
                    "Number of People: " + reservationSystem.getReservationById(id).getNumberOfGuests(), "VIEW RESERVATION", JOptionPane.INFORMATION_MESSAGE);
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
        cancelFrame.add(goBackButton);
        cancelFrame.setVisible(true);
        cancelFrame.setLocationRelativeTo(null);         

    }

    public static void main(String[] args)
    {
        ReservationSystemUI obj = new ReservationSystemUI();
        obj.start();
    }
}