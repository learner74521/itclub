package com.itclub.common.check;

/**
 * 字符串工具类
 *
 * @author OnePiece
 */
public class StringCheck {

    /**
     * 小写a字符
     */
    private static final char LOWER_A = 'a';
    /**
     * 小写z字符
     */
    private static final char LOWER_Z = 'z';
    /**
     * 大写A字符
     */
    private static final char UPPER_A = 'A';
    /**
     * 大写Z字符
     */
    private static final char UPPER_Z = 'Z';

    /**
     * 校验字符串是否为空
     *
     * @param cs 校验的字符串
     * @return 返回true则为空, 返回false则不为空
     */
    public static boolean isBlank(CharSequence cs) {
        int length = cs.length();
        if (cs == null || length == 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            // 一个字符不是空，就不是空串
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 校验字符串是否不是空
     *
     * @param cs 校验的字符串
     * @return 返回true则不为空，返回false则为空
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * 校验字符串是否为空
     *
     * @param str 校验的字符串
     * @return 返回true则为空，返回false则不为空
     */
    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    /**
     * 校验字符串是否不为空
     *
     * @param str 校验的字符串
     * @return 返回true则不为空，返回false则为空
     */
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    /**
     * 校验字符串是否已指定字符串开头
     *
     * @param cs     校验字符串
     * @param prefix 开头字符串
     * @return 返回true则包含指定字符串，返回false则不包含指定字符串
     */
    public static boolean startWith(CharSequence cs, CharSequence prefix) {
        return startWith(cs, prefix, false);
    }

    /**
     * 校验字符串是否已指定字符串开头
     *
     * @param cs         校验字符串
     * @param prefix     开头字符串
     * @param ignoreCase 是否忽略大小写
     * @return 返回true则包含指定字符串，返回false则不包含指定字符串
     */
    public static boolean startWith(CharSequence cs, CharSequence prefix, boolean ignoreCase) {
        if (cs == null || prefix == null) {
            return cs == null && prefix == null;
        }

        boolean isStartWith;
        if (ignoreCase) {
            isStartWith = cs.toString().toLowerCase().startsWith(prefix.toString().toLowerCase());
        } else {
            isStartWith = cs.toString().startsWith(prefix.toString());
        }

        return isStartWith;
    }

    /**
     * 校验字符串是否以指定字符串结尾
     *
     * @param cs     校验的字符串
     * @param suffix 指定的字符串
     * @return 返回true则是以指定字符串结尾，返回false则不是以指定字符串结尾
     */
    public static boolean endWith(CharSequence cs, CharSequence suffix) {
        return endWith(cs, suffix, false);
    }

    /**
     * 校验字符串是否以指定字符串结尾
     *
     * @param cs         校验的字符串
     * @param suffix     指定的字符串
     * @param ignoreCase 是否忽略大小写
     * @return 返回true则是以指定字符串结尾，返回false则不是以指定字符串结尾
     */
    public static boolean endWith(CharSequence cs, CharSequence suffix, boolean ignoreCase) {
        if (cs == null || suffix == null) {
            return cs == null && suffix == null;
        }

        boolean isEndWith;
        if (ignoreCase) {
            isEndWith = cs.toString().toLowerCase().endsWith(suffix.toString().toLowerCase());
        } else {
            isEndWith = cs.toString().endsWith(suffix.toString());
        }
        return isEndWith;
    }

    /**
     * 字符串首字母转大写
     *
     * @param str 转换的字符串
     * @return 转换结果
     */
    public static String firstUpper(String str) {
        if (isBlank(str)) {
            return null;
        }
        char first = str.charAt(0);
        if (first >= LOWER_A && first <= LOWER_Z) {
            char[] chars = str.toCharArray();
            chars[0] -= 32;
            return new String(chars);
        }
        return str;
    }

    /**
     * 字符串首字母转小写
     *
     * @param str 转换的字符串
     * @return 转换结果
     */
    public static String firstLower(String str) {
        if (isBlank(str)) {
            return null;
        }
        char first = str.charAt(0);
        if (first >= UPPER_A && first <= UPPER_Z) {
            char[] chars = str.toCharArray();
            chars[0] += 32;
            return new String(chars);
        }
        return str;
    }
}