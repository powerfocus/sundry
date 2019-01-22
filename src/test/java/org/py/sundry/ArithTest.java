package org.py.sundry;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ArithTest {
	@Test
	public void test() throws IOException {
		File filesdir = FileUtils.getFile("files");
		FileUtils.forceMkdir(filesdir);
		System.out.println(filesdir.getAbsolutePath() + " " + filesdir.exists());
	}
	@Test
	public void test2() {
		File dir = FileUtils.getFile(".");
		long size = FileUtils.sizeOf(dir);
		BigDecimal re = BigDecimal.valueOf(size)
			.divide(BigDecimal.valueOf(FileUtils.ONE_KB))
			.setScale(2, RoundingMode.FLOOR);
		System.out.println("目录大小：" + re.doubleValue() + " KB");
	}
	@Test
	public void test3() {
		System.out.println(FileUtils.ONE_EB);
		System.out.println(FileUtils.ONE_GB);
		System.out.println(FileUtils.ONE_KB);
		System.out.println(FileUtils.ONE_MB);
	}
	@Test
	public void test4() {
		Optional<BigDecimal> re = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
			.limit(100)
			.reduce((a, b) -> a.multiply(b));
		String restr = re.get().toString();
		Matcher matcher = Pattern.compile("0+\\b").matcher(restr);
		System.out.println(restr);
		if(matcher.find())
			System.out.println(matcher.group().length());
	}
	@Test
	public void test5() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<String> list = new ArrayList<>(Arrays.asList(
					"win95", "win98", "win2000", "winxp", "win2003", "winvista", "win2008", "win7", "win8", "win10"
				));
		mapper.writeValue(System.out, list); 
	}
	@Test
	public void test6() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("os", "win10");
		map.put("name", "powerfocus");
		map.put("version", "1.0");
		map.put("copyring", "pythagoras");
		map.put("qq", "20926399");
		map.put("oslist", Arrays.asList("win7", "win8", "win10", "ubuntu"));
		String json = mapper.writeValueAsString(map);
		System.out.println(json);
		Map<?, ?> readMap = mapper.readValue(json, Map.class);
		System.out.println(readMap);
	}
}