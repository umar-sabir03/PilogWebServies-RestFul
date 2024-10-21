package com.pilog.plontology.service.impl;

import com.pilog.plontology.exception.BadRequestException;
import com.pilog.plontology.payloads.ImageRequest;
import com.pilog.plontology.payloads.ImageResponse;
import com.pilog.plontology.repository.apex.OrgTerminologyRepository;
import com.pilog.plontology.service.ISearchImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SearchImageServiceImpl implements ISearchImageService {
    @Autowired
    private OrgTerminologyRepository orgTerminologyRepository;
    @Override
    public ImageResponse show(ImageRequest request) {
        ImageResponse response = new ImageResponse();

       if(request.getTerm()==null || request.getTerm().isEmpty())
           throw new BadRequestException(HttpStatus.BAD_REQUEST,"class cannot b empty","invalid.class");

       String image = orgTerminologyRepository.getImageByClass(request.getTerm());
       response.setBase64(image);

        return response;
    }
}
