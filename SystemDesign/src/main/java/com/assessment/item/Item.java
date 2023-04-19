package com.assessment.item;

import com.assessment.permission.PermissionGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )
    @Id
    private Long id;

    @Enumerated
    private ItemType type;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "permission_group_id")
    private PermissionGroup permissionGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_item_id")
    private Item parentItem;

}
