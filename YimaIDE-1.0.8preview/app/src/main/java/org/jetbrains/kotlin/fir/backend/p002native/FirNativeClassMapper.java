package org.jetbrains.kotlin.fir.backend.p002native;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.NativeRuntimeNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016J\u0014\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016R \u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/native/FirNativeClassMapper;", "Lorg/jetbrains/kotlin/fir/scopes/FirPlatformClassMapper;", "<init>", "()V", "getCorrespondingPlatformClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/name/ClassId;", "classId", "getCorrespondingKotlinClass", "classTypealiasesThatDontCauseAmbiguity", Argument.Delimiters.none, "getClassTypealiasesThatDontCauseAmbiguity", "()Ljava/util/Map;", "org.jetbrains.kotlin:fir-native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeClassMapper extends FirPlatformClassMapper {
    private final Map<ClassId, ClassId> classTypealiasesThatDontCauseAmbiguity;

    public FirNativeClassMapper() {
        NativeRuntimeNames.Annotations annotations = NativeRuntimeNames.Annotations.INSTANCE;
        this.classTypealiasesThatDontCauseAmbiguity = MapsKt.mapOf(new Pair[]{TuplesKt.to(annotations.getThrows(), annotations.getThrowsAlias()), TuplesKt.to(annotations.getSharedImmutable(), annotations.getSharedImmutableAlias()), TuplesKt.to(annotations.getThreadLocal(), annotations.getThreadLocalAlias())});
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
    public Map<ClassId, ClassId> getClassTypealiasesThatDontCauseAmbiguity() {
        return this.classTypealiasesThatDontCauseAmbiguity;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
    public ClassId getCorrespondingKotlinClass(ClassId classId) {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
    public FirRegularClass getCorrespondingPlatformClass(FirClassLikeDeclaration declaration) {
        declaration.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
    public ClassId getCorrespondingPlatformClass(ClassId classId) {
        return null;
    }
}
