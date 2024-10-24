package com.foto.ai.domain.model

data class Offer(
    val title: String,
    val fee: Int,
    //val planType: PlanType,
    val features: List<Feature>,
) {
    enum class PlanType {
        MONTHLY,
        YEARLY
    }

    data class Feature(
        val title: String,
        val description: String,
        //val icon: Int,
        val isEnhanced: Boolean,
    )
}