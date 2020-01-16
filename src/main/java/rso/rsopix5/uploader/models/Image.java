package rso.rsopix5.uploader.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import rso.rsopix5.commons.SubmissionAlbumiJsonConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Table(name = "images")

public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String s3_image_id;
    private Integer author_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_date;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified_date;

    @Convert(converter = SubmissionAlbumiJsonConverter.class)
    @Column(name = "albums")
    private Integer[] albums;
    //private String albums;

    public Image(){}
    public Image(ImagePost imagePost){
        this.author_id = imagePost.getAuthor_id();
        //this.albums = Arrays.copyOf(imagePost.getAlbums(), imagePost.getAlbums().length);
        this.s3_image_id = imagePost.getS3_image_id();
    }


    public Integer getId() {
        return id;
    }

    public Image setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getS3_image_id() {
        return s3_image_id;
    }

    public Image setS3_image_id(String s3_image_id) {
        this.s3_image_id = s3_image_id;
        return this;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public Image setAuthor_id(Integer author_id) {
        this.author_id = author_id;
        return this;
    }

    /*public Image setAlbums(String albums){
        this.albums = albums;
        return this;
    }*/

    public Date getCreated_date() {
        return created_date;
    }

    public Image setCreated_date(Date created_date) {
        this.created_date = created_date;
        return this;
    }

    public Date getModified_date() {
        return modified_date;
    }

    public Image setModified_date(Date modified_date) {
        this.modified_date = modified_date;
        return this;
    }

    public Integer[] getAlbums() {
        return albums;
    }

    public void setAlbums(Integer[] albums) {
        this.albums = albums;
    }


}
