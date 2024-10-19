package io.mpolivaha.service.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record PromptRequest(
    String model,
    List<Message> messages,
    int n,
    Boolean stream,
    @JsonProperty("max_tokens") Integer maxTokens,
    @JsonProperty("repetition_penalty") Double repetitionPenalty,
    @JsonProperty("update_interval") Integer updateInterval
) {

  public record Message(
      String role,
      String content
  ) { }
}
