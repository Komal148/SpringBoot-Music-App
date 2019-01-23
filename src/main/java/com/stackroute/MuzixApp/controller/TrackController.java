package com.stackroute.MuzixApp.controller;

import com.stackroute.MuzixApp.Exceptions.TrackAlreadyExistException;
import com.stackroute.MuzixApp.Exceptions.TrackNotFound;
import com.stackroute.MuzixApp.domain.Track;
import com.stackroute.MuzixApp.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1")
@Api(value="onlineMusicApp", description="Operations pertaining to Tracks in Muzix App")
public class TrackController extends ResponseEntityExceptionHandler {

   private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService)
    {
        this.trackService=trackService;
    }

    /*
    Save Track
     */
    @ApiOperation(value = "Enter List of music")
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistException
    {
        return new ResponseEntity<Track>(trackService.saveTrack(track),HttpStatus.OK);
    }
    /*
    Retrieve all tracks method
     */
    @ApiOperation(value = "View a list of available music",response = Iterable.class)
    @GetMapping("track")
    public ResponseEntity<List<Track>> getAllUser() throws TrackNotFound
    {
        return new ResponseEntity<List<Track>>(trackService.getAllTrack(),HttpStatus.OK);
    }

    /*
    Update Tracks method
     */
    @ApiOperation(value = "Updated List of Tracks")
    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track) throws TrackNotFound {
        trackService.updateTrack(track);
        return new ResponseEntity<String>("Successfully Updated", HttpStatus.CREATED);

    }
    /*
    Delete a Track given by Id
     */

    @ApiOperation(value = "Delete a Track")
    @DeleteMapping(value = "/track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable String id) throws TrackNotFound
    {
        trackService.deleteTrack(Integer.parseInt(id));
        return new ResponseEntity<String>("Successfully deleted",HttpStatus.OK);

    }
    /*
    Get Track by Id Method
     */
    @ApiOperation(value = "View a track by its Id")
    @GetMapping(value = "/track/{id}")
    public ResponseEntity<Optional<Track>> getByIdTrack(@PathVariable String id) throws TrackNotFound
    {

       return new ResponseEntity<Optional<Track>>(trackService.getTrackById(Integer.parseInt(id)),HttpStatus.CREATED);}

    /*
    Get Track by track name method
     */

    //    public ResponseEntity<Track> getByTrackName(@PathVariable String trackName)
    @ApiOperation(value = "View an available Track")
    @GetMapping(value = "/trackN/{trackName}")
    public ResponseEntity<Track> getByTrackName(@PathVariable String trackName) throws TrackNotFound
    {
        return new ResponseEntity<Track>(trackService.trackByName(trackName),HttpStatus.OK);
    }

}
