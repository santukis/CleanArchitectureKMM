package com.santukis.navigation.movies.arguments

import com.santukis.navigation.DestinationArgumentsMapper

class MoviesArgumentMapper : DestinationArgumentsMapper {

    override fun <Result : Any> map(from: Result): Any = from

}