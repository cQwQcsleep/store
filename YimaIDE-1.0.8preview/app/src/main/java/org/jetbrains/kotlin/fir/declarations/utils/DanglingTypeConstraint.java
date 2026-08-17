package org.jetbrains.kotlin.fir.declarations.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/utils/DanglingTypeConstraint;", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/KtSourceElement;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class DanglingTypeConstraint {
    private final Name name;
    private final KtSourceElement source;

    public DanglingTypeConstraint(Name name, KtSourceElement ktSourceElement) {
        name.getClass();
        ktSourceElement.getClass();
        this.name = name;
        this.source = ktSourceElement;
    }

    public static /* synthetic */ DanglingTypeConstraint copy$default(DanglingTypeConstraint danglingTypeConstraint, Name name, KtSourceElement ktSourceElement, int i, Object obj) {
        if ((i & 1) != 0) {
            name = danglingTypeConstraint.name;
        }
        if ((i & 2) != 0) {
            ktSourceElement = danglingTypeConstraint.source;
        }
        return danglingTypeConstraint.copy(name, ktSourceElement);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Name getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final KtSourceElement getSource() {
        return this.source;
    }

    public final DanglingTypeConstraint copy(Name name, KtSourceElement source) {
        name.getClass();
        source.getClass();
        return new DanglingTypeConstraint(name, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DanglingTypeConstraint)) {
            return false;
        }
        DanglingTypeConstraint danglingTypeConstraint = (DanglingTypeConstraint) other;
        return Intrinsics.areEqual(this.name, danglingTypeConstraint.name) && Intrinsics.areEqual(this.source, danglingTypeConstraint.source);
    }

    public final Name getName() {
        return this.name;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.source.hashCode();
    }

    public String toString() {
        return "DanglingTypeConstraint(name=" + this.name + ", source=" + this.source + ')';
    }
}
