package com.pilog.plontology.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponseDTO {
    private List<PropResponse> otherLangResponseList ;
    private List<PropResponse> repResponseList ;
}

