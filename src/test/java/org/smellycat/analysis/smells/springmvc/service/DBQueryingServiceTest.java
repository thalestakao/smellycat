package org.smellycat.analysis.smells.springmvc.service;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smellycat.analysis.smells.SmellAnalysis;
import org.smellycat.analysis.smells.SmellTest;
import org.smellycat.architecture.springmvc.SpringMVCArchitecture;
import org.smellycat.domain.SmellyClass;

public class DBQueryingServiceTest extends SmellTest {

	private SpringMVCArchitecture arch;

	@Before
	public void createArch() {
		this.arch = new SpringMVCArchitecture();
	}
	
	@Test
	public void shouldDetectTheUseOfJDBC() throws UnsupportedEncodingException {
		SmellAnalysis tool = new SmellAnalysis(arch, basePath + "dbquerying-service/t1", ps, repo);
		tool.run();
		
		SmellyClass sc = repo.getByClass("mfa.t1.InvoiceService");
		Assert.assertEquals(1, sc.getAttribute("use-persistence-mechanism"));
	}
	

	@Test
	public void shouldDetectTheUseOfJPA() throws UnsupportedEncodingException {
		SmellAnalysis tool = new SmellAnalysis(arch, basePath + "dbquerying-service/t1", ps, repo);
		tool.run();
		
		SmellyClass sc = repo.getByClass("mfa.t1.JPAService");
		Assert.assertEquals(1, sc.getAttribute("use-persistence-mechanism"));
	}

	@Test
	public void shouldDetectTheUseOfHibernate() throws UnsupportedEncodingException {
		SmellAnalysis tool = new SmellAnalysis(arch, basePath + "dbquerying-service/t1", ps, repo);
		tool.run();
		
		SmellyClass sc = repo.getByClass("mfa.t1.HibernateService");
		Assert.assertEquals(1, sc.getAttribute("use-persistence-mechanism"));
	}

	@Test
	public void shouldDetectTheUseOfSpringData() throws UnsupportedEncodingException {
		// TODO: i need a code example
	}
	
	@Test
	public void ignoreOnlyImports() throws UnsupportedEncodingException {
		SmellAnalysis tool = new SmellAnalysis(arch, basePath + "dbquerying-service/t1", ps, repo);
		tool.run();
		
		SmellyClass sc = repo.getByClass("mfa.t1.CleanService");
		Assert.assertEquals(0, sc.getAttribute("use-persistence-mechanism"));
	}
}
