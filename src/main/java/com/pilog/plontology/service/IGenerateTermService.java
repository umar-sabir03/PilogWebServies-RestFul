package com.pilog.plontology.service;

import com.pilog.plontology.payloads.GenerateTermDTO;
import com.pilog.plontology.payloads.GenerateTermDrDTO;
import com.pilog.plontology.payloads.TaxonomyOperationRequestDto;

import java.util.List;

public interface IGenerateTermService {
    String generateConceptId(TaxonomyOperationRequestDto taxonomyOperationRequestDto);

    List<GenerateTermDTO> generateConceptReg(TaxonomyOperationRequestDto taxonomyOperationRequestDto);

    List<GenerateTermDTO> generateConceptApp(TaxonomyOperationRequestDto taxonomyOperationRequestDto);

    List<GenerateTermDTO> generateConceptPropertyReg(TaxonomyOperationRequestDto taxonomyOperationRequestDto);

    List<GenerateTermDTO> generateConceptPropertyApp(TaxonomyOperationRequestDto taxonomyOperationRequestDto);

    List<GenerateTermDTO> generateConceptDr(TaxonomyOperationRequestDto taxonomyOperationRequestDto);

    List<GenerateTermDTO> ConceptDelete(TaxonomyOperationRequestDto taxonomyOperationRequestDto);

    List<GenerateTermDTO> ConceptPropDelete(TaxonomyOperationRequestDto taxonomyOperationRequestDto);

    List<GenerateTermDTO> ConceptPropModify(TaxonomyOperationRequestDto taxonomyOperationRequestDto);

    List<Object[]> generateConceptTxmnyProcess(TaxonomyOperationRequestDto taxonomyOperationRequestDto);

    GenerateTermDrDTO getDtDrChangesUpdate(TaxonomyOperationRequestDto taxonomyOperationRequestDto);

    List<GenerateTermDrDTO> generateConceptDrProtyApp(TaxonomyOperationRequestDto taxonomyOperationRequestDto);

    List<GenerateTermDrDTO> generateConceptDrClassApp(TaxonomyOperationRequestDto taxonomyOperationRequestDto);

    List<GenerateTermDrDTO> getNewDridInsertion(List<GenerateTermDrDTO> resultList);

    List<String> getIrdi(TaxonomyOperationRequestDto taxonomyOperationRequestDto);

    List<String> getDrid(TaxonomyOperationRequestDto taxonomyOperationRequestDto);

    List<GenerateTermDTO> generateConceptClass(TaxonomyOperationRequestDto taxonomyOperationRequestDto);
}
