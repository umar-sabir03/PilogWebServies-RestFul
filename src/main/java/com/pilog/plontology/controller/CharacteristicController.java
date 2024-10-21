package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.CharacteristicERP;
import com.pilog.plontology.payloads.CharacteristicRequest;
import com.pilog.plontology.service.ICharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/WSRep/Content/Characteristic")
public class CharacteristicController {

    @Autowired
    private ICharacteristicService characteristicService;
    @PostMapping("/searchCharacteristic")
    public ResponseEntity<List<CharacteristicERP>> searchCharacteristic(@RequestBody CharacteristicRequest request) {
        if (request.getOrgnId() == null) {
            request.setOrgnId("E6EE49F8383C494098B18D06C64DDFF0");
        }

        String itemRef = Optional.ofNullable(request.getClasss()).orElse("");
        String property = Optional.ofNullable(request.getProperty()).orElse("");
        String orgnId = request.getOrgnId();

        request.setClasss(itemRef);
        request.setProperty(property);
        request.setOrgnId(orgnId);

        List<CharacteristicERP> characteristicERPS = characteristicService.duplicateRecords(request);

        return new ResponseEntity<>(characteristicERPS, HttpStatus.OK);
    }

}
