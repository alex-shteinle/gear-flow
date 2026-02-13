package ls.gear.flow.domain.test.usecase

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.usecase.pincode.CheckPinCodeExistsUseCase

@RunWith(MockitoJUnitRunner::class)
class CheckPinCodeExistsUseCaseTest {

    @Test
    fun return_false_on_error() = runTest {
        val getPinCodeUseCase = { Result.failure<String>(GearFlowError.PinCodeUnsaved) }
        assertFalse(CheckPinCodeExistsUseCase(getPinCodeUseCase).invoke())
    }

    @Test
    fun return_false_on_empty_pincode() = runTest {
        val getPinCodeUseCase = { Result.success("") }
        assertFalse(CheckPinCodeExistsUseCase(getPinCodeUseCase).invoke())
    }

    @Test
    fun return_true_on_existing_pincode() = runTest {
        val getPinCodeUseCase = { Result.success("1") }
        assertTrue(CheckPinCodeExistsUseCase(getPinCodeUseCase).invoke())
    }
}
