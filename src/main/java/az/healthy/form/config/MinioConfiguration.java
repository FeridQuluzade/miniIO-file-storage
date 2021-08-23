package az.healthy.form.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfiguration {
    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKey;
    @Value("${minio.url}")
    private String url;
    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .credentials(accessKey,secretKey)
                .endpoint(url).build();
    }
}
