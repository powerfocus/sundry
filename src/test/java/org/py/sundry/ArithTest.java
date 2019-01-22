package org.py.sundry;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

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
}