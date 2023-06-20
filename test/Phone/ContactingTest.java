package Phone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContactingTest {
    Contacting contacting;
    @BeforeEach void setUp(){
        contacting = new Contacting("firstName", "lastName", "phoneNumber");
    }
    @Test public void objectExist(){assertNotNull(contacting);}
    @Test public void contactCanBeEdited(){
        contacting.edit("firstName", "Dele", "phoneNumber");
        String result= """
                Dele
                firstName
                phoneNumber
                """;
        assertEquals(result, contacting.showDetails());
    }

}
