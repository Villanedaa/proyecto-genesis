package com.genesis.proyecto2.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedUsers {
    private List<UserAdminView> content;
    private Integer page;
    private Integer size;
    private Integer totalElements;
    private Integer totalPages;
}
