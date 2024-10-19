package io.mpolivaha.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
public @ConfigurationProperties(prefix = "sber.llm", value = "config") record GigaChatProperties(
    String model,
    Double frequencyPenalty,
    Integer maxTokens,
    Double presencePenalty,
    List<String> stopSequences,
    Double temperature,
    Integer topK,
    Double topP
) {

  @ConstructorBinding
  public GigaChatProperties {}
}
