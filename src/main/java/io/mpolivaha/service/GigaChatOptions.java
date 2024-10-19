package io.mpolivaha.service;

import io.mpolivaha.config.GigaChatProperties;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.ChatOptions;

@RequiredArgsConstructor
public class GigaChatOptions implements ChatOptions {

  private final GigaChatProperties gigaChatProperties;

  @Override
  public String getModel() {
    return gigaChatProperties.model();
  }

  @Override
  public Double getFrequencyPenalty() {
    return gigaChatProperties.frequencyPenalty();
  }

  @Override
  public Integer getMaxTokens() {
    return gigaChatProperties.maxTokens();
  }

  @Override
  public Double getPresencePenalty() {
    return gigaChatProperties.presencePenalty();
  }

  @Override
  public List<String> getStopSequences() {
    return gigaChatProperties.stopSequences();
  }

  @Override
  public Double getTemperature() {
    return gigaChatProperties.temperature();
  }

  @Override
  public Integer getTopK() {
    return gigaChatProperties.topK();
  }

  @Override
  public Double getTopP() {
    return gigaChatProperties.topP();
  }

  @Override
  public ChatOptions copy() {
    return new GigaChatOptions(gigaChatProperties);
  }
}
