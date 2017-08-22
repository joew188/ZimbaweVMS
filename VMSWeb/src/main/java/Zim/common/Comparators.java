package Zim.common;

import Zim.model.modelview.chart.ApplicantOfDateAvg;

import java.util.Comparator;

/**
 * Created by Laxton-Joe on 2017/7/27.
 */
public class Comparators {
    public static Comparator getComparator() {
        return new Comparator() {

            public int compare(Object o1, Object o2) {
                if (o1 instanceof String) {
                    return compare((String) o1, (String) o2);
                } else if (o1 instanceof Integer) {
                    return compare((Integer) o1, (Integer) o2);
                } else if (o1 instanceof Long) {
                    return compare(o1, o2);
                } else if (o1 instanceof ApplicantOfDateAvg) {
                    return compare((ApplicantOfDateAvg) o1, (ApplicantOfDateAvg) o2);
                } else {
                    System.err.println("未找到合适的比较器");
                    return 1;
                }
            }

            public int compare(String o1, String o2) {

                String s1 = o1;
                String s2 = o2;
     /*
      * System.out.println("s1=="+s1+"                s2=="+s2);
      * 取出数组相邻的两个姓firstname或者名lastname
      */
                int len1 = s1.length();
                int len2 = s2.length();
                int n = Math.min(len1, len2);
                char v1[] = s1.toCharArray();
                char v2[] = s2.toCharArray();
                int pos = 0;

                while (n-- != 0) {
                    char c1 = v1[pos];
                    char c2 = v2[pos];
                    if (c1 != c2) {
                        return c1 - c2;
                    }
                    pos++;
                }
                return len1 - len2;
            }

            public int compare(Integer o1, Integer o2) {
                int val1 = o1;
                int val2 = o2;
                return (val1 < val2 ? -1 : (val1 == val2 ? 0 : 1));
            }

            public int compare(char o1, char o2) {
       /*
           * System.out.println("o1=="+o1+"                o2=="+o2);
           * 取出数组相邻的两个人的性别
           * 对return的逻辑解释：
           *
           比如两个人的性别分别是男和女，当第一个人的性别是‘男’，则返回1
           （返回1的对于compare方法来说，即认为o1（男）>（女）o2，而o1>o2返回1则按照升序排序，升序排序即将小的（女）排在前面 )

           还不懂的话：接着看compare方法的解释
           int compare(Object o1, Object o2) 返回一个基本类型的整型
            如果要按照升序排序，
            则o1 小于o2，返回-1（负数），相等返回0，01大于02返回1（正数）
            如果要按照降序排序
            则o1 小于o2，返回1（正数），相等返回0，01大于02返回-1（负数）

           */

                return ((o1 == o2) ? 0 : (o1 == '男' ? 1 : -1));

            }

            public int compare(ApplicantOfDateAvg o1, ApplicantOfDateAvg o2) {
                return o1.getCount() - o2.getCount();
            }
        };
    }

}
