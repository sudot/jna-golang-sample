package org.example.jna;

import com.sun.jna.WString;
import org.example.jna.gotype.GoSlice;
import org.example.jna.gotype.GoString;
import org.example.utils.NativeProxy;

/**
 * 演示接口（direct-mapping）
 * <p>
 * direct-mapping 对于基本类型（包括 Pointer）性能更好，interface-mapping 在复杂类型上略优。
 *
 * @author tangjialin on 2021-02-02.
 */
public class AwesomeNative {
    static {
        NativeProxy.register("awesome", AwesomeNative.class);
    }

    public static native long add(long a, long b);

    public static native double cosine(double val);

    public static native void sort(GoSlice arrays);

    public static native long print(GoString value);

    public static native String echoString(GoString value);

    public static native WString echoWString(GoString value);

    public static native GoString.ByReference echoGoString(GoString value);

}
