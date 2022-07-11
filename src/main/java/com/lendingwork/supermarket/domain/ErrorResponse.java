package com.lendingwork.supermarket.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {
    private static final long serialVersionUID = 224467434214L;

    Integer errorCode;
    String errorDescription;
}
