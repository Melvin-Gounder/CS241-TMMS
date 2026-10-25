package fj.usp.tmms.config;
import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.security.test.context.support.WithMockUser;import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest @AutoConfigureMockMvc class RoleAccessIntegrationTest{
 @Autowired MockMvc mvc;
 @Test void publicTrafficIsOpen() throws Exception{mvc.perform(get("/traffic")).andExpect(status().isOk());}
 @Test @WithMockUser(roles="PUBLIC") void publicUserCannotOpenAdmin() throws Exception{mvc.perform(get("/admin")).andExpect(status().isForbidden());}
 @Test @WithMockUser(roles="ADMIN") void adminCanOpenAdmin() throws Exception{mvc.perform(get("/admin")).andExpect(status().isOk());}
}
