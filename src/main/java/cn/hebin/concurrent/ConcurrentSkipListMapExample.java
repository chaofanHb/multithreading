package cn.hebin.concurrent;

import java.text.Collator;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * ConcurrentSkipListMap可以对Map的键值进行排序,并且是线程安全
 * @author Administrator
 *
 */
public class ConcurrentSkipListMapExample {
	static ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<String, String>( Collator.getInstance(java.util.Locale.CHINA));
	public static void main(String[] args) {
		map.put("何斌", "何斌");
		map.put("肖凯", "肖凯");
		map.put("z", "z");
		map.put("詹自立", "詹自立");
		map.put("英雄联盟", "英雄联盟");
		map.put("a", "a");
		map.put("A", "A");
		map.put("德玛西亚", "德玛西亚");
		map.keySet().stream().forEach(System.out::println);
	}
}
