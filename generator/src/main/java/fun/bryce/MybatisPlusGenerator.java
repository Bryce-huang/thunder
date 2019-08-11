package fun.bryce;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bryce
 * @date 2019/6/30 7:54
 */
public class MybatisPlusGenerator
{


    public static void generate()
    {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
      //  String projectPath = System.getProperty("user.dir");
        String projectPath = GeneratorCode.projectPath;
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("bryce");
        gc.setOpen(false);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        gc.setEnableCache(GeneratorCode.enableCache);
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        gc.setIdType(IdType.UUID);
        gc.setDateType(DateType.ONLY_DATE);

        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(GeneratorCode.jdbcUrl);
        // dsc.setSchemaName("public");
        dsc.setDriverName(GeneratorCode.jdbcDriver);
        dsc.setUsername(GeneratorCode.jdbcUser);
        dsc.setPassword(GeneratorCode.jdbcPwd);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(GeneratorCode.moduleName);
        pc.setParent(GeneratorCode.parentPackage);
        pc.setEntity(GeneratorCode.entityPackage);
        pc.setController(GeneratorCode.controllerPackage);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig()
        {
            @Override
            public void initMap()
            {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        //  String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath)
        {
            @Override
            public String outputFile(TableInfo tableInfo)
            {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        //
        //                cfg.setFileCreate(new IFileCreate()
        //                {
        //                    @Override
        //                    public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath)
        //                    {
        //                        // 判断自定义文件夹是否需要创建
        //                        checkDir("调用默认方法创建的目录");
        //                        return false;
        //                    }
        //                });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        //                templateConfig.setEntity("templates/entity.java");
        //                templateConfig.setServiceImpl("templates/serviceImpl.java");
        //                templateConfig.setController("templates/controller.java");
        //                templateConfig.setService("templates/service.java");
        templateConfig.setXml(null);
        //templateConfig.setEntityKt("templates/entity.kt");
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntitySerialVersionUID(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
      //  strategy.setSuperEntityClass(GeneratorCode.superEntity);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setEntityBuilderModel(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setEntityBooleanColumnRemoveIsPrefix(false);
       // strategy.setSuperControllerClass(GeneratorCode.superController);
        strategy.setVersionFieldName("version");
        strategy.setLogicDeleteFieldName("logic_delete");
        strategy.setTablePrefix(GeneratorCode.TABLE_PREFIX);
        strategy.setInclude(GeneratorCode.tables);
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        //  strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

}
