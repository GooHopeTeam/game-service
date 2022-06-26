package ru.goohope.gameservice.dto.request;

import java.util.Date;
import java.util.List;

public record GameDtoRequest(
        String name,
        String shortDescription,
        String fullDescription,
        Date releaseData,
        String imageUrl,
        List<String> screenshotsUrls) {
}
