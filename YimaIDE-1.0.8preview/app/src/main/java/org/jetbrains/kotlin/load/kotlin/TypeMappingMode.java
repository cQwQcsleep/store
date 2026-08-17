package org.jetbrains.kotlin.load.kotlin;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB}\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0000\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0000\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0000\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u0003J\u0006\u0010\u001c\u001a\u00020\u0000J\u0006\u0010\u001d\u001a\u00020\u0000J\u0012\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;", "", "needPrimitiveBoxing", "", "needInlineClassWrapping", "isForAnnotationParameter", "skipDeclarationSiteWildcards", "skipDeclarationSiteWildcardsIfPossible", "genericArgumentMode", "kotlinCollectionsToJavaCollections", "genericContravariantArgumentMode", "genericInvariantArgumentMode", "mapTypeAliases", "ignoreTypeArgumentsBounds", "<init>", "(ZZZZZLorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;ZLorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;Lorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;ZZ)V", "getNeedPrimitiveBoxing", "()Z", "getNeedInlineClassWrapping", "getSkipDeclarationSiteWildcards", "getSkipDeclarationSiteWildcardsIfPossible", "getKotlinCollectionsToJavaCollections", "getMapTypeAliases", "getIgnoreTypeArgumentsBounds", "toGenericArgumentMode", "effectiveVariance", "Lorg/jetbrains/kotlin/types/Variance;", "ofArray", "wrapInlineClassesMode", "dontWrapInlineClassesMode", "Companion", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TypeMappingMode {
    public static final TypeMappingMode CLASS_DECLARATION;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final TypeMappingMode DEFAULT;
    public static final TypeMappingMode DEFAULT_UAST;
    public static final TypeMappingMode GENERIC_ARGUMENT;
    public static final TypeMappingMode GENERIC_ARGUMENT_FOR_SUPER_TYPES_AS_IS;
    public static final TypeMappingMode GENERIC_ARGUMENT_UAST;
    public static final TypeMappingMode INVOKE_DYNAMIC_BOOTSTRAP_ARGUMENT;
    public static final TypeMappingMode RETURN_TYPE_BOXED;
    public static final TypeMappingMode SUPER_TYPE;
    public static final TypeMappingMode SUPER_TYPE_AS_IS;
    public static final TypeMappingMode SUPER_TYPE_KOTLIN_COLLECTIONS_AS_IS;
    public static final TypeMappingMode VALUE_FOR_ANNOTATION;
    private final TypeMappingMode genericArgumentMode;
    private final TypeMappingMode genericContravariantArgumentMode;
    private final TypeMappingMode genericInvariantArgumentMode;
    private final boolean ignoreTypeArgumentsBounds;
    private final boolean isForAnnotationParameter;
    private final boolean kotlinCollectionsToJavaCollections;
    private final boolean mapTypeAliases;
    private final boolean needInlineClassWrapping;
    private final boolean needPrimitiveBoxing;
    private final boolean skipDeclarationSiteWildcards;
    private final boolean skipDeclarationSiteWildcardsIfPossible;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            try {
                iArr[Variance.IN_VARIANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Variance.INVARIANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        TypeMappingMode typeMappingMode = new TypeMappingMode(z, z2, z3, z4, z5, null, false, null, null, z6, z7, 2047, null);
        GENERIC_ARGUMENT = typeMappingMode;
        DefaultConstructorMarker defaultConstructorMarker = null;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        TypeMappingMode typeMappingMode2 = null;
        boolean z11 = false;
        TypeMappingMode typeMappingMode3 = null;
        TypeMappingMode typeMappingMode4 = null;
        TypeMappingMode typeMappingMode5 = new TypeMappingMode(z8, z9, z6, z7, z10, typeMappingMode2, z11, typeMappingMode3, typeMappingMode4, false, true, 1023, defaultConstructorMarker);
        GENERIC_ARGUMENT_FOR_SUPER_TYPES_AS_IS = typeMappingMode5;
        boolean z12 = false;
        TypeMappingMode typeMappingMode6 = new TypeMappingMode(z8, z9, z6, z7, z10, typeMappingMode2, z11, typeMappingMode3, typeMappingMode4, true, z12, 1535, defaultConstructorMarker);
        GENERIC_ARGUMENT_UAST = typeMappingMode6;
        RETURN_TYPE_BOXED = new TypeMappingMode(z8, true, z6, z7, z10, typeMappingMode2, z11, typeMappingMode3, typeMappingMode4, false, z12, 2045, defaultConstructorMarker);
        DEFAULT = new TypeMappingMode(z, z2, z3, z4, z5, typeMappingMode, false, null, null, z6, z7, 2012, typeMappingMode2);
        DEFAULT_UAST = new TypeMappingMode(false, false, z6, z7, false, typeMappingMode6, z11, typeMappingMode3, typeMappingMode4, true, z12, 1500, defaultConstructorMarker);
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        boolean z13 = false;
        TypeMappingMode typeMappingMode7 = null;
        TypeMappingMode typeMappingMode8 = null;
        CLASS_DECLARATION = new TypeMappingMode(z, true, z3, z4, z5, typeMappingMode, z13, typeMappingMode7, typeMappingMode8, z6, z7, 2012, defaultConstructorMarker2);
        boolean z14 = false;
        SUPER_TYPE = new TypeMappingMode(z, z14, z3, true, z5, typeMappingMode, z13, typeMappingMode7, typeMappingMode8, z6, z7, 2007, defaultConstructorMarker2);
        boolean z15 = false;
        boolean z16 = false;
        boolean z17 = true;
        boolean z18 = false;
        boolean z19 = false;
        boolean z20 = true;
        SUPER_TYPE_AS_IS = new TypeMappingMode(z15, z16, z6, z17, z18, typeMappingMode5, z11, typeMappingMode3, typeMappingMode4, z19, z20, 983, defaultConstructorMarker);
        SUPER_TYPE_KOTLIN_COLLECTIONS_AS_IS = new TypeMappingMode(z15, z16, z6, z17, z18, typeMappingMode5, z11, typeMappingMode3, typeMappingMode4, z19, z20, 919, defaultConstructorMarker);
        boolean z21 = true;
        boolean z22 = false;
        TypeMappingMode typeMappingMode9 = null;
        boolean z23 = false;
        VALUE_FOR_ANNOTATION = new TypeMappingMode(z, z14, z21, z22, z5, typeMappingMode, false, null, typeMappingMode9, z6, z23, 2008, null);
        INVOKE_DYNAMIC_BOOTSTRAP_ARGUMENT = new TypeMappingMode(true, z21, z22, z5, false, null, true, typeMappingMode9, null, z23, false, 1980, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TypeMappingMode(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, TypeMappingMode typeMappingMode, boolean z6, TypeMappingMode typeMappingMode2, TypeMappingMode typeMappingMode3, boolean z7, boolean z8, int i, DefaultConstructorMarker defaultConstructorMarker) {
        z = (i & 1) != 0 ? true : z;
        z2 = (i & 2) != 0 ? true : z2;
        z3 = (i & 4) != 0 ? false : z3;
        z4 = (i & 8) != 0 ? false : z4;
        z5 = (i & 16) != 0 ? false : z5;
        typeMappingMode = (i & 32) != 0 ? null : typeMappingMode;
        this(z, z2, z3, z4, z5, typeMappingMode, (i & 64) != 0 ? true : z6, (i & 128) != 0 ? typeMappingMode : typeMappingMode2, (i & 256) != 0 ? typeMappingMode : typeMappingMode3, (i & 512) != 0 ? false : z7, (i & BinaryVersion.MAX_LENGTH) != 0 ? false : z8);
    }

    @JvmStatic
    public static final TypeMappingMode createWithConstantDeclarationSiteWildcardsMode(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, TypeMappingMode typeMappingMode) {
        return INSTANCE.createWithConstantDeclarationSiteWildcardsMode(z, z2, z3, z4, z5, typeMappingMode);
    }

    @JvmStatic
    public static final TypeMappingMode getModeForReturnTypeNoGeneric(boolean z) {
        return INSTANCE.getModeForReturnTypeNoGeneric(z);
    }

    public static /* synthetic */ TypeMappingMode mapTypeAliases$default(TypeMappingMode typeMappingMode, TypeMappingMode typeMappingMode2, int i, Object obj) {
        if ((i & 1) != 0) {
            typeMappingMode2 = null;
        }
        return typeMappingMode.mapTypeAliases(typeMappingMode2);
    }

    public static /* synthetic */ TypeMappingMode toGenericArgumentMode$default(TypeMappingMode typeMappingMode, Variance variance, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return typeMappingMode.toGenericArgumentMode(variance, z);
    }

    public final TypeMappingMode dontWrapInlineClassesMode() {
        return new TypeMappingMode(this.needPrimitiveBoxing, false, this.isForAnnotationParameter, this.skipDeclarationSiteWildcards, this.skipDeclarationSiteWildcardsIfPossible, this.genericArgumentMode, this.kotlinCollectionsToJavaCollections, this.genericContravariantArgumentMode, this.genericInvariantArgumentMode, this.mapTypeAliases, this.ignoreTypeArgumentsBounds);
    }

    public final boolean getIgnoreTypeArgumentsBounds() {
        return this.ignoreTypeArgumentsBounds;
    }

    public final boolean getKotlinCollectionsToJavaCollections() {
        return this.kotlinCollectionsToJavaCollections;
    }

    public final boolean getMapTypeAliases() {
        return this.mapTypeAliases;
    }

    public final boolean getNeedInlineClassWrapping() {
        return this.needInlineClassWrapping;
    }

    public final boolean getNeedPrimitiveBoxing() {
        return this.needPrimitiveBoxing;
    }

    public final boolean getSkipDeclarationSiteWildcards() {
        return this.skipDeclarationSiteWildcards;
    }

    public final boolean getSkipDeclarationSiteWildcardsIfPossible() {
        return this.skipDeclarationSiteWildcardsIfPossible;
    }

    /* JADX INFO: renamed from: isForAnnotationParameter, reason: from getter */
    public final boolean getIsForAnnotationParameter() {
        return this.isForAnnotationParameter;
    }

    public final TypeMappingMode mapTypeAliases(TypeMappingMode genericArgumentMode) {
        boolean z = this.needPrimitiveBoxing;
        boolean z2 = this.needInlineClassWrapping;
        boolean z3 = this.isForAnnotationParameter;
        boolean z4 = this.skipDeclarationSiteWildcards;
        boolean z5 = this.skipDeclarationSiteWildcardsIfPossible;
        if (genericArgumentMode == null) {
            genericArgumentMode = this.genericArgumentMode;
        }
        return new TypeMappingMode(z, z2, z3, z4, z5, genericArgumentMode, this.kotlinCollectionsToJavaCollections, this.genericContravariantArgumentMode, this.genericInvariantArgumentMode, true, this.ignoreTypeArgumentsBounds);
    }

    public final TypeMappingMode toGenericArgumentMode(Variance effectiveVariance, boolean ofArray) {
        effectiveVariance.getClass();
        if (!ofArray || !this.isForAnnotationParameter) {
            int i = WhenMappings.$EnumSwitchMapping$0[effectiveVariance.ordinal()];
            if (i == 1) {
                TypeMappingMode typeMappingMode = this.genericContravariantArgumentMode;
                if (typeMappingMode != null) {
                    return typeMappingMode;
                }
            } else if (i != 2) {
                TypeMappingMode typeMappingMode2 = this.genericArgumentMode;
                if (typeMappingMode2 != null) {
                    return typeMappingMode2;
                }
            } else {
                TypeMappingMode typeMappingMode3 = this.genericInvariantArgumentMode;
                if (typeMappingMode3 != null) {
                    return typeMappingMode3;
                }
            }
        }
        return this;
    }

    public final TypeMappingMode wrapInlineClassesMode() {
        return new TypeMappingMode(this.needPrimitiveBoxing, true, this.isForAnnotationParameter, this.skipDeclarationSiteWildcards, this.skipDeclarationSiteWildcardsIfPossible, this.genericArgumentMode, this.kotlinCollectionsToJavaCollections, this.genericContravariantArgumentMode, this.genericInvariantArgumentMode, this.mapTypeAliases, this.ignoreTypeArgumentsBounds);
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J>\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00132\b\b\u0002\u0010\u0019\u001a\u00020\u00132\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0005H\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/TypeMappingMode$Companion;", "", "<init>", "()V", "GENERIC_ARGUMENT", "Lorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;", "GENERIC_ARGUMENT_FOR_SUPER_TYPES_AS_IS", "GENERIC_ARGUMENT_UAST", "RETURN_TYPE_BOXED", "DEFAULT", "DEFAULT_UAST", "CLASS_DECLARATION", "SUPER_TYPE", "SUPER_TYPE_AS_IS", "SUPER_TYPE_KOTLIN_COLLECTIONS_AS_IS", "VALUE_FOR_ANNOTATION", "INVOKE_DYNAMIC_BOOTSTRAP_ARGUMENT", "getModeForReturnTypeNoGeneric", "isAnnotationMethod", "", "createWithConstantDeclarationSiteWildcardsMode", "skipDeclarationSiteWildcards", "isForAnnotationParameter", "needInlineClassWrapping", "mapTypeAliases", "ignoreTypeArgumentsBounds", "fallbackMode", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ TypeMappingMode createWithConstantDeclarationSiteWildcardsMode$default(Companion companion, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, TypeMappingMode typeMappingMode, int i, Object obj) {
            if ((i & 16) != 0) {
                z5 = false;
            }
            boolean z6 = z5;
            if ((i & 32) != 0) {
                typeMappingMode = null;
            }
            return companion.createWithConstantDeclarationSiteWildcardsMode(z, z2, z3, z4, z6, typeMappingMode);
        }

        @JvmStatic
        public final TypeMappingMode createWithConstantDeclarationSiteWildcardsMode(boolean skipDeclarationSiteWildcards, boolean isForAnnotationParameter, boolean needInlineClassWrapping, boolean mapTypeAliases, boolean ignoreTypeArgumentsBounds, TypeMappingMode fallbackMode) {
            return new TypeMappingMode(false, needInlineClassWrapping, isForAnnotationParameter, skipDeclarationSiteWildcards, false, fallbackMode, false, null, null, mapTypeAliases, ignoreTypeArgumentsBounds, 465, null);
        }

        @JvmStatic
        public final TypeMappingMode getModeForReturnTypeNoGeneric(boolean isAnnotationMethod) {
            return isAnnotationMethod ? TypeMappingMode.VALUE_FOR_ANNOTATION : TypeMappingMode.DEFAULT;
        }

        private Companion() {
        }
    }

    @TypeMappingModeInternals
    public TypeMappingMode(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, TypeMappingMode typeMappingMode, boolean z6, TypeMappingMode typeMappingMode2, TypeMappingMode typeMappingMode3, boolean z7, boolean z8) {
        this.needPrimitiveBoxing = z;
        this.needInlineClassWrapping = z2;
        this.isForAnnotationParameter = z3;
        this.skipDeclarationSiteWildcards = z4;
        this.skipDeclarationSiteWildcardsIfPossible = z5;
        this.genericArgumentMode = typeMappingMode;
        this.kotlinCollectionsToJavaCollections = z6;
        this.genericContravariantArgumentMode = typeMappingMode2;
        this.genericInvariantArgumentMode = typeMappingMode3;
        this.mapTypeAliases = z7;
        this.ignoreTypeArgumentsBounds = z8;
    }

    @TypeMappingModeInternals
    public TypeMappingMode() {
        this(false, false, false, false, false, null, false, null, null, false, false, 2047, null);
    }
}
