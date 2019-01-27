package com.stackroute.MuzixApp.service;

import com.stackroute.MuzixApp.Exceptions.TrackAlreadyExistException;
import com.stackroute.MuzixApp.Exceptions.TrackNotFound;
import com.stackroute.MuzixApp.domain.Track;
import com.stackroute.MuzixApp.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TrackServiceTest {

        private Track track;

        //Create a mock for UserRepository
        @Mock
        private TrackRepository trackRepository;

        //Inject the mocks as dependencies into UserServiceImpl
        @InjectMocks
        private TrackServiceImpl trackService;
        List<Track> list= null;


        @Before
        public void setUp(){
            //Initialising the mock object
            MockitoAnnotations.initMocks(this);
            track = new Track();
            track.setTrackComments("Good Song");
            track.setTrackId(101);
            track.setTrackName("Going");
            list = new ArrayList<>();
            list.add(track);


        }

        @Test
        public void saveUserTestSuccess() throws TrackAlreadyExistException {

            when(trackRepository.save((Track) any())).thenReturn(track);
            Track savedTrack = trackService.saveTrack(track);
            assertEquals(track,savedTrack);

            //verify here verifies that userRepository save method is only called once
            verify(trackRepository,times(1)).save(track);

        }

        @Test(expected = TrackAlreadyExistException.class)
        public void saveUserTestFailure() throws TrackAlreadyExistException {
            when(trackRepository.save((Track)any())).thenReturn(null);
            Track savedTrack = trackService.saveTrack(track);
            System.out.println("savedTrack" + savedTrack);
            assertEquals(track,savedTrack);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/


        }

        @Test
        public void getAllUser() throws TrackNotFound {

            trackRepository.save(track);
            //stubbing the mock to return specific data
            when(trackRepository.findAll()).thenReturn(list);
            List<Track> userlist = trackService.getAllTrack();
            assertEquals(list,userlist);
        }

        @Test
        public void deleteTrackSuccess() throws TrackNotFound
        {
            trackRepository.save(track);
            when(trackRepository.existsById(anyInt())).thenReturn(true);
            boolean actualValue = trackService.deleteTrack(101);
            assertEquals(true,actualValue);
        }

        @Test
          public void deleteTrackFailure() throws TrackNotFound
        {
         trackRepository.save(track);
         when(trackRepository.existsById(anyInt())).thenReturn(true);
         boolean actualValue = trackService.deleteTrack(101);
         assertNotEquals(false,actualValue);
        }

        @Test
        public void updateTrackSuccess() throws TrackNotFound
        {

            when(trackRepository.existsById(track.getTrackId())).thenReturn(true);
            Track updatedTrack=new Track();
            updatedTrack.setTrackId(101);
            updatedTrack.setTrackName("anything");
            updatedTrack.setTrackComments("something");
            when(trackRepository.save(updatedTrack)).thenReturn(updatedTrack);
            assertEquals(updatedTrack,trackService.updateTrack(updatedTrack));
        }

        @Test(expected = TrackNotFound.class)
        public void updateTrackFailure() throws TrackNotFound
        {
            when(trackRepository.existsById(track.getTrackId())).thenReturn(false);
            when(trackRepository.save((Track)any())).thenReturn(null);
            Track updateTrack=trackService.updateTrack(new Track(101,"abc","efg"));
            assertNotEquals(track,updateTrack);
        }

        @Test
        public void getTrackByIdSucces() throws TrackNotFound
        {
            when(trackRepository.existsById(any())).thenReturn(true);
            when(trackRepository.findById(any())).thenReturn(Optional.ofNullable(track));
            Optional<Track> idTrack=trackService.getTrackById(101);
            assertEquals(Optional.ofNullable(track),idTrack);


        }
        @Test(expected = TrackNotFound.class)
        public void getTrackByIdFailure() throws TrackNotFound
        {
             when(trackRepository.existsById(any())).thenReturn(false);
             when(trackRepository.findById(any())).thenReturn(Optional.ofNullable(track));
             Optional<Track> idTrack=trackService.getTrackById(101);
            assertNotEquals(Optional.ofNullable(track),idTrack);
        }

        @Test
        public void getTrackbyNameSuccess() throws TrackNotFound
        {
//            when(trackRepository.save(track)).thenReturn(track);
            when(trackRepository.findByTrackName(anyString())).thenReturn(track);
            Track track1= trackService.trackByName(track.getTrackName());

            assertEquals(track1,track);

            verify(trackRepository,times(0)).save(track1);
        }

        @Test(expected = TrackNotFound.class)
        public void getTrackbyNameFailure() throws TrackNotFound
        {
//            when(trackRepository.save(track)).thenReturn(track);
            when(trackRepository.findByTrackName(anyString())).thenReturn(null);
            Track track1= trackService.trackByName(track.getTrackName());

            assertNotEquals(track1,track);

             verify(trackRepository,times(0)).save(track1);
         }





    }


