package com.stackroute.MuzixApp.service;

import com.stackroute.MuzixApp.Exceptions.TrackAlreadyExistException;
import com.stackroute.MuzixApp.Exceptions.TrackNotFound;
import com.stackroute.MuzixApp.domain.Track;

import java.util.List;
import java.util.Optional;
//Track Dao Class
public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistException;
    public List<Track> getAllTrack() throws TrackNotFound;
    public boolean deleteTrack(int id) throws TrackNotFound;
    public Track updateTrack(Track track) throws TrackNotFound;
    public Optional<Track> getTrackById(int id) throws TrackNotFound;
    public Track trackByName(String trackName)throws TrackNotFound;

}
