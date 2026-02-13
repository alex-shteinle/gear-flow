package ls.gear.flow.domain.test.usecase

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.model.User
import ls.gear.flow.domain.repository.DemandItemRepository
import ls.gear.flow.domain.usecase.CheckConnectionUseCase
import ls.gear.flow.domain.usecase.order.GetDemandItemsUseCase
import ls.gear.flow.domain.usecase.user.GetLocalUserUseCase

@RunWith(MockitoJUnitRunner::class)
class GetDemandItemsUseCaseTest {

    private val getLocalUserUseCase: GetLocalUserUseCase = mock()
    private val checkConnectionUseCase: CheckConnectionUseCase = mock()
    private val demandItemRepository: DemandItemRepository = mock()
    private val getDemandItemsUseCase = GetDemandItemsUseCase(
        getLocalUserUseCase,
        checkConnectionUseCase,
        demandItemRepository
    )

    private val user: User = mock {
        on { id } doReturn "test"
    }

    @Test
    fun return_error_when_user_is_absent() = runTest {
        Mockito.`when`(getLocalUserUseCase.invoke()).thenReturn(null)
        assertEquals(GearFlowError.UserNotFound, getDemandItemsUseCase.invoke().exceptionOrNull())
    }

    @Test
    fun return_error_when_network_is_absent() = runTest {
        Mockito.`when`(getLocalUserUseCase.invoke()).thenReturn(user)
        Mockito.`when`(checkConnectionUseCase.invoke()).thenReturn(false)
        assertEquals(GearFlowError.Network.NoInternet, getDemandItemsUseCase.invoke().exceptionOrNull())
    }

    @Test
    fun return_error_when_repo_returns_error() = runTest {
        Mockito.`when`(getLocalUserUseCase.invoke()).thenReturn(user)
        Mockito.`when`(checkConnectionUseCase.invoke()).thenReturn(true)
        Mockito
            .`when`(demandItemRepository.fetchAvailableItems(user.id))
            .thenReturn(Result.failure(GearFlowError.Network.ServerError()))
        assertEquals(
            GearFlowError.Network.ServerError(),
            getDemandItemsUseCase.invoke().exceptionOrNull()
        )
    }

    @Test
    fun return_success_when_repo_returns_success() = runTest {
        Mockito.`when`(getLocalUserUseCase.invoke()).thenReturn(user)
        Mockito.`when`(checkConnectionUseCase.invoke()).thenReturn(true)
        Mockito
            .`when`(demandItemRepository.fetchAvailableItems(user.id))
            .thenReturn(Result.success(emptyList()))
        assertTrue(getDemandItemsUseCase.invoke().isSuccess)
    }
}
