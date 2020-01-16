package rso.rsopix5.uploader.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import rso.rsopix5.commons.SubmissionAlbumiJsonConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImagePost implements Serializable {



    private String s3_image_id;
    private Integer author_id;
    private Integer[] albums;
    private String imageName;
    private String imageContent;
    private String imageType;


    public String getImageName(){
        return imageName;
    }

    public ImagePost setImageName(String imageName){
        this.imageName = imageName;
        return this;
    }

    public String getImageContent(){
        return imageContent;
    }

    public ImagePost setImageContent(String imageContent){
        this.imageContent = imageContent;
        return this;
    }

    public String getImageType(){
        return imageType;
    }

    public ImagePost setImageType(String imageType){
        this.imageType = imageType;
        return this;
    }


    public String getS3_image_id() {
        return s3_image_id;
    }

    public ImagePost setS3_image_id(String s3_image_id) {
        this.s3_image_id = s3_image_id;
        return this;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public ImagePost setAuthor_id(Integer author_id) {
        this.author_id = author_id;
        return this;
    }

    public Integer[] getAlbums() {
        return albums;
    }

    public void setAlbums(Integer[] albums) {
        this.albums = albums;
    }


}