package com.djq.springGarden.config;

import java.io.FileWriter;
import java.io.IOException;

/*支付宝接口配置*/
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2021000119616532";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCa30K/fMWr1wJXQgfBBN+SsGmJYvPCy6QOqhDPA62rO/ffoRH+KXYJtYjlGv4S6aeybvvVDeBQQzzZmbd93VYQ6tjdSDiW2JA5zZWOIl+YVB/seK8SKOQtitjtS4ndzPQE1IC5ade6O8ndvMSjJfmdY8tM9kWQVZkvhBbMKFDBZFBtXziGOmCpEZBGpkeX4Sb2RLfygS4wAFyrZleOY8ku1RdNRFOH50VTnsynGfE+riSweo5davp6fFDzhMWiqw0dOtrWriJQv4eD2qf7pV2OS091AQoXBvcK5c1pyfpQZuL/g9OXm1TPzldkoXJilEFoZrEsYR13+XY9PbA5UEBTAgMBAAECggEBAJDG3y4E5QEUGSMbHgaJmLsXYUFGmJtkJTR0NQxg4uvtPnX2q8xOVAMViiiqiBOEC/mZGKB9vVEq2eqNcWQfEkL0V9Dcn9IcFw+CKSna5kSPd9xmoTq3sT8Fym5iLDqFSyRsxv0Nfdah5776pVPivysrzDRhEHyyGGa781fc5Ek0GU1OpGWGSR7YymG7HWb3/O9u3EyruffLB7qbEjEQR5a1xKi3uUTmjOTaJ8ZCAzT3SRgptzxrnmUq7WAJE43g9iDPJd85J9azWisNFOARa/YSMcM6vRrMaYbiOXyAKHVghmtPAx+FrhvWkObUEgPhN27HuyeCg2ZWdiKeW5qKWQkCgYEA1pPG8osGWH0Q7DWsNkPm6KYaopXLD4i66HlcN8Q+WnroMV7FS6Gig4Xm+a4xavQBvgYJTG97xEueOpD+2OJPWE27zzENHE8WOpolCx1U4Zo8TNzZdpaMRkhf8TS5DJAEaJoXVez/xMdWZXF62mJyXcr3I4b8/aZWAaMaKjW6O+8CgYEAuMToLm4ekF0IwTSZ6gHiO9eDZKK/j4XRVEw8X0/Jk4/hKtnU41FUFKm0+uWUQfOIEXEnsCBGNL8dUtlRZdJJS6Uw+GP97wVSKlEencdaFzGHZkn/Hi2OvfeSI7MWzL4lIRR5ieuBfnwv3r1BdVsw1rc7yhrRkdpHxYLqmRGWrd0CgYEAwdKOfHuz6a5e4N7Chenj4/ZWTNI9r/Rz0JX/BKiRIi2vGxld4alJIMCNGK+OF+v8vua484AoLfGYJZLfgI072qmsocTO8OlHTPv7xgRirb4iBdPf4syuPLYXY8uZ840LTmkR9x2zEs50bgGJVUZjw/EYN9ExKPVc7vGjKsmJRaUCgYEAm9LEFNQdxpYPWhFodGdeSiV41BhFAV9TqIoaqC77ywyeUqse1Q6zoDF3Rdjz7aLacAtD809kpfs/Wa2Y7gwocDlCUVjSwQ91ueZrsZh8K+9DR0rEcsjtBtyRTLYuVkFNwtYnod8ReIB3RpvJZpWfXJVvQQTZFO5HdRTxkGG696kCgYAJNjcswARVUvtIgX4aOyWStuuL1OVXoZo4hANxGQm7nozwlZ82P+jh2PWNNF58Cvs0Li7olOZkaL+QK6addoNlI8voEhtUZVYb1N/PBgDgZIuLr5XyREx/zv0IjNXCguCPqblKE0N5Eq8WVNEx0c9pKHm1Ln+LZaapFW+fKMMowA==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA+ZPiA2LfrMH6f6Uj4ji12I/l8GlUv2Lj8kj6O28XsCTdcs+IJCaVsi1JdeECRli01HDwBWriyzdD6S/wFXjnMyRf0CPjHogqoGW9iUpYX1fvfQr5+HB+jEXso56qZB30LIKy0nMiCG5FM86GPCIOr7iopCCblIsla4LJF68U6UrYyZ+ZHPLfzPgZ0QORgy91DsspkluCY/0DGvHbIYromHL6uAb28Da3401BVG/P0Md4A5j71EUX/kpetIkVCGWtY8rzIsaxIEIWOSwKMK8kTmRAA+X66xAVgH9w2jr00571xUqtFaMHJqDClT1Q8KTzWzqAi/rn/Av5eQM1/LK+jQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8080/";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
