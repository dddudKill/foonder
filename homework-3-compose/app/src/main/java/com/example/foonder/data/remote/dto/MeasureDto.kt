package com.example.foonder.data.remote.dto

import com.example.foonder.domain.models.Measure

data class MeasureDto(
    val metric: MetricDto,
    val us: UsDto
) {
    fun toMeasure(): Measure {
        return Measure(
            metric = metric.toMetric(),
            us = us.toUs()
        )
    }
}