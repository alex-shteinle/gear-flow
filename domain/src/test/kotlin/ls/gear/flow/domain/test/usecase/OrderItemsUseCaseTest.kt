package ls.gear.flow.domain.test.usecase

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.repository.DemandItemRepository
import ls.gear.flow.domain.usecase.CheckConnectionUseCase
import ls.gear.flow.domain.usecase.order.OrderItemsUseCase

@RunWith(MockitoJUnitRunner::class)
class OrderItemsUseCaseTest {

    private val checkConnectionUseCase: CheckConnectionUseCase = mock()
    private val demandItemRepository: DemandItemRepository = mock()
    private val orderItemsUseCase = OrderItemsUseCase(
        checkConnectionUseCase,
        demandItemRepository
    )

    @Test
    fun return_error_when_network_is_absent() = runTest {
        Mockito.`when`(checkConnectionUseCase.invoke()).thenReturn(false)
        assertEquals(GearFlowError.Network.NoInternet, orderItemsUseCase.invoke().exceptionOrNull())
    }

    @Test
    fun return_error_when_repo_returns_error() = runTest {
        Mockito.`when`(checkConnectionUseCase.invoke()).thenReturn(true)
        Mockito
            .`when`(demandItemRepository.orderItems())
            .thenReturn(Result.failure(GearFlowError.Network.ServerError()))
        assertEquals(
            GearFlowError.Network.ServerError(),
            orderItemsUseCase.invoke().exceptionOrNull()
        )
    }

    @Test
    fun return_success_when_repo_returns_success() = runTest {
        Mockito.`when`(checkConnectionUseCase.invoke()).thenReturn(true)
        Mockito
            .`when`(demandItemRepository.orderItems())
            .thenReturn(Result.success(Unit))
        assertTrue(orderItemsUseCase.invoke().isSuccess)
    }
}
