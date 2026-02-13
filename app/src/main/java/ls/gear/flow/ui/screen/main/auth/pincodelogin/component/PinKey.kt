package ls.gear.flow.ui.screen.main.auth.pincodelogin.component

sealed class PinKey {
    data class Number(val value: Int) : PinKey()
    object BackSpace : PinKey()
    object Biometric : PinKey()
    object EmptySpace : PinKey()
}
