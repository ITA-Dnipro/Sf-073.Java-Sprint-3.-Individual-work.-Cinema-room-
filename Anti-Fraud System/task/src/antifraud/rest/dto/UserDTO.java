package antifraud.rest.dto;


import antifraud.domain.model.CustomUser;
import antifraud.domain.model.User;
import antifraud.domain.model.UserFactory;
import antifraud.domain.model.constants.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.validation.constraints.NotEmpty;

@Builder
public record UserDTO(@JsonProperty(access = JsonProperty.Access.READ_ONLY)
                      Long id,
                      @NotEmpty
                      String name,
                      @NotEmpty
                      String username,
                      @NotEmpty
                      @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
                      String password,
                      @JsonProperty(access = JsonProperty.Access.READ_ONLY)
                      UserRole role) {
    public static UserDTO fromModel(User registeredUser) {
        return UserDTO.builder()
                .id(registeredUser.getId())
                .name(registeredUser.getName())
                .username(registeredUser.getUsername())
                .role(registeredUser.getRole())
                .build();
    }

    public User toModel() {
        return UserFactory.create(name, username, password);
    }
}