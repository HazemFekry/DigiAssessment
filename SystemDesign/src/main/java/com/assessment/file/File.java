package com.assessment.file;

import com.assessment.item.Item;
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
@Table(name = "\"file\"")
public class File {

    @SequenceGenerator(
            name = "file_sequence",
            sequenceName = "file_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "file_sequence"
    )
    @Id
    private Long id;

    @Lob
    @Column(name =  "\"binary\"", nullable = false)
    private byte[] binary;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
