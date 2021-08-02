package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterBusinessSuccessTest {

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
}