package com.sun.tools.javac.tree;

import com.sun.source.tree.CaseTree;
import com.sun.source.tree.MemberReferenceTree;
import com.sun.source.tree.ModuleTree;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.BoundKind;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Pair;
import defpackage.s22;
import java.util.Iterator;
import java.util.function.Supplier;
import javax.lang.model.type.TypeKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TreeMaker implements JCTree.Factory {
    protected static final Context.Key<TreeMaker> treeMakerKey = new Context.Key<>();
    AnnotationBuilder annotationBuilder = new AnnotationBuilder();
    Names names;
    public int pos;
    Symtab syms;
    public JCTree.JCCompilationUnit toplevel;
    Types types;

    /* JADX INFO: renamed from: com.sun.tools.javac.tree.TreeMaker$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$type$TypeKind;

        static {
            int[] iArr = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr;
            try {
                iArr[TypeTag.BYTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CHAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.SHORT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.INT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.LONG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.FLOAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.DOUBLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BOOLEAN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.VOID.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.TYPEVAR.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.WILDCARD.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CLASS.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.ARRAY.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.ERROR.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            int[] iArr2 = new int[TypeKind.values().length];
            $SwitchMap$javax$lang$model$type$TypeKind = iArr2;
            try {
                iArr2[TypeKind.UNION.ordinal()] = 1;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.INTERSECTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    public TreeMaker(Context context) {
        this.pos = -1;
        context.put(treeMakerKey, this);
        this.pos = -1;
        this.toplevel = null;
        this.names = Names.instance(context);
        this.syms = Symtab.instance(context);
        this.types = Types.instance(context);
    }

    public static TreeMaker instance(Context context) {
        TreeMaker treeMaker = (TreeMaker) context.get(treeMakerKey);
        return treeMaker == null ? new TreeMaker(context) : treeMaker;
    }

    public JCTree.JCAnnotatedType AnnotatedType(List<JCTree.JCAnnotation> list, JCTree.JCExpression jCExpression) {
        JCTree.JCAnnotatedType jCAnnotatedType = new JCTree.JCAnnotatedType(list, jCExpression);
        jCAnnotatedType.pos = this.pos;
        return jCAnnotatedType;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCAnnotation Annotation(JCTree jCTree, List<JCTree.JCExpression> list) {
        JCTree.JCAnnotation jCAnnotation = new JCTree.JCAnnotation(JCTree.Tag.ANNOTATION, jCTree, list);
        jCAnnotation.pos = this.pos;
        return jCAnnotation;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<JCTree.JCAnnotation> Annotations(List<Attribute.Compound> list) {
        if (list == null) {
            return List.nil();
        }
        ListBuffer listBuffer = new ListBuffer();
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            listBuffer.append(Annotation((Attribute) list2.head));
        }
        return listBuffer.toList();
    }

    public JCTree.JCClassDecl AnonymousClassDef(JCTree.JCModifiers jCModifiers, List<JCTree> list) {
        return ClassDef(jCModifiers, this.names.empty, List.nil(), null, List.nil(), list);
    }

    public JCTree.JCAnyPattern AnyPattern() {
        JCTree.JCAnyPattern jCAnyPattern = new JCTree.JCAnyPattern();
        jCAnyPattern.pos = this.pos;
        return jCAnyPattern;
    }

    public JCTree.JCMethodInvocation App(JCTree.JCExpression jCExpression) {
        return Apply(null, jCExpression, List.nil()).setType(jCExpression.type.mo73getReturnType());
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCMethodInvocation Apply(List<JCTree.JCExpression> list, JCTree.JCExpression jCExpression, List<JCTree.JCExpression> list2) {
        JCTree.JCMethodInvocation jCMethodInvocation = new JCTree.JCMethodInvocation(list, jCExpression, list2);
        jCMethodInvocation.pos = this.pos;
        return jCMethodInvocation;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCAssert Assert(JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2) {
        JCTree.JCAssert jCAssert = new JCTree.JCAssert(jCExpression, jCExpression2);
        jCAssert.pos = this.pos;
        return jCAssert;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCAssign Assign(JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2) {
        JCTree.JCAssign jCAssign = new JCTree.JCAssign(jCExpression, jCExpression2);
        jCAssign.pos = this.pos;
        return jCAssign;
    }

    public JCTree.JCStatement Assignment(Symbol symbol, JCTree.JCExpression jCExpression) {
        return Exec(Assign(Ident(symbol), jCExpression).setType(symbol.type));
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCAssignOp Assignop(JCTree.Tag tag, JCTree jCTree, JCTree jCTree2) {
        JCTree.JCAssignOp jCAssignOp = new JCTree.JCAssignOp(tag, jCTree, jCTree2, null);
        jCAssignOp.pos = this.pos;
        return jCAssignOp;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCBinary Binary(JCTree.Tag tag, JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2) {
        JCTree.JCBinary jCBinary = new JCTree.JCBinary(tag, jCExpression, jCExpression2, null);
        jCBinary.pos = this.pos;
        return jCBinary;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCBindingPattern BindingPattern(JCTree.JCVariableDecl jCVariableDecl) {
        JCTree.JCBindingPattern jCBindingPattern = new JCTree.JCBindingPattern(jCVariableDecl);
        jCBindingPattern.pos = this.pos;
        return jCBindingPattern;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCBlock Block(long j, List<JCTree.JCStatement> list) {
        JCTree.JCBlock jCBlock = new JCTree.JCBlock(j, list);
        jCBlock.pos = this.pos;
        return jCBlock;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCBreak Break(Name name) {
        JCTree.JCBreak jCBreak = new JCTree.JCBreak(name, null);
        jCBreak.pos = this.pos;
        return jCBreak;
    }

    public JCTree.JCStatement Call(JCTree.JCExpression jCExpression) {
        return jCExpression.type.hasTag(TypeTag.VOID) ? Exec(jCExpression) : Return(jCExpression);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCCase Case(CaseTree.CaseKind caseKind, List<JCTree.JCCaseLabel> list, JCTree.JCExpression jCExpression, List<JCTree.JCStatement> list2, JCTree jCTree) {
        JCTree.JCCase jCCase = new JCTree.JCCase(caseKind, list, jCExpression, list2, jCTree);
        jCCase.pos = this.pos;
        return jCCase;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCCatch Catch(JCTree.JCVariableDecl jCVariableDecl, JCTree.JCBlock jCBlock) {
        JCTree.JCCatch jCCatch = new JCTree.JCCatch(jCVariableDecl, jCBlock);
        jCCatch.pos = this.pos;
        return jCCatch;
    }

    public JCTree.JCClassDecl ClassDef(JCTree.JCModifiers jCModifiers, Name name, List<JCTree.JCTypeParameter> list, JCTree.JCExpression jCExpression, List<JCTree.JCExpression> list2, List<JCTree.JCExpression> list3, List<JCTree> list4) {
        JCTree.JCClassDecl jCClassDecl = new JCTree.JCClassDecl(jCModifiers, name, list, jCExpression, list2, list3, list4, null);
        jCClassDecl.pos = this.pos;
        return jCClassDecl;
    }

    public JCTree.JCExpression ClassLiteral(Type type) {
        return Select(Type(type), new Symbol.VarSymbol(25L, this.names._class, type, type.tsym));
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCConditional Conditional(JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2, JCTree.JCExpression jCExpression3) {
        JCTree.JCConditional jCConditional = new JCTree.JCConditional(jCExpression, jCExpression2, jCExpression3);
        jCConditional.pos = this.pos;
        return jCConditional;
    }

    public JCTree.JCConstantCaseLabel ConstantCaseLabel(JCTree.JCExpression jCExpression) {
        JCTree.JCConstantCaseLabel jCConstantCaseLabel = new JCTree.JCConstantCaseLabel(jCExpression);
        jCConstantCaseLabel.pos = this.pos;
        return jCConstantCaseLabel;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCContinue Continue(Name name) {
        JCTree.JCContinue jCContinue = new JCTree.JCContinue(name, null);
        jCContinue.pos = this.pos;
        return jCContinue;
    }

    public JCTree.JCExpression Create(Symbol symbol, List<JCTree.JCExpression> list) {
        Type typeErasure = symbol.owner.erasure(this.types);
        JCTree.JCNewClass jCNewClassNewClass = NewClass(null, null, Type(typeErasure), list, null);
        jCNewClassNewClass.constructor = symbol;
        jCNewClassNewClass.setType(typeErasure);
        return jCNewClassNewClass;
    }

    public JCTree.JCDefaultCaseLabel DefaultCaseLabel() {
        JCTree.JCDefaultCaseLabel jCDefaultCaseLabel = new JCTree.JCDefaultCaseLabel();
        jCDefaultCaseLabel.pos = this.pos;
        return jCDefaultCaseLabel;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCDoWhileLoop DoLoop(JCTree.JCStatement jCStatement, JCTree.JCExpression jCExpression) {
        JCTree.JCDoWhileLoop jCDoWhileLoop = new JCTree.JCDoWhileLoop(jCStatement, jCExpression);
        jCDoWhileLoop.pos = this.pos;
        return jCDoWhileLoop;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCErroneous Erroneous(List<? extends JCTree> list) {
        JCTree.JCErroneous jCErroneous = new JCTree.JCErroneous(list);
        jCErroneous.pos = this.pos;
        return jCErroneous;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCExpressionStatement Exec(JCTree.JCExpression jCExpression) {
        JCTree.JCExpressionStatement jCExpressionStatement = new JCTree.JCExpressionStatement(jCExpression);
        jCExpressionStatement.pos = this.pos;
        return jCExpressionStatement;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCExports Exports(JCTree.JCExpression jCExpression, List<JCTree.JCExpression> list) {
        JCTree.JCExports jCExports = new JCTree.JCExports(jCExpression, list);
        jCExports.pos = this.pos;
        return jCExports;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCForLoop ForLoop(List<JCTree.JCStatement> list, JCTree.JCExpression jCExpression, List<JCTree.JCExpressionStatement> list2, JCTree.JCStatement jCStatement) {
        JCTree.JCForLoop jCForLoop = new JCTree.JCForLoop(list, jCExpression, list2, jCStatement);
        jCForLoop.pos = this.pos;
        return jCForLoop;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCEnhancedForLoop ForeachLoop(JCTree.JCVariableDecl jCVariableDecl, JCTree.JCExpression jCExpression, JCTree.JCStatement jCStatement) {
        JCTree.JCEnhancedForLoop jCEnhancedForLoop = new JCTree.JCEnhancedForLoop(jCVariableDecl, jCExpression, jCStatement);
        jCEnhancedForLoop.pos = this.pos;
        return jCEnhancedForLoop;
    }

    public JCTree.JCIdent Ident(Symbol symbol) {
        Name nameFlatName = symbol.name;
        if (nameFlatName == this.names.empty) {
            nameFlatName = symbol.flatName();
        }
        return (JCTree.JCIdent) new JCTree.JCIdent(nameFlatName, symbol).setPos(this.pos).setType(symbol.type);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<JCTree.JCExpression> Idents(List<JCTree.JCVariableDecl> list) {
        ListBuffer listBuffer = new ListBuffer();
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            listBuffer.append(Ident((JCTree.JCVariableDecl) list2.head));
        }
        return listBuffer.toList();
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCIf If(JCTree.JCExpression jCExpression, JCTree.JCStatement jCStatement, JCTree.JCStatement jCStatement2) {
        JCTree.JCIf jCIf = new JCTree.JCIf(jCExpression, jCStatement, jCStatement2);
        jCIf.pos = this.pos;
        return jCIf;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCImport Import(JCTree.JCFieldAccess jCFieldAccess, boolean z) {
        JCTree.JCImport jCImport = new JCTree.JCImport(jCFieldAccess, z);
        jCImport.pos = this.pos;
        return jCImport;
    }

    public JCTree.JCArrayAccess Indexed(Symbol symbol, JCTree.JCExpression jCExpression) {
        JCTree.JCArrayAccess jCArrayAccess = new JCTree.JCArrayAccess(QualIdent(symbol), jCExpression);
        jCArrayAccess.type = ((Type.ArrayType) symbol.type).elemtype;
        return jCArrayAccess;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCLabeledStatement Labelled(Name name, JCTree.JCStatement jCStatement) {
        JCTree.JCLabeledStatement jCLabeledStatement = new JCTree.JCLabeledStatement(name, jCStatement);
        jCLabeledStatement.pos = this.pos;
        return jCLabeledStatement;
    }

    public JCTree.JCLambda Lambda(List<JCTree.JCVariableDecl> list, JCTree jCTree) {
        JCTree.JCLambda jCLambda = new JCTree.JCLambda(list, jCTree);
        jCLambda.pos = this.pos;
        return jCLambda;
    }

    public JCTree.LetExpr LetExpr(JCTree.JCVariableDecl jCVariableDecl, JCTree.JCExpression jCExpression) {
        JCTree.LetExpr letExpr = new JCTree.LetExpr(List.of(jCVariableDecl), jCExpression);
        letExpr.pos = this.pos;
        return letExpr;
    }

    public JCTree.JCLiteral Literal(Object obj) {
        if (obj instanceof String) {
            return Literal(TypeTag.CLASS, obj).setType(this.syms.stringType.constType(obj));
        }
        if (obj instanceof Integer) {
            return Literal(TypeTag.INT, obj).setType(this.syms.intType.constType(obj));
        }
        if (obj instanceof Long) {
            return Literal(TypeTag.LONG, obj).setType(this.syms.longType.constType(obj));
        }
        if (obj instanceof Byte) {
            return Literal(TypeTag.BYTE, obj).setType(this.syms.byteType.constType(obj));
        }
        if (obj instanceof Character) {
            char cCharAt = ((Character) obj).toString().charAt(0);
            return Literal(TypeTag.CHAR, Integer.valueOf(cCharAt)).setType(this.syms.charType.constType(Integer.valueOf(cCharAt)));
        }
        if (obj instanceof Double) {
            return Literal(TypeTag.DOUBLE, obj).setType(this.syms.doubleType.constType(obj));
        }
        if (obj instanceof Float) {
            return Literal(TypeTag.FLOAT, obj).setType(this.syms.floatType.constType(obj));
        }
        if (obj instanceof Short) {
            return Literal(TypeTag.SHORT, obj).setType(this.syms.shortType.constType(obj));
        }
        if (obj instanceof Boolean) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            return Literal(TypeTag.BOOLEAN, Integer.valueOf(zBooleanValue ? 1 : 0)).setType(this.syms.booleanType.constType(Integer.valueOf(zBooleanValue ? 1 : 0)));
        }
        x01.a(obj);
        return null;
    }

    public JCTree.JCMethodDecl MethodDef(Symbol.MethodSymbol methodSymbol, Type type, JCTree.JCBlock jCBlock) {
        JCTree.JCModifiers jCModifiersModifiers = Modifiers(methodSymbol.flags(), Annotations(methodSymbol.getRawAttributes()));
        Name name = methodSymbol.name;
        return (JCTree.JCMethodDecl) new JCTree.JCMethodDecl(jCModifiersModifiers, name, name != this.names.init ? Type(type.mo73getReturnType()) : null, TypeParams(type.getTypeArguments()), null, methodSymbol.params != null ? Params(methodSymbol) : Params(methodSymbol, type.mo71getParameterTypes()), Types(type.mo74getThrownTypes()), jCBlock, null, methodSymbol).setPos(this.pos).setType(type);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCModifiers Modifiers(long j, List<JCTree.JCAnnotation> list) {
        JCTree.JCModifiers jCModifiers = new JCTree.JCModifiers(j, list);
        jCModifiers.pos = ((j & (-9223081765785031169L)) == 0 && list.isEmpty()) ? -1 : this.pos;
        return jCModifiers;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCModuleDecl ModuleDef(JCTree.JCModifiers jCModifiers, ModuleTree.ModuleKind moduleKind, JCTree.JCExpression jCExpression, List<JCTree.JCDirective> list) {
        JCTree.JCModuleDecl jCModuleDecl = new JCTree.JCModuleDecl(jCModifiers, moduleKind, jCExpression, list);
        jCModuleDecl.pos = this.pos;
        return jCModuleDecl;
    }

    public JCTree.JCModuleImport ModuleImport(JCTree.JCExpression jCExpression) {
        JCTree.JCModuleImport jCModuleImport = new JCTree.JCModuleImport(jCExpression);
        jCModuleImport.pos = this.pos;
        return jCModuleImport;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCNewArray NewArray(JCTree.JCExpression jCExpression, List<JCTree.JCExpression> list, List<JCTree.JCExpression> list2) {
        JCTree.JCNewArray jCNewArray = new JCTree.JCNewArray(jCExpression, list, list2);
        jCNewArray.pos = this.pos;
        return jCNewArray;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCNewClass NewClass(JCTree.JCExpression jCExpression, List<JCTree.JCExpression> list, JCTree.JCExpression jCExpression2, List<JCTree.JCExpression> list2, JCTree.JCClassDecl jCClassDecl) {
        return SpeculativeNewClass(jCExpression, list, jCExpression2, list2, jCClassDecl, false);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCOpens Opens(JCTree.JCExpression jCExpression, List<JCTree.JCExpression> list) {
        JCTree.JCOpens jCOpens = new JCTree.JCOpens(jCExpression, list);
        jCOpens.pos = this.pos;
        return jCOpens;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCPackageDecl PackageDecl(List<JCTree.JCAnnotation> list, JCTree.JCExpression jCExpression) {
        Assert.checkNonNull(list);
        Assert.checkNonNull(jCExpression);
        JCTree.JCPackageDecl jCPackageDecl = new JCTree.JCPackageDecl(list, jCExpression);
        jCPackageDecl.pos = this.pos;
        return jCPackageDecl;
    }

    public JCTree.JCVariableDecl Param(Name name, Type type, Symbol symbol) {
        return VarDef(new Symbol.VarSymbol(8589934592L, name, type, symbol), null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<JCTree.JCVariableDecl> Params(Symbol.MethodSymbol methodSymbol, List<Type> list) {
        int i = 0;
        Assert.check(methodSymbol.params == null);
        ListBuffer listBuffer = new ListBuffer();
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            listBuffer.append(Param(paramName(i), (Type) list2.head, methodSymbol));
            i++;
        }
        return listBuffer.toList();
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCParens Parens(JCTree.JCExpression jCExpression) {
        JCTree.JCParens jCParens = new JCTree.JCParens(jCExpression);
        jCParens.pos = this.pos;
        return jCParens;
    }

    public JCTree.JCPatternCaseLabel PatternCaseLabel(JCTree.JCPattern jCPattern) {
        JCTree.JCPatternCaseLabel jCPatternCaseLabel = new JCTree.JCPatternCaseLabel(jCPattern);
        jCPatternCaseLabel.pos = this.pos;
        return jCPatternCaseLabel;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCProvides Provides(JCTree.JCExpression jCExpression, List<JCTree.JCExpression> list) {
        JCTree.JCProvides jCProvides = new JCTree.JCProvides(jCExpression, list);
        jCProvides.pos = this.pos;
        return jCProvides;
    }

    public JCTree.JCExpression QualIdent(Symbol symbol) {
        JCTree.JCExpression jCExpressionIdent = isUnqualifiable(symbol) ? Ident(symbol) : Select(QualIdent(symbol.owner), symbol);
        if (symbol.kind == Kinds.Kind.TYP) {
            jCExpressionIdent.setType(this.types.erasure(symbol.type));
        }
        return jCExpressionIdent;
    }

    public JCTree.JCExpression QualThis(Type type) {
        return Select(Type(type), new Symbol.VarSymbol(16L, this.names._this, type, type.tsym));
    }

    public JCTree.JCVariableDecl ReceiverVarDef(JCTree.JCModifiers jCModifiers, JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2) {
        JCTree.JCVariableDecl jCVariableDecl = new JCTree.JCVariableDecl(jCModifiers, jCExpression, jCExpression2);
        jCVariableDecl.pos = this.pos;
        return jCVariableDecl;
    }

    public JCTree.JCRecordPattern RecordPattern(JCTree.JCExpression jCExpression, List<JCTree.JCPattern> list) {
        JCTree.JCRecordPattern jCRecordPattern = new JCTree.JCRecordPattern(jCExpression, list);
        jCRecordPattern.pos = this.pos;
        return jCRecordPattern;
    }

    public JCTree.JCMemberReference Reference(MemberReferenceTree.ReferenceMode referenceMode, Name name, JCTree.JCExpression jCExpression, List<JCTree.JCExpression> list) {
        JCTree.JCMemberReference jCMemberReference = new JCTree.JCMemberReference(referenceMode, name, jCExpression, list);
        jCMemberReference.pos = this.pos;
        return jCMemberReference;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCRequires Requires(boolean z, boolean z2, JCTree.JCExpression jCExpression) {
        JCTree.JCRequires jCRequires = new JCTree.JCRequires(z, z2, jCExpression);
        jCRequires.pos = this.pos;
        return jCRequires;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCReturn Return(JCTree.JCExpression jCExpression) {
        JCTree.JCReturn jCReturn = new JCTree.JCReturn(jCExpression);
        jCReturn.pos = this.pos;
        return jCReturn;
    }

    public JCTree.JCFieldAccess Select(JCTree.JCExpression jCExpression, Symbol symbol) {
        return (JCTree.JCFieldAccess) new JCTree.JCFieldAccess(jCExpression, symbol.name, symbol).setPos(this.pos).setType(symbol.type);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCSkip Skip() {
        JCTree.JCSkip jCSkip = new JCTree.JCSkip();
        jCSkip.pos = this.pos;
        return jCSkip;
    }

    public JCTree.JCNewClass SpeculativeNewClass(JCTree.JCExpression jCExpression, List<JCTree.JCExpression> list, JCTree.JCExpression jCExpression2, List<JCTree.JCExpression> list2, JCTree.JCClassDecl jCClassDecl, boolean z) {
        TreeMaker treeMaker;
        JCTree.JCNewClass jCNewClass;
        if (z) {
            jCNewClass = new JCTree.JCNewClass(jCExpression, list, jCExpression2, list2, jCClassDecl) { // from class: com.sun.tools.javac.tree.TreeMaker.1
                @Override // com.sun.tools.javac.tree.JCTree.JCNewClass
                public boolean classDeclRemoved() {
                    return true;
                }
            };
            treeMaker = this;
        } else {
            treeMaker = this;
            jCNewClass = new JCTree.JCNewClass(jCExpression, list, jCExpression2, list2, jCClassDecl);
        }
        jCNewClass.pos = treeMaker.pos;
        return jCNewClass;
    }

    public JCTree.JCIdent Super(Type type, Symbol.TypeSymbol typeSymbol) {
        return Ident(new Symbol.VarSymbol(16L, this.names._super, type, typeSymbol));
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCSwitch Switch(JCTree.JCExpression jCExpression, List<JCTree.JCCase> list) {
        JCTree.JCSwitch jCSwitch = new JCTree.JCSwitch(jCExpression, list);
        jCSwitch.pos = this.pos;
        return jCSwitch;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCSwitchExpression SwitchExpression(JCTree.JCExpression jCExpression, List<JCTree.JCCase> list) {
        JCTree.JCSwitchExpression jCSwitchExpression = new JCTree.JCSwitchExpression(jCExpression, list);
        jCSwitchExpression.pos = this.pos;
        return jCSwitchExpression;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCSynchronized Synchronized(JCTree.JCExpression jCExpression, JCTree.JCBlock jCBlock) {
        JCTree.JCSynchronized jCSynchronized = new JCTree.JCSynchronized(jCExpression, jCBlock);
        jCSynchronized.pos = this.pos;
        return jCSynchronized;
    }

    public JCTree.JCExpression This(Type type) {
        return Ident(new Symbol.VarSymbol(16L, this.names._this, type, type.tsym));
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCThrow Throw(JCTree.JCExpression jCExpression) {
        JCTree.JCThrow jCThrow = new JCTree.JCThrow(jCExpression);
        jCThrow.pos = this.pos;
        return jCThrow;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCCompilationUnit TopLevel(List<JCTree> list) {
        for (final JCTree jCTree : list) {
            Assert.check((jCTree instanceof JCTree.JCClassDecl) || (jCTree instanceof JCTree.JCPackageDecl) || (jCTree instanceof JCTree.JCImport) || (jCTree instanceof JCTree.JCModuleImport) || (jCTree instanceof JCTree.JCModuleDecl) || (jCTree instanceof JCTree.JCSkip) || (jCTree instanceof JCTree.JCErroneous) || (jCTree instanceof JCTree.JCMethodDecl) || (jCTree instanceof JCTree.JCVariableDecl) || ((jCTree instanceof JCTree.JCExpressionStatement) && (((JCTree.JCExpressionStatement) jCTree).expr instanceof JCTree.JCErroneous)), (Supplier<String>) new Supplier() { // from class: vne
                @Override // java.util.function.Supplier
                public final Object get() {
                    return jCTree.getClass().getSimpleName();
                }
            });
        }
        JCTree.JCCompilationUnit jCCompilationUnit = new JCTree.JCCompilationUnit(list);
        jCCompilationUnit.pos = this.pos;
        return jCCompilationUnit;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCTry Try(List<JCTree> list, JCTree.JCBlock jCBlock, List<JCTree.JCCatch> list2, JCTree.JCBlock jCBlock2) {
        JCTree.JCTry jCTry = new JCTree.JCTry(list, jCBlock, list2, jCBlock2);
        jCTry.pos = this.pos;
        return jCTry;
    }

    public JCTree.JCExpression Type(Type type) {
        JCTree.JCExpression jCExpressionTypeIdent;
        if (type == null) {
            return null;
        }
        switch (AnonymousClass2.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                jCExpressionTypeIdent = TypeIdent(type.getTag());
                break;
            case 10:
                jCExpressionTypeIdent = Ident(type.tsym);
                break;
            case 11:
                Type.WildcardType wildcardType = (Type.WildcardType) type;
                jCExpressionTypeIdent = Wildcard(TypeBoundKind(wildcardType.kind), wildcardType.kind != BoundKind.UNBOUND ? Type(wildcardType.type) : null);
                break;
            case 12:
                int i = AnonymousClass2.$SwitchMap$javax$lang$model$type$TypeKind[type.getKind().ordinal()];
                if (i == 1) {
                    ListBuffer listBuffer = new ListBuffer();
                    Iterator<? extends Type> it = ((Type.UnionClassType) type).getAlternativeTypes().iterator();
                    while (it.hasNext()) {
                        listBuffer.add(Type(it.next()));
                    }
                    jCExpressionTypeIdent = TypeUnion(listBuffer.toList());
                } else if (i == 2) {
                    ListBuffer listBuffer2 = new ListBuffer();
                    Iterator<Type> it2 = ((Type.IntersectionClassType) type).getExplicitComponents().iterator();
                    while (it2.hasNext()) {
                        listBuffer2.add(Type(it2.next()));
                    }
                    jCExpressionTypeIdent = TypeIntersection(listBuffer2.toList());
                } else {
                    Type enclosingType = type.getEnclosingType();
                    JCTree.JCExpression jCExpressionSelect = (enclosingType.hasTag(TypeTag.CLASS) && type.tsym.owner.kind == Kinds.Kind.TYP) ? Select(Type(enclosingType), type.tsym) : QualIdent(type.tsym);
                    jCExpressionTypeIdent = !type.getTypeArguments().isEmpty() ? TypeApply(jCExpressionSelect, Types(type.getTypeArguments())) : jCExpressionSelect;
                }
                break;
            case 13:
                jCExpressionTypeIdent = TypeArray(Type(this.types.elemtype(type)));
                break;
            case 14:
                jCExpressionTypeIdent = TypeIdent(TypeTag.ERROR);
                break;
            default:
                s22.a("unexpected type: ", type);
                return null;
        }
        return jCExpressionTypeIdent.setType(type);
    }

    public JCTree.JCAnnotation TypeAnnotation(JCTree jCTree, List<JCTree.JCExpression> list) {
        JCTree.JCAnnotation jCAnnotation = new JCTree.JCAnnotation(JCTree.Tag.TYPE_ANNOTATION, jCTree, list);
        jCAnnotation.pos = this.pos;
        return jCAnnotation;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCTypeApply TypeApply(JCTree.JCExpression jCExpression, List<JCTree.JCExpression> list) {
        JCTree.JCTypeApply jCTypeApply = new JCTree.JCTypeApply(jCExpression, list);
        jCTypeApply.pos = this.pos;
        return jCTypeApply;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCArrayTypeTree TypeArray(JCTree.JCExpression jCExpression) {
        JCTree.JCArrayTypeTree jCArrayTypeTree = new JCTree.JCArrayTypeTree(jCExpression);
        jCArrayTypeTree.pos = this.pos;
        return jCArrayTypeTree;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.TypeBoundKind TypeBoundKind(BoundKind boundKind) {
        JCTree.TypeBoundKind typeBoundKind = new JCTree.TypeBoundKind(boundKind);
        typeBoundKind.pos = this.pos;
        return typeBoundKind;
    }

    public JCTree.JCTypeCast TypeCast(Type type, JCTree.JCExpression jCExpression) {
        return (JCTree.JCTypeCast) TypeCast(Type(type), jCExpression).setType(type);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCPrimitiveTypeTree TypeIdent(TypeTag typeTag) {
        JCTree.JCPrimitiveTypeTree jCPrimitiveTypeTree = new JCTree.JCPrimitiveTypeTree(typeTag);
        jCPrimitiveTypeTree.pos = this.pos;
        return jCPrimitiveTypeTree;
    }

    public JCTree.JCTypeIntersection TypeIntersection(List<JCTree.JCExpression> list) {
        JCTree.JCTypeIntersection jCTypeIntersection = new JCTree.JCTypeIntersection(list);
        jCTypeIntersection.pos = this.pos;
        return jCTypeIntersection;
    }

    public JCTree.JCTypeParameter TypeParam(Name name, Type.TypeVar typeVar) {
        return (JCTree.JCTypeParameter) TypeParameter(name, Types(this.types.getBounds(typeVar))).setPos(this.pos).setType(typeVar);
    }

    public JCTree.JCTypeParameter TypeParameter(Name name, List<JCTree.JCExpression> list, List<JCTree.JCAnnotation> list2) {
        JCTree.JCTypeParameter jCTypeParameter = new JCTree.JCTypeParameter(name, list, list2);
        jCTypeParameter.pos = this.pos;
        return jCTypeParameter;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<JCTree.JCTypeParameter> TypeParams(List<Type> list) {
        ListBuffer listBuffer = new ListBuffer();
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            A a = list2.head;
            listBuffer.append(TypeParam(((Type) a).tsym.name, (Type.TypeVar) a));
        }
        return listBuffer.toList();
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCInstanceOf TypeTest(JCTree.JCExpression jCExpression, JCTree jCTree) {
        JCTree.JCInstanceOf jCInstanceOf = new JCTree.JCInstanceOf(jCExpression, jCTree);
        jCInstanceOf.pos = this.pos;
        return jCInstanceOf;
    }

    public JCTree.JCTypeUnion TypeUnion(List<JCTree.JCExpression> list) {
        JCTree.JCTypeUnion jCTypeUnion = new JCTree.JCTypeUnion(list);
        jCTypeUnion.pos = this.pos;
        return jCTypeUnion;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<JCTree.JCExpression> Types(List<Type> list) {
        ListBuffer listBuffer = new ListBuffer();
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            listBuffer.append(Type((Type) list2.head));
        }
        return listBuffer.toList();
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCUnary Unary(JCTree.Tag tag, JCTree.JCExpression jCExpression) {
        JCTree.JCUnary jCUnary = new JCTree.JCUnary(tag, jCExpression);
        jCUnary.pos = this.pos;
        return jCUnary;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCUses Uses(JCTree.JCExpression jCExpression) {
        JCTree.JCUses jCUses = new JCTree.JCUses(jCExpression);
        jCUses.pos = this.pos;
        return jCUses;
    }

    public JCTree.JCVariableDecl VarDef(Symbol.VarSymbol varSymbol, JCTree.JCExpression jCExpression) {
        return (JCTree.JCVariableDecl) new JCTree.JCVariableDecl(Modifiers(varSymbol.flags(), Annotations(varSymbol.getRawAttributes())), varSymbol.name, Type(varSymbol.type), jCExpression, varSymbol).setPos(this.pos).setType(varSymbol.type);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCWhileLoop WhileLoop(JCTree.JCExpression jCExpression, JCTree.JCStatement jCStatement) {
        JCTree.JCWhileLoop jCWhileLoop = new JCTree.JCWhileLoop(jCExpression, jCStatement);
        jCWhileLoop.pos = this.pos;
        return jCWhileLoop;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCWildcard Wildcard(JCTree.TypeBoundKind typeBoundKind, JCTree jCTree) {
        JCTree.JCWildcard jCWildcard = new JCTree.JCWildcard(typeBoundKind, jCTree);
        jCWildcard.pos = this.pos;
        return jCWildcard;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCYield Yield(JCTree.JCExpression jCExpression) {
        JCTree.JCYield jCYield = new JCTree.JCYield(jCExpression, null);
        jCYield.pos = this.pos;
        return jCYield;
    }

    public TreeMaker at(JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        this.pos = diagnosticPosition == null ? -1 : diagnosticPosition.getStartPosition();
        return this;
    }

    public TreeMaker forToplevel(JCTree.JCCompilationUnit jCCompilationUnit) {
        return new TreeMaker(jCCompilationUnit, this.names, this.types, this.syms);
    }

    public boolean isUnqualifiable(Symbol symbol) {
        Symbol symbol2;
        Kinds.Kind kind;
        JCTree.JCCompilationUnit jCCompilationUnit;
        if (symbol.name == this.names.empty || (symbol2 = symbol.owner) == null || symbol2 == this.syms.rootPackage || (kind = symbol2.kind) == Kinds.Kind.MTH || kind == Kinds.Kind.VAR) {
            return true;
        }
        if (symbol.kind == Kinds.Kind.TYP && (jCCompilationUnit = this.toplevel) != null) {
            Scope.NamedImportScope namedImportScope = jCCompilationUnit.namedImportScope;
            Scope.WriteableScope writeableScopeMembers = jCCompilationUnit.packge.members();
            JCTree.JCCompilationUnit jCCompilationUnit2 = this.toplevel;
            Scope[] scopeArr = {namedImportScope, writeableScopeMembers, jCCompilationUnit2.starImportScope, jCCompilationUnit2.moduleImportScope};
            for (int i = 0; i < 4; i++) {
                Iterator<Symbol> it = scopeArr[i].getSymbolsByName(symbol.name).iterator();
                if (it.hasNext()) {
                    return it.next() == symbol && !it.hasNext();
                }
            }
        }
        return symbol.kind == Kinds.Kind.TYP && symbol.isImplicit();
    }

    public Name paramName(int i) {
        return this.names.fromString("x" + i);
    }

    public Name typaramName(int i) {
        return this.names.fromString("A" + i);
    }

    public class AnnotationBuilder implements Attribute.Visitor {
        JCTree.JCExpression result = null;

        public AnnotationBuilder() {
        }

        public JCTree.JCExpression translate(Attribute attribute) {
            attribute.accept(this);
            return this.result;
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitArray(Attribute.Array array) {
            ListBuffer listBuffer = new ListBuffer();
            int i = 0;
            while (true) {
                Attribute[] attributeArr = array.values;
                if (i >= attributeArr.length) {
                    this.result = TreeMaker.this.NewArray(null, List.nil(), listBuffer.toList()).setType(array.type);
                    return;
                } else {
                    listBuffer.append(translate(attributeArr[i]));
                    i++;
                }
            }
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitClass(Attribute.Class r2) {
            this.result = TreeMaker.this.ClassLiteral(r2.classType).setType(TreeMaker.this.syms.classType);
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitCompound(Attribute.Compound compound) {
            if (compound instanceof Attribute.TypeCompound) {
                this.result = visitTypeCompoundInternal((Attribute.TypeCompound) compound);
            } else {
                this.result = visitCompoundInternal(compound);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public JCTree.JCAnnotation visitCompoundInternal(Attribute.Compound compound) {
            ListBuffer listBuffer = new ListBuffer();
            for (List list = compound.values; list.nonEmpty(); list = list.tail) {
                Pair pair = (Pair) list.head;
                JCTree.JCExpression jCExpressionTranslate = translate((Attribute) pair.snd);
                TreeMaker treeMaker = TreeMaker.this;
                listBuffer.append(treeMaker.Assign(treeMaker.Ident((Symbol) pair.fst), jCExpressionTranslate).setType(jCExpressionTranslate.type));
            }
            TreeMaker treeMaker2 = TreeMaker.this;
            return treeMaker2.Annotation(treeMaker2.Type(compound.type), listBuffer.toList());
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitConstant(Attribute.Constant constant) {
            this.result = TreeMaker.this.Literal(constant.type.getTag(), constant.value);
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitEnum(Attribute.Enum r2) {
            this.result = TreeMaker.this.QualIdent(r2.value);
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitError(Attribute.Error error) {
            boolean z = error instanceof Attribute.UnresolvedClass;
            TreeMaker treeMaker = TreeMaker.this;
            if (z) {
                this.result = treeMaker.ClassLiteral(((Attribute.UnresolvedClass) error).classType).setType(TreeMaker.this.syms.classType);
            } else {
                this.result = treeMaker.Erroneous();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public JCTree.JCAnnotation visitTypeCompoundInternal(Attribute.TypeCompound typeCompound) {
            ListBuffer listBuffer = new ListBuffer();
            for (List list = typeCompound.values; list.nonEmpty(); list = list.tail) {
                Pair pair = (Pair) list.head;
                JCTree.JCExpression jCExpressionTranslate = translate((Attribute) pair.snd);
                TreeMaker treeMaker = TreeMaker.this;
                listBuffer.append(treeMaker.Assign(treeMaker.Ident((Symbol) pair.fst), jCExpressionTranslate).setType(jCExpressionTranslate.type));
            }
            TreeMaker treeMaker2 = TreeMaker.this;
            return treeMaker2.TypeAnnotation(treeMaker2.Type(typeCompound.type), listBuffer.toList());
        }

        public JCTree.JCAnnotation translate(Attribute.Compound compound) {
            return visitCompoundInternal(compound);
        }

        public JCTree.JCAnnotation translate(Attribute.TypeCompound typeCompound) {
            return visitTypeCompoundInternal(typeCompound);
        }
    }

    public JCTree.JCErroneous Erroneous() {
        return Erroneous(List.nil());
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCTry Try(JCTree.JCBlock jCBlock, List<JCTree.JCCatch> list, JCTree.JCBlock jCBlock2) {
        return Try(List.nil(), jCBlock, list, jCBlock2);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCTypeParameter TypeParameter(Name name, List<JCTree.JCExpression> list) {
        return TypeParameter(name, list, List.nil());
    }

    public TreeMaker at(int i) {
        this.pos = i;
        return this;
    }

    public JCTree.JCAnnotation Annotation(Attribute attribute) {
        return this.annotationBuilder.translate((Attribute.Compound) attribute);
    }

    public JCTree.JCAnnotation TypeAnnotation(Attribute attribute) {
        return this.annotationBuilder.translate((Attribute.TypeCompound) attribute);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.LetExpr LetExpr(List<JCTree.JCStatement> list, JCTree.JCExpression jCExpression) {
        JCTree.LetExpr letExpr = new JCTree.LetExpr(list, jCExpression);
        letExpr.pos = this.pos;
        return letExpr;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCTypeCast TypeCast(JCTree jCTree, JCTree.JCExpression jCExpression) {
        JCTree.JCTypeCast jCTypeCast = new JCTree.JCTypeCast(jCTree, jCExpression);
        jCTypeCast.pos = this.pos;
        return jCTypeCast;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCArrayAccess Indexed(JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2) {
        JCTree.JCArrayAccess jCArrayAccess = new JCTree.JCArrayAccess(jCExpression, jCExpression2);
        jCArrayAccess.pos = this.pos;
        return jCArrayAccess;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCClassDecl ClassDef(JCTree.JCModifiers jCModifiers, Name name, List<JCTree.JCTypeParameter> list, JCTree.JCExpression jCExpression, List<JCTree.JCExpression> list2, List<JCTree> list3) {
        return ClassDef(jCModifiers, name, list, jCExpression, list2, List.nil(), list3);
    }

    public JCTree.JCMethodInvocation App(JCTree.JCExpression jCExpression, List<JCTree.JCExpression> list) {
        return Apply(null, jCExpression, list).setType(jCExpression.type.mo73getReturnType());
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCFieldAccess Select(JCTree.JCExpression jCExpression, Name name) {
        JCTree.JCFieldAccess jCFieldAccess = new JCTree.JCFieldAccess(jCExpression, name, null);
        jCFieldAccess.pos = this.pos;
        return jCFieldAccess;
    }

    public JCTree.JCExpression ClassLiteral(Symbol.ClassSymbol classSymbol) {
        return ClassLiteral(classSymbol.type);
    }

    public JCTree.JCModifiers Modifiers(long j) {
        return Modifiers(j, List.nil());
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCIdent Ident(Name name) {
        JCTree.JCIdent jCIdent = new JCTree.JCIdent(name, null);
        jCIdent.pos = this.pos;
        return jCIdent;
    }

    public JCTree.JCExpression Ident(JCTree.JCVariableDecl jCVariableDecl) {
        return Ident(jCVariableDecl.sym);
    }

    public TreeMaker(JCTree.JCCompilationUnit jCCompilationUnit, Names names, Types types, Symtab symtab) {
        this.pos = -1;
        this.pos = 0;
        this.toplevel = jCCompilationUnit;
        this.names = names;
        this.types = types;
        this.syms = symtab;
    }

    public JCTree.JCVariableDecl VarDef(JCTree.JCModifiers jCModifiers, Name name, JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2, JCTree.JCVariableDecl.DeclKind declKind, int i) {
        JCTree.JCVariableDecl jCVariableDecl = new JCTree.JCVariableDecl(jCModifiers, name, jCExpression, jCExpression2, null, declKind, i);
        jCVariableDecl.pos = this.pos;
        return jCVariableDecl;
    }

    public List<JCTree.JCVariableDecl> Params(Symbol.MethodSymbol methodSymbol) {
        Assert.check(methodSymbol.params != null);
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Symbol.VarSymbol> it = methodSymbol.params.iterator();
        while (it.hasNext()) {
            listBuffer.append(VarDef(it.next(), null));
        }
        return listBuffer.toList();
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCVariableDecl VarDef(JCTree.JCModifiers jCModifiers, Name name, JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2) {
        JCTree.JCVariableDecl jCVariableDecl = new JCTree.JCVariableDecl(jCModifiers, name, jCExpression, jCExpression2, null);
        jCVariableDecl.pos = this.pos;
        return jCVariableDecl;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCMethodDecl MethodDef(JCTree.JCModifiers jCModifiers, Name name, JCTree.JCExpression jCExpression, List<JCTree.JCTypeParameter> list, JCTree.JCVariableDecl jCVariableDecl, List<JCTree.JCVariableDecl> list2, List<JCTree.JCExpression> list3, JCTree.JCBlock jCBlock, JCTree.JCExpression jCExpression2) {
        JCTree.JCMethodDecl jCMethodDecl = new JCTree.JCMethodDecl(jCModifiers, name, jCExpression, list, jCVariableDecl, list2, list3, jCBlock, jCExpression2, null);
        jCMethodDecl.pos = this.pos;
        return jCMethodDecl;
    }

    public JCTree.JCMethodDecl MethodDef(Symbol.MethodSymbol methodSymbol, JCTree.JCBlock jCBlock) {
        return MethodDef(methodSymbol, methodSymbol.type, jCBlock);
    }

    public JCTree.JCMethodDecl MethodDef(JCTree.JCModifiers jCModifiers, Name name, JCTree.JCExpression jCExpression, List<JCTree.JCTypeParameter> list, List<JCTree.JCVariableDecl> list2, List<JCTree.JCExpression> list3, JCTree.JCBlock jCBlock, JCTree.JCExpression jCExpression2) {
        return MethodDef(jCModifiers, name, jCExpression, list, null, list2, list3, jCBlock, jCExpression2);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Factory
    public JCTree.JCLiteral Literal(TypeTag typeTag, Object obj) {
        JCTree.JCLiteral jCLiteral = new JCTree.JCLiteral(typeTag, obj);
        jCLiteral.pos = this.pos;
        return jCLiteral;
    }
}
