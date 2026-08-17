package org.jetbrains.kotlin.resolve;

import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B5\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0097\u0082\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/resolve/AnnotationTargetList;", "", "defaultTargets", "", "Lorg/jetbrains/kotlin/descriptors/annotations/KotlinTarget;", "canBeSubstituted", "onlyWithUseSiteTarget", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getDefaultTargets", "()Ljava/util/List;", "getCanBeSubstituted", "getOnlyWithUseSiteTarget", "equals", "", "other", "hashCode", "", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class AnnotationTargetList {
    private final List<KotlinTarget> canBeSubstituted;
    private final List<KotlinTarget> defaultTargets;
    private final List<KotlinTarget> onlyWithUseSiteTarget;

    public /* synthetic */ AnnotationTargetList(List list, List list2, List list3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2, (i & 4) != 0 ? CollectionsKt.emptyList() : list3);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "These lists are not intended to compare them")
    public boolean equals(Object other) {
        return this == other;
    }

    public final List<KotlinTarget> getCanBeSubstituted() {
        return this.canBeSubstituted;
    }

    public final List<KotlinTarget> getDefaultTargets() {
        return this.defaultTargets;
    }

    public final List<KotlinTarget> getOnlyWithUseSiteTarget() {
        return this.onlyWithUseSiteTarget;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public AnnotationTargetList(List<? extends KotlinTarget> list, List<? extends KotlinTarget> list2, List<? extends KotlinTarget> list3) {
        list.getClass();
        list2.getClass();
        list3.getClass();
        this.defaultTargets = list;
        this.canBeSubstituted = list2;
        this.onlyWithUseSiteTarget = list3;
    }
}
