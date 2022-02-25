package com.ke.live.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * @author 小凡
 */
public class ObjectCopyUtils {
    private static final Logger logger = LoggerFactory.getLogger(ObjectCopyUtils.class);

    public ObjectCopyUtils() {
    }

    public static <T> T convert(Object origin, Class<T> clazz) {
        Object dest = null;
        if (null != origin) {
            try {
                dest = clazz.newInstance();
                copyProperties(dest, origin);
            } catch (InstantiationException var4) {
                dest = null;
                logger.error("InstantiationException getBean error. ", var4);
            } catch (IllegalAccessException var5) {
                dest = null;
                logger.error("IllegalAccessException getBean error. ", var5);
            }
        }

        return null != dest ? (T) dest : null;
    }

    public static <T, S> List<T> convert(Collection<S> srcList, Class<T> targetClazz) throws JsonProcessingException {
        if (CollectionUtils.isEmpty(srcList)) {
            return Collections.emptyList();
        } else {
            Object target = null;

            try {
                List<T> dist = new ArrayList();
                Iterator var4 = srcList.iterator();

                while(var4.hasNext()) {
                    S src = (S) var4.next();
                    target = targetClazz.newInstance();
                    copyProperties(target, src);
                    dist.add((T) target);
                }

                return dist;
            } catch (Exception var6) {
                logger.error("对象{}复制属性出错:{}", targetClazz.getSimpleName(), new ObjectMapper().writeValueAsString(srcList));
                throw new IllegalArgumentException("对象" + targetClazz.getSimpleName() + "复制属性出错", var6);
            }
        }
    }

    public static void copyProperties(Object dest, Object origin) {
        if (null == origin) {
            dest = null;
        } else {
            try {
                org.springframework.beans.BeanUtils.copyProperties(origin, dest);
            } catch (BeansException var3) {
                logger.error("BeansException copyProperties error.", var3);
            }

        }
    }

    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet();
        PropertyDescriptor[] var4 = pds;
        int var5 = pds.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            PropertyDescriptor pd = var4[var6];
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return (String[])emptyNames.toArray(result);
    }

    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
}
