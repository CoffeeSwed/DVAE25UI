package org.northernaurora.dvae25.GUI.signup.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.northernaurora.dvae25.GUI.GUIComponent.Page;
import org.northernaurora.dvae25.GUI.GUIComponent.RoundedJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegistrationHome extends Page {
    private static final Logger logger = LogManager.getLogger(RegistrationHome.class);

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JButton signupButton;
    private JLabel statusLabel;
    private RoundedJPanel center;
    private Box rightColumnFiller;
    private static final int inset = 8;
    @Override
    public String getTitleString() {
        return null;
    }

    @Override
    public void init() {
        super.init();
        this.setBackground(Utils.BACKGROUND_COLOR);
        RoundedJPanel center = new RoundedJPanel(20);
        center.setBackground(Utils.PANEL_COLOR);
        center.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(inset, inset, inset, inset); // spacing
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        center.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField("a");
        center.add(usernameField, gbc);

        //Age
        gbc.gridx = 0;
        gbc.gridy++;
        center.add(new JLabel("Age"), gbc);

        gbc.gridx = 1;
        center.add(new JTextField("20"), gbc);

        //Gender
        gbc.gridx = 0;
        gbc.gridy++;
        center.add(new JLabel("Gender"), gbc);

        gbc.gridx = 1;
        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addItem( "Man" );
        comboBox.addItem( "Woman" );
        comboBox.addItem( "Other" );
        center.add(comboBox, gbc);

        //Occupation
        gbc.gridx = 0;
        gbc.gridy++;
        center.add(new JLabel("Occupation field"), gbc);

        gbc.gridx = 1;
        comboBox = new JComboBox<String>();
        comboBox.addItem( "IT" );
        comboBox.addItem( "Social welfare" );
        comboBox.addItem( "Industrial" );
        comboBox.addItem("Transport");
        center.add(comboBox, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy++;
        center.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        emailField = new JTextField("a");
        center.add(emailField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy++;
        center.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField("a");
        center.add(passwordField, gbc);

        // Confirm Password
        gbc.gridx = 0;
        gbc.gridy++;
        center.add(new JLabel("Confirm Password:"), gbc);

        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField("a");
        center.add(confirmPasswordField, gbc);

        // Signup Button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        signupButton = new JButton("Sign Up");
        JPanel p = new JPanel();
        p.add(signupButton);
        p.setOpaque(false);
        center.add(p, gbc);

        // Status Label
        gbc.gridy++;
        gbc.gridwidth = 2;
        statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        center.add(statusLabel, gbc);

        // Button action
        signupButton.addActionListener(this::onSignup);
        this.center = center;
        this.onWindowUpdate();
        this.add(center);
    }

    private void onSignup(ActionEvent e) {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirm = new String(confirmPasswordField.getPassword());

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please fill all fields.");
            statusLabel.setForeground(Color.RED);

            return;
        }

        if (!password.equals(confirm)) {
            statusLabel.setText("Passwords do not match!");
            statusLabel.setForeground(Color.RED);
            return;
        }

        // Success!
        statusLabel.setForeground(new Color(0, 128, 0));
        statusLabel.setText("Signup successful!");

       this.getDvguiParent().addPage(new UserAgreement());
    }

    private int getColumnWidth() {

        FontMetrics metrics = this.usernameField.getFontMetrics(getFont());
        return metrics.charWidth('m');
    }

    @Override
    public void onWindowUpdate() {
        if(this.getDvguiParent() != null){
            Dimension newSize = this.getDvguiParent().getWindowSize();
            JLabel label = (JLabel) this.center.getComponent(0);
            logger.info(this.getDvguiParent().getWindowSize());
            int new_columns = (int) Math.ceil(8.0f*(float)newSize.width/10.0f);
            new_columns = new_columns - this.usernameField.getInsets().left - this.usernameField.getInsets().right;
            new_columns = new_columns - 4*inset;
            new_columns = new_columns - label.getSize().width;
            new_columns = new_columns - label.getInsets().left - label.getInsets().right;
            new_columns = Math.min(new_columns, 400);
            new_columns = new_columns / this.getColumnWidth();
            new_columns = new_columns - 1;
            if(new_columns > 0) {
                this.usernameField.setColumns(new_columns);
            }
            SwingUtilities.invokeLater(() -> {

                        this.revalidate();
                        this.repaint();
                    });
            logger.info("new Columns : "+new_columns);

        }
    }
}
