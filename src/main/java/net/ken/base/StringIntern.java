package net.ken.base;

/**
 * String.intern(): 将这个字符串尝试放入string table，如果有则不会放入
 * 如果没有则放入，无论是否放入，都会返回string table中的对象。
 * []代表string table当前状态
 * {}代表heap当前状态
 */
public class StringIntern {
    public static void main(String[] args) {
        //test1();
        test2();
    }

    public static void test1() {
        // ["a"]
        String a = "a";
        // ["a", "b"]
        String b = "b";
        // {String("ab")}, ["a", "b"]
        // 底层是生成两个StringBuilder对象拼接然后toString();
        String ab = a + b;
        // {String("ab")}, ["a", "b", "ab"]
        // ab在heap中，"ab"在string table, 引用不同，所以false
        System.out.println(ab == "ab");
    }

    public static void test2() {
        // ["a"]
        String a = "a";
        // ["a", "b"]
        String b = "b";
        // {String("ab")}, ["a", "b"]
        String ab = a + b;
        // {String("ab")}, ["a", "b", "ab"]
        // 将"ab"放入string table中，并将ab指向"ab"，返回的ab2同样指向"ab"
        String ab2 = ab.intern();
        // {String("ab")}, ["a", "b", "ab"]
        // ab和"ab"都指向string table, 所以都为true
        System.out.println(ab == "ab");
        System.out.println(ab2 == "ab");
        System.out.println(ab == ab2);

        // {String("ab"), String("ab")}, ["a", "b", "ab"]
        // 由于"ab"在string table中已存在，所以ab3.intern()并不会重新把"ab"
        // 放入string table，即ab3不会指向"ab", 所以ab3 == "ab"为false
        // ab3.intern()虽然失败，但仍会返回的"ab"的引用，这里赋值给了ab4，
        // 所以ab4 == "ab" 为true
        String ab3 = a + b;
        String ab4 = ab3.intern();
        System.out.println(ab3 == "ab");
        System.out.println(ab4 == "ab");
    }

}
