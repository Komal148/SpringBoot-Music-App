package com.stackroute.MuzixApp.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.Entity;
import javax.persistence.Id;
/*import org.springframework.data.mongodb.core.mapping.Document;*/


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {


    @Id
    private int trackId;
    private String trackName;
    private String trackComments;

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", trackName='" + trackName + '\'' +
                ", trackComments='" + trackComments + '\'' +
                '}';
    }

}
