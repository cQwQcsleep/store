package org.jetbrains.kotlin.fir.pipeline;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\b\u0087@\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\f\u0010\rJ\u0011\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0088\u0001\u0002Ê\u0001\u0002\b\u0017¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/pipeline/AllModulesFrontendOutput;", Argument.Delimiters.none, "outputs", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/pipeline/SingleModuleFrontendOutput;", "constructor-impl", "(Ljava/util/List;)Ljava/util/List;", "getOutputs", "()Ljava/util/List;", "equals", Argument.Delimiters.none, "other", "equals-impl", "(Ljava/util/List;Ljava/lang/Object;)Z", "hashCode", Argument.Delimiters.none, "hashCode-impl", "(Ljava/util/List;)I", "toString", Argument.Delimiters.none, "toString-impl", "(Ljava/util/List;)Ljava/lang/String;", "org.jetbrains.kotlin:entrypoint", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
@JvmInline
public final class AllModulesFrontendOutput {
    private final List<SingleModuleFrontendOutput> outputs;

    private /* synthetic */ AllModulesFrontendOutput(List list) {
        this.outputs = list;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ AllModulesFrontendOutput m572boximpl(List list) {
        return new AllModulesFrontendOutput(list);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static List<? extends SingleModuleFrontendOutput> m573constructorimpl(List<SingleModuleFrontendOutput> list) {
        list.getClass();
        return list;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m574equalsimpl(List<? extends SingleModuleFrontendOutput> list, Object obj) {
        return (obj instanceof AllModulesFrontendOutput) && Intrinsics.areEqual(list, ((AllModulesFrontendOutput) obj).getOutputs());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m575equalsimpl0(List<? extends SingleModuleFrontendOutput> list, List<? extends SingleModuleFrontendOutput> list2) {
        return Intrinsics.areEqual(list, list2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m576hashCodeimpl(List<? extends SingleModuleFrontendOutput> list) {
        return list.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m577toStringimpl(List<? extends SingleModuleFrontendOutput> list) {
        return "AllModulesFrontendOutput(outputs=" + list + ')';
    }

    public boolean equals(Object obj) {
        return m574equalsimpl(this.outputs, obj);
    }

    public final List<SingleModuleFrontendOutput> getOutputs() {
        return this.outputs;
    }

    public int hashCode() {
        return m576hashCodeimpl(this.outputs);
    }

    public String toString() {
        return m577toStringimpl(this.outputs);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ List getOutputs() {
        return this.outputs;
    }
}
