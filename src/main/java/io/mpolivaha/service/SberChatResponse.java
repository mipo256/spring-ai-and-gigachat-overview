package io.mpolivaha.service;

import java.util.List;
import org.springframework.ai.chat.prompt.ChatOptions;

public abstract class SberChatResponse implements ChatOptions {

  @Override
  public Double getFrequencyPenalty() {
    return null;
  }

  @Override
  public Integer getMaxTokens() {
    return null;
  }

  @Override
  public Double getPresencePenalty() {
    return null;
  }

  @Override
  public List<String> getStopSequences() {
    return null;
  }

  @Override
  public Double getTemperature() {
    return null;
  }

  @Override
  public Integer getTopK() {
    return null;
  }

  @Override
  public Double getTopP() {
    return null;
  }

  @Override
  public ChatOptions copy() {
    return null;
  }
}
