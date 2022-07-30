package com.example.clothingrental.Clothing.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.clothingrental.Clothing.common.Res;
import com.example.clothingrental.Clothing.entity.ClothesDetail;
import com.example.clothingrental.Clothing.service.IClothesDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 服装信息管理 前端控制器
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@RestController
@RequestMapping("/Clothing/clothes-detail")
@CrossOrigin
public class ClothesDetailController {

    @Autowired
    IClothesDetailService clothesDetailService;

    /**
     * 条件查询服装/
     */
    @PostMapping("/queryClothes")
    public Res<List<ClothesDetail>> query(@RequestBody Map<String,String> map){
        List<ClothesDetail> list=null;
        if(!map.isEmpty()) {
            LambdaQueryWrapper<ClothesDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            //季节查询
            lambdaQueryWrapper.eq(!Objects.equals(map.get("seasons"), ""), ClothesDetail::getSeasons, map.get("seasons"));
            //服装颜色查询
            lambdaQueryWrapper.eq(!Objects.equals(map.get("color"), ""), ClothesDetail::getColor, map.get("color"));
            list = clothesDetailService.list(lambdaQueryWrapper);
        }else {
            list = clothesDetailService.list();
        }
        return Res.success(list);
    }

    /**
     * 根据id 查询服装
     */
    @GetMapping("/{id}")
    public Res<ClothesDetail> queryById(@PathVariable Long id){
        ClothesDetail clothesDetail = clothesDetailService.getById(id);
        return Res.success(clothesDetail);
    }
    /**
     * 添加服装和编辑服装
     */
    @PostMapping("/addCloth")
    public Res<String> add(@RequestBody ClothesDetail clothesDetail){
        if(clothesDetail.getId()==null){
            clothesDetailService.save(clothesDetail);
        }else {
            LambdaUpdateWrapper<ClothesDetail> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(ClothesDetail::getId, clothesDetail.getId());
            clothesDetail.setId(null);
            clothesDetailService.update(clothesDetail, updateWrapper);
        }
        return Res.success("操作成功");
    }

    /**
     * 根据id删除服装
     */
    @GetMapping("/delete/")
    public Res<String> delete(Long id){
       clothesDetailService.removeById(id);
       return Res.success("服装删除成功");
    }
}
