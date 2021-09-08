package com.itheima.jobs;

import com.itheima.constant.RedisConstant;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJobs {
    @Autowired
    private JedisPool jedisPool;
    /**
     * 清理垃圾图片任务方法
     */
    public void clearImg(){
        Set<String> sdiff = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        System.out.println("数量********"+sdiff.size());
        if(sdiff != null && sdiff.size()>0){
            for (String filename : sdiff) {
                // 调用七牛云删除图片
                QiniuUtils.deleteFileFromQiniu(filename);
                // redis中也需要删除
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,filename);
                System.out.println("filename="+filename+"被删除了。。。");
            }
        }
    }
}
