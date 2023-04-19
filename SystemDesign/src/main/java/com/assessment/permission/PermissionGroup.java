package com.assessment.permission;

import com.assessment.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permission_groups")
public class PermissionGroup {
    @SequenceGenerator(
            name = "permission_group_sequence",
            sequenceName = "permission_group_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "permission_group_sequence"
    )
    @Id
    private int id;

    @Column(nullable = false)
    private String groupName;


}