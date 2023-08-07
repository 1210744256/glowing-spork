package com.baizhi.dto;

import com.baizhi.entity.Group;
import com.baizhi.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse extends Group {
    //    小组的学生人数
    private Integer studentCount;
//    小组标签
    private List<Tag> tags;
}
