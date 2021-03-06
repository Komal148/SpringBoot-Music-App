package com.stackroute.MuzixApp.repository;

import com.stackroute.MuzixApp.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//Track Repository Class which will extends JPA Repository
@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {
    @Query("Select t from Track t where t.trackName = ?1")
    public Track findByTrackName(String trackName);
}
