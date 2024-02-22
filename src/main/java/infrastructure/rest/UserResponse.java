package infrastructure.rest;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {

    private static final long serialVersionUID = 42L;

    private UUID id;
    private String name;
    private String email;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

}