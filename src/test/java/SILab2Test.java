import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    @Test
    void everyPathTest() {
        RuntimeException ex;
        //1,2, 3, 22
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(null, null));
        assertTrue(ex.getMessage().contains("The user is not instantiated"));

        //1,2, 4, 5, 22
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(new User(null, null, "nebitno"), null));
        assertTrue(ex.getMessage().contains("The user is missing some mandatory information"));

        //1,2, 4, 6, 7, 22
        assertFalse(SILab2.function(new User("jovan", "jovan*", "nebitno"), null));

        //1,2, 4, 6, 8, 9, 22
        assertFalse(SILab2.function(new User("username", "jovan*", "nebitno"), null));

        //1,2, 4, 6, 8, 10, 11.1, 11.2, 19, 20, 22 - ne e mozno

        //1,2, 4, 6, 8, 10, 11.1, 11.2, 19, 21, 22 - ne e mozno

        //1,2, 4, 6, 8, 10, 11.1, (site slucai kade sto passwordot ima dolzina pogolema od 8 no nema da gi ispolni uslovite za validen password), 19, 20, 22
        assertFalse(SILab2.function(new User("username", "invalidpassword", "nebitno"), null)); // nothing
        assertFalse(SILab2.function(new User("username", "1nval1dpassword", "nebitno"), null)); // only digit
        assertFalse(SILab2.function(new User("username", "InvalidPassword", "nebitno"), null)); // only upper
        assertFalse(SILab2.function(new User("username", "inv@lidp@ssword", "nebitno"), null)); // only special
        assertFalse(SILab2.function(new User("username", "1Nval1dPassword", "nebitno"), null)); // digit and upper
        assertFalse(SILab2.function(new User("username", "1nv@l1dp@ssword", "nebitno"), null)); // digit and special
        assertFalse(SILab2.function(new User("username", "Inv@lidP@ssword", "nebitno"), null)); // upper and special

        //1,2, 4, 6, 8, 10, 11.1, (11.2, 12, 13, 14, 15, 16, 17, 18, 11.3), 19, 21, 22
        assertTrue(SILab2.function(new User("username", "P@ssword123", "nebitno"), null));
    }

    @Test
    void multipleConditions() {
        RuntimeException ex;
        //user.getUsername() == null || user.getPassword() == null , 4
        //T || X
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(new User(null, null, "nebitno"), null));
        assertTrue(ex.getMessage().contains("The user is missing some mandatory information"));

        //F || T
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(new User("username", null, "nebitno"), null));
        assertTrue(ex.getMessage().contains("The user is missing some mandatory information"));

        //F || F
        assertTrue(SILab2.function(new User("username", "P@ssword123", "nebitno"), null));

        //!digit || !upper || !special , 19
        //T || X || X
        assertFalse(SILab2.function(new User("username", "invalidpassword", "nebitno"), null)); // nothing

        //F || T || X
        assertFalse(SILab2.function(new User("username", "1nval1dpassword", "nebitno"), null)); // only digit

        //F || F || T
        assertFalse(SILab2.function(new User("username", "1Nval1dPassword", "nebitno"), null)); // digit and upper

        //F || F || F
        assertTrue(SILab2.function(new User("username", "P@ssword123", "nebitno"), null));
    }
}