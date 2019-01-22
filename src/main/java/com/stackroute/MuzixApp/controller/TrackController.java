package com.stackroute.MuzixApp.controller;

import com.stackroute.MuzixApp.domain.Track;
import com.stackroute.MuzixApp.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1")
public class TrackController {

    TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService)
    {
        this.trackService=trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
        }
        catch( Exception ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<List<Track>> getAllUser()
    {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTrack(),HttpStatus.OK);
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.updateTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully Updated", HttpStatus.CREATED);
        }
        catch( Exception ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable String id)
    {
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(Integer.parseInt(id));
            responseEntity=new ResponseEntity<String>("Successfully deleted",HttpStatus.OK);
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping(value = "/track/{id}")
    public ResponseEntity<Optional<Track>> getByIdTrack(@PathVariable String id)
    {
        ResponseEntity responseEntity;
        try {

            return new ResponseEntity<Optional<Track>>(trackService.getTrackById(Integer.parseInt(id)),HttpStatus.CREATED);
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


}
