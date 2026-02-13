package ls.gear.flow.ui.screen.main.home.personalsizes.state

import ls.gear.flow.R
import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.model.PersonalSizes
import ls.gear.flow.domain.model.TypicalSize
import ls.gear.flow.ui.screen.base.Message
import ls.gear.flow.util.ext.toEmptyIfNotPositive
import ls.gear.flow.ext.toIntOrZero

fun PersonalSizes.toSizeState(error: GearFlowError? = null, message: Message? = null) = SizesState(
    height = height.toEmptyIfNotPositive(),
    sleeve = sleeve.toEmptyIfNotPositive(),
    chest = chest.toEmptyIfNotPositive(),
    waist = waist.toEmptyIfNotPositive(),
    head = head.toEmptyIfNotPositive(),
    neck = neck.toEmptyIfNotPositive(),
    shoe = shoe.toEmptyIfNotPositive(),
    insole = insole.toEmptyIfNotPositive(),
    uniform = uniform,
    error = error,
    message = message
)

fun SizesState.toPersonalSizes() = PersonalSizes(
    height = height.toIntOrZero(),
    sleeve = sleeve.toIntOrZero(),
    chest = chest.toIntOrZero(),
    waist = waist.toIntOrZero(),
    head = head.toIntOrZero(),
    neck = neck.toIntOrZero(),
    shoe = shoe.toIntOrZero(),
    insole = insole.toIntOrZero(),
    uniform = uniform,
)

fun TypicalSize.toSizeAction(newValue: String) = when (this) {
    TypicalSize.Chest -> SizesAction.Update.Chest(newValue)
    TypicalSize.Head -> SizesAction.Update.Head(newValue)
    TypicalSize.Height -> SizesAction.Update.Height(newValue)
    TypicalSize.Insole -> SizesAction.Update.Insole(newValue)
    TypicalSize.Neck -> SizesAction.Update.Neck(newValue)
    TypicalSize.Shoe -> SizesAction.Update.Shoe(newValue)
    TypicalSize.Sleeve -> SizesAction.Update.Sleeve(newValue)
    TypicalSize.Uniform -> SizesAction.Update.Uniform(newValue)
    TypicalSize.Waist -> SizesAction.Update.Waist(newValue)
}

fun SizesState.sizeByTypical(typicalSize: TypicalSize) = when (typicalSize) {
    TypicalSize.Chest -> UiSize(R.string.chest, chest, typicalSize, R.string.cm)
    TypicalSize.Head -> UiSize(R.string.head, head, typicalSize, R.string.cm)
    TypicalSize.Height -> UiSize(R.string.height, height, typicalSize, R.string.cm)
    TypicalSize.Insole -> UiSize(R.string.insole, insole, typicalSize, R.string.mm)
    TypicalSize.Neck -> UiSize(R.string.neck, neck, typicalSize, R.string.cm)
    TypicalSize.Shoe -> UiSize(R.string.footwear, shoe, typicalSize)
    TypicalSize.Sleeve -> UiSize(R.string.sleeve, sleeve, typicalSize, R.string.cm)
    TypicalSize.Uniform -> UiSize(R.string.uniform, uniform, typicalSize)
    TypicalSize.Waist -> UiSize(R.string.waist, waist, typicalSize, R.string.cm)
}
