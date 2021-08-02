package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterBusinessFailureTest {

    @Test
    @DisplayName("Firstname ของ speaker เป็น null")
    public void case01() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Exception exception = assertThrows(ArgumentNullException.class, () ->
                registerBusiness.register(null, new Speaker()));
        assertEquals("First name is required.", exception.getMessage());
    }

    @Test
    @DisplayName("Lastname ของ speaker เป็น null")
    public void case02() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("Supoj");
        Exception exception = assertThrows(ArgumentNullException.class, () ->
                registerBusiness.register(null, speaker));
        assertEquals("Last name is required.", exception.getMessage());
    }

    @Test
    @DisplayName("Email ของ speaker เป็น null")
    public void case03() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("Supoj");
        speaker.setLastName("Ploy");
        Exception exception = assertThrows(ArgumentNullException.class, () ->
                registerBusiness.register(null, speaker));
        assertEquals("Email is required.", exception.getMessage());
    }

    @Test
    @DisplayName("ไม่สามารถ save speaker ลง database")
    public void case04() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("Supoj");
        speaker.setLastName("Ploy");
        speaker.setEmail("supoj.pl@gmail.com");
        Exception exception = assertThrows(SaveSpeakerException.class, () ->
                registerBusiness.register(null, speaker)
        );
        assertEquals("Can't save a speaker.", exception.getMessage());
    }

    @Test
    @DisplayName("Email ไม่ถูก format")
    public void case05() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("Supoj");
        speaker.setLastName("Ploy");
        speaker.setEmail("supoj.pl");
        Exception exception = assertThrows(DomainEmailInvalidException.class, () ->
                registerBusiness.register(null, speaker));
        assertNull(exception.getMessage());
    }

    @Test
    @DisplayName("Domain ของ Email ไม่ support")
    public void case06() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("Supoj");
        speaker.setLastName("Ploy");
        speaker.setEmail("supoj.pl@kbtg.tech");
        Exception exception = assertThrows(SpeakerDoesntMeetRequirementsException.class, () ->
                registerBusiness.register(null, speaker));
        assertEquals("Speaker doesn't meet our standard rules.", exception.getMessage());
    }
}