package org.sarthak.sample.Modals;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class ErrorResponse {

    private Utilities.STATUS status;
    private String reason;
}

