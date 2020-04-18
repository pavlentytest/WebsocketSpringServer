package ru.myitschool.chat;

import lombok.extern.slf4j.Slf4j;


import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.myitschool.chat.models.Message;
import ru.myitschool.chat.models.Response;

@Controller
public class ChatController {


    private static final String token = "12345";

    @GetMapping(path="/")
    public String index() {
        return "index";
    }

    @GetMapping(path="/chat")
    public String chat() {
        return "chat";
    }

    @MessageMapping("/chat")
    @SendTo("/all")
    public Response chat(Message message, @Header(value="authorization") String authtoken) {
        Response response = new Response("Token incorrect!");
        if(authtoken.equals(token)) {
            if(message.getType().equals("name")) {
                response.setResponse("Привет, " + message.getMessage());
            } else {
                response.setResponse(message.getName() + ": "+message.getMessage());
            }
        } else {
            System.out.println(response.getResponse());
        }
        return response;
    }
}
