package org.cheng.boot;

import java.io.File;

import org.zeroturnaround.zip.ZipUtil;

public class ZipTest {

	public static void main(String[] args) {
		ZipUtil.pack(new File("D:\\test\\zip\\a"), new File("D:\\test\\zip\\test.zip"));
	}

}
