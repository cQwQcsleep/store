package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirDeclarationNameInvalidCharsProviderKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0010\f\n\u0000\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005\u001a'\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u000b\u001a\u001a\u0010\f\u001a\u00020\r*\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002¨\u0006\u0011"}, d2 = {"setUnnamedContextParameterNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionHolder;", "sessionHolder", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "erasedUpperBoundName", "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "tryApproximation", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Z)Lorg/jetbrains/kotlin/name/Name;", "replaceInvalidChars", Argument.Delimiters.none, "invalidChars", Argument.Delimiters.none, Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SetUnnamedContextParameterNamesKt {
    private static final Name erasedUpperBoundName(SessionHolder sessionHolder, ConeKotlinType coneKotlinType, boolean z) {
        ConeKotlinType coneKotlinTypeApproximateToSuperType;
        if (coneKotlinType instanceof ConeTypeParameterType) {
            List<FirResolvedTypeRef> resolvedBounds = ((ConeTypeParameterType) coneKotlinType).getLookupTag().getSymbol().getResolvedBounds();
            Iterator<FirResolvedTypeRef> it = resolvedBounds.iterator();
            while (it.hasNext()) {
                FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol(sessionHolder, TypeExpansionUtilsKt.fullyExpandedType(sessionHolder, it.next().getConeType()));
                if (classSymbol != null && classSymbol.getClassKind() != ClassKind.ANNOTATION_CLASS && classSymbol.getClassKind() != ClassKind.INTERFACE) {
                    return classSymbol.getName();
                }
            }
            return erasedUpperBoundName(sessionHolder, ((FirResolvedTypeRef) CollectionsKt.first(resolvedBounds)).getConeType(), z);
        }
        if (coneKotlinType instanceof ConeLookupTagBasedType) {
            ConeClassifierLookupTag lookupTagIfAny = ConeTypeUtilsKt.getLookupTagIfAny(TypeExpansionUtilsKt.fullyExpandedType(sessionHolder, (ConeSimpleKotlinType) coneKotlinType));
            if (lookupTagIfAny != null) {
                return lookupTagIfAny.getName();
            }
            return null;
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return erasedUpperBoundName(sessionHolder, ((ConeDefinitelyNotNullType) coneKotlinType).getOriginal(), z);
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            return erasedUpperBoundName(sessionHolder, ((ConeFlexibleType) coneKotlinType).getUpperBound(), z);
        }
        if (!z || (coneKotlinTypeApproximateToSuperType = TypeComponentsKt.getTypeApproximator(sessionHolder.getSession()).approximateToSuperType(coneKotlinType, TypeApproximatorConfiguration.FrontendToBackendTypesApproximation.INSTANCE)) == null) {
            return null;
        }
        return erasedUpperBoundName(sessionHolder, coneKotlinTypeApproximateToSuperType, false);
    }

    private static final String replaceInvalidChars(String str, Set<Character> set) {
        Iterator<T> it = set.iterator();
        while (true) {
            String str2 = str;
            while (it.hasNext()) {
                char cCharValue = ((Character) it.next()).charValue();
                if (StringsKt.contains$default(str2, cCharValue, false, 2, (Object) null)) {
                    str = StringsKt.replace$default(str2, cCharValue, '_', false, 4, (Object) null);
                }
            }
            return str2;
        }
    }

    public static final void setUnnamedContextParameterNames(SessionHolder sessionHolder, FirCallableDeclaration firCallableDeclaration) {
        String string;
        String strReplaceInvalidChars;
        sessionHolder.getClass();
        firCallableDeclaration.getClass();
        if (firCallableDeclaration.getContextParameters().isEmpty()) {
            return;
        }
        List<FirValueParameter> contextParameters = firCallableDeclaration.getContextParameters();
        ArrayList arrayList = new ArrayList();
        for (Object obj : contextParameters) {
            FirValueParameter firValueParameter = (FirValueParameter) obj;
            if (firValueParameter.getName().isSpecial() && !ConeTypeUtilsKt.hasError(FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()))) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList, 10)), 16));
        for (Object obj2 : arrayList) {
            FirValueParameter firValueParameter2 = (FirValueParameter) obj2;
            Name nameErasedUpperBoundName = erasedUpperBoundName(sessionHolder, FirTypeUtilsKt.getConeType(firValueParameter2.getReturnTypeRef()), true);
            if (nameErasedUpperBoundName == null || (string = nameErasedUpperBoundName.toString()) == null || (strReplaceInvalidChars = replaceInvalidChars(string, FirDeclarationNameInvalidCharsProviderKt.getDeclarationNameInvalidChars(sessionHolder.getSession()))) == null) {
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Cannot compute generated name for context parameter", (Throwable) null);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "contextParameter", firValueParameter2);
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "containingDeclaration", firCallableDeclaration);
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
            linkedHashMap.put(obj2, strReplaceInvalidChars);
        }
        Set<Map.Entry> setEntrySet = linkedHashMap.entrySet();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry entry : setEntrySet) {
            String str = (String) entry.getValue();
            Object arrayList2 = linkedHashMap2.get(str);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                linkedHashMap2.put(str, arrayList2);
            }
            ((List) arrayList2).add((FirValueParameter) entry.getKey());
        }
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            FirValueParameter firValueParameter3 = (FirValueParameter) entry2.getKey();
            String str2 = (String) entry2.getValue();
            Object obj3 = linkedHashMap2.get(str2);
            obj3.getClass();
            List list = (List) obj3;
            ClassMembersKt.setGeneratedContextParameterName(firValueParameter3, Name.identifier("$context-" + str2 + (list.size() == 1 ? Argument.Delimiters.none : "#" + (list.indexOf(firValueParameter3) + 1))));
        }
    }
}
