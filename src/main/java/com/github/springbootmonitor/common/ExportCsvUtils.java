package com.github.springbootmonitor.common;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/19 0019 15:56
 */
@Slf4j
public class ExportCsvUtils {
    /** CSV文件列分隔符 */
    private static final String CSV_COLUMN_SEPARATOR = ",";

    /** CSV文件列分隔符 */
    private static final String CSV_RN = "\r\n";

    private static final String headLine = "host,ipSource,ipCdn,ipWaf,http,desc,status,title,md5_source,md5_cdn,md5_waf,accessSource,accessCdn,accessWaf,defend";

    /**
     *
     * @param dataList 集合数据
     * @param colNames 表头部数据
     * @param mapKey 查找的对应数据
     * @param os 返回结果
     */
    public static boolean doExport(List<Map<String, Object>> dataList, String colNames, String mapKey, OutputStream os) {
        try {
            StringBuilder buf = new StringBuilder();

            String[] colNamesArr;
            String[] mapKeyArr;

            colNamesArr = colNames.split(CSV_COLUMN_SEPARATOR);
            mapKeyArr = mapKey.split(CSV_COLUMN_SEPARATOR);

            // 完成数据csv文件的封装
            // 输出列头
            for (String aColNamesArr : colNamesArr) {
                buf.append(aColNamesArr).append(CSV_COLUMN_SEPARATOR);
            }
            buf.append(CSV_RN);
            // 输出数据
            if (null != dataList) {
                for (Map<String, Object> aDataList : dataList) {
                    for (String aMapKeyArr : mapKeyArr) {
                        Object obj = aDataList.get(aMapKeyArr);
                        buf.append(obj==null ? "" : obj.toString().replaceAll(",","，"))
                                .append(CSV_COLUMN_SEPARATOR);
                    }
                    buf.append(CSV_RN);
                }
            }
            // 写出响应
            os.write(buf.toString().getBytes("GBK"));
            os.flush();
            return true;
        } catch (Exception e) {
            log.error("doExport错误...", e);
        }
        return false;
    }

    /**
     * setHeader
     */
    public static void responseSetProperties(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        // 设置文件后缀
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fn = fileName + sdf.format(new Date()) + ".csv";
        // 读取字符编码
        String utf = "UTF-8";

        // 设置响应
        response.setContentType("application/ms-txt.numberformat:@");
        response.setCharacterEncoding(utf);
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fn, utf));
    }

    public static String getHeadLine(){
        return headLine;
    }
}
