package org.jetbrains.kotlin.fir.analysis.js.checkers;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.FirIdentityLessPlatformDeterminer;
import org.jetbrains.kotlin.fir.analysis.checkers.TypeInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsIdentityLessPlatformDeterminer;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirIdentityLessPlatformDeterminer;", "<init>", "()V", "isIdentityLess", Argument.Delimiters.none, "typeInfo", "Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsIdentityLessPlatformDeterminer extends FirIdentityLessPlatformDeterminer {
    public static final FirJsIdentityLessPlatformDeterminer INSTANCE = new FirJsIdentityLessPlatformDeterminer();

    private FirJsIdentityLessPlatformDeterminer() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirIdentityLessPlatformDeterminer
    public boolean isIdentityLess(TypeInfo typeInfo) {
        typeInfo.getClass();
        return false;
    }
}
