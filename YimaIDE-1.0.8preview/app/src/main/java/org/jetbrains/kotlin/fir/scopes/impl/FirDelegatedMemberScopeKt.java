package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.scopes.impl.FirDelegatedMemberScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\n\u0010\f\u001a\u00020\u0001*\u00020\r\u001a\u001a\u0010\u000e\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0001\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"\u001b\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u000b\"\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010\u0016\u001a\u00020\u0017*\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"<set-?>", Argument.Delimiters.none, "multipleDelegatesWithTheSameSignature", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "getMultipleDelegatesWithTheSameSignature", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Ljava/lang/Boolean;", "setMultipleDelegatesWithTheSameSignature", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Ljava/lang/Boolean;)V", "multipleDelegatesWithTheSameSignature$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Ljava/lang/Boolean;", "isPublicInAny", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "hasTypeOf", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "allowNullable", "PUBLIC_METHOD_NAMES_IN_ANY", Argument.Delimiters.none, Argument.Delimiters.none, "delegatedMembersFilter", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirDelegatedMembersFilter;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getDelegatedMembersFilter", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/impl/FirDelegatedMembersFilter;", "delegatedMembersFilter$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDelegatedMemberScopeKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirDelegatedMemberScopeKt.class, "multipleDelegatesWithTheSameSignature", "getMultipleDelegatesWithTheSameSignature(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Ljava/lang/Boolean;", 1), new PropertyReference1Impl<>(FirDelegatedMemberScopeKt.class, "delegatedMembersFilter", "getDelegatedMembersFilter(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/impl/FirDelegatedMembersFilter;", 1)};
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor multipleDelegatesWithTheSameSignature$delegate = FirDeclarationDataRegistry.INSTANCE.data(MultipleDelegatesWithTheSameSignatureKey.INSTANCE);
    private static final Set<String> PUBLIC_METHOD_NAMES_IN_ANY = SetsKt.setOf(new String[]{"equals", "hashCode", "toString"});
    private static final ArrayMapAccessor delegatedMembersFilter$delegate = FirSession.INSTANCE.generateAccessor(Reflection.getOrCreateKotlinClass(FirDelegatedMembersFilter.class), FirDelegatedMembersFilter.Default.INSTANCE);

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirDelegatedMembersFilter getDelegatedMembersFilter(FirSession firSession) {
        return (FirDelegatedMembersFilter) delegatedMembersFilter$delegate.getValue(firSession, $$delegatedProperties[1]);
    }

    public static final Boolean getMultipleDelegatesWithTheSameSignature(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return (Boolean) multipleDelegatesWithTheSameSignature$delegate.getValue(firCallableDeclaration, $$delegatedProperties[0]);
    }

    public static final boolean hasTypeOf(FirValueParameter firValueParameter, ClassId classId, boolean z) {
        ConeClassLikeType coneClassLikeType;
        firValueParameter.getClass();
        classId.getClass();
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef());
        if (coneType instanceof ConeClassLikeType) {
            coneClassLikeType = (ConeClassLikeType) coneType;
        } else {
            if (!(coneType instanceof ConeFlexibleType)) {
                return false;
            }
            ConeRigidType upperBound = ((ConeFlexibleType) coneType).getUpperBound();
            coneClassLikeType = upperBound instanceof ConeClassLikeType ? (ConeClassLikeType) upperBound : null;
            if (coneClassLikeType == null) {
                return false;
            }
        }
        if (!coneClassLikeType.getIsMarkedNullable() || z) {
            return Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), classId);
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0036, code lost:
    
        if (r0.equals("hashCode") != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0062, code lost:
    
        if (r0.equals("toString") != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006c, code lost:
    
        return r4.getValueParameters().isEmpty();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final boolean isPublicInAny(final FirNamedFunction firNamedFunction) throws KotlinIllegalArgumentExceptionWithAttachments {
        firNamedFunction.getClass();
        if (!PUBLIC_METHOD_NAMES_IN_ANY.contains(firNamedFunction.getName().asString())) {
            return false;
        }
        String strAsString = firNamedFunction.getName().asString();
        int iHashCode = strAsString.hashCode();
        if (iHashCode != -1776922004) {
            if (iHashCode != -1295482945) {
                if (iHashCode == 147696667) {
                }
            } else if (strAsString.equals("equals")) {
                FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.singleOrNull(firNamedFunction.getValueParameters());
                return firValueParameter != null && hasTypeOf(firValueParameter, StandardClassIds.INSTANCE.getAny(), true);
            }
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unexpected method name", (Throwable) null);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            exceptionAttachmentBuilder.withEntry("methodName", firNamedFunction.getName(), new Function1() { // from class: t25
                public final Object invoke(Object obj) {
                    return FirDelegatedMemberScopeKt.isPublicInAny$lambda$0$0(firNamedFunction, (Name) obj);
                }
            });
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String isPublicInAny$lambda$0$0(FirNamedFunction firNamedFunction, Name name) {
        name.getClass();
        String strAsString = firNamedFunction.getName().asString();
        strAsString.getClass();
        return strAsString;
    }

    public static final void setMultipleDelegatesWithTheSameSignature(FirCallableDeclaration firCallableDeclaration, Boolean bool) {
        firCallableDeclaration.getClass();
        multipleDelegatesWithTheSameSignature$delegate.setValue(firCallableDeclaration, $$delegatedProperties[0], bool);
    }

    public static final Boolean getMultipleDelegatesWithTheSameSignature(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return getMultipleDelegatesWithTheSameSignature((FirCallableDeclaration) firCallableSymbol.getFir());
    }
}
