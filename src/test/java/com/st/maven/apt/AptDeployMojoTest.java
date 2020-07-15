package com.st.maven.apt;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.UUID;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.junit.Test;

public class AptDeployMojoTest {
	
	@Test(expected = MojoExecutionException.class)
	public void testUnknownFile() throws Exception {
		AptDeployMojo.readControl(new SystemStreamLog(), new File(UUID.randomUUID().toString()));
	}
	
	@Test
	public void testControlFile() throws Exception {
		ControlFile file = AptDeployMojo.readControl(new SystemStreamLog(), new File("./src/test/resources/rtl-sdr_0.6git_armhf.deb"));
		assertEquals("rtl-sdr", file.getPackageName());
	}

	@Test
	public void testControlFileNewFormat() throws Exception {
		ControlFile file = AptDeployMojo.readControl(new SystemStreamLog(), new File("./src/test/resources/rtl-sdr_0.6_armhf.deb"));
		assertEquals("rtl-sdr", file.getPackageName());
	}
}
