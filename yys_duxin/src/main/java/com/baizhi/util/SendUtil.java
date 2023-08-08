//package com.baizhi.util;
//
//import cn.hutool.core.util.RandomUtil;
//import com.aliyun.tea.TeaException;
//import com.aliyun.tea.*;
//public class SendUtil {
//    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
//        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
//                // 必填，您的 AccessKey ID
//                .setAccessKeyId(accessKeyId)
//
//                // 必填，您的 AccessKey Secret
//                .setAccessKeySecret(accessKeySecret);
//        // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
//        config.endpoint = "dysmsapi.aliyuncs.com";
//        return new com.aliyun.dysmsapi20170525.Client(config);
//    }
//
//    public static void main(String[] args_) throws Exception {
//        java.util.List<String> args = java.util.Arrays.asList(args_);
//        // 请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID 和 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
//        // 工程代码泄露可能会导致 AccessKey 泄露，并威胁账号下所有资源的安全性。以下代码示例使用环境变量获取 AccessKey 的方式进行调用，仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
//        com.aliyun.dysmsapi20170525.Client client = SendUtil.createClient(
//                "LTAI5tBduWVmQJPaoUfGe1E5","elJoAnbag1DgI5cWdt7kGz5qNvWfkV");
//        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
//                .setPhoneNumbers("19582150918")
//                .setSignName("[测试专用]阿里云通信")
//                .setTemplateCode("SMS_283975452")
//                .setTemplateParam("您登录豫优社的验证码为:"+ RandomUtil.randomNumbers(6));
//        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
//        try {
//            // 复制代码运行请自行打印 API 的返回值
//            client.sendSmsWithOptions(sendSmsRequest, runtime);
//            System.out.println("成功");
//        } catch (TeaException error) {
//            // 如有需要，请打印 error
//            com.aliyun.teautil.Common.assertAsString(error.message);
//        } catch (Exception _error) {
//            TeaException error = new TeaException(_error.getMessage(), _error);
//            // 如有需要，请打印 error
//            com.aliyun.teautil.Common.assertAsString(error.message);
//        }
//    }
//}
