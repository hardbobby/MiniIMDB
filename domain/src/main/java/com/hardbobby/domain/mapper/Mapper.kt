package com.hardbobby.domain.mapper

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
interface Mapper<Source, Destination> {
    fun mapFromSource(source: Source): Destination

}