package ls.gear.flow.ui.screen.main.home.order.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderItem(
    val userItemId: String,
    val name: String,
    val measureUnit: String,
    val checked: Boolean,
    val availableQuantity: Int,
    val selectedQuantity: Int,
    val typeGroupName: String
) : Parcelable {
    val showButtons: Boolean get() = checked && availableQuantity > 1
    val canBeIncreased: Boolean get() = checked && selectedQuantity < availableQuantity
    val canBeDecreased: Boolean get() = checked && selectedQuantity > 0
    val selectedQuantityWithMeasureUnit: String get() = "$selectedQuantity $measureUnit"
}
