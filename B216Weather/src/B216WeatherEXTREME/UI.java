package B216WeatherEXTREME;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static javax.swing.BorderFactory.createEmptyBorder;

@SuppressWarnings("serial")
public class UI extends JFrame implements WindowListener, ActionListener {
    private static final JLabel windowHeader = new JLabel("<html>Weather Station <font color='red'>EXTREME</font></html>");
    private static final JButton toggleWeatherButton = new JButton("Start vejrstation");
    private static final JButton saveWeatherButton = new JButton("Gem vejrdata");

    private static final JLabel rainDataLabel = new JLabel("Regn:\n 0 mm");
    private static final JLabel moistDataLabel = new JLabel("Luftfugtighed:\n 69%");
    private static final JLabel tempDataLabel = new JLabel("Temperatur:\n 42.0 \u2103");
    private static final JTextArea fileInfoText = new JTextArea(1,1);
    private static final JScrollPane fileInfoScroll = new JScrollPane(fileInfoText);

    private static boolean weatherStationRunning = false;
    private Vejrstation vejrstation;

    private final Runnable runnable = new Runnable() {
        Regnmaaler regn;
        Fugtighedsmaaler fugt;
        Termometer temp;

        @Override
        public void run() {
            regn = new Regnmaaler(0);
            fugt = new Fugtighedsmaaler(1);
            temp = new Termometer(2);

            fugt.rainSensorAddress(regn);
            regn.start();
            fugt.start();
            temp.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.err.println("Something happened...");
            }
            while (weatherStationRunning) {

                    double[] dataArray = getAllData();
                    vejrstation.addData(dataArray);
                    updateUI(dataArray);
                    System.out.println(dataArray[0] + ", " + dataArray[1] + ", " + dataArray[2]);
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println("Something happened...");
                    }
            }
            regn.stopThread();
            fugt.stopThread();
            temp.stopThread();
            System.out.println("STOPPED WEATHER STATION!");
        }

        public double[] getAllData() {
            double[] returnData = new double[3];

            returnData[0] = regn.getData();
            returnData[1] = fugt.getData();
            returnData[2] = temp.getData();

            return returnData;
        }
    };


    public static void main(String[] args) {
    	@SuppressWarnings("unused")
        UI window = new UI("Vejrstation");

    }

    public UI(String title) {
        super(title);

        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addWindowListener(this);
        JPanel titlePane = new JPanel(new GridBagLayout());
        JPanel dataPane = new JPanel(new GridBagLayout());
        JPanel filePane = new JPanel(new GridBagLayout());
        Border blackline = BorderFactory.createLineBorder(Color.black);

        GridBagConstraints headerConstraint = new GridBagConstraints();
        headerConstraint.gridx = 0;
        headerConstraint.gridy = 0;
        headerConstraint.weighty = 0;
        headerConstraint.gridwidth = 2;
        headerConstraint.fill = GridBagConstraints.NONE;
        headerConstraint.anchor = GridBagConstraints.CENTER;
        Font headerFont  = new Font(Font.SANS_SERIF,  Font.BOLD, 50);
        windowHeader.setFont(headerFont);
        titlePane.add(windowHeader, headerConstraint);

/*      GridBagConstraints button1Constraint = new GridBagConstraints();
        button1Constraint.gridx = 0;
        button1Constraint.gridy = 1;
        button1Constraint.weightx = 0.1;
        titlePane.add(startWeatherButton, button1Constraint);*/

        GridBagConstraints button2Constraint = new GridBagConstraints();
        button2Constraint.gridx = 0;
        button2Constraint.gridy = 1;
        button2Constraint.weightx = 0.1;
        button2Constraint.insets = new Insets(50, 15, 0, 15);
        toggleWeatherButton.addActionListener(this);
        titlePane.add(toggleWeatherButton, button2Constraint);

        GridBagConstraints dataPaneConstraint = new GridBagConstraints();
        dataPaneConstraint.gridx = 1;
        dataPaneConstraint.gridy = 1;
        dataPaneConstraint.weightx = 1.0;
        dataPaneConstraint.weighty = 1.0;
        dataPaneConstraint.fill = GridBagConstraints.BOTH;
        dataPaneConstraint.insets = new Insets(50, 0, 0, 15);
        dataPane.setBorder(BorderFactory.createTitledBorder(blackline, "Weather Data"));
        titlePane.add(dataPane, dataPaneConstraint);

        GridBagConstraints seperatorConstraint = new GridBagConstraints();
        seperatorConstraint.gridx = 0;
        seperatorConstraint.gridy = 2;
        seperatorConstraint.gridwidth = 2;
        seperatorConstraint.fill = GridBagConstraints.HORIZONTAL;
        seperatorConstraint.weightx = 1.0;
        seperatorConstraint.insets = new Insets(50, 10, 50, 10);
        titlePane.add(new JSeparator(JSeparator.HORIZONTAL), seperatorConstraint);

        GridBagConstraints button3Constraint = new GridBagConstraints();
        button3Constraint.gridx = 0;
        button3Constraint.gridy = 3;
        button3Constraint.weightx = 0.1;
        button3Constraint.insets = new Insets(0, 15, 50, 15);
        saveWeatherButton.addActionListener(this);
        titlePane.add(saveWeatherButton, button3Constraint);

        GridBagConstraints filePaneConstraint = new GridBagConstraints();
        filePaneConstraint.gridx = 1;
        filePaneConstraint.gridy = 3;
        filePaneConstraint.weightx = 1.0;
        filePaneConstraint.weighty = 0.5;
        filePaneConstraint.fill = GridBagConstraints.BOTH;
        filePane.setMaximumSize(filePane.getPreferredSize());
        filePaneConstraint.insets = new Insets(0, 0, 50, 15);

        filePane.setBorder(BorderFactory.createTitledBorder(blackline, "File Information"));
        titlePane.add(filePane, filePaneConstraint);

        // ADD DATA TO DATA PANEL
        GridBagConstraints rainDataConstraints = new GridBagConstraints();
        rainDataConstraints.gridx = 0;
        rainDataConstraints.gridy = 0;
        rainDataConstraints.weightx = 1.0;
        rainDataConstraints.weighty = 0.0;
        rainDataConstraints.fill = GridBagConstraints.NONE;
        dataPane.add(rainDataLabel, rainDataConstraints);

        GridBagConstraints moistDataConstraints = new GridBagConstraints();
        moistDataConstraints.gridx = 1;
        moistDataConstraints.gridy = 0;
        moistDataConstraints.weightx = 1.0;
        moistDataConstraints.weighty = 0.0;
        moistDataConstraints.fill = GridBagConstraints.NONE;
        dataPane.add(moistDataLabel, moistDataConstraints);

        GridBagConstraints tempDataConstraints = new GridBagConstraints();
        tempDataConstraints.gridx = 2;
        tempDataConstraints.gridy = 0;
        tempDataConstraints.weightx = 1.0;
        tempDataConstraints.weighty = 0.0;
        tempDataConstraints.fill = GridBagConstraints.NONE;
        dataPane.add(tempDataLabel, tempDataConstraints);

        GridBagConstraints fileInfoConstraints = new GridBagConstraints();
        fileInfoConstraints.gridx = 0;
        fileInfoConstraints.gridy = 0;
        fileInfoConstraints.weightx = 1.0;
        fileInfoConstraints.weighty = 1.0;
        fileInfoConstraints.fill = GridBagConstraints.BOTH;
        fileInfoText.setEditable(false);
        fileInfoText.setOpaque(true);
        fileInfoScroll.setOpaque(true);
        fileInfoScroll.setBorder(createEmptyBorder());
        filePane.add(fileInfoScroll, fileInfoConstraints);

        titlePane.setBackground(Color.lightGray);
        this.add(titlePane);
    }

    private void updateUI(double[] data) {
        rainDataLabel.setText(String.format("Regn: %.2f mm", data[0]));
        moistDataLabel.setText(String.format("Luftfugtighed: %.2f %%", data[1]));
        tempDataLabel.setText(String.format("Temperatur: %.2f \u2103", data[2]));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == toggleWeatherButton) {
            if(weatherStationRunning) {
                toggleWeatherButton.setText("Start vejrstation");
            } else {
                toggleWeatherButton.setText("Stop vejrstation ");
                vejrstation = new Vejrstation(runnable);
            }
            weatherStationRunning = !weatherStationRunning;
        }

        if(e.getSource() == saveWeatherButton) {
            try {
                String fileName = vejrstation.saveLogAs();
                fileInfoText.append(String.format("Successfully saved weather data to '%s'\n", fileName));
            } catch (IOException er) {
                er.printStackTrace();
            }
        }
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Closing program!");
        try {
            vejrstation.deleteTemp();
        } catch(Exception er) {
            System.out.println("Couldn't delete temp-file.");
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

