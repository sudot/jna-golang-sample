package org.example.jna;

import com.sun.jna.Library;
import com.sun.jna.WString;
import org.example.jna.gotype.GoSlice;
import org.example.jna.gotype.GoString;

/**
 * 演示接口（interface-mapping）
 * <p>
 * direct-mapping 对于基本类型（包括 Pointer）性能更好，interface-mapping 在复杂类型上略优。
 *
 * @author tangjialin on 2021-01-28.
 */
public interface AwesomeInterface extends Library {

    /**
     * 两个数相加，并返回相加后的数
     *
     * @param a 相加的第一个数
     * @param b 相加的第二个数
     * @return 两个数相加的结果
     */
    long add(long a, long b);

    /**
     * 计算一个角度的余弦值
     *
     * @param val 角度值
     * @return 角度的余弦值
     */
    double cosine(double val);

    /**
     * 对数组进行排序
     *
     * @param arrays 需要排序的数字
     */
    void sort(GoSlice arrays);

    /**
     * 输出给定值到控制台
     *
     * @param value 需要输出的值
     * @return 返回此方法被调用的总次数
     */
    long print(GoString value);

    /**
     * 回显字符串
     *
     * @param value 字符串入参
     * @return 返回和入参一样的字符串
     */
    String echoString(GoString value);

    /**
     * 回显字符串
     *
     * @param value 字符串入参
     * @return 返回和入参一样的字符串
     */
    WString echoWString(GoString value);

    /**
     * 回显字符串
     *
     * @param value 字符串入参
     * @return 返回和入参一样的字符串
     */
    GoString.ByReference echoGoString(GoString value);


}
