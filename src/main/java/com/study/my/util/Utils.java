package com.study.my.util;

import com.study.my.model.Faculty;
import com.study.my.model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Comparator;

import static com.study.my.util.Constants.FILE_NAME;

public class Utils {
    private static final Logger LOGGER = Logger.getLogger(Utils.class);

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
        LOGGER.debug("students faculties: " + student.getFaculties());
        request.setAttribute("diploma", student.getDiploma());
    }

    public static Comparator<Faculty> getComparator(String sortBy, String locale) {
        if (sortBy == null) {
            return Comparator.comparing(Faculty::getNameEn);
        }
        switch (sortBy) {
            case "name":
                return locale.equals("ua")
                        ? Comparator.comparing(Faculty::getNameUa)
                        : Comparator.comparing(Faculty::getNameEn);
            case "budg":
                return Comparator.comparing(Faculty::getVacancyBudge);
            case "contr":
                return Comparator.comparing(Faculty::getVacancyContr);
        }
        throw new RuntimeException("Illegal sorting parameters");
    }
}
