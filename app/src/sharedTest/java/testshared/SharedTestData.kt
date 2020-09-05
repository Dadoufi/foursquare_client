package testshared

import com.dadoufi.foursquare_client.data.model.*

object SharedTestData {

    val venueSearchResponseTestData = VenueSearchResponse(
        meta = Meta(200, "someId"),
        response = Response(
            true,
            mutableListOf(
                VenuesItem(
                    name = "New York Pizza",
                    location = Location(
                        mutableListOf(
                            "Damstraat 24",
                            "1012 JM Amsterdam",
                            "Nederland"
                        )
                    ),
                    id = "4a27db7bf964a52016941fe3"
                ),
                VenuesItem(
                    name = "Cooler venue",
                    location = Location(mutableListOf("address3", "address2", "address1")),
                    id = "id2"
                )
            )
        )
    )

    val venueDetailResponseTestData = VenueDetailResponse(
        meta = Meta(200, "someId"),
        response = DetailResponse(
            Venue(
                id = "4a27db7bf964a52016941fe3",
                name = "New York Pizza",
                rating = 6.6,
                description = "New York Pizza is al sinds de oprichting in 1993 dé smaakmaker van Nederland",
                contact = Contact(
                    twitter = "new_york_pizza",
                    formattedPhone = "+31 20 422 2123",
                    instagram = "newyorkpizza_nl"
                ),
                location = Location(
                    mutableListOf(
                        "Damstraat 24",
                        "1012 JM Amsterdam",
                        "Nederland"
                    )
                ),
                bestPhoto = BestPhoto(
                    id = "5db9db65fa06e10008a9e6c4",
                    prefix = "https://fastly.4sqi.net/img/general/",
                    suffix = "/77182479_BLUvlCvvRkrDEa5OWtcceib0TCqJtG27euDmlRBgv3k.jpg",
                    width = 1440,
                    height = 1920
                )
            )
        )
    )

}