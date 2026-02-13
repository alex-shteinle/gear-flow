package ls.gear.flow.ui.screen.main.home.personalsizes.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch

@Composable
fun SizeNumberPicker(
    startIndex: Int,
    sizes: ImmutableList<String>,
    itemHeight: Dp,
    onSelected: (Int) -> Unit
) {
    val state = rememberLazyListState(startIndex)
    val scope = rememberCoroutineScope()
    var currentIndex by remember { mutableStateOf(startIndex) }

    VerticalWheelPicker(
        modifier = Modifier.fillMaxWidth(),
        state = state,
        count = sizes.size,
        itemHeight = itemHeight,
        visibleItemCount = 3,
        onScrollFinish = {
            currentIndex = it
            onSelected(it)
        }
    ) { index ->
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .clickable { scope.launch { state.animateScrollToItem(index) } },
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.fillMaxHeight(),
                text = sizes[index],
                color = if (index == currentIndex) Color.Black else Color.Gray,
                fontWeight = if (index == currentIndex) FontWeight.Bold else FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        }
    }
}
