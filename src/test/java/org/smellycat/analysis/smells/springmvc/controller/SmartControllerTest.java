package org.smellycat.analysis.smells.springmvc.controller;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smellycat.analysis.smells.SmellAnalysis;
import org.smellycat.analysis.smells.SmellTest;
import org.smellycat.architecture.springmvc.SpringMVCArchitecture;
import org.smellycat.domain.SmellyClass;

public class SmartControllerTest extends SmellTest {

	private SpringMVCArchitecture arch;

	@Before
	public void createArch() {
		this.arch = new SpringMVCArchitecture();
	}
	
	@Test
	public void ignoreSpringMethodInvocations() throws UnsupportedEncodingException {
		SmellAnalysis tool = new SmellAnalysis(arch, basePath + "smart-controller/t1", ps, repo);
		tool.run();
		
		SmellyClass sc = repo.getByClass("mfa.t1.InvoiceController");
		Assert.assertEquals(0, sc.getAttribute("rfc-but-spring"));
	}

	@Test
	public void countOtherInvocations() throws UnsupportedEncodingException {
		SmellAnalysis tool = new SmellAnalysis(arch, basePath + "smart-controller/t1", ps, repo);
		tool.run();
		
		SmellyClass sc = repo.getByClass("mfa.t1.InvoiceController2");
		Assert.assertEquals(2, sc.getAttribute("rfc-but-spring"));

		sc = repo.getByClass("mfa.t1.InvoiceController3");
		Assert.assertEquals(3, sc.getAttribute("rfc-but-spring"));
	}

	@Test
	public void countStaticInvocations() throws UnsupportedEncodingException {
		SmellAnalysis tool = new SmellAnalysis(arch, basePath + "smart-controller/t1", ps, repo);
		tool.run();
		
		SmellyClass sc = repo.getByClass("mfa.t1.InvoiceController5");
		Assert.assertEquals(2, sc.getAttribute("rfc-but-spring"));
	}

	@Test
	public void shouldDealWithInlineMultipleVariableDeclaration() throws UnsupportedEncodingException {
		SmellAnalysis tool = new SmellAnalysis(arch, basePath + "smart-controller/t1", ps, repo);
		tool.run();
		
		SmellyClass sc = repo.getByClass("mfa.t1.InvoiceController6");
		Assert.assertEquals(1, sc.getAttribute("rfc-but-spring"));
	}

}