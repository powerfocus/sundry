package org.py.sundry;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ArithTest {
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ArithTest.class);
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
		System.out.println(readMap.get("os").getClass());
		System.out.println(readMap);
	}
	@Test
	public void test7() throws ClientProtocolException, IOException {
		String html = Request.Get("http://www.163.com")
			.execute()
			.returnContent()
			.asString();
		System.out.println(html);
	}
	@Test
	public void test8() throws IOException {
		Document doc = Jsoup.connect("http://www.163.com").get();
		doc.select(".cm_ul_round li a")
			.forEach(a -> System.out.println(a.text()));
	}
	@Test
	public void test9() throws IOException {
		Document doc = Jsoup.connect("http://www.163.com").get();
		String baseUri = doc.baseUri();
		doc.getElementsByAttribute("src")
			.forEach(src -> {
				String srcattr = src.attr("src");
				srcattr = (srcattr.startsWith("//") ? baseUri.concat(srcattr) : srcattr);
				try {
					String filename = FilenameUtils.getName(srcattr);
					String extensionName = FilenameUtils.getExtension(filename);
					if(!extensionName.isEmpty() && extensionName.length() == 3 && !extensionName.contains("?") && !extensionName.contains("&")) {
						String fn = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
						FileUtils.copyURLToFile(new URL(srcattr), FileUtils.getFile("C:\\Users\\Administrator\\Desktop\\files", fn.concat(".").concat(extensionName)));
						LOGGER.info("save file: " + filename);
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			});
	}
	@Test
	public void test10() throws IOException {
		String data = "这是一个测试程序，io测试，中文文本文档输出";
		FileUtils.writeStringToFile(FileUtils.getFile("files", "readme.txt"), data, StandardCharsets.UTF_8);
		LOGGER.info("文件操作完成！");
	}
	@Test
	public void test11() throws IOException {
		String content = FileUtils.readFileToString(FileUtils.getFile("files", "readme.txt"), StandardCharsets.UTF_8);
		LOGGER.info("文档内容：");
		LOGGER.info(content);
	}
	@Test
	public void test12() throws IOException {
		List<String> colls = new ArrayList<>();
		colls.add("hello world.");
		colls.add("2019.01.24");
		FileUtils.writeLines(FileUtils.getFile("files", "readme.txt"), colls);
		LOGGER.info("文件写入完成！");
	}
	@Test
	public void test13() throws IOException {
		String content = FileUtils.readFileToString(FileUtils.getFile("files", "readme.txt"), StandardCharsets.UTF_8);
		LOGGER.info("文件内容：");
		LOGGER.info(content);
	}
	@Test
	public void test14() throws IOException {
		List<String> lines = new ArrayList<>();
		lines.add("添加一些内容");
		lines.add("这是一个文本文件追加操作");
		lines.add("2019.01.24");
		FileUtils.writeLines(FileUtils.getFile("files", "readme.txt"), lines, true);
		LOGGER.info("文件写入操作完成！");
	}
	@Test
	public void test15() {
		Stream.generate(Math::random)
			.limit(10)
			.map(n -> Double.valueOf(Math.floor(n * 1000)).longValue())
			.forEach(System.out::println);
	}
	@Test
	public void test16() {
		OptionalInt re = IntStream.iterate(Integer.valueOf(1), n -> n + 1)
			.limit(100)
			.reduce((a, b) -> a + b);
		System.out.println(re.getAsInt());
	}
	@Test
	public void test17() {
		Optional<BigDecimal> re = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
			.limit(100)
			.reduce((a, b) -> a.multiply(b));
		System.out.println(re.get().toBigInteger());
		System.out.println(re.get());
	}
	@Test
	public void test18() {
		Optional<BigInteger> re = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE))
			.limit(100)
			.reduce((a, b) -> a.multiply(b));
		System.out.println(re.get());
	}
	@Test
	public void test19() {
		IntStream.rangeClosed(1,  100)
			.boxed()
			.flatMap(a -> IntStream.rangeClosed(a, 100)
					.filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
					.mapToObj(b -> new int[] {a, b, (int)Math.sqrt(a * a + b * b)})
				);
	}
	@Test
	public void test20() {
		List<String> words = Arrays.asList("hello ", "world.");
		words.stream()
			.map(word -> word.split(""))
			.flatMap(Arrays::stream)
			.collect(Collectors.toList())
			.forEach(System.out::println);
	}
	@Test
	public void test21() {
		List<Integer> coll1 = Arrays.asList(1, 2).stream().collect(Collectors.toList());
		List<Integer> coll2 = Arrays.asList(3, 4, 5, 6, 7).stream().collect(Collectors.toList());
		coll1.stream()
			.flatMap(i -> coll2.stream().map(j -> new int[] {i, j}))
			.collect(Collectors.toList())
			.stream()
			.map(it -> Arrays.stream(it));
	}
	@Test
	public void test22() {
		Stream.iterate(new int[] {0, 1}, t -> new int[] {t[1], t[0] + t[1]})
			.limit(10)
			.map(t -> t[0])
			.forEach(System.out::println);
	}
}