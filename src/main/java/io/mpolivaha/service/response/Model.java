package io.mpolivaha.service.response;

import java.util.Objects;

public record Model(String id, String object, String ownedBy) {

  String getName() {
    return this.id;
  }

  boolean isPro() {
    return Objects.equals(getName(), "GigaChat-Pro");
  }
}
