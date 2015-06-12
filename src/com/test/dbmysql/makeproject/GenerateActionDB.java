package com.test.dbmysql.makeproject;

/**
 * Created by zhangfan on 2015/5/29.
 */

public class GenerateActionDB {


    public static void execute() {


        for (DBTableBean dbTableBean : MainThread.dbTableBeans) {

            String parameterName = MainUtil.LineToUpper(dbTableBean.getTableName());
            String javaModelName = MainUtil.UpperToFirst(parameterName);


            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("package com." + MainThread.packageNameYourself + ".action.dbaction;\n\n");

            stringBuffer.append("import org.springframework.beans.factory.annotation.Autowired;\n" +
                    "import org.springframework.stereotype.Controller;\n" +
                    "import org.springframework.web.bind.annotation.RequestBody;\n" +
                    "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                    "import org.springframework.web.bind.annotation.RequestMethod;\n" +
                    "import org.springframework.web.bind.annotation.ResponseBody;\n" +
                    "import org.springframework.web.servlet.ModelAndView;\n" +
                    "import java.util.List;\n" +
                    "import com."+MainThread.packageNameYourself+".dao."+javaModelName +"Mapper;\n" +
                    "import com."+MainThread.packageNameYourself+".model."+javaModelName +";\n");

            stringBuffer.append("@Controller\n");
            stringBuffer.append("@RequestMapping(value = \"/db/" + parameterName + "\")\n");
            stringBuffer.append("public class " + javaModelName + "ActionDB {\n");
            stringBuffer.append("\t@Autowired\n");
            stringBuffer.append("\tprivate " + javaModelName + "Mapper " + parameterName + "Mapper;\n");


            generateSaveMethod(stringBuffer, parameterName, javaModelName); //生成save方法
            generateUpdateMethod(stringBuffer, parameterName, javaModelName, dbTableBean.getPrimaryKey()); //生成update方法
            generateGetByMethod(stringBuffer, parameterName, javaModelName); //生成getBy方法

            stringBuffer.append("\n}");

            MainUtil.writeFile(javaModelName + "ActionDB.java", stringBuffer, MainThread.path + GenerateFrameWork.pathsMap.get("dbaction"));
        }


    }


    /**
     * 生成save方法
     *
     * @param stringBuffer
     * @param parameterName
     * @param javaModelName
     */
    public static void generateSaveMethod(StringBuffer stringBuffer, String parameterName, String javaModelName) {

        stringBuffer.append("\t@RequestMapping(value = \"save\", method = {RequestMethod.POST,RequestMethod.GET})\n");
        stringBuffer.append("\t@ResponseBody\n");
        stringBuffer.append("\tpublic void savem (@RequestBody " + javaModelName + " " + parameterName + ") {\n");
        stringBuffer.append("\t\t" + parameterName + "Mapper.save(" + parameterName + ");\n");
        stringBuffer.append("\t}\n");
    }


    /**
     * 生成update方法
     *
     * @param stringBuffer
     * @param parameterName
     * @param javaModelName
     */
    public static void generateUpdateMethod(StringBuffer stringBuffer, String parameterName, String javaModelName, String primaryKey) {

        stringBuffer.append("\t@RequestMapping(value = \"update\", method = {RequestMethod.POST,RequestMethod.GET})\n");
        stringBuffer.append("\t@ResponseBody\n");
        stringBuffer.append("\tpublic void updatem (@RequestBody " + javaModelName + " " + parameterName + ") {\n");
        stringBuffer.append("\t\tif(" + parameterName + ".get"+MainUtil.UpperToFirst(MainUtil.LineToUpper(primaryKey))+"()==null){\n\t\t\treturn;\n\t\t}\n");
        stringBuffer.append("\t\t" + parameterName + "Mapper.update(" + parameterName + ");\n");
        stringBuffer.append("\t}\n");
    }

    public static void generateDeleteMethod() {

    }

    /**
     * 生成getBy方法
     *
     * @param stringBuffer
     * @param parameterName
     * @param javaModelName
     */
    public static void generateGetByMethod(StringBuffer stringBuffer, String parameterName, String javaModelName) {

        stringBuffer.append("\t@RequestMapping(value = \"getBy\", method = {RequestMethod.POST,RequestMethod.GET})\n");
        stringBuffer.append("\t@ResponseBody\n");
        stringBuffer.append("\tpublic ModelAndView getBym () {\n");
        stringBuffer.append("\t\tList<" + javaModelName + "> list = " + parameterName + "Mapper.getBy(new " + javaModelName + "());\n");
        stringBuffer.append("\t\tModelAndView mav = new ModelAndView(\"/jspdb/"+javaModelName+".jsp\");\n");
        stringBuffer.append("\t\tmav.addObject(\"" + parameterName + "\", list);\n");
        stringBuffer.append("\t\treturn mav;\n");
        stringBuffer.append("\t}\n");
    }

}
