package cn.xc.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
class MyController {

    private final ChatClient chatClient;

    public MyController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/ai")
    List<Generation> generation() {

        String userInput = "hello !-";

        PromptTemplate promptTemplate = new PromptTemplate(userInput);
        Prompt prompt = promptTemplate.create();

        ChatClient.ChatClientRequest.CallPromptResponseSpec call = chatClient.prompt(prompt).call();
        List<Generation> results = call.chatResponse().getResults();
        return results;
    }
}