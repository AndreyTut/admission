package com.study.my.util;

import com.study.my.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import static com.study.my.util.Constants.FILE_NAME;

public class Utils {

    public static byte[] getImageFromRequest(HttpServletRequest request) {
        byte[] image = null;
        try {
            Part filePart = request.getPart(FILE_NAME);
            if (filePart != null) {
                InputStream fileContent = filePart.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (fileContent.available() > 0) {
                    byteArrayOutputStream.write(fileContent.read());
                }
                byteArrayOutputStream.flush();
                image = byteArrayOutputStream.toByteArray();
                return image;
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
            //TODO process
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void putStudentToRequest(User student, HttpServletRequest request) {
        byte[] diplomImage = student.getDiplomImage();
        String base64Image = null;
        if (diplomImage != null) {
            base64Image = Base64.getEncoder().encodeToString(diplomImage);
        }
        request.setAttribute("stringimage", base64Image);
        request.setAttribute("student", student);
        request.setAttribute("diploma", student.getDiploma());
    }
}
