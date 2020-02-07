package com.example.carbuddy;

public class Service {
    private String nameService;
    private String locationService;
    private String mailService;
    private String phoneService;

    public Service(String nameService, String locationService, String mailService, String phoneService) {
        this.nameService = nameService;
        this.locationService = locationService;
        this.mailService = mailService;
        this.phoneService = phoneService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public void setLocationService(String locationService) {
        this.locationService = locationService;
    }

    public void setMailService(String mailService) {
        this.mailService = mailService;
    }

    public void setPhoneService(String phoneService) {
        this.phoneService = phoneService;
    }

    public String getNameService() {
        return nameService;
    }

    public String getLocationService() {
        return locationService;
    }

    public String getMailService() {
        return mailService;
    }

    public String getPhoneService() {
        return phoneService;
    }


}
