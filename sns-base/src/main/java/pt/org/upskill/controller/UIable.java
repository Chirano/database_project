package pt.org.upskill.controller;

import pt.org.upskill.dto.DTO;

public interface UIable {
    void register(DTO dto);
    boolean confirm();
}
