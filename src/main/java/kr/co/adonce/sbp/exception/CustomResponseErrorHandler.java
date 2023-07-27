package kr.co.adonce.sbp.exception;

import java.io.IOException;

import org.apache.http.auth.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class CustomResponseErrorHandler implements ResponseErrorHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomResponseErrorHandler.class);

	@Override
	public void handleError(ClientHttpResponse clienthttpresponse) throws IOException {

		if (clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {
			logger.debug(HttpStatus.FORBIDDEN + " response. Throwing authentication exception");
			try {
				throw new AuthenticationException();
			} catch (AuthenticationException e) {
				logger.error("Authentication Exception.");
			}
		}
	}

	@Override
	public boolean hasError(ClientHttpResponse clienthttpresponse) throws IOException {

		if (clienthttpresponse.getStatusCode() != HttpStatus.OK) {
			logger.debug("Status code: " + clienthttpresponse.getStatusCode());
			logger.debug("Response" + clienthttpresponse.getStatusText());
			// logger.debug(clienthttpresponse.getBody());

			if (clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {
				logger.debug("Call returned a error 403 forbidden resposne ");
				return true;
			}
		}
		return false;
	}
}
