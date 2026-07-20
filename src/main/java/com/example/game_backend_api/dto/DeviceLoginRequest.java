package com.example.game_backend_api.dto;


import jakarta.validation.constraints.NotBlank;

public class DeviceLoginRequest
{

    @NotBlank(message = "Device ID cannot be empty")
    private String deviceId;

    @NotBlank(message = "Username cannot be empty")
    private String username;

    public DeviceLoginRequest() {
    }

    public DeviceLoginRequest(String deviceId, String username) {
        this.deviceId = deviceId;
        this.username = username;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}