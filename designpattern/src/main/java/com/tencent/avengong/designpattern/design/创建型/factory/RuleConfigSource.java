package com.tencent.avengong.designpattern.design.创建型.factory;

import android.support.annotation.NonNull;
import com.tencent.avengong.designpattern.design.创建型.factory.dicontainer.BeansDifinition;
import com.tencent.avengong.designpattern.design.创建型.factory.factorymethod.AbsIParserFactory;
import com.tencent.avengong.designpattern.design.创建型.factory.factorymethod.AbsJsonParserFactory;
import com.tencent.avengong.designpattern.design.创建型.factory.factorymethod.AbstractFactory;
import com.tencent.avengong.designpattern.design.创建型.factory.parser.IRuleConfigParser;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleConfigSource {
    static Map<String, AbsIParserFactory> mfactorys = new HashMap();

    static {
        mfactorys.put("json", new AbsJsonParserFactory());
    }

    Map beansDefinitions = new HashMap<String, BeansDifinition>();
    private Class clazz;
    private Object object;

    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = getiRuleConfigParser(ruleConfigFilePath, ruleConfigFileExtension);
        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    @NonNull
    private IRuleConfigParser getiRuleConfigParser(String ruleConfigFilePath, String ruleConfigFileExtension) {

//    return SimpleFactory.createParse(ruleConfigFilePath,ruleConfigFileExtension);

        return AbstractFactory.createParser(ruleConfigFilePath, ruleConfigFileExtension);

    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }

    private Object create(BeansDifinition beansDifinition) {

        Object object = null;
        try {
            Class<?> clazz = Class.forName(beansDifinition.className);

            List<BeansDifinition.ConstructorAgr> constructAgrs = beansDifinition.getConstructAgrs();
            int size = constructAgrs.size();
            if (size > 0) {
                Class[] clazzes = new Class[size];
                Object[] objects = new Object[size];
                for (int i = 0; i < size; i++) {
                    BeansDifinition.ConstructorAgr agr = constructAgrs.get(i);
                    if (!agr.isRef()) {
                        //构造参数对象没有引用其他对象
                        clazzes[i] = agr.getClazz();
                        objects[i] = agr.getMObject();
                    } else {
                        //引用的对象还引用了其他的对象
                        //如果引用了其他对象，那么就设置这个object为string
                        BeansDifinition referDifinition = (BeansDifinition) beansDefinitions.get(agr.getMObject());
                        Class<?> aClass = Class.forName(referDifinition.className);
                        clazzes[i] = aClass;
                        objects[i] = create(referDifinition);

                    }
                }

                object = clazz.getConstructor(clazzes).newInstance(objects);
            } else {

                object = clazz.newInstance();

            }

            return object;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;

    }


}