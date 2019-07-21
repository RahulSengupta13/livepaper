package me.rahulsengupta.network.endpoints.unsplash.responses

import java.io.Serializable

data class Collections(
    val id: Int,
    val title: String?,
    val description: String?
) : Serializable