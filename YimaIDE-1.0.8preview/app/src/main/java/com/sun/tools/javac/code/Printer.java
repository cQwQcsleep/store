package com.sun.tools.javac.code;

import com.sun.tools.javac.api.Messages;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Printer implements Type.Visitor<String, Locale>, Symbol.Visitor<String, Locale> {
    static final int PRIME = 997;
    List<Type> seenCaptured = List.nil();

    public static Printer createStandardPrinter(final Messages messages) {
        return new Printer() { // from class: com.sun.tools.javac.code.Printer.1
            @Override // com.sun.tools.javac.code.Printer
            public String capturedVarId(Type.CapturedType capturedType, Locale locale) {
                return ((((long) capturedType.hashCode()) & 4294967295L) % 997) + "";
            }

            @Override // com.sun.tools.javac.code.Printer
            public String localize(Locale locale, String str, Object... objArr) {
                return messages.getLocalizedString(locale, str, objArr);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
            public /* bridge */ /* synthetic */ String visitArrayType(Type.ArrayType arrayType, Locale locale) {
                return super.visitArrayType(arrayType, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
            public /* bridge */ /* synthetic */ String visitCapturedType(Type.CapturedType capturedType, Locale locale) {
                return super.visitCapturedType(capturedType, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Symbol.Visitor
            public /* bridge */ /* synthetic */ String visitClassSymbol(Symbol.ClassSymbol classSymbol, Locale locale) {
                return super.visitClassSymbol(classSymbol, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
            public /* bridge */ /* synthetic */ String visitClassType(Type.ClassType classType, Locale locale) {
                return super.visitClassType(classType, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
            public /* bridge */ /* synthetic */ String visitErrorType(Type.ErrorType errorType, Locale locale) {
                return super.visitErrorType(errorType, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
            public /* bridge */ /* synthetic */ String visitForAll(Type.ForAll forAll, Locale locale) {
                return super.visitForAll(forAll, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Symbol.Visitor
            public /* bridge */ /* synthetic */ String visitMethodSymbol(Symbol.MethodSymbol methodSymbol, Locale locale) {
                return super.visitMethodSymbol(methodSymbol, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
            public /* bridge */ /* synthetic */ String visitMethodType(Type.MethodType methodType, Locale locale) {
                return super.visitMethodType(methodType, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
            public /* bridge */ /* synthetic */ String visitModuleType(Type.ModuleType moduleType, Locale locale) {
                return super.visitModuleType(moduleType, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Symbol.Visitor
            public /* bridge */ /* synthetic */ String visitOperatorSymbol(Symbol.OperatorSymbol operatorSymbol, Locale locale) {
                return super.visitOperatorSymbol(operatorSymbol, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Symbol.Visitor
            public /* bridge */ /* synthetic */ String visitPackageSymbol(Symbol.PackageSymbol packageSymbol, Locale locale) {
                return super.visitPackageSymbol(packageSymbol, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
            public /* bridge */ /* synthetic */ String visitPackageType(Type.PackageType packageType, Locale locale) {
                return super.visitPackageType(packageType, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Symbol.Visitor
            public /* bridge */ /* synthetic */ String visitSymbol(Symbol symbol, Locale locale) {
                return super.visitSymbol(symbol, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
            public /* bridge */ /* synthetic */ String visitType(Type type, Locale locale) {
                return super.visitType(type, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Symbol.Visitor
            public /* bridge */ /* synthetic */ String visitTypeSymbol(Symbol.TypeSymbol typeSymbol, Locale locale) {
                return super.visitTypeSymbol(typeSymbol, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
            public /* bridge */ /* synthetic */ String visitTypeVar(Type.TypeVar typeVar, Locale locale) {
                return super.visitTypeVar(typeVar, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
            public /* bridge */ /* synthetic */ String visitUndetVar(Type.UndetVar undetVar, Locale locale) {
                return super.visitUndetVar(undetVar, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Symbol.Visitor
            public /* bridge */ /* synthetic */ String visitVarSymbol(Symbol.VarSymbol varSymbol, Locale locale) {
                return super.visitVarSymbol(varSymbol, locale);
            }

            @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
            public /* bridge */ /* synthetic */ String visitWildcardType(Type.WildcardType wildcardType, Locale locale) {
                return super.visitWildcardType(wildcardType, locale);
            }
        };
    }

    private String printAnnotations(Type type, boolean z) {
        StringBuilder sb = new StringBuilder();
        List<Attribute.TypeCompound> annotationMirrors = type.getAnnotationMirrors();
        if (!annotationMirrors.isEmpty()) {
            if (z) {
                sb.append(' ');
            }
            Iterator<Attribute.TypeCompound> it = annotationMirrors.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    private void printBaseElementType(Type type, StringBuilder sb, Locale locale) {
        while (type.hasTag(TypeTag.ARRAY)) {
            type = ((Type.ArrayType) type).elemtype;
        }
        sb.append(visit(type, locale));
    }

    private void printBrackets(Type type, StringBuilder sb, Locale locale) {
        while (type.hasTag(TypeTag.ARRAY)) {
            sb.append(printAnnotations(type, true));
            sb.append("[]");
            type = ((Type.ArrayType) type).elemtype;
        }
    }

    public abstract String capturedVarId(Type.CapturedType capturedType, Locale locale);

    /* JADX WARN: Multi-variable type inference failed */
    public String className(Type.ClassType classType, boolean z, Locale locale) {
        Symbol.TypeSymbol typeSymbol = classType.tsym;
        if (typeSymbol.name.length() != 0 || (typeSymbol.flags() & 16777216) == 0) {
            if (typeSymbol.name.length() != 0) {
                return z ? typeSymbol.getQualifiedName().toString() : typeSymbol.name.toString();
            }
            Type.ClassType classType2 = (Type.ClassType) classType.tsym.type;
            if (classType2 == null) {
                return localize(locale, "compiler.misc.anonymous.class", null);
            }
            List<Type> list = classType2.interfaces_field;
            return (list == null || !list.nonEmpty()) ? localize(locale, "compiler.misc.anonymous.class", visit(classType2.supertype_field, locale)) : localize(locale, "compiler.misc.anonymous.class", visit(classType2.interfaces_field.head, locale));
        }
        StringBuilder sb = new StringBuilder(visit(classType.supertype_field, locale));
        for (List list2 = classType.interfaces_field; list2.nonEmpty(); list2 = list2.tail) {
            sb.append('&');
            sb.append(visit((Type) list2.head, locale));
        }
        return sb.toString();
    }

    public abstract String localize(Locale locale, String str, Object... objArr);

    /* JADX WARN: Multi-variable type inference failed */
    public String printMethodArgs(List<Type> list, boolean z, Locale locale) {
        A a;
        if (!z) {
            return visitTypes(list, locale);
        }
        StringBuilder sb = new StringBuilder();
        List list2 = list;
        while (true) {
            boolean zNonEmpty = list2.tail.nonEmpty();
            a = list2.head;
            if (!zNonEmpty) {
                break;
            }
            sb.append(visit((Type) a, locale));
            List list3 = list2.tail;
            sb.append(',');
            list2 = list3;
        }
        boolean zHasTag = ((Type) a).hasTag(TypeTag.ARRAY);
        A a2 = list2.head;
        if (zHasTag) {
            sb.append(visit(((Type.ArrayType) a2).elemtype, locale));
            if (((Type) list2.head).getAnnotationMirrors().nonEmpty()) {
                sb.append(' ');
                sb.append(((Type) list2.head).getAnnotationMirrors());
                sb.append(' ');
            }
            sb.append("...");
        } else {
            sb.append(visit((Type) a2, locale));
        }
        return sb.toString();
    }

    public String visit(Type type, Locale locale) {
        return (String) type.accept(this, locale);
    }

    @Override // com.sun.tools.javac.code.Type.Visitor
    public String visitArrayType(Type.ArrayType arrayType, Locale locale) {
        StringBuilder sb = new StringBuilder();
        printBaseElementType(arrayType, sb, locale);
        printBrackets(arrayType, sb, locale);
        return sb.toString();
    }

    @Override // com.sun.tools.javac.code.Type.Visitor
    public String visitCapturedType(Type.CapturedType capturedType, Locale locale) {
        if (this.seenCaptured.contains(capturedType)) {
            return printAnnotations(capturedType) + localize(locale, "compiler.misc.type.captureof.1", capturedVarId(capturedType, locale));
        }
        try {
            this.seenCaptured = this.seenCaptured.prepend(capturedType);
            return printAnnotations(capturedType) + localize(locale, "compiler.misc.type.captureof", capturedVarId(capturedType, locale), visit(capturedType.wildcard, locale));
        } finally {
            this.seenCaptured = this.seenCaptured.tail;
        }
    }

    @Override // com.sun.tools.javac.code.Symbol.Visitor
    public String visitClassSymbol(Symbol.ClassSymbol classSymbol, Locale locale) {
        return classSymbol.name.length() == 0 ? localize(locale, "compiler.misc.anonymous.class", classSymbol.flatname) : classSymbol.fullname.toString();
    }

    @Override // com.sun.tools.javac.code.Type.Visitor
    public String visitClassType(Type.ClassType classType, Locale locale) {
        StringBuilder sb = new StringBuilder();
        if (classType.getEnclosingType().hasTag(TypeTag.CLASS) && classType.tsym.owner.kind == Kinds.Kind.TYP) {
            sb.append(visit(classType.getEnclosingType(), locale));
            sb.append('.');
            sb.append(printAnnotations(classType));
            sb.append(className(classType, false, locale));
        } else {
            sb.append(printAnnotations(classType));
            sb.append(className(classType, true, locale));
        }
        if (classType.getTypeArguments().nonEmpty()) {
            sb.append('<');
            sb.append(visitTypes(classType.getTypeArguments(), locale));
            sb.append('>');
        }
        return sb.toString();
    }

    @Override // com.sun.tools.javac.code.Type.Visitor
    public String visitForAll(Type.ForAll forAll, Locale locale) {
        return printAnnotations(forAll) + "<" + visitTypes(forAll.tvars, locale) + ">" + visit(forAll.qtype, locale);
    }

    @Override // com.sun.tools.javac.code.Symbol.Visitor
    public String visitMethodSymbol(Symbol.MethodSymbol methodSymbol, Locale locale) {
        if (methodSymbol.isStaticOrInstanceInit()) {
            return methodSymbol.owner.name.toString();
        }
        Name name = methodSymbol.name;
        String string = name == name.table.names.init ? methodSymbol.owner.name.toString() : name.toString();
        Type type = methodSymbol.type;
        if (type == null) {
            return string;
        }
        if (type.hasTag(TypeTag.FORALL)) {
            string = "<" + visitTypes(methodSymbol.type.getTypeArguments(), locale) + ">" + string;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append("(");
        sb.append(printMethodArgs(methodSymbol.type.mo71getParameterTypes(), (methodSymbol.flags() & Flags.VARARGS) != 0, locale));
        sb.append(")");
        return sb.toString();
    }

    @Override // com.sun.tools.javac.code.Type.Visitor
    public String visitMethodType(Type.MethodType methodType, Locale locale) {
        return "(" + printMethodArgs(methodType.argtypes, false, locale) + ")" + visit(methodType.restype, locale);
    }

    @Override // com.sun.tools.javac.code.Symbol.Visitor
    public String visitPackageSymbol(Symbol.PackageSymbol packageSymbol, Locale locale) {
        return packageSymbol.isUnnamed() ? localize(locale, "compiler.misc.unnamed.package", new Object[0]) : packageSymbol.fullname.toString();
    }

    @Override // com.sun.tools.javac.code.Type.Visitor
    public String visitPackageType(Type.PackageType packageType, Locale locale) {
        return packageType.tsym.getQualifiedName().toString();
    }

    public String visitSymbols(List<Symbol> list, Locale locale) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Symbol> it = list.iterator();
        while (it.hasNext()) {
            listBuffer.append(visit(it.next(), locale));
        }
        return listBuffer.toList().toString();
    }

    @Override // com.sun.tools.javac.code.Type.Visitor
    public String visitType(Type type, Locale locale) {
        Name name;
        Symbol.TypeSymbol typeSymbol = type.tsym;
        return (typeSymbol == null || (name = typeSymbol.name) == null) ? localize(locale, "compiler.misc.type.none", new Object[0]) : name.toString();
    }

    public String visitTypes(List<Type> list, Locale locale) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            listBuffer.append(visit(it.next(), locale));
        }
        return listBuffer.toList().toString();
    }

    @Override // com.sun.tools.javac.code.Type.Visitor
    public String visitUndetVar(Type.UndetVar undetVar, Locale locale) {
        if (undetVar.getInst() != null) {
            return printAnnotations(undetVar) + visit(undetVar.getInst(), locale);
        }
        return printAnnotations(undetVar) + visit(undetVar.qtype, locale) + "?";
    }

    @Override // com.sun.tools.javac.code.Type.Visitor
    public String visitWildcardType(Type.WildcardType wildcardType, Locale locale) {
        StringBuilder sb = new StringBuilder();
        sb.append(wildcardType.kind);
        if (wildcardType.kind != BoundKind.UNBOUND) {
            sb.append(printAnnotations(wildcardType));
            sb.append(visit(wildcardType.type, locale));
        }
        return sb.toString();
    }

    public String visit(Symbol symbol, Locale locale) {
        return (String) symbol.accept(this, locale);
    }

    @Override // com.sun.tools.javac.code.Type.Visitor
    public String visitErrorType(Type.ErrorType errorType, Locale locale) {
        return visitType((Type) errorType, locale);
    }

    @Override // com.sun.tools.javac.code.Type.Visitor
    public String visitModuleType(Type.ModuleType moduleType, Locale locale) {
        return visitType((Type) moduleType, locale);
    }

    @Override // com.sun.tools.javac.code.Symbol.Visitor
    public String visitOperatorSymbol(Symbol.OperatorSymbol operatorSymbol, Locale locale) {
        return visitMethodSymbol((Symbol.MethodSymbol) operatorSymbol, locale);
    }

    @Override // com.sun.tools.javac.code.Symbol.Visitor
    public String visitSymbol(Symbol symbol, Locale locale) {
        return symbol.name.toString();
    }

    @Override // com.sun.tools.javac.code.Symbol.Visitor
    public String visitTypeSymbol(Symbol.TypeSymbol typeSymbol, Locale locale) {
        return visitSymbol((Symbol) typeSymbol, locale);
    }

    @Override // com.sun.tools.javac.code.Type.Visitor
    public String visitTypeVar(Type.TypeVar typeVar, Locale locale) {
        return visitType((Type) typeVar, locale);
    }

    @Override // com.sun.tools.javac.code.Symbol.Visitor
    public String visitVarSymbol(Symbol.VarSymbol varSymbol, Locale locale) {
        return visitSymbol((Symbol) varSymbol, locale);
    }

    private String printAnnotations(Type type) {
        return printAnnotations(type, false);
    }
}
