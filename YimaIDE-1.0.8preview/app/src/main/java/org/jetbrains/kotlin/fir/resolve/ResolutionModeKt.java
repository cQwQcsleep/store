package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001\u001a/\u0010\u0005\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\f\u001a\u00020\rH\u0007b\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u000e¢\u0006\u0002\b\u000e\u001a\u0018\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\r\u001a\n\u0010\u0011\u001a\u00020\u0002*\u00020\u0012\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0013"}, d2 = {"expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "getExpectedType", "(Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "withExpectedType", "expectedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "arrayLiteralPosition", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ArrayLiteralPosition;", "hintForContextSensitiveResolution", "coneType", "lastStatementInBlock", Argument.Delimiters.none, "withExpectedTypeNullable", "Lkotlin/jvm/JvmName;", ModuleXmlParser.NAME, "mode", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ResolutionModeKt {
    public static final ConeKotlinType getExpectedType(ResolutionMode resolutionMode) {
        resolutionMode.getClass();
        if (resolutionMode instanceof ResolutionMode.WithExpectedType) {
            ResolutionMode.WithExpectedType withExpectedType = (ResolutionMode.WithExpectedType) resolutionMode;
            ConeKotlinType expectedType = withExpectedType.getExpectedType();
            if (!withExpectedType.getFromCast()) {
                return expectedType;
            }
        }
        return null;
    }

    public static final ResolutionMode mode(FirDeclarationStatus firDeclarationStatus) {
        firDeclarationStatus.getClass();
        return new ResolutionMode.WithStatus(firDeclarationStatus);
    }

    public static final ResolutionMode withExpectedType(ConeKotlinType coneKotlinType, boolean z) {
        coneKotlinType.getClass();
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setConeType(coneKotlinType);
        return new ResolutionMode.WithExpectedType(firResolvedTypeRefBuilder.build(), z, false, null, null, false, 60, null);
    }

    public static /* synthetic */ ResolutionMode withExpectedType$default(FirTypeRef firTypeRef, ResolutionMode.ArrayLiteralPosition arrayLiteralPosition, ConeKotlinType coneKotlinType, int i, Object obj) {
        if ((i & 2) != 0) {
            arrayLiteralPosition = null;
        }
        if ((i & 4) != 0) {
            coneKotlinType = null;
        }
        return withExpectedType(firTypeRef, arrayLiteralPosition, coneKotlinType);
    }

    public static final ResolutionMode withExpectedTypeNullable(ConeKotlinType coneKotlinType, boolean z) {
        ResolutionMode resolutionModeWithExpectedType;
        return (coneKotlinType == null || (resolutionModeWithExpectedType = withExpectedType(coneKotlinType, z)) == null) ? ResolutionMode.ContextDependent.INSTANCE : resolutionModeWithExpectedType;
    }

    public static /* synthetic */ ResolutionMode withExpectedTypeNullable$default(ConeKotlinType coneKotlinType, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return withExpectedTypeNullable(coneKotlinType, z);
    }

    public static /* synthetic */ ResolutionMode withExpectedType$default(ConeKotlinType coneKotlinType, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return withExpectedType(coneKotlinType, z);
    }

    public static final ResolutionMode withExpectedType(FirTypeRef firTypeRef, ResolutionMode.ArrayLiteralPosition arrayLiteralPosition, ConeKotlinType coneKotlinType) {
        firTypeRef.getClass();
        if (firTypeRef instanceof FirResolvedTypeRef) {
            return new ResolutionMode.WithExpectedType((FirResolvedTypeRef) firTypeRef, false, false, arrayLiteralPosition, coneKotlinType, false, 38, null);
        }
        return ResolutionMode.ContextIndependent.INSTANCE;
    }
}
