package com.michael.magaisa.user.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindUsersByIdQuery {
    private String id;
}
