package fun.bryce;

/**
 * @author bryce
 * 2019/7/2 13:30
 */
public class GeneratorCode
{

    static String TABLE_PREFIX = "t_";
    static String[] tables = new String[]{"t_user","t_log"};
    static String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/dev?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8";
    static String jdbcUser = "root";
    static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    static String jdbcPwd = "123456";
    static String moduleName = "mybatistest";
    static String parentPackage = "fun.bryce";
    static String entityPackage = "domain.bean";
    static String controllerPackage = "web.controller";
   // static String superEntity = "base.BaseEntity";
    //static String superController = "base.BaseController";
    static Boolean enableCache = false;
    static String projectPath = "D:\\codes\\thunder\\mybatis-test";

    public static void main(String[] args)
    {
        MybatisPlusGenerator.generate();
    }

}
