package com.clw;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * @Author: clw
 * @Description: mybatis-plus 代码生成器
 * @Date: 2020/7/21 22:10
 */
public class Generator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        //作者
        String author = "clw";
        //模块名
        String moduleName = "sys";
        //包名
        String parentPackage = "com.clw";
        //需要生成的表
        String[] tableNames = new String[]{"tb_user", "tb_role", "tb_menu"};
        //需要去除表的前缀
        String[] tablePrefix = new String[]{"tb_"};
        //数据库配置
        String username = "root";
        String password = "wj113";
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/admin?serverTimezone=Asia/Shanghai&useUnicode=true&useSSL=false&characterEncoding=utf8";

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false); //当代码生成完成之后是否打开代码所在的文件夹
        gc.setDateType(DateType.ONLY_DATE);
        gc.setEnableCache(false);  //XML 二级缓存
        gc.setBaseResultMap(true);  //XML 通用查询映射结果 ResultMap
        gc.setBaseColumnList(true);  //XML 通用查询结果列 columnList
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        // 干掉生成的Service层接口的I,即: IUserService => UserService
        gc.setServiceName("%sService");

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driver);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        //pc.setModuleName("sys");  // 模块名写死 : sys
        pc.setModuleName(moduleName);  // 模块名写死 : sys
        pc.setParent(parentPackage); //默认生成 controller, entity, service, service.impl, mapper

        pc.setController("controller");
        pc.setEntity("domain");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setXml("mapper.xml");

        mpg.setPackageInfo(pc);

        //// 自定义配置
        //InjectionConfig cfg = new InjectionConfig() {
        //    @Override
        //    public void initMap() {
        //        // to do nothing
        //    }
        //};

        //// 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        //// 如果模板引擎是 velocity
        //// String templatePath = "/templates/mapper.xml.vm";

        //// 自定义输出配置
        //List<FileOutConfig> focList = new ArrayList<>();
        //// 自定义配置会被优先输出
        //focList.add(new FileOutConfig(templatePath) {
        //    @Override
        //    public String outputFile(TableInfo tableInfo) {
        //        // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
        //        return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
        //                + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
        //    }
        //});
        ///*
        //cfg.setFileCreate(new IFileCreate() {
        //    @Override
        //    public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
        //        // 判断自定义文件夹是否需要创建
        //        checkDir("调用默认方法创建的目录，自定义目录用");
        //        if (fileType == FileType.MAPPER) {
        //            // 已经生成 mapper 文件判断存在，不想重新生成返回 false
        //            return !new File(filePath).exists();
        //        }
        //        // 允许生成模板文件
        //        return true;
        //    }
        //});
        //*/
        //cfg.setFileOutConfigList(focList);
        //mpg.setCfg(cfg);

        //// 配置模板
        //TemplateConfig templateConfig = new TemplateConfig();
        //
        //// 配置自定义输出模板
        ////指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        //// templateConfig.setEntity("templates/entity2.java");
        //// templateConfig.setService();
        //// templateConfig.setController();
        //
        //templateConfig.setXml(null);
        //mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 把表名和实体属性改成驼峰命名规则
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 设置生成的实体类继承的父类
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        //是否生成Lombok
        strategy.setEntityLombokModel(true);
        //是否生成RestController
        strategy.setRestControllerStyle(true);
        // 公共父类
        //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        //strategy.setSuperEntityColumns("id");
        //strategy.setInclude 注释掉后所有的表都会被生成
        //strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setInclude(tableNames);
        // 生成TableField注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setControllerMappingHyphenStyle(true);
        // 下面设置去除表的前缀
        strategy.setTablePrefix(tablePrefix);
        mpg.setStrategy(strategy);
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
