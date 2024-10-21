package com.pilog.plontology.repository.apex;

import com.pilog.plontology.model.apex.MtrlPpoTermAdvanced;
import com.pilog.plontology.payloads.SimpleCharacteristicDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacteristicsRepository extends JpaRepository<MtrlPpoTermAdvanced,String>{


    @Query("SELECT new com.pilog.plontology.payloads.SimpleCharacteristicDto(M.clazz, M.conceptId) FROM MtrlPpoTermAdvanced M " +
            "WHERE UPPER(M.clazz) LIKE UPPER(:class) " +
            "GROUP BY M.clazz, M.conceptId " +
            "ORDER BY M.clazz, M.conceptId")
    List<SimpleCharacteristicDto> findByClass(@Param("class") String clazz);

    @Query(value = "SELECT DISTINCT PROPERTY FROM PPO_VISION_TEMPLATES WHERE CLASS = :class", nativeQuery = true)
    List<String> findPropertiesByClass(@Param("class") String clazz);
}
