package dev.vorstu.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDTO {
    private Long id;
    private String nameOfGroup;
}
