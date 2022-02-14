package com.srw.common.component;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.UnsupportedEncodingException;

/**
 * @Description: 自定义zk序列化
 * @Author: songrenwei
 * @Date: 2021/7/16
 */
public class CustomZkSerializer implements ZkSerializer {

    private String charset = "UTF-8";

    public CustomZkSerializer(){}

    public CustomZkSerializer(String charset){
        this.charset = charset;
    }

    @Override
    public byte[] serialize(Object data) throws ZkMarshallingError {
        try{
            return String.valueOf(data).getBytes(charset);
        }catch (UnsupportedEncodingException e){
            throw new ZkMarshallingError("Wrong Charset:" + charset);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        String result;
        try {
            result = new String(bytes, charset);
        } catch (UnsupportedEncodingException e) {
            throw new ZkMarshallingError("Wrong Charset:" + charset);
        }
        return result;
    }

}
