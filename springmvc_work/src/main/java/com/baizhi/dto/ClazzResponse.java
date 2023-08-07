package com.baizhi.dto;

import com.baizhi.entity.Clazz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzResponse extends Clazz {
    private Integer clazzStudentCount;
}
