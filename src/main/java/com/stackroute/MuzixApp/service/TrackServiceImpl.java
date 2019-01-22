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
    @Override
    public Track saveTrack(Track track) {
        Track savedTrack=trackRepository.save(track);
        return savedTrack;
    }

    @Override
    public List<Track> getAllTrack() {
        return trackRepository.findAll();
    }

    @Override
    public boolean deleteTrack(int id) {
        trackRepository.deleteById(id);
        return true;
    }

    @Override
    public Track updateTrack(Track track) {
       Track updateTrack= trackRepository.save(track);
        return updateTrack;
    }

    @Override
    public Optional<Track> getTrackById(int id) {

        if(trackRepository.existsById(id))
        {
            return trackRepository.findById(id);
        }
        return null;
    }

    @Override
    public Track trackByName(String trackName) {
        return trackRepository.findByTrackName(trackName);
    }
}
