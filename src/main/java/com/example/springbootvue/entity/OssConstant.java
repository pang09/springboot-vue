package com.example.springbootvue.entity;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OssConstant implements InitializingBean {
    @Value("${aliyun.oss.file.endPoint}")
    private String oss_file_endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String oss_file_keyid;

    @Value("${aliyun.oss.file.keysecret}")
    private String oss_file_keysecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String oss_file_bucketname;



    public static String OSS_END_POINT_IM;
    public static String OSS_BUCKET_IM;
    public static String OSS_ACCESS_KEY_ID_IM;
    public static String OSS_ACCESS_KEY_SECRET_IM;

    @Override
    public void afterPropertiesSet() throws Exception {
        OSS_END_POINT_IM = oss_file_endpoint;
        OSS_BUCKET_IM = oss_file_bucketname;
        OSS_ACCESS_KEY_ID_IM = oss_file_keyid;
        OSS_ACCESS_KEY_SECRET_IM = oss_file_keysecret;
    }
}
