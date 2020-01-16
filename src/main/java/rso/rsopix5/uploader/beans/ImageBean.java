package rso.rsopix5.uploader.beans;


import com.google.cloud.storage.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import rso.rsopix5.uploader.models.Image;

import javax.enterprise.context.RequestScoped;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.logging.LogManager;
import static java.nio.charset.StandardCharsets.UTF_8;

import rso.rsopix5.uploader.models.ImagePost;

@RequestScoped
public class ImageBean {

  @PersistenceContext(unitName = "imageUpload")
  private EntityManager entityManager;

  public Response insertNewImage(ImagePost imagePost) {

    Storage storage = StorageOptions.getDefaultInstance().getService();

    //Bucket bucket = storage.get("image-catalog-images");


    /*BlobId blobId = BlobId.of("image-catalog-images", "images/blob_name");
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();
    Blob blob = storage.create(blobInfo, "Hello, Cloud Storage!".getBytes(UTF_8));*/

  try {
    String contentBlobString = URLDecoder.decode(imagePost.getImageContent());
    String imageDataBytes = contentBlobString.substring(contentBlobString.indexOf(",") + 1);
    byte[] typeBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(imageDataBytes);
    /*byte[] typeBytes = Base64
            .getDecoder()
            .decode(imageDataBytes);*/
    String imageMimeType = getImageType(typeBytes);

    /*byte[] decodedBytes = Base64
            .getDecoder()
            .decode(contentBlobString);*/

    //byte[] decodedBytes = contentBlobString.getBytes("UTF-8");
    String base64Image = contentBlobString.split(",")[1];
    byte[] decodedBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);

    BlobId blobId = BlobId.of("image-catalog-images", "images/" + imagePost.getImageName());
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(imageMimeType).build();
    Blob blob = storage.create(blobInfo, decodedBytes);

    Image image = new Image(imagePost);
    Date date = new Date();
    image.setCreated_date(date).setModified_date(date).setS3_image_id(blob.getMediaLink());
      /*Image newImage = new Image(image);
              .setCreated_date(date).setModified_date(date);*/

    try {
      doImageInsert(image);
    } catch (EntityExistsException entityExistsException) {
      entityExistsException.printStackTrace();
      return Response.ok("Entity exists").build();
    } catch (IllegalArgumentException illegalArgumentException) {
      illegalArgumentException.printStackTrace();
      return Response.ok("Instance is not an entity").build();
    } catch (TransactionRequiredException transactionRequiredException) {
      transactionRequiredException.printStackTrace();
      return Response.ok("Transaction error").build();
    }
    return Response.ok(image.getId()).build();
  }
  catch(Exception e){
    e.printStackTrace();
    return Response.ok("Blob data decode error").build();
  }

/*
      try {
        Date dateNew = new Date();
        Image toUpdate = image;
        toUpdate.setModified_date(dateNew);
        try {
          //LOG.info("Starting update");
          EntityManager toCaptureEm = Persistence.createEntityManagerFactory("imageUpload").createEntityManager();
          toCaptureEm.getTransaction().begin();
          toCaptureEm.merge(toUpdate);
          toCaptureEm.getTransaction().commit();
          //LOG.info("ended update");
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException entityExistsException) {
          System.out.println(entityExistsException.getStackTrace());
          //LOG.error(entityExistsException);
          entityExistsException.printStackTrace();
        }
      } catch (Exception e){//(InterruptedException | ExecutionException | TimeoutException e) {
        //LOG.error(e+"", e);
      }
*/


    //return Response.ok(image).build();

  }

  private void doImageInsert(Image newImage) throws IllegalArgumentException {//EntityExistsException, IllegalArgumentException, TransactionRequiredException {
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(newImage);
      entityManager.getTransaction().commit();
    } /*catch (EntityExistsException entityExistsException) {
      throw entityExistsException;
    }*/ catch (IllegalArgumentException illegalArgumentException) {
      throw illegalArgumentException;
    }/* catch (TransactionRequiredException transactionRequiredException) {
      throw transactionRequiredException;
    }*/
  }

  private void checkFileExtension(String fileName) throws ServletException {
    if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
      String[] allowedExt = {".jpg", ".jpeg", ".png", ".gif"};
      for (String ext : allowedExt) {
        if (fileName.endsWith(ext)) {
          return;
        }
      }
      throw new ServletException("file must be an image");
    }
  }

  private boolean isMatch(byte[] pattern, byte[] data) {
    if (pattern.length <= data.length) {
      for (int idx = 0; idx < pattern.length; ++idx) {
        if (pattern[idx] != data[idx])
          return false;
      }
      return true;
    }

    return false;
  }

  private String getImageType(byte[] data) {
//        filetype    magic number(hex)
//        jpg         FF D8 FF
//        gif         47 49 46 38
//        png         89 50 4E 47 0D 0A 1A 0A
//        bmp         42 4D
//        tiff(LE)    49 49 2A 00
//        tiff(BE)    4D 4D 00 2A

    final byte[] pngPattern = new byte[] { (byte)0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A};
    final byte[] jpgPattern = new byte[] { (byte)0xFF, (byte)0xD8, (byte)0xFF};
    final byte[] gifPattern = new byte[] { 0x47, 0x49, 0x46, 0x38};
    final byte[] bmpPattern = new byte[] { 0x42, 0x4D };
    final byte[] tiffLEPattern = new byte[] { 0x49, 0x49, 0x2A, 0x00};
    final byte[] tiffBEPattern = new byte[] { 0x4D, 0x4D, 0x00, 0x2A};
    if (isMatch(pngPattern, data))
      return "image/png";

    if (isMatch(jpgPattern, data))
      return "image/jpg";

    if (isMatch(gifPattern, data))
      return "image/gif";

    if (isMatch(bmpPattern, data))
      return "image/bmp";

    if (isMatch(tiffLEPattern, data))
      return "image/tif";

    if (isMatch(tiffBEPattern, data))
      return "image/tif";

    return "image/png";
  }










}
