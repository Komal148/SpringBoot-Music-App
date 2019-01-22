package com.stackroute.MuzixApp.controller;

import com.stackroute.MuzixApp.Exceptions.TrackAlreadyExistException;
import com.stackroute.MuzixApp.Exceptions.TrackNotFound;
import com.stackroute.MuzixApp.domain.ExceptionInfoJSON;
import com.stackroute.MuzixApp.domain.Track;
import com.stackroute.MuzixApp.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ControllerAdvice
@RestController
@RequestMapping(value = "api/v1")
@Api(value="onlineMusicApp", description="Operations pertaining to Tracks in Muzix App")
public class TrackController extends ResponseEntityExceptionHandler {

    TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService)
    {
        this.trackService=trackService;
    }

    @ApiOperation(value = "Enter List of music")
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
        }
        catch( TrackAlreadyExistException ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "View a list of available music",response = Iterable.class)
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

    @ApiOperation(value = "Updated List of Tracks")
    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.updateTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully Updated", HttpStatus.CREATED);
        }
        catch( TrackNotFound ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Delete a Track")
    @DeleteMapping(value = "/track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable String id)
    {
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(Integer.parseInt(id));
            responseEntity=new ResponseEntity<String>("Successfully deleted",HttpStatus.OK);
        }
        catch (TrackNotFound ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "View a track by its Id")
    @GetMapping(value = "/track/{id}")
    public ResponseEntity<Optional<Track>> getByIdTrack(@PathVariable String id)
    {
        ResponseEntity responseEntity;
        try {

            return new ResponseEntity<Optional<Track>>(trackService.getTrackById(Integer.parseInt(id)),HttpStatus.CREATED);
        }
        catch (TrackNotFound ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

//    public ResponseEntity<Track> getByTrackName(@PathVariable String trackName)
    @ApiOperation(value = "View an available Track")
    @GetMapping(value = "/trackN/{trackName}")
    public ResponseEntity<Track> getByTrackName(@PathVariable String trackName)
    {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<Track>(trackService.trackByName(trackName),HttpStatus.OK);
        }
        catch (TrackNotFound ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ExceptionHandler(TrackNotFound.class)
    public final ResponseEntity<ExceptionInfoJSON> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionInfoJSON errorDetails = new ExceptionInfoJSON(request.getDescription(false), ex.getMessage(),new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
