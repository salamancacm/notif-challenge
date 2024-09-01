package com.gila.notifchallenge.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@WebMvcTest(CorsConfigTest.TestController.class)
class CorsConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Configuration
    static class TestConfig {
        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/api/**")
                            .allowedOrigins("http://localhost:3000")
                            .allowedMethods("GET", "POST")
                            .allowedHeaders("*")
                            .allowCredentials(true);
                }
            };
        }
    }

    @RestController
    @RequestMapping("/api")
    static class TestController {
        @GetMapping("/test")
        public ResponseEntity<String> testEndpoint() {
            return ResponseEntity.ok("Test endpoint");
        }
    }

    @Test
    void corsConfigurer_shouldAllowCorsForSpecificOrigin() throws Exception {
        mockMvc.perform(get("/api/test")
                        .header("Origin", "http://localhost:3000"))
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:3000"))
                .andExpect(header().string("Access-Control-Allow-Credentials", "true"));
    }

    @Test
    void corsConfigurer_shouldNotAllowCorsForDifferentOrigin() throws Exception {
        mockMvc.perform(get("/api/test")
                        .header("Origin", "http://notallowed.com"))
                .andExpect(header().doesNotExist("Access-Control-Allow-Origin"));
    }
}
