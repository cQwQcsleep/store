package com.sun.tools.javac.util;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Printer;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.RichDiagnosticFormatter;
import defpackage.s22;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RichDiagnosticFormatter extends ForwardingDiagnosticFormatter<JCDiagnostic, AbstractDiagnosticFormatter> {
    final JCDiagnostic.Factory diags;
    final JavacMessages messages;
    protected ClassNameSimplifier nameSimplifier;
    private RichPrinter printer;
    protected Types.DefaultSymbolVisitor<Void, Void> symbolPreprocessor;
    final Symtab syms;
    protected Types.UnaryVisitor<Void> typePreprocessor;
    final Types types;
    WhereClauses whereClauses;

    public class ClassNameSimplifier {
        Map<Name, List<Symbol>> nameClashes = new HashMap();

        public ClassNameSimplifier() {
        }

        public void addUsage(Symbol symbol) {
            Name simpleName = symbol.getSimpleName();
            List<Symbol> listNil = this.nameClashes.get(simpleName);
            if (listNil == null) {
                listNil = List.nil();
            }
            if (listNil.contains(symbol)) {
                return;
            }
            this.nameClashes.put(simpleName, listNil.append(symbol));
        }

        public String simplify(Symbol symbol) {
            List<Symbol> list;
            String string = symbol.getQualifiedName().toString();
            if (symbol.type.isCompound() || symbol.type.isPrimitive() || !((list = this.nameClashes.get(symbol.getSimpleName())) == null || (list.size() == 1 && list.contains(symbol)))) {
                return string;
            }
            List listNil = List.nil();
            while (true) {
                Type type = symbol.type;
                TypeTag typeTag = TypeTag.CLASS;
                if (!type.hasTag(typeTag) || !symbol.type.getEnclosingType().hasTag(typeTag) || symbol.owner.kind != Kinds.Kind.TYP) {
                    break;
                }
                listNil = listNil.prepend(symbol.getSimpleName());
                symbol = symbol.owner;
            }
            List<javax.lang.model.element.Name> listPrepend = listNil.prepend(symbol.getSimpleName());
            StringBuilder sb = new StringBuilder();
            String str = "";
            for (javax.lang.model.element.Name name : listPrepend) {
                sb.append(str);
                sb.append((CharSequence) name);
                str = com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_THIS;
            }
            return sb.toString();
        }
    }

    public static class RichConfiguration extends ForwardingDiagnosticFormatter.ForwardingConfiguration {
        protected EnumSet<RichFormatterFeature> features;

        public enum RichFormatterFeature {
            WHERE_CLAUSES,
            SIMPLE_NAMES,
            UNIQUE_TYPEVAR_NAMES
        }

        public RichConfiguration(Options options, AbstractDiagnosticFormatter abstractDiagnosticFormatter) {
            super(abstractDiagnosticFormatter.getConfiguration());
            this.features = abstractDiagnosticFormatter.isRaw() ? EnumSet.noneOf(RichFormatterFeature.class) : EnumSet.of(RichFormatterFeature.SIMPLE_NAMES, RichFormatterFeature.WHERE_CLAUSES, RichFormatterFeature.UNIQUE_TYPEVAR_NAMES);
            String str = options.get("diags.formatterOptions");
            if (str != null) {
                for (String str2 : str.split(",")) {
                    if (str2.equals("-where")) {
                        this.features.remove(RichFormatterFeature.WHERE_CLAUSES);
                    } else if (str2.equals("where")) {
                        this.features.add(RichFormatterFeature.WHERE_CLAUSES);
                    }
                    if (str2.equals("-simpleNames")) {
                        this.features.remove(RichFormatterFeature.SIMPLE_NAMES);
                    } else if (str2.equals("simpleNames")) {
                        this.features.add(RichFormatterFeature.SIMPLE_NAMES);
                    }
                    if (str2.equals("-disambiguateTvars")) {
                        this.features.remove(RichFormatterFeature.UNIQUE_TYPEVAR_NAMES);
                    } else if (str2.equals("disambiguateTvars")) {
                        this.features.add(RichFormatterFeature.UNIQUE_TYPEVAR_NAMES);
                    }
                }
            }
        }

        public void disable(RichFormatterFeature richFormatterFeature) {
            this.features.remove(richFormatterFeature);
        }

        public void enable(RichFormatterFeature richFormatterFeature) {
            this.features.add(richFormatterFeature);
        }

        public RichFormatterFeature[] getAvailableFeatures() {
            return RichFormatterFeature.values();
        }

        public boolean isEnabled(RichFormatterFeature richFormatterFeature) {
            return this.features.contains(richFormatterFeature);
        }
    }

    public class RichPrinter extends Printer {
        public RichPrinter() {
        }

        @Override // com.sun.tools.javac.code.Printer
        public String capturedVarId(Type.CapturedType capturedType, Locale locale) {
            return RichDiagnosticFormatter.this.indexOf(capturedType, WhereClauseKind.CAPTURED) + "";
        }

        @Override // com.sun.tools.javac.code.Printer
        public String className(Type.ClassType classType, boolean z, Locale locale) {
            Symbol.TypeSymbol typeSymbol = classType.tsym;
            if (typeSymbol.name.length() == 0 || !RichDiagnosticFormatter.this.getConfiguration().isEnabled(RichConfiguration.RichFormatterFeature.SIMPLE_NAMES)) {
                return super.className(classType, z, locale);
            }
            return z ? RichDiagnosticFormatter.this.nameSimplifier.simplify(typeSymbol).toString() : typeSymbol.name.toString();
        }

        @Override // com.sun.tools.javac.code.Printer
        public String localize(Locale locale, String str, Object... objArr) {
            return ((AbstractDiagnosticFormatter) RichDiagnosticFormatter.this.formatter).localize(locale, str, objArr);
        }

        @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
        public String visitCapturedType(Type.CapturedType capturedType, Locale locale) {
            return RichDiagnosticFormatter.this.getConfiguration().isEnabled(RichConfiguration.RichFormatterFeature.WHERE_CLAUSES) ? localize(locale, "compiler.misc.captured.type", Integer.valueOf(RichDiagnosticFormatter.this.indexOf(capturedType, WhereClauseKind.CAPTURED))) : super.visitCapturedType(capturedType, locale);
        }

        @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Symbol.Visitor
        public String visitClassSymbol(Symbol.ClassSymbol classSymbol, Locale locale) {
            if (classSymbol.type.isCompound()) {
                return visit(classSymbol.type, locale);
            }
            String strSimplify = RichDiagnosticFormatter.this.nameSimplifier.simplify(classSymbol);
            return (strSimplify.length() == 0 || !RichDiagnosticFormatter.this.getConfiguration().isEnabled(RichConfiguration.RichFormatterFeature.SIMPLE_NAMES)) ? super.visitClassSymbol(classSymbol, locale) : strSimplify;
        }

        @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
        public String visitClassType(Type.ClassType classType, Locale locale) {
            return (classType.isCompound() && RichDiagnosticFormatter.this.getConfiguration().isEnabled(RichConfiguration.RichFormatterFeature.WHERE_CLAUSES)) ? localize(locale, "compiler.misc.intersection.type", Integer.valueOf(RichDiagnosticFormatter.this.indexOf(classType, WhereClauseKind.INTERSECTION))) : super.visitClassType(classType, locale);
        }

        @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Symbol.Visitor
        public String visitMethodSymbol(Symbol.MethodSymbol methodSymbol, Locale locale) {
            String strVisit = visit(methodSymbol.owner, locale);
            if (methodSymbol.isStaticOrInstanceInit()) {
                return strVisit;
            }
            Name name = methodSymbol.name;
            if (name != name.table.names.init) {
                strVisit = name.toString();
            }
            Type type = methodSymbol.type;
            if (type == null) {
                return strVisit;
            }
            if (type.hasTag(TypeTag.FORALL)) {
                strVisit = "<" + visitTypes(methodSymbol.type.getTypeArguments(), locale) + ">" + strVisit;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(strVisit);
            sb.append("(");
            sb.append(printMethodArgs(methodSymbol.type.mo71getParameterTypes(), (methodSymbol.flags() & Flags.VARARGS) != 0, locale));
            sb.append(")");
            return sb.toString();
        }

        @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
        public String visitType(Type type, Locale locale) {
            return type == RichDiagnosticFormatter.this.syms.botType ? localize(locale, "compiler.misc.type.null", new Object[0]) : super.visitType(type, locale);
        }

        @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
        public String visitTypeVar(Type.TypeVar typeVar, Locale locale) {
            return (RichDiagnosticFormatter.this.unique(typeVar) || !RichDiagnosticFormatter.this.getConfiguration().isEnabled(RichConfiguration.RichFormatterFeature.UNIQUE_TYPEVAR_NAMES)) ? typeVar.toString() : localize(locale, "compiler.misc.type.var", typeVar.toString(), Integer.valueOf(RichDiagnosticFormatter.this.indexOf(typeVar, WhereClauseKind.TYPEVAR)));
        }
    }

    public enum WhereClauseKind {
        TYPEVAR("where.description.typevar"),
        CAPTURED("where.description.captured"),
        INTERSECTION("where.description.intersection");

        private final String key;

        WhereClauseKind(String str) {
            this.key = str;
        }

        public String key() {
            return this.key;
        }
    }

    public static class WhereClauses {
        private final Map<WhereClauseKind, Map<Type, JCDiagnostic>> whereClauses;

        public WhereClauses() {
            EnumMap enumMap = new EnumMap(WhereClauseKind.class);
            for (WhereClauseKind whereClauseKind : WhereClauseKind.values()) {
                enumMap.put(whereClauseKind, new LinkedHashMap());
            }
            this.whereClauses = enumMap;
        }

        public Map<Type, JCDiagnostic> get(WhereClauseKind whereClauseKind) {
            return this.whereClauses.get(whereClauseKind);
        }
    }

    public RichDiagnosticFormatter(Context context) {
        super((AbstractDiagnosticFormatter) Log.instance(context).getDiagnosticFormatter());
        this.typePreprocessor = new Types.UnaryVisitor<Void>() { // from class: com.sun.tools.javac.util.RichDiagnosticFormatter.1
            private List<Type> getBounds(Type type) {
                return type.isCompound() ? RichDiagnosticFormatter.this.types.directSupertypes(type) : List.of(type);
            }

            public Void visit(List<Type> list) {
                Iterator<Type> it = list.iterator();
                while (it.hasNext()) {
                    visit(it.next());
                }
                return null;
            }

            @Override // com.sun.tools.javac.code.Types.SimpleVisitor, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
            public Void visitCapturedType(Type.CapturedType capturedType, Void r7) {
                RichDiagnosticFormatter richDiagnosticFormatter = RichDiagnosticFormatter.this;
                WhereClauseKind whereClauseKind = WhereClauseKind.CAPTURED;
                if (richDiagnosticFormatter.indexOf(capturedType, whereClauseKind) != -1) {
                    return null;
                }
                Type type = capturedType.lower;
                RichDiagnosticFormatter richDiagnosticFormatter2 = RichDiagnosticFormatter.this;
                RichDiagnosticFormatter.this.whereClauses.get(whereClauseKind).put(capturedType, richDiagnosticFormatter2.diags.fragment("where.captured".concat(type == richDiagnosticFormatter2.syms.botType ? ".1" : ""), capturedType, capturedType.getUpperBound(), capturedType.lower, capturedType.wildcard));
                visit(capturedType.wildcard);
                visit(capturedType.lower);
                visit(capturedType.getUpperBound());
                return null;
            }

            @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
            public Void visitClassType(Type.ClassType classType, Void r6) {
                Type.ClassType classType2;
                if (classType.isCompound()) {
                    RichDiagnosticFormatter richDiagnosticFormatter = RichDiagnosticFormatter.this;
                    WhereClauseKind whereClauseKind = WhereClauseKind.INTERSECTION;
                    if (richDiagnosticFormatter.indexOf(classType, whereClauseKind) == -1) {
                        Type typeSupertype = RichDiagnosticFormatter.this.types.supertype(classType);
                        List<Type> listInterfaces = RichDiagnosticFormatter.this.types.interfaces(classType);
                        RichDiagnosticFormatter.this.whereClauses.get(whereClauseKind).put(classType, RichDiagnosticFormatter.this.diags.fragment(CompilerProperties.Fragments.WhereIntersection(classType, listInterfaces.prepend(typeSupertype))));
                        visit(typeSupertype);
                        visit(listInterfaces);
                    }
                } else if (classType.tsym.name.length() == 0 && (classType2 = (Type.ClassType) classType.tsym.type) != null) {
                    List<Type> list = classType2.interfaces_field;
                    if (list == null || !list.nonEmpty()) {
                        visit(classType2.supertype_field);
                    } else {
                        visit(classType2.interfaces_field.head);
                    }
                }
                RichDiagnosticFormatter.this.nameSimplifier.addUsage(classType.tsym);
                visit(classType.getTypeArguments());
                try {
                    if (classType.getEnclosingType() != Type.noType) {
                        visit(classType.getEnclosingType());
                    }
                } catch (Symbol.CompletionFailure unused) {
                }
                return null;
            }

            @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
            public Void visitErrorType(Type.ErrorType errorType, Void r2) {
                Type originalType = errorType.getOriginalType();
                if (originalType == null) {
                    return null;
                }
                visit(originalType);
                return null;
            }

            @Override // com.sun.tools.javac.code.Types.SimpleVisitor, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
            public Void visitForAll(Type.ForAll forAll, Void r2) {
                visit(forAll.tvars);
                visit(forAll.qtype);
                return null;
            }

            @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
            public Void visitMethodType(Type.MethodType methodType, Void r2) {
                visit(methodType.argtypes);
                visit(methodType.restype);
                return null;
            }

            @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
            public Void visitTypeVar(Type.TypeVar typeVar, Void r9) {
                Type.TypeVar typeVar2 = (Type.TypeVar) typeVar.stripMetadata();
                if (RichDiagnosticFormatter.this.indexOf(typeVar2, WhereClauseKind.TYPEVAR) == -1) {
                    Type upperBound = typeVar2.getUpperBound();
                    while (upperBound instanceof Type.ErrorType) {
                        upperBound = ((Type.ErrorType) upperBound).getOriginalType();
                    }
                    List<Type> listNil = (upperBound == null || !(upperBound.hasTag(TypeTag.CLASS) || upperBound.hasTag(TypeTag.TYPEVAR))) ? List.nil() : getBounds(upperBound);
                    RichDiagnosticFormatter.this.nameSimplifier.addUsage(typeVar2.tsym);
                    Type type = listNil.head;
                    boolean z = type == null || type.hasTag(TypeTag.NONE) || listNil.head.hasTag(TypeTag.ERROR);
                    if ((typeVar2.tsym.flags() & 4096) == 0) {
                        RichDiagnosticFormatter.this.whereClauses.get(WhereClauseKind.TYPEVAR).put(typeVar2, RichDiagnosticFormatter.this.diags.fragment("where.typevar".concat(z ? ".1" : ""), typeVar2, listNil, Kinds.kindName(typeVar2.tsym.location()), typeVar2.tsym.location()));
                        RichDiagnosticFormatter.this.symbolPreprocessor.visit(typeVar2.tsym.location(), null);
                        visit(listNil);
                    } else {
                        Assert.check(!z);
                        RichDiagnosticFormatter.this.whereClauses.get(WhereClauseKind.TYPEVAR).put(typeVar2, RichDiagnosticFormatter.this.diags.fragment(CompilerProperties.Fragments.WhereFreshTypevar(typeVar2, listNil)));
                        visit(listNil);
                    }
                }
                return null;
            }

            @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
            public Void visitArrayType(Type.ArrayType arrayType, Void r2) {
                visit(arrayType.elemtype);
                return null;
            }

            @Override // com.sun.tools.javac.code.Type.Visitor
            public Void visitType(Type type, Void r2) {
                return null;
            }

            @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
            public Void visitWildcardType(Type.WildcardType wildcardType, Void r2) {
                visit(wildcardType.type);
                return null;
            }
        };
        this.symbolPreprocessor = new Types.DefaultSymbolVisitor<Void, Void>() { // from class: com.sun.tools.javac.util.RichDiagnosticFormatter.2
            @Override // com.sun.tools.javac.code.Types.DefaultSymbolVisitor, com.sun.tools.javac.code.Symbol.Visitor
            public Void visitClassSymbol(Symbol.ClassSymbol classSymbol, Void r2) {
                boolean zIsCompound = classSymbol.type.isCompound();
                RichDiagnosticFormatter richDiagnosticFormatter = RichDiagnosticFormatter.this;
                if (zIsCompound) {
                    richDiagnosticFormatter.typePreprocessor.visit(classSymbol.type);
                    return null;
                }
                richDiagnosticFormatter.nameSimplifier.addUsage(classSymbol);
                return null;
            }

            @Override // com.sun.tools.javac.code.Types.DefaultSymbolVisitor, com.sun.tools.javac.code.Symbol.Visitor
            public Void visitMethodSymbol(Symbol.MethodSymbol methodSymbol, Void r3) {
                visit(methodSymbol.owner, null);
                Type type = methodSymbol.type;
                if (type != null) {
                    RichDiagnosticFormatter.this.typePreprocessor.visit(type);
                }
                return null;
            }

            @Override // com.sun.tools.javac.code.Symbol.Visitor
            public Void visitSymbol(Symbol symbol, Void r2) {
                return null;
            }
        };
        setRichPrinter(new RichPrinter());
        this.syms = Symtab.instance(context);
        this.diags = JCDiagnostic.Factory.instance(context);
        this.types = Types.instance(context);
        this.messages = JavacMessages.instance(context);
        this.configuration = new RichConfiguration(Options.instance(context), (AbstractDiagnosticFormatter) this.formatter);
    }

    public static /* synthetic */ String a(RichDiagnosticFormatter richDiagnosticFormatter, JCDiagnostic jCDiagnostic, Locale locale) {
        StringBuilder sb = new StringBuilder();
        richDiagnosticFormatter.preprocessDiagnostic(jCDiagnostic);
        sb.append(((AbstractDiagnosticFormatter) richDiagnosticFormatter.formatter).format(jCDiagnostic, locale));
        if (richDiagnosticFormatter.getConfiguration().isEnabled(RichConfiguration.RichFormatterFeature.WHERE_CLAUSES)) {
            List<JCDiagnostic> whereClauses = richDiagnosticFormatter.getWhereClauses();
            String strIndentString = ((AbstractDiagnosticFormatter) richDiagnosticFormatter.formatter).isRaw() ? "" : ((AbstractDiagnosticFormatter) richDiagnosticFormatter.formatter).indentString(2);
            Iterator<JCDiagnostic> it = whereClauses.iterator();
            while (it.hasNext()) {
                String str = ((AbstractDiagnosticFormatter) richDiagnosticFormatter.formatter).format(it.next(), locale);
                if (str.length() > 0) {
                    sb.append("\n" + strIndentString + str);
                }
            }
        }
        return sb.toString();
    }

    public static /* synthetic */ String b(RichDiagnosticFormatter richDiagnosticFormatter, JCDiagnostic jCDiagnostic, Locale locale) {
        richDiagnosticFormatter.preprocessDiagnostic(jCDiagnostic);
        return super.formatMessage(jCDiagnostic, locale);
    }

    private String enter(Supplier<String> supplier) {
        ClassNameSimplifier classNameSimplifier = this.nameSimplifier;
        WhereClauses whereClauses = this.whereClauses;
        try {
            this.nameSimplifier = new ClassNameSimplifier();
            this.whereClauses = new WhereClauses();
            return supplier.get();
        } finally {
            this.nameSimplifier = classNameSimplifier;
            this.whereClauses = whereClauses;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int indexOf(Type type, WhereClauseKind whereClauseKind) {
        int i = 1;
        for (Type type2 : this.whereClauses.get(whereClauseKind).keySet()) {
            if (type2.tsym == type.tsym) {
                return i;
            }
            if (whereClauseKind != WhereClauseKind.TYPEVAR || type2.toString().equals(type.toString())) {
                i++;
            }
        }
        return -1;
    }

    public static RichDiagnosticFormatter instance(Context context) {
        RichDiagnosticFormatter richDiagnosticFormatter = (RichDiagnosticFormatter) context.get(RichDiagnosticFormatter.class);
        return richDiagnosticFormatter == null ? new RichDiagnosticFormatter(context) : richDiagnosticFormatter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean unique(Type.TypeVar typeVar) {
        Type.TypeVar typeVar2 = (Type.TypeVar) typeVar.stripMetadata();
        Iterator<Type> it = this.whereClauses.get(WhereClauseKind.TYPEVAR).keySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().stripMetadata().toString().equals(typeVar2.toString())) {
                i++;
            }
        }
        if (i >= 1) {
            return i == 1;
        }
        s22.a("Missing type variable in where clause: ", typeVar2);
        return false;
    }

    @Override // com.sun.tools.javac.util.ForwardingDiagnosticFormatter, com.sun.tools.javac.api.DiagnosticFormatter
    public String format(final JCDiagnostic jCDiagnostic, final Locale locale) {
        return enter(new Supplier() { // from class: nkc
            @Override // java.util.function.Supplier
            public final Object get() {
                return RichDiagnosticFormatter.a(this.b, jCDiagnostic, locale);
            }
        });
    }

    @Override // com.sun.tools.javac.util.ForwardingDiagnosticFormatter, com.sun.tools.javac.api.DiagnosticFormatter
    public String formatMessage(final JCDiagnostic jCDiagnostic, final Locale locale) {
        return enter(new Supplier() { // from class: mkc
            @Override // java.util.function.Supplier
            public final Object get() {
                return RichDiagnosticFormatter.b(this.b, jCDiagnostic, locale);
            }
        });
    }

    public RichPrinter getRichPrinter() {
        return this.printer;
    }

    public List<JCDiagnostic> getWhereClauses() {
        List listNil = List.nil();
        for (WhereClauseKind whereClauseKind : WhereClauseKind.values()) {
            List listNil2 = List.nil();
            Iterator<Map.Entry<Type, JCDiagnostic>> it = this.whereClauses.get(whereClauseKind).entrySet().iterator();
            while (it.hasNext()) {
                listNil2 = listNil2.prepend(it.next().getValue());
            }
            if (!listNil2.isEmpty()) {
                String strKey = whereClauseKind.key();
                if (listNil2.size() > 1) {
                    strKey = strKey + ".1";
                }
                listNil = listNil.prepend(new JCDiagnostic.MultilineDiagnostic(this.diags.fragment(strKey, this.whereClauses.get(whereClauseKind).keySet()), listNil2.reverse()));
            }
        }
        return listNil.reverse();
    }

    public void preprocessArgument(Object obj) {
        if (obj instanceof Type) {
            preprocessType((Type) obj);
            return;
        }
        if (obj instanceof JCDiagnostic.AnnotatedType) {
            preprocessType(((JCDiagnostic.AnnotatedType) obj).type());
            return;
        }
        if (obj instanceof Symbol) {
            preprocessSymbol((Symbol) obj);
            return;
        }
        if (obj instanceof JCDiagnostic) {
            preprocessDiagnostic((JCDiagnostic) obj);
            return;
        }
        if (obj instanceof Iterable) {
            Iterable iterable = (Iterable) obj;
            if (obj instanceof Path) {
                return;
            }
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                preprocessArgument(it.next());
            }
        }
    }

    public void preprocessDiagnostic(JCDiagnostic jCDiagnostic) {
        for (Object obj : jCDiagnostic.getArgs()) {
            if (obj != null) {
                preprocessArgument(obj);
            }
        }
        if (jCDiagnostic.isMultiline()) {
            Iterator<JCDiagnostic> it = jCDiagnostic.getSubdiagnostics().iterator();
            while (it.hasNext()) {
                preprocessDiagnostic(it.next());
            }
        }
    }

    public void preprocessSymbol(Symbol symbol) {
        this.symbolPreprocessor.visit(symbol, null);
    }

    public void preprocessType(Type type) {
        this.typePreprocessor.visit(type);
    }

    public void setRichPrinter(RichPrinter richPrinter) {
        this.printer = richPrinter;
        ((AbstractDiagnosticFormatter) this.formatter).setPrinter(richPrinter);
    }

    @Override // com.sun.tools.javac.util.ForwardingDiagnosticFormatter, com.sun.tools.javac.api.DiagnosticFormatter
    public RichConfiguration getConfiguration() {
        return (RichConfiguration) this.configuration;
    }
}
