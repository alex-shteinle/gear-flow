package ls.gear.flow.domain.test.usecase

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.usecase.pincode.MatchPinCodesUseCase
import ls.gear.flow.domain.usecase.pincode.ValidatePinCodeUseCase

@RunWith(MockitoJUnitRunner::class)
class MatchPinCodeUseCaseTest {

    private val validatePinCodeUseCase: ValidatePinCodeUseCase = mock()
    private val matchPinCodesUseCase = MatchPinCodesUseCase(validatePinCodeUseCase)

    @Test
    fun return_error_if_some_pincode_is_invalid() {
        assertEquals(
            GearFlowError.InvalidPinCode,
            matchPinCodesUseCase("1111", "11", 4).exceptionOrNull()
        )
    }

    @Test
    fun return_error_if_pincodes_are_different() {
        val firstPin = "1111"
        val secondPin = "2222"
        Mockito
            .`when`(validatePinCodeUseCase.invoke(4, firstPin, secondPin))
            .thenReturn(true)
        assertEquals(
            GearFlowError.PinCodesDidNotMatch,
            matchPinCodesUseCase(firstPin, secondPin, 4).exceptionOrNull()
        )
    }

    @Test
    fun return_success_if_pincodes_are_same() {
        val firstPin = "1111"
        val secondPin = "1111"
        Mockito
            .`when`(validatePinCodeUseCase.invoke(4, firstPin, secondPin))
            .thenReturn(true)
        assertTrue(matchPinCodesUseCase(firstPin, secondPin, 4).isSuccess)
    }
}
