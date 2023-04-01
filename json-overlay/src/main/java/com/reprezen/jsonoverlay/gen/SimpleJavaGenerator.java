/*********************************************************************
*  Copyright (c) 2017 ModelSolv, Inc. and others.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *     ModelSolv, Inc. 
 *     - initial API and implementation and/or initial documentation
**********************************************************************/
package com.reprezen.jsonoverlay.gen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

//import com.github.javaparser.JavaParser;
//import com.github.javaparser.ast.CompilationUnit;
//import com.github.javaparser.ast.NodeList;
//import com.github.javaparser.ast.body.BodyDeclaration;
//import com.github.javaparser.ast.body.ConstructorDeclaration;
//import com.github.javaparser.ast.body.FieldDeclaration;
//import com.github.javaparser.ast.body.MethodDeclaration;
//import com.github.javaparser.ast.body.TypeDeclaration;
//import com.github.javaparser.ast.body.VariableDeclarator;
//import com.github.javaparser.ast.comments.JavadocComment;
//import com.github.javaparser.ast.expr.Expression;

public class SimpleJavaGenerator {

	private String pkg;
	private Set<String> imports = new HashSet<>();
	private TypeGenerator.TypeDec type;
	private List<ClassMember> members = new ArrayList<>();
	private String fileComment;
	private static int indentation = 4;

	public SimpleJavaGenerator(String pkg, TypeGenerator.TypeDec type) {
		this.pkg = pkg;
		this.type = type;
	}

	public String getPackage() {
		return pkg;
	}

	public void setPackage(String pkg) {
		this.pkg = pkg;
	}

	public int getIndentation() {
		return indentation;
	}

	public void setIndentation(int indentation) {
		SimpleJavaGenerator.indentation = indentation;
	}

	public Collection<ClassMember> getMembers() {
		return members;
	}

	public void addMember(ClassMember member) {
		members.add(member);
	}

	public void addMembers(Collection<ClassMember> members) {
		this.members.addAll(members);
	}

	public void addGeneratedMembers(Collection<ClassMember> members) {
		for (ClassMember member : members) {
			this.members.add(member.generated());
		}
	}

	public void setFileComment(String fileComment) {
		this.fileComment = fileComment;
	}

	public void addImport(String imp) {
		if (imp != null) {
			imports.add(imp);
		}
	}

	public String format() {
		CompilationUnit cu = new CompilationUnit();
		if (fileComment != null) {
			cu.addOrphanComment(new JavadocComment(fileComment));
		}
		cu.setPackageDeclaration(pkg);
		for (String imp : imports) {
			cu.addImport(imp);
		}
		cu.addType(type);
		for (ClassMember member : gatherFinalMembers(members, cu)) {
			type.addMember(member.getDeclaration());
		}
		return cu.toString();
	}

	private Collection<ClassMember> gatherFinalMembers(List<ClassMember> members, CompilationUnit cu) {
		Map<String, ClassMember> memberMap = new LinkedHashMap<>();
		for (ClassMember member : members) {
			String key = member.getKey();
			if (!memberMap.containsKey(key)) {
				memberMap.put(key, member);
			} else {
				BodyDeclaration<?> copy = member.getDeclaration().clone();
				if (copy instanceof ConstructorDeclaration) {
					((ConstructorDeclaration) copy).setBody(JavaParser.parseBlock("{}"));
					((ConstructorDeclaration) copy).setComment(null);
				} else if (copy instanceof MethodDeclaration) {
					((MethodDeclaration) copy).setBody(null);
					((MethodDeclaration) copy).setComment(null);
				} else if (copy instanceof FieldDeclaration) {
					((FieldDeclaration) copy).getVariable(0).setInitializer((Expression) null);
					((FieldDeclaration) copy).setComment(null);
				}
				copy.setAnnotations(new NodeList<>());
				Logger.getGlobal().warning(String.format("Suppressing already-present generated member in type %s: %s",
						cu.getType(0).getNameAsString(), copy));
			}
		}
		return memberMap.values();
	}

}
