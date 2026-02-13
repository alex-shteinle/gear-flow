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
import ls.gear.flow.domain.model.Norm
import ls.gear.flow.domain.model.NormItem
import ls.gear.flow.domain.model.PersonalSizes
import ls.gear.flow.domain.model.StuffItem
import ls.gear.flow.domain.model.StuffItemGroupType
import ls.gear.flow.domain.model.User
import ls.gear.flow.domain.repository.LoginRepository
import ls.gear.flow.domain.repository.NormRepository
import ls.gear.flow.domain.repository.PersonalSizesRepository
import ls.gear.flow.domain.repository.UserCacheRepository
import ls.gear.flow.domain.repository.UserRepository
import ls.gear.flow.domain.usecase.CheckConnectionUseCase
import ls.gear.flow.domain.usecase.user.GetRemoteUserUseCase

private const val HEAD_TEST = 55
private const val HEIGHT_TEST = 170
private const val ITEM_ID_TEST = "testId"
private const val NORM_QUANTITY_TEST = 5

@RunWith(MockitoJUnitRunner::class)
class GetRemoteUserUseCaseTest {

    private val checkConnectionUseCase: CheckConnectionUseCase = mock()
    private val loginRepository: LoginRepository = mock()
    private val userRepository: UserRepository = mock()
    private val personalSizesRepository: PersonalSizesRepository = mock()
    private val userCacheRepository: UserCacheRepository = mock()
    private val normRepository: NormRepository = mock()

    private val getRemoteUserUseCase = GetRemoteUserUseCase(
        checkConnectionUseCase,
        loginRepository,
        userRepository,
        personalSizesRepository,
        userCacheRepository,
        normRepository
    )

    @OptIn(ExperimentalStdlibApi::class)
    private val testStuffItem = StuffItem(
        id = ITEM_ID_TEST,
        name = "testItem",
        quantity = 2,
        measureUnit = "unit",
        issueDate = null,
        type = StuffItemGroupType.entries.random()
    )

    private val testUser = User(
        id = "test",
        unitOrderId = "",
        firstName = "",
        lastName = "",
        middleName = "",
        items = listOf(testStuffItem),
        sizes = PersonalSizes()
    )

    private val testNormItem = NormItem(
        itemId = ITEM_ID_TEST,
        itemName = "testItem",
        quantity = NORM_QUANTITY_TEST,
        usePeriodInMonths = 1,
        measureUnit = "",
        typeGroupName = ""
    )

    private val testNorm = Norm(
        soldierId = testUser.id,
        provision = "",
        items = listOf(testNormItem)
    )

    @Test
    fun return_error_when_network_is_absent() = runTest {
        Mockito.`when`(checkConnectionUseCase.invoke()).thenReturn(false)
        assertEquals(GearFlowError.Network.NoInternet, getRemoteUserUseCase.invoke().exceptionOrNull())
    }

    @Test
    fun return_error_when_login_repo_returns_error() = runTest {
        Mockito.`when`(checkConnectionUseCase.invoke()).thenReturn(true)
        Mockito
            .`when`(loginRepository.login())
            .thenReturn(Result.failure(GearFlowError.Network.ServerError()))
        assertEquals(
            GearFlowError.Network.ServerError(),
            getRemoteUserUseCase.invoke().exceptionOrNull()
        )
    }

    @Test
    fun return_error_when_user_repo_returns_error() = runTest {
        Mockito.`when`(checkConnectionUseCase.invoke()).thenReturn(true)
        Mockito
            .`when`(loginRepository.login())
            .thenReturn(Result.success(Unit))
        Mockito
            .`when`(userRepository.getAll())
            .thenReturn(Result.failure(GearFlowError.UserNotFound))
        assertEquals(
            GearFlowError.UserNotFound,
            getRemoteUserUseCase.invoke().exceptionOrNull()
        )
    }

    @Test
    fun return_success_when_user_repo_returns_success() = runTest {
        mockSuccessfulReposResults()
        assertTrue(getRemoteUserUseCase.invoke().isSuccess)
    }

    @Test
    fun return_updated_user_when_size_repo_returns_some_sizes() = runTest {
        mockSuccessfulReposResults()
        val updatedUser = getRemoteUserUseCase.invoke().getOrNull()
        assertEquals(HEAD_TEST, updatedUser?.sizes?.head)
        assertEquals(HEIGHT_TEST, updatedUser?.sizes?.height)
    }

    @Test
    fun return_updated_user_when_norm_repo_returns_some_norms() = runTest {
        mockSuccessfulReposResults()
        val updatedUser = getRemoteUserUseCase.invoke().getOrNull()
        assertEquals(NORM_QUANTITY_TEST, updatedUser?.items?.firstOrNull()?.normQuantity)
    }

    private suspend fun mockSuccessfulReposResults() {
        Mockito.`when`(checkConnectionUseCase.invoke()).thenReturn(true)
        Mockito
            .`when`(loginRepository.login())
            .thenReturn(Result.success(Unit))
        val sizes = PersonalSizes(head = HEAD_TEST, height = HEIGHT_TEST)
        Mockito
            .`when`(userRepository.getAll())
            .thenReturn(Result.success(listOf(testUser)))
        Mockito
            .`when`(personalSizesRepository.getByUserId(testUser.id))
            .thenReturn(Result.success(sizes))
        Mockito
            .`when`(normRepository.getByUserId(testUser.id))
            .thenReturn(Result.success(testNorm))
    }
}
