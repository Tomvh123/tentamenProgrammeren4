package com.example.jeffr.tentamenprogrammeren4.service;

/**
 * Created by tom on 16-6-2017.
 */

public class Config {
    private static final String BASIC_URL = "https://tentamenprog4.herokuapp.com";

    public static final String URL_LOGIN = BASIC_URL + "/api/v1/login";
    public static final String URL_Movies = BASIC_URL + "/api/v1/films/film";
    public static final String URL_MoviesId = BASIC_URL + "/api/v1/films/";
    public static final String URL_REGISTER = BASIC_URL + "/api/v1/register";

    public static final String URL_RENTALMOVIES = BASIC_URL + "/api/v1/rentals/";
}
