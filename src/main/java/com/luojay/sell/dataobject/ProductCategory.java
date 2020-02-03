package com.luojay.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 类目实体类 遵循驼峰命名
 * 映射数据库表 product_category
 */
@Entity         //标注实体类映射到数据库
@DynamicUpdate  //动态更新注解
@Data           //相当于@Getter和@Setter
//@Table(name = "S_ProductCategory") 如果表名和实体名不一致要加上这个注解
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    //类目名字
    private String categoryName;
    //类目编号
    private Integer categoryType;
    //更新时间
    private Date updateTime;
    //创建时间
    private Date createTime;

    public ProductCategory() {
    }

}
