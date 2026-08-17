package com.sun.tools.javac.code;

import com.sun.jna.platform.win32.COM.tlb.imp.TlbConst;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.Options;
import java.util.HashMap;
import java.util.Map;
import javax.lang.model.SourceVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public enum Source {
    JDK1_2("1.2"),
    JDK1_3("1.3"),
    JDK1_4("1.4"),
    JDK5(TlbConst.TYPELIB_MINOR_VERSION_OFFICE),
    JDK6("6"),
    JDK7("7"),
    JDK8(TlbConst.TYPELIB_MAJOR_VERSION_WORD),
    JDK9("9"),
    JDK10("10"),
    JDK11("11"),
    JDK12("12"),
    JDK13("13"),
    JDK14("14"),
    JDK15("15"),
    JDK16("16"),
    JDK17("17"),
    JDK18("18"),
    JDK19("19"),
    JDK20("20"),
    JDK21("21"),
    JDK22("22"),
    JDK23("23"),
    JDK24("24"),
    JDK25("25"),
    JDK26("26");

    public static final Source DEFAULT;
    private static final Source MAX;
    public static final Source MIN;
    private static final Context.Key<Source> sourceKey = new Context.Key<>();
    private static final Map<String, Source> tab = new HashMap();
    public final String name;

    static {
        for (Source source : values()) {
            tab.put(source.name, source);
        }
        Map<String, Source> map = tab;
        map.put("1.5", JDK5);
        map.put("1.6", JDK6);
        map.put("1.7", JDK7);
        Source source2 = JDK8;
        map.put("1.8", source2);
        map.put("1.9", JDK9);
        map.put("1.10", JDK10);
        MIN = source2;
        Source source3 = values()[values().length - 1];
        MAX = source3;
        DEFAULT = source3;
    }

    Source(String str) {
        this.name = str;
    }

    public static Source instance(Context context) {
        Context.Key<Source> key = sourceKey;
        Source sourceLookup = (Source) context.get(key);
        if (sourceLookup == null) {
            String str = Options.instance(context).get(Option.SOURCE);
            if (str != null) {
                sourceLookup = lookup(str);
            }
            if (sourceLookup == null) {
                sourceLookup = DEFAULT;
            }
            context.put(key, sourceLookup);
        }
        return sourceLookup;
    }

    public static Source lookup(String str) {
        return tab.get(str);
    }

    public static SourceVersion toSourceVersion(Source source) {
        switch (source) {
            case JDK1_2:
                return SourceVersion.RELEASE_2;
            case JDK1_3:
                return SourceVersion.RELEASE_3;
            case JDK1_4:
                return SourceVersion.RELEASE_4;
            case JDK5:
                return SourceVersion.RELEASE_5;
            case JDK6:
                return SourceVersion.RELEASE_6;
            case JDK7:
                return SourceVersion.RELEASE_7;
            case JDK8:
                return SourceVersion.RELEASE_8;
            case JDK9:
                return SourceVersion.RELEASE_9;
            case JDK10:
                return SourceVersion.RELEASE_10;
            case JDK11:
                return SourceVersion.RELEASE_11;
            case JDK12:
                return SourceVersion.RELEASE_12;
            case JDK13:
                return SourceVersion.RELEASE_13;
            case JDK14:
                return SourceVersion.RELEASE_14;
            case JDK15:
                return SourceVersion.RELEASE_15;
            case JDK16:
                return SourceVersion.RELEASE_16;
            case JDK17:
                return SourceVersion.RELEASE_17;
            case JDK18:
                return SourceVersion.RELEASE_18;
            case JDK19:
                return SourceVersion.RELEASE_19;
            case JDK20:
                return SourceVersion.RELEASE_20;
            case JDK21:
                return SourceVersion.RELEASE_21;
            case JDK22:
                return SourceVersion.RELEASE_22;
            case JDK23:
                return SourceVersion.RELEASE_23;
            case JDK24:
                return SourceVersion.RELEASE_24;
            case JDK25:
                return SourceVersion.RELEASE_25;
            case JDK26:
                return SourceVersion.RELEASE_26;
            default:
                return null;
        }
    }

    public boolean isSupported() {
        return compareTo(MIN) >= 0;
    }

    public Target requiredTarget() {
        switch (ordinal()) {
            case 2:
                return Target.JDK1_4;
            case 3:
                return Target.JDK1_5;
            case 4:
                return Target.JDK1_6;
            case 5:
                return Target.JDK1_7;
            case 6:
                return Target.JDK1_8;
            case 7:
                return Target.JDK1_9;
            case 8:
                return Target.JDK1_10;
            case 9:
                return Target.JDK1_11;
            case 10:
                return Target.JDK1_12;
            case 11:
                return Target.JDK1_13;
            case 12:
                return Target.JDK1_14;
            case 13:
                return Target.JDK1_15;
            case 14:
                return Target.JDK1_16;
            case 15:
                return Target.JDK1_17;
            case 16:
                return Target.JDK1_18;
            case 17:
                return Target.JDK1_19;
            case 18:
                return Target.JDK1_20;
            case 19:
                return Target.JDK1_21;
            case 20:
                return Target.JDK1_22;
            case 21:
                return Target.JDK1_23;
            case 22:
                return Target.JDK1_24;
            case 23:
                return Target.JDK1_25;
            case 24:
                return Target.JDK1_26;
            default:
                return Target.JDK1_1;
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'MODULES' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class Feature {
        private static final /* synthetic */ Feature[] $VALUES;
        public static final Feature CAPTURE_MREF_RETURN_TYPE;
        public static final Feature CASE_NULL;
        public static final Feature DEPRECATION_ON_IMPORT;
        public static final Feature DIAMOND_WITH_ANONYMOUS_CLASS_CREATION;
        public static final Feature EFFECTIVELY_FINAL_VARIABLES_IN_TRY_WITH_RESOURCES;
        public static final Feature ERASE_POLY_SIG_RETURN_TYPE;
        public static final Feature FLEXIBLE_CONSTRUCTORS;
        public static final Feature IMPLICIT_CLASSES;
        public static final Feature IMPORT_ON_DEMAND_OBSERVABLE_PACKAGES;
        public static final Feature JAVA_BASE_TRANSITIVE;
        public static final Feature LOCAL_VARIABLE_TYPE_INFERENCE;
        public static final Feature MODULES;
        public static final Feature MODULE_IMPORTS;
        public static final Feature NO_TARGET_ANNOTATION_APPLICABILITY;
        public static final Feature PATTERN_MATCHING_IN_INSTANCEOF;
        public static final Feature PATTERN_SWITCH;
        public static final Feature PRIMITIVE_PATTERNS;
        public static final Feature PRIVATE_INTERFACE_METHODS;
        public static final Feature PRIVATE_MEMBERS_IN_PERMITS_CLAUSE;
        public static final Feature PRIVATE_SAFE_VARARGS;
        public static final Feature RECORDS;
        public static final Feature RECORD_PATTERNS;
        public static final Feature REDUNDANT_STRICTFP;
        public static final Feature REIFIABLE_TYPES_INSTANCEOF;
        public static final Feature SEALED_CLASSES;
        public static final Feature SWITCH_EXPRESSION;
        public static final Feature SWITCH_MULTIPLE_CASE_LABELS;
        public static final Feature SWITCH_RULE;
        public static final Feature TEXT_BLOCKS;
        public static final Feature UNCONDITIONAL_PATTERN_IN_INSTANCEOF;
        public static final Feature UNDERSCORE_IDENTIFIER;
        public static final Feature UNNAMED_VARIABLES;
        public static final Feature VAR_SYNTAX_IMPLICIT_LAMBDAS;
        public static final Feature WARN_ON_ILLEGAL_UTF8;
        private final Source maxLevel;
        private final Source minLevel;
        private final JCDiagnostic.Fragment optFragment;
        private final DiagKind optKind;

        public enum DiagKind {
            NORMAL,
            PLURAL
        }

        private static /* synthetic */ Feature[] $values() {
            return new Feature[]{MODULES, EFFECTIVELY_FINAL_VARIABLES_IN_TRY_WITH_RESOURCES, DEPRECATION_ON_IMPORT, PRIVATE_SAFE_VARARGS, DIAMOND_WITH_ANONYMOUS_CLASS_CREATION, UNDERSCORE_IDENTIFIER, PRIVATE_INTERFACE_METHODS, LOCAL_VARIABLE_TYPE_INFERENCE, VAR_SYNTAX_IMPLICIT_LAMBDAS, IMPORT_ON_DEMAND_OBSERVABLE_PACKAGES, SWITCH_MULTIPLE_CASE_LABELS, SWITCH_RULE, SWITCH_EXPRESSION, NO_TARGET_ANNOTATION_APPLICABILITY, TEXT_BLOCKS, PATTERN_MATCHING_IN_INSTANCEOF, REIFIABLE_TYPES_INSTANCEOF, RECORDS, SEALED_CLASSES, CASE_NULL, PATTERN_SWITCH, REDUNDANT_STRICTFP, UNCONDITIONAL_PATTERN_IN_INSTANCEOF, RECORD_PATTERNS, IMPLICIT_CLASSES, WARN_ON_ILLEGAL_UTF8, UNNAMED_VARIABLES, PRIMITIVE_PATTERNS, FLEXIBLE_CONSTRUCTORS, MODULE_IMPORTS, JAVA_BASE_TRANSITIVE, PRIVATE_MEMBERS_IN_PERMITS_CLAUSE, ERASE_POLY_SIG_RETURN_TYPE, CAPTURE_MREF_RETURN_TYPE};
        }

        static {
            Source source = Source.JDK9;
            JCDiagnostic.Fragment fragment = CompilerProperties.Fragments.FeatureModules;
            DiagKind diagKind = DiagKind.PLURAL;
            MODULES = new Feature("MODULES", 0, source, fragment, diagKind);
            EFFECTIVELY_FINAL_VARIABLES_IN_TRY_WITH_RESOURCES = new Feature("EFFECTIVELY_FINAL_VARIABLES_IN_TRY_WITH_RESOURCES", 1, source, CompilerProperties.Fragments.FeatureVarInTryWithResources, diagKind);
            Source source2 = Source.MIN;
            Source source3 = Source.JDK8;
            DEPRECATION_ON_IMPORT = new Feature("DEPRECATION_ON_IMPORT", 2, source2, source3);
            PRIVATE_SAFE_VARARGS = new Feature("PRIVATE_SAFE_VARARGS", 3, source);
            JCDiagnostic.Fragment fragment2 = CompilerProperties.Fragments.FeatureDiamondAndAnonClass;
            DiagKind diagKind2 = DiagKind.NORMAL;
            DIAMOND_WITH_ANONYMOUS_CLASS_CREATION = new Feature("DIAMOND_WITH_ANONYMOUS_CLASS_CREATION", 4, source, fragment2, diagKind2);
            UNDERSCORE_IDENTIFIER = new Feature("UNDERSCORE_IDENTIFIER", 5, source2, source3);
            PRIVATE_INTERFACE_METHODS = new Feature("PRIVATE_INTERFACE_METHODS", 6, source, CompilerProperties.Fragments.FeaturePrivateIntfMethods, diagKind);
            LOCAL_VARIABLE_TYPE_INFERENCE = new Feature("LOCAL_VARIABLE_TYPE_INFERENCE", 7, Source.JDK10);
            VAR_SYNTAX_IMPLICIT_LAMBDAS = new Feature("VAR_SYNTAX_IMPLICIT_LAMBDAS", 8, Source.JDK11, CompilerProperties.Fragments.FeatureVarSyntaxInImplicitLambda, diagKind);
            IMPORT_ON_DEMAND_OBSERVABLE_PACKAGES = new Feature("IMPORT_ON_DEMAND_OBSERVABLE_PACKAGES", 9, Source.JDK1_2, source3);
            Source source4 = Source.JDK14;
            SWITCH_MULTIPLE_CASE_LABELS = new Feature("SWITCH_MULTIPLE_CASE_LABELS", 10, source4, CompilerProperties.Fragments.FeatureMultipleCaseLabels, diagKind);
            SWITCH_RULE = new Feature("SWITCH_RULE", 11, source4, CompilerProperties.Fragments.FeatureSwitchRules, diagKind);
            SWITCH_EXPRESSION = new Feature("SWITCH_EXPRESSION", 12, source4, CompilerProperties.Fragments.FeatureSwitchExpressions, diagKind);
            NO_TARGET_ANNOTATION_APPLICABILITY = new Feature("NO_TARGET_ANNOTATION_APPLICABILITY", 13, source4);
            TEXT_BLOCKS = new Feature("TEXT_BLOCKS", 14, Source.JDK15, CompilerProperties.Fragments.FeatureTextBlocks, diagKind);
            Source source5 = Source.JDK16;
            PATTERN_MATCHING_IN_INSTANCEOF = new Feature("PATTERN_MATCHING_IN_INSTANCEOF", 15, source5, CompilerProperties.Fragments.FeaturePatternMatchingInstanceof, diagKind2);
            REIFIABLE_TYPES_INSTANCEOF = new Feature("REIFIABLE_TYPES_INSTANCEOF", 16, source5, CompilerProperties.Fragments.FeatureReifiableTypesInstanceof, diagKind);
            RECORDS = new Feature("RECORDS", 17, source5, CompilerProperties.Fragments.FeatureRecords, diagKind);
            Source source6 = Source.JDK17;
            SEALED_CLASSES = new Feature("SEALED_CLASSES", 18, source6, CompilerProperties.Fragments.FeatureSealedClasses, diagKind);
            Source source7 = Source.JDK21;
            CASE_NULL = new Feature("CASE_NULL", 19, source7, CompilerProperties.Fragments.FeatureCaseNull, diagKind2);
            PATTERN_SWITCH = new Feature("PATTERN_SWITCH", 20, source7, CompilerProperties.Fragments.FeaturePatternSwitch, diagKind);
            REDUNDANT_STRICTFP = new Feature("REDUNDANT_STRICTFP", 21, source6);
            UNCONDITIONAL_PATTERN_IN_INSTANCEOF = new Feature("UNCONDITIONAL_PATTERN_IN_INSTANCEOF", 22, source7, CompilerProperties.Fragments.FeatureUnconditionalPatternsInInstanceof, diagKind);
            RECORD_PATTERNS = new Feature("RECORD_PATTERNS", 23, source7, CompilerProperties.Fragments.FeatureDeconstructionPatterns, diagKind);
            Source source8 = Source.JDK25;
            IMPLICIT_CLASSES = new Feature("IMPLICIT_CLASSES", 24, source8, CompilerProperties.Fragments.FeatureImplicitClasses, diagKind);
            WARN_ON_ILLEGAL_UTF8 = new Feature("WARN_ON_ILLEGAL_UTF8", 25, source2, source7);
            UNNAMED_VARIABLES = new Feature("UNNAMED_VARIABLES", 26, Source.JDK22, CompilerProperties.Fragments.FeatureUnnamedVariables, diagKind);
            PRIMITIVE_PATTERNS = new Feature("PRIMITIVE_PATTERNS", 27, Source.JDK23, CompilerProperties.Fragments.FeaturePrimitivePatterns, diagKind);
            FLEXIBLE_CONSTRUCTORS = new Feature("FLEXIBLE_CONSTRUCTORS", 28, source8, CompilerProperties.Fragments.FeatureFlexibleConstructors, diagKind2);
            MODULE_IMPORTS = new Feature("MODULE_IMPORTS", 29, source8, CompilerProperties.Fragments.FeatureModuleImports, diagKind);
            JAVA_BASE_TRANSITIVE = new Feature("JAVA_BASE_TRANSITIVE", 30, source8, CompilerProperties.Fragments.FeatureJavaBaseTransitive, diagKind);
            PRIVATE_MEMBERS_IN_PERMITS_CLAUSE = new Feature("PRIVATE_MEMBERS_IN_PERMITS_CLAUSE", 31, Source.JDK19);
            ERASE_POLY_SIG_RETURN_TYPE = new Feature("ERASE_POLY_SIG_RETURN_TYPE", 32, Source.JDK24);
            CAPTURE_MREF_RETURN_TYPE = new Feature("CAPTURE_MREF_RETURN_TYPE", 33, Source.JDK26);
            $VALUES = $values();
        }

        private Feature(String str, int i, Source source, JCDiagnostic.Fragment fragment, DiagKind diagKind) {
            this(str, i, source, Source.MAX, fragment, diagKind);
        }

        public static Feature valueOf(String str) {
            return (Feature) Enum.valueOf(Feature.class, str);
        }

        public static Feature[] values() {
            return (Feature[]) $VALUES.clone();
        }

        public boolean allowedInSource(Source source) {
            return source.compareTo(this.minLevel) >= 0 && source.compareTo(this.maxLevel) <= 0;
        }

        public JCDiagnostic.Error error(String str) {
            Assert.checkNonNull(this.optFragment);
            DiagKind diagKind = this.optKind;
            DiagKind diagKind2 = DiagKind.NORMAL;
            JCDiagnostic.Fragment fragment = this.optFragment;
            return diagKind == diagKind2 ? CompilerProperties.Errors.FeatureNotSupportedInSource(fragment, str, this.minLevel.name) : CompilerProperties.Errors.FeatureNotSupportedInSourcePlural(fragment, str, this.minLevel.name);
        }

        public JCDiagnostic.Fragment fragment(String str) {
            Assert.checkNonNull(this.optFragment);
            DiagKind diagKind = this.optKind;
            DiagKind diagKind2 = DiagKind.NORMAL;
            JCDiagnostic.Fragment fragment = this.optFragment;
            return diagKind == diagKind2 ? CompilerProperties.Fragments.FeatureNotSupportedInSource(fragment, str, this.minLevel.name) : CompilerProperties.Fragments.FeatureNotSupportedInSourcePlural(fragment, str, this.minLevel.name);
        }

        public boolean isPlural() {
            Assert.checkNonNull(this.optKind);
            return this.optKind == DiagKind.PLURAL;
        }

        public JCDiagnostic.Fragment nameFragment() {
            Assert.checkNonNull(this.optFragment);
            return this.optFragment;
        }

        private Feature(String str, int i, Source source) {
            this(str, i, source, null, null);
        }

        private Feature(String str, int i, Source source, Source source2) {
            this(str, i, source, source2, null, null);
        }

        private Feature(String str, int i, Source source, Source source2, JCDiagnostic.Fragment fragment, DiagKind diagKind) {
            super(str, i);
            this.minLevel = source;
            this.maxLevel = source2;
            this.optFragment = fragment;
            this.optKind = diagKind;
        }
    }
}
