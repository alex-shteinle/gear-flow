package ls.gear.flow.ui.screen.main.auth.pincodelogin.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ls.gear.flow.R
import ls.gear.flow.ui.screen.main.auth.PinConst

private const val KEYPAD_MAX = 9
private const val KEYPAD_CELL_COUNT = 3

@Composable
fun PinCodeKeypad(
    hasBiometric: Boolean,
    onNumberClick: (Int) -> Unit,
    onBackSpaceClick: () -> Unit,
    onBioMetricClick: () -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(KEYPAD_CELL_COUNT),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val keys = provideKeys(hasBiometric = hasBiometric)
        items(keys.size) {
            Box(modifier = Modifier.aspectRatio(1f)) {
                when (val pinKey = keys[it]) {
                    is PinKey.Number -> PinCodeButton(
                        onClick = { onNumberClick(pinKey.value) }
                    ) {
                        Text(
                            text = pinKey.value.toString(),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 36.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    PinKey.BackSpace -> PinCodeButton(
                        onClick = onBackSpaceClick
                    ) {
                        PinKeyIcon(
                            painterRes = R.drawable.ic_backspace,
                            iconSize = 24.dp,
                        )
                    }
                    PinKey.Biometric -> PinCodeButton(
                        onClick = onBioMetricClick
                    ) {
                        PinKeyIcon(
                            painterRes = R.drawable.ic_touch_id,
                            iconSize = 32.dp,
                        )
                    }
                    PinKey.EmptySpace -> Spacer(modifier = Modifier.size(PinConst.PIN_KEY_SIZE.dp))
                }
            }
        }
    }
}

private fun provideKeys(hasBiometric: Boolean): List<PinKey> {
    val keys: MutableList<PinKey> = (1..KEYPAD_MAX)
        .map { PinKey.Number(it) }
        .toMutableList()
    return keys.apply {
        add(if (hasBiometric) PinKey.Biometric else PinKey.EmptySpace)
        add(PinKey.Number(0))
        add(PinKey.BackSpace)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFAFAFC)
@Composable
fun PinCodeKeypadPreview() {
    PinCodeKeypad(
        hasBiometric = true,
        onNumberClick = {},
        onBackSpaceClick = {},
        onBioMetricClick = {}
    )
}
