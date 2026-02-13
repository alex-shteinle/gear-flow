package ls.gear.flow.navigation

sealed class NavOptionType {
    object Default : NavOptionType()
    object ClearStack : NavOptionType()
    object BottomItem : NavOptionType()
}
