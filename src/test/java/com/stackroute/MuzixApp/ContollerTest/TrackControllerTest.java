package com.stackroute.MuzixApp.ContollerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.MuzixApp.Exceptions.TrackAlreadyExistException;
import com.stackroute.MuzixApp.Exceptions.TrackNotFound;
import com.stackroute.MuzixApp.controller.TrackController;
import com.stackroute.MuzixApp.domain.Track;
import com.stackroute.MuzixApp.service.TrackService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {

        @Autowired
        private MockMvc mockMvc;
        private Track track;
        @MockBean
        private TrackService trackService;
        @InjectMocks
        private TrackController trackController;

        private List<Track> list =null;

        @Before
        public void setUp(){

            MockitoAnnotations.initMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
            track = new Track();
            track.setTrackId(26);
            track.setTrackName("Closer");
            track.setTrackComments("ChainSmokers");
            list = new ArrayList();
            list.add(track);
        }

        @Test
        public void saveTrack() throws  Exception {
            when(trackService.saveTrack(track)).thenReturn(track);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andDo(MockMvcResultHandlers.print());


        }
        @Test
        public void saveTrackFailure() throws Exception {
            when(trackService.saveTrack(track)).thenThrow(TrackAlreadyExistException.class);
            mockMvc.perform(MockMvcRequestBuilders.post("api/v1/track")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void getAllUser() throws Exception {
            when(trackService.getAllTrack()).thenReturn(list);
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());

        }


        @Test
        public void updateTrack() throws Exception
        {

            when(trackService.updateTrack(track)).thenReturn(track);
            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andDo(MockMvcResultHandlers.print());

        }

        @Test
        public void updateTrackFailure() throws Exception {
            when(trackService.updateTrack(new Track(34,"hello","I m here"))).thenThrow(TrackNotFound.class);
            mockMvc.perform(MockMvcRequestBuilders.post("api/v1/track")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void deleteTrack() throws Exception
        {
            when(trackService.deleteTrack(26)).thenReturn(true);
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/26")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());

        }
    @Test
    public void deleteTrackFailure() throws Exception
    {
        when(trackService.deleteTrack(27)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/27")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


        @Test
        public void getByIdTrack() throws Exception
        {
            when(trackService.getTrackById(26)).thenReturn(java.util.Optional.ofNullable(track));
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/26")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void getByIdTrackFailure() throws Exception
        {
            when(trackService.getTrackById(45)).thenThrow(TrackNotFound.class);
            mockMvc.perform(MockMvcRequestBuilders.get("api/v1/track")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void getTrackByName() throws Exception
        {
            when(trackService.trackByName("Closer")).thenReturn(track);
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/trackN/Closer")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());


        }
    @Test
    public void getTrackByNameFailure() throws Exception
    {
        when(trackService.trackByName(track.getTrackName())).thenThrow(TrackNotFound.class);
        mockMvc.perform(MockMvcRequestBuilders.get("api/v1/trackN/hello")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }




    private static String asJsonString(final Object obj)
        {
            try{
                return new ObjectMapper().writeValueAsString(obj);

            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }










    }


