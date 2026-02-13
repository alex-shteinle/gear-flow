package ls.gear.flow.ui.screen.main.home.groupdetail.state

import ls.gear.flow.domain.model.StuffItemGroupType

sealed class GroupDetailAction {
    data class GetGroupInfo(val type: StuffItemGroupType) : GroupDetailAction()
}
