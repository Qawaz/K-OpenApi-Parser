package com.reprezen.jsonoverlay.gen;

import javax.annotation.Generated;
import java.util.stream.Collectors;
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

public class ClassMember {
    protected BodyDeclaration<?> declaration;

    public ClassMember(BodyDeclaration<?> declaration) {
        this.declaration = declaration;
    }

    public ClassMember(String code) {
        try {
            this.declaration = JavaParser.parseBodyDeclaration(code);
        } catch (Exception e) {
            System.out.println("MESSAGE :" + e.getMessage());
            System.out.println("CODE :" + code);
        }
        // this(JavaParser.parseBodyDeclaration(code));
    }

    public ClassMember generated() {
        declaration.addSingleMemberAnnotation(Generated.class, "\"" + CodeGenerator.class.getName() + "\"");
        return this;
    }

    public ClassMember comment(String comment) {
        if (comment != null) {
            declaration.setLineComment(comment);
        } else {
            declaration.removeComment();
        }
        return this;
    }

    public ClassMember override() {
        declaration.addMarkerAnnotation(Override.class);
        return this;
    }

    public ClassMember rename(String from, String to) {
        if (declaration instanceof MethodDeclaration) {
            ((MethodDeclaration) declaration).setName(to);
        } else if (declaration instanceof FieldDeclaration) {
            for (VariableDeclarator var : ((FieldDeclaration) declaration).getVariables()) {
                if (var.getName().getIdentifier().equals(from)) {
                    var.setName(to);
                    break;
                }
            }
        }
        return this;
    }

    public BodyDeclaration<?> getDeclaration() {
        return declaration;
    }

    public String getKey() {
        if (declaration instanceof FieldDeclaration) {
            FieldDeclaration field = (FieldDeclaration) declaration;
            if (field.getVariables().size() != 1) {
                throw new RuntimeException(
                        "Multiple fields in a single manual field declaration is not yet supported: "
                                + field.toString());
            }
            return "F:" + field.getVariable(0).getNameAsString();
        } else if (declaration instanceof MethodDeclaration) {
            MethodDeclaration method = (MethodDeclaration) declaration;
            return "M:" + method.getNameAsString() + ":" + method.getParameters().stream()
                    .map(p -> p.getType().toString()).collect(Collectors.joining(","));
        } else if (declaration instanceof ConstructorDeclaration) {
            ConstructorDeclaration constructor = (ConstructorDeclaration) declaration;
            return "C:" + constructor.getParameters().stream().map(p -> p.getType().toString())
                    .collect(Collectors.joining(","));
        }
        throw new RuntimeException(
                "Unsupported manual member type encountered: " + declaration.getClass().getName());
    }

    public String format() {
        return declaration.toString();
    }

    public String getName() {
        if (declaration instanceof MethodDeclaration) {
            return ((MethodDeclaration) declaration).getNameAsString();
        } else if (declaration instanceof FieldDeclaration) {
            NodeList<VariableDeclarator> vars = ((FieldDeclaration) declaration).getVariables();
            if (vars.size() == 1) {
                return vars.get(0).getNameAsString();
            }
        }
        return null;
    }
}
