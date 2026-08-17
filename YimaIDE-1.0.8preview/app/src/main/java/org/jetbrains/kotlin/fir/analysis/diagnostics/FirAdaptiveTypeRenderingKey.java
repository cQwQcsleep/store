package org.jetbrains.kotlin.fir.analysis.diagnostics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticBaseContext;
import org.jetbrains.kotlin.diagnostics.rendering.RenderingContext;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirAdaptiveTypeRenderingKey;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.diagnostics.ConeCannotInferTypeParameterType;
import org.jetbrains.kotlin.fir.renderer.ConeIdRenderer;
import org.jetbrains.kotlin.fir.renderer.ConeIdShortRenderer;
import org.jetbrains.kotlin.fir.renderer.ConeTypeRendererForReadability;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeErrorLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeConstructorMarker;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J,\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J,\u0010\r\u001a\u0004\u0018\u00010\u0004*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u001c\u0010\u0014\u001a\u00020\u0015*\u00060\u0016j\u0002`\u00172\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0002J\f\u0010\u001a\u001a\u00020\u000e*\u00020\u000eH\u0002¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/FirAdaptiveTypeRenderingKey;", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Key;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", Argument.Delimiters.none, "<init>", "()V", "compute", "objectsToRender", Argument.Delimiters.none, Argument.Delimiters.none, "diagnosticContext", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "toFinalRepresentation", "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "representation", "typesWithSameRepresentation", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "appendClassLikeTemplate", Argument.Delimiters.none, "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "delegatedConstructorOrSelf", "org.jetbrains.kotlin:diagnostic-renderers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAdaptiveTypeRenderingKey extends RenderingContext.Key<Map<ConeKotlinType, ? extends String>> {
    public static final FirAdaptiveTypeRenderingKey INSTANCE = new FirAdaptiveTypeRenderingKey();

    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B#\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J0\u0010\u0011\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"org/jetbrains/kotlin/fir/analysis/diagnostics/FirAdaptiveTypeRenderingKey$appendClassLikeTemplate$ClassInfo", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "genericsStartingIndex", Argument.Delimiters.none, "typeArgumentCount", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;II)V", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getGenericsStartingIndex", "()I", "getTypeArgumentCount", "component1", "component2", "component3", "copy", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;II)Lorg/jetbrains/kotlin/fir/analysis/diagnostics/FirAdaptiveTypeRenderingKey$appendClassLikeTemplate$ClassInfo;", "equals", Argument.Delimiters.none, "other", "hashCode", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:diagnostic-renderers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ClassInfo {
        private final int genericsStartingIndex;
        private final FirClassLikeSymbol<?> symbol;
        private final int typeArgumentCount;

        public ClassInfo(FirClassLikeSymbol<?> firClassLikeSymbol, int i, int i2) {
            firClassLikeSymbol.getClass();
            this.symbol = firClassLikeSymbol;
            this.genericsStartingIndex = i;
            this.typeArgumentCount = i2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ClassInfo copy$default(ClassInfo classInfo, FirClassLikeSymbol firClassLikeSymbol, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                firClassLikeSymbol = classInfo.symbol;
            }
            if ((i3 & 2) != 0) {
                i = classInfo.genericsStartingIndex;
            }
            if ((i3 & 4) != 0) {
                i2 = classInfo.typeArgumentCount;
            }
            return classInfo.copy(firClassLikeSymbol, i, i2);
        }

        public final FirClassLikeSymbol<?> component1() {
            return this.symbol;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getGenericsStartingIndex() {
            return this.genericsStartingIndex;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getTypeArgumentCount() {
            return this.typeArgumentCount;
        }

        public final ClassInfo copy(FirClassLikeSymbol<?> symbol, int genericsStartingIndex, int typeArgumentCount) {
            symbol.getClass();
            return new ClassInfo(symbol, genericsStartingIndex, typeArgumentCount);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ClassInfo)) {
                return false;
            }
            ClassInfo classInfo = (ClassInfo) other;
            return Intrinsics.areEqual(this.symbol, classInfo.symbol) && this.genericsStartingIndex == classInfo.genericsStartingIndex && this.typeArgumentCount == classInfo.typeArgumentCount;
        }

        public final int getGenericsStartingIndex() {
            return this.genericsStartingIndex;
        }

        public final FirClassLikeSymbol<?> getSymbol() {
            return this.symbol;
        }

        public final int getTypeArgumentCount() {
            return this.typeArgumentCount;
        }

        public int hashCode() {
            return (((this.symbol.hashCode() * 31) + Integer.hashCode(this.genericsStartingIndex)) * 31) + Integer.hashCode(this.typeArgumentCount);
        }

        public String toString() {
            return "ClassInfo(symbol=" + this.symbol + ", genericsStartingIndex=" + this.genericsStartingIndex + ", typeArgumentCount=" + this.typeArgumentCount + ')';
        }
    }

    private FirAdaptiveTypeRenderingKey() {
        super("ADAPTIVE_RENDERED_TYPES");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void appendClassLikeTemplate(StringBuilder sb, FirClassLikeSymbol<?> firClassLikeSymbol) {
        ArrayList arrayList = new ArrayList();
        boolean zIsInner = true;
        int i = 0;
        while (firClassLikeSymbol != null) {
            FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firClassLikeSymbol);
            int size = zIsInner ? (containingClassSymbol == null && ((FirClassLikeDeclaration) firClassLikeSymbol.getFir()).getIsLocal()) ? firClassLikeSymbol.getTypeParameterSymbols().size() : firClassLikeSymbol.getOwnTypeParameterSymbols().size() : 0;
            arrayList.add(new ClassInfo(firClassLikeSymbol, i, size));
            i += size;
            zIsInner = firClassLikeSymbol.getRawStatus().isInner();
            firClassLikeSymbol = containingClassSymbol;
        }
        for (ClassInfo classInfo : CollectionsKt.asReversedMutable(arrayList)) {
            FirClassLikeSymbol<?> firClassLikeSymbolComponent1 = classInfo.component1();
            int genericsStartingIndex = classInfo.getGenericsStartingIndex();
            int typeArgumentCount = classInfo.getTypeArgumentCount();
            sb.append(firClassLikeSymbolComponent1.getClassId().getShortClassName());
            if (typeArgumentCount != 0) {
                sb.append("<");
                for (int i2 = 0; i2 < typeArgumentCount; i2++) {
                    if (i2 != 0) {
                        sb.append(", ");
                    }
                    sb.append("{" + (i2 + genericsStartingIndex) + '}');
                }
                sb.append(">");
            }
            if (!Intrinsics.areEqual(firClassLikeSymbolComponent1, ((ClassInfo) CollectionsKt.first(arrayList)).getSymbol())) {
                sb.append(".");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConeIdRenderer compute$lambda$2$0$0() {
        return new ConeIdShortRenderer();
    }

    private final TypeConstructorMarker delegatedConstructorOrSelf(TypeConstructorMarker typeConstructorMarker) {
        ConeRigidType coneRigidTypeLowerBoundIfFlexible;
        ConeTypeConstructorMarker constructor;
        if (!(typeConstructorMarker instanceof ConeClassLikeErrorLookupTag)) {
            return typeConstructorMarker;
        }
        ConeClassLikeErrorLookupTag coneClassLikeErrorLookupTag = (ConeClassLikeErrorLookupTag) typeConstructorMarker;
        if (!(coneClassLikeErrorLookupTag.getDiagnostic() instanceof ConeCannotInferTypeParameterType)) {
            return typeConstructorMarker;
        }
        ConeKotlinType delegatedType = coneClassLikeErrorLookupTag.getDelegatedType();
        return (delegatedType == null || (coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(delegatedType)) == null || (constructor = ConeTypeUtilsKt.getConstructor(coneRigidTypeLowerBoundIfFlexible)) == null) ? (ConeTypeConstructorMarker) typeConstructorMarker : constructor;
    }

    private final String toFinalRepresentation(TypeConstructorMarker typeConstructorMarker, String str, List<? extends TypeConstructorMarker> list, FirSession firSession) {
        FirTypeParameterSymbol typeParameterSymbol;
        ConeKotlinType delegatedType;
        ConeRigidType coneRigidTypeLowerBoundIfFlexible;
        FirClassLikeSymbol<?> symbol;
        List<FirTypeParameterSymbol> typeParameterSymbols;
        int size = 0;
        boolean z = list.size() > 1;
        boolean z2 = typeConstructorMarker instanceof ConeClassLikeErrorLookupTag;
        boolean z3 = (typeConstructorMarker instanceof ConeClassLikeLookupTag) && !z2;
        StringBuilder sb = new StringBuilder();
        if (z2 && (((ConeClassLikeErrorLookupTag) typeConstructorMarker).getDiagnostic() instanceof ConeCannotInferTypeParameterType)) {
            sb.append("uninferred ");
        }
        FirTypeParameterSymbol typeParameterSymbol2 = null;
        if (z3) {
            if (z) {
                ConeClassLikeLookupTag coneClassLikeLookupTag = (ConeClassLikeLookupTag) typeConstructorMarker;
                sb.append(coneClassLikeLookupTag.getClassId().getPackageFqName().isRoot() ? "<root>" : coneClassLikeLookupTag.getClassId().getPackageFqName().asString());
                sb.append(".");
            }
            FirClassLikeSymbol<?> symbol2 = ToSymbolUtilsKt.toSymbol((ConeClassLikeLookupTag) typeConstructorMarker, firSession);
            if (symbol2 == null) {
                return null;
            }
            INSTANCE.appendClassLikeTemplate(sb, symbol2);
        } else {
            sb.append(str);
        }
        if (!z3 && !z2 && z) {
            sb.append('#');
            sb.append(list.indexOf(typeConstructorMarker) + 1);
        }
        if (z3 && (symbol = ToSymbolUtilsKt.toSymbol((ConeClassLikeLookupTag) typeConstructorMarker, firSession)) != null && (typeParameterSymbols = symbol.getTypeParameterSymbols()) != null) {
            size = typeParameterSymbols.size();
        }
        sb.append("{" + size + '}');
        ConeClassLikeErrorLookupTag coneClassLikeErrorLookupTag = z2 ? (ConeClassLikeErrorLookupTag) typeConstructorMarker : null;
        ConeTypeConstructorMarker constructor = (coneClassLikeErrorLookupTag == null || (delegatedType = coneClassLikeErrorLookupTag.getDelegatedType()) == null || (coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(delegatedType)) == null) ? null : ConeTypeUtilsKt.getConstructor(coneRigidTypeLowerBoundIfFlexible);
        ConeTypeParameterLookupTag coneTypeParameterLookupTag = constructor instanceof ConeTypeParameterLookupTag ? (ConeTypeParameterLookupTag) constructor : null;
        if (coneTypeParameterLookupTag == null || (typeParameterSymbol = coneTypeParameterLookupTag.getTypeParameterSymbol()) == null) {
            ConeTypeParameterLookupTag coneTypeParameterLookupTag2 = typeConstructorMarker instanceof ConeTypeParameterLookupTag ? (ConeTypeParameterLookupTag) typeConstructorMarker : null;
            if (coneTypeParameterLookupTag2 != null) {
                typeParameterSymbol2 = coneTypeParameterLookupTag2.getTypeParameterSymbol();
            }
        } else {
            typeParameterSymbol2 = typeParameterSymbol;
        }
        if (typeParameterSymbol2 != null) {
            sb.append(" (of ");
            sb.append(FirDiagnosticRenderers.INSTANCE.getTYPE_PARAMETER_OWNER_SYMBOL().render(typeParameterSymbol2.getContainingDeclarationSymbol()));
            sb.append(')');
        }
        return sb.toString();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.diagnostics.rendering.RenderingContext.Key
    /* JADX INFO: renamed from: compute, reason: avoid collision after fix types in other method */
    public Map<ConeKotlinType, ? extends String> compute2(Collection<? extends Object> objectsToRender, DiagnosticBaseContext diagnosticContext) throws UninitializedPropertyAccessException {
        objectsToRender.getClass();
        diagnosticContext.getClass();
        Collection<? extends Object> collection = objectsToRender;
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (obj instanceof ConeKotlinType) {
                arrayList.add(obj);
            }
        }
        ArrayList<Iterable> arrayList2 = new ArrayList();
        for (Object obj2 : collection) {
            if (obj2 instanceof Iterable) {
                arrayList2.add(obj2);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Iterable iterable : arrayList2) {
            ArrayList arrayList4 = new ArrayList();
            for (Object obj3 : iterable) {
                if (obj3 instanceof ConeKotlinType) {
                    arrayList4.add(obj3);
                }
            }
            CollectionsKt.addAll(arrayList3, arrayList4);
        }
        List listPlus = CollectionsKt.plus(arrayList, arrayList3);
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        List list = listPlus;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            List listMutableListOf = CollectionsKt.mutableListOf(new ConeKotlinType[]{(ConeKotlinType) it.next()});
            while (!listMutableListOf.isEmpty()) {
                ConeKotlinType coneKotlinType = (ConeKotlinType) AddToStdlibKt.popLast(listMutableListOf);
                ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
                if (!(coneRigidTypeLowerBoundIfFlexible instanceof ConeIntersectionType)) {
                    setCreateSetBuilder.add(ConeTypeUtilsKt.getConstructor(coneRigidTypeLowerBoundIfFlexible));
                }
                if (coneKotlinType instanceof ConeFlexibleType) {
                    ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
                    listMutableListOf.add(coneFlexibleType.getLowerBound());
                    if (!coneFlexibleType.getIsTrivial()) {
                        listMutableListOf.add(coneFlexibleType.getUpperBound());
                    }
                } else if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
                    listMutableListOf.add(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
                } else if (coneKotlinType instanceof ConeIntersectionType) {
                    listMutableListOf.addAll(((ConeIntersectionType) coneKotlinType).getIntersectedTypes());
                } else {
                    for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType.getTypeArguments()) {
                        if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                            listMutableListOf.add(coneKotlinTypeProjection.getType());
                        }
                    }
                }
            }
        }
        Set setBuild = SetsKt.build(setCreateSetBuilder);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(setBuild, 10)), 16));
        for (Object obj4 : setBuild) {
            StringBuilder sb = new StringBuilder();
            new ConeTypeRendererForReadability(sb, null, new Function0() { // from class: ey4
                public final Object invoke() {
                    return FirAdaptiveTypeRenderingKey.compute$lambda$2$0$0();
                }
            }, 2, null).renderConstructor(INSTANCE.delegatedConstructorOrSelf((TypeConstructorMarker) obj4));
            linkedHashMap.put(obj4, sb.toString());
        }
        Set<Map.Entry> setEntrySet = linkedHashMap.entrySet();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry entry : setEntrySet) {
            String str = (String) entry.getValue();
            Object arrayList5 = linkedHashMap2.get(str);
            if (arrayList5 == null) {
                arrayList5 = new ArrayList();
                linkedHashMap2.put(str, arrayList5);
            }
            ((List) arrayList5).add((TypeConstructorMarker) entry.getKey());
        }
        FirSession session = ((SessionHolder) diagnosticContext).getSession();
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(setBuild, 10)), 16));
        for (Object obj5 : setBuild) {
            TypeConstructorMarker typeConstructorMarker = (TypeConstructorMarker) obj5;
            linkedHashMap3.put(obj5, INSTANCE.toFinalRepresentation(typeConstructorMarker, (String) MapsKt.getValue(linkedHashMap, typeConstructorMarker), (List) MapsKt.getValue(linkedHashMap2, MapsKt.getValue(linkedHashMap, typeConstructorMarker)), session));
        }
        LinkedHashMap linkedHashMap4 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (Object obj6 : list) {
            linkedHashMap4.put(obj6, ConeTypeUtilsKt.renderReadableWithFqNames((ConeKotlinType) obj6, linkedHashMap3));
        }
        return linkedHashMap4;
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.RenderingContext.Key
    public /* bridge */ /* synthetic */ Map<ConeKotlinType, ? extends String> compute(Collection collection, DiagnosticBaseContext diagnosticBaseContext) {
        return compute2((Collection<? extends Object>) collection, diagnosticBaseContext);
    }
}
