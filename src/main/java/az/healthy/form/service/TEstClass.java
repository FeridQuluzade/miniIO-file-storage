package az.healthy.form.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TEstClass implements CommandLineRunner {
    CloudinaryService cloudinaryService;

    public TEstClass(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void run(String... args) throws Exception {
    System.out.println("start");
    cloudinaryService.test();
    }
}