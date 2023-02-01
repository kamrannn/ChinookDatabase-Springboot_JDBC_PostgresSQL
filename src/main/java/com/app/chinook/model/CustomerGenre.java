package com.app.chinook.model;

import lombok.*;

/**
 * The type Customer genre.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CustomerGenre {
    private String name;
    private String tracks_count;
}
