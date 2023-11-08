package com.example.myapplication

import java.io.Serializable

class Detail : Serializable {
    /**
     * Title : Guardians of the Galaxy Vol. 2
     * Year : 2017
     * Rated : PG-13
     * Released : 05 May 2017
     * Runtime : 136 min
     * Genre : Action, Adventure, Comedy
     * Director : James Gunn
     * Writer : James Gunn, Dan Abnett, Andy Lanning
     * Actors : Chris Pratt, Zoe Saldana, Dave Bautista
     * Plot : The Guardians struggle to keep together as a team while dealing with their personal family issues, notably Star-Lord's encounter with his father, the ambitious celestial being Ego.
     * Language : English
     * Country : United States
     * Awards : Nominated for 1 Oscar. 15 wins & 60 nominations total
     * Poster : https://m.media-amazon.com/images/M/MV5BNjM0NTc0NzItM2FlYS00YzEwLWE0YmUtNTA2ZWIzODc2OTgxXkEyXkFqcGdeQXVyNTgwNzIyNzg@._V1_SX300.jpg
     * Ratings : [{"Source":"Internet Movie Database","Value":"7.6/10"},{"Source":"Rotten Tomatoes","Value":"85%"},{"Source":"Metacritic","Value":"67/100"}]
     * Metascore : 67
     * imdbRating : 7.6
     * imdbVotes : 738,822
     * imdbID : tt3896198
     * Type : movie
     * DVD : 10 Jul 2017
     * BoxOffice : $389,813,101
     * Production : N/A
     * Website : N/A
     * Response : True
     */
    var Title: String? = null
    var Year: String? = null
    var Rrated: String? = null
    var Genre: String? = null
    var Released: String? = null
    var Runtime: String? = null
    var Qenre: String? = null
    var Director: String? = null
    var Writer: String? = null
    var Actors: String? = null
    var Plot: String? = null
    var Language: String? = null
    var Country: String? = null
    var Awards: String? = null
    var Poster: String? = null
    var Metascore: String? = null
    var imdbRating: String? = null
    var imdbVotes: String? = null
    var imdbID: String? = null
    var type: String? = null
    var dVD: String? = null
    var boxOffice: String? = null
    var production: String? = null
    var website: String? = null
    var Response: String? = null
    var ratings: List<RatingsBean>? = null

    class RatingsBean : Serializable {
        /**
         * Source : Internet Movie Database
         * Value : 7.6/10
         */
        var source: String? = null
        var value: String? = null
    }
}