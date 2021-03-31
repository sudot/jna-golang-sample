package org.example.jna;

import com.sun.jna.WString;
import org.example.jna.gotype.GoSlice;
import org.example.jna.gotype.GoString;
import org.example.utils.NativeProxy;
import org.example.utils.StdLib;
import org.junit.Assert;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;

public class AwesomeInterfaceTest {
    private static String text = "";
    private final AwesomeInterface awesome = NativeProxy.load("awesome", AwesomeInterface.class);

    @BeforeClass
    public static void beforeClass() throws IOException {
        try (InputStream inputStream = Demo.class.getClassLoader().getResourceAsStream("README.md")) {
            StringBuilder stringBuilder = new StringBuilder(1024);
            byte[] bytes = new byte[1024];
            for (int i = -1; (i = inputStream.read(bytes)) != -1; ) {
                stringBuilder.append(new String(bytes, 0, i));
            }
            text = stringBuilder.toString();
        }
    }

    @org.junit.Test
    public void sort() {
        long[] longs = new Random().longs(1024).toArray();
        // fill in the GoSlice class for type mapping
        GoSlice slice = new GoSlice(longs);
        awesome.sort(slice);
        long[] sorted = slice.data.getLongArray(0, longs.length);
        Arrays.sort(longs);
        StdLib.freeNativeHeap(slice.data);
        Assert.assertArrayEquals(sorted, longs);
    }

    @org.junit.Test
    public void print() {
        Assert.assertEquals(1L, awesome.print(new GoString(text)));
    }

    @org.junit.Test
    public void echoString() {
        String value = awesome.echoString(new GoString(text));
        Assert.assertEquals("echoString 响应数据错误", text, value);
    }

    @org.junit.Test
    public void echoWString() {
        WString value = awesome.echoWString(new GoString(text));
        Assert.assertEquals("echoWString 响应数据错误", text, value.toString());
    }

    @org.junit.Test
    public void echoGoString() {
        GoString pointer = awesome.echoGoString(new GoString(text));
        Assert.assertEquals("echoGoString 响应数据错误", text, pointer.value);
    }

}
