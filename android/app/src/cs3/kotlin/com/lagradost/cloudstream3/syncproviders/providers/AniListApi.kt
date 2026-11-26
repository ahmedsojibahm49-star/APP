package com.lagradost.cloudstream3.syncproviders.providers

/**
 * JSON data holders that plugins embed in their OWN response models (for
 * example Anichi parses AniList "recommendations" into these). Without the
 * classes, Jackson fails while building the plugin's model with
 * `ClassNotFoundException: ...AniListApi$CoverImage`, which broke every
 * title page of those plugins.
 *
 * Only the data shapes are provided — the AniList account/sync client
 * itself does not exist in StreamBox. Property names match the real
 * classes (they ARE the AniList GraphQL field names, which is all Jackson's
 * Kotlin module needs — no annotations, so this file has no compile-time
 * dependency beyond the standard library); the `$stable` fields exist
 * because plugins compiled with Jetpack Compose read them.
 */
class AniListApi {
    data class Title(
        val english: String? = null,
        val romaji: String? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class CoverImage(
        val medium: String? = null,
        val large: String? = null,
        val extraLarge: String? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class MediaTitle(
        val romaji: String? = null,
        val english: String? = null,
        val native: String? = null,
        val userPreferred: String? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class MediaCoverImage(
        val extraLarge: String? = null,
        val large: String? = null,
        val medium: String? = null,
        val color: String? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class LikePageInfo(
        val total: Int? = null,
        val currentPage: Int? = null,
        val lastPage: Int? = null,
        val perPage: Int? = null,
        val hasNextPage: Boolean? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class SeasonNextAiringEpisode(
        val episode: Int? = null,
        val timeUntilAiring: Int? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class RecommendedMedia(
        val id: Int? = null,
        val title: MediaTitle? = null,
        val coverImage: MediaCoverImage? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class Recommendation(
        val mediaRecommendation: RecommendedMedia? = null,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class RecommendationEdge(
        val node: Recommendation,
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }

    data class RecommendationConnection(
        val edges: List<RecommendationEdge> = emptyList(),
        val nodes: List<Recommendation> = emptyList(),
    ) {
        companion object {
            const val `$stable`: Int = 0
        }
    }
}
