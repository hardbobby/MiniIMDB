package com.hardbobby.domain.mapper

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
class ImageMovieUrlMapper : Mapper<String, String> {

    override fun mapFromSource(source: String): String {
        return "https://image.tmdb.org/t/p/w500${source}"
    }
}