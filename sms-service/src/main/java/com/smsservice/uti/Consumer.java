package com.smsservice.uti;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smsservice.dto.Pkg;
import com.smsservice.service.MsgService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Consumer {
    private static final String msgTopic = "${topic.name}";

    private final MsgService msgService;

    private final ObjectMapper objectMapper;


    private Pkg pkg;

    @KafkaListener(topics = msgTopic)
    public void consumeMessage(String message) throws IOException {
        Pkg pkg = objectMapper.readValue(message, Pkg.class);
        msgService.sendMsg(pkg);
    }

}
