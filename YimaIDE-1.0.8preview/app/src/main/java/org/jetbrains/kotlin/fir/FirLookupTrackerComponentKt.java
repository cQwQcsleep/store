package org.jetbrains.kotlin.fir;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCallInfo;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;
import org.jetbrains.kotlin.utils.SmartList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u001a&\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u001a&\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u001a.\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u001a&\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u001a4\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u001a.\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u001a&\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u001a*\u0010\u001d\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001e2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u001a(\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\b\u0010\u001f\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u001a*\u0010 \u001a\u00020\u0001*\u00020\u00022\n\u0010!\u001a\u0006\u0012\u0002\b\u00030\"2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\"!\u0010#\u001a\u0004\u0018\u00010\u0002*\u00020$8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b%\u0010&¨\u0006)"}, d2 = {"recordCallLookup", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirLookupTrackerComponent;", "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCallInfo;", "inType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "inScopes", Argument.Delimiters.none, Argument.Delimiters.none, "recordClassLikeLookup", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "fileSource", "recordCompanionLookup", "recordClassMemberLookup", "memberName", "recordFqNameLookup", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "recordNameLookup", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "inScope", "recordTypeResolveAsLookup", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "recordUserTypeRefLookup", "Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;", ModuleXmlParser.TYPE, "recordCallableCandidateAsLookup", "callableSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "lookupTracker", "Lorg/jetbrains/kotlin/fir/FirSession;", "getLookupTracker", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirLookupTrackerComponent;", "lookupTracker$delegate", "Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLookupTrackerComponentKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirLookupTrackerComponentKt.class, "lookupTracker", "getLookupTracker(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirLookupTrackerComponent;", 1)};
    private static final NullableArrayMapAccessor lookupTracker$delegate = FirSession.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(FirLookupTrackerComponent.class));

    public static final FirLookupTrackerComponent getLookupTracker(FirSession firSession) {
        firSession.getClass();
        return (FirLookupTrackerComponent) lookupTracker$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    public static final void recordCallLookup(FirLookupTrackerComponent firLookupTrackerComponent, AbstractCallInfo abstractCallInfo, ConeKotlinType coneKotlinType) {
        ClassId outerClassId;
        String strAsFqNameString;
        firLookupTrackerComponent.getClass();
        abstractCallInfo.getClass();
        coneKotlinType.getClass();
        ClassId classId = ConeTypeUtilsKt.getClassId(coneKotlinType);
        if (classId == null || classId.isLocal()) {
            return;
        }
        SmartList smartList = new SmartList(classId.asFqNameString());
        if (Intrinsics.areEqual(classId.getShortClassName(), SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT) && (outerClassId = classId.getOuterClassId()) != null && (strAsFqNameString = outerClassId.asFqNameString()) != null) {
            smartList.add(strAsFqNameString);
        }
        recordNameLookup(firLookupTrackerComponent, abstractCallInfo.getName(), (Iterable<String>) smartList, abstractCallInfo.getCallSite().getSource(), abstractCallInfo.getContainingFile().getSource());
    }

    public static final void recordCallableCandidateAsLookup(FirLookupTrackerComponent firLookupTrackerComponent, FirCallableSymbol<?> firCallableSymbol, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2) {
        firLookupTrackerComponent.getClass();
        firCallableSymbol.getClass();
        CallableId callableId = firCallableSymbol.getCallableId();
        if (callableId == null || callableId.isLocal() || (firCallableSymbol instanceof FirConstructorSymbol)) {
            return;
        }
        recordTypeResolveAsLookup(firLookupTrackerComponent, ((FirCallableDeclaration) firCallableSymbol.getFir()).getReturnTypeRef(), ktSourceElement, ktSourceElement2);
        recordFqNameLookup(firLookupTrackerComponent, callableId.asSingleFqName(), ktSourceElement, ktSourceElement2);
    }

    public static final void recordClassLikeLookup(FirLookupTrackerComponent firLookupTrackerComponent, ClassId classId, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2) {
        firLookupTrackerComponent.getClass();
        classId.getClass();
        if (classId.isLocal() || StandardClassIds.INSTANCE.getAllBuiltinTypes().contains(classId)) {
            return;
        }
        FqName fqNameAsSingleFqName = classId.asSingleFqName();
        String strAsString = fqNameAsSingleFqName.shortName().asString();
        strAsString.getClass();
        firLookupTrackerComponent.recordLookup(strAsString, fqNameAsSingleFqName.parent().asString(), ktSourceElement, ktSourceElement2);
    }

    public static final void recordClassMemberLookup(FirLookupTrackerComponent firLookupTrackerComponent, String str, ClassId classId, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2) {
        firLookupTrackerComponent.getClass();
        str.getClass();
        classId.getClass();
        firLookupTrackerComponent.recordLookup(str, classId.asFqNameString(), ktSourceElement, ktSourceElement2);
    }

    public static final void recordCompanionLookup(FirLookupTrackerComponent firLookupTrackerComponent, ClassId classId, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2) {
        firLookupTrackerComponent.getClass();
        classId.getClass();
        if (classId.isLocal() || StandardClassIds.INSTANCE.getAllBuiltinTypes().contains(classId)) {
            return;
        }
        FqName fqNameAsSingleFqName = classId.asSingleFqName();
        String strAsString = fqNameAsSingleFqName.shortName().asString();
        strAsString.getClass();
        firLookupTrackerComponent.recordLookup(strAsString, fqNameAsSingleFqName.parent().asString(), ktSourceElement, ktSourceElement2);
        String strAsString2 = fqNameAsSingleFqName.parent().shortName().asString();
        strAsString2.getClass();
        firLookupTrackerComponent.recordLookup(strAsString2, fqNameAsSingleFqName.parent().parent().asString(), ktSourceElement, ktSourceElement2);
    }

    public static final void recordFqNameLookup(FirLookupTrackerComponent firLookupTrackerComponent, FqName fqName, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2) {
        firLookupTrackerComponent.getClass();
        fqName.getClass();
        String strAsString = fqName.shortName().asString();
        strAsString.getClass();
        firLookupTrackerComponent.recordLookup(strAsString, fqName.parent().asString(), ktSourceElement, ktSourceElement2);
    }

    public static final void recordNameLookup(FirLookupTrackerComponent firLookupTrackerComponent, Name name, Iterable<String> iterable, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2) {
        firLookupTrackerComponent.getClass();
        name.getClass();
        iterable.getClass();
        String strAsString = name.asString();
        strAsString.getClass();
        firLookupTrackerComponent.recordLookup(strAsString, iterable, ktSourceElement, ktSourceElement2);
    }

    public static final void recordTypeResolveAsLookup(FirLookupTrackerComponent firLookupTrackerComponent, ConeKotlinType coneKotlinType, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2) {
        firLookupTrackerComponent.getClass();
        if (coneKotlinType == null) {
            return;
        }
        if ((ktSourceElement == null && ktSourceElement2 == null) || (coneKotlinType instanceof ConeErrorType)) {
            return;
        }
        ClassId classId = ConeTypeUtilsKt.getClassId(coneKotlinType);
        if (classId != null) {
            recordClassLikeLookup(firLookupTrackerComponent, classId, ktSourceElement, ktSourceElement2);
        }
        for (ConeKotlinType coneKotlinType2 : coneKotlinType.getTypeArguments()) {
            if (coneKotlinType2 instanceof ConeKotlinType) {
                recordTypeResolveAsLookup(firLookupTrackerComponent, coneKotlinType2, ktSourceElement, ktSourceElement2);
            }
        }
    }

    public static final void recordUserTypeRefLookup(FirLookupTrackerComponent firLookupTrackerComponent, FirUserTypeRef firUserTypeRef, Iterable<String> iterable, KtSourceElement ktSourceElement) {
        firLookupTrackerComponent.getClass();
        firUserTypeRef.getClass();
        iterable.getClass();
        for (String str : iterable) {
            List<FirQualifierPart> qualifier = firUserTypeRef.getQualifier();
            FqName fqName = new FqName(str);
            for (FirQualifierPart firQualifierPart : qualifier) {
                String strAsString = firQualifierPart.getName().asString();
                strAsString.getClass();
                firLookupTrackerComponent.recordLookup(strAsString, fqName.asString(), firUserTypeRef.getSource(), ktSourceElement);
                fqName = fqName.child(firQualifierPart.getName());
            }
        }
    }

    public static final void recordNameLookup(FirLookupTrackerComponent firLookupTrackerComponent, Name name, String str, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2) {
        firLookupTrackerComponent.getClass();
        name.getClass();
        str.getClass();
        String strAsString = name.asString();
        strAsString.getClass();
        firLookupTrackerComponent.recordLookup(strAsString, str, ktSourceElement, ktSourceElement2);
    }

    public static final void recordTypeResolveAsLookup(FirLookupTrackerComponent firLookupTrackerComponent, FirTypeRef firTypeRef, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2) {
        firLookupTrackerComponent.getClass();
        firTypeRef.getClass();
        if (firTypeRef instanceof FirResolvedTypeRef) {
            recordTypeResolveAsLookup(firLookupTrackerComponent, ((FirResolvedTypeRef) firTypeRef).getConeType(), ktSourceElement, ktSourceElement2);
        }
    }

    public static final void recordCallLookup(FirLookupTrackerComponent firLookupTrackerComponent, AbstractCallInfo abstractCallInfo, Iterable<String> iterable) {
        firLookupTrackerComponent.getClass();
        abstractCallInfo.getClass();
        iterable.getClass();
        recordNameLookup(firLookupTrackerComponent, abstractCallInfo.getName(), iterable, abstractCallInfo.getCallSite().getSource(), abstractCallInfo.getContainingFile().getSource());
    }
}
