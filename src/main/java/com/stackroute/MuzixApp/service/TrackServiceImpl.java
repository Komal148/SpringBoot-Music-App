package com.stackroute.MuzixApp.service;

import com.stackroute.MuzixApp.domain.Track;
import com.stackroute.MuzixApp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService{

    TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository=trackRepository;
    }

    //Save Track
    @Override
    public Track saveTrack(Track track) {
        Track savedTrack=trackRepository.save(track);
        return savedTrack;
    }

    //Get All Track
    @Override
    public List<Track> getAllTrack() {
        return trackRepository.findAll();
    }

    //delete track by Id
    @Override
    public boolean deleteTrack(int id) {
        trackRepository.deleteById(id);
        return true;
    }

    //Update List of Tracks
    @Override
    public Track updateTrack(Track track) {
       Track updateTrack= trackRepository.save(track);
        return updateTrack;
    }

    // Retrieve Track by Id
    @Override
    public Optional<Track> getTrackById(int id) {

        if(trackRepository.existsById(id))
        {
            return trackRepository.findById(id);
        }
        return null;
    }

    // Retrieve Track by Track name
    @Override
    public Track trackByName(String trackName) {
        return trackRepository.findByTrackName(trackName);
    }
}
