package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorAntecedenciaMinimaConsulta implements ValidadorAgendamentoDeConsulta {
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        if(Duration.between(agora,dataConsulta).toMinutes() < 30){

            throw new ValidacaoException("A consulta deve ser agendada com antecedência mínima de 30 minutos.");

        }
    }
}
