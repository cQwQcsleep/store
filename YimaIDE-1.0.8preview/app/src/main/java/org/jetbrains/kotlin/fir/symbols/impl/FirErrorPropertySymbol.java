package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \n2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\nB\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirErrorPropertySymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularPropertySymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirErrorCallableSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "<init>", "(Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;)V", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "Companion", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirErrorPropertySymbol extends FirRegularPropertySymbol implements FirErrorCallableSymbol<FirProperty> {
    private static final CallableId CALLABLE_ID;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Name NAME;
    private final ConeDiagnostic diagnostic;

    static {
        Name nameSpecial = Name.special("<error property>");
        nameSpecial.getClass();
        NAME = nameSpecial;
        CALLABLE_ID = new CallableId(FqName.ROOT, nameSpecial);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirErrorPropertySymbol(ConeDiagnostic coneDiagnostic) {
        super(CALLABLE_ID);
        coneDiagnostic.getClass();
        this.diagnostic = coneDiagnostic;
    }

    public final ConeDiagnostic getDiagnostic() {
        return this.diagnostic;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirErrorPropertySymbol$Companion;", Argument.Delimiters.none, "<init>", "()V", "NAME", "Lorg/jetbrains/kotlin/name/Name;", "getNAME", "()Lorg/jetbrains/kotlin/name/Name;", "CALLABLE_ID", "Lorg/jetbrains/kotlin/name/CallableId;", "getCALLABLE_ID", "()Lorg/jetbrains/kotlin/name/CallableId;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CallableId getCALLABLE_ID() {
            return FirErrorPropertySymbol.CALLABLE_ID;
        }

        public final Name getNAME() {
            return FirErrorPropertySymbol.NAME;
        }

        private Companion() {
        }
    }
}
