package com.stackroute.MuzixApp.repository;

import com.stackroute.MuzixApp.Exceptions.TrackNotFound;
import com.stackroute.MuzixApp.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {

        @Autowired
        private TrackRepository trackRepository;
        private Track track;

        @Before
        public void setUp()
        {
            MockitoAnnotations.initMocks(this);
            track = new Track();
            track.setTrackComments("Good Song");
            track.setTrackId(101);
            track.setTrackName("Going");
        /*    list = new ArrayList<>();
            list.add(track);*/
        }

        @After
        public void tearDown(){

            trackRepository.deleteAll();
        }


        @Test
        public void testSaveTrack(){
            trackRepository.save(track);
            Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
            Assert.assertEquals(101,fetchTrack.getTrackId());

        }

        @Test
        public void testSaveTrackFailure(){
            Track testTrack = new Track(13,"Hope","ChainSmokers");
            trackRepository.save(track);
            Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
            Assert.assertNotSame(testTrack,track);
        }

        @Test
        public void testGetAllUser(){
            Track u = new Track(145,"Perfect","Edshereen");
            Track u1 = new Track(148,"Me Before You","Good");
            trackRepository.save(u);
            trackRepository.save(u1);

            List<Track> list = trackRepository.findAll();
            Assert.assertEquals("Perfect",list.get(0).getTrackName());
        }

        @Test
        public void updateTrackSuccess()
        {
            track.setTrackName("Hope");
            trackRepository.save(track);
            Assert.assertEquals("Hope",trackRepository.findById(track.getTrackId()).get().getTrackName());

        }

        @Test
        public void updateTrackFailure()
        {
            track.setTrackName("Hope");
            trackRepository.save(track);
            Assert.assertNotEquals("Hope1",trackRepository.findById(track.getTrackId()).get().getTrackName());

        }

        @Test
        public void deleteTrackSuccess()
        {
            Track track1=new Track(10,"hope","chainsmoker");
            trackRepository.save(track1);
            trackRepository.delete(track1);
            Assert.assertEquals(Optional.empty(),trackRepository.findById(10));
        }
        @Test
        public void deleteTrackFailure()
        {
            Track track1=new Track(10,"hope","chainsmoker");
            trackRepository.save(track1);
            trackRepository.delete(track);
            Assert.assertNotEquals(Optional.empty(),trackRepository.findById(10));
        }

        @Test
        public void getTrackbyIdSuccess()
        {
            trackRepository.save(track);
            Assert.assertEquals(101,trackRepository.findById(track.getTrackId()).get().getTrackId());
        }

        @Test
        public void getTrackByIdFailure()
        {
            trackRepository.save(track);
            Assert.assertNotEquals(102,trackRepository.findById(track.getTrackId()).get().getTrackId());
        }

        @Test
        public void getTrackByName()
        {
            trackRepository.save(track);
            Assert.assertEquals("Going",trackRepository.findById(track.getTrackId()).get().getTrackName());
        }

        @Test
        public void getTrackByNameFailure()
        {
            trackRepository.save(track);
            Assert.assertEquals("Going",trackRepository.findById(track.getTrackId()).get().getTrackName());
        }

    }
