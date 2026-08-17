package org.jetbrains.kotlin.backend.konan.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.library.loader.KlibLoaderResult;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/serialization/UnknownIrProvider;", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$OtherCheckMismatch;", "irProviderName", "", "<init>", "(Ljava/lang/String;)V", "getIrProviderName", "()Ljava/lang/String;", "caption", "getCaption", "details", "getDetails", "org.jetbrains.kotlin:ir.serialization.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class UnknownIrProvider extends KlibLoaderResult.ProblemCase.OtherCheckMismatch {
    private final String irProviderName;

    public UnknownIrProvider(String str) {
        str.getClass();
        this.irProviderName = str;
    }

    @Override // org.jetbrains.kotlin.library.loader.KlibLoaderResult.ProblemCase.OtherCheckMismatch
    public String getCaption() {
        return "Library with unsupported IR provider " + this.irProviderName;
    }

    @Override // org.jetbrains.kotlin.library.loader.KlibLoaderResult.ProblemCase.OtherCheckMismatch
    public String getDetails() {
        return "Only libraries without IR provider or with kotlin.native.cinterop IR provider are supported.";
    }

    public final String getIrProviderName() {
        return this.irProviderName;
    }
}
