package ru.gb.jtwo.lesson_four.online.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {

    private static final int WIN_WIDTH = 400;
    private static final int WIN_HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final JTextField tfLogin = new JTextField("ivan");
    private final JPasswordField tfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final JList<String> userList = new JList<>();
    //Файл
    PrintWriter pw = new PrintWriter(new FileWriter("logfile.txt"));



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new ClientGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private ClientGUI() throws IOException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Thread.setDefaultUncaughtExceptionHandler(this);
        setLocationRelativeTo(null);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setTitle("Chat client");
        setAlwaysOnTop(false);
        JScrollPane scrollLog = new JScrollPane(log);
        JScrollPane scrollUsers = new JScrollPane(userList);

        log.setEditable(false);
        String[] users = {"user01", "user02", "user03", "user04", "user05", "user06", "user07", "user_with_a_very_long_name_in_the_chat"};
        userList.setListData(users);
        userList.setPreferredSize(new Dimension(100, 0));

        //Добавляем слушатели событий
        cbAlwaysOnTop.addActionListener(this);
        btnSend.addActionListener(this);


        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) sendMessage();
            }
        });

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);
        add(scrollLog, BorderLayout.CENTER);
        add(scrollUsers, BorderLayout.EAST);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else {
            //throw new RuntimeException("Unknown source: " + src);
            //Нажатие кнопки отправки сообщения
            if (src == btnSend) sendMessage();
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        StackTraceElement[] ste = e.getStackTrace();
        String msg = String.format("Exception in thread %s: %s: %s\n\t at %s",
                t.getName(),
                e.getClass().getCanonicalName(),
                e.getMessage(),
                ste[0]
        );
        JOptionPane.showMessageDialog(null, msg, "Exception", JOptionPane.ERROR_MESSAGE);
    }
    //Отправка сообщений и запись в файл
    public void sendMessage() {
        String out = tfMessage.getText();
        log.append(out + "\n");
        pw.append(out + "\n");
        pw.flush();
        tfMessage.setText("");
        tfMessage.grabFocus();
    }
}
