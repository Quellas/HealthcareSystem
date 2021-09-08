package com.itheima.controller;

import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/*
套餐控制层
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;
    /*
    新增套餐
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody Setmeal setmeal,Integer[] checkgroupIds){
        try {
            setmealService.add(setmeal,checkgroupIds);
            //记录提交 文件名到redis
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }

        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }


    /**
     * 图片上传
     * 方式一：
     * MultipartFile imgFile
     * 方式二：
     * @RequestParam("imgFile") MultipartFile aaaa
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload(MultipartFile imgFile) {
        try {
            //1.可以获取图片字节数组
            byte[] bytes = imgFile.getBytes();
            //2.通过uuid生成唯一文件名称
            String uuid = UUID.randomUUID().toString();//文件名称  xxx-xxx-xxx
            String originalFilename = imgFile.getOriginalFilename();//获取原始文件名称 1.jpg
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));//文件后缀 .jpg
            String newFileName = uuid+suffix;
            //3.调用七牛云进行图片上传
            QiniuUtils.upload2Qiniu(bytes,newFileName);
            // 上传图片后 文件名到redis
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,newFileName);
            //4.将上传成功后图片名称 返回
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,newFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

}
