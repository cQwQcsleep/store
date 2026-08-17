package org.jetbrains.kotlin.fir.scopes.jvm;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.jvm.JavaToKotlinClassMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.scopes.jvm.SignatureUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitAnyTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitNullableAnyTypeRef;
import org.jetbrains.kotlin.fir.types.jvm.FirJavaTypeRef;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.load.java.structure.JavaPrimitiveType;
import org.jetbrains.kotlin.load.kotlin.SignatureBuildingComponents;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a$\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0016\b\u0002\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004\u001a8\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\t\u001a\u00020\n2\u0016\b\u0002\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004\u001a\"\u0010\u000f\u001a\u00020\u0001*\u00020\u00062\u0016\b\u0002\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004\u001a<\u0010\u0010\u001a\u00020\u0011*\u00060\u0012j\u0002`\u00132\u0006\u0010\u0014\u001a\u00020\u00062\u0014\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00042\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002\u001a\f\u0010\u001a\u001a\u00020\n*\u00020\u0005H\u0002\"\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"computeJvmSignature", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "typeConversion", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "computeJvmDescriptor", "customName", "includeReturnType", Argument.Delimiters.none, "PRIMITIVE_TYPE_SIGNATURE", Argument.Delimiters.none, "PRIMITIVE_TYPE_ARRAYS_SIGNATURE", "PRIMITIVE_TYPE_OR_ARRAY_SIGNATURE", "computeJvmDescriptorRepresentation", "appendConeType", Argument.Delimiters.none, "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "coneType", "visitedTypeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "unitClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "isVoid", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SignatureUtilsKt {
    private static final Map<String, String> PRIMITIVE_TYPE_ARRAYS_SIGNATURE;
    private static final Map<String, String> PRIMITIVE_TYPE_OR_ARRAY_SIGNATURE;
    private static final Map<String, String> PRIMITIVE_TYPE_SIGNATURE;
    private static final ClassId unitClassId;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.jvm.SignatureUtilsKt$computeJvmDescriptor$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<FirTypeRef, ConeKotlinType> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1, FirTypeUtilsKt.class, "coneTypeSafe", "coneTypeSafe(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", 1);
        }

        public final ConeKotlinType invoke(FirTypeRef firTypeRef) {
            firTypeRef.getClass();
            FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            if (coneType == null) {
                return null;
            }
            return coneType;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.jvm.SignatureUtilsKt$computeJvmDescriptorRepresentation$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00661 extends FunctionReferenceImpl implements Function1<FirTypeRef, ConeKotlinType> {
        public static final C00661 INSTANCE = new C00661();

        public C00661() {
            super(1, FirTypeUtilsKt.class, "coneTypeSafe", "coneTypeSafe(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", 1);
        }

        public final ConeKotlinType invoke(FirTypeRef firTypeRef) {
            firTypeRef.getClass();
            FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            if (coneType == null) {
                return null;
            }
            return coneType;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.jvm.SignatureUtilsKt$computeJvmSignature$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00671 extends FunctionReferenceImpl implements Function1<FirTypeRef, ConeKotlinType> {
        public static final C00671 INSTANCE = new C00671();

        public C00671() {
            super(1, FirTypeUtilsKt.class, "coneTypeSafe", "coneTypeSafe(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", 1);
        }

        public final ConeKotlinType invoke(FirTypeRef firTypeRef) {
            firTypeRef.getClass();
            FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            if (coneType == null) {
                return null;
            }
            return coneType;
        }
    }

    static {
        Map<String, String> mapMapOf = MapsKt.mapOf(new Pair[]{TuplesKt.to("Boolean", "Z"), TuplesKt.to("Byte", "B"), TuplesKt.to("Char", "C"), TuplesKt.to("Short", "S"), TuplesKt.to("Int", "I"), TuplesKt.to("Long", "J"), TuplesKt.to("Float", "F"), TuplesKt.to("Double", "D")});
        PRIMITIVE_TYPE_SIGNATURE = mapMapOf;
        ArrayList arrayList = new ArrayList(mapMapOf.size());
        for (Map.Entry<String, String> entry : mapMapOf.entrySet()) {
            String key = entry.getKey();
            String str = key + "Array";
            arrayList.add(TuplesKt.to(str, "[" + entry.getValue()));
        }
        Map<String, String> map = MapsKt.toMap(arrayList);
        PRIMITIVE_TYPE_ARRAYS_SIGNATURE = map;
        PRIMITIVE_TYPE_OR_ARRAY_SIGNATURE = MapsKt.plus(PRIMITIVE_TYPE_SIGNATURE, map);
        unitClassId = ClassId.Companion.topLevel(new FqName("kotlin.Unit"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void appendConeType(StringBuilder sb, ConeKotlinType coneKotlinType, Function1<? super FirTypeRef, ? extends ConeKotlinType> function1, Set<FirTypeParameterSymbol> set) {
        String str;
        boolean z = coneKotlinType instanceof ConeClassLikeType;
        Pair pair = null;
        ConeClassLikeType coneClassLikeType = z ? (ConeClassLikeType) coneKotlinType : null;
        if (coneClassLikeType != null) {
            ClassId classId = coneClassLikeType.getLookupTag().getClassId();
            if (Intrinsics.areEqual(classId.getPackageFqName().toString(), "kotlin") && (str = PRIMITIVE_TYPE_OR_ARRAY_SIGNATURE.get(classId.getShortClassName().getIdentifier())) != null) {
                sb.append(str);
                return;
            }
        }
        if (coneKotlinType instanceof ConeErrorType) {
            return;
        }
        if (z) {
            appendConeType$appendClassLikeType(sb, function1, set, (ConeClassLikeType) coneKotlinType);
            return;
        }
        if (!(coneKotlinType instanceof ConeTypeParameterType)) {
            if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
                appendConeType(sb, ((ConeDefinitelyNotNullType) coneKotlinType).getOriginal(), function1, set);
                return;
            } else {
                if (coneKotlinType instanceof ConeFlexibleType) {
                    appendConeType(sb, ((ConeFlexibleType) coneKotlinType).getLowerBound(), function1, set);
                    return;
                }
                return;
            }
        }
        ConeTypeParameterType coneTypeParameterType = (ConeTypeParameterType) coneKotlinType;
        if (set.add(coneTypeParameterType.getLookupTag().getTypeParameterSymbol())) {
            for (FirTypeRef firTypeRef : ((FirTypeParameter) coneTypeParameterType.getLookupTag().getTypeParameterSymbol().getFir()).getBounds()) {
                ConeKotlinType coneKotlinType2 = (ConeKotlinType) function1.invoke(firTypeRef);
                Pair pair2 = coneKotlinType2 != null ? TuplesKt.to(firTypeRef, coneKotlinType2) : null;
                if (pair2 != null) {
                    pair = pair2;
                    break;
                }
            }
            if (pair != null) {
                FirTypeRef firTypeRef2 = (FirTypeRef) pair.component1();
                ConeKotlinType coneKotlinType3 = (ConeKotlinType) pair.component2();
                if (!(firTypeRef2 instanceof FirImplicitNullableAnyTypeRef) && !(firTypeRef2 instanceof FirImplicitAnyTypeRef)) {
                    appendConeType(sb, coneKotlinType3, function1, set);
                    return;
                }
            }
        }
        sb.append("Ljava/lang/Object;");
    }

    private static final void appendConeType$appendClassLikeType(StringBuilder sb, Function1<? super FirTypeRef, ? extends ConeKotlinType> function1, Set<FirTypeParameterSymbol> set, ConeClassLikeType coneClassLikeType) {
        ClassId classId = coneClassLikeType.getLookupTag().getClassId();
        ClassId classIdMapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(classId.asSingleFqName().toUnsafe());
        if (classIdMapKotlinToJava != null) {
            classId = classIdMapKotlinToJava;
        }
        if (!Intrinsics.areEqual(classId, StandardClassIds.INSTANCE.getArray())) {
            sb.append("L");
            sb.append(StringsKt.replace$default(classId.getPackageFqName().asString(), ".", "/", false, 4, (Object) null));
            sb.append("/");
            sb.append(classId.getRelativeClassName());
            sb.append(Argument.Delimiters.semicolon);
            return;
        }
        sb.append("[");
        for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneClassLikeType.getTypeArguments()) {
            if (Intrinsics.areEqual(coneKotlinTypeProjection, ConeStarProjection.INSTANCE)) {
                sb.append("*");
            } else {
                if (!(coneKotlinTypeProjection instanceof ConeKotlinTypeProjection)) {
                    bu8.a();
                    return;
                }
                appendConeType(sb, coneKotlinTypeProjection.getType(), function1, set);
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:29:0x00c0  */
    public static final String computeJvmDescriptor(FirFunction firFunction, String str, boolean z, Function1<? super FirTypeRef, ? extends ConeKotlinType> function1) throws KotlinIllegalArgumentExceptionWithAttachments {
        firFunction.getClass();
        function1.getClass();
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
        } else if (firFunction instanceof FirNamedFunction) {
            sb.append(((FirNamedFunction) firFunction).getName().asString());
        } else {
            sb.append("<init>");
        }
        sb.append("(");
        for (FirValueParameter firValueParameter : firFunction.getValueParameters()) {
            ConeKotlinType coneKotlinType = (ConeKotlinType) function1.invoke(firValueParameter.getReturnTypeRef());
            if (coneKotlinType != null) {
                try {
                    appendConeType(sb, coneKotlinType, function1, new LinkedHashSet());
                } catch (ConcurrentModificationException e) {
                    KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("CME from appendConeType", e);
                    ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                    exceptionAttachmentBuilder.withEntry("typeClass", Reflection.getOrCreateKotlinClass(coneKotlinType.getClass()).getSimpleName());
                    exceptionAttachmentBuilder.withEntry(ModuleXmlParser.TYPE, coneKotlinType, new Function1() { // from class: kbd
                        public final Object invoke(Object obj) {
                            return SignatureUtilsKt.computeJvmDescriptor$lambda$0$0$0$0((ConeKotlinType) obj);
                        }
                    });
                    FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "parameter", firValueParameter);
                    FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "function", firFunction);
                    kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                    throw kotlinIllegalArgumentExceptionWithAttachments;
                }
            }
        }
        sb.append(")");
        if (z) {
            if (firFunction instanceof FirNamedFunction) {
                FirNamedFunction firNamedFunction = (FirNamedFunction) firFunction;
                if (isVoid(firNamedFunction.getReturnTypeRef())) {
                    sb.append("V");
                } else {
                    ConeKotlinType coneKotlinType2 = (ConeKotlinType) function1.invoke(firNamedFunction.getReturnTypeRef());
                    if (coneKotlinType2 != null) {
                        appendConeType(sb, coneKotlinType2, function1, new LinkedHashSet());
                    }
                }
            } else {
                sb.append("V");
            }
        }
        return sb.toString();
    }

    public static /* synthetic */ String computeJvmDescriptor$default(FirFunction firFunction, String str, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            function1 = AnonymousClass1.INSTANCE;
        }
        return computeJvmDescriptor(firFunction, str, z, function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String computeJvmDescriptor$lambda$0$0$0$0(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        try {
            return ConeTypeUtilsKt.renderForDebugging(coneKotlinType);
        } catch (Throwable th) {
            return "Render is failed due to " + Reflection.getOrCreateKotlinClass(th.getClass());
        }
    }

    public static final String computeJvmDescriptorRepresentation(ConeKotlinType coneKotlinType, Function1<? super FirTypeRef, ? extends ConeKotlinType> function1) {
        coneKotlinType.getClass();
        function1.getClass();
        StringBuilder sb = new StringBuilder();
        appendConeType(sb, coneKotlinType, function1, new LinkedHashSet());
        return sb.toString();
    }

    public static /* synthetic */ String computeJvmDescriptorRepresentation$default(ConeKotlinType coneKotlinType, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = C00661.INSTANCE;
        }
        return computeJvmDescriptorRepresentation(coneKotlinType, function1);
    }

    public static final String computeJvmSignature(FirFunction firFunction, Function1<? super FirTypeRef, ? extends ConeKotlinType> function1) {
        firFunction.getClass();
        function1.getClass();
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firFunction);
        if (coneClassLikeLookupTagContainingClassLookupTag == null) {
            return null;
        }
        return SignatureBuildingComponents.INSTANCE.signature(coneClassLikeLookupTagContainingClassLookupTag.getClassId(), computeJvmDescriptor$default(firFunction, null, false, function1, 3, null));
    }

    public static /* synthetic */ String computeJvmSignature$default(FirFunction firFunction, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = C00671.INSTANCE;
        }
        return computeJvmSignature(firFunction, function1);
    }

    private static final boolean isVoid(FirTypeRef firTypeRef) {
        if (firTypeRef instanceof FirJavaTypeRef) {
            JavaPrimitiveType type = ((FirJavaTypeRef) firTypeRef).getType();
            return (type instanceof JavaPrimitiveType) && type.getType() == null;
        }
        if (firTypeRef instanceof FirResolvedTypeRef) {
            ConeKotlinType coneType = ((FirResolvedTypeRef) firTypeRef).getConeType();
            if ((coneType instanceof ConeClassLikeType) && Intrinsics.areEqual(((ConeClassLikeType) coneType).getLookupTag().getClassId(), unitClassId)) {
                return true;
            }
        }
        return false;
    }
}
