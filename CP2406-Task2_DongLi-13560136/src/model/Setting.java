package model;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;


//Main window: Contains the settings of the traffic light parameters, and the frequency of vehicles generated at the north-south and east-west intersections
public class Setting extends JFrame {
    static JTextField redText;
    static JTextField greenText;
    static JTextField delayText;
    static JTextField SNText;
    static JTextField EWText;


    public Setting() {
        getContentPane().setLayout(null);

        redText = new JTextField();
        redText.setBounds(251, 87, 86, 21);
        getContentPane().add(redText);
        redText.setColumns(20);

        //Set the start button, when click the start button, draw the traffic map according to the set parameters
        JButton startButton = new JButton("Start");
        startButton.setBounds(165, 332, 91, 23);
        getContentPane().add(startButton);

        greenText = new JTextField();
        greenText.setBounds(251, 136, 86, 21);
        getContentPane().add(greenText);
        greenText.setColumns(20);

        JLabel redLabel = new JLabel("Red light duration");
        redLabel.setBounds(73, 90, 143, 15);
        getContentPane().add(redLabel);

        JLabel greenLabel = new JLabel("Green light duration");
        greenLabel.setBounds(73, 139, 143, 15);
        getContentPane().add(greenLabel);

        Component verticalGlue = Box.createVerticalGlue();
        verticalGlue.setBounds(62, 87, 1, 1);
        getContentPane().add(verticalGlue);

        delayText = new JTextField();
        delayText.setBounds(251, 181, 86, 21);
        getContentPane().add(delayText);
        delayText.setColumns(20);

        JLabel delayLabel = new JLabel("Green light delay");
        delayLabel.setBounds(73, 184, 127, 15);
        getContentPane().add(delayLabel);

        JLabel lblNewLabel = new JLabel("North-south distance");
        lblNewLabel.setBounds(73, 235, 129, 15);
        getContentPane().add(lblNewLabel);

        SNText = new JTextField();
        SNText.setBounds(251, 232, 86, 21);
        getContentPane().add(SNText);
        SNText.setColumns(20);

        JLabel lblNewLabel_1 = new JLabel("East-west distance");
        lblNewLabel_1.setBounds(73, 282, 134, 15);
        getContentPane().add(lblNewLabel_1);

        EWText = new JTextField();
        EWText.setBounds(251, 279, 86, 21);
        getContentPane().add(EWText);
        EWText.setColumns(20);

        JLabel label = new JLabel("Traffic Simulator ");
        label.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label.setBounds(128, 33, 149, 15);
        getContentPane().add(label);


        this.setSize(431, 443);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        SimpleListener ourListener = new SimpleListener();
        startButton.addActionListener(ourListener);
    }

    //Monitor the start button, when you click the start button, the FrameDemo class of the new drawing window is created
    private class SimpleListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub

            FrameDemo demo = new FrameDemo();
        }

    }

    public static void main(String[] args) {
        Setting demo = new Setting();
    }
}