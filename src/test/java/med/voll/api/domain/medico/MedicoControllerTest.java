/*package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.endereco.Endereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
class MedicoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    MedicoRepository medicoRepository;
    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosCadastroMedicoJacksonTester;
    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJacksonTester;
    @Test
    @DisplayName("Deveria devolver o código http 200 quando as informações estiverem válidas")
    @WithMockUser
    void cadastraMedicoCenario1() throws Exception {
        var dadosCadastroMedico = new DadosCadastroMedico("Davi","davi@pop.com","90909090",
                "123456",Especialidade.CARDIOLOGIA, Endereco());
        var medico = new Medico(dadosCadastroMedico);
        when(medicoRepository.save(any())).thenReturn(new Medico(dadosCadastroMedico));
        var response = mockMvc.perform(post("/medicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroMedicoJacksonTester.write(dadosCadastroMedico).getJson()))
                .andReturn().getResponse();
        var dadosDetalhamento = new DadosDetalhamentoMedico(
                null,
                dadosCadastroMedico.nome(),
                dadosCadastroMedico.email(),
                dadosCadastroMedico.crm(),
                dadosCadastroMedico.telefone(),
                dadosCadastroMedico.especialidade(),
                new Endereco(dadosCadastroMedico.endereco())
        );
        var jsonEsperado = dadosDetalhamentoMedicoJacksonTester.write(dadosDetalhamento).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private DadosEndereco Endereco() {
        return new DadosEndereco(
                "a","b","12345678","Brasilia","df","",""
        );
    }
}*/
