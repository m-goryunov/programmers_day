package ru.yandex.programmers_day;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Register {
    String name;
    String gitHubUrl;
    List<User> participants = new ArrayList<>();

}
