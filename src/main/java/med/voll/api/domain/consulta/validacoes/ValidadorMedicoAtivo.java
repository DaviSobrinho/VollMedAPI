package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private MedicoRepository medicoRepository;
    public void validar(DadosAgendamentoConsulta dados){
        var idMedico = dados.idMedico();
        if(idMedico == null){
            return;
        }
        var medicoAtivo = medicoRepository.findByAtivoTrue(dados.idMedico());
        if(medicoAtivo.equals(Boolean.FALSE)){
            throw new ValidacaoException("A consulta não pode ser agendada com esse médico");
        }

    }
}
