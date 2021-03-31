package org.example.jna.gotype;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * // GoSlice class maps to:
 * // C type struct { void *data; GoInt len; GoInt cap; }
 * // 属性必须是 public, 否则会异常 which do not match declared field names
 *
 * @author tangjialin on 2021-01-14.
 */
public class GoSlice extends Structure implements Structure.ByValue {
    public Pointer data;
    public long len;
    public long cap;

    public GoSlice() {
    }

    public GoSlice(long[] array) {
//        try {
//            Type genericSuperclass = null;
//            for (Class clazz = getClass();
//                 clazz != null && !((genericSuperclass = clazz.getGenericSuperclass()) instanceof ParameterizedType);
//                 clazz = getClass().getSuperclass()) {}
//
//            ParameterizedType type = (ParameterizedType) genericSuperclass;
//            Type actualTypeArgument = type.getActualTypeArguments()[0];
//            //noinspection unchecked
//            Class<T> vClass = (Class<T>) (actualTypeArgument instanceof Class ? actualTypeArgument : ((ParameterizedType) actualTypeArgument).getRawType());
//        } catch (Exception e) {
//            throw new RuntimeException(getClass() + " 缺少泛型", e);
//        }
        int size = Native.getNativeSize(long.class);
        Memory arr = new Memory(array.length * size);
        arr.write(0, array, 0, array.length);
        this.data = arr;
        this.len = array.length;
        this.cap = array.length;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("data", "len", "cap");
    }

}
