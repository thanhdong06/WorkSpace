package fpt.swp.WorkSpace.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;



public class Helper {

    public static String convertLocalDateTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String creationTime = now.format(formatter);
        return creationTime;
    }

    public static String generateRandomString(int begin, int end){
        String randomString =  UUID.randomUUID().toString().replace("-", "").substring(begin, end);
        return randomString ;
    }
}
