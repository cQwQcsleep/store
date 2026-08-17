package org.jetbrains.kotlin.fir.resolve;

import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.builtins.jvm.JavaToKotlinClassMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.JvmStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0014\u0010\u0006\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016J\u0014\u0010\f\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirJavaClassMapper;", "Lorg/jetbrains/kotlin/fir/scopes/FirPlatformClassMapper;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getCorrespondingPlatformClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/name/ClassId;", "classId", "getCorrespondingKotlinClass", "classTypealiasesThatDontCauseAmbiguity", Argument.Delimiters.none, "getClassTypealiasesThatDontCauseAmbiguity", "()Ljava/util/Map;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaClassMapper extends FirPlatformClassMapper {
    private final Map<ClassId, ClassId> classTypealiasesThatDontCauseAmbiguity;
    private final FirSession session;

    public FirJavaClassMapper(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        JvmStandardClassIds.Annotations annotations = JvmStandardClassIds.Annotations.INSTANCE;
        this.classTypealiasesThatDontCauseAmbiguity = MapsKt.mapOf(TuplesKt.to(annotations.getThrows(), annotations.getThrowsAlias()));
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
    public Map<ClassId, ClassId> getClassTypealiasesThatDontCauseAmbiguity() {
        return this.classTypealiasesThatDontCauseAmbiguity;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
    public ClassId getCorrespondingKotlinClass(ClassId classId) {
        if (classId == null) {
            return null;
        }
        return JavaToKotlinClassMap.INSTANCE.mapJavaToKotlin(classId.asSingleFqName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
    public FirRegularClass getCorrespondingPlatformClass(FirClassLikeDeclaration declaration) {
        FirClassLikeSymbol<?> classLikeSymbolByClassId;
        declaration.getClass();
        ClassId correspondingPlatformClass = getCorrespondingPlatformClass(declaration.getSymbol().getClassId());
        FirClassLikeDeclaration firClassLikeDeclaration = (correspondingPlatformClass == null || (classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(this.session).getClassLikeSymbolByClassId(correspondingPlatformClass)) == null) ? null : (FirClassLikeDeclaration) classLikeSymbolByClassId.getFir();
        if (firClassLikeDeclaration instanceof FirRegularClass) {
            return (FirRegularClass) firClassLikeDeclaration;
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
    public ClassId getCorrespondingPlatformClass(ClassId classId) {
        if (classId == null) {
            return null;
        }
        return JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(classId.asSingleFqName().toUnsafe());
    }
}
