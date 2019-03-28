package com.itmuch.cloud.study.user.feign;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "microservice-file-upload", configuration = UploadFeignClient.MultipartSupportConfig.class)
public interface UploadFeignClient {
  @RequestMapping(value = "/upload", method = RequestMethod.POST,
      produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseBody
  String handleFileUpload(@RequestPart(value = "file") MultipartFile file);

  class MultipartSupportConfig {
    @Bean
    public Encoder feignFormEncoder() {
      return new SpringFormEncoder();
    }
  }
}
