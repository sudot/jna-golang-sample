package org.example.utils;

import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;

/**
 * 通用库
 *
 * @author tangjialin on 2021-01-28.
 */
public class StdLib {
    static {
        Native.register(Platform.C_LIBRARY_NAME);
    }

    /**
     * 释放 Native Heap 空间
     *
     * @param p 释放变量的指针值
     */
    public static native void free(long p);

    /**
     * 释放底层语言分配的空间
     * <p>
     * 调用 C 通用库的 free 函数来释放空间
     * <p>
     * 使用场景:
     * 底层语言创建并使用的空间，例如下面代码，此代码返回的 C.CString(msg) 就需要调用此函数进行释放
     * <pre>
     * //export echoString
     * func echoString(msg string) *C.char {
     * 	return C.CString(msg)
     * }
     * </pre>
     *
     * @param pointer 释放变量的指针值
     */
    public static void free(Pointer pointer) {
        long p = Pointer.nativeValue(pointer);
        if (p == 0L) { return; }
        free(p);
        Pointer.nativeValue(pointer, 0L);
    }

    /**
     * 释放 Native Heap 空间
     * <p>
     * 调用 {@link Native#free(long)} 来释放空间
     * <p>
     * 使用场景:
     * 底层语言创建并使用的空间，例如下面代码，此代码产生的 new Memory 就需要调用此函数进行释放
     * <pre>
     * int size = Native.getNativeSize(long.class);
     * Memory arr = new Memory(array.length * size);
     * arr.write(0, array, 0, array.length);
     * </pre>
     *
     * @param pointer 释放变量的指针值
     */
    public static void freeNativeHeap(Pointer pointer) {
        long p = Pointer.nativeValue(pointer);
        if (p == 0L) { return; }
        Native.free(p);
        Pointer.nativeValue(pointer, 0L);
    }
}
