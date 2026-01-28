package com.pavam.library.dto;

import java.util.Set;

public record BookRecordDto(String name,
                            Long publisherId,
                            Set<Long> authorsId,
                            String reviewContent) {

}
