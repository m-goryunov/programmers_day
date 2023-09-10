package ru.yandex.programmers_day;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {
    String email;
    String cohort;
    String firstName;
    String lastName;
}
