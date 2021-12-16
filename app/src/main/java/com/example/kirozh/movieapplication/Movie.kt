package com.example.kirozh.movieapplication

/**
 * @author Kirill Ozhigin on 13.12.2021
 */
data class Movie(
    var display_title: String = "",
    var mpaa_rating: String = "",
    var critics_pick: Int = 0,
    var byline: String = "",
    var headline: String = "",
    var summary_short: String = "",
    var publication_date: String = "",
    var opening_date: String = "",
    var date_updated: String = "",
    var link: Link,
    var multimedia: Multimedia
)

data class Link(
    var type: String = "",
    var url: String = "",
    var suggested_link_text: String = ""
)

data class Multimedia(
    var type: String = "",
    var src: String = "",
    var height: Int = 0,
    var width: Int = 0
)