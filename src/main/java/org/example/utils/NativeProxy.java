package org.example.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import org.example.jna.mapper.FreeMemoryTypeMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态库代理
 *
 * @author tangjialin on 2021-01-28.
 */
public class NativeProxy {

    /**
     * native 方法映射（direct-mapping）
     * <p>
     * 通过注入 {@link com.sun.jna.TypeMapper} 自动释放返回值内存
     *
     * @param libName 库名称
     * @param tClass  与此库映射的类
     * @see com.sun.jna.Native#register(java.lang.Class, com.sun.jna.NativeLibrary)
     * @see com.sun.jna.Native#getConversion(java.lang.Class, com.sun.jna.TypeMapper, boolean)
     */
    public static void register(String libName, Class<?> tClass) {
        Map<String, Object> options = new HashMap<>(2);
        options.put(Library.OPTION_CLASSLOADER, tClass.getClassLoader());
        // 给定一个 type-mapper 处理器，将返回 String 类型的函数的返回值转换成 String 后释放 Java 无法管理的内存，避免内存溢出
        options.put(Library.OPTION_TYPE_MAPPER, new FreeMemoryTypeMapper());
        NativeLibrary library = NativeLibrary.getInstance(libName, options);
        Native.register(tClass, library);
    }

    /**
     * interface 接口映射（interface-mapping）
     * <p>
     * 通过注入 {@link com.sun.jna.InvocationMapper} 并为不同类型实现不同的 {@link java.lang.reflect.InvocationHandler} 处理器实现自动释放返回值内存
     *
     * @param libName 库名称
     * @param tClass  与此库映射的类
     * @see com.sun.jna.Library.Handler#Handler(java.lang.String, java.lang.Class, java.util.Map)
     * @see com.sun.jna.Library.Handler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    public static <T extends Library> T load(String libName, Class<T> tClass) {
        Map<String, Object> options = new HashMap<>(2);
        // 给定一个 invocation-mapper 处理器，将返回 String 类型的函数的返回值转换成 String 后释放 Java 无法管理的内存，避免内存溢出
        options.put(Library.OPTION_INVOCATION_MAPPER, new FreeMemoryTypeMapper());
        return Native.load(libName, tClass, options);
    }
}
