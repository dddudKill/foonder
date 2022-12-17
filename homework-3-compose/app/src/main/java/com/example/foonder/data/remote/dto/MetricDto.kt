package com.example.foonder.data.remote.dto

import com.example.foonder.domain.models.Metric

data class MetricDto(
    val amount: Double,
    val unitLong: String,
    val unitShort: String
) {
    fun toMetric(): Metric {
        return Metric(
            amount = amount,
            unitLong = unitLong,
            unitShort = unitShort
        )
    }
}