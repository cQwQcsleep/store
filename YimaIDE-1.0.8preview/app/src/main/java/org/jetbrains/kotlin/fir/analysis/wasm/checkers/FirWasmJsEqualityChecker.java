package org.jetbrains.kotlin.fir.analysis.wasm.checkers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformSpecificEqualityChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.FirTypeCompatibilityHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirEqualityCompatibilityChecker;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.JsStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\f\u0010\r\u001a\u00020\b*\u00020\u0005H\u0002J%\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00052\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\u0011H\u0082\bJ9\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u00020\u0013j\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0002\u0010\u001bJ9\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u00020\u0013j\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0002\u0010\u001bR\u0018\u0010\t\u001a\u00020\b*\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/wasm/checkers/FirWasmJsEqualityChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformSpecificEqualityChecker;", "<init>", "()V", "tryUnwrapJsReferenceType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", ModuleXmlParser.TYPE, "isJsAnyOrJsReference", Argument.Delimiters.none, "hasJsAnyOrJsReference", "Lorg/jetbrains/kotlin/fir/types/ConeIntersectionType;", "getHasJsAnyOrJsReference", "(Lorg/jetbrains/kotlin/fir/types/ConeIntersectionType;)Z", "isJsAny", "minApplicabilityAmongJsTypesComponents", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityCompatibilityChecker$Applicability;", "predicate", "Lkotlin/Function1;", "runApplicabilityCheck", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "equalityOperation", "Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "leftType", "rightType", "checker", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityCompatibilityChecker;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirOperation;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityCompatibilityChecker;)Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityCompatibilityChecker$Applicability;", "runCheckForIntersectionComponents", "operation", "org.jetbrains.kotlin:checkers.wasm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWasmJsEqualityChecker extends FirPlatformSpecificEqualityChecker {
    public static final FirWasmJsEqualityChecker INSTANCE = new FirWasmJsEqualityChecker();

    private FirWasmJsEqualityChecker() {
    }

    private final boolean getHasJsAnyOrJsReference(ConeIntersectionType coneIntersectionType) {
        Collection<ConeKotlinType> intersectedTypes = coneIntersectionType.getIntersectedTypes();
        if ((intersectedTypes instanceof Collection) && intersectedTypes.isEmpty()) {
            return false;
        }
        Iterator<T> it = intersectedTypes.iterator();
        while (it.hasNext()) {
            if (INSTANCE.isJsAnyOrJsReference((ConeKotlinType) it.next())) {
                return true;
            }
        }
        return false;
    }

    private final boolean isJsAny(ConeKotlinType coneKotlinType) {
        return Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneKotlinType), JsStandardClassIds.JsAny);
    }

    private final boolean isJsAnyOrJsReference(ConeKotlinType type) {
        ClassId classId = ConeTypeUtilsKt.getClassId(type);
        if (classId == null) {
            return false;
        }
        return Intrinsics.areEqual(classId, JsStandardClassIds.JsAny) || Intrinsics.areEqual(classId, JsStandardClassIds.JsReference);
    }

    private final FirEqualityCompatibilityChecker.Applicability runCheckForIntersectionComponents(CheckerContext checkerContext, FirOperation firOperation, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirEqualityCompatibilityChecker firEqualityCompatibilityChecker) {
        ConeKotlinType coneKotlinTypeTryUnwrapJsReferenceType;
        if (isJsAny(coneKotlinType) || isJsAny(coneKotlinType2)) {
            return FirEqualityCompatibilityChecker.Applicability.APPLICABLE;
        }
        ConeKotlinType coneKotlinTypeTryUnwrapJsReferenceType2 = tryUnwrapJsReferenceType(coneKotlinType);
        if (coneKotlinTypeTryUnwrapJsReferenceType2 != null && (coneKotlinTypeTryUnwrapJsReferenceType = tryUnwrapJsReferenceType(coneKotlinType2)) != null) {
            return firEqualityCompatibilityChecker.checkApplicability(checkerContext, firOperation, FirTypeCompatibilityHelpersKt.toTypeInfo(coneKotlinTypeTryUnwrapJsReferenceType2, checkerContext.getSession()), FirTypeCompatibilityHelpersKt.toTypeInfo(coneKotlinTypeTryUnwrapJsReferenceType, checkerContext.getSession()));
        }
        return FirEqualityCompatibilityChecker.Applicability.APPLICABLE;
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00da  */
    /* JADX WARN: Code duplicated, block: B:63:0x0178  */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformSpecificEqualityChecker
    public FirEqualityCompatibilityChecker.Applicability runApplicabilityCheck(CheckerContext checkerContext, FirOperation firOperation, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirEqualityCompatibilityChecker firEqualityCompatibilityChecker) {
        FirEqualityCompatibilityChecker.Applicability applicabilityRunCheckForIntersectionComponents;
        FirEqualityCompatibilityChecker.Applicability applicability;
        FirEqualityCompatibilityChecker.Applicability applicabilityRunCheckForIntersectionComponents2;
        checkerContext.getClass();
        firOperation.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        firEqualityCompatibilityChecker.getClass();
        FirEqualityCompatibilityChecker.Applicability applicability2 = null;
        if (coneKotlinType instanceof ConeIntersectionType) {
            ConeIntersectionType coneIntersectionType = (ConeIntersectionType) coneKotlinType;
            if (getHasJsAnyOrJsReference(coneIntersectionType)) {
                Collection<ConeKotlinType> intersectedTypes = coneIntersectionType.getIntersectedTypes();
                ArrayList arrayList = new ArrayList();
                for (Object obj : intersectedTypes) {
                    if (INSTANCE.isJsAnyOrJsReference((ConeKotlinType) obj)) {
                        arrayList.add(obj);
                    }
                }
                Iterator it = arrayList.iterator();
                if (it.hasNext()) {
                    ConeKotlinType coneKotlinType3 = (ConeKotlinType) it.next();
                    FirWasmJsEqualityChecker firWasmJsEqualityChecker = INSTANCE;
                    boolean z = coneKotlinType2 instanceof ConeIntersectionType;
                    if (z) {
                        ConeIntersectionType coneIntersectionType2 = (ConeIntersectionType) coneKotlinType2;
                        if (firWasmJsEqualityChecker.getHasJsAnyOrJsReference(coneIntersectionType2)) {
                            Collection<ConeKotlinType> intersectedTypes2 = coneIntersectionType2.getIntersectedTypes();
                            ArrayList arrayList2 = new ArrayList();
                            for (Object obj2 : intersectedTypes2) {
                                if (INSTANCE.isJsAnyOrJsReference((ConeKotlinType) obj2)) {
                                    arrayList2.add(obj2);
                                }
                            }
                            Iterator it2 = arrayList2.iterator();
                            if (it2.hasNext()) {
                                applicabilityRunCheckForIntersectionComponents = INSTANCE.runCheckForIntersectionComponents(checkerContext, firOperation, coneKotlinType3, (ConeKotlinType) it2.next(), firEqualityCompatibilityChecker);
                                while (it2.hasNext()) {
                                    FirEqualityCompatibilityChecker.Applicability applicabilityRunCheckForIntersectionComponents3 = INSTANCE.runCheckForIntersectionComponents(checkerContext, firOperation, coneKotlinType3, (ConeKotlinType) it2.next(), firEqualityCompatibilityChecker);
                                    if (applicabilityRunCheckForIntersectionComponents.compareTo(applicabilityRunCheckForIntersectionComponents3) > 0) {
                                        applicabilityRunCheckForIntersectionComponents = applicabilityRunCheckForIntersectionComponents3;
                                    }
                                }
                            } else {
                                applicabilityRunCheckForIntersectionComponents = null;
                            }
                            if (applicabilityRunCheckForIntersectionComponents == null) {
                                applicabilityRunCheckForIntersectionComponents = FirEqualityCompatibilityChecker.Applicability.APPLICABLE;
                            }
                        } else {
                            applicabilityRunCheckForIntersectionComponents = firWasmJsEqualityChecker.runCheckForIntersectionComponents(checkerContext, firOperation, coneKotlinType3, coneKotlinType2, firEqualityCompatibilityChecker);
                        }
                    } else {
                        applicabilityRunCheckForIntersectionComponents = firWasmJsEqualityChecker.runCheckForIntersectionComponents(checkerContext, firOperation, coneKotlinType3, coneKotlinType2, firEqualityCompatibilityChecker);
                    }
                    loop2: while (true) {
                        applicability = applicabilityRunCheckForIntersectionComponents;
                        do {
                            if (!it.hasNext()) {
                                break loop2;
                            }
                            ConeKotlinType coneKotlinType4 = (ConeKotlinType) it.next();
                            FirWasmJsEqualityChecker firWasmJsEqualityChecker2 = INSTANCE;
                            if (z) {
                                ConeIntersectionType coneIntersectionType3 = (ConeIntersectionType) coneKotlinType2;
                                if (firWasmJsEqualityChecker2.getHasJsAnyOrJsReference(coneIntersectionType3)) {
                                    Collection<ConeKotlinType> intersectedTypes3 = coneIntersectionType3.getIntersectedTypes();
                                    ArrayList arrayList3 = new ArrayList();
                                    for (Object obj3 : intersectedTypes3) {
                                        if (INSTANCE.isJsAnyOrJsReference((ConeKotlinType) obj3)) {
                                            arrayList3.add(obj3);
                                        }
                                    }
                                    Iterator it3 = arrayList3.iterator();
                                    if (it3.hasNext()) {
                                        applicabilityRunCheckForIntersectionComponents = INSTANCE.runCheckForIntersectionComponents(checkerContext, firOperation, coneKotlinType4, (ConeKotlinType) it3.next(), firEqualityCompatibilityChecker);
                                        while (it3.hasNext()) {
                                            FirEqualityCompatibilityChecker.Applicability applicabilityRunCheckForIntersectionComponents4 = INSTANCE.runCheckForIntersectionComponents(checkerContext, firOperation, coneKotlinType4, (ConeKotlinType) it3.next(), firEqualityCompatibilityChecker);
                                            if (applicabilityRunCheckForIntersectionComponents.compareTo(applicabilityRunCheckForIntersectionComponents4) > 0) {
                                                applicabilityRunCheckForIntersectionComponents = applicabilityRunCheckForIntersectionComponents4;
                                            }
                                        }
                                    } else {
                                        applicabilityRunCheckForIntersectionComponents = null;
                                    }
                                    if (applicabilityRunCheckForIntersectionComponents == null) {
                                        applicabilityRunCheckForIntersectionComponents2 = FirEqualityCompatibilityChecker.Applicability.APPLICABLE;
                                    }
                                } else {
                                    applicabilityRunCheckForIntersectionComponents2 = firWasmJsEqualityChecker2.runCheckForIntersectionComponents(checkerContext, firOperation, coneKotlinType4, coneKotlinType2, firEqualityCompatibilityChecker);
                                }
                                applicabilityRunCheckForIntersectionComponents = applicabilityRunCheckForIntersectionComponents2;
                            } else {
                                applicabilityRunCheckForIntersectionComponents2 = firWasmJsEqualityChecker2.runCheckForIntersectionComponents(checkerContext, firOperation, coneKotlinType4, coneKotlinType2, firEqualityCompatibilityChecker);
                                applicabilityRunCheckForIntersectionComponents = applicabilityRunCheckForIntersectionComponents2;
                            }
                        } while (applicability.compareTo(applicabilityRunCheckForIntersectionComponents) <= 0);
                    }
                    applicability2 = applicability;
                }
                return applicability2 == null ? FirEqualityCompatibilityChecker.Applicability.APPLICABLE : applicability2;
            }
        }
        FirWasmJsEqualityChecker firWasmJsEqualityChecker3 = INSTANCE;
        if (coneKotlinType2 instanceof ConeIntersectionType) {
            ConeIntersectionType coneIntersectionType4 = (ConeIntersectionType) coneKotlinType2;
            if (firWasmJsEqualityChecker3.getHasJsAnyOrJsReference(coneIntersectionType4)) {
                Collection<ConeKotlinType> intersectedTypes4 = coneIntersectionType4.getIntersectedTypes();
                ArrayList arrayList4 = new ArrayList();
                for (Object obj4 : intersectedTypes4) {
                    if (INSTANCE.isJsAnyOrJsReference((ConeKotlinType) obj4)) {
                        arrayList4.add(obj4);
                    }
                }
                Iterator it4 = arrayList4.iterator();
                if (it4.hasNext()) {
                    FirEqualityCompatibilityChecker.Applicability applicabilityRunCheckForIntersectionComponents5 = INSTANCE.runCheckForIntersectionComponents(checkerContext, firOperation, coneKotlinType, (ConeKotlinType) it4.next(), firEqualityCompatibilityChecker);
                    loop8: while (true) {
                        applicability2 = applicabilityRunCheckForIntersectionComponents5;
                        do {
                            if (!it4.hasNext()) {
                                break loop8;
                            }
                            applicabilityRunCheckForIntersectionComponents5 = INSTANCE.runCheckForIntersectionComponents(checkerContext, firOperation, coneKotlinType, (ConeKotlinType) it4.next(), firEqualityCompatibilityChecker);
                        } while (applicability2.compareTo(applicabilityRunCheckForIntersectionComponents5) <= 0);
                    }
                }
                return applicability2 == null ? FirEqualityCompatibilityChecker.Applicability.APPLICABLE : applicability2;
            }
        }
        return firWasmJsEqualityChecker3.runCheckForIntersectionComponents(checkerContext, firOperation, coneKotlinType, coneKotlinType2, firEqualityCompatibilityChecker);
    }

    public final ConeKotlinType tryUnwrapJsReferenceType(ConeKotlinType type) {
        type.getClass();
        if (!Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(type), JsStandardClassIds.JsReference)) {
            return type;
        }
        ConeKotlinTypeProjectionOut coneKotlinTypeProjectionOut = (ConeTypeProjection) ArraysKt.firstOrNull(type.getTypeArguments());
        if (coneKotlinTypeProjectionOut instanceof ConeKotlinTypeProjectionOut) {
            return coneKotlinTypeProjectionOut.getType();
        }
        if (coneKotlinTypeProjectionOut instanceof ConeKotlinType) {
            return (ConeKotlinType) coneKotlinTypeProjectionOut;
        }
        return null;
    }
}
