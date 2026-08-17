package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.kotlin.types.model.TypeVariableTypeConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u0015\u001a\u00020\u0016J\n\u0010\u0017\u001a\u00020\u0004H\u0096\u0080\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableTypeConstructor;", "Lorg/jetbrains/kotlin/types/model/TypeVariableTypeConstructorMarker;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeConstructorMarker;", "debugName", Argument.Delimiters.none, "originalTypeParameter", "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;)V", "getDebugName", "()Ljava/lang/String;", "getOriginalTypeParameter", "()Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "value", Argument.Delimiters.none, "isContainedInInvariantOrContravariantPositions", "()Z", "recordInfoAboutTypeVariableUsagesAsInvariantOrContravariantParameter", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeVariableTypeConstructor implements ConeTypeConstructorMarker, TypeVariableTypeConstructorMarker {
    private final String debugName;
    private boolean isContainedInInvariantOrContravariantPositions;
    private final TypeParameterMarker originalTypeParameter;

    public ConeTypeVariableTypeConstructor(String str, TypeParameterMarker typeParameterMarker) {
        str.getClass();
        this.debugName = str;
        this.originalTypeParameter = typeParameterMarker;
    }

    public final String getDebugName() {
        return this.debugName;
    }

    public final Name getName() {
        Name nameIdentifier = Name.identifier(this.debugName);
        nameIdentifier.getClass();
        return nameIdentifier;
    }

    public final TypeParameterMarker getOriginalTypeParameter() {
        return this.originalTypeParameter;
    }

    /* JADX INFO: renamed from: isContainedInInvariantOrContravariantPositions, reason: from getter */
    public final boolean getIsContainedInInvariantOrContravariantPositions() {
        return this.isContainedInInvariantOrContravariantPositions;
    }

    public final void recordInfoAboutTypeVariableUsagesAsInvariantOrContravariantParameter() {
        this.isContainedInInvariantOrContravariantPositions = true;
    }

    public String toString() {
        return Reflection.getOrCreateKotlinClass(ConeTypeVariableTypeConstructor.class).getSimpleName() + '(' + this.debugName + ')';
    }
}
