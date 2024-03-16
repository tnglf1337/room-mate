package com.roommate.applicationService;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {

    private SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }


    public boolean seatWithEquipment(String desiredSeat, String[] desiredEquipment) {
        if(desiredSeat == null) return true;

        int x = 0;
        boolean[] found = new boolean[desiredEquipment.length];

        for(int i = 0; i < desiredEquipment.length; i++) {
            List<String> temp = seatRepository.findSeatsByEquipment(desiredEquipment[i]);

            for(int j = 0; j < temp.size(); j++) {
                if(desiredSeat.equals(temp.get(j))) {
                    found[i] = true;
                }
            }
        }

        for(int i = 0; i < found.length; i++) {
            if(found[i]) {
                x++;
            }
        }

        if(x == found.length) {
            return true;
        } else  {
            return false;
        }
    }

    public List<String> getSeatsWithEquipment(String[] desiredEquipment) {
        //desiredEquipment = "HDMI-Anschluss,USB3,Drucker

        List<String> dummyList = new ArrayList<>();

        for(int i = 0; i < desiredEquipment.length; i++) {
            List<String> temp = seatRepository.findSeatsByEquipment(desiredEquipment[i]);
            dummyList.addAll(temp);
        }

        int counter = 0;
        List<String> actualList = new ArrayList<>();

        for(int i = 0; i < dummyList.size(); i++) {
            String currentSeat = dummyList.get(i);

            for(int j = 0; j < dummyList.size(); j++) {
                if(currentSeat.equals(dummyList.get(j))) {
                    counter++;
                }
            }

            if(counter == desiredEquipment.length && actualList.contains(currentSeat) == false) {
                actualList.add(currentSeat);

            }
            counter = 0;
        }

        return actualList;
    }

}
