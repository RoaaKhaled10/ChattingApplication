package chattingapplication;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Client implements ActionListener {

    JTextField text;
    static JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream dout;
    static JFrame f = new JFrame();

    Client() {
        f.setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        f.add(p1);

        // زر الرجوع
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/arrow-pointing-to-left-direction_icon-icons.com_57013.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        // صورة الملف الشخصي
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/3289576-individual-man-people-person_107097.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);

        // أيقونة مكالمة الفيديو
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/videocall_vide_13384.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300, 20, 30, 30);
        p1.add(video);

        // أيقونة الاتصال الصوتي
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/call_phone_icon_131556.png"));
        Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(360, 20, 35, 30);
        p1.add(phone);

        // أيقونة المزيد
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/more_icon_143086.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert = new JLabel(i15);
        morevert.setBounds(420, 20, 10, 25);
        p1.add(morevert);

        JLabel name = new JLabel("Roaa");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);

        JLabel status = new JLabel("Active Now");
        status.setBounds(110, 35, 100, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        p1.add(status);

        a1 = new JPanel();
        a1.setBounds(5, 75, 440, 570);
        a1.setLayout(new BorderLayout());
        f.add(a1);

        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);

        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        send.addActionListener(this);
        f.add(send);

        // لوحة الملصقات
        JPanel stickerPanel = new JPanel();
        stickerPanel.setBounds(5, 600, 440, 50);
        stickerPanel.setBackground(Color.WHITE);

        String[] stickers = {"😊", "😂", "😍", "😎", "😢", "🤔", "👍", "🎉"};

        for (String sticker : stickers) {
            JButton stickerBtn = new JButton(sticker);
            stickerBtn.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
            stickerBtn.setBackground(new Color(7, 94, 84));
            stickerBtn.setForeground(Color.WHITE);
            stickerBtn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 25));

            stickerBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    text.setText(text.getText() + " " + sticker);
                }
            });

            stickerPanel.add(stickerBtn);
        }

        f.add(stickerPanel);

        f.setSize(450, 780);
        f.setLocation(800, 50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String out = text.getText();

            JPanel p2 = formatLabel(out);

            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            a1.add(vertical, BorderLayout.PAGE_START);

            dout.writeUTF(out);
            text.setText("");

            f.repaint();
            f.invalidate();
            f.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Dialog", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));

        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(time);

        return panel;
    }

    public static void main(String[] args) {
        new Client();

        try {
            Socket s = new Socket("localhost", 1234);
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            new ClientThread(din).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientThread extends Thread {

    private DataInputStream din;

    public ClientThread(DataInputStream din) {
        this.din = din;
    }

    public void run() {
        try {
            while (true) {
                String msg = din.readUTF();
                JPanel panel = Client.formatLabel(msg);
                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                Client.vertical.add(left);
                Client.a1.add(Client.vertical, BorderLayout.PAGE_START);
                Client.f.validate();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
