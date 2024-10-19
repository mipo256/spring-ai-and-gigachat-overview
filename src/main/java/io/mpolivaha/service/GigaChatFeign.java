package io.mpolivaha.service;

import io.mpolivaha.service.request.PromptRequest;
import io.mpolivaha.service.response.AccessToken;
import io.mpolivaha.service.response.Model;
import io.mpolivaha.service.response.ModelResponse;
import io.mpolivaha.service.response.ObjectResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient
public interface GigaChatFeign {

  /**
   * Receive an access token for a given authorization token (which is a Sber's fancy name for refresh token)
   */
  @PostMapping(
      path = "https://ngw.devices.sberbank.ru:9443/api/v2/oauth",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  ResponseEntity<AccessToken> getAccessToken(@RequestHeader("Authorization") String auth);

  /**
   * Receive an access token for a given authorization token (which is a Sber's fancy name for refresh token)
   */
  @PostMapping(
      path = "https://gigachat.devices.sberbank.ru/api/v1/models",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  ResponseEntity<ObjectResponse<List<Model>>> getModels(@RequestHeader("Authorization") String auth);

  /**
   * Receive an access token for a given authorization token (which is a Sber's fancy name for refresh token)
   */
  @PostMapping(
      path = "https://gigachat.devices.sberbank.ru/api/v1/chat/completions",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  ResponseEntity<ModelResponse> prompt(
      @RequestHeader("Authorization") String auth,
      @RequestBody PromptRequest promptRequest
  );
}
