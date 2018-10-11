/**
 * 上传控制类
 */
package io.renren.modules.upload.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA_2_3.portable.OutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.StringUtil;
import io.renren.config.UploadConfig;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.upload.entity.DevUploadAttachmentEntity;
import io.renren.modules.upload.service.DevUploadAttachmentService;

/**
 * @author hawkbird date 2018-09-27
 *
 */
@Controller
@RequestMapping("upload")
public class DevUploadAttachmentController extends AbstractController {
	@Autowired
	private DevUploadAttachmentService devUploadAttachmentService;
	@Autowired
    private UploadConfig uploadConfig;
	@RequestMapping("/browserpic/{id}")
	public void browserPic(@PathVariable("id") String id,HttpServletResponse response) {
		try {
			DevUploadAttachmentEntity entity = devUploadAttachmentService.selectById(id);
			String masterId = entity.getMasterid();
			String fileType = entity.getFiletype();
			String path = uploadConfig.getRoot()+File.separator+entity.getPath();
			String fn = "";
			if(StringUtil.isEmpty(masterId)) {
			    fn = path+File.separator+id+fileType;
			}else {
				fn = path+File.separator+id+masterId+fileType;
			}
			
			File f = new File(fn);
			if(!f.exists())
				return;
			FileInputStream fis = new FileInputStream(f);
			byte[] data = new byte[(int)f.length()];
			fis.read(data);
			fis.close();
			response.getOutputStream().write(data);
			response.getOutputStream().flush();
			response.getOutputStream().close();			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
