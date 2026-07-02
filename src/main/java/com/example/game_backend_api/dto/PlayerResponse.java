package com.example.game_backend_api.dto;

public class PlayerResponse
{
    private Long id ;
    private String userName;
    private String userEmail;
    private String userPassword;

    PlayerResponse(Long id, String userName, String userEmail, String userPassword)
    {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
