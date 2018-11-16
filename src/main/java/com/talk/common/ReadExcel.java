package com.talk.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.talk.dto.User;


/**
 * Excel处理函数
 * @author 12878
 *
 */
public class ReadExcel {  
    //总行数  
    private int totalRows = 0;    
    //总条数  
    private int totalCells = 0;   
    //错误信息接收器  
    private String errorMsg;  
    //构造方法  
    public ReadExcel(){}  
    //获取总行数  
    public int getTotalRows()  { return totalRows;}   
    //获取总列数  
    public int getTotalCells() {  return totalCells;}   
    //获取错误信息  
    public String getErrorInfo() { return errorMsg; }    
      
  /** 
   * 读EXCEL文件，获取信息集合 
   * @param fielName 
   * @return 
   */  
    public List<User> getExcelInfo(MultipartFile mFile) {  
        String fileName = mFile.getOriginalFilename();//获取文件名  
        List<User> userList = null;
        try {  
            if (!validateExcel(fileName)) {// 验证文件名是否合格  
                return null;  
            }  
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本  
            if (isExcel2007(fileName)) {  
                isExcel2003 = false;  
            }  
            userList = createExcel(mFile.getInputStream(), isExcel2003);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return userList;  
    }  
    
  /** 
   * 根据excel里面的内容读取客户信息 
   * @param is 输入流 
   * @param isExcel2003 excel是2003还是2007版本 
   * @return 
   * @throws IOException 
   */  
    public List<User> createExcel(InputStream is, boolean isExcel2003) {  
    	List<User> UserList = null;
        try{  
            Workbook wb = null;  
            if (isExcel2003) {// 当excel是2003时,创建excel2003  
                wb = new HSSFWorkbook(is);  
            } else {// 当excel是2007时,创建excel2007  
                wb = new XSSFWorkbook(is);  
            }  
            UserList = readExcelValue(wb);// 读取Excel里面客户的信息  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return UserList;  
    }  
    
  /** 
   * 读取Excel里面客户的信息 
   * @param wb 
   * @return 
   */  
    private List<User> readExcelValue(Workbook wb) {  
        // 得到第一个shell  
        Sheet sheet = wb.getSheetAt(0);  
        // 得到Excel的行数  
        this.totalRows = sheet.getPhysicalNumberOfRows();  
        // 得到Excel的列数(前提是有行数)  
        if (totalRows > 1 && sheet.getRow(0) != null) {  
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();  
        }  
        List<User> UserList = new ArrayList<User>();  
        // 循环Excel行数  
        for (int r = 1; r < totalRows; r++) {  
            Row row = sheet.getRow(r);  
            if (row == null){  
                continue;  
            }  
            User User = new User();
            // 循环Excel的列  
            for (int c = 0; c < this.totalCells; c++) {  
                Cell cell = row.getCell(c);  
                if (null != cell) {  
                    if (c == 0) {  
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25  
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String userId = String.valueOf(cell.getNumericCellValue());  
                            User.setUserId(userId.substring(0, userId.length()-2>0?userId.length()-2:1));
                        }else{  
                        	User.setUserId(cell.getStringCellValue());
                        }  
                    } else if (c == 1) {  
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String userName = String.valueOf(cell.getNumericCellValue());  
                            User.setUserName(userName.substring(0, userName.length()-2>0?userName.length()-2:1));
                        }else{  
                        	User.setUserName(cell.getStringCellValue());
                        }  
                    } else if (c == 2){  
                    	if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String password = String.valueOf(cell.getNumericCellValue());  
                            User.setPassword(password.substring(0, password.length()-2>0?password.length()-2:1));
                        }else{  
                        	User.setPassword(cell.getStringCellValue());
                        }  
                    } else if (c == 3){  
                    	if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String authenticateCode = String.valueOf(cell.getNumericCellValue());  
                            User.setAuthenticateCode(authenticateCode.substring(0, authenticateCode.length()-2>0?authenticateCode.length()-2:1));
                        }else{  
                        	User.setAuthenticateCode(cell.getStringCellValue()); 
                        }  
                    } else if (c == 4){  
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String priority = String.valueOf(cell.getNumericCellValue());  
                            User.setPriority(priority.substring(0, priority.length()-2>0?priority.length()-2:1));
                        }else{  
                        	User.setPriority(cell.getStringCellValue());
                        }  
                    } else if (c == 5){  
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String type = String.valueOf(cell.getNumericCellValue());  
                            User.setType(type.substring(0, type.length()-2>0?type.length()-2:1));
                        }else{  
                        	User.setType(cell.getStringCellValue());
                        }  
                    } else if (c == 6){  
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String loginTime = String.valueOf(cell.getNumericCellValue());  
                            User.setLoginTime(loginTime.substring(0, loginTime.length()-2>0?loginTime.length()-2:1));
                        }else{  
                        	User.setLoginTime(cell.getStringCellValue());
                        }  
                    } else if (c == 7){  
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String loginStatus = String.valueOf(cell.getNumericCellValue());  
                            User.setLoginStatus(loginStatus.substring(0, loginStatus.length()-2>0?loginStatus.length()-2:1));
                        }else{  
                        	User.setLoginStatus(cell.getStringCellValue());
                        }  
                    } else if (c == 8){  
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String scanEn = String.valueOf(cell.getNumericCellValue());  
                            User.setScanEn(scanEn.substring(0, scanEn.length()-2>0?scanEn.length()-2:1));
                        }else{  
                        	User.setScanEn(cell.getStringCellValue());
                        }  
                    } else if (c == 9){  
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String browserType = String.valueOf(cell.getNumericCellValue());  
                            User.setBrowserType(browserType.substring(0, browserType.length()-2>0?browserType.length()-2:1));
                        }else{  
                        	User.setBrowserType(cell.getStringCellValue());
                        }  
                    } else if (c == 10){  
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String level = String.valueOf(cell.getNumericCellValue());  
                            User.setLevel(level.substring(0, level.length()-2>0?level.length()-2:1));
                        }else{  
                        	User.setLevel(cell.getStringCellValue());
                        }  
                    } else if (c == 11){  
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String power = String.valueOf(cell.getNumericCellValue());  
                            User.setPower(power.substring(0, power.length()-2>0?power.length()-2:1));
                        }else{  
                        	User.setPower(cell.getStringCellValue());
                        }  
                    }
                }  
            }  
            // 添加到list  
            UserList.add(User);  
        }  
        return UserList;  
    }  
      
    /** 
     * 验证EXCEL文件 
     *  
     * @param filePath 
     * @return 
     */  
    public boolean validateExcel(String filePath) {  
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {  
            errorMsg = "文件名不是excel格式";  
            return false;  
        }  
        return true;  
    }  
      
    // @描述：是否是2003的excel，返回true是2003   
    public static boolean isExcel2003(String filePath)  {    
         return filePath.matches("^.+\\.(?i)(xls)$");    
     }    
     
    //@描述：是否是2007的excel，返回true是2007   
    public static boolean isExcel2007(String filePath)  {    
         return filePath.matches("^.+\\.(?i)(xlsx)$");    
     }    
}  
