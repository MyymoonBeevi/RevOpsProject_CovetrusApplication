package utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static utils.ExtentReport.*;

public class Hooks extends Driver {
    public ExtentTest test;
    // SoftAssert softAssertion= new SoftAssert();
    String testClassName;
    String sessionId;

    @BeforeSuite
        //This method execute before the suite.It perform create a report folder and set the report structure
    void createReport(Method method) throws Exception {
        test = getTest(method.getDeclaringClass().getSimpleName() + "-" + method.getName(), method.getName());
    }

    @BeforeTest(alwaysRun = true)
    //This method execute before the test.It perform delete the previous logger file and reports
    public void deleteFile() {

        File fs1 = new File(System.getProperty("user.dir") + "/log4j-application.log");
        File fs2 = new File(System.getProperty("user.dir") + "/ReportsZip.zip");
        File fs3 = new File(System.getProperty("user.dir") + "/reports");
        File fs4 = new File(System.getProperty("user.dir") + "/src/main/resources/downloadedFiles/");
        try {
            fs1.delete();
            fs2.delete();
            File[] list = fs3.listFiles();
            for (File fname : list) {
                if (!fname.isDirectory()) {
                    fname.delete();
                }
            }
            FileUtils.cleanDirectory(fs4);
            /*File[] lists = fs4.listFiles();
            for (File fname : lists) {
                if (fname.isDirectory()) {
                FileUtils.deleteDirectory(fname);
                }
            }*/
        } catch (Throwable e) {

        }
    }

    //This method execute before the class .It perform browser invoke and get the parameters for environment,browser,device Version,device Name,os Version,resolution and browser version

    @BeforeClass
    public void setUp(){
        driver = this.setDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://engage-dev.setvi.com/");
        driver.manage().window().maximize();
    }


    @AfterMethod(alwaysRun = true)
    public void reportWrapUp(ITestResult result, Method method) throws Exception {
        String reportsPath = System.getProperty("user.dir") + "/reports";
        testClassName = method.getDeclaringClass().getSimpleName();
        int i = 0;
        if (!result.isSuccess()) {
            String imageName = method.getName() + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String imagePath = reportsPath + "/" + imageName;
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String dynamicFileName = "./reports/images/report_" + System.currentTimeMillis() + ".jpg";
                FileUtils.copyFile(screenshot, new File(dynamicFileName));
                System.out.println(imagePath + ".png");
                getTest().log(LogStatus.FAIL, "Method - " + method.getName() + " failed , see the screenshot - " + imagePath + ".png");

                String[] fs = new File(reportsPath).list();
                for (String temp : fs) {
                    if (temp.endsWith(".png")) {
                        i++;
                    }
                }
            } catch (Throwable e) {

                Assert.fail("Error while taking screenshot - " + e);
            }
            getTest().log(LogStatus.FAIL, getTest().addScreenCapture("./" + imageName + ".png"));
        }
        try {

            closeTest(test);

        } catch (Throwable e) {

        }
    }
    @AfterClass
    public void vv(){
        closeTest(test);

    }
    @AfterTest(alwaysRun = true)
    public void reportclose() throws Exception {
        int reportStatusCount;
        String name;
        String status;
        String reportStatus = null;
        int count;
        String reStatus;
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        String reportsPath = System.getProperty("user.dir") + "/reports";
        closeReport();
        try {
            FileReader fr = new FileReader("reports/AutomationReport.html");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder html = new StringBuilder();
            String val;
            while ((val = br.readLine()) != null) {
                html.append(val);
            }
            String result = html.toString();
            Document document = Jsoup.parse(result);
            Elements testName = document.select("span.test-name");
            Elements testStatus = document.select("span.test-status");
            reportStatusCount = testName.size();
            String header[] = new String[reportStatusCount];
            for (count = 0; count < reportStatusCount; count++) {
                name = testName.get(count).text();
                status = testStatus.get(count).text();
                reStatus = name + "->" + status;
                header[count] = reStatus;
                reportStatus = Arrays.toString(header).replace("[", "").replace("]", "").replace(",", "\r\n");
            }
            br.close();
        } catch (Exception e) {
        }
    }
    @AfterSuite
    public void close(){
//    driver.quit();
    }
}
