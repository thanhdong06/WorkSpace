package fpt.swp.WorkSpace.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {

    public static String convertLocalDateTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String creationTime = now.format(formatter);
        return creationTime;
    }

    public static String converArray(){
    return null;
    }
}
