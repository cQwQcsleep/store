package com.sun.tools.javac.tree;

import com.sun.source.tree.Tree;
import com.sun.source.util.TreePath;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import defpackage.s22;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import javax.lang.model.element.ElementKind;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TreeInfo {
    public static final int addPrec = 12;
    public static final int andPrec = 5;
    public static final int assignPrec = 1;
    public static final int assignopPrec = 2;
    public static final int bitandPrec = 8;
    public static final int bitorPrec = 6;
    public static final int bitxorPrec = 7;
    public static final int condPrec = 3;
    public static final int eqPrec = 9;
    public static final int mulPrec = 13;
    public static final int noPrec = 0;
    public static final int notExpression = -1;
    public static final int orPrec = 4;
    public static final int ordPrec = 10;
    public static final int postfixPrec = 15;
    public static final int precCount = 16;
    public static final int prefixPrec = 14;
    public static final int shiftPrec = 11;

    /* JADX INFO: renamed from: com.sun.tools.javac.tree.TreeInfo$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;

        static {
            int[] iArr = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr;
            try {
                iArr[JCTree.Tag.APPLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NEWCLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PARENS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.IDENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.SELECT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.TYPEAPPLY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.ANNOTATED_TYPE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.VARDEF.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.REFERENCE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PREINC.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PREDEC.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.POSTINC.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.POSTDEC.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.ASSIGN.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.BITOR_ASG.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.BITXOR_ASG.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.BITAND_ASG.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.SL_ASG.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.SR_ASG.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.USR_ASG.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PLUS_ASG.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.MINUS_ASG.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.MUL_ASG.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.DIV_ASG.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.MOD_ASG.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.ERRONEOUS.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.TYPEARRAY.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.MODULEDEF.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PACKAGEDEF.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.OR.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.AND.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.BITOR.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.BITXOR.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.BITAND.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.EQ.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NE.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.LT.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.GT.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.LE.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.GE.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.SL.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.SR.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.USR.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PLUS.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.MINUS.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.MUL.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.DIV.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.MOD.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.CLASSDEF.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.CONDEXPR.ordinal()] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.EXEC.ordinal()] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.INDEXED.ordinal()] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.METHODDEF.ordinal()] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.TYPETEST.ordinal()] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.BINDINGPATTERN.ordinal()] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.POS.ordinal()] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NEG.ordinal()] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NOT.ordinal()] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.COMPL.ordinal()] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.CASE.ordinal()] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.CATCH.ordinal()] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.FORLOOP.ordinal()] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.FOREACHLOOP.ordinal()] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.IF.ordinal()] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.LABELLED.ordinal()] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.MODIFIERS.ordinal()] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.SYNCHRONIZED.ordinal()] = 67;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.TOPLEVEL.ordinal()] = 68;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.TRY.ordinal()] = 69;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.WILDCARD.ordinal()] = 70;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.TYPECAST.ordinal()] = 71;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.WHILELOOP.ordinal()] = 72;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.DOLOOP.ordinal()] = 73;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.SWITCH.ordinal()] = 74;
            } catch (NoSuchFieldError unused74) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.ANNOTATION.ordinal()] = 75;
            } catch (NoSuchFieldError unused75) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.TYPE_ANNOTATION.ordinal()] = 76;
            } catch (NoSuchFieldError unused76) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.TYPEPARAMETER.ordinal()] = 77;
            } catch (NoSuchFieldError unused77) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.BLOCK.ordinal()] = 78;
            } catch (NoSuchFieldError unused78) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NULLCHK.ordinal()] = 79;
            } catch (NoSuchFieldError unused79) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.EXPORTS.ordinal()] = 80;
            } catch (NoSuchFieldError unused80) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.OPENS.ordinal()] = 81;
            } catch (NoSuchFieldError unused81) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.TYPEIDENT.ordinal()] = 82;
            } catch (NoSuchFieldError unused82) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.RECORDPATTERN.ordinal()] = 83;
            } catch (NoSuchFieldError unused83) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.ANYPATTERN.ordinal()] = 84;
            } catch (NoSuchFieldError unused84) {
            }
        }
    }

    public static class ConstructorCallFinder extends TreeScanner {
        final ListBuffer<JCTree.JCMethodInvocation> calls = new ListBuffer<>();
        final Names names;

        public ConstructorCallFinder(Names names) {
            this.names = names;
        }

        public List<JCTree.JCMethodInvocation> find(JCTree.JCMethodDecl jCMethodDecl) {
            scan(jCMethodDecl);
            return this.calls.toList();
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
            Name name = TreeInfo.name(jCMethodInvocation.meth);
            Names names = this.names;
            if (name == names._this || name == names._super) {
                this.calls.append(jCMethodInvocation);
            }
            super.visitApply(jCMethodInvocation);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
        }
    }

    public enum PosKind {
        START_POS(new ToIntFunction() { // from class: sne
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return TreeInfo.getStartPos((JCTree) obj);
            }
        }),
        FIRST_STAT_POS(new ToIntFunction() { // from class: tne
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return TreeInfo.firstStatPos((JCTree) obj);
            }
        }),
        END_POS(new ToIntFunction() { // from class: une
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return TreeInfo.endPos((JCTree) obj);
            }
        });

        final ToIntFunction<JCTree> posFunc;

        PosKind(ToIntFunction toIntFunction) {
            this.posFunc = toIntFunction;
        }

        public int toPos(JCTree jCTree) {
            return this.posFunc.applyAsInt(jCTree);
        }
    }

    public static class SuperCallTranslator extends TreeTranslator {
        final Function<? super JCTree.JCExpressionStatement, ? extends JCTree.JCStatement> translator;

        public SuperCallTranslator(Function<? super JCTree.JCExpressionStatement, ? extends JCTree.JCStatement> function) {
            this.translator = function;
        }

        @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            this.result = jCClassDecl;
        }

        @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitExec(JCTree.JCExpressionStatement jCExpressionStatement) {
            if (TreeInfo.isSuperCall(jCExpressionStatement)) {
                JCTree.JCStatement jCStatementApply = this.translator.apply(jCExpressionStatement);
                this.result = jCStatementApply;
                if (jCStatementApply != null) {
                    return;
                }
            }
            super.visitExec(jCExpressionStatement);
        }

        @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
            this.result = jCLambda;
        }
    }

    public static class TypeAnnotationFinder extends TreeScanner {
        public boolean foundTypeAnno;

        private TypeAnnotationFinder() {
            this.foundTypeAnno = false;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner
        public void scan(JCTree jCTree) {
            if (this.foundTypeAnno || jCTree == null) {
                return;
            }
            super.scan(jCTree);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
            this.foundTypeAnno = this.foundTypeAnno || jCAnnotation.hasTag(JCTree.Tag.TYPE_ANNOTATION);
        }
    }

    public static /* synthetic */ boolean a(JCTree jCTree) {
        return true;
    }

    public static List<JCTree.JCExpression> args(JCTree jCTree) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i == 1) {
            return ((JCTree.JCMethodInvocation) jCTree).args;
        }
        if (i != 2) {
            return null;
        }
        return ((JCTree.JCNewClass) jCTree).args;
    }

    public static /* synthetic */ boolean b(JCTree.JCVariableDecl jCVariableDecl) {
        return (jCVariableDecl.getModifiers().flags & Flags.RECORD) == Flags.RECORD;
    }

    public static Name calledMethodName(JCTree jCTree) {
        if (!jCTree.hasTag(JCTree.Tag.EXEC)) {
            return null;
        }
        JCTree.JCExpressionStatement jCExpressionStatement = (JCTree.JCExpressionStatement) jCTree;
        if (jCExpressionStatement.expr.hasTag(JCTree.Tag.APPLY)) {
            return name(((JCTree.JCMethodInvocation) jCExpressionStatement.expr).meth);
        }
        return null;
    }

    public static boolean containsTypeAnnotation(JCTree jCTree) {
        TypeAnnotationFinder typeAnnotationFinder = new TypeAnnotationFinder();
        typeAnnotationFinder.scan(jCTree);
        return typeAnnotationFinder.foundTypeAnno;
    }

    public static JCTree declarationFor(Symbol symbol, JCTree jCTree) {
        DeclScanner declScanner = new DeclScanner(symbol);
        jCTree.accept(declScanner);
        return declScanner.result;
    }

    public static JCDiagnostic.DiagnosticPosition diagEndPos(final JCTree jCTree) {
        final int iEndPos = endPos(jCTree);
        return new JCDiagnostic.DiagnosticPosition() { // from class: com.sun.tools.javac.tree.TreeInfo.1
            @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
            public int getEndPosition(EndPosTable endPosTable) {
                return TreeInfo.getEndPos(jCTree, endPosTable);
            }

            @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
            public int getPreferredPosition() {
                return iEndPos;
            }

            @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
            public int getStartPosition() {
                return TreeInfo.getStartPos(jCTree);
            }

            @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
            public JCTree getTree() {
                return jCTree;
            }
        };
    }

    public static JCDiagnostic.DiagnosticPosition diagnosticPositionFor(final Symbol symbol, List<? extends JCTree> list) {
        return (JCDiagnostic.DiagnosticPosition) list.stream().map(new Function() { // from class: hne
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TreeInfo.diagnosticPositionFor(symbol, (JCTree) obj);
            }
        }).filter(new Predicate() { // from class: ine
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TreeInfo.m((JCDiagnostic.DiagnosticPosition) obj);
            }
        }).findFirst().get();
    }

    public static int endPos(JCTree jCTree) {
        int i;
        int i2;
        int i3;
        if (jCTree.hasTag(JCTree.Tag.BLOCK) && (i3 = ((JCTree.JCBlock) jCTree).bracePos) != -1) {
            return i3;
        }
        if (jCTree.hasTag(JCTree.Tag.SYNCHRONIZED)) {
            return endPos(((JCTree.JCSynchronized) jCTree).body);
        }
        if (!jCTree.hasTag(JCTree.Tag.TRY)) {
            if (!jCTree.hasTag(JCTree.Tag.SWITCH) || (i2 = ((JCTree.JCSwitch) jCTree).bracePos) == -1) {
                return (!jCTree.hasTag(JCTree.Tag.SWITCH_EXPRESSION) || (i = ((JCTree.JCSwitchExpression) jCTree).bracePos) == -1) ? jCTree.pos : i;
            }
            return i2;
        }
        JCTree.JCTry jCTry = (JCTree.JCTry) jCTree;
        JCTree.JCBlock jCBlock = jCTry.finalizer;
        if (jCBlock == null) {
            jCBlock = jCTry.catchers.nonEmpty() ? jCTry.catchers.last().body : jCTry.body;
        }
        return endPos(jCBlock);
    }

    public static boolean expectedExhaustive(JCTree.JCSwitch jCSwitch) {
        return jCSwitch.patternSwitch || jCSwitch.cases.stream().flatMap(new Function() { // from class: fne
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JCTree.JCCase) obj).labels.stream();
            }
        }).anyMatch(new Predicate() { // from class: gne
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TreeInfo.isNullCaseLabel((JCTree.JCCaseLabel) obj);
            }
        });
    }

    public static /* synthetic */ JCTree.JCVariableDecl f(JCTree jCTree) {
        return (JCTree.JCVariableDecl) jCTree;
    }

    public static int finalizerPos(JCTree jCTree, PosKind posKind) {
        if (jCTree.hasTag(JCTree.Tag.TRY)) {
            JCTree.JCTry jCTry = (JCTree.JCTry) jCTree;
            Assert.checkNonNull(jCTry.finalizer);
            return posKind.toPos(jCTry.finalizer);
        }
        if (jCTree.hasTag(JCTree.Tag.SYNCHRONIZED)) {
            return endPos(((JCTree.JCSynchronized) jCTree).body);
        }
        x1f.a();
        return 0;
    }

    public static JCTree.JCMethodInvocation findConstructorCall(JCTree.JCMethodDecl jCMethodDecl) {
        if (!isConstructor(jCMethodDecl) || jCMethodDecl.body == null) {
            return null;
        }
        return new ConstructorCallFinder(jCMethodDecl.name.table.names).find(jCMethodDecl).head;
    }

    public static long firstFlag(long j) {
        long j2 = 1;
        while ((j2 & j) == 0) {
            j2 <<= 1;
        }
        return j2;
    }

    public static int firstStatPos(JCTree jCTree) {
        if (jCTree.hasTag(JCTree.Tag.BLOCK)) {
            JCTree.JCBlock jCBlock = (JCTree.JCBlock) jCTree;
            if (jCBlock.stats.nonEmpty()) {
                return jCBlock.stats.head.pos;
            }
        }
        return jCTree.pos;
    }

    public static String flagNames(long j) {
        return Flags.toString(j & Flags.ExtendedStandardFlags).trim();
    }

    public static long flags(JCTree jCTree) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i == 8) {
            return ((JCTree.JCVariableDecl) jCTree).mods.flags;
        }
        if (i == 49) {
            return ((JCTree.JCClassDecl) jCTree).mods.flags;
        }
        if (i == 53) {
            return ((JCTree.JCMethodDecl) jCTree).mods.flags;
        }
        if (i != 78) {
            return 0L;
        }
        return ((JCTree.JCBlock) jCTree).flags;
    }

    public static Name fullName(JCTree jCTree) {
        Name nameFullName;
        JCTree jCTreeSkipParens = skipParens(jCTree);
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTreeSkipParens.getTag().ordinal()];
        if (i == 4) {
            return ((JCTree.JCIdent) jCTreeSkipParens).name;
        }
        if (i == 5 && (nameFullName = fullName(((JCTree.JCFieldAccess) jCTreeSkipParens).selected)) != null) {
            return nameFullName.append('.', name(jCTreeSkipParens));
        }
        return null;
    }

    public static String getCommentText(Env<?> env, JCTree jCTree) {
        DocCommentTable docCommentTable = jCTree.hasTag(JCTree.Tag.TOPLEVEL) ? ((JCTree.JCCompilationUnit) jCTree).docComments : env.toplevel.docComments;
        if (docCommentTable == null) {
            return null;
        }
        return docCommentTable.getCommentText(jCTree);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int getEndPos(JCTree jCTree, EndPosTable endPosTable) {
        if (jCTree == null) {
            return -1;
        }
        if (endPosTable == null) {
            return endPos(jCTree);
        }
        int endPos = endPosTable.getEndPos(jCTree);
        if (endPos != -1) {
            return endPos;
        }
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i == 7) {
            return getEndPos(((JCTree.JCAnnotatedType) jCTree).underlyingType, endPosTable);
        }
        if (i == 50) {
            return getEndPos(((JCTree.JCConditional) jCTree).falsepart, endPosTable);
        }
        if (i == 54) {
            return getEndPos(((JCTree.JCInstanceOf) jCTree).pattern, endPosTable);
        }
        if (i != 10 && i != 11) {
            switch (i) {
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                    break;
                case 26:
                    JCTree.JCErroneous jCErroneous = (JCTree.JCErroneous) jCTree;
                    List<? extends JCTree> list = jCErroneous.errs;
                    if (list != null && list.nonEmpty()) {
                        return getEndPos(jCErroneous.errs.last(), endPosTable);
                    }
                    return -1;
                default:
                    switch (i) {
                        default:
                            switch (i) {
                                case 56:
                                case 57:
                                case 58:
                                case 59:
                                    break;
                                case 60:
                                    return getEndPos(((JCTree.JCCase) jCTree).stats.last(), endPosTable);
                                case 61:
                                    return getEndPos(((JCTree.JCCatch) jCTree).body, endPosTable);
                                case 62:
                                    return getEndPos(((JCTree.JCForLoop) jCTree).body, endPosTable);
                                case 63:
                                    return getEndPos(((JCTree.JCEnhancedForLoop) jCTree).body, endPosTable);
                                case 64:
                                    JCTree.JCIf jCIf = (JCTree.JCIf) jCTree;
                                    JCTree.JCStatement jCStatement = jCIf.elsepart;
                                    return jCStatement == null ? getEndPos(jCIf.thenpart, endPosTable) : getEndPos(jCStatement, endPosTable);
                                case 65:
                                    return getEndPos(((JCTree.JCLabeledStatement) jCTree).body, endPosTable);
                                case 66:
                                    return getEndPos(((JCTree.JCModifiers) jCTree).annotations.last(), endPosTable);
                                case 67:
                                    return getEndPos(((JCTree.JCSynchronized) jCTree).body, endPosTable);
                                case 68:
                                    return getEndPos(((JCTree.JCCompilationUnit) jCTree).defs.last(), endPosTable);
                                case 69:
                                    JCTree.JCTry jCTry = (JCTree.JCTry) jCTree;
                                    JCTree.JCBlock jCBlock = jCTry.finalizer;
                                    if (jCBlock != null) {
                                        return getEndPos(jCBlock, endPosTable);
                                    }
                                    return !jCTry.catchers.isEmpty() ? getEndPos(jCTry.catchers.last(), endPosTable) : getEndPos(jCTry.body, endPosTable);
                                case 70:
                                    return getEndPos(((JCTree.JCWildcard) jCTree).inner, endPosTable);
                                case 71:
                                    return getEndPos(((JCTree.JCTypeCast) jCTree).expr, endPosTable);
                                case 72:
                                    return getEndPos(((JCTree.JCWhileLoop) jCTree).body, endPosTable);
                                default:
                                    return -1;
                            }
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                        case 44:
                        case 45:
                        case 46:
                        case 47:
                        case 48:
                            return getEndPos(((JCTree.JCOperatorExpression) jCTree).getOperand(JCTree.JCOperatorExpression.OperandPos.RIGHT), endPosTable);
                    }
                    break;
            }
        }
        return getEndPos(((JCTree.JCOperatorExpression) jCTree).getOperand(JCTree.JCOperatorExpression.OperandPos.RIGHT), endPosTable);
    }

    public static JCTree.JCModifiers getModifiers(JCTree jCTree) {
        JCTree jCTreeSkipParens = skipParens(jCTree);
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTreeSkipParens.getTag().ordinal()];
        if (i == 8) {
            return ((JCTree.JCVariableDecl) jCTreeSkipParens).mods;
        }
        if (i == 28) {
            return ((JCTree.JCModuleDecl) jCTreeSkipParens).mods;
        }
        if (i == 49) {
            return ((JCTree.JCClassDecl) jCTreeSkipParens).mods;
        }
        if (i != 53) {
            return null;
        }
        return ((JCTree.JCMethodDecl) jCTreeSkipParens).mods;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int getStartPos(JCTree jCTree) {
        int startPos;
        if (jCTree == null) {
            return -1;
        }
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i == 1) {
            return getStartPos(((JCTree.JCMethodInvocation) jCTree).meth);
        }
        if (i == 2) {
            JCTree.JCExpression jCExpression = ((JCTree.JCNewClass) jCTree).encl;
            if (jCExpression != null) {
                return getStartPos(jCExpression);
            }
        } else {
            if (i == 5) {
                return getStartPos(((JCTree.JCFieldAccess) jCTree).selected);
            }
            if (i == 6) {
                return getStartPos(((JCTree.JCTypeApply) jCTree).clazz);
            }
            if (i == 7) {
                JCTree.JCAnnotatedType jCAnnotatedType = (JCTree.JCAnnotatedType) jCTree;
                boolean zNonEmpty = jCAnnotatedType.annotations.nonEmpty();
                JCTree.JCExpression jCExpression2 = jCAnnotatedType.underlyingType;
                if (zNonEmpty) {
                    return (jCExpression2.hasTag(JCTree.Tag.TYPEARRAY) || jCAnnotatedType.underlyingType.hasTag(JCTree.Tag.SELECT)) ? getStartPos(jCAnnotatedType.underlyingType) : getStartPos(jCAnnotatedType.annotations.head);
                }
                return getStartPos(jCExpression2);
            }
            if (i != 8) {
                switch (i) {
                    case 12:
                    case 13:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                        return getStartPos(((JCTree.JCOperatorExpression) jCTree).getOperand(JCTree.JCOperatorExpression.OperandPos.LEFT));
                    case 14:
                        return getStartPos(((JCTree.JCAssign) jCTree).lhs);
                    case 26:
                        JCTree.JCErroneous jCErroneous = (JCTree.JCErroneous) jCTree;
                        List<? extends JCTree> list = jCErroneous.errs;
                        if (list != null && list.nonEmpty() && (startPos = getStartPos((JCTree) jCErroneous.errs.head)) != -1) {
                            return startPos;
                        }
                        break;
                    case 27:
                        return getStartPos(((JCTree.JCArrayTypeTree) jCTree).elemtype);
                    case 28:
                        JCTree.JCModuleDecl jCModuleDecl = (JCTree.JCModuleDecl) jCTree;
                        return jCModuleDecl.mods.annotations.isEmpty() ? jCModuleDecl.pos : jCModuleDecl.mods.annotations.head.pos;
                    case 29:
                        JCTree.JCPackageDecl jCPackageDecl = (JCTree.JCPackageDecl) jCTree;
                        return jCPackageDecl.annotations.isEmpty() ? jCPackageDecl.pos : jCPackageDecl.annotations.head.pos;
                    case 49:
                        int i2 = ((JCTree.JCClassDecl) jCTree).mods.pos;
                        if (i2 != -1) {
                            return i2;
                        }
                        break;
                    case 50:
                        return getStartPos(((JCTree.JCConditional) jCTree).cond);
                    case 51:
                        return getStartPos(((JCTree.JCExpressionStatement) jCTree).expr);
                    case 52:
                        return getStartPos(((JCTree.JCArrayAccess) jCTree).indexed);
                    case 53:
                        JCTree.JCMethodDecl jCMethodDecl = (JCTree.JCMethodDecl) jCTree;
                        int i3 = jCMethodDecl.mods.pos;
                        if (i3 != -1) {
                            return i3;
                        }
                        if (jCMethodDecl.typarams.nonEmpty()) {
                            return getStartPos(jCMethodDecl.typarams.head);
                        }
                        JCTree.JCExpression jCExpression3 = jCMethodDecl.restype;
                        return jCExpression3 == null ? jCMethodDecl.pos : getStartPos(jCExpression3);
                    case 54:
                        return getStartPos(((JCTree.JCInstanceOf) jCTree).expr);
                    case 55:
                        return getStartPos(((JCTree.JCBindingPattern) jCTree).var);
                }
            } else {
                JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) jCTree;
                int i4 = jCVariableDecl.mods.pos;
                if (i4 != -1) {
                    return i4;
                }
                JCTree.JCExpression jCExpression4 = jCVariableDecl.vartype;
                if (jCExpression4 != null) {
                    return getStartPos(jCExpression4);
                }
                int i5 = jCVariableDecl.typePos;
                if (i5 != -1) {
                    return i5;
                }
            }
        }
        return jCTree.pos;
    }

    public static boolean hasAnyConstructorCall(JCTree.JCMethodDecl jCMethodDecl) {
        return hasConstructorCall(jCMethodDecl, null);
    }

    public static boolean hasConstructorCall(JCTree.JCMethodDecl jCMethodDecl, Name name) {
        JCTree.JCMethodInvocation jCMethodInvocationFindConstructorCall = findConstructorCall(jCMethodDecl);
        if (jCMethodInvocationFindConstructorCall != null) {
            return name == null || name == name(jCMethodInvocationFindConstructorCall.meth);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean hasConstructors(List<JCTree> list) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            if (isConstructor((JCTree) list2.head)) {
                return true;
            }
        }
        return false;
    }

    public static JCTree innermostType(JCTree jCTree, boolean z) {
        JCTree jCTree2;
        int i;
        while (true) {
            jCTree2 = null;
            while (true) {
                i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
                if (i != 7) {
                    break;
                }
                jCTree2 = jCTree;
                jCTree = ((JCTree.JCAnnotatedType) jCTree).underlyingType;
            }
            if (i == 27) {
                jCTree = ((JCTree.JCArrayTypeTree) jCTree).elemtype;
            } else {
                if (i != 70) {
                    break;
                }
                jCTree = ((JCTree.JCWildcard) jCTree).inner;
            }
        }
        return (z || jCTree2 == null) ? jCTree : jCTree2;
    }

    public static boolean isBooleanWithValue(JCTree.JCExpression jCExpression, int i) {
        Object objConstValue = jCExpression.type.constValue();
        return objConstValue != null && jCExpression.type.hasTag(TypeTag.BOOLEAN) && ((Integer) objConstValue).intValue() == i;
    }

    public static boolean isCanonicalConstructor(JCTree jCTree) {
        return isConstructor(jCTree) && (((JCTree.JCMethodDecl) jCTree).sym.flags_field & Flags.RECORD) != 0;
    }

    public static boolean isCompactConstructor(JCTree jCTree) {
        return isCanonicalConstructor(jCTree) && (((JCTree.JCMethodDecl) jCTree).sym.flags_field & 2251799813685248L) != 0;
    }

    public static boolean isConstructor(JCTree jCTree) {
        if (jCTree.hasTag(JCTree.Tag.METHODDEF)) {
            Name name = ((JCTree.JCMethodDecl) jCTree).name;
            if (name == name.table.names.init) {
                return true;
            }
        }
        return false;
    }

    public static boolean isConstructorCall(JCTree.JCMethodInvocation jCMethodInvocation) {
        Name name = name(jCMethodInvocation.meth);
        Names names = name.table.names;
        return name == names._this || name == names._super;
    }

    public static boolean isDeclaration(JCTree jCTree) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[skipParens(jCTree).getTag().ordinal()];
        return i == 8 || i == 29 || i == 49 || i == 53;
    }

    public static boolean isDiamond(JCTree jCTree) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i == 2) {
            return isDiamond(((JCTree.JCNewClass) jCTree).clazz);
        }
        if (i == 6) {
            return ((JCTree.JCTypeApply) jCTree).getTypeArguments().isEmpty();
        }
        if (i != 7) {
            return false;
        }
        return isDiamond(((JCTree.JCAnnotatedType) jCTree).underlyingType);
    }

    public static boolean isEnumInit(JCTree jCTree) {
        return AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()] == 8 && (((JCTree.JCVariableDecl) jCTree).mods.flags & 16384) != 0;
    }

    public static boolean isErrorEnumSwitch(JCTree.JCExpression jCExpression, List<JCTree.JCCase> list) {
        return jCExpression.type.tsym.kind == Kinds.Kind.ERR && list.stream().flatMap(new Function() { // from class: one
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JCTree.JCCase) obj).labels.stream();
            }
        }).filter(new Predicate() { // from class: pne
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((JCTree.JCCaseLabel) obj).hasTag(JCTree.Tag.CONSTANTCASELABEL);
            }
        }).map(new Function() { // from class: qne
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JCTree.JCConstantCaseLabel) ((JCTree.JCCaseLabel) obj)).expr;
            }
        }).allMatch(new Predicate() { // from class: rne
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((JCTree.JCExpression) obj).hasTag(JCTree.Tag.IDENT);
            }
        });
    }

    public static boolean isExplicitThisReference(Types types, Type.ClassType classType, JCTree jCTree) {
        Name name;
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i == 3) {
            return isExplicitThisReference(types, classType, skipParens(jCTree));
        }
        if (i == 4) {
            Name name2 = ((JCTree.JCIdent) jCTree).name;
            Names names = name2.table.names;
            return name2 == names._this || name2 == names._super;
        }
        if (i != 5) {
            return false;
        }
        JCTree.JCFieldAccess jCFieldAccess = (JCTree.JCFieldAccess) jCTree;
        Type typeErasure = types.erasure(jCFieldAccess.selected.type);
        if (!typeErasure.hasTag(TypeTag.CLASS)) {
            return false;
        }
        Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) ((Type.ClassType) types.erasure(classType)).tsym;
        Symbol.ClassSymbol classSymbol2 = (Symbol.ClassSymbol) ((Type.ClassType) typeErasure).tsym;
        Names names2 = jCFieldAccess.name.table.names;
        return classSymbol.isSubClass(classSymbol2, types) && ((name = jCFieldAccess.name) == names2._super || (name == names2._this && (classSymbol == classSymbol2 || !classSymbol.isEnclosedBy(classSymbol2))));
    }

    public static boolean isExpressionStatement(JCTree.JCExpression jCExpression) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCExpression.getTag().ordinal()];
        if (i != 1 && i != 2) {
            switch (i) {
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public static boolean isIdentOrThisDotIdent(JCTree jCTree) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i == 3) {
            return isIdentOrThisDotIdent(skipParens(jCTree));
        }
        if (i == 4) {
            return true;
        }
        if (i != 5) {
            return false;
        }
        return isThisQualifier(((JCTree.JCFieldAccess) jCTree).selected);
    }

    public static boolean isInAnnotation(Env<?> env, JCTree jCTree) {
        TreePath path = TreePath.getPath(env.toplevel, jCTree);
        if (path == null) {
            return false;
        }
        Iterator<Tree> it = path.iterator();
        while (it.hasNext()) {
            if (it.next().getKind() == Tree.Kind.ANNOTATION) {
                return true;
            }
        }
        return false;
    }

    public static boolean isModuleInfo(JCTree.JCCompilationUnit jCCompilationUnit) {
        return jCCompilationUnit.sourcefile.isNameCompatible("module-info", JavaFileObject.Kind.SOURCE) && jCCompilationUnit.getModuleDecl() != null;
    }

    public static boolean isMultiCatch(JCTree.JCCatch jCCatch) {
        return jCCatch.param.vartype.hasTag(JCTree.Tag.TYPEUNION);
    }

    public static boolean isNull(JCTree jCTree) {
        return jCTree.hasTag(JCTree.Tag.LITERAL) && ((JCTree.JCLiteral) jCTree).typetag == TypeTag.BOT;
    }

    public static boolean isNullCaseLabel(JCTree.JCCaseLabel jCCaseLabel) {
        return jCCaseLabel.hasTag(JCTree.Tag.CONSTANTCASELABEL) && isNull(((JCTree.JCConstantCaseLabel) jCCaseLabel).expr);
    }

    public static boolean isPackageInfo(JCTree.JCCompilationUnit jCCompilationUnit) {
        return jCCompilationUnit.sourcefile.isNameCompatible("package-info", JavaFileObject.Kind.SOURCE);
    }

    public static boolean isReceiverParam(JCTree jCTree) {
        return jCTree.hasTag(JCTree.Tag.VARDEF) && ((JCTree.JCVariableDecl) jCTree).nameexpr != null;
    }

    public static boolean isStatement(JCTree jCTree) {
        return (!(jCTree instanceof JCTree.JCStatement) || jCTree.hasTag(JCTree.Tag.CLASSDEF) || jCTree.hasTag(JCTree.Tag.BLOCK) || jCTree.hasTag(JCTree.Tag.METHODDEF)) ? false : true;
    }

    public static boolean isStaticSelector(JCTree jCTree, Names names) {
        return isTypeSelector(jCTree, names, new Predicate() { // from class: nne
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TreeInfo.isStaticSym((JCTree) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isStaticSym(JCTree jCTree) {
        Kinds.Kind kind = symbol(jCTree).kind;
        return kind == Kinds.Kind.TYP || kind == Kinds.Kind.PCK;
    }

    public static boolean isSuperCall(JCTree jCTree) {
        Name nameCalledMethodName = calledMethodName(jCTree);
        return nameCalledMethodName != null && nameCalledMethodName == nameCalledMethodName.table.names._super;
    }

    public static boolean isSyntheticInit(JCTree jCTree) {
        JCTree.JCFieldAccess jCFieldAccess;
        Symbol symbol;
        Name name;
        if (!jCTree.hasTag(JCTree.Tag.EXEC)) {
            return false;
        }
        JCTree.JCExpressionStatement jCExpressionStatement = (JCTree.JCExpressionStatement) jCTree;
        if (!jCExpressionStatement.expr.hasTag(JCTree.Tag.ASSIGN)) {
            return false;
        }
        JCTree.JCAssign jCAssign = (JCTree.JCAssign) jCExpressionStatement.expr;
        return (!jCAssign.lhs.hasTag(JCTree.Tag.SELECT) || (symbol = (jCFieldAccess = (JCTree.JCFieldAccess) jCAssign.lhs).sym) == null || (symbol.flags() & 4096) == 0 || (name = name(jCFieldAccess.selected)) == null || name != name.table.names._this) ? false : true;
    }

    public static boolean isThisQualifier(JCTree jCTree) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i == 3) {
            return isThisQualifier(skipParens(jCTree));
        }
        if (i != 4) {
            return false;
        }
        Name name = ((JCTree.JCIdent) jCTree).name;
        return name == name.table.names._this;
    }

    public static boolean isType(JCTree jCTree, Names names) {
        return isTypeSelector(jCTree, names, new Predicate() { // from class: jne
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TreeInfo.a((JCTree) obj);
            }
        });
    }

    private static boolean isTypeSelector(JCTree jCTree, Names names, Predicate<JCTree> predicate) {
        if (jCTree == null) {
            return false;
        }
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i == 4) {
            Name name = ((JCTree.JCIdent) jCTree).name;
            return (name == names._this || name == names._super || !predicate.test(jCTree)) ? false : true;
        }
        if (i == 5) {
            return predicate.test(jCTree) && isStaticSelector(((JCTree.JCFieldAccess) jCTree).selected, names);
        }
        if (i != 6) {
            if (i == 7) {
                return isStaticSelector(((JCTree.JCAnnotatedType) jCTree).underlyingType, names);
            }
            if (i != 27) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ boolean m(JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        return diagnosticPosition != null;
    }

    public static void mapSuperCalls(JCTree.JCBlock jCBlock, Function<? super JCTree.JCExpressionStatement, ? extends JCTree.JCStatement> function) {
        List<JCTree.JCStatement> list = jCBlock.stats;
        final SuperCallTranslator superCallTranslator = new SuperCallTranslator(function);
        jCBlock.stats = list.map(new Function() { // from class: com.sun.tools.javac.tree.a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (JCTree.JCStatement) superCallTranslator.translate((JCTree.JCStatement) obj);
            }
        });
    }

    public static Name name(JCTree jCTree) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i == 4) {
            return ((JCTree.JCIdent) jCTree).name;
        }
        if (i == 5) {
            return ((JCTree.JCFieldAccess) jCTree).name;
        }
        if (i != 6) {
            return null;
        }
        return name(((JCTree.JCTypeApply) jCTree).clazz);
    }

    public static boolean nonstaticSelect(JCTree jCTree) {
        JCTree jCTreeSkipParens = skipParens(jCTree);
        if (!jCTreeSkipParens.hasTag(JCTree.Tag.SELECT)) {
            return false;
        }
        Symbol symbol = symbol(((JCTree.JCFieldAccess) jCTreeSkipParens).selected);
        if (symbol == null) {
            return true;
        }
        Kinds.Kind kind = symbol.kind;
        return (kind == Kinds.Kind.PCK || kind == Kinds.Kind.TYP) ? false : true;
    }

    public static int opPrec(JCTree.Tag tag) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[tag.ordinal()];
        if (i == 54) {
            return 10;
        }
        if (i == 79) {
            return 15;
        }
        switch (i) {
            case 10:
            case 11:
                return 14;
            case 12:
            case 13:
                return 15;
            case 14:
                return 1;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
                return 2;
            default:
                switch (i) {
                    case 30:
                        return 4;
                    case 31:
                        return 5;
                    case 32:
                        return 6;
                    case 33:
                        return 7;
                    case 34:
                        return 8;
                    case 35:
                    case 36:
                        return 9;
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                        return 10;
                    case 41:
                    case 42:
                    case 43:
                        return 11;
                    case 44:
                    case 45:
                        return 12;
                    case 46:
                    case 47:
                    case 48:
                        return 13;
                    default:
                        switch (i) {
                            case 56:
                            case 57:
                            case 58:
                            case 59:
                                return 14;
                            default:
                                x1f.a();
                                return 0;
                        }
                }
        }
    }

    public static int positionFor(Symbol symbol, JCTree jCTree) {
        JCTree jCTreeDeclarationFor = declarationFor(symbol, jCTree);
        if (jCTreeDeclarationFor != null) {
            jCTree = jCTreeDeclarationFor;
        }
        return jCTree.pos;
    }

    public static Type primaryPatternType(JCTree jCTree) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i == 55) {
            return jCTree.type;
        }
        if (i == 83) {
            return ((JCTree.JCRecordPattern) jCTree).type;
        }
        if (i == 84) {
            return ((JCTree.JCAnyPattern) jCTree).type;
        }
        x1f.a();
        return null;
    }

    public static JCTree primaryPatternTypeTree(JCTree jCTree) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i == 55) {
            return ((JCTree.JCBindingPattern) jCTree).var.vartype;
        }
        if (i == 83) {
            return ((JCTree.JCRecordPattern) jCTree).deconstructor;
        }
        x1f.a();
        return null;
    }

    public static List<Type> recordFieldTypes(JCTree.JCClassDecl jCClassDecl) {
        return (List) recordFields(jCClassDecl).stream().map(new Function() { // from class: mne
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JCTree.JCVariableDecl) obj).type;
            }
        }).collect(List.collector());
    }

    public static List<JCTree.JCVariableDecl> recordFields(JCTree.JCClassDecl jCClassDecl) {
        return (List) jCClassDecl.defs.stream().filter(new Predicate() { // from class: ene
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((JCTree) obj).hasTag(JCTree.Tag.VARDEF);
            }
        }).map(new Function() { // from class: kne
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TreeInfo.f((JCTree) obj);
            }
        }).filter(new Predicate() { // from class: lne
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TreeInfo.b((JCTree.JCVariableDecl) obj);
            }
        }).collect(List.collector());
    }

    public static JCTree referencedStatement(JCTree.JCLabeledStatement jCLabeledStatement) {
        JCTree.JCStatement jCStatement = jCLabeledStatement;
        do {
            jCStatement = ((JCTree.JCLabeledStatement) jCStatement).body;
        } while (jCStatement.hasTag(JCTree.Tag.LABELLED));
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCStatement.getTag().ordinal()];
        if (i != 62 && i != 63) {
            switch (i) {
                case 72:
                case 73:
                case 74:
                    break;
                default:
                    return jCLabeledStatement;
            }
        }
        return jCStatement;
    }

    public static void setPolyKind(JCTree jCTree, JCTree.JCPolyExpression.PolyKind polyKind) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i == 1) {
            ((JCTree.JCMethodInvocation) jCTree).polyKind = polyKind;
            return;
        }
        if (i == 2) {
            ((JCTree.JCNewClass) jCTree).polyKind = polyKind;
        } else if (i == 9) {
            ((JCTree.JCMemberReference) jCTree).refPolyKind = polyKind;
        } else {
            s22.a("Unexpected tree: ", jCTree);
        }
    }

    public static void setSymbol(JCTree jCTree, Symbol symbol) {
        JCTree jCTreeSkipParens = skipParens(jCTree);
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTreeSkipParens.getTag().ordinal()];
        if (i == 4) {
            ((JCTree.JCIdent) jCTreeSkipParens).sym = symbol;
        } else {
            if (i != 5) {
                return;
            }
            ((JCTree.JCFieldAccess) jCTreeSkipParens).sym = symbol;
        }
    }

    public static void setVarargsElement(JCTree jCTree, Type type) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i == 1) {
            ((JCTree.JCMethodInvocation) jCTree).varargsElement = type;
            return;
        }
        if (i == 2) {
            ((JCTree.JCNewClass) jCTree).varargsElement = type;
        } else if (i == 9) {
            ((JCTree.JCMemberReference) jCTree).varargsElement = type;
        } else {
            s22.a("Unexpected tree: ", jCTree);
        }
    }

    public static JCTree skipParens(JCTree jCTree) {
        return jCTree.hasTag(JCTree.Tag.PARENS) ? skipParens((JCTree.JCExpression) jCTree) : jCTree;
    }

    public static Symbol symbol(JCTree jCTree) {
        JCTree jCTreeSkipParens = skipParens(jCTree);
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTreeSkipParens.getTag().ordinal()];
        if (i == 4) {
            return ((JCTree.JCIdent) jCTreeSkipParens).sym;
        }
        if (i == 5) {
            return ((JCTree.JCFieldAccess) jCTreeSkipParens).sym;
        }
        if (i == 6) {
            return symbol(((JCTree.JCTypeApply) jCTreeSkipParens).clazz);
        }
        if (i == 7) {
            return symbol(((JCTree.JCAnnotatedType) jCTreeSkipParens).underlyingType);
        }
        if (i == 9) {
            return ((JCTree.JCMemberReference) jCTreeSkipParens).sym;
        }
        if (i != 49) {
            return null;
        }
        return ((JCTree.JCClassDecl) jCTreeSkipParens).sym;
    }

    public static Symbol symbolFor(JCTree jCTree) {
        Symbol symbolSymbolForImpl = symbolForImpl(jCTree);
        if (symbolSymbolForImpl != null) {
            return symbolSymbolForImpl.baseSymbol();
        }
        return null;
    }

    private static Symbol symbolForImpl(JCTree jCTree) {
        JCTree jCTreeSkipParens = skipParens(jCTree);
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTreeSkipParens.getTag().ordinal()];
        if (i == 1) {
            return symbolFor(((JCTree.JCMethodInvocation) jCTreeSkipParens).meth);
        }
        if (i == 2) {
            return ((JCTree.JCNewClass) jCTreeSkipParens).constructor;
        }
        if (i == 4) {
            return ((JCTree.JCIdent) jCTreeSkipParens).sym;
        }
        if (i == 5) {
            return ((JCTree.JCFieldAccess) jCTreeSkipParens).sym;
        }
        if (i == 6) {
            return symbolFor(((JCTree.JCTypeApply) jCTreeSkipParens).clazz);
        }
        if (i == 8) {
            return ((JCTree.JCVariableDecl) jCTreeSkipParens).sym;
        }
        if (i == 9) {
            return ((JCTree.JCMemberReference) jCTreeSkipParens).sym;
        }
        if (i == 28) {
            return ((JCTree.JCModuleDecl) jCTreeSkipParens).sym;
        }
        if (i == 29) {
            return ((JCTree.JCPackageDecl) jCTreeSkipParens).packge;
        }
        if (i == 49) {
            return ((JCTree.JCClassDecl) jCTreeSkipParens).sym;
        }
        if (i == 53) {
            return ((JCTree.JCMethodDecl) jCTreeSkipParens).sym;
        }
        if (i == 68) {
            JCTree.JCCompilationUnit jCCompilationUnit = (JCTree.JCCompilationUnit) jCTreeSkipParens;
            JCTree.JCModuleDecl moduleDecl = jCCompilationUnit.getModuleDecl();
            return (!isModuleInfo(jCCompilationUnit) || moduleDecl == null) ? jCCompilationUnit.packge : symbolFor(moduleDecl);
        }
        switch (i) {
            case 75:
            case 76:
            case 77:
                Type type = jCTreeSkipParens.type;
                if (type != null) {
                    return type.tsym;
                }
                return null;
            default:
                return null;
        }
    }

    public static Tree.Kind tagToKind(JCTree.Tag tag) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[tag.ordinal()];
        if (i == 75) {
            return Tree.Kind.ANNOTATION;
        }
        if (i == 76) {
            return Tree.Kind.TYPE_ANNOTATION;
        }
        switch (i) {
            case 10:
                return Tree.Kind.PREFIX_INCREMENT;
            case 11:
                return Tree.Kind.PREFIX_DECREMENT;
            case 12:
                return Tree.Kind.POSTFIX_INCREMENT;
            case 13:
                return Tree.Kind.POSTFIX_DECREMENT;
            default:
                switch (i) {
                    case 15:
                        return Tree.Kind.OR_ASSIGNMENT;
                    case 16:
                        return Tree.Kind.XOR_ASSIGNMENT;
                    case 17:
                        return Tree.Kind.AND_ASSIGNMENT;
                    case 18:
                        return Tree.Kind.LEFT_SHIFT_ASSIGNMENT;
                    case 19:
                        return Tree.Kind.RIGHT_SHIFT_ASSIGNMENT;
                    case 20:
                        return Tree.Kind.UNSIGNED_RIGHT_SHIFT_ASSIGNMENT;
                    case 21:
                        return Tree.Kind.PLUS_ASSIGNMENT;
                    case 22:
                        return Tree.Kind.MINUS_ASSIGNMENT;
                    case 23:
                        return Tree.Kind.MULTIPLY_ASSIGNMENT;
                    case 24:
                        return Tree.Kind.DIVIDE_ASSIGNMENT;
                    case 25:
                        return Tree.Kind.REMAINDER_ASSIGNMENT;
                    default:
                        switch (i) {
                            case 30:
                                return Tree.Kind.CONDITIONAL_OR;
                            case 31:
                                return Tree.Kind.CONDITIONAL_AND;
                            case 32:
                                return Tree.Kind.OR;
                            case 33:
                                return Tree.Kind.XOR;
                            case 34:
                                return Tree.Kind.AND;
                            case 35:
                                return Tree.Kind.EQUAL_TO;
                            case 36:
                                return Tree.Kind.NOT_EQUAL_TO;
                            case 37:
                                return Tree.Kind.LESS_THAN;
                            case 38:
                                return Tree.Kind.GREATER_THAN;
                            case 39:
                                return Tree.Kind.LESS_THAN_EQUAL;
                            case 40:
                                return Tree.Kind.GREATER_THAN_EQUAL;
                            case 41:
                                return Tree.Kind.LEFT_SHIFT;
                            case 42:
                                return Tree.Kind.RIGHT_SHIFT;
                            case 43:
                                return Tree.Kind.UNSIGNED_RIGHT_SHIFT;
                            case 44:
                                return Tree.Kind.PLUS;
                            case 45:
                                return Tree.Kind.MINUS;
                            case 46:
                                return Tree.Kind.MULTIPLY;
                            case 47:
                                return Tree.Kind.DIVIDE;
                            case 48:
                                return Tree.Kind.REMAINDER;
                            default:
                                switch (i) {
                                    case 56:
                                        return Tree.Kind.UNARY_PLUS;
                                    case 57:
                                        return Tree.Kind.UNARY_MINUS;
                                    case 58:
                                        return Tree.Kind.LOGICAL_COMPLEMENT;
                                    case 59:
                                        return Tree.Kind.BITWISE_COMPLEMENT;
                                    default:
                                        switch (i) {
                                            case 79:
                                                return Tree.Kind.OTHER;
                                            case 80:
                                                return Tree.Kind.EXPORTS;
                                            case 81:
                                                return Tree.Kind.OPENS;
                                            default:
                                                return null;
                                        }
                                }
                        }
                }
        }
    }

    public static JCTree.JCExpression typeIn(JCTree.JCExpression jCExpression) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCExpression.getTag().ordinal()];
        if (i == 4 || i == 5 || i == 6) {
            return jCExpression;
        }
        if (i == 7) {
            return ((JCTree.JCAnnotatedType) jCExpression).underlyingType;
        }
        if (i == 26 || i == 27 || i == 70 || i == 77 || i == 82) {
            return jCExpression;
        }
        s22.a("Unexpected type tree: ", jCExpression);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static List<Type> types(List<? extends JCTree> list) {
        ListBuffer listBuffer = new ListBuffer();
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            listBuffer.append(((JCTree) list2.head).type);
        }
        return listBuffer.toList();
    }

    public static boolean unguardedCase(JCTree.JCCase jCCase) {
        JCTree.JCExpression jCExpression = jCCase.guard;
        if (jCExpression == null) {
            return true;
        }
        return isBooleanWithValue(jCExpression, 1);
    }

    public static class DeclScanner extends TreeScanner {
        final Predicate<? super JCTree> filter;
        JCTree result;
        final Symbol sym;

        public DeclScanner(Symbol symbol, Predicate<? super JCTree> predicate) {
            this.result = null;
            this.sym = symbol;
            this.filter = predicate;
        }

        public boolean checkMatch(JCTree jCTree, Symbol symbol) {
            Predicate<? super JCTree> predicate;
            if (symbol == this.sym && ((predicate = this.filter) == null || predicate.test(jCTree))) {
                this.result = jCTree;
                return true;
            }
            if (this.sym.getKind() != ElementKind.RECORD_COMPONENT || symbol == null || symbol.getKind() != ElementKind.FIELD || (symbol.flags_field & Flags.RECORD) == 0) {
                return false;
            }
            Symbol.RecordComponent recordComponent = symbol.enclClass().getRecordComponent((Symbol.VarSymbol) symbol);
            return checkMatch(recordComponent.declarationFor(), recordComponent);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner
        public void scan(JCTree jCTree) {
            if (jCTree == null || this.result != null) {
                return;
            }
            jCTree.accept(this);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            if (checkMatch(jCClassDecl, jCClassDecl.sym)) {
                return;
            }
            super.visitClassDef(jCClassDecl);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
            if (checkMatch(jCMethodDecl, jCMethodDecl.sym)) {
                return;
            }
            super.visitMethodDef(jCMethodDecl);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitModuleDef(JCTree.JCModuleDecl jCModuleDecl) {
            checkMatch(jCModuleDecl, jCModuleDecl.sym);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitPackageDef(JCTree.JCPackageDecl jCPackageDecl) {
            if (checkMatch(jCPackageDecl, jCPackageDecl.packge)) {
                return;
            }
            super.visitPackageDef(jCPackageDecl);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTopLevel(JCTree.JCCompilationUnit jCCompilationUnit) {
            if (checkMatch(jCCompilationUnit, jCCompilationUnit.packge)) {
                return;
            }
            super.visitTopLevel(jCCompilationUnit);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeParameter(JCTree.JCTypeParameter jCTypeParameter) {
            Type type = jCTypeParameter.type;
            if (type == null || !checkMatch(jCTypeParameter, type.tsym)) {
                super.visitTypeParameter(jCTypeParameter);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
            if (checkMatch(jCVariableDecl, jCVariableDecl.sym)) {
                return;
            }
            super.visitVarDef(jCVariableDecl);
        }

        public DeclScanner(Symbol symbol) {
            this(symbol, null);
        }
    }

    public static JCTree.JCExpression skipParens(JCTree.JCExpression jCExpression) {
        while (jCExpression.hasTag(JCTree.Tag.PARENS)) {
            jCExpression = ((JCTree.JCParens) jCExpression).expr;
        }
        return jCExpression;
    }

    public static JCDiagnostic.DiagnosticPosition diagnosticPositionFor(Symbol symbol, JCTree jCTree, boolean z) {
        return diagnosticPositionFor(symbol, jCTree, z, null);
    }

    public static JCDiagnostic.DiagnosticPosition diagnosticPositionFor(Symbol symbol, JCTree jCTree, boolean z, Predicate<? super JCTree> predicate) {
        DeclScanner declScanner = new DeclScanner(symbol, predicate) { // from class: com.sun.tools.javac.tree.TreeInfo.1DiagScanner
            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitIdent(JCTree.JCIdent jCIdent) {
                if (checkMatch(jCIdent, jCIdent.sym)) {
                    return;
                }
                super.visitIdent(jCIdent);
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
                if (checkMatch(jCFieldAccess, jCFieldAccess.sym)) {
                    return;
                }
                super.visitSelect(jCFieldAccess);
            }
        };
        jCTree.accept(declScanner);
        JCTree jCTree2 = declScanner.result;
        if (jCTree2 == null && z) {
            return null;
        }
        if (jCTree2 != null) {
            jCTree = jCTree2;
        }
        return jCTree.pos();
    }

    public static JCDiagnostic.DiagnosticPosition diagnosticPositionFor(Symbol symbol, JCTree jCTree) {
        return diagnosticPositionFor(symbol, jCTree, false);
    }
}
