package pt.org.upskill.controller;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.dto.DTOlists;
import pt.org.upskill.dto.KeyValueDTO;

import java.util.List;

public class FacilityController {

    public List<KeyValueDTO<Integer>> dtoList() {
        return new DTOlists().facilityDTOList();
    }
}
