package com.stackroute.MuzixApp.service;

import com.stackroute.MuzixApp.Exceptions.TrackAlreadyExistException;
import com.stackroute.MuzixApp.Exceptions.TrackNotFound;
import com.stackroute.MuzixApp.domain.Track;
import com.stackroute.MuzixApp.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

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
            Assert.assertEquals(track,savedTrack);

            //verify here verifies that userRepository save method is only called once
            verify(trackRepository,times(1)).save(track);

        }

        @Test(expected = TrackAlreadyExistException.class)
        public void saveUserTestFailure() throws TrackAlreadyExistException {
            when(trackRepository.save((Track)any())).thenReturn(null);
            Track savedTrack = trackService.saveTrack(track);
            System.out.println("savedTrack" + savedTrack);
            Assert.assertEquals(track,savedTrack);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/


        }

        @Test
        public void getAllUser() throws TrackNotFound {

            trackRepository.save(track);
            //stubbing the mock to return specific data
            when(trackRepository.findAll()).thenReturn(list);
            List<Track> userlist = trackService.getAllTrack();
            Assert.assertEquals(list,userlist);
        }





    }


