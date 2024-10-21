package com.pilog.plontology.service;

import com.pilog.plontology.payloads.ImageRequest;
import com.pilog.plontology.payloads.ImageResponse;


public interface ISearchImageService {
     ImageResponse show(ImageRequest request);
}
