package net.smihi.customerservice.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class UserActivateEvent {
    private String email;
    private boolean active;
}
