package org.jetbrains.kotlin.fir.analysis.jvm.checkers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.JvmDefaultMode;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.name.JvmStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\u00020\u0001\"\f\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"isCompiledToJvmDefault", Argument.Delimiters.none, "D", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "jvmDefaultMode", "Lorg/jetbrains/kotlin/config/JvmDefaultMode;", "org.jetbrains.kotlin:checkers.jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmAnnotationHelperKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <D extends FirDeclaration & FirAnnotationContainer> boolean isCompiledToJvmDefault(FirBasedSymbol<? extends D> firBasedSymbol, FirSession firSession, JvmDefaultMode jvmDefaultMode) {
        firBasedSymbol.getClass();
        firSession.getClass();
        jvmDefaultMode.getClass();
        if (FirAnnotationUtilsKt.hasAnnotationWithClassId(firBasedSymbol, JvmStandardClassIds.Annotations.INSTANCE.getJvmDefault(), firSession)) {
            return true;
        }
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firBasedSymbol);
        if (containingClassSymbol instanceof FirRegularClassSymbol) {
            FirRegularClassSymbol firRegularClassSymbol = (FirRegularClassSymbol) containingClassSymbol;
            if (!firRegularClassSymbol.getOrigin().getFromSource()) {
                return Intrinsics.areEqual(ClassMembersKt.isNewPlaceForBodyGeneration((FirRegularClass) firRegularClassSymbol.getFir()), Boolean.TRUE);
            }
        }
        return jvmDefaultMode.isEnabled();
    }
}
