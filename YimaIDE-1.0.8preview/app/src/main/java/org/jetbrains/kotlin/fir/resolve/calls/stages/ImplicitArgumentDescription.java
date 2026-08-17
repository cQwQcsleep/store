package org.jetbrains.kotlin.fir.resolve.calls.stages;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ImplicitArgumentDescription;", Argument.Delimiters.none, "atom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getAtom", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "getType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final /* data */ class ImplicitArgumentDescription {
    private final ConeResolutionAtom atom;
    private final ConeKotlinType type;

    public ImplicitArgumentDescription(ConeResolutionAtom coneResolutionAtom, ConeKotlinType coneKotlinType) {
        coneResolutionAtom.getClass();
        coneKotlinType.getClass();
        this.atom = coneResolutionAtom;
        this.type = coneKotlinType;
    }

    public static /* synthetic */ ImplicitArgumentDescription copy$default(ImplicitArgumentDescription implicitArgumentDescription, ConeResolutionAtom coneResolutionAtom, ConeKotlinType coneKotlinType, int i, Object obj) {
        if ((i & 1) != 0) {
            coneResolutionAtom = implicitArgumentDescription.atom;
        }
        if ((i & 2) != 0) {
            coneKotlinType = implicitArgumentDescription.type;
        }
        return implicitArgumentDescription.copy(coneResolutionAtom, coneKotlinType);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ConeResolutionAtom getAtom() {
        return this.atom;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ConeKotlinType getType() {
        return this.type;
    }

    public final ImplicitArgumentDescription copy(ConeResolutionAtom atom, ConeKotlinType type) {
        atom.getClass();
        type.getClass();
        return new ImplicitArgumentDescription(atom, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImplicitArgumentDescription)) {
            return false;
        }
        ImplicitArgumentDescription implicitArgumentDescription = (ImplicitArgumentDescription) other;
        return Intrinsics.areEqual(this.atom, implicitArgumentDescription.atom) && Intrinsics.areEqual(this.type, implicitArgumentDescription.type);
    }

    public final ConeResolutionAtom getAtom() {
        return this.atom;
    }

    public final ConeKotlinType getType() {
        return this.type;
    }

    public int hashCode() {
        return (this.atom.hashCode() * 31) + this.type.hashCode();
    }

    public String toString() {
        return "ImplicitArgumentDescription(atom=" + this.atom + ", type=" + this.type + ')';
    }
}
