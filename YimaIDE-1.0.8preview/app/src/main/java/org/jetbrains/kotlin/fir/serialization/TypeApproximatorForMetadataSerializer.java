package org.jetbrains.kotlin.fir.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.diagnostics.ConeIntermediateDiagnostic;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.types.AbstractTypeApproximator;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.SimpleTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/TypeApproximatorForMetadataSerializer;", "Lorg/jetbrains/kotlin/types/AbstractTypeApproximator;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "createErrorType", "Lorg/jetbrains/kotlin/types/model/SimpleTypeMarker;", "debugName", Argument.Delimiters.none, "delegatedType", "Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeApproximatorForMetadataSerializer extends AbstractTypeApproximator {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TypeApproximatorForMetadataSerializer(FirSession firSession) {
        super(TypeComponentsKt.getTypeContext(firSession), FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession));
        firSession.getClass();
    }

    public SimpleTypeMarker createErrorType(String debugName, RigidTypeMarker delegatedType) {
        debugName.getClass();
        return new ConeErrorType(new ConeIntermediateDiagnostic(debugName), false, null, null, null, null, null, 126, null);
    }
}
