package io.mpolivaha.service;

import io.mpolivaha.config.GigaChatProperties;
import io.mpolivaha.service.request.PromptRequest;
import io.mpolivaha.service.request.PromptRequest.Message;
import io.mpolivaha.service.response.AccessToken;
import io.mpolivaha.service.response.Model;
import io.mpolivaha.service.response.ModelResponse;
import io.mpolivaha.service.response.ObjectResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
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

  private final GigaChatProperties gigaChatProperties;

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
    String token = Optional
        .ofNullable(accessToken)
        .filter(s -> !s.isEmpty())
        .orElseGet(this::requestToken);

    ResponseEntity<ModelResponse> response = gigaChatFeign.prompt(accessToken, new PromptRequest(
            getDefaultOptions().getModel(),
            List.of(
                new Message(MessageType.USER.getValue(), prompt.getContents())
            ),
            1,
            false,
            getDefaultOptions().getMaxTokens(),
            getDefaultOptions().getFrequencyPenalty(),
            0
        )
    );

    checkStatus(response);

    // TODO
    return new ChatResponse(
        List.of(
            new Generation(
                new AssistantMessage("")
            )
        ),
        new ChatResponseMetadata()
    );
  }

  @Override
  public ChatOptions getDefaultOptions() {
    return new GigaChatOptions(gigaChatProperties);
  }

  private static void checkStatus(ResponseEntity<?> accessToken) {
    if (!accessToken.getStatusCode().is2xxSuccessful()) {
      throw new IllegalStateException("Received '%d' status code for call".formatted(accessToken.getStatusCode().value()));
    }
  }
}
