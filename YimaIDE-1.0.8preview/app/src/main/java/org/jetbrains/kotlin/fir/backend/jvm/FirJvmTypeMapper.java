package org.jetbrains.kotlin.fir.backend.jvm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.builtins.jvm.JavaToKotlinClassMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter;
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapper;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.backend.jvm.FirJvmTypeMapper;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.load.kotlin.TypeMappingMode;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.types.AbstractTypeMapper;
import org.jetbrains.kotlin.types.TypeMappingContext;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 !2\u00020\u00012\u00020\u0002:\u0003!\"#B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J>\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0018\b\u0002\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0018\u00010\u0012J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\fJ\u0015\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001eH\u0000¢\u0006\u0002\b\u001fJ\u0010\u0010 \u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\u0016\u001a\u00060\u0017R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmTypeMapper;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "mapType", "Lorg/jetbrains/org/objectweb/asm/Type;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "mode", "Lorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;", "sw", "Lorg/jetbrains/kotlin/codegen/signature/JvmSignatureWriter;", "unresolvedQualifierRemapper", "Lkotlin/Function1;", Argument.Delimiters.none, "isPrimitiveBacked", Argument.Delimiters.none, "defaultContext", "Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmTypeMapper$Context;", "typeContext", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "getTypeContext", "()Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "getJvmShortName", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getJvmShortName$org_jetbrains_kotlin_jvm_backend", "safeShortClassName", "Companion", "Context", "PossiblyInnerConeType", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmTypeMapper implements FirSessionComponent, SessionHolder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ClassId NON_EXISTENT_ID;
    private static final ConeClassLikeType typeForNonExistentClass;
    private final Context defaultContext;
    private final FirSession session;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\b\u0010\tJ\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00000\u0005R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmTypeMapper$PossiblyInnerConeType;", Argument.Delimiters.none, "classifier", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "arguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "outerType", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Ljava/util/List;Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmTypeMapper$PossiblyInnerConeType;)V", "getClassifier", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getArguments", "()Ljava/util/List;", "segments", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class PossiblyInnerConeType {
        private final List<ConeTypeProjection> arguments;
        private final FirRegularClassSymbol classifier;
        private final PossiblyInnerConeType outerType;

        public PossiblyInnerConeType(FirRegularClassSymbol firRegularClassSymbol, List<? extends ConeTypeProjection> list, PossiblyInnerConeType possiblyInnerConeType) {
            list.getClass();
            this.classifier = firRegularClassSymbol;
            this.arguments = list;
            this.outerType = possiblyInnerConeType;
        }

        public final List<ConeTypeProjection> getArguments() {
            return this.arguments;
        }

        public final FirRegularClassSymbol getClassifier() {
            return this.classifier;
        }

        public final List<PossiblyInnerConeType> segments() {
            PossiblyInnerConeType possiblyInnerConeType = this.outerType;
            List<PossiblyInnerConeType> listSegments = possiblyInnerConeType != null ? possiblyInnerConeType.segments() : null;
            if (listSegments == null) {
                listSegments = CollectionsKt.emptyList();
            }
            return CollectionsKt.plus(listSegments, this);
        }
    }

    static {
        ClassId classId = ClassId.Companion.topLevel(StandardNames.INSTANCE.getNON_EXISTENT_CLASS());
        NON_EXISTENT_ID = classId;
        typeForNonExistentClass = TypeConstructionUtilsKt.constructClassType$default(TypeConstructionUtilsKt.toLookupTag(classId), null, false, null, 7, null);
    }

    public FirJvmTypeMapper(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.defaultContext = new Context(this, new Function1() { // from class: fa5
            public final Object invoke(Object obj) {
                return FirJvmTypeMapper.b((String) obj);
            }
        });
    }

    public static String b(String str) {
        str.getClass();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Type mapType$default(FirJvmTypeMapper firJvmTypeMapper, ConeKotlinType coneKotlinType, TypeMappingMode typeMappingMode, JvmSignatureWriter jvmSignatureWriter, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            typeMappingMode = TypeMappingMode.DEFAULT;
        }
        if ((i & 4) != 0) {
            jvmSignatureWriter = null;
        }
        if ((i & 8) != 0) {
            function1 = null;
        }
        return firJvmTypeMapper.mapType(coneKotlinType, typeMappingMode, jvmSignatureWriter, function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String safeShortClassName(ClassId classId) {
        String identifier = SpecialNames.safeIdentifier(classId.getShortClassName()).getIdentifier();
        identifier.getClass();
        return identifier;
    }

    public final String getJvmShortName$org_jetbrains_kotlin_jvm_backend(ClassId classId) {
        Name shortClassName;
        classId.getClass();
        String strAsString = null;
        if (!classId.isLocal()) {
            ClassId classIdMapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(classId.asSingleFqName().toUnsafe());
            if (classIdMapKotlinToJava != null && (shortClassName = classIdMapKotlinToJava.getShortClassName()) != null) {
                strAsString = shortClassName.asString();
            }
        }
        return strAsString == null ? safeShortClassName(classId) : strAsString;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    public final TypeSystemCommonBackendContext getTypeContext() {
        return this.defaultContext.m256getTypeContext();
    }

    public final boolean isPrimitiveBacked(ConeKotlinType type) {
        type.getClass();
        return AbstractTypeMapper.INSTANCE.isPrimitiveBacked(this.defaultContext, type);
    }

    public final Type mapType(ConeKotlinType type, TypeMappingMode mode, JvmSignatureWriter sw, Function1<? super String, String> unresolvedQualifierRemapper) {
        type.getClass();
        mode.getClass();
        return AbstractTypeMapper.mapType$default(AbstractTypeMapper.INSTANCE, unresolvedQualifierRemapper != null ? new Context(this, unresolvedQualifierRemapper) : this.defaultContext, type, mode, sw, false, 16, (Object) null);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmTypeMapper$Companion;", Argument.Delimiters.none, "<init>", "()V", "NON_EXISTENT_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "getNON_EXISTENT_ID", "()Lorg/jetbrains/kotlin/name/ClassId;", "typeForNonExistentClass", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ClassId getNON_EXISTENT_ID() {
            return FirJvmTypeMapper.NON_EXISTENT_ID;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0014\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0002J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J$\u0010\u0017\u001a\u00020\u0018*\u00020\u00022\u0006\u0010\n\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\f\u0010\u001d\u001a\u00020\u001e*\u00020\u000bH\u0002J\u000e\u0010\u001f\u001a\u0004\u0018\u00010 *\u00020!H\u0002J \u0010\u001d\u001a\u0004\u0018\u00010\u001e*\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010 2\u0006\u0010#\u001a\u00020$H\u0002J \u0010%\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020\rH\u0002J4\u0010%\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\u00022\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0'2\u0006\u0010\f\u001a\u00020\rH\u0002J.\u0010+\u001a\u00020\u00182\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001e0'2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010#\u001a\u00020$H\u0002R\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmTypeMapper$Context;", "Lorg/jetbrains/kotlin/types/TypeMappingContext;", "Lorg/jetbrains/kotlin/codegen/signature/JvmSignatureWriter;", "unresolvedQualifierRemapper", "Lkotlin/Function1;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmTypeMapper;Lkotlin/jvm/functions/Function1;)V", "mapType", "Lorg/jetbrains/org/objectweb/asm/Type;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "mode", "Lorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;", "sw", "typeContext", "Lorg/jetbrains/kotlin/fir/backend/jvm/ConeTypeSystemCommonBackendContextForTypeMapping;", "getTypeContext", "()Lorg/jetbrains/kotlin/fir/backend/jvm/ConeTypeSystemCommonBackendContextForTypeMapping;", "getClassInternalName", "typeConstructor", "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "getScriptInternalName", "writeGenericType", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "asmType", "hasNothingInNonContravariantPosition", Argument.Delimiters.none, "buildPossiblyInnerType", "Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmTypeMapper$PossiblyInnerConeType;", "parentClassOrNull", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "classifier", "index", Argument.Delimiters.none, "writeGenericArguments", "arguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "parameterSymbols", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "writeInnerParts", "innerTypesAsList", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class Context implements TypeMappingContext<JvmSignatureWriter> {
        final /* synthetic */ FirJvmTypeMapper this$0;
        private final ConeTypeSystemCommonBackendContextForTypeMapping typeContext;

        public Context(FirJvmTypeMapper firJvmTypeMapper, Function1<? super String, String> function1) {
            function1.getClass();
            this.this$0 = firJvmTypeMapper;
            this.typeContext = new ConeTypeSystemCommonBackendContextForTypeMapping(TypeComponentsKt.getTypeContext(firJvmTypeMapper.getSession()), function1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final PossiblyInnerConeType buildPossiblyInnerType(ConeClassLikeType coneClassLikeType, FirRegularClassSymbol firRegularClassSymbol, int i) {
            if (firRegularClassSymbol == null) {
                return null;
            }
            FirRegularClass firRegularClass = (FirRegularClass) firRegularClassSymbol.getFir();
            List<FirTypeParameterRef> typeParameters = firRegularClass.getTypeParameters();
            int i2 = 0;
            if (!(typeParameters instanceof Collection) || !typeParameters.isEmpty()) {
                Iterator<T> it = typeParameters.iterator();
                while (it.hasNext()) {
                    if ((((FirTypeParameterRef) it.next()) instanceof FirTypeParameter) && (i2 = i2 + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
            int i3 = i2 + i;
            if (firRegularClass.getStatus().isInner()) {
                return new PossiblyInnerConeType(firRegularClassSymbol, ArraysKt.toList(coneClassLikeType.getTypeArguments()).subList(i, i3), buildPossiblyInnerType(coneClassLikeType, parentClassOrNull(ScopeUtilsKt.defaultType(firRegularClass)), i3));
            }
            if (i3 != coneClassLikeType.getTypeArguments().length) {
                firRegularClass.isLocal();
            }
            return new PossiblyInnerConeType(firRegularClassSymbol, ArraysKt.toList(coneClassLikeType.getTypeArguments()).subList(i, coneClassLikeType.getTypeArguments().length), null);
        }

        private static final PossiblyInnerConeType buildPossiblyInnerType$createForError(ConeKotlinType coneKotlinType) {
            return new PossiblyInnerConeType(null, ArraysKt.toList(coneKotlinType.getTypeArguments()), null);
        }

        private final boolean hasNothingInNonContravariantPosition(ConeKotlinType type) {
            return KotlinTypeMapper.INSTANCE.hasNothingInNonContravariantPosition(m256getTypeContext(), type);
        }

        private final Type mapType(ConeKotlinType type, TypeMappingMode mode, JvmSignatureWriter sw) {
            return AbstractTypeMapper.mapType$default(AbstractTypeMapper.INSTANCE, this, type, mode, sw, false, 16, (Object) null);
        }

        public static /* synthetic */ Type mapType$default(Context context, ConeKotlinType coneKotlinType, TypeMappingMode typeMappingMode, JvmSignatureWriter jvmSignatureWriter, int i, Object obj) {
            if ((i & 2) != 0) {
                typeMappingMode = TypeMappingMode.DEFAULT;
            }
            if ((i & 4) != 0) {
                jvmSignatureWriter = null;
            }
            return context.mapType(coneKotlinType, typeMappingMode, jvmSignatureWriter);
        }

        private final FirRegularClassSymbol parentClassOrNull(ConeClassLikeType coneClassLikeType) {
            ClassId outerClassId = ConeTypeUtilsKt.getClassId(coneClassLikeType).getOuterClassId();
            if (outerClassId == null) {
                return null;
            }
            FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(this.this$0.getSession()).getClassLikeSymbolByClassId(outerClassId);
            if (classLikeSymbolByClassId instanceof FirRegularClassSymbol) {
                return (FirRegularClassSymbol) classLikeSymbolByClassId;
            }
            return null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final void writeGenericArguments(JvmSignatureWriter sw, PossiblyInnerConeType type, TypeMappingMode mode) {
            ConeClassLikeType coneClassLikeTypeDefaultType;
            FirRegularClassSymbol classifier = type.getClassifier();
            FirRegularClass firRegularClass = classifier != null ? (FirRegularClass) classifier.getFir() : null;
            if (firRegularClass == null || (coneClassLikeTypeDefaultType = ScopeUtilsKt.defaultType(firRegularClass)) == null) {
                coneClassLikeTypeDefaultType = FirJvmTypeMapper.typeForNonExistentClass;
            }
            List<FirTypeParameterRef> typeParameters = firRegularClass != null ? firRegularClass.getTypeParameters() : null;
            if (typeParameters == null) {
                typeParameters = CollectionsKt.emptyList();
            }
            List<FirTypeParameterRef> list = typeParameters;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((FirTypeParameterRef) it.next()).getSymbol());
            }
            List<ConeTypeProjection> arguments = type.getArguments();
            FunctionTypeKind functionTypeKindFunctionTypeKind$default = FunctionalTypeUtilsKt.functionTypeKind$default((ConeRigidType) coneClassLikeTypeDefaultType, this.this$0.getSession(), false, 2, (Object) null);
            if (((Intrinsics.areEqual(functionTypeKindFunctionTypeKind$default, FunctionTypeKind.Function.INSTANCE) || Intrinsics.areEqual(functionTypeKindFunctionTypeKind$default, FunctionTypeKind.SuspendFunction.INSTANCE)) && arguments.size() > 23) || FunctionalTypeUtilsKt.isReflectFunctionType(coneClassLikeTypeDefaultType, this.this$0.getSession())) {
                writeGenericArguments(sw, CollectionsKt.listOf(CollectionsKt.last(arguments)), CollectionsKt.listOf(CollectionsKt.last(arrayList)), mode);
            } else {
                writeGenericArguments(sw, arguments, arrayList, mode);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Type writeGenericArguments$lambda$2$1(Context context, KotlinTypeMarker kotlinTypeMarker, JvmSignatureWriter jvmSignatureWriter, TypeMappingMode typeMappingMode) {
            kotlinTypeMarker.getClass();
            jvmSignatureWriter.getClass();
            typeMappingMode.getClass();
            return context.mapType((ConeKotlinType) kotlinTypeMarker, typeMappingMode, jvmSignatureWriter);
        }

        private final void writeInnerParts(List<PossiblyInnerConeType> innerTypesAsList, JvmSignatureWriter sw, TypeMappingMode mode, int index) {
            ClassId non_existent_id;
            for (PossiblyInnerConeType possiblyInnerConeType : innerTypesAsList.subList(index, innerTypesAsList.size())) {
                FirJvmTypeMapper firJvmTypeMapper = this.this$0;
                FirRegularClassSymbol classifier = possiblyInnerConeType.getClassifier();
                if (classifier == null || (non_existent_id = classifier.getClassId()) == null) {
                    non_existent_id = FirJvmTypeMapper.INSTANCE.getNON_EXISTENT_ID();
                }
                sw.writeInnerClass(firJvmTypeMapper.getJvmShortName$org_jetbrains_kotlin_jvm_backend(non_existent_id));
                writeGenericArguments(sw, possiblyInnerConeType, mode);
            }
        }

        public String getClassInternalName(TypeConstructorMarker typeConstructor) {
            typeConstructor.getClass();
            if (typeConstructor instanceof ConeClassLikeLookupTag) {
                ClassId classId = ((ConeClassLikeLookupTag) typeConstructor).getClassId();
                return StringsKt.replace$default(classId.isLocal() ? this.this$0.safeShortClassName(classId) : classId.asString(), ".", InlineCodegenUtilsKt.CAPTURED_FIELD_PREFIX, false, 4, (Object) null);
            }
            w01.a("Failed requirement.");
            return null;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
        public String getScriptInternalName(TypeConstructorMarker typeConstructor) throws NotImplementedError {
            typeConstructor.getClass();
            throw new NotImplementedError("An operation is not implemented: Not yet implemented");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void writeGenericType(JvmSignatureWriter jvmSignatureWriter, KotlinTypeMarker kotlinTypeMarker, Type type, TypeMappingMode typeMappingMode) {
            ConeClassLikeType coneClassLikeTypeDefaultType;
            FirRegularClass firRegularClass;
            jvmSignatureWriter.getClass();
            kotlinTypeMarker.getClass();
            type.getClass();
            typeMappingMode.getClass();
            if (kotlinTypeMarker instanceof ConeKotlinType) {
                if (!jvmSignatureWriter.skipGenericSignature()) {
                    ConeKotlinType coneKotlinType = (ConeKotlinType) kotlinTypeMarker;
                    if (!hasNothingInNonContravariantPosition(coneKotlinType) && coneKotlinType.getTypeArguments().length != 0) {
                        PossiblyInnerConeType possiblyInnerConeTypeBuildPossiblyInnerType = buildPossiblyInnerType(coneKotlinType);
                        List<PossiblyInnerConeType> listSegments = possiblyInnerConeTypeBuildPossiblyInnerType.segments();
                        Iterator<PossiblyInnerConeType> it = listSegments.iterator();
                        int i = 0;
                        while (true) {
                            if (!it.hasNext()) {
                                i = -1;
                                break;
                            } else if (!it.next().getArguments().isEmpty()) {
                                break;
                            } else {
                                i++;
                            }
                        }
                        if (i < 0 || listSegments.size() == 1) {
                            jvmSignatureWriter.writeClassBegin(type);
                            writeGenericArguments(jvmSignatureWriter, possiblyInnerConeTypeBuildPossiblyInnerType, typeMappingMode);
                        } else {
                            PossiblyInnerConeType possiblyInnerConeType = listSegments.get(i);
                            FirRegularClassSymbol classifier = possiblyInnerConeType.getClassifier();
                            if (classifier == null || (firRegularClass = (FirRegularClass) classifier.getFir()) == null || (coneClassLikeTypeDefaultType = ScopeUtilsKt.defaultType(firRegularClass)) == null) {
                                coneClassLikeTypeDefaultType = FirJvmTypeMapper.typeForNonExistentClass;
                            }
                            jvmSignatureWriter.writeOuterClassBegin(type, mapType$default(this, coneClassLikeTypeDefaultType, null, null, 6, null).getInternalName());
                            writeGenericArguments(jvmSignatureWriter, possiblyInnerConeType, typeMappingMode);
                            writeInnerParts(listSegments, jvmSignatureWriter, typeMappingMode, i + 1);
                        }
                        jvmSignatureWriter.writeClassEnd();
                        return;
                    }
                }
                jvmSignatureWriter.writeAsmType(type);
            }
        }

        /* JADX INFO: renamed from: getTypeContext, reason: from getter and merged with bridge method [inline-methods] */
        public ConeTypeSystemCommonBackendContextForTypeMapping m256getTypeContext() {
            return this.typeContext;
        }

        /* JADX WARN: Code duplicated, block: B:14:0x003e  */
        private final PossiblyInnerConeType buildPossiblyInnerType(ConeKotlinType coneKotlinType) {
            PossiblyInnerConeType possiblyInnerConeTypeBuildPossiblyInnerType;
            if (!(coneKotlinType instanceof ConeClassLikeType)) {
                return buildPossiblyInnerType$createForError(coneKotlinType);
            }
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneKotlinType;
            FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) this.this$0, coneClassLikeType.getLookupTag());
            if (symbol instanceof FirRegularClassSymbol) {
                possiblyInnerConeTypeBuildPossiblyInnerType = buildPossiblyInnerType(coneClassLikeType, (FirRegularClassSymbol) symbol, 0);
            } else if (symbol instanceof FirTypeAliasSymbol) {
                ConeClassLikeType coneClassLikeTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) this.this$0, coneClassLikeType);
                FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) this.this$0, coneClassLikeTypeFullyExpandedType.getLookupTag());
                if (regularClassSymbol != null) {
                    possiblyInnerConeTypeBuildPossiblyInnerType = buildPossiblyInnerType(coneClassLikeTypeFullyExpandedType, regularClassSymbol, 0);
                } else {
                    possiblyInnerConeTypeBuildPossiblyInnerType = null;
                }
            } else {
                possiblyInnerConeTypeBuildPossiblyInnerType = null;
            }
            return possiblyInnerConeTypeBuildPossiblyInnerType == null ? buildPossiblyInnerType$createForError(coneKotlinType) : possiblyInnerConeTypeBuildPossiblyInnerType;
        }

        private final void writeGenericArguments(JvmSignatureWriter sw, List<? extends ConeTypeProjection> arguments, List<FirTypeParameterSymbol> parameterSymbols, TypeMappingMode mode) {
            KotlinTypeMapper.Companion companion = KotlinTypeMapper.INSTANCE;
            List<FirTypeParameterSymbol> list = parameterSymbols;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new ConeTypeParameterLookupTag((FirTypeParameterSymbol) it.next()));
            }
            companion.writeGenericArguments(m256getTypeContext(), sw, arguments, arrayList, mode, new Function3() { // from class: org.jetbrains.kotlin.fir.backend.jvm.a
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return FirJvmTypeMapper.Context.writeGenericArguments$lambda$2$1(this.b, (KotlinTypeMarker) obj, (JvmSignatureWriter) obj2, (TypeMappingMode) obj3);
                }
            });
        }
    }
}
