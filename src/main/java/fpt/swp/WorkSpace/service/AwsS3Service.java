package fpt.swp.WorkSpace.service;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class AwsS3Service {

     final String bucketname = "swp-workspace-images";         // bucket name on S3

     @Value("${aws.s3.secret.key}")
    private String awsSecretKey;         // Secret key

     @Value("${aws.s3.access.key}")
    private String awsAccessKey;

     public String saveImgToS3(MultipartFile photo) {
         String s3LocationImg = null;

         try {

             String fileName = photo.getOriginalFilename();
             BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
             AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                     .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                     .withRegion(Regions.US_EAST_2)
                     .build();

             InputStream inputStream = photo.getInputStream();
             ObjectMetadata metadata = new ObjectMetadata();
             metadata.setContentType("image/jpeg");

             PutObjectRequest putObjectRequest = new PutObjectRequest(bucketname, fileName, inputStream, metadata);
             s3Client.putObject(putObjectRequest);
             return s3LocationImg = "https://" + bucketname + ".s3.amazonaws.com" + fileName;

         } catch (Exception e) {
             throw new RuntimeException(e.getMessage());
         }
     }


}
