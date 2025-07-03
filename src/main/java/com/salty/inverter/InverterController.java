package com.salty.inverter;


/*
 * @author sefun-mi
 * @createdOn 26/06/2025
 */

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class InverterController {
    private final InvertService invertService;

    @GetMapping("/invert")
    public ResponseEntity<byte[]> testEndpoint(@RequestParam MultipartFile imageFile){
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(invertService.invert(imageFile));
    }
}