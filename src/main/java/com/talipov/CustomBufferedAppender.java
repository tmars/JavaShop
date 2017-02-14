package com.talipov;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CustomBufferedAppender extends AppenderSkeleton {
    private String username;
    private String password;
    private PrintWriter printWriter;
    private String filename;
    private File file;

    public void setFilename(String filename) {
        this.filename = filename;
        initFile();
    }

    private void initFile() {
        this.file = new File(filename);

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file, true);
            printWriter = new PrintWriter(new BufferedWriter(fileWriter));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected void append(LoggingEvent event) {
        printWriter.println(layout.format(event));
        printWriter.flush();

        if (file.length() >= 1048576) { // 1mb
            sendMail();
            clearFile();
            initFile();
        }
    }

    private void clearFile() {
        try {
            FileWriter fwOb = new FileWriter(filename, false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMail() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
        });

        try {
            Multipart multipart = new MimeMultipart();

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filename);

            messageBodyPart.setDataHandler( new DataHandler( source ) );
            messageBodyPart.setFileName( "log.txt" );
            multipart.addBodyPart(messageBodyPart);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress());
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("t.mars@mail.ru"));
            message.setSubject("Testing Buffered Subject");
            message.setContent(multipart);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
    }

    public boolean requiresLayout() {
        return false;
    }
}