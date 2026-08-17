package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public final class TypeMappingMode {
    public static final TypeMappingMode CLASS_DECLARATION;
    public static final Companion Companion = new Companion(null);
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

    public final boolean isForAnnotationParameter() {
        return this.isForAnnotationParameter;
    }

    public final TypeMappingMode toGenericArgumentMode(Variance variance, boolean z) {
        variance.getClass();
        if (!z || !this.isForAnnotationParameter) {
            int i = WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
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

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

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

    public TypeMappingMode() {
        this(false, false, false, false, false, null, false, null, null, false, false, 2047, null);
    }
}
