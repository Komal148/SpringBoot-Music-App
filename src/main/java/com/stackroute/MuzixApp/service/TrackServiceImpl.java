package com.stackroute.MuzixApp.service;

import com.stackroute.MuzixApp.Exceptions.TrackAlreadyExistException;
import com.stackroute.MuzixApp.Exceptions.TrackNotFound;
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
    public Track saveTrack(Track track) throws TrackAlreadyExistException {
        if( trackRepository.existsById(track.getTrackId()))
        {
            throw new TrackAlreadyExistException("Track Already Exist");
        }
        Track savedTrack=trackRepository.save(track);
        if( savedTrack == null )
        {
            throw new TrackAlreadyExistException("Track Already Exist");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTrack() {
        return trackRepository.findAll();
    }

    @Override
    public boolean deleteTrack(int id) throws TrackNotFound {

        if( !trackRepository.existsById(id) )
        {
            throw new TrackNotFound("Track Not Found by Id!!");
        }
        trackRepository.deleteById(id);
        return true;
    }

    @Override
    public Track updateTrack(Track track) throws TrackNotFound{
        if( !trackRepository.existsById(track.getTrackId()))
        {
            throw new TrackNotFound("Track Not Found for Updation ");
        }
       Track updateTrack= trackRepository.save(track);
        return updateTrack;
    }

    @Override
    public Optional<Track> getTrackById(int id) throws TrackNotFound{

        if(trackRepository.existsById(id))
        {
            return trackRepository.findById(id);
        }
        else
        {
            throw new TrackNotFound("Track is not there of given Id");
        }
    }

    @Override
    public Track trackByName(String trackName) throws TrackNotFound {
        if( trackRepository.findByTrackName(trackName) == null )
        {
            throw new TrackNotFound("Track with this TrackName not Exist!!");
        }
        return trackRepository.findByTrackName(trackName);
    }
}
