package sample1;

import jflash.display.Quad;
import jflash.display.Stage;
import jflash.util.Color;
import libcore.Native;
import libgame.GL;
import libgame.SDLEngineContext;
//import libgame.*;

public class Sample1 {
	static public int sum(int[] args) {
		int sum = 0;
		for (int arg : args) sum += arg;
		return sum;
	}

    static public TestInterface ti = new TestImplementation();
    static int test = 1;

    static {
        test = -1;
    }

    static public void test1(TestInterface test) {
        test.test();
    }

	static public int main(String[] args) {
        System.out.println("Hello world!");
        //libcore.Native.putchar('H');
        //return -1;
		int[] list = new int[10];

		list[0] = 1;
		list[2] = 20;

		System.arraycopy(list, 0, list, 4, 4);

		int len = sum(list);

		Native.putchar(':');

        System.out.println("test:" + Sample1.test);

        System.out.println("123456789");
        System.out.println("" + 123456789);

        int[] data2 = new int[10000000];

        long start = System.currentTimeMillis();
		System.out.println("start:" + start);
        for (int m = 0; m < data2.length; m++) {
            data2[m] = m * 2;
        }
        long end = System.currentTimeMillis();
		System.out.println("end:" + end);
        System.out.println("diff:" + (end - start));

        test1(new TestImplementation());

        TestInheritance1 ti = new TestInheritance1();
        System.out.println("test1:" + ti.test1());
        System.out.println("test2:" + ti.test2());
        System.out.println("test3:" + ti.test3());

        int mm = 1;

        try {
            if (mm == 1) throw(new Exception("hello world"));
            System.out.println("This shouldn't be shown");
        } catch (Exception e) {
            System.out.println("Catch exception: " + e.getMessage());
            e.printStackTrace();
        }

		for (int n = 0; n < len; n++) {
			if ((n % 2) == 0) {
				System.out.println(("Hello world!" + n).toUpperCase());
				//System.out.println("Hello world!" + n);
			}
		}

		System.out.println("" + System.currentTimeMillis());

		//for (int n = 0; n < 10; n++) {
		//	for (int m = 0; m < 10; m++) {
		//		System.out.println("" + n + "x" + m + "=" + (n * m));
		//	}
		//}

        final int seven = 7;
        MyTestInterface v = new MyTestInterface() {
            @Override
            public int run() {
                return -seven;
            }
        };

        System.out.println("WOW. Inner class says minus seven is: " + v.run());

        SDLEngineContext context = new SDLEngineContext();
        Stage stage = new Stage(context);

        stage.addChild(new Quad() {
            {
                this.color = Color.red;
                this.width = 200;
                this.height = 200;
            }
        });

        context.loop(stage);

		return 0;
	}
}

interface MyTestInterface {
    int run();
}