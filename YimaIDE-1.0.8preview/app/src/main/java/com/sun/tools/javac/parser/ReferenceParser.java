package com.sun.tools.javac.parser;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.source.tree.AnnotatedTypeTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import java.util.Objects;
import javax.tools.JavaFileObject;
import nbjavac.StringWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ReferenceParser {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final ParserFactory fac;

    public enum Mode {
        MEMBER_DISALLOWED,
        MEMBER_OPTIONAL,
        MEMBER_REQUIRED
    }

    public static class ParseException extends Exception {
        private static final long serialVersionUID = 0;
        final int pos;

        public ParseException(int i, String str) {
            super(str);
            this.pos = i;
        }
    }

    public static class Reference {
        public final Name member;
        public final JCTree.JCExpression moduleName;
        public final List<JCTree> paramTypes;
        public final JCTree qualExpr;

        public Reference(JCTree.JCExpression jCExpression, JCTree jCTree, Name name, List<JCTree> list) {
            this.moduleName = jCExpression;
            this.qualExpr = jCTree;
            this.member = name;
            this.paramTypes = list;
        }
    }

    public ReferenceParser(ParserFactory parserFactory) {
        this.fac = parserFactory;
    }

    private void checkDiags(Log.DeferredDiagnosticHandler deferredDiagnosticHandler, int i) throws ParseException {
        java.util.List<JCDiagnostic> diagnostics = deferredDiagnosticHandler.getDiagnostics();
        if (!diagnostics.isEmpty()) {
            throw new ParseException(i + ((int) diagnostics.get(0).getPosition()), "dc.ref.syntax.error");
        }
    }

    private Name parseMember(String str, int i, int i2, Log.DeferredDiagnosticHandler deferredDiagnosticHandler) throws ParseException {
        String strSubstring = str.substring(i, i2);
        JavaFileObject javaFileObjectUseSource = this.fac.log.useSource(null);
        try {
            JavacParser javacParserNewParser = this.fac.newParser(strSubstring, false, false, false);
            Name nameIdent = javacParserNewParser.ident();
            if (javacParserNewParser.token().kind != Tokens.TokenKind.EOF) {
                throw new ParseException(i + javacParserNewParser.token().pos, "dc.ref.unexpected.input");
            }
            checkDiags(deferredDiagnosticHandler, i);
            this.fac.log.useSource(javaFileObjectUseSource);
            return nameIdent;
        } catch (Throwable th) {
            this.fac.log.useSource(javaFileObjectUseSource);
            throw th;
        }
    }

    private JCTree.JCExpression parseModule(String str, int i, int i2, Log.DeferredDiagnosticHandler deferredDiagnosticHandler) throws ParseException {
        String strSubstring = str.substring(i, i2);
        JavaFileObject javaFileObjectUseSource = this.fac.log.useSource(null);
        try {
            JavacParser javacParserNewParser = this.fac.newParser(strSubstring, false, false, false);
            JCTree.JCExpression jCExpressionQualident = javacParserNewParser.qualident(false);
            if (javacParserNewParser.token().kind != Tokens.TokenKind.EOF) {
                throw new ParseException(i + javacParserNewParser.token().pos, "dc.ref.unexpected.input");
            }
            checkDiags(deferredDiagnosticHandler, i);
            this.fac.log.useSource(javaFileObjectUseSource);
            return jCExpressionQualident;
        } catch (Throwable th) {
            this.fac.log.useSource(javaFileObjectUseSource);
            throw th;
        }
    }

    private List<JCTree> parseParams(String str, int i, int i2, Log.DeferredDiagnosticHandler deferredDiagnosticHandler) throws ParseException {
        String strSubstring = str.substring(i, i2);
        if (StringWrapper.isBlank(strSubstring)) {
            return List.nil();
        }
        JavaFileObject javaFileObjectUseSource = this.fac.log.useSource(null);
        try {
            JavacParser javacParserNewParser = this.fac.newParser(strSubstring.replace("...", "[]"), false, false, false);
            ListBuffer listBuffer = new ListBuffer();
            listBuffer.add(javacParserNewParser.parseType());
            if (javacParserNewParser.token().kind == Tokens.TokenKind.IDENTIFIER) {
                javacParserNewParser.nextToken();
            }
            while (javacParserNewParser.token().kind == Tokens.TokenKind.COMMA) {
                javacParserNewParser.nextToken();
                listBuffer.add(javacParserNewParser.parseType());
                if (javacParserNewParser.token().kind == Tokens.TokenKind.IDENTIFIER) {
                    javacParserNewParser.nextToken();
                }
            }
            if (javacParserNewParser.token().kind != Tokens.TokenKind.EOF) {
                throw new ParseException(javacParserNewParser.token().pos, "dc.ref.unexpected.input");
            }
            Tree treeScan = new TypeAnnotationFinder().scan(listBuffer, (Object) null);
            if (treeScan != null) {
                throw new ParseException(i + ((JCTree) treeScan).getStartPosition(), "dc.ref.annotations.not.allowed");
            }
            checkDiags(deferredDiagnosticHandler, i);
            List<JCTree> list = listBuffer.toList();
            this.fac.log.useSource(javaFileObjectUseSource);
            return list;
        } catch (Throwable th) {
            this.fac.log.useSource(javaFileObjectUseSource);
            throw th;
        }
    }

    private JCTree parseType(String str, int i, int i2, Log.DeferredDiagnosticHandler deferredDiagnosticHandler) throws ParseException {
        String strSubstring = str.substring(i, i2);
        JavaFileObject javaFileObjectUseSource = this.fac.log.useSource(null);
        try {
            JavacParser javacParserNewParser = this.fac.newParser(strSubstring, false, false, false);
            JCTree.JCExpression type = javacParserNewParser.parseType();
            if (javacParserNewParser.token().kind != Tokens.TokenKind.EOF) {
                throw new ParseException(i + javacParserNewParser.token().pos, "dc.ref.unexpected.input");
            }
            Tree treeScan = new TypeAnnotationFinder().scan(type, (Object) null);
            if (treeScan != null) {
                throw new ParseException(i + ((JCTree) treeScan).getStartPosition(), "dc.ref.annotations.not.allowed");
            }
            checkDiags(deferredDiagnosticHandler, i);
            this.fac.log.useSource(javaFileObjectUseSource);
            return type;
        } catch (Throwable th) {
            this.fac.log.useSource(javaFileObjectUseSource);
            throw th;
        }
    }

    public Reference parse(String str, Mode mode) throws ParseException {
        JCTree.JCExpression module;
        Name member;
        JCTree type;
        Log log = this.fac.log;
        Objects.requireNonNull(log);
        Log.DeferredDiagnosticHandler deferredDiagnosticHandler = new Log.DeferredDiagnosticHandler(log);
        try {
            int iIndexOf = str.indexOf(PsuedoNames.PSEUDONAME_ROOT);
            int i = iIndexOf + 1;
            int iIndexOf2 = str.indexOf("#", i);
            int i2 = iIndexOf2 + 1;
            int iIndexOf3 = str.indexOf("(", Math.max(iIndexOf, iIndexOf2) + 1);
            int i3 = iIndexOf3 + 1;
            List<JCTree> params = null;
            if (iIndexOf == -1) {
                module = null;
            } else {
                if (iIndexOf == 0) {
                    throw new ParseException(0, "dc.ref.syntax.error");
                }
                module = parseModule(str, 0, iIndexOf, deferredDiagnosticHandler);
            }
            if (iIndexOf > 0 && str.length() == i) {
                type = null;
                member = null;
            } else if (iIndexOf2 == -1) {
                if (iIndexOf3 == -1 && mode != Mode.MEMBER_REQUIRED) {
                    type = parseType(str, i, str.length(), deferredDiagnosticHandler);
                    member = null;
                } else {
                    if (mode == Mode.MEMBER_DISALLOWED) {
                        throw new ParseException(iIndexOf2, "dc.ref.unexpected.input");
                    }
                    member = parseMember(str, i, iIndexOf3 > -1 ? iIndexOf3 : str.length(), deferredDiagnosticHandler);
                    type = null;
                }
            } else {
                if (mode == Mode.MEMBER_DISALLOWED) {
                    throw new ParseException(iIndexOf2, "dc.ref.unexpected.input");
                }
                JCTree type2 = iIndexOf2 == i ? null : parseType(str, i, iIndexOf2, deferredDiagnosticHandler);
                if (str.indexOf("#", i2) != i2) {
                    member = iIndexOf3 == -1 ? parseMember(str, i2, str.length(), deferredDiagnosticHandler) : parseMember(str, i2, iIndexOf3, deferredDiagnosticHandler);
                    type = type2;
                } else {
                    if (mode != Mode.MEMBER_OPTIONAL) {
                        throw new ParseException(i2, "dc.ref.unexpected.input");
                    }
                    type = type2;
                    member = null;
                }
            }
            if (iIndexOf3 != -1) {
                int iIndexOf4 = str.indexOf(")", iIndexOf3);
                if (iIndexOf4 != str.length() - 1) {
                    throw new ParseException(iIndexOf4, "dc.ref.bad.parens");
                }
                params = parseParams(str, i3, iIndexOf4, deferredDiagnosticHandler);
            }
            this.fac.log.popDiagnosticHandler(deferredDiagnosticHandler);
            return new Reference(module, type, member, params);
        } catch (Throwable th) {
            this.fac.log.popDiagnosticHandler(deferredDiagnosticHandler);
            throw th;
        }
    }

    public static class TypeAnnotationFinder extends TreeScanner<Tree, Void> {
        @Override // com.sun.source.util.TreeScanner, com.sun.source.tree.TreeVisitor
        public Tree visitAnnotatedType(AnnotatedTypeTree annotatedTypeTree, Void r2) {
            return annotatedTypeTree;
        }

        @Override // com.sun.source.util.TreeScanner
        public Tree reduce(Tree tree, Tree tree2) {
            return tree != null ? tree : tree2;
        }
    }
}
