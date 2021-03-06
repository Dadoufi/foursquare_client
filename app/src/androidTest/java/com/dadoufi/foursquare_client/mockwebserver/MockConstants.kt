package com.dadoufi.foursquare_client.mockwebserver


const val API_URL = "http://localhost:8080/"

object APIPaths {
    const val PIZZA_SEARCH =
        "http://localhost:8080/venues/search?near=amsterdam&radius=1000&limit=10&query=pizza&client_id=DWU21SJWIILG5JSWJCRVPYVJQTT4LGGI44BKN3GVSUPTPQGJ&client_secret=525VCSB2ZWO3G0XYODUZP4BHDFBYJ12Q2B5NSNPPRBXFNIS1&v=20120609"
    const val VENUE_DETAILS =
        "http://localhost:8080/venues/4a27db7bf964a52016941fe3?client_id=DWU21SJWIILG5JSWJCRVPYVJQTT4LGGI44BKN3GVSUPTPQGJ&client_secret=525VCSB2ZWO3G0XYODUZP4BHDFBYJ12Q2B5NSNPPRBXFNIS1&v=20120609"
}

object MockFiles {
    const val PIZZA_SEARCH_SUCCESS = "venues.json"
    const val VENUE_DETAILS_SUCCESS = "venue_details.json"

}