package io.mpolivaha.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.DefaultChatClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfiguration {

  public DefaultChatClient defaultChatClient() {
    ChatClient.builder()
  }
}
