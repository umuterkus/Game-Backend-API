package com.example.game_backend_api.dto;

public class CreatePlayerRequest
{
    private String username;
    private String password;
    private String email;

    public CreatePlayerRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // GET
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
