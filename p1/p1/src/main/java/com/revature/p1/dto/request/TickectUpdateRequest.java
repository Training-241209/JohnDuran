package com.revature.p1.dto.request;

import lombok.Data;
import lombok.Getter;

@Data
public class TickectUpdateRequest {
    public long ticketId;
    public String description;
}
