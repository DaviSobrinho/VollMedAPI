package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;
    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("ID de paciente não existe!");
        }
        if (!medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("ID de médico não existe!");
        }
        validadores.forEach(v -> v.validar(dados));
        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null,medico,paciente,dados.data());
        if(medico == null){
            throw new ValidacaoException("Não existem médicos disoponíveis nessa data");
        }
        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }
    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null){
            throw new ValidacaoException("Especilidade é obrigatória quando o médico não for escolhido");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),dados.data());
    }

    public void cancelar(DadosDetalhamentoConsulta dados) {
        if (!consultaRepository.existsByIdAndPacienteIdAndMedicoIdAndData(dados.id(), dados.idPaciente(), dados.idMedico(), dados.data())) {
            throw new ValidacaoException("Não existe consulta agendada com os dados informados");
        }
        consultaRepository.deleteById(dados.id());
    }
}
