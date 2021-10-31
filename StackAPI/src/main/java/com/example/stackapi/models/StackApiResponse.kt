package com.example.stackapi.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StackApiResponse(
    @Json(name = "has_more")
    val hasMore: Boolean,
    @Json(name = "items")
    val items: List<Item>,
    @Json(name = "quota_max")
    val quotaMax: Int,
    @Json(name = "quota_remaining")
    val quotaRemaining: Int
) {
    @JsonClass(generateAdapter = true)
    data class Item(
        @Json(name = "accepted_answer_id")
        val acceptedAnswerId: Int?,
        @Json(name = "answer_count")
        val answerCount: Int,
        @Json(name = "closed_date")
        val closedDate: Int?,
        @Json(name = "closed_reason")
        val closedReason: String?,
        @Json(name = "content_license")
        val contentLicense: String?,
        @Json(name = "creation_date")
        val creationDate: Int,
        @Json(name = "is_answered")
        val isAnswered: Boolean,
        @Json(name = "last_activity_date")
        val lastActivityDate: Int,
        @Json(name = "last_edit_date")
        val lastEditDate: Int?,
        @Json(name = "link")
        val link: String,
        @Json(name = "owner")
        val owner: Owner,
        @Json(name = "question_id")
        val questionId: Int,
        @Json(name = "score")
        val score: Int,
        @Json(name = "tags")
        val tags: List<String>,
        @Json(name = "title")
        val title: String,
        @Json(name = "view_count")
        val viewCount: Int
    ) {
        @JsonClass(generateAdapter = true)
        data class Owner(
            @Json(name = "accept_rate")
            val acceptRate: Int?,
            @Json(name = "display_name")
            val displayName: String,
            @Json(name = "link")
            val link: String,
            @Json(name = "profile_image")
            val profileImage: String,
            @Json(name = "reputation")
            val reputation: Int,
            @Json(name = "user_id")
            val userId: Int,
            @Json(name = "user_type")
            val userType: String
        )
    }
}