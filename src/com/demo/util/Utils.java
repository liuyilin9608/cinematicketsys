package com.demo.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * 时间: 2017/11/18 13:27
 * 功能: 抽取出的一些用到的方法
 */

public class Utils {
    /**
     * 将提交的参数值赋值给对象
     */
    public static void populateToBean(Object bean, Map<String, ?> params) {
        try {
            populateBean(bean, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void populateBean(Object bean, Map<String, ?> params) throws Exception {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object obj = params.get(field.getName());
            String[] value = null;
            if (obj instanceof String) {
                value = new String[]{(String) obj};
            } else if (obj instanceof String[]) {
                value = (String[]) obj;
            }
            if (value != null ) {
                Class<?> clazz = field.getType();
                if (clazz == String.class) {
                    field.set(bean, value[0]);
                } else if (clazz == String[].class) {
                    field.set(bean, value);
                } else if (!isEmpty(value[0])) {
                    if (List.class.isAssignableFrom(clazz)) {
                        field.set(bean, new ArrayList<>(Arrays.asList(value)));
                    } else if (clazz == int.class || clazz == Integer.class) {
                        field.set(bean, Integer.valueOf(value[0]));
                    } else if (clazz == long.class || clazz == Long.class) {
                        field.set(bean, Long.valueOf(value[0]));
                    } else if (clazz == short.class || clazz == Short.class) {
                        field.set(bean, Short.valueOf(value[0]));
                    } else if (clazz == float.class || clazz == Float.class) {
                        field.set(bean, Float.valueOf(value[0]));
                    } else if (clazz == double.class || clazz == Double.class) {
                        field.set(bean, Double.valueOf(value[0]));
                    } 
                }
            }
        }
    }

    /**
     * 将提交的参数值赋值给对象
     */
    public static <T> T populateToBean(Class<T> c, Map<String, ?> params) {
        try {
            T bean = c.newInstance();
            populateBean(bean, params);
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 字符串是否为空
     */
    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    /**
     * 判断密码格式是否正确
     * @param password 明文密码
     */
    public static boolean isPasswordValid(String password) {
        //为空或长度不符
        if (password == null || password.length() < 6 || password.length() > 30) {
            return false;
        }
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(password);
        //如果包含空白字符
        if (matcher.find()) {
            return false;
        }
        //只包含一种字符
        return !password.matches("\\d+") && !password.matches("[a-z]+") && !password.matches("[A-Z]+") && !password.matches("\\W+");
    }

    public static void fillAttr(HttpServletRequest req, Map<String, ?> map, String key) {
        Object value = map.get(key);
        String s = null;
        if (value instanceof String) {
            s = (String) value;
        } else if (value instanceof String[]) {
            s = ((String[]) value)[0];
        }
        if (s != null) {
            req.setAttribute(key, s);
        }
    }
    
    public static void fillAttr(HttpServletRequest req, String key, String value) {
        if (value != null) {
            req.setAttribute(key, value);
        }
    }
    
    public static void fillAttr(HttpServletRequest req, Object bean) {
        try {
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(bean);
                if (value != null) {
                    req.setAttribute(field.getName(), value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String generateImgFileName(String fileName) {
        // 获得扩展名
        int beginIndex = fileName.lastIndexOf(".");
        String ext = "";
        if (beginIndex != -1) {
            ext = fileName.substring(beginIndex);
        }
        return MD5Utils.getMD5Code(UUID.randomUUID().toString()) + ext;
    }
    
    public static int compare(Object o1, String s1, Object o2, String s2) {
        if (o2 == null || s2 == null)
            return 1;
        if (o1 == null || s1 == null)
            return -1;
        String s3 = CharacterParser.getSelling(s1);
        String s4 = CharacterParser.getSelling(s2);
        return s3.compareTo(s4);
    }
}
