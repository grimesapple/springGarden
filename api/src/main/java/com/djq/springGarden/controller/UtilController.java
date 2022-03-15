package com.djq.springGarden.controller;

import cn.hutool.core.lang.UUID;
import com.djq.springGarden.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author yerong
 * @date 2022/2/17 15:58
 */
@RestController
@RequestMapping("/util")
@Slf4j
public class UtilController {
    /**
     * 文件上传
     * @param picture 文件
     * @param request 请求
     * @return 上传结果
     */
    @PostMapping("/upload")
    public ResultVO<HashMap<String, String>> upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request) {

        log.info("您已进入图片上传服务");
        //获取文件在服务器的储存位置
//        String path = "D:\\01_yr\\03_studyProject\\springGarden\\resource\\imgs";
        String path = System.getProperty("user.dir")+"\\resource\\imgs";
        File filePath = new File(path);
        log.info("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            log.info("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        log.info("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        log.info("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        String fileName = UUID.fastUUID() + name + "." + type;
        log.info("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);
        log.info("图片地址："+path+"/"+fileName);
        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            log.info("上传成功");
            HashMap<String, String> map = new HashMap<>();

            map.put("filename",fileName);
            //将文件在服务器的存储路径返回
            return ResultVO.ok(map);
        } catch (IOException e) {
            log.info("上传失败");
            e.printStackTrace();
            return ResultVO.error("上传失败");
        }
    }
}
