package ls.gear.flow.ui.screen.main.auth.touchid

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ls.gear.flow.R
import ls.gear.flow.ui.theme.GearFlowTheme

@Composable
fun TouchIdDialog(onDialogButtonClick: (Boolean) -> Unit) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_touch_id),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 48.dp, height = 48.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.should_use_touch_id_title),
                    fontSize = 24.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.should_use_touch_id_description),
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                TouchDialogButton(
                    stringId = R.string.enable_touch_id,
                    isAccented = true,
                    onClick = { onDialogButtonClick(true) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TouchDialogButton(
                    stringId = R.string.not_now,
                    isAccented = false,
                    onClick = { onDialogButtonClick(false) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TouchDialogPreview() {
    GearFlowTheme {
        TouchIdDialog { }
    }
}
