package gestao.matriculas.model;

import lombok.Builder;
import lombok.Getter;


@Builder // evite usar em classes com serializable
@Getter
public class ErrorResponse {
	private String mensagem;
	private int httpStatus;
	private long timeStamp;

}
