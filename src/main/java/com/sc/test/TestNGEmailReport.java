package com.sc.test;

import com.sc.email.EmailUtils;
import com.sc.util.DateUtil;
import com.sc.util.ZipUtil;
import org.apache.commons.io.FileUtils;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import java.io.File;
import java.util.List;

public class TestNGEmailReport implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> list, List<ISuite> list1, String s) {
        System.out.println("report--------------");
        String path =System.getProperty("user.dir")+File.separator+"report-output";
        //文件名过长，可能会导致邮件中附件的名称显示为“未命名.bin”
        String zip_path = path + File.separator +"API测试结果_"+ DateUtil.getCurrentDate()+".zip";
        ZipUtil.zip(path,zip_path);

            try {
                EmailUtils.sendEmailsWithAttachments("api测试结果","具体测试结果，请查看附件，谢谢！",zip_path);
                FileUtils.cleanDirectory(new File(path));
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

}
