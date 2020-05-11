package org.sarthak.sample.Modals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FetchLead {

    private Integer id;
    private String first_name;
    private String last_name;
    private String mobile;
    private String email;
    private Utilities.LOCATION location_type;
    private String location_string;
    private Utilities.STATUS status;
}