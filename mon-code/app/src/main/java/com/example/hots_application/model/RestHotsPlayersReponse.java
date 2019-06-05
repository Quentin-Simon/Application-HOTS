package com.example.hots_application.model;

import java.util.List;

public class RestHotsPlayersReponse {

    private Integer count;
    private List<HotsPlayers> results;

    public List<HotsPlayers> getResults() {
        return results;
    }
}
