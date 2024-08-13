package com.knuk.algo_memo.domain.group.dto;

import com.knuk.algo_memo.domain.group.model.Group;
import com.knuk.algo_memo.domain.user.dto.UserDTO;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class GroupDTO {
    Long id;
    String name;
    UserDTO owner;
    public GroupDTO(Group group){
        this.id = group.getId();
        this.name = group.getName();
        this.owner = group.getOwner().toDTO();
    }

}
