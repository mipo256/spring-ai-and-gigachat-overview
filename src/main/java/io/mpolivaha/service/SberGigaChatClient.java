package io.mpolivaha.service;

import io.mpolivaha.service.response.AccessToken;
import io.mpolivaha.service.response.Model;
import io.mpolivaha.service.response.ObjectResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SberGigaChatClient implements ChatModel {

  private final GigaChatFeign gigaChatFeign;
  private final @Value("${sber.auth.token}") String authToken;
  private final @Value("${sber.model:use-pro}") Boolean usePro;

  private String accessToken;

  public String requestToken() {
    ResponseEntity<AccessToken> accessToken = gigaChatFeign.getAccessToken(authToken);
    checkStatus(accessToken);
    return accessToken.getBody().accessToken();
  }

  public List<Model> getModels() {
    ResponseEntity<ObjectResponse<List<Model>>> models = gigaChatFeign.getModels(authToken);
    checkStatus(models);
    return models.getBody().data();
  }

  @Override
  public ChatResponse call(Prompt prompt) {
    return null;
  }

  @Override
  public ChatOptions getDefaultOptions() {
    return null;
  }

  private static void checkStatus(ResponseEntity<?> accessToken) {
    if (!accessToken.getStatusCode().is2xxSuccessful()) {
      throw new IllegalStateException("Received '%d' status code for call".formatted(accessToken.getStatusCode().value()));
    }
  }
}
