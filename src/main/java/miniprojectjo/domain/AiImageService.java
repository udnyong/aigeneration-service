package miniprojectjo.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class AiImageService {

    @Value("${ai.openai.api-key}")
    private String openAiApiKey;

    private WebClient webClient;

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
            .baseUrl("https://api.openai.com/v1")
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + openAiApiKey)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    // ✅ GPT-4o 요약 생성
    /**
     * @param content
     * @return
     */
    @SuppressWarnings("unchecked")
    public String generateSummary(String content) {
        String prompt = "다음 내용을 3문장으로 요약해줘:\n" + content;

        try {
            final Map<String, Object> response = webClient.post()
                .uri("/chat/completions")
                .bodyValue(Map.of(
                    "model", "gpt-4o",
                    "messages", new Object[] {
                        Map.of("role", "system", "content", "당신은 요약 전문가입니다."),
                        Map.of("role", "user", "content", prompt)
                    },
                    "temperature", 0.7
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

            return ((Map<String, Object>) ((Map<String, Object>) ((java.util.List<?>) response.get("choices")).get(0)).get("message")).get("content").toString().trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "⚠️ 요약 생성 실패";
        }
    }

    // ✅ DALL·E 기반 표지 이미지 생성
    public String generateCoverImage(String prompt) {
        try {
            Map<String, Object> response = webClient.post()
                .uri("/images/generations")
                .bodyValue(Map.of(
                    "model", "dall-e-3",
                    "prompt", prompt,
                    "n", 1,
                    "size", "1024x1024"
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

            return ((Map<String, String>) ((java.util.List<?>) response.get("data")).get(0)).get("url");

        } catch (Exception e) {
            e.printStackTrace();
            return "⚠️ 이미지 생성 실패";
        }
    }
}
