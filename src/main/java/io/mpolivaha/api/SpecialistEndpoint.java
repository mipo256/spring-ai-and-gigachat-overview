package io.mpolivaha.api;

import io.mpolivaha.api.dto.request.PromptedRequestDto;
import io.mpolivaha.api.dto.response.SpecialistTypeDto;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/specialist/v1.0")
public class SpecialistEndpoint {

  private ChatClient chatClient;

  @GetMapping(path = "/type")
  public ResponseEntity<SpecialistTypeDto> getMostRelevantSpecialist(PromptedRequestDto promptedRequestDto) {
    throw new UnsupportedOperationException();
  }
}
