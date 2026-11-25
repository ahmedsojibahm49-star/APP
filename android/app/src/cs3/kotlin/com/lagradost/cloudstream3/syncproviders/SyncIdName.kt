package com.lagradost.cloudstream3.syncproviders

/**
 * Tracker identifiers. Plugins only name these (e.g. "this title is
 * AniList id 123"); StreamBox has no account sync, so nothing acts on them.
 * Mirrors the real enum's constants.
 */
enum class SyncIdName {
    Anilist,
    MyAnimeList,
    Kitsu,
    Trakt,
    Imdb,
    Simkl,
    LocalList,
}
