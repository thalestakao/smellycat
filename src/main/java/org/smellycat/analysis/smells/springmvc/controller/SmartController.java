package org.smellycat.analysis.smells.springmvc.controller;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.smellycat.analysis.smells.Smell;
import org.smellycat.domain.Repository;
import org.smellycat.domain.SmellyClass;

public class SmartController implements Smell {

	@Override
	public List<Callable<ASTVisitor>> analyzers(Repository repo, SmellyClass clazz) {
		return Arrays.asList(
			() -> new RFCButSpring(clazz)
		);
	}

	@Override
	public boolean conciliate(SmellyClass clazz) {
		// TODO Auto-generated method stub
		return false;
	}

}