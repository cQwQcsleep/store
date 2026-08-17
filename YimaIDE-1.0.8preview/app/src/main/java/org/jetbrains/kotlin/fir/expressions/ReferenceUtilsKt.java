package org.jetbrains.kotlin.fir.expressions;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.builder.FirErrorNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedNameError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedSymbolError;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0001*\u00020\u0005H\u0007b\u0002\b\u0007\u001a\u0018\u0010\b\u001a\u0004\u0018\u00010\u0001*\u00020\u00052\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0004\u001a\f\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u0010\u001a\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u000f*\u00020\u0005H\u0007b\u0002\b\u0007\u001a\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u000f*\u00020\u00052\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001a\u0018\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0014*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0010\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0014*\u00020\u0010\u001a\u0016\u0010\u0015\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0014*\u00020\u0005H\u0007b\u0002\b\u0007\"\u0017\u0010\n\u001a\u0004\u0018\u00010\u0001*\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"toReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "Lorg/jetbrains/kotlin/fir/FirElement;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "toReferenceUnsafe", "Lorg/jetbrains/kotlin/fir/expressions/UnsafeExpressionUtility;", "toReferenceImpl", "Lorg/jetbrains/kotlin/fir/expressions/FirEnumEntryDeserializedAccessExpression;", "calleeReference", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "getCalleeReference", "(Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;)Lorg/jetbrains/kotlin/fir/references/FirReference;", "toResolvedCallableReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;", "toResolvedCallableReferenceUnsafe", "toResolvedCallableReferenceImpl", "toResolvedCallableSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "toResolvedCallableSymbolUnsafe", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReferenceUtilsKt {
    public static final FirReference getCalleeReference(FirVariableAssignment firVariableAssignment) {
        firVariableAssignment.getClass();
        return toReferenceImpl(firVariableAssignment.getLValue(), null);
    }

    private static final FirReference toReference(FirEnumEntryDeserializedAccessExpression firEnumEntryDeserializedAccessExpression, FirSession firSession) {
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(FirTypeUtilsKt.getResolvedType(firEnumEntryDeserializedAccessExpression), firSession);
        Object obj = null;
        if (regularClassSymbol == null) {
            ClassId classId = ConeTypeUtilsKt.getClassId(FirTypeUtilsKt.getResolvedType(firEnumEntryDeserializedAccessExpression));
            classId.getClass();
            return FirReferenceUtilsKt.buildErrorNamedReferenceWithNoName$default(new ConeUnresolvedSymbolError(classId), null, 2, null);
        }
        for (Object obj2 : DeclarationUtilsKt.collectEnumEntries(regularClassSymbol, firSession)) {
            if (Intrinsics.areEqual(((FirEnumEntrySymbol) obj2).getName(), firEnumEntryDeserializedAccessExpression.getEnumEntryName())) {
                obj = obj2;
                break;
            }
        }
        FirEnumEntrySymbol firEnumEntrySymbol = (FirEnumEntrySymbol) obj;
        if (firEnumEntrySymbol == null) {
            FirErrorNamedReferenceBuilder firErrorNamedReferenceBuilder = new FirErrorNamedReferenceBuilder();
            firErrorNamedReferenceBuilder.setDiagnostic(new ConeUnresolvedNameError(firEnumEntryDeserializedAccessExpression.getEnumEntryName(), null, null, 6, null));
            firErrorNamedReferenceBuilder.setName(firEnumEntryDeserializedAccessExpression.getEnumEntryName());
            return firErrorNamedReferenceBuilder.build();
        }
        FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
        firResolvedNamedReferenceBuilder.setName(firEnumEntryDeserializedAccessExpression.getEnumEntryName());
        firResolvedNamedReferenceBuilder.setResolvedSymbol(firEnumEntrySymbol);
        return firResolvedNamedReferenceBuilder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final FirReference toReferenceImpl(FirExpression firExpression, FirSession firSession) {
        if (firExpression instanceof FirEnumEntryDeserializedAccessExpression) {
            if (firSession != null) {
                return toReference((FirEnumEntryDeserializedAccessExpression) firExpression, firSession);
            }
            w01.a("Required value was null.");
            return null;
        }
        if (firExpression instanceof FirWrappedArgumentExpression) {
            return toResolvedCallableReferenceImpl(((FirWrappedArgumentExpression) firExpression).getExpression(), firSession);
        }
        if (firExpression instanceof FirSmartCastExpression) {
            return toReferenceImpl(((FirSmartCastExpression) firExpression).getOriginalExpression(), firSession);
        }
        if (firExpression instanceof FirDesugaredAssignmentValueReferenceExpression) {
            return toReferenceImpl(((FirDesugaredAssignmentValueReferenceExpression) firExpression).getExpressionRef().getValue(), firSession);
        }
        if (firExpression instanceof FirResolvable) {
            return ((FirResolvable) firExpression).getCalleeReference();
        }
        return null;
    }

    @UnsafeExpressionUtility
    public static final FirReference toReferenceUnsafe(FirExpression firExpression) {
        firExpression.getClass();
        return toReferenceImpl(firExpression, null);
    }

    public static final FirResolvedNamedReference toResolvedCallableReference(FirResolvable firResolvable) {
        firResolvable.getClass();
        return FirReferenceUtilsKt.getResolved(firResolvable.getCalleeReference());
    }

    public static final FirResolvedNamedReference toResolvedCallableReferenceImpl(FirExpression firExpression, FirSession firSession) {
        firExpression.getClass();
        FirReference referenceImpl = toReferenceImpl(firExpression, firSession);
        if (referenceImpl != null) {
            return FirReferenceUtilsKt.getResolved(referenceImpl);
        }
        return null;
    }

    @UnsafeExpressionUtility
    public static final FirResolvedNamedReference toResolvedCallableReferenceUnsafe(FirExpression firExpression) {
        firExpression.getClass();
        return toResolvedCallableReferenceImpl(firExpression, null);
    }

    public static final FirCallableSymbol<?> toResolvedCallableSymbol(FirExpression firExpression, FirSession firSession) {
        firExpression.getClass();
        firSession.getClass();
        FirResolvedNamedReference resolvedCallableReference = toResolvedCallableReference(firExpression, firSession);
        FirBasedSymbol<?> resolvedSymbol = resolvedCallableReference != null ? resolvedCallableReference.getResolvedSymbol() : null;
        if (resolvedSymbol instanceof FirCallableSymbol) {
            return (FirCallableSymbol) resolvedSymbol;
        }
        return null;
    }

    @UnsafeExpressionUtility
    public static final FirCallableSymbol<?> toResolvedCallableSymbolUnsafe(FirExpression firExpression) {
        firExpression.getClass();
        FirResolvedNamedReference resolvedCallableReferenceUnsafe = toResolvedCallableReferenceUnsafe(firExpression);
        FirBasedSymbol<?> resolvedSymbol = resolvedCallableReferenceUnsafe != null ? resolvedCallableReferenceUnsafe.getResolvedSymbol() : null;
        if (resolvedSymbol instanceof FirCallableSymbol) {
            return (FirCallableSymbol) resolvedSymbol;
        }
        return null;
    }

    public static final FirResolvedNamedReference toResolvedCallableReference(FirExpression firExpression, FirSession firSession) {
        firExpression.getClass();
        firSession.getClass();
        return toResolvedCallableReferenceImpl(firExpression, firSession);
    }

    public static final FirCallableSymbol<?> toResolvedCallableSymbol(FirResolvable firResolvable) {
        firResolvable.getClass();
        FirResolvedNamedReference resolvedCallableReference = toResolvedCallableReference(firResolvable);
        FirBasedSymbol<?> resolvedSymbol = resolvedCallableReference != null ? resolvedCallableReference.getResolvedSymbol() : null;
        if (resolvedSymbol instanceof FirCallableSymbol) {
            return (FirCallableSymbol) resolvedSymbol;
        }
        return null;
    }

    public static final FirReference toReference(FirExpression firExpression, FirSession firSession) {
        firExpression.getClass();
        firSession.getClass();
        return toReferenceImpl(firExpression, firSession);
    }

    public static final FirReference toReference(FirElement firElement, FirSession firSession) {
        firElement.getClass();
        firSession.getClass();
        if (firElement instanceof FirExpression) {
            return toReferenceImpl((FirExpression) firElement, firSession);
        }
        if (firElement instanceof FirVariableAssignment) {
            return getCalleeReference((FirVariableAssignment) firElement);
        }
        if (firElement instanceof FirResolvable) {
            return ((FirResolvable) firElement).getCalleeReference();
        }
        return null;
    }
}
