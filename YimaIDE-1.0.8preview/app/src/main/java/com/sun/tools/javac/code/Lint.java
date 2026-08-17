package com.sun.tools.javac.code;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import defpackage.xa9;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Lint {
    protected static final Context.Key<Lint> lintKey = new Context.Key<>();
    private static final Map<String, LintCategory> map = new LinkedHashMap(40);
    private final Context context;
    private final Log log;
    private Names names;
    private final Options options;
    private EnumSet<LintCategory> suppressedValues;
    private Symtab syms;
    private EnumSet<LintCategory> values;

    public Lint(Lint lint) {
        lint.initializeRootIfNeeded();
        this.context = lint.context;
        this.options = lint.options;
        this.log = lint.log;
        this.syms = lint.syms;
        this.names = lint.names;
        this.values = lint.values.clone();
        this.suppressedValues = lint.suppressedValues.clone();
    }

    public static /* synthetic */ boolean b(Attribute attribute) {
        return attribute instanceof Attribute.Constant;
    }

    public static /* synthetic */ String c(Attribute attribute) {
        return (String) ((Attribute.Constant) attribute).value;
    }

    public static /* synthetic */ boolean d(Lint lint, Attribute.Compound compound) {
        lint.getClass();
        return compound.type.tsym == lint.syms.suppressWarningsType.tsym;
    }

    public static /* synthetic */ boolean f(Lint lint, Source source, LintCategory lintCategory) {
        lint.getClass();
        int iOrdinal = lintCategory.ordinal();
        if (iOrdinal == 5) {
            return source.compareTo(Source.JDK9) >= 0;
        }
        if (iOrdinal == 29) {
            return Source.Feature.REDUNDANT_STRICTFP.allowedInSource(source);
        }
        if (iOrdinal != 35) {
            return lintCategory.enabledByDefault;
        }
        return !lint.options.isSet(Option.PREVIEW);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EnumSet<LintCategory> getDefaults() {
        EnumSet<LintCategory> enumSetNewEmptySet = LintCategory.newEmptySet();
        final Source sourceInstance = Source.instance(this.context);
        Stream streamFilter = Stream.of((Object[]) LintCategory.values()).filter(new Predicate() { // from class: wa9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Lint.f(this.b, sourceInstance, (Lint.LintCategory) obj);
            }
        });
        Objects.requireNonNull(enumSetNewEmptySet);
        streamFilter.forEach(new xa9(enumSetNewEmptySet));
        return enumSetNewEmptySet;
    }

    private void initializeRootIfNeeded() {
        if (this.values == null) {
            this.values = this.options.getLintCategoriesOf(Option.XLINT, new Supplier() { // from class: bb9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return this.b.getDefaults();
                }
            });
            this.suppressedValues = LintCategory.newEmptySet();
        }
    }

    private void initializeSymbolsIfNeeded() {
        if (this.syms == null) {
            this.syms = Symtab.instance(this.context);
            this.names = Names.instance(this.context);
        }
    }

    public static Lint instance(Context context) {
        Lint lint = (Lint) context.get(lintKey);
        return lint == null ? new Lint(context) : lint;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EnumSet<LintCategory> suppressionsFrom(Attribute.Compound compound) {
        EnumSet<LintCategory> enumSetNewEmptySet = LintCategory.newEmptySet();
        Attribute attributeMember = compound.member(this.names.value);
        if (attributeMember instanceof Attribute.Array) {
            for (Attribute attribute : ((Attribute.Array) attributeMember).values) {
                Optional optionalFilter = Optional.of(attribute).filter(new Predicate() { // from class: cb9
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Lint.b((Attribute) obj);
                    }
                }).map(new Function() { // from class: db9
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return Lint.c((Attribute) obj);
                    }
                }).flatMap(new Function() { // from class: eb9
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return Lint.LintCategory.get((String) obj);
                    }
                }).filter(new Predicate() { // from class: fb9
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return ((Lint.LintCategory) obj).annotationSuppression;
                    }
                });
                Objects.requireNonNull(enumSetNewEmptySet);
                optionalFilter.ifPresent(new xa9(enumSetNewEmptySet));
            }
        }
        return enumSetNewEmptySet;
    }

    public Lint augment(Symbol symbol) {
        EnumSet<LintCategory> enumSetSuppressionsFrom = suppressionsFrom(symbol);
        if (enumSetSuppressionsFrom.isEmpty()) {
            return this;
        }
        Lint lint = new Lint(this);
        lint.values.removeAll(enumSetSuppressionsFrom);
        lint.suppressedValues.addAll(enumSetSuppressionsFrom);
        return lint;
    }

    public Lint enable(LintCategory... lintCategoryArr) {
        Lint lint = new Lint(this);
        lint.values.addAll(Arrays.asList(lintCategoryArr));
        lint.suppressedValues.removeAll(Arrays.asList(lintCategoryArr));
        return lint;
    }

    public boolean isEnabled(LintCategory lintCategory) {
        initializeRootIfNeeded();
        return this.values.contains(lintCategory);
    }

    public boolean isSuppressed(LintCategory lintCategory) {
        initializeRootIfNeeded();
        return this.suppressedValues.contains(lintCategory);
    }

    public Lint suppress(LintCategory... lintCategoryArr) {
        Lint lint = new Lint(this);
        lint.values.removeAll(Arrays.asList(lintCategoryArr));
        lint.suppressedValues.addAll(Arrays.asList(lintCategoryArr));
        return lint;
    }

    public String toString() {
        initializeRootIfNeeded();
        return "Lint:[enable" + this.values + ",suppress" + this.suppressedValues + "]";
    }

    public enum LintCategory {
        AUXILIARYCLASS("auxiliaryclass"),
        CAST("cast"),
        CLASSFILE("classfile", false, false, new String[0]),
        DANGLING_DOC_COMMENTS("dangling-doc-comments"),
        DEPRECATION("deprecation"),
        DEP_ANN("dep-ann", true, true, new String[0]),
        DIVZERO("divzero"),
        EMPTY(Constants.ELEMNAME_EMPTY_STRING),
        EXPORTS(PsiKeyword.EXPORTS),
        FALLTHROUGH("fallthrough"),
        FINALLY(PsiKeyword.FINALLY),
        IDENTITY("identity", true, true, "synchronization"),
        INCUBATING("incubating", false, true, new String[0]),
        LOSSY_CONVERSIONS("lossy-conversions"),
        MISSING_EXPLICIT_CTOR("missing-explicit-ctor"),
        MODULE(PsiKeyword.MODULE, true, true, new String[0]),
        OPENS(PsiKeyword.OPENS, true, true, new String[0]),
        OPTIONS("options", false, false, new String[0]),
        OUTPUT_FILE_CLASH("output-file-clash", false, false, new String[0]),
        OVERLOADS("overloads"),
        OVERRIDES("overrides"),
        PATH("path", false, false, new String[0]),
        PROCESSING("processing", false, false, new String[0]),
        RAW("rawtypes"),
        REMOVAL("removal", true, true, new String[0]),
        REQUIRES_AUTOMATIC("requires-automatic"),
        REQUIRES_TRANSITIVE_AUTOMATIC("requires-transitive-automatic", true, true, new String[0]),
        SERIAL("serial"),
        STATIC(PsiKeyword.STATIC),
        STRICTFP(PsiKeyword.STRICTFP, true, true, new String[0]),
        TEXT_BLOCKS("text-blocks"),
        THIS_ESCAPE("this-escape"),
        TRY(PsiKeyword.TRY),
        UNCHECKED("unchecked"),
        VARARGS("varargs"),
        PREVIEW("preview", true, true, new String[0]),
        RESTRICTED("restricted");

        public final boolean annotationSuppression;
        public final boolean enabledByDefault;
        public final String option;
        public final List<String> optionList;

        LintCategory(String str, boolean z, boolean z2, String... strArr) {
            this.option = str;
            this.annotationSuppression = z;
            this.enabledByDefault = z2;
            ArrayList arrayList = new ArrayList(strArr.length + 1);
            arrayList.add(str);
            Collections.addAll(arrayList, strArr);
            List<String> listUnmodifiableList = Collections.unmodifiableList(arrayList);
            this.optionList = listUnmodifiableList;
            listUnmodifiableList.forEach(new Consumer() { // from class: gb9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Lint.LintCategory.b(this.b, (String) obj);
                }
            });
        }

        public static /* synthetic */ void b(LintCategory lintCategory, String str) {
            lintCategory.getClass();
            Lint.map.put(str, lintCategory);
        }

        public static Optional<LintCategory> get(String str) {
            return Optional.ofNullable((LintCategory) Lint.map.get(str));
        }

        public static EnumSet<LintCategory> newEmptySet() {
            return EnumSet.noneOf(LintCategory.class);
        }

        public static Set<String> options() {
            return Collections.unmodifiableSet(Lint.map.keySet());
        }

        LintCategory(String str) {
            this(str, true, false, new String[0]);
        }
    }

    public Lint(Context context) {
        this.context = context;
        context.put(lintKey, this);
        this.options = Options.instance(context);
        this.log = Log.instance(context);
    }

    private EnumSet<LintCategory> suppressionsFrom(Stream<Attribute.Compound> stream) {
        initializeSymbolsIfNeeded();
        final EnumSet<LintCategory> enumSetNewEmptySet = LintCategory.newEmptySet();
        Stream<R> map2 = stream.filter(new Predicate() { // from class: ya9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Lint.d(this.b, (Attribute.Compound) obj);
            }
        }).map(new Function() { // from class: za9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.suppressionsFrom((Attribute.Compound) obj);
            }
        });
        Objects.requireNonNull(enumSetNewEmptySet);
        map2.forEach(new Consumer() { // from class: ab9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                enumSetNewEmptySet.addAll((EnumSet) obj);
            }
        });
        return enumSetNewEmptySet;
    }

    public EnumSet<LintCategory> suppressionsFrom(Symbol symbol) {
        EnumSet<LintCategory> enumSetSuppressionsFrom = suppressionsFrom(symbol.getDeclarationAttributes().stream());
        if (symbol.isDeprecated() && symbol.isDeprecatableViaAnnotation()) {
            enumSetSuppressionsFrom.add(LintCategory.DEPRECATION);
        }
        return enumSetSuppressionsFrom;
    }
}
