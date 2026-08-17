package com.sun.tools.javac.jvm;

import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Resolve;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class StringConcat {
    private static final int MAX_INDY_CONCAT_ARG_SLOTS = 200;
    private static final char TAG_ARG = 1;
    private static final char TAG_CONST = 2;
    protected static final Context.Key<StringConcat> concatKey = new Context.Key<>();
    protected final Gen gen;
    protected final TreeMaker make;
    protected final Names names;
    protected final Resolve rs;
    protected final Map<Type, Symbol> sbAppends;
    protected final Symtab syms;
    protected final Types types;

    public static final class IndyConstants extends Indy {
        public IndyConstants(Context context) {
            super(context);
        }

        private void doCall(Type type, JCDiagnostic.DiagnosticPosition diagnosticPosition, String str, List<PoolConstant.LoadableConstant> list, List<Type> list2) {
            Type.MethodType methodType = new Type.MethodType(list2, type, List.nil(), this.syms.methodClass);
            TreeMaker treeMaker = this.make;
            int i = treeMaker.pos;
            try {
                treeMaker.at(diagnosticPosition);
                ListBuffer listBuffer = new ListBuffer();
                ListBuffer listBuffer2 = new ListBuffer();
                Iterator<PoolConstant.LoadableConstant> it = list.iterator();
                while (it.hasNext()) {
                    listBuffer2.add(it.next());
                    listBuffer.add(this.syms.stringType);
                }
                Symtab symtab = this.syms;
                this.gen.getItems().makeDynamicItem(new Symbol.DynamicMethodSymbol(this.names.makeConcatWithConstants, this.syms.noSymbol, this.rs.resolveInternalMethod(diagnosticPosition, this.gen.getAttrEnv(), this.syms.stringConcatFactory, this.names.makeConcatWithConstants, List.of(symtab.methodHandleLookupType, symtab.stringType, symtab.methodTypeType).append(this.syms.stringType).appendList(listBuffer), null).asHandle(), methodType, (PoolConstant.LoadableConstant[]) List.of(PoolConstant.LoadableConstant.String(str)).appendList(listBuffer2).toArray(new PoolConstant.LoadableConstant[listBuffer2.size()]))).invoke();
            } finally {
                this.make.at(i);
            }
        }

        @Override // com.sun.tools.javac.jvm.StringConcat.Indy
        public void emit(JCDiagnostic.DiagnosticPosition diagnosticPosition, List<JCTree> list, boolean z, Type type) {
            int i;
            List<List<JCTree>> listSplit = split(list);
            Iterator<List<JCTree>> it = listSplit.iterator();
            boolean z2 = true;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                List<JCTree> next = it.next();
                Assert.check(!next.isEmpty(), "Arguments list is empty");
                StringBuilder sb = new StringBuilder(next.size());
                ListBuffer listBuffer = new ListBuffer();
                ListBuffer listBuffer2 = new ListBuffer();
                boolean z3 = z2;
                for (JCTree jCTree : next) {
                    Object objConstValue = jCTree.type.constValue();
                    if (!"".equals(objConstValue)) {
                        if (jCTree.type == this.syms.botType && jCTree.hasTag(JCTree.Tag.LITERAL)) {
                            sb.append((String) null);
                        } else if (objConstValue != null) {
                            String strStringValue = jCTree.type.stringValue();
                            if (strStringValue.indexOf(2) == -1 && strStringValue.indexOf(1) == -1) {
                                sb.append(strStringValue);
                            } else {
                                sb.append(StringConcat.TAG_CONST);
                                listBuffer2.add(PoolConstant.LoadableConstant.String(strStringValue));
                            }
                        } else {
                            sb.append(StringConcat.TAG_ARG);
                            Type type2 = jCTree.type;
                            if (!z3 || z) {
                                this.gen.genExpr(jCTree, type2).load();
                            }
                            if (shouldConvertToStringEagerly(type2)) {
                                Gen gen = this.gen;
                                Symtab symtab = this.syms;
                                gen.callMethod(diagnosticPosition, symtab.stringType, this.names.valueOf, List.of(symtab.objectType), true);
                                type2 = this.syms.stringType;
                            }
                            listBuffer.add(type2);
                            z3 = false;
                        }
                    }
                }
                doCall(type, diagnosticPosition, sb.toString(), listBuffer2.toList(), listBuffer.toList());
                z2 = z3;
            }
            if (listSplit.size() > 1) {
                ListBuffer listBuffer3 = new ListBuffer();
                StringBuilder sb2 = new StringBuilder();
                for (i = 0; i < listSplit.size(); i++) {
                    listBuffer3.append(this.syms.stringType);
                    sb2.append(StringConcat.TAG_ARG);
                }
                doCall(type, diagnosticPosition, sb2.toString(), List.nil(), listBuffer3.toList());
            }
        }
    }

    public static class IndyPlain extends Indy {
        public IndyPlain(Context context) {
            super(context);
        }

        private void doCall(Type type, JCDiagnostic.DiagnosticPosition diagnosticPosition, List<Type> list) {
            Type.MethodType methodType = new Type.MethodType(list, type, List.nil(), this.syms.methodClass);
            TreeMaker treeMaker = this.make;
            int i = treeMaker.pos;
            try {
                treeMaker.at(diagnosticPosition);
                Symtab symtab = this.syms;
                this.gen.getItems().makeDynamicItem(new Symbol.DynamicMethodSymbol(this.names.makeConcat, this.syms.noSymbol, this.rs.resolveInternalMethod(diagnosticPosition, this.gen.getAttrEnv(), this.syms.stringConcatFactory, this.names.makeConcat, List.of(symtab.methodHandleLookupType, symtab.stringType, symtab.methodTypeType), null).asHandle(), methodType, (PoolConstant.LoadableConstant[]) List.nil().toArray(new PoolConstant.LoadableConstant[0]))).invoke();
            } finally {
                this.make.at(i);
            }
        }

        @Override // com.sun.tools.javac.jvm.StringConcat.Indy
        public void emit(JCDiagnostic.DiagnosticPosition diagnosticPosition, List<JCTree> list, boolean z, Type type) {
            int i;
            List<List<JCTree>> listSplit = split(list);
            Iterator<List<JCTree>> it = listSplit.iterator();
            boolean z2 = true;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                List<JCTree> next = it.next();
                Assert.check(!next.isEmpty(), "Arguments list is empty");
                ListBuffer listBuffer = new ListBuffer();
                for (JCTree jCTree : next) {
                    if (!"".equals(jCTree.type.constValue())) {
                        Type type2 = jCTree.type;
                        Symtab symtab = this.syms;
                        if (type2 == symtab.botType) {
                            type2 = this.types.boxedClass(symtab.voidType).type;
                        }
                        if (!z2 || z) {
                            this.gen.genExpr(jCTree, jCTree.type).load();
                        }
                        if (shouldConvertToStringEagerly(type2)) {
                            Gen gen = this.gen;
                            Symtab symtab2 = this.syms;
                            gen.callMethod(diagnosticPosition, symtab2.stringType, this.names.valueOf, List.of(symtab2.objectType), true);
                            type2 = this.syms.stringType;
                        }
                        listBuffer.add(type2);
                        z2 = false;
                    }
                }
                doCall(type, diagnosticPosition, listBuffer.toList());
            }
            if (listSplit.size() > 1) {
                ListBuffer listBuffer2 = new ListBuffer();
                for (i = 0; i < listSplit.size(); i++) {
                    listBuffer2.append(this.syms.stringType);
                }
                doCall(type, diagnosticPosition, listBuffer2.toList());
            }
        }
    }

    public StringConcat(Context context) {
        context.put(concatKey, this);
        this.gen = Gen.instance(context);
        this.syms = Symtab.instance(context);
        this.types = Types.instance(context);
        this.names = Names.instance(context);
        this.make = TreeMaker.instance(context);
        this.rs = Resolve.instance(context);
        this.sbAppends = new HashMap();
    }

    private List<JCTree> collect(JCTree jCTree, List<JCTree> list) {
        JCTree jCTreeSkipParens = TreeInfo.skipParens(jCTree);
        if (jCTreeSkipParens.hasTag(JCTree.Tag.PLUS) && jCTreeSkipParens.type.constValue() == null) {
            JCTree.JCBinary jCBinary = (JCTree.JCBinary) jCTreeSkipParens;
            Symbol.OperatorSymbol operatorSymbol = jCBinary.operator;
            if (operatorSymbol.kind == Kinds.Kind.MTH && operatorSymbol.opcode == 256) {
                return list.appendList(collect(jCBinary.lhs, list)).appendList(collect(jCBinary.rhs, list));
            }
        }
        return list.append(jCTreeSkipParens);
    }

    public static StringConcat instance(Context context) {
        StringConcat stringConcat = (StringConcat) context.get(concatKey);
        return stringConcat == null ? makeConcat(context) : stringConcat;
    }

    private static StringConcat makeConcat(Context context) {
        Target targetInstance = Target.instance(context);
        String str = Options.instance(context).get("stringConcat");
        if (!targetInstance.hasStringConcatFactory()) {
            if (str != null && !"inline".equals(str)) {
                Assert.error("StringConcatFactory-based string concat is requested on a platform that does not support it.");
            }
            str = "inline";
        } else if (str == null) {
            str = "indyWithConstants";
        }
        switch (str) {
            case "inline":
                return new Inline(context);
            case "indy":
                return new IndyPlain(context);
            case "indyWithConstants":
                return new IndyConstants(context);
            default:
                Assert.error("Unknown stringConcat: ".concat(str));
                k2d.a("Unknown stringConcat: ".concat(str));
                return null;
        }
    }

    public List<JCTree> collectAll(JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2) {
        return List.nil().appendList(collectAll(jCExpression)).appendList(collectAll(jCExpression2));
    }

    public abstract Items.Item makeConcat(JCTree.JCAssignOp jCAssignOp);

    public abstract Items.Item makeConcat(JCTree.JCBinary jCBinary);

    public List<JCTree> collectAll(JCTree jCTree) {
        return collect(jCTree, List.nil());
    }

    public static abstract class Indy extends StringConcat {
        public Indy(Context context) {
            super(context);
        }

        public abstract void emit(JCDiagnostic.DiagnosticPosition diagnosticPosition, List<JCTree> list, boolean z, Type type);

        @Override // com.sun.tools.javac.jvm.StringConcat
        public Items.Item makeConcat(JCTree.JCAssignOp jCAssignOp) {
            List<JCTree> listCollectAll = collectAll(jCAssignOp.lhs, jCAssignOp.rhs);
            Gen gen = this.gen;
            JCTree.JCExpression jCExpression = jCAssignOp.lhs;
            Items.Item itemGenExpr = gen.genExpr(jCExpression, jCExpression.type);
            itemGenExpr.duplicate();
            itemGenExpr.load();
            emit(jCAssignOp.pos(), listCollectAll, false, jCAssignOp.type);
            return itemGenExpr;
        }

        public boolean shouldConvertToStringEagerly(Type type) {
            return (this.types.unboxedTypeOrType(type).isPrimitive() || type.tsym == this.syms.stringType.tsym) ? false : true;
        }

        public List<List<JCTree>> split(List<JCTree> list) {
            ListBuffer listBuffer = new ListBuffer();
            ListBuffer listBuffer2 = new ListBuffer();
            int i = 0;
            for (JCTree jCTree : list) {
                int i2 = (jCTree.type.getTag() == TypeTag.LONG || jCTree.type.getTag() == TypeTag.DOUBLE) ? 2 : 1;
                if (i + i2 >= 200) {
                    listBuffer.add(listBuffer2.toList());
                    listBuffer2.clear();
                    i = 0;
                }
                listBuffer2.add(jCTree);
                i += i2;
            }
            if (!listBuffer2.isEmpty()) {
                listBuffer.add(listBuffer2.toList());
            }
            return listBuffer.toList();
        }

        @Override // com.sun.tools.javac.jvm.StringConcat
        public Items.Item makeConcat(JCTree.JCBinary jCBinary) {
            emit(jCBinary.pos(), collectAll(jCBinary.lhs, jCBinary.rhs), true, jCBinary.type);
            return this.gen.getItems().makeStackItem(this.syms.stringType);
        }
    }

    public static class Inline extends StringConcat {
        public Inline(Context context) {
            super(context);
        }

        private void appendString(JCTree jCTree) {
            Type typeBaseType = jCTree.type.baseType();
            if (!typeBaseType.isPrimitive()) {
                Symbol.TypeSymbol typeSymbol = typeBaseType.tsym;
                Symtab symtab = this.syms;
                if (typeSymbol != symtab.stringType.tsym) {
                    typeBaseType = symtab.objectType;
                }
            }
            Assert.checkNull(typeBaseType.constValue());
            Symbol symbolResolveInternalMethod = this.sbAppends.get(typeBaseType);
            if (symbolResolveInternalMethod == null) {
                symbolResolveInternalMethod = this.rs.resolveInternalMethod(jCTree.pos(), this.gen.getAttrEnv(), this.syms.stringBuilderType, this.names.append, List.of(typeBaseType), null);
                this.sbAppends.put(typeBaseType, symbolResolveInternalMethod);
            }
            this.gen.getItems().makeMemberItem(symbolResolveInternalMethod, false).invoke();
        }

        private void builderToString(JCDiagnostic.DiagnosticPosition diagnosticPosition) {
            this.gen.callMethod(diagnosticPosition, this.syms.stringBuilderType, this.names.toString, List.nil(), false);
        }

        private JCDiagnostic.DiagnosticPosition newStringBuilder(JCTree jCTree) {
            JCDiagnostic.DiagnosticPosition diagnosticPositionPos = jCTree.pos();
            this.gen.getCode().emitop2(187, this.gen.makeRef(diagnosticPositionPos, this.syms.stringBuilderType), this.syms.stringBuilderType);
            this.gen.getCode().emitop0(89);
            this.gen.callMethod(diagnosticPositionPos, this.syms.stringBuilderType, this.names.init, List.nil(), false);
            return diagnosticPositionPos;
        }

        @Override // com.sun.tools.javac.jvm.StringConcat
        public Items.Item makeConcat(JCTree.JCAssignOp jCAssignOp) {
            JCDiagnostic.DiagnosticPosition diagnosticPositionPos = jCAssignOp.pos();
            newStringBuilder(jCAssignOp);
            Gen gen = this.gen;
            JCTree.JCExpression jCExpression = jCAssignOp.lhs;
            Items.Item itemGenExpr = gen.genExpr(jCExpression, jCExpression.type);
            if (itemGenExpr.width() > 0) {
                this.gen.getCode().emitop0(((itemGenExpr.width() - 1) * 3) + 90);
            }
            itemGenExpr.load();
            appendString(jCAssignOp.lhs);
            for (JCTree jCTree : collectAll(jCAssignOp.rhs)) {
                this.gen.genExpr(jCTree, jCTree.type).load();
                appendString(jCTree);
            }
            builderToString(diagnosticPositionPos);
            return itemGenExpr;
        }

        @Override // com.sun.tools.javac.jvm.StringConcat
        public Items.Item makeConcat(JCTree.JCBinary jCBinary) {
            JCDiagnostic.DiagnosticPosition diagnosticPositionPos = jCBinary.pos();
            newStringBuilder(jCBinary);
            for (JCTree jCTree : collectAll(jCBinary)) {
                this.gen.genExpr(jCTree, jCTree.type).load();
                appendString(jCTree);
            }
            builderToString(diagnosticPositionPos);
            return this.gen.getItems().makeStackItem(this.syms.stringType);
        }
    }
}
