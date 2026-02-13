package ls.gear.flow.ui.theme

import android.os.Build
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import ls.gear.flow.R

// Width: 151, GRAD: 0, Slant: 0, XTRA: 468, XOPQ: 96, YOPQ: 79,
// YTLC: 514, YTUC: 666, YTAS: 750, YTDE: -203, YTFI: 738
@OptIn(ExperimentalTextApi::class)
val robotoFlexFontFamily = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    FontFamily(
        Font(
            R.font.roboto_flex_variable,
            variationSettings = FontVariation.Settings(
                FontVariation.weight(800),
                FontVariation.slant(0f),
                FontVariation.width(151f),
                FontVariation.Setting("YTAS", 750f),
                FontVariation.Setting("XTRA", 468f),
                FontVariation.Setting("YTDE", -203f),
                FontVariation.Setting("YTFI", 738f),
                FontVariation.Setting("GRAD", 0f),
                FontVariation.Setting("YTLC", 514f),
                FontVariation.Setting("YOPQ", 79f),
                FontVariation.Setting("YTUC", 666f)
            )
        )
    )
} else {
    FontFamily(
        Font(R.font.roboto_flex_static)
    )
}
