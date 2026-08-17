package org.jetbrains.kotlin.fir.analysis.checkers;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirIdentityLessPlatformDeterminer;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "isIdentityLess", Argument.Delimiters.none, "typeInfo", "Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;", "Default", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirIdentityLessPlatformDeterminer implements FirSessionComponent {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirIdentityLessPlatformDeterminer$Default;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirIdentityLessPlatformDeterminer;", "<init>", "()V", "isIdentityLess", Argument.Delimiters.none, "typeInfo", "Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Default extends FirIdentityLessPlatformDeterminer {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirIdentityLessPlatformDeterminer
        public boolean isIdentityLess(TypeInfo typeInfo) {
            typeInfo.getClass();
            return typeInfo.getIsPrimitive();
        }
    }

    public abstract boolean isIdentityLess(TypeInfo typeInfo);
}
