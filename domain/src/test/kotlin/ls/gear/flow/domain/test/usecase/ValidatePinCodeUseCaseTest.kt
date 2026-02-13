package ls.gear.flow.domain.test.usecase

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ls.gear.flow.domain.usecase.pincode.ValidatePinCodeUseCase

@RunWith(MockitoJUnitRunner::class)
class ValidatePinCodeUseCaseTest {

    private val validatePinCodeUseCase = ValidatePinCodeUseCase()

    @Test
    fun return_false_on_empty_pincode() {
        assertFalse(validatePinCodeUseCase(4, ""))
    }

    @Test
    fun return_false_if_any_pincode_is_empty() {
        assertFalse(validatePinCodeUseCase(4, "1111", ""))
    }

    @Test
    fun return_false_on_too_short_pincode() {
        assertFalse(validatePinCodeUseCase(4, "111"))
    }

    @Test
    fun return_false_if_any_pincode_is_too_short() {
        assertFalse(validatePinCodeUseCase(4, "1111", "111"))
    }

    @Test
    fun return_false_on_too_long_pincode() {
        assertFalse(validatePinCodeUseCase(4, "11111"))
    }

    @Test
    fun return_false_if_any_pincode_is_too_long() {
        assertFalse(validatePinCodeUseCase(4, "11111", "1111"))
    }

    @Test
    fun return_false_on_pincode_with_not_numbers() {
        assertFalse(validatePinCodeUseCase(4, "111a"))
    }

    @Test
    fun return_false_if_any_pincode_is_has_not_numbers() {
        assertFalse(validatePinCodeUseCase(4, "1111", "111a"))
    }

    @Test
    fun return_true_on_pincode_with_same_length_and_numbers() {
        assertTrue(validatePinCodeUseCase(4, "1111"))
    }

    @Test
    fun return_true_if_all_pincodes_have_same_length_and_only_numbers() {
        assertTrue(validatePinCodeUseCase(4, "1111", "2222", "1765"))
    }
}
