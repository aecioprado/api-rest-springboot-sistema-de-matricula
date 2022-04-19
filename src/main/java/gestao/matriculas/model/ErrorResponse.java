package gestao.matriculas.model;

import lombok.Builder;
import lombok.Getter;

// cria o objeto de retorno sempre que houver uma exceção
@Builder // evite usar em classes com serializable
@Getter
public class ErrorResponse {
	private String mensagem;
	private int httpStatus;
	private long timeStamp;

}
