package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationLevelValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/SimpleFirDeprecationInfo;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "deprecationLevel", "Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationLevelValue;", "propagatesToOverrides", Argument.Delimiters.none, "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "<init>", "(Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationLevelValue;ZLorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)V", "getDeprecationLevel", "()Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationLevelValue;", "getPropagatesToOverrides", "()Z", "getMessage", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SimpleFirDeprecationInfo extends FirDeprecationInfo {
    private final FirAnnotation annotation;
    private final DeprecationLevelValue deprecationLevel;
    private final boolean propagatesToOverrides;

    public SimpleFirDeprecationInfo(DeprecationLevelValue deprecationLevelValue, boolean z, FirAnnotation firAnnotation) {
        deprecationLevelValue.getClass();
        firAnnotation.getClass();
        this.deprecationLevel = deprecationLevelValue;
        this.propagatesToOverrides = z;
        this.annotation = firAnnotation;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo
    public DeprecationLevelValue getDeprecationLevel() {
        return this.deprecationLevel;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo
    public String getMessage(FirSession session) {
        FirBasedSymbol<?> containingDeclarationSymbol;
        session.getClass();
        FirAnnotation firAnnotation = this.annotation;
        FirAnnotationCall firAnnotationCall = firAnnotation instanceof FirAnnotationCall ? (FirAnnotationCall) firAnnotation : null;
        if (firAnnotationCall != null && (containingDeclarationSymbol = firAnnotationCall.getContainingDeclarationSymbol()) != null) {
            FirLazyDeclarationResolverKt.lazyResolveToPhase(containingDeclarationSymbol, FirResolvePhase.ANNOTATION_ARGUMENTS);
        }
        return FirAnnotationUtilsKt.getStringArgument(this.annotation, StandardClassIds$Annotations.ParameterNames.INSTANCE.getDeprecatedMessage());
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo
    public boolean getPropagatesToOverrides() {
        return this.propagatesToOverrides;
    }
}
