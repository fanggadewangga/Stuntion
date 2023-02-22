package com.killjoy.stuntion.features.presentation.utils

import android.content.Context
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.killjoy.stuntion.features.domain.model.zscore.ZScoreStandard
import com.killjoy.stuntion.features.presentation.utils.zscore_util.HeightCategory
import com.killjoy.stuntion.features.presentation.utils.zscore_util.WeightCategory
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter

fun Modifier.dashedBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

        this.then(
            Modifier.drawWithCache {
                onDrawBehind {
                    val stroke = Stroke(
                        width = strokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )

                    drawRoundRect(
                        color = color,
                        style = stroke,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }
        )
    }
)

fun countZScoreByWeight(
    context: Context,
    birthDate: String,
    gender: String,
    weight: Double,
): String {
    var zScore: Double? = null
    val bufferedReader = BufferedReader(context.assets.open("BBU$gender.csv").reader())
    val csvParser = CSVParser.parse(
        bufferedReader,
        CSVFormat.DEFAULT
    )

    val currentDate = DateTimeFormatter.ofPattern("MM/dd/yyy").format(LocalDateTime.now())
    val period = Period.between(
        LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")),
        LocalDate.parse(currentDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"))
    )

    val year = period.years
    val month = period.months
    val ageInMonth = (year * 12) + month

    csvParser.forEachIndexed { index, it ->
        it?.let {
            val standard = ZScoreStandard(
                age = if (index == 0) 0 else it.get(0).toInt(),
                low3SD = it.get(1).toDouble(),
                low2SD = it.get(2).toDouble(),
                low1SD = it.get(3).toDouble(),
                median = it.get(4).toDouble(),
                high1SD = it.get(5).toDouble(),
                high2SD = it.get(6).toDouble(),
                high3SD = it.get(7).toDouble()
            )

            if (standard.age == ageInMonth) {
                val bbMedian = weight - standard.median
                zScore = if (bbMedian > 0) {
                    bbMedian / (standard.high1SD - standard.median)
                } else {
                    bbMedian / (standard.median - standard.low1SD)
                }
            }
        }
    }
    if (zScore != null) {
        if (zScore!! < -3.0) return WeightCategory.SeverelyStunted.description
        else if (zScore!! >= -3.0 && zScore!! < -2.0) return WeightCategory.Stunted.description
        else if (zScore!! >= -2.0 && zScore!! < 3.0) return WeightCategory.Normal.description
        else if (zScore!! > 3.0) return WeightCategory.High.description
    }

    return WeightCategory.Undefined.description
}

fun countZScoreByHeight(
    context: Context,
    birthDate: String,
    gender: String,
    height: Double,
): String {
    var zScore: Double? = null
    val bufferedReader = BufferedReader(context.assets.open("TBU$gender.csv").reader())
    val csvParser = CSVParser.parse(
        bufferedReader,
        CSVFormat.DEFAULT
    )

    val currentDate = DateTimeFormatter.ofPattern("MM/dd/yyyy").format(LocalDateTime.now())
    val period = Period.between(
        LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")),
        LocalDate.parse(currentDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"))
    )

    val year = period.years
    val month = period.months
    val ageInMonth = (year * 12) + month

    csvParser.forEachIndexed { index, it ->
        it?.let {
            val standard = ZScoreStandard(
                age = if (index == 0) 0 else it.get(0).toInt(),
                low3SD = it.get(1).toDouble(),
                low2SD = it.get(2).toDouble(),
                low1SD = it.get(3).toDouble(),
                median = it.get(4).toDouble(),
                high1SD = it.get(5).toDouble(),
                high2SD = it.get(6).toDouble(),
                high3SD = it.get(7).toDouble()
            )

            if (standard.age == ageInMonth) {
                val bbMedian = height - standard.median
                zScore = if (bbMedian > 0) {
                    bbMedian / (standard.high1SD - standard.median)
                } else {
                    bbMedian / (standard.median - standard.low1SD)
                }
            }
        }
    }

    if (zScore != null) {
        if (zScore!! < -3.0) return HeightCategory.SeverelyUnderweight.description
        else if (zScore!! >= -3.0 && zScore!! < -2.0) return HeightCategory.Underweight.description
        else if (zScore!! >= -2.0 && zScore!! < 1.0) return HeightCategory.Normal.description
        else if (zScore!! > 1.0) return HeightCategory.High.description
    }

    return HeightCategory.Undefined.description
}

fun countPeriod(
    startDate: String,
    finishDate: String? = null,
    datePattern: String = "MM/dd/yyy",
    showMonth: Boolean = false,
    showDay: Boolean = false,
): Int {
    val currentDate = DateTimeFormatter.ofPattern(datePattern).format(LocalDateTime.now())
    var dayPeriod = Period.between(
        LocalDate.parse(startDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")),
        LocalDate.parse(currentDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"))
    )

    if (finishDate != null) {
        dayPeriod = Period.between(
            LocalDate.parse(startDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")),
            LocalDate.parse(finishDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"))
        )
    }

    return if (showMonth) dayPeriod.months
    else if (showDay) dayPeriod.days
    else dayPeriod.years
}
fun countIdealWeight(birthDate: String): Double {
    val ageInYear = countPeriod(birthDate)
    val ageInMonth = (ageInYear * 12) + countPeriod(birthDate, showMonth = true)


    if (ageInMonth in 3..12)
        return (ageInMonth + 9) / 2.0
    else if (ageInMonth in 13..72)
        return (ageInYear * 2.0 + 8)
    return 0.0
}