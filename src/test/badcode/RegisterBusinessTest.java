package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterBusinessTest {

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

    @Test
    @DisplayName("Save speaker with return 1")
    public void case07() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("Supoj");
        speaker.setLastName("Ploy");
        speaker.setEmail("supoj.pl@gmail.com");

        SpeakerRepository speakerRepository = new SpeakerRepository() {
            @Override
            public Integer saveSpeaker(Speaker speaker) {
                return 1;
            }
        };

        assertEquals(1, registerBusiness.register(speakerRepository, speaker));
    }

    @Test
    @DisplayName("Set experience year <= 1, return fee 500")
    public void case08() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setExp(1);
        assertEquals(500, registerBusiness.getFee(1));
    }

    @Test
    @DisplayName("Set experience year > 1 && <= 3, return fee 250")
    public void case09() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        assertEquals(250, registerBusiness.getFee(3));
    }

    @Test
    @DisplayName("Set experience year > 3 && <= 5, return fee 100")
    public void case10() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        assertEquals(100, registerBusiness.getFee(5));
    }

    @Test
    @DisplayName("Set experience year > 5 && <= 9, return fee 50")
    public void case11() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        assertEquals(50, registerBusiness.getFee(9));
    }
}