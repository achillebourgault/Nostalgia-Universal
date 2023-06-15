package com.nostalgia;

public class PlayerPreferences {

    private String lang;

    //TODO: Fetch API to retrieve player preferences
    public PlayerPreferences() {
        //TODO: Remove this when API is implemented
        this.lang = "en";
    }

    public String getLang() {
        return lang;
    }
}
