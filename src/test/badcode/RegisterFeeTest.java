package badcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RegisterFeeTest {

    @ParameterizedTest
    @CsvSource({
            "0,500",
            "1,500",
            "3,250",
            "5,100",
            "9,50",
            "10,0"
    })
    public void getFeeWithDataTable(int experience, int fee) {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        assertEquals(fee, registerBusiness.getFee(experience));
    }
}