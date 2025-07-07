package com.smartjob.dto;

public class PhoneDTO {
    private String numbrer;
    private String cityCode;
    private String countryCode;
    private UserDTO user;

    Integer phoneID;

    public String getNumbrer() {
        return numbrer;
    }

    public void setNumbrer(String numbrer) {
        this.numbrer = numbrer;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(Integer phoneID) {
        this.phoneID = phoneID;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
