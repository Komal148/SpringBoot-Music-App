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

    //Save Track
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

    //Get All Track
    @Override
    public List<Track> getAllTrack() throws TrackNotFound {
        List<Track> trackList = trackRepository.findAll();
        if( trackList.isEmpty())
        {
            throw new TrackNotFound("Track Repository is empty");
        }
        return trackRepository.findAll();
    }

    //delete track by Id
    @Override
    public boolean deleteTrack(int id) throws TrackNotFound {

        if( !trackRepository.existsById(id) )
        {
            throw new TrackNotFound("Track Not Found by Id!!");
        }
        trackRepository.deleteById(id);
        return true;
    }

    //Update List of Tracks
    @Override
    public Track updateTrack(Track track) throws TrackNotFound{
        if( !trackRepository.existsById(track.getTrackId()))
        {
            throw new TrackNotFound("Track Not Found for Updation ");
        }
       Track updateTrack= trackRepository.save(track);
        return updateTrack;
    }

    // Retrieve Track by Id
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

    // Retrieve Track by Track name
    @Override
    public Track trackByName(String trackName) throws TrackNotFound {
        if( trackRepository.findByTrackName(trackName) == null )
        {
            throw new TrackNotFound("Track with this TrackName not Exist!!");
        }
        return trackRepository.findByTrackName(trackName);
    }
}
