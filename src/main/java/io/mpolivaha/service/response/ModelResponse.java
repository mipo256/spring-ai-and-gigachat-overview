package io.mpolivaha.service.response;

public record ModelResponse(
    Long created,
    String model,
    String object,
    Usage usage
) {

  record Usage(
      Integer completion_tokens,
      Integer prompt_tokens,
      Integer system_tokens,
      Integer total_tokens
  ) {
  }
}
