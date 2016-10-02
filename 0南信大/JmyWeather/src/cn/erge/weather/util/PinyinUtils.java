package cn.erge.weather.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import android.util.Log;

public class PinyinUtils {
	@SuppressWarnings("null")
	public static String getPinYinByZhongwen(String string) {
		String result = "";
		char[] chars = string.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			String[] pinyin = null;
			try {
				pinyin = PinyinHelper.toHanyuPinyinStringArray(chars[i],
						getDefault());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (pinyin != null) {
				result += pinyin[0];
				result = result.substring(0, result.length());
			} else {
				result += pinyin[i];
			}

		}
		return result;
	}

	private static HanyuPinyinOutputFormat getDefault() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);
		return format;
	}
}
