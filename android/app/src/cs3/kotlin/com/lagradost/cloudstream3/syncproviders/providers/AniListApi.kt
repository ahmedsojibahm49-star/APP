package com.lagradost.cloudstream3.syncproviders.providers

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * JSON data holders that plugins embed in their OWN response models (for
 * example Anichi parses AniList "recommendations" into these). Without the
 * classes, Jackson fails while building the plugin's model with
 * `ClassNotFoundException: ...AniListApi$CoverImage`, which broke every
 * title page of those plugins.
 *
 * Only the data shapes are provided — the AniList account/sync client
 * itself does not exist in StreamBox. Property names match the real
 * classes (they are the AniList GraphQL field names); the `$stable` fields
 * exist because plugins compiled with Jetpack Compose read them.
 */
class AniListApi {
    data class Title(
        @JsonProperty("english") val english: String? = null,
        @JsonProperty("romaji") val romaji: String? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class CoverImage(
        @JsonProperty("medium") val medium: String? = null,
        @JsonProperty("large") val large: String? = null,
        @JsonProperty("extraLarge") val extraLarge: String? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class MediaTitle(
        @JsonProperty("romaji") val romaji: String? = null,
        @JsonProperty("english") val english: String? = null,
        @JsonProperty("native") val native: String? = null,
        @JsonProperty("userPreferred") val userPreferred: String? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class MediaCoverImage(
        @JsonProperty("extraLarge") val extraLarge: String? = null,
        @JsonProperty("large") val large: String? = null,
        @JsonProperty("medium") val medium: String? = null,
        @JsonProperty("color") val color: String? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class LikePageInfo(
        @JsonProperty("total") val total: Int? = null,
        @JsonProperty("currentPage") val currentPage: Int? = null,
        @JsonProperty("lastPage") val lastPage: Int? = null,
        @JsonProperty("perPage") val perPage: Int? = null,
        @JsonProperty("hasNextPage") val hasNextPage: Boolean? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class SeasonNextAiringEpisode(
        @JsonProperty("episode") val episode: Int? = null,
        @JsonProperty("timeUntilAiring") val timeUntilAiring: Int? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class RecommendedMedia(
        @JsonProperty("id") val id: Int? = null,
        @JsonProperty("title") val title: MediaTitle? = null,
        @JsonProperty("coverImage") val coverImage: MediaCoverImage? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class Recommendation(
        @JsonProperty("mediaRecommendation") val mediaRecommendation: RecommendedMedia? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class RecommendationEdge(
        @JsonProperty("node") val node: Recommendation,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class RecommendationConnection(
        @JsonProperty("edges") val edges: List<RecommendationEdge> = emptyList(),
        @JsonProperty("nodes") val nodes: List<Recommendation> = emptyList(),
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }
}
