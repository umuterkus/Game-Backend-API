package com.example.game_backend_api.dto;

public class UpdatePlayerRequest
{
    public String email;
    public String username;
    public String password;

    public UpdatePlayerRequest(String username, String password, String email)
    {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
