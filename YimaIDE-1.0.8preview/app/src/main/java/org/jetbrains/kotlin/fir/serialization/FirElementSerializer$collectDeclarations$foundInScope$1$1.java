package org.jetbrains.kotlin.fir.serialization;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirElementSerializer$collectDeclarations$foundInScope$1$1 implements Function1 {
    final /* synthetic */ List $this_buildList;
    final /* synthetic */ FirClass $this_collectDeclarations;

    public FirElementSerializer$collectDeclarations$foundInScope$1$1(FirClass firClass, List list) {
        this.$this_collectDeclarations = firClass;
        this.$this_buildList = list;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:10:0x003b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:11:0x003d  */
    /* JADX WARN: Code duplicated, block: B:12:0x0042  */
    /* JADX WARN: Code duplicated, block: B:23:0x006b  */
    /* JADX WARN: Code duplicated, block: B:25:0x0071  */
    /* JADX WARN: Code duplicated, block: B:27:0x007b  */
    /* JADX WARN: Code duplicated, block: B:33:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    public final void invoke(FirCallableSymbol firCallableSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        ClassId classId;
        Modality modality;
        firCallableSymbol.getClass();
        D fir = firCallableSymbol.getFir();
        Intrinsics.reifiedOperationMarker(1, "T");
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) fir;
        ConeClassLikeLookupTag coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull = ClassMembersKt.dispatchReceiverClassLookupTagOrNull(firCallableDeclaration);
        FirClass firClass = this.$this_collectDeclarations;
        if (firClass instanceof FirRegularClass) {
            if (((FirRegularClass) firClass).getStatus().isData()) {
                if (coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull != null) {
                    classId = coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull.getClassId();
                } else {
                    classId = null;
                }
                if (Intrinsics.areEqual(classId, StandardClassIds.INSTANCE.getAny())) {
                    if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                        return;
                    }
                    if (!firCallableDeclaration.getStatus().isStatic()) {
                        return;
                    }
                } else {
                    if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                        return;
                    }
                    if (!firCallableDeclaration.getStatus().isStatic()) {
                        return;
                    }
                }
            } else {
                FirClass firClass2 = this.$this_collectDeclarations;
                if (firClass2.getStatus().isInline() || firClass2.getStatus().isValue()) {
                    if (coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull != null) {
                        classId = coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull.getClassId();
                    } else {
                        classId = null;
                    }
                    if (Intrinsics.areEqual(classId, StandardClassIds.INSTANCE.getAny()) || (modality = firCallableDeclaration.getStatus().getModality()) == null || modality == Modality.FINAL || this.$this_collectDeclarations.getStatus().isExpect()) {
                        if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                            return;
                        }
                        if (!firCallableDeclaration.getStatus().isStatic()) {
                            return;
                        }
                    }
                } else {
                    if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                        return;
                    }
                    if (!firCallableDeclaration.getStatus().isStatic()) {
                        return;
                    }
                }
            }
        } else {
            if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                return;
            }
            if (!firCallableDeclaration.getStatus().isStatic() && !(firCallableDeclaration instanceof FirConstructor) && !Intrinsics.areEqual(coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull, this.$this_collectDeclarations.getSymbol().getLookupTag())) {
                return;
            }
        }
        this.$this_buildList.add(firCallableDeclaration);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public /* bridge */ /* synthetic */ Object invoke(Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        invoke((FirCallableSymbol) obj);
        return Unit.INSTANCE;
    }
}
