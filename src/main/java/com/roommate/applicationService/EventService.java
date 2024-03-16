package com.roommate.applicationService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;
import roommate.domain.model.keymaster.Access;
import roommate.domain.model.keymaster.Key;
import roommate.domain.model.Reservation;
import roommate.domain.model.keymaster.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventService {

    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;

    public EventService(SeatRepository seatRepository, ReservationRepository reservationRepository) {
        this.seatRepository = seatRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Room> getSeatsReservedByKeyHolder(Key key) {
        String keyHolder = key.owner();

        List<Room> rooms = new ArrayList<>();

        List<Reservation> allReservations = reservationRepository.findReservationsByRealName(keyHolder);

        for(int i = 0; i < allReservations.size();i++){
            String uuid = seatRepository.getUuid(allReservations.get(i).getSeat());
            Room r = new Room(uuid, allReservations.get(i).getSeat());
            rooms.add(r);
        }

        return rooms;

    }

    public List<Access> createAccessList(List<Key> keyHolders, List<Room> rooms) {
        List<Access> accesses = new ArrayList<>();

        for(int i = 0; i < keyHolders.size(); i++) {
            Key currentKey = keyHolders.get(i);
            List<Room> seatsReservedByKeyHolder = getSeatsReservedByKeyHolder(currentKey);

            for(int j = 0; j < seatsReservedByKeyHolder.size();j++){

                if(seatsReservedByKeyHolder.get(j).id() == null) {
                } else {
                    Access a = new Access(
                            UUID.fromString(currentKey.id()),
                            UUID.fromString(seatsReservedByKeyHolder.get(j).id()));

                    accesses.add(a);
                }
            }
        }

        return accesses;

    }





    public void setUuids(List<Room> rooms) {

        for(int i = 0; i < rooms.size(); i++) {
            seatRepository.setUuidtoRoom(
                    rooms.get(i).id(),
                    rooms.get(i).room());
        }

    }

    @Scheduled(fixedDelay =10000)
    public List<Key> fetchKeys() throws JsonProcessingException {
        System.out.println("fetching keys...");
        WebClient webClient = WebClient.create();

        List<Key> keys = new ArrayList<>();

        String jsonResponse = webClient.get()
                .uri(
                        uriBuilder -> uriBuilder
                                .scheme("http")
                                .host("localhost")
                                .port(3000)
                                .path("/key")
                                .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(jsonResponse);

        String id;
        String owner;

        for (JsonNode node : jsonNode) {
            id = node.get("id").asText();
            owner = node.get("owner").asText();
            Key r = new Key(id, owner);
            keys.add(r);
        }

        return keys;
    }

    @Scheduled(fixedDelay = 10000)
    public List<Room> fetchRooms() throws JsonProcessingException {
        System.out.println("fetching rooms...");
        WebClient webClient = WebClient.create();

        List<Room> rooms = new ArrayList<>();

        String jsonResponse = webClient.get()
                .uri(
                        uriBuilder -> uriBuilder
                                .scheme("http")
                                .host("localhost")
                                .port(3000)
                                .path("/room")
                                .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(jsonResponse);

        String id;
        String raum;

        for (JsonNode node : jsonNode) {
            id = node.get("id").asText();
            raum = node.get("raum").asText();
            Room r = new Room(id, raum);
            rooms.add(r);
        }

        return rooms;
    }
}
