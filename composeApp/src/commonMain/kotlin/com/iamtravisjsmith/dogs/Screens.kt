package com.iamtravisjsmith.dogs

import kotlinx.serialization.Serializable

@Serializable
data object DogGrid

@Serializable
data class DogDetails(val imageUrl: String)
