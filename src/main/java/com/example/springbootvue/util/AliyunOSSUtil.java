package com.example.springbootvue.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.example.springbootvue.entity.OssConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AliyunOSSUtil {
    private static final String accessKeyId= OssConstant.OSS_ACCESS_KEY_ID_IM;
    private static final String accessKeySecret=OssConstant.OSS_ACCESS_KEY_SECRET_IM;
    private static final String endpoint =  OssConstant.OSS_END_POINT_IM;
    private static final String bucket = OssConstant.OSS_BUCKET_IM;

    private static final Logger logger = LoggerFactory.getLogger(AliyunOSSUtil.class);

    public static String OSSUploadFile(String filename){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        File file = new File(filename);
        String dateStr = format.format(new Date());
        if(null == file){
            return null;
        }
        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        try {
            //容器不存在，就创建
            if(! ossClient.doesBucketExist(bucket)){
                ossClient.createBucket(bucket);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
                createBucketRequest.setCannedACL(CannedAccessControlList.Default);
                ossClient.createBucket(createBucketRequest);
            }

            String fileUrl = dateStr + "/" + new Date().getTime()+"-"+file.getName();

            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucket, fileUrl, file));
            //设置权限 这里是私有权限读写
            ossClient.setBucketAcl(bucket,CannedAccessControlList.Default);
            if(null != result){
                logger.info("==========>OSS文件上传成功,OSS地址："+fileUrl);
                return bucket+"/"+fileUrl;
            }
        }catch (OSSException oe){
            logger.error(oe.getMessage());
        }catch (ClientException ce){
            logger.error(ce.getMessage());
        }finally {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }
}

