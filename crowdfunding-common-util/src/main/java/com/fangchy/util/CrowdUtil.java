package com.fangchy.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.fangchy.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 10136
 */
public class CrowdUtil {
	
	/**
	 * 判断当前请求是否为Ajax请求
	 * @param request 请求对象
	 * @return
	 * 		true：当前请求是Ajax请求
	 * 		false：当前请求不是Ajax请求
	 */
	public static boolean judgeRequestType(HttpServletRequest request) {
		
		// 1.获取请求消息头
		String acceptHeader = request.getHeader("Accept");
		String xRequestHeader = request.getHeader("X-Requested-With");
		
		// 2.判断
		return (acceptHeader != null && acceptHeader.contains("application/json"))
				|| (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
	}

	/**
	 * md5加密
	 * @param source 加密内容
	 * @return encoded 加密后的内容
	 */
	public static String md5(String source) {
		// 1.判断source是否有效
		if (source == null || source.length() == 0) {
			// 2.如果不是有效字符串抛出异常
			throw new RuntimeException(CrowdConstant.MESSAGE_LOGIN_STRING_INVALID);
		}
        try {
            // 3.获取MessageDigest对象
            String algorithm = "md5";
            MessageDigest messageDisgest = MessageDigest.getInstance(algorithm);
            // 4.获取明文字符串对应的字节数组
            byte[] input = source.getBytes();
            // 5.执行加密
            byte[] output = messageDisgest.digest(input);
            // 6.创建BigInteger对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);
            // 7.按照16进制将BigInteger的值转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 专门负责上传文件到OSS服务器的工具方法
     *
     * @param endpoint        OSS参数
     * @param accessKeyId     OSS参数
     * @param accessKeySecret OSS参数
     * @param inputStream     要上传的文件的输入流
     * @param bucketName      OSS参数
     * @param bucketDomain    OSS参数
     * @param originalName    要上传的文件的原始文件名
     * @return 包含上传结果以及上传的文件在OSS上的访问路径
     */
    public static ResultEntity<String> uploadFileToOss(
            String endpoint,
            String accessKeyId,
            String accessKeySecret,
            InputStream inputStream,
            String bucketName,
            String bucketDomain,
            String originalName) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 生成上传文件的目录
        String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());

        // 生成上传文件在OSS服务器上保存时的文件名
        // 原始文件名：beautfulgirl.jpg
        // 生成文件名：wer234234efwer235346457dfswet346235.jpg
        // 使用UUID生成文件主体名称
        String fileMainName = UUID.randomUUID().toString().replace("-", "");

        // 从原始文件名中获取文件扩展名
        String extensionName = originalName.substring(originalName.lastIndexOf("."));

        //上传文件的根目录
        String rootName = "crowdfunding";

        // 使用目录、文件主体名称、文件扩展名称拼接得到对象名称
        String objectName = rootName + "/" + folderName + "/" + fileMainName + extensionName;

        try {
            // 调用OSS客户端对象的方法上传文件并获取响应结果数据
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);

            // 从响应结果中获取具体响应消息
            ResponseMessage responseMessage = putObjectResult.getResponse();

            // 根据响应状态码判断请求是否成功
            if (responseMessage == null) {

                // 拼接访问刚刚上传的文件的路径
                String ossFileAccessPath = bucketDomain + "/" + objectName;

                // 当前方法返回成功
                return ResultEntity.successWithData(ossFileAccessPath);
            } else {
                // 获取响应状态码
                int statusCode = responseMessage.getStatusCode();

                // 如果请求没有成功，获取错误消息
                String errorMessage = responseMessage.getErrorResponseAsString();

                // 当前方法返回失败
                return ResultEntity.failed("当前响应状态码=" + statusCode + " 错误消息=" + errorMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();

            // 当前方法返回失败
            return ResultEntity.failed(e.getMessage());
        } finally {

            if (ossClient != null) {

                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }

    }

//	public static void main(String[] args) throws FileNotFoundException {
//		FileInputStream inputStream = new FileInputStream("crowdfunding-common-util/1.jpg");
//		ResultEntity<String> resultEntity = uploadFileToOss("http://oss-cn-hangzhou.aliyuncs.com", "LTAI4GA9uiDrNLcswgFMQtmF", "fHkqgUFKyFh9QqvygEymz6OQlhRB8W", inputStream, "fangchenyong", "http://fangchenyong.oss-cn-hangzhou.aliyuncs.com", "1.jpg");
//		System.out.println(resultEntity);
//	}
}
