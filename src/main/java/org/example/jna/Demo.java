package org.example.jna;

import com.sun.jna.WString;
import org.example.jna.gotype.GoSlice;
import org.example.jna.gotype.GoString;
import org.example.utils.NativeProxy;
import org.example.utils.StdLib;

import java.io.InputStream;

/**
 * window:
 * java -Djava.library.path=. -jar jna-golang-sample-jar-with-dependencies.jar "D:/src/jna-golang-sample/go/awesome.dll"
 * <p>
 * linux / macos
 * java -Djava.library.path=. -jar jna-golang-sample-jar-with-dependencies.jar "/home/jna-golang-sample/go/awesome.so"
 *
 * @author tangjialin on 2020-12-28.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        if (args == null || args.length <= 0) {
            args = new String[]{"awesome"};
        }
        AwesomeInterface awesome = NativeProxy.load(args[0], AwesomeInterface.class);

        System.out.printf("awesome.add(12, 99) = %s\n", awesome.add(12, 99));
        System.out.printf("awesome.cosine(1.0) = %s\n", awesome.cosine(1.0));

        // Call Sort
        // First, prepare data array
        long[] nums = new long[]{53, 11, 5, 2, 88};
        // fill in the GoSlice class for type mapping
        GoSlice slice = new GoSlice(nums);
        awesome.sort(slice);
        System.out.print("awesome.sort(53,11,5,2,88) = [");
        long[] sorted = slice.data.getLongArray(0, nums.length);
        StdLib.freeNativeHeap(slice.data);
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i] + " ");
        }
        System.out.println("]");

        // Call print
        String text = "";
        try (InputStream inputStream = Demo.class.getClassLoader().getResourceAsStream("README.md")) {
            StringBuilder stringBuilder = new StringBuilder(1024);
            byte[] bytes = new byte[1024];
            for (int i = -1; (i = inputStream.read(bytes)) != -1; ) {
                stringBuilder.append(new String(bytes, 0, i));
            }
            text = stringBuilder.toString();
        }
        System.out.printf("msgid %d\n", awesome.print(new GoString(text)));

        String value = awesome.echoString(new GoString(text));
        System.out.println("awesome.echoString :" + value);

        WString echo2 = awesome.echoWString(new GoString(text));
        System.out.println("awesome.echoWString :" + echo2.toString());

        GoString echo = awesome.echoGoString(new GoString(text));
        System.out.println("awesome.echoGoString :" + echo.value);
    }

}
