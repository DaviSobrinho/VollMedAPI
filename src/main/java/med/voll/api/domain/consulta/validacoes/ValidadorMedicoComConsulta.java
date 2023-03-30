package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComConsulta implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiConsulta = consultaRepository.existsByMedicoIdAndData(dados.idPaciente(), dados.data());
        if(medicoPossuiConsulta){
            throw new ValidacaoException("O Médico já possui uma consulta agendada para esse horário");
        }
    }
}
