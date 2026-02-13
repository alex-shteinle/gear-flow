package ls.gear.flow.domain.test.usecase

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import ls.gear.flow.domain.model.PersonalSizes
import ls.gear.flow.domain.model.StuffItem
import ls.gear.flow.domain.model.StuffItemGroupType
import ls.gear.flow.domain.model.User
import ls.gear.flow.domain.repository.UserCacheRepository
import ls.gear.flow.domain.usecase.item.GetItemGroupsUseCase
import ls.gear.flow.domain.usecase.user.GetLocalUserUseCase

class GetItemGroupsUseCaseTest {

    private val items = listOf(
        createStuffItem(1, StuffItemGroupType.HEADWEAR),
        createStuffItem(2, StuffItemGroupType.UNIFORM),
        createStuffItem(3, StuffItemGroupType.FOOTWEAR),
        createStuffItem(4, StuffItemGroupType.EQUIPMENT)
    )

    private val mockedUser = User(
        id = "",
        unitOrderId = "",
        firstName = "",
        lastName = "",
        middleName = "",
        items = items,
        sizes = PersonalSizes()
    )

    private val mockedUserWithOneTypeItems = mockedUser.copy(
        items = items.map { it.copy(type = StuffItemGroupType.EQUIPMENT) }
    )

    private fun createStuffItem(id: Int, type: StuffItemGroupType): StuffItem {
        return StuffItem(
            id = id.toString(),
            name = "",
            quantity = 1,
            measureUnit = "",
            issueDate = null,
            type = type
        )
    }

    @Test
    fun return_empty_list_if_there_is_no_items() {
        val userCacheRepository = mock<UserCacheRepository> {
            on { get() } doReturn mockedUser.copy(items = emptyList())
        }
        val getLocalUserUseCase = GetLocalUserUseCase(userCacheRepository)
        assertTrue(GetItemGroupsUseCase(getLocalUserUseCase).invoke().isEmpty())
    }

    @Test
    fun return_one_group_if_all_items_has_same_typeGroupName() {
        val userCacheRepository = mock<UserCacheRepository> {
            on { get() } doReturn mockedUserWithOneTypeItems
        }
        val getLocalUserUseCase = GetLocalUserUseCase(userCacheRepository)
        assertEquals(1, GetItemGroupsUseCase(getLocalUserUseCase).invoke().size)
        assertEquals(StuffItemGroupType.EQUIPMENT, GetItemGroupsUseCase(getLocalUserUseCase).invoke().first().type)
    }

    @Test
    fun return_same_number_groups_as_number_of_items_with_different_typeGroupName() {
        val userCacheRepository = mock<UserCacheRepository> {
            on { get() } doReturn mockedUser
        }
        val getLocalUserUseCase = GetLocalUserUseCase(userCacheRepository)
        assertEquals(items.size, GetItemGroupsUseCase(getLocalUserUseCase).invoke().size)
    }
}
