package com.sun.tools.javac.tree;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.source.tree.MemberReferenceTree;
import com.sun.source.tree.ModuleTree;
import com.sun.tools.javac.code.BoundKind;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.util.Convert;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import defpackage.u8i;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.io.Writer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Pretty extends JCTree.Visitor {
    private static final int PREFERRED_LENGTH = 20;
    private static final String trimSequence = "[...]";
    Name enclClassName;
    Writer out;
    int prec;
    private final boolean sourceOutput;
    public int width = 4;
    int lmargin = 0;
    DocCommentTable docComments = null;
    String lineSep = System.getProperty("line.separator");

    /* JADX INFO: renamed from: com.sun.tools.javac.tree.Pretty$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;

        static {
            int[] iArr = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr;
            try {
                iArr[TypeTag.INT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.DOUBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CHAR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BOOLEAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BOT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BYTE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.SHORT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.VOID.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            int[] iArr2 = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr2;
            try {
                iArr2[JCTree.Tag.POS.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NEG.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NOT.ordinal()] = 3;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.COMPL.ordinal()] = 4;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PREINC.ordinal()] = 5;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PREDEC.ordinal()] = 6;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.POSTINC.ordinal()] = 7;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.POSTDEC.ordinal()] = 8;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NULLCHK.ordinal()] = 9;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.OR.ordinal()] = 10;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.AND.ordinal()] = 11;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.EQ.ordinal()] = 12;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NE.ordinal()] = 13;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.LT.ordinal()] = 14;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.GT.ordinal()] = 15;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.LE.ordinal()] = 16;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.GE.ordinal()] = 17;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.BITOR.ordinal()] = 18;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.BITXOR.ordinal()] = 19;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.BITAND.ordinal()] = 20;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.SL.ordinal()] = 21;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.SR.ordinal()] = 22;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.USR.ordinal()] = 23;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PLUS.ordinal()] = 24;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.MINUS.ordinal()] = 25;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.MUL.ordinal()] = 26;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.DIV.ordinal()] = 27;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.MOD.ordinal()] = 28;
            } catch (NoSuchFieldError unused38) {
            }
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.tree.Pretty$1UsedVisitor, reason: invalid class name */
    public class C1UsedVisitor extends TreeScanner {
        boolean result = false;
        final /* synthetic */ Pretty this$0;
        final /* synthetic */ Symbol val$t;

        public C1UsedVisitor(Pretty pretty, Symbol symbol) {
            this.val$t = symbol;
            this.this$0 = pretty;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner
        public void scan(JCTree jCTree) {
            if (jCTree == null || this.result) {
                return;
            }
            jCTree.accept(this);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIdent(JCTree.JCIdent jCIdent) {
            if (jCIdent.sym == this.val$t) {
                this.result = true;
            }
        }
    }

    public Pretty(Writer writer, boolean z) {
        this.out = writer;
        this.sourceOutput = z;
    }

    public static int lineEndPos(String str, int i) {
        int iIndexOf = str.indexOf(10, i);
        return iIndexOf < 0 ? str.length() : iIndexOf;
    }

    private void printBaseElementType(JCTree jCTree) throws IOException {
        printExpr(TreeInfo.innermostType(jCTree, false));
    }

    private void printBrackets(JCTree jCTree) throws IOException {
        while (true) {
            if (jCTree.hasTag(JCTree.Tag.ANNOTATED_TYPE)) {
                JCTree.JCAnnotatedType jCAnnotatedType = (JCTree.JCAnnotatedType) jCTree;
                JCTree.JCExpression jCExpression = jCAnnotatedType.underlyingType;
                if (jCExpression.hasTag(JCTree.Tag.TYPEARRAY)) {
                    print(' ');
                    printTypeAnnotations(jCAnnotatedType.annotations);
                }
                jCTree = jCExpression;
            }
            if (!jCTree.hasTag(JCTree.Tag.TYPEARRAY)) {
                return;
            }
            print("[]");
            jCTree = ((JCTree.JCArrayTypeTree) jCTree).elemtype;
        }
    }

    public static String toSimpleString(JCTree jCTree, int i) {
        StringWriter stringWriter = new StringWriter();
        try {
            new Pretty(stringWriter, false).printExpr(jCTree);
            String strReplaceAll = stringWriter.toString().trim().replaceAll("\\s+", " ").replaceAll("/\\*missing\\*/", "");
            if (strReplaceAll.length() < i) {
                return strReplaceAll;
            }
            int i2 = i - 5;
            int i3 = (i2 * 2) / 3;
            return strReplaceAll.substring(0, i3) + trimSequence + strReplaceAll.substring(strReplaceAll.length() - (i2 - i3));
        } catch (IOException e) {
            x01.a(e);
            return null;
        }
    }

    public void align() throws IOException {
        for (int i = 0; i < this.lmargin; i++) {
            this.out.write(" ");
        }
    }

    public void close(int i, int i2) throws IOException {
        if (i2 < i) {
            this.out.write(")");
        }
    }

    public void indent() {
        this.lmargin += this.width;
    }

    public boolean isEnumerator(JCTree jCTree) {
        return jCTree.hasTag(JCTree.Tag.VARDEF) && (((JCTree.JCVariableDecl) jCTree).mods.flags & 16384) != 0;
    }

    public boolean isUsed(Symbol symbol, JCTree jCTree) {
        C1UsedVisitor c1UsedVisitor = new C1UsedVisitor(this, symbol);
        c1UsedVisitor.scan(jCTree);
        return c1UsedVisitor.result;
    }

    public void open(int i, int i2) throws IOException {
        if (i2 < i) {
            this.out.write("(");
        }
    }

    public String operatorName(JCTree.Tag tag) {
        switch (AnonymousClass1.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[tag.ordinal()]) {
            case 1:
                return "+";
            case 2:
                return "-";
            case 3:
                return "!";
            case 4:
                return "~";
            case 5:
                return "++";
            case 6:
                return "--";
            case 7:
                return "++";
            case 8:
                return "--";
            case 9:
                return "<*nullchk*>";
            case 10:
                return "||";
            case 11:
                return "&&";
            case 12:
                return "==";
            case 13:
                return "!=";
            case 14:
                return "<";
            case 15:
                return ">";
            case 16:
                return "<=";
            case 17:
                return ">=";
            case 18:
                return "|";
            case 19:
                return "^";
            case 20:
                return "&";
            case 21:
                return "<<";
            case 22:
                return ">>";
            case 23:
                return ">>>";
            case 24:
                return "+";
            case 25:
                return "-";
            case 26:
                return "*";
            case 27:
                return PsuedoNames.PSEUDONAME_ROOT;
            case 28:
                return "%";
            default:
                throw new Error();
        }
    }

    public void print(Object obj) throws IOException {
        this.out.write(Convert.escapeUnicode(obj.toString()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void printAnnotations(List<JCTree.JCAnnotation> list) throws IOException {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            printStat((JCTree) list2.head);
            println();
            align();
        }
    }

    public void printBlock(List<? extends JCTree> list) throws IOException {
        print('{');
        println();
        indent();
        printStats(list);
        undent();
        align();
        print('}');
    }

    public void printDocComment(JCTree jCTree) throws IOException {
        String commentText;
        DocCommentTable docCommentTable = this.docComments;
        if (docCommentTable == null || (commentText = docCommentTable.getCommentText(jCTree)) == null) {
            return;
        }
        print("/**");
        println();
        int i = 0;
        int iLineEndPos = lineEndPos(commentText, 0);
        while (i < commentText.length()) {
            align();
            print(" *");
            if (i < commentText.length() && commentText.charAt(i) > ' ') {
                print(' ');
            }
            print(commentText.substring(i, iLineEndPos));
            println();
            i = iLineEndPos + 1;
            iLineEndPos = lineEndPos(commentText, i);
        }
        align();
        print(" */");
        println();
        align();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void printEnumBody(List<JCTree> list) throws IOException {
        print('{');
        println();
        indent();
        boolean z = true;
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            if (isEnumerator((JCTree) list2.head)) {
                if (!z) {
                    print(',');
                    println();
                }
                align();
                printStat((JCTree) list2.head);
                z = false;
            }
        }
        print(';');
        println();
        for (List list3 = list; list3.nonEmpty(); list3 = list3.tail) {
            if (!isEnumerator((JCTree) list3.head)) {
                align();
                printStat((JCTree) list3.head);
                println();
            }
        }
        undent();
        align();
        print('}');
    }

    public void printExpr(JCTree jCTree, int i) throws IOException {
        int i2 = this.prec;
        try {
            try {
                this.prec = i;
                if (jCTree == null) {
                    print("/*missing*/");
                } else {
                    jCTree.accept(this);
                }
                this.prec = i2;
            } catch (UncheckedIOException e) {
                throw e.getCause();
            }
        } catch (Throwable th) {
            this.prec = i2;
            throw th;
        }
    }

    public <T extends JCTree> void printExprs(List<T> list, String str) throws IOException {
        if (!list.nonEmpty()) {
            return;
        }
        printExpr(list.head);
        while (true) {
            list = list.tail;
            if (!list.nonEmpty()) {
                return;
            }
            print(str);
            printExpr(list.head);
        }
    }

    public void printFlags(long j) throws IOException {
        if ((4096 & j) != 0) {
            print("/*synthetic*/ ");
        }
        print(TreeInfo.flagNames(j));
        if ((Flags.ExtendedStandardFlags & j) != 0) {
            print(' ');
        }
        if ((j & 8192) != 0) {
            print('@');
        }
    }

    public void printPattern(JCTree jCTree) throws IOException {
        printExpr(jCTree);
    }

    public void printStat(JCTree jCTree) throws IOException {
        printExpr(jCTree, -1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void printStats(List<? extends JCTree> list) throws IOException {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            align();
            printStat((JCTree) list2.head);
            println();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void printTypeAnnotations(List<JCTree.JCAnnotation> list) throws IOException {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            printExpr((JCTree) list2.head);
            print(' ');
        }
    }

    public void printTypeParameters(List<JCTree.JCTypeParameter> list) throws IOException {
        if (list.nonEmpty()) {
            print('<');
            printExprs(list);
            print('>');
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void printUnit(JCTree.JCCompilationUnit jCCompilationUnit, JCTree.JCClassDecl jCClassDecl) throws IOException {
        this.docComments = jCCompilationUnit.docComments;
        printDocComment(jCCompilationUnit);
        boolean z = true;
        for (List list = jCCompilationUnit.defs; list.nonEmpty() && (jCClassDecl == null || ((JCTree) list.head).hasTag(JCTree.Tag.IMPORT) || ((JCTree) list.head).hasTag(JCTree.Tag.PACKAGEDEF)); list = list.tail) {
            A a = list.head;
            if (a instanceof JCTree.JCImportBase) {
                JCTree.JCImportBase jCImportBase = (JCTree.JCImportBase) a;
                Name name = TreeInfo.name(jCImportBase.getQualifiedIdentifier());
                if (name == name.table.names.asterisk || jCClassDecl == null || (jCImportBase instanceof JCTree.JCModuleImport) || isUsed(TreeInfo.symbol(jCImportBase.getQualifiedIdentifier()), jCClassDecl)) {
                    if (z) {
                        println();
                        z = false;
                    }
                    printStat(jCImportBase);
                }
            } else {
                printStat((JCTree) a);
            }
        }
        if (jCClassDecl != null) {
            printStat(jCClassDecl);
            println();
        }
    }

    public void println() throws IOException {
        this.out.write(this.lineSep);
    }

    public void undent() {
        this.lmargin -= this.width;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnnotatedType(JCTree.JCAnnotatedType jCAnnotatedType) {
        try {
            boolean zHasTag = jCAnnotatedType.underlyingType.hasTag(JCTree.Tag.SELECT);
            JCTree.JCExpression jCExpression = jCAnnotatedType.underlyingType;
            if (zHasTag) {
                JCTree.JCFieldAccess jCFieldAccess = (JCTree.JCFieldAccess) jCExpression;
                printExpr(jCFieldAccess.selected, 15);
                print('.');
                printTypeAnnotations(jCAnnotatedType.annotations);
                print(jCFieldAccess.name);
                return;
            }
            if (jCExpression.hasTag(JCTree.Tag.TYPEARRAY)) {
                printBaseElementType(jCAnnotatedType);
                printBrackets(jCAnnotatedType);
            } else {
                printTypeAnnotations(jCAnnotatedType.annotations);
                printExpr(jCAnnotatedType.underlyingType);
            }
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
        try {
            print('@');
            printExpr(jCAnnotation.annotationType);
            if (jCAnnotation.args.isEmpty()) {
                return;
            }
            print('(');
            printExprs(jCAnnotation.args);
            print(')');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnyPattern(JCTree.JCAnyPattern jCAnyPattern) {
        try {
            print('_');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
        try {
            boolean zIsEmpty = jCMethodInvocation.typeargs.isEmpty();
            JCTree.JCExpression jCExpression = jCMethodInvocation.meth;
            if (zIsEmpty) {
                printExpr(jCExpression);
            } else if (jCExpression.hasTag(JCTree.Tag.SELECT)) {
                JCTree.JCFieldAccess jCFieldAccess = (JCTree.JCFieldAccess) jCMethodInvocation.meth;
                printExpr(jCFieldAccess.selected);
                print(".<");
                printExprs(jCMethodInvocation.typeargs);
                print('>');
                print(jCFieldAccess.name);
            } else {
                print('<');
                printExprs(jCMethodInvocation.typeargs);
                print('>');
                printExpr(jCMethodInvocation.meth);
            }
            print('(');
            printExprs(jCMethodInvocation.args);
            print(')');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssert(JCTree.JCAssert jCAssert) {
        try {
            print("assert ");
            printExpr(jCAssert.cond);
            if (jCAssert.detail != null) {
                print(" : ");
                printExpr(jCAssert.detail);
            }
            print(';');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssign(JCTree.JCAssign jCAssign) {
        try {
            open(this.prec, 1);
            printExpr(jCAssign.lhs, 2);
            print(" = ");
            printExpr(jCAssign.rhs, 1);
            close(this.prec, 1);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssignop(JCTree.JCAssignOp jCAssignOp) {
        try {
            open(this.prec, 2);
            printExpr(jCAssignOp.lhs, 3);
            print(' ');
            print(operatorName(jCAssignOp.getTag().noAssignOp()));
            print("= ");
            printExpr(jCAssignOp.rhs, 2);
            close(this.prec, 2);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBinary(JCTree.JCBinary jCBinary) {
        try {
            int iOpPrec = TreeInfo.opPrec(jCBinary.getTag());
            String strOperatorName = operatorName(jCBinary.getTag());
            open(this.prec, iOpPrec);
            printExpr(jCBinary.lhs, iOpPrec);
            print(' ');
            print(strOperatorName);
            print(' ');
            printExpr(jCBinary.rhs, iOpPrec + 1);
            close(this.prec, iOpPrec);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBindingPattern(JCTree.JCBindingPattern jCBindingPattern) {
        try {
            printExpr(jCBindingPattern.var);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBlock(JCTree.JCBlock jCBlock) {
        try {
            printFlags(jCBlock.flags);
            printBlock(jCBlock.stats);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBreak(JCTree.JCBreak jCBreak) {
        try {
            print(PsiKeyword.BREAK);
            if (jCBreak.label != null) {
                print(' ');
                print(jCBreak.label);
            }
            print(';');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitCase(JCTree.JCCase jCCase) {
        try {
            if (jCCase.labels.size() == 1 && jCCase.labels.get(0).hasTag(JCTree.Tag.DEFAULTCASELABEL)) {
                print("default");
            } else {
                print("case ");
                printExprs(jCCase.labels);
            }
            if (jCCase.guard != null) {
                print(" when ");
                print(jCCase.guard);
            }
            if (jCCase.caseKind == JCTree.JCCase.STATEMENT) {
                print(':');
                println();
                indent();
                printStats(jCCase.stats);
                undent();
                align();
                return;
            }
            print(" -> ");
            int size = jCCase.stats.size();
            List<JCTree.JCStatement> list = jCCase.stats;
            if (size == 1) {
                printStat(list.head);
            } else {
                printBlock(list);
            }
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitCatch(JCTree.JCCatch jCCatch) {
        try {
            print(" catch (");
            printExpr(jCCatch.param);
            print(") ");
            printStat(jCCatch.body);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        try {
            println();
            align();
            printDocComment(jCClassDecl);
            printAnnotations(jCClassDecl.mods.annotations);
            printFlags(jCClassDecl.mods.flags & (-513));
            Name name = this.enclClassName;
            this.enclClassName = jCClassDecl.name;
            long j = jCClassDecl.mods.flags;
            if ((512 & j) != 0) {
                print("interface ");
                print(jCClassDecl.name);
                printTypeParameters(jCClassDecl.typarams);
                if (jCClassDecl.implementing.nonEmpty()) {
                    print(" extends ");
                    printExprs(jCClassDecl.implementing);
                }
                if (jCClassDecl.permitting.nonEmpty()) {
                    print(" permits ");
                    printExprs(jCClassDecl.permitting);
                }
            } else {
                if ((j & 16384) != 0) {
                    print("enum ");
                } else {
                    print("class ");
                }
                print(jCClassDecl.name);
                printTypeParameters(jCClassDecl.typarams);
                if (jCClassDecl.extending != null) {
                    print(" extends ");
                    printExpr(jCClassDecl.extending);
                }
                if (jCClassDecl.implementing.nonEmpty()) {
                    print(" implements ");
                    printExprs(jCClassDecl.implementing);
                }
                if (jCClassDecl.permitting.nonEmpty()) {
                    print(" permits ");
                    printExprs(jCClassDecl.permitting);
                }
            }
            print(' ');
            long j2 = jCClassDecl.mods.flags & 16384;
            List<JCTree> list = jCClassDecl.defs;
            if (j2 != 0) {
                printEnumBody(list);
            } else {
                printBlock(list);
            }
            this.enclClassName = name;
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConditional(JCTree.JCConditional jCConditional) {
        try {
            open(this.prec, 3);
            printExpr(jCConditional.cond, 4);
            print(" ? ");
            printExpr(jCConditional.truepart);
            print(" : ");
            printExpr(jCConditional.falsepart, 3);
            close(this.prec, 3);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConstantCaseLabel(JCTree.JCConstantCaseLabel jCConstantCaseLabel) {
        try {
            print(jCConstantCaseLabel.expr);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitContinue(JCTree.JCContinue jCContinue) {
        try {
            print(PsiKeyword.CONTINUE);
            if (jCContinue.label != null) {
                print(' ');
                print(jCContinue.label);
            }
            print(';');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitDefaultCaseLabel(JCTree.JCDefaultCaseLabel jCDefaultCaseLabel) {
        try {
            print("default");
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitDoLoop(JCTree.JCDoWhileLoop jCDoWhileLoop) {
        try {
            print("do ");
            printStat(jCDoWhileLoop.body);
            align();
            print(" while ");
            if (jCDoWhileLoop.cond.hasTag(JCTree.Tag.PARENS)) {
                printExpr(jCDoWhileLoop.cond);
            } else {
                print('(');
                printExpr(jCDoWhileLoop.cond);
                print(')');
            }
            print(';');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitErroneous(JCTree.JCErroneous jCErroneous) {
        try {
            print("(ERROR)");
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitExec(JCTree.JCExpressionStatement jCExpressionStatement) {
        try {
            printExpr(jCExpressionStatement.expr);
            if (this.prec == -1) {
                print(';');
            }
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitExports(JCTree.JCExports jCExports) {
        try {
            print("exports ");
            printExpr(jCExports.qualid);
            if (jCExports.moduleNames != null) {
                print(" to ");
                printExprs(jCExports.moduleNames);
            }
            print(';');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForLoop(JCTree.JCForLoop jCForLoop) {
        try {
            print("for (");
            if (jCForLoop.init.nonEmpty()) {
                boolean zHasTag = jCForLoop.init.head.hasTag(JCTree.Tag.VARDEF);
                List<JCTree.JCStatement> list = jCForLoop.init;
                if (zHasTag) {
                    printExpr(list.head);
                    List list2 = jCForLoop.init;
                    while (true) {
                        list2 = list2.tail;
                        if (!list2.nonEmpty()) {
                            break;
                        }
                        JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) list2.head;
                        print(", ");
                        print(jCVariableDecl.name);
                        if (jCVariableDecl.init != null) {
                            print(" = ");
                            printExpr(jCVariableDecl.init);
                        }
                    }
                } else {
                    printExprs(list);
                }
            }
            print("; ");
            JCTree.JCExpression jCExpression = jCForLoop.cond;
            if (jCExpression != null) {
                printExpr(jCExpression);
            }
            print("; ");
            printExprs(jCForLoop.step);
            print(") ");
            printStat(jCForLoop.body);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
        try {
            print("for (");
            printExpr(jCEnhancedForLoop.var);
            print(" : ");
            printExpr(jCEnhancedForLoop.expr);
            print(") ");
            printStat(jCEnhancedForLoop.body);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIdent(JCTree.JCIdent jCIdent) {
        try {
            print(jCIdent.name);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIf(JCTree.JCIf jCIf) {
        try {
            print("if ");
            if (jCIf.cond.hasTag(JCTree.Tag.PARENS)) {
                printExpr(jCIf.cond);
            } else {
                print('(');
                printExpr(jCIf.cond);
                print(')');
            }
            print(' ');
            printStat(jCIf.thenpart);
            if (jCIf.elsepart != null) {
                print(" else ");
                printStat(jCIf.elsepart);
            }
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitImport(JCTree.JCImport jCImport) {
        try {
            print("import ");
            if (jCImport.staticImport) {
                print("static ");
            }
            printExpr(jCImport.qualid);
            print(';');
            println();
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIndexed(JCTree.JCArrayAccess jCArrayAccess) {
        try {
            printExpr(jCArrayAccess.indexed, 15);
            print('[');
            printExpr(jCArrayAccess.index);
            print(']');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLabelled(JCTree.JCLabeledStatement jCLabeledStatement) {
        try {
            print(jCLabeledStatement.label);
            print(": ");
            printStat(jCLabeledStatement.body);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLambda(JCTree.JCLambda jCLambda) {
        try {
            print('(');
            if (jCLambda.paramKind == JCTree.JCLambda.ParameterKind.EXPLICIT) {
                printExprs(jCLambda.params);
            } else {
                String str = "";
                for (JCTree.JCVariableDecl jCVariableDecl : jCLambda.params) {
                    print(str);
                    print(jCVariableDecl.name);
                    str = ",";
                }
            }
            print(")->");
            printExpr(jCLambda.body);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLetExpr(JCTree.LetExpr letExpr) {
        try {
            print("(let ");
            print(letExpr.defs);
            print(" in ");
            print(letExpr.expr);
            print(')');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLiteral(JCTree.JCLiteral jCLiteral) {
        try {
            switch (AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[jCLiteral.typetag.ordinal()]) {
                case 1:
                    print(jCLiteral.value.toString());
                    break;
                case 2:
                    print(jCLiteral.value);
                    print('L');
                    break;
                case 3:
                    print(jCLiteral.value);
                    print('F');
                    break;
                case 4:
                    print(jCLiteral.value.toString());
                    break;
                case 5:
                    print('\'');
                    print(Convert.quote((char) ((Number) jCLiteral.value).intValue(), true));
                    print('\'');
                    break;
                case 6:
                    print(((Number) jCLiteral.value).intValue() == 1 ? "true" : "false");
                    break;
                case 7:
                    print(PsiKeyword.NULL);
                    break;
                default:
                    print('\"');
                    print(Convert.quote(jCLiteral.value.toString()));
                    print('\"');
                    break;
            }
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
        try {
            Name name = jCMethodDecl.name;
            if (name == name.table.names.init && this.enclClassName == null && this.sourceOutput) {
                return;
            }
            println();
            align();
            printDocComment(jCMethodDecl);
            printExpr(jCMethodDecl.mods);
            printTypeParameters(jCMethodDecl.typarams);
            Name name2 = jCMethodDecl.name;
            if (name2 == name2.table.names.init) {
                Name name3 = this.enclClassName;
                if (name3 != null) {
                    name2 = name3;
                }
                print(name2);
            } else {
                printExpr(jCMethodDecl.restype);
                print(' ');
                print(jCMethodDecl.name);
            }
            print('(');
            JCTree jCTree = jCMethodDecl.recvparam;
            if (jCTree != null) {
                printExpr(jCTree);
                if (jCMethodDecl.params.size() > 0) {
                    print(", ");
                }
            }
            printExprs(jCMethodDecl.params);
            print(')');
            if (jCMethodDecl.thrown.nonEmpty()) {
                print(" throws ");
                printExprs(jCMethodDecl.thrown);
            }
            if (jCMethodDecl.defaultValue != null) {
                print(" default ");
                printExpr(jCMethodDecl.defaultValue);
            }
            if (jCMethodDecl.body == null) {
                print(';');
            } else {
                print(' ');
                printStat(jCMethodDecl.body);
            }
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModifiers(JCTree.JCModifiers jCModifiers) {
        try {
            printAnnotations(jCModifiers.annotations);
            printFlags(jCModifiers.flags);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModuleDef(JCTree.JCModuleDecl jCModuleDecl) {
        try {
            printDocComment(jCModuleDecl);
            printAnnotations(jCModuleDecl.mods.annotations);
            if (jCModuleDecl.getModuleType() == ModuleTree.ModuleKind.OPEN) {
                print("open ");
            }
            print("module ");
            printExpr(jCModuleDecl.qualId);
            if (jCModuleDecl.directives == null) {
                print(';');
            } else {
                print(' ');
                printBlock(jCModuleDecl.directives);
            }
            println();
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModuleImport(JCTree.JCModuleImport jCModuleImport) {
        try {
            print("import module ");
            printExpr(jCModuleImport.module);
            print(';');
            println();
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewArray(JCTree.JCNewArray jCNewArray) {
        try {
            if (jCNewArray.elemtype != null) {
                print("new ");
                JCTree.JCExpression jCExpression = jCNewArray.elemtype;
                printBaseElementType(jCExpression);
                if (!jCNewArray.annotations.isEmpty()) {
                    print(' ');
                    printTypeAnnotations(jCNewArray.annotations);
                }
                if (jCNewArray.elems != null) {
                    print("[]");
                }
                List<List<JCTree.JCAnnotation>> list = jCNewArray.dimAnnotations;
                int i = 0;
                for (List list2 = jCNewArray.dims; list2.nonEmpty(); list2 = list2.tail) {
                    if (list.size() > i && !list.get(i).isEmpty()) {
                        print(' ');
                        printTypeAnnotations(list.get(i));
                    }
                    print('[');
                    i++;
                    printExpr((JCTree) list2.head);
                    print(']');
                }
                printBrackets(jCExpression);
            }
            if (jCNewArray.elems != null) {
                print('{');
                printExprs(jCNewArray.elems);
                print('}');
            }
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x006f  */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewClass(JCTree.JCNewClass jCNewClass) {
        try {
            JCTree.JCExpression jCExpression = jCNewClass.encl;
            if (jCExpression != null) {
                printExpr(jCExpression);
                print('.');
            }
            print("new ");
            if (!jCNewClass.typeargs.isEmpty()) {
                print('<');
                printExprs(jCNewClass.typeargs);
                print('>');
            }
            JCTree.JCClassDecl jCClassDecl = jCNewClass.def;
            if (jCClassDecl != null && jCClassDecl.mods.annotations.nonEmpty()) {
                printTypeAnnotations(jCNewClass.def.mods.annotations);
            }
            printExpr(jCNewClass.clazz);
            print('(');
            printExprs(jCNewClass.args);
            print(')');
            JCTree.JCClassDecl jCClassDecl2 = jCNewClass.def;
            if (jCClassDecl2 != null) {
                Name name = this.enclClassName;
                Name name2 = jCClassDecl2.name;
                if (name2 == null) {
                    Type type = jCNewClass.type;
                    if (type != null) {
                        name2 = type.tsym.name;
                        if (name2 == name2.table.names.empty) {
                            name2 = null;
                        }
                    } else {
                        name2 = null;
                    }
                }
                this.enclClassName = name2;
                if ((jCClassDecl2.mods.flags & 16384) != 0) {
                    print("/*enum*/");
                }
                printBlock(jCNewClass.def.defs);
                this.enclClassName = name;
            }
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitOpens(JCTree.JCOpens jCOpens) {
        try {
            print("opens ");
            printExpr(jCOpens.qualid);
            if (jCOpens.moduleNames != null) {
                print(" to ");
                printExprs(jCOpens.moduleNames);
            }
            print(';');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitPackageDef(JCTree.JCPackageDecl jCPackageDecl) {
        try {
            printDocComment(jCPackageDecl);
            printAnnotations(jCPackageDecl.annotations);
            if (jCPackageDecl.pid != null) {
                print("package ");
                printExpr(jCPackageDecl.pid);
                print(';');
                println();
            }
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitParens(JCTree.JCParens jCParens) {
        try {
            print('(');
            printExpr(jCParens.expr);
            print(')');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitPatternCaseLabel(JCTree.JCPatternCaseLabel jCPatternCaseLabel) {
        try {
            print(jCPatternCaseLabel.pat);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitProvides(JCTree.JCProvides jCProvides) {
        try {
            print("provides ");
            printExpr(jCProvides.serviceName);
            print(" with ");
            printExprs(jCProvides.implNames);
            print(';');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitRecordPattern(JCTree.JCRecordPattern jCRecordPattern) {
        try {
            printExpr(jCRecordPattern.deconstructor);
            print('(');
            printExprs(jCRecordPattern.nested);
            print(')');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReference(JCTree.JCMemberReference jCMemberReference) {
        try {
            printExpr(jCMemberReference.expr);
            print("::");
            if (jCMemberReference.typeargs != null) {
                print('<');
                printExprs(jCMemberReference.typeargs);
                print('>');
            }
            print(jCMemberReference.getMode() == MemberReferenceTree.ReferenceMode.INVOKE ? jCMemberReference.name : PsiKeyword.NEW);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitRequires(JCTree.JCRequires jCRequires) {
        try {
            print("requires ");
            if (jCRequires.isStaticPhase) {
                print("static ");
            }
            if (jCRequires.isTransitive) {
                print("transitive ");
            }
            printExpr(jCRequires.moduleName);
            print(';');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReturn(JCTree.JCReturn jCReturn) {
        try {
            print(PsiKeyword.RETURN);
            if (jCReturn.expr != null) {
                print(' ');
                printExpr(jCReturn.expr);
            }
            print(';');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
        try {
            printExpr(jCFieldAccess.selected, 15);
            print('.');
            print(jCFieldAccess.name);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSkip(JCTree.JCSkip jCSkip) {
        try {
            print(';');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitch(JCTree.JCSwitch jCSwitch) {
        try {
            print("switch ");
            if (jCSwitch.selector.hasTag(JCTree.Tag.PARENS)) {
                printExpr(jCSwitch.selector);
            } else {
                print('(');
                printExpr(jCSwitch.selector);
                print(')');
            }
            print(" {");
            println();
            printStats(jCSwitch.cases);
            align();
            print('}');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
        try {
            print("switch ");
            if (jCSwitchExpression.selector.hasTag(JCTree.Tag.PARENS)) {
                printExpr(jCSwitchExpression.selector);
            } else {
                print('(');
                printExpr(jCSwitchExpression.selector);
                print(')');
            }
            print(" {");
            println();
            printStats(jCSwitchExpression.cases);
            align();
            print('}');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSynchronized(JCTree.JCSynchronized jCSynchronized) {
        try {
            print("synchronized ");
            if (jCSynchronized.lock.hasTag(JCTree.Tag.PARENS)) {
                printExpr(jCSynchronized.lock);
            } else {
                print('(');
                printExpr(jCSynchronized.lock);
                print(')');
            }
            print(' ');
            printStat(jCSynchronized.body);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitThrow(JCTree.JCThrow jCThrow) {
        try {
            print("throw ");
            printExpr(jCThrow.expr);
            print(';');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTopLevel(JCTree.JCCompilationUnit jCCompilationUnit) {
        try {
            printUnit(jCCompilationUnit, null);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTree(JCTree jCTree) {
        try {
            print("(UNKNOWN: ");
            print(jCTree.getTag());
            print(')');
            println();
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTry(JCTree.JCTry jCTry) {
        try {
            print("try ");
            if (jCTry.resources.nonEmpty()) {
                print('(');
                boolean z = true;
                for (JCTree jCTree : jCTry.resources) {
                    if (!z) {
                        println();
                        indent();
                    }
                    printStat(jCTree);
                    z = false;
                }
                print(") ");
            }
            printStat(jCTry.body);
            for (List list = jCTry.catchers; list.nonEmpty(); list = list.tail) {
                printStat((JCTree) list.head);
            }
            if (jCTry.finalizer != null) {
                print(" finally ");
                printStat(jCTry.finalizer);
            }
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeApply(JCTree.JCTypeApply jCTypeApply) {
        try {
            printExpr(jCTypeApply.clazz);
            print('<');
            printExprs(jCTypeApply.arguments);
            print('>');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeArray(JCTree.JCArrayTypeTree jCArrayTypeTree) {
        try {
            printBaseElementType(jCArrayTypeTree);
            printBrackets(jCArrayTypeTree);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeBoundKind(JCTree.TypeBoundKind typeBoundKind) {
        try {
            print(String.valueOf(typeBoundKind.kind));
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeCast(JCTree.JCTypeCast jCTypeCast) {
        try {
            open(this.prec, 14);
            print('(');
            printExpr(jCTypeCast.clazz);
            print(')');
            printExpr(jCTypeCast.expr, 14);
            close(this.prec, 14);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeIdent(JCTree.JCPrimitiveTypeTree jCPrimitiveTypeTree) {
        try {
            switch (AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[jCPrimitiveTypeTree.typetag.ordinal()]) {
                case 1:
                    print("int");
                    break;
                case 2:
                    print("long");
                    break;
                case 3:
                    print("float");
                    break;
                case 4:
                    print("double");
                    break;
                case 5:
                    print(PsiKeyword.CHAR);
                    break;
                case 6:
                    print("boolean");
                    break;
                case 7:
                default:
                    print("error");
                    break;
                case 8:
                    print("byte");
                    break;
                case 9:
                    print("short");
                    break;
                case 10:
                    print(PsiKeyword.VOID);
                    break;
            }
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeIntersection(JCTree.JCTypeIntersection jCTypeIntersection) {
        try {
            printExprs(jCTypeIntersection.bounds, " & ");
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeParameter(JCTree.JCTypeParameter jCTypeParameter) {
        try {
            if (jCTypeParameter.annotations.nonEmpty()) {
                printTypeAnnotations(jCTypeParameter.annotations);
            }
            print(jCTypeParameter.name);
            if (jCTypeParameter.bounds.nonEmpty()) {
                print(" extends ");
                printExprs(jCTypeParameter.bounds, " & ");
            }
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeTest(JCTree.JCInstanceOf jCInstanceOf) {
        try {
            open(this.prec, 10);
            printExpr(jCInstanceOf.expr, 10);
            print(" instanceof ");
            JCTree jCTree = jCInstanceOf.pattern;
            if (jCTree instanceof JCTree.JCPattern) {
                printPattern(jCTree);
            } else {
                printExpr(jCInstanceOf.getType(), 11);
            }
            close(this.prec, 10);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeUnion(JCTree.JCTypeUnion jCTypeUnion) {
        try {
            printExprs(jCTypeUnion.alternatives, " | ");
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitUnary(JCTree.JCUnary jCUnary) {
        try {
            int iOpPrec = TreeInfo.opPrec(jCUnary.getTag());
            String strOperatorName = operatorName(jCUnary.getTag());
            open(this.prec, iOpPrec);
            if (jCUnary.getTag().isPostUnaryOp()) {
                printExpr(jCUnary.arg, iOpPrec);
                print(strOperatorName);
            } else {
                print(strOperatorName);
                printExpr(jCUnary.arg, iOpPrec);
            }
            close(this.prec, iOpPrec);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitUses(JCTree.JCUses jCUses) {
        try {
            print("uses ");
            printExpr(jCUses.qualid);
            print(';');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
        List<JCTree.JCAnnotation> list;
        try {
            DocCommentTable docCommentTable = this.docComments;
            if (docCommentTable != null && docCommentTable.hasComment(jCVariableDecl)) {
                println();
                align();
            }
            printDocComment(jCVariableDecl);
            JCTree.JCModifiers jCModifiers = jCVariableDecl.mods;
            if ((jCModifiers.flags & 16384) == 0) {
                printExpr(jCModifiers);
                long j = jCVariableDecl.mods.flags & Flags.VARARGS;
                JCTree.JCExpression jCExpression = jCVariableDecl.vartype;
                if (j != 0) {
                    if (jCExpression instanceof JCTree.JCAnnotatedType) {
                        JCTree.JCAnnotatedType jCAnnotatedType = (JCTree.JCAnnotatedType) jCExpression;
                        list = jCAnnotatedType.annotations;
                        jCExpression = jCAnnotatedType.underlyingType;
                    } else {
                        list = null;
                    }
                    printExpr(((JCTree.JCArrayTypeTree) jCExpression).elemtype);
                    if (list != null) {
                        print(' ');
                        printTypeAnnotations(list);
                    }
                    print("... ");
                    print(jCVariableDecl.name);
                } else {
                    if (jCExpression == null && jCVariableDecl.declaredUsingVar()) {
                        print(PsiKeyword.VAR);
                    } else {
                        printExpr(jCVariableDecl.vartype);
                    }
                    print(' ');
                    if (jCVariableDecl.name.length() == 0) {
                        print('_');
                    } else {
                        print(jCVariableDecl.name);
                    }
                }
                if (jCVariableDecl.init != null) {
                    print(" = ");
                    printExpr(jCVariableDecl.init);
                }
                if (this.prec == -1) {
                    print(';');
                    return;
                }
                return;
            }
            print("/*public static final*/ ");
            print(jCVariableDecl.name);
            JCTree.JCExpression jCExpression2 = jCVariableDecl.init;
            if (jCExpression2 != null) {
                if (!jCExpression2.hasTag(JCTree.Tag.NEWCLASS)) {
                    print(" /* = ");
                    printExpr(jCVariableDecl.init);
                    print(" */");
                    return;
                }
                JCTree.JCNewClass jCNewClass = (JCTree.JCNewClass) jCVariableDecl.init;
                if (this.sourceOutput) {
                    print(" /*enum*/ ");
                    List<JCTree.JCExpression> list2 = jCNewClass.args;
                    if (list2 != null && list2.nonEmpty()) {
                        print('(');
                        print(jCNewClass.args);
                        print(')');
                    }
                    JCTree.JCClassDecl jCClassDecl = jCNewClass.def;
                    if (jCClassDecl == null || jCClassDecl.defs == null) {
                        return;
                    }
                    print(' ');
                    printBlock(jCNewClass.def.defs);
                    return;
                }
                print(" /* = ");
                print("new ");
                JCTree.JCClassDecl jCClassDecl2 = jCNewClass.def;
                if (jCClassDecl2 != null && jCClassDecl2.mods.annotations.nonEmpty()) {
                    printTypeAnnotations(jCNewClass.def.mods.annotations);
                }
                printExpr(jCNewClass.clazz);
                print('(');
                printExprs(jCNewClass.args);
                print(')');
                print(" */");
                print(" /*enum*/ ");
                List<JCTree.JCExpression> list3 = jCNewClass.args;
                if (list3 != null && list3.nonEmpty()) {
                    print('(');
                    printExprs(jCNewClass.args);
                    print(')');
                }
                JCTree.JCClassDecl jCClassDecl3 = jCNewClass.def;
                if (jCClassDecl3 == null || jCClassDecl3.defs == null) {
                    return;
                }
                print(' ');
                printBlock(jCNewClass.def.defs);
            }
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWhileLoop(JCTree.JCWhileLoop jCWhileLoop) {
        try {
            print("while ");
            if (jCWhileLoop.cond.hasTag(JCTree.Tag.PARENS)) {
                printExpr(jCWhileLoop.cond);
            } else {
                print('(');
                printExpr(jCWhileLoop.cond);
                print(')');
            }
            print(' ');
            printStat(jCWhileLoop.body);
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWildcard(JCTree.JCWildcard jCWildcard) {
        try {
            print(jCWildcard.kind);
            if (jCWildcard.kind.kind != BoundKind.UNBOUND) {
                printExpr(jCWildcard.inner);
            }
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitYield(JCTree.JCYield jCYield) {
        try {
            print(PsiKeyword.YIELD);
            print(' ');
            printExpr(jCYield.value);
            print(';');
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    private void print(char c) throws IOException {
        this.out.write(c);
    }

    public void printExpr(JCTree jCTree) throws IOException {
        printExpr(jCTree, 0);
    }

    public <T extends JCTree> void printExprs(List<T> list) throws IOException {
        printExprs(list, ", ");
    }

    public static String toSimpleString(JCTree jCTree) {
        return toSimpleString(jCTree, 20);
    }
}
