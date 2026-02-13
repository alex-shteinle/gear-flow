package ls.gear.flow.ui.screen.main.home.groupdetail

import androidx.lifecycle.SavedStateHandle
import ls.gear.flow.domain.model.StuffItemGroupType
import ls.gear.flow.domain.usecase.item.GetItemGroupByTypeUseCase
import ls.gear.flow.ext.enumValueOfOrNull
import ls.gear.flow.navigation.graph.GraphConst
import ls.gear.flow.ui.screen.base.BaseViewModel
import ls.gear.flow.ui.screen.main.home.groupdetail.state.GroupDetailAction
import ls.gear.flow.ui.screen.main.home.groupdetail.state.GroupDetailState

class ItemGroupDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val getItemGroupByTypeUseCase: GetItemGroupByTypeUseCase,
) : BaseViewModel<GroupDetailAction, GroupDetailState>(
    initialValue = GroupDetailState()
) {
    private val groupName = savedStateHandle.get<String>(GraphConst.KEY_GROUP_TYPE).orEmpty()
    private val groupType: StuffItemGroupType =
        enumValueOfOrNull<StuffItemGroupType>(groupName) ?: StuffItemGroupType.ALL_PROPERTY

    init {
        dispatch(GroupDetailAction.GetGroupInfo(groupType))
    }

    override suspend fun reduce(action: GroupDetailAction) = when (action) {
        is GroupDetailAction.GetGroupInfo -> getGroupInfo(action.type)
    }

    private fun getGroupInfo(type: StuffItemGroupType) =
        GroupDetailState(group = getItemGroupByTypeUseCase(type))
}
