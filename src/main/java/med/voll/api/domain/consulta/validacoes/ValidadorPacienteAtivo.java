package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private PacienteRepository pacienteRepository;
    public void validar(DadosAgendamentoConsulta dados){
        var idPaciente = dados.idMedico();
        if(idPaciente == null){
            return;
        }
        var pacienteAtivo = pacienteRepository.findByAtivoTrue(dados.idPaciente());
        if(pacienteAtivo.equals(Boolean.FALSE)){
            throw new ValidacaoException("A consulta não agendada com um paciente desligado");
        }

    }
}
