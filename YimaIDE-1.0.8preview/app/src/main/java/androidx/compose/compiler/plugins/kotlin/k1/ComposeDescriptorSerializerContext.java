package androidx.compose.compiler.plugins.kotlin.k1;

import androidx.compose.compiler.plugins.kotlin.lower.ClassStabilityInferredCollection;
import androidx.compose.compiler.plugins.kotlin.lower.hiddenfromobjc.HideFromObjCDeclarationsSet;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k1/ComposeDescriptorSerializerContext;", "", "hideFromObjCDeclarationsSet", "Landroidx/compose/compiler/plugins/kotlin/lower/hiddenfromobjc/HideFromObjCDeclarationsSet;", "classStabilityInferredCollection", "Landroidx/compose/compiler/plugins/kotlin/lower/ClassStabilityInferredCollection;", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/lower/hiddenfromobjc/HideFromObjCDeclarationsSet;Landroidx/compose/compiler/plugins/kotlin/lower/ClassStabilityInferredCollection;)V", "getHideFromObjCDeclarationsSet", "()Landroidx/compose/compiler/plugins/kotlin/lower/hiddenfromobjc/HideFromObjCDeclarationsSet;", "getClassStabilityInferredCollection", "()Landroidx/compose/compiler/plugins/kotlin/lower/ClassStabilityInferredCollection;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeDescriptorSerializerContext {
    private final ClassStabilityInferredCollection classStabilityInferredCollection;
    private final HideFromObjCDeclarationsSet hideFromObjCDeclarationsSet;

    public /* synthetic */ ComposeDescriptorSerializerContext(HideFromObjCDeclarationsSet hideFromObjCDeclarationsSet, ClassStabilityInferredCollection classStabilityInferredCollection, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new HideFromObjCDeclarationsSet() : hideFromObjCDeclarationsSet, (i & 2) != 0 ? new ClassStabilityInferredCollection() : classStabilityInferredCollection);
    }

    public final ClassStabilityInferredCollection getClassStabilityInferredCollection() {
        return this.classStabilityInferredCollection;
    }

    public final HideFromObjCDeclarationsSet getHideFromObjCDeclarationsSet() {
        return this.hideFromObjCDeclarationsSet;
    }

    public ComposeDescriptorSerializerContext(HideFromObjCDeclarationsSet hideFromObjCDeclarationsSet, ClassStabilityInferredCollection classStabilityInferredCollection) {
        hideFromObjCDeclarationsSet.getClass();
        classStabilityInferredCollection.getClass();
        this.hideFromObjCDeclarationsSet = hideFromObjCDeclarationsSet;
        this.classStabilityInferredCollection = classStabilityInferredCollection;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ComposeDescriptorSerializerContext() {
        HideFromObjCDeclarationsSet hideFromObjCDeclarationsSet = null;
        this(hideFromObjCDeclarationsSet, hideFromObjCDeclarationsSet, 3, hideFromObjCDeclarationsSet);
    }
}
