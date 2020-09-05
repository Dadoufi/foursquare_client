package com.dadoufi.foursquare_client

import com.dadoufi.foursquare_client.data.model.*

object SharedTestData {

    val venueSearchResponseTestData = VenueSearchResponse(
        meta = Meta(200, "someId"),
        response = Response(
            true,
            mutableListOf(
                VenuesItem(
                    name = "Cool venue",
                    location = Location(mutableListOf("address1", "address2", "address3")),
                    id = "id1"
                ),
                VenuesItem(
                    name = "Cooler venue",
                    location = Location(mutableListOf("address3", "address2", "address1")),
                    id = "id2"
                )
            )
        )
    )

    val venueFailedSearchResponseTestData = VenueSearchResponse(
        meta = Meta(400, "someId"),
        response = Response(false, mutableListOf())
    )

    val venueDetailResponseTestData = VenueDetailResponse(
        meta = Meta(200, "someId"),
        response = DetailResponse(
            Venue(
                id = "id1",
                name = "Cool Venue",
                rating = 5.5,
                description = "cool description",
                photos = Photos(1, mutableListOf()),
                contact = Contact(
                    twitter = "twitterName",
                    phone = "+3164273212",
                    formattedPhone = "+31 64 273 212",
                    facebook = "45354345",
                    facebookName = "cool_venue",
                    facebookUsername = "cool_venue",
                    instagram = "cool_venue"
                ),
                location = Location(mutableListOf("address1", "address2", "address3")),
                bestPhoto = BestPhoto(
                    id = "234234",
                    prefix = "prefix",
                    suffix = "suffix",
                    width = 720,
                    height = 460
                )
            )
        )
    )

//    val venueFailedDetailResponseTestData = VenueDetailResponse(
//        meta = Meta(400, "someId"),
//        response = DetailResponse()
//    )

}