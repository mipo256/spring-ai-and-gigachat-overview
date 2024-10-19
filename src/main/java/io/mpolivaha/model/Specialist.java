package io.mpolivaha.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Getter
@Setter
@Accessors(chain = true)
public class Specialist {

  @Id
  private Long id;

  private String firstName;

  private String lastName;

  private String middleName;

  private int yearsOfExperience;

  private SpecialistType specialistType;

  private LocalDate employedFrom;

  private LocalDate employedUntil;

  private TimeRange openHours;
}
