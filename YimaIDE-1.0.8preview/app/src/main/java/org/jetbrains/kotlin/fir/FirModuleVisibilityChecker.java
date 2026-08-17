package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirModuleVisibilityChecker;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "isInFriendModule", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "Standard", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirModuleVisibilityChecker implements FirSessionComponent {

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirModuleVisibilityChecker$Standard;", "Lorg/jetbrains/kotlin/fir/FirModuleVisibilityChecker;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "isInFriendModule", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Standard extends FirModuleVisibilityChecker {
        private final FirSession session;

        public Standard(FirSession firSession) {
            firSession.getClass();
            this.session = firSession;
        }

        public final FirSession getSession() {
            return this.session;
        }

        @Override // org.jetbrains.kotlin.fir.FirModuleVisibilityChecker
        public boolean isInFriendModule(FirMemberDeclaration declaration) {
            declaration.getClass();
            return FirModuleDataKt.canSeeInternalsOf(FirModuleDataKt.getModuleData(this.session), declaration.getModuleData());
        }
    }

    public abstract boolean isInFriendModule(FirMemberDeclaration declaration);
}
