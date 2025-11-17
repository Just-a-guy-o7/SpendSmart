package com.spendSmart.spendSmart.forms;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GroupForm {
    @NotBlank(message = "Name is required")
    public String name;

    public String description;
    
    public List<String> groupMembers;

}
