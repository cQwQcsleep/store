package org.jetbrains.kotlin.codegen.inline;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\"\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0000J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0000J\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0006J\u000e\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0006J\u0016\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0006J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u001aJ\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u001cR\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/InlineResult;", Argument.Delimiters.none, "<init>", "()V", "notChangedTypes", "Ljava/util/HashSet;", Argument.Delimiters.none, "Lkotlin/collections/HashSet;", "classesToRemove", "changedTypes", "Ljava/util/HashMap;", "reifiedTypeParametersUsages", "Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeParametersUsages;", "getReifiedTypeParametersUsages", "()Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeParametersUsages;", "merge", Argument.Delimiters.none, "child", "mergeWithNotChangeInfo", "addClassToRemove", "classInternalName", "addNotChangedClass", "addChangedType", "oldClassInternalName", "newClassInternalName", "calcClassesToRemove", Argument.Delimiters.none, "getChangedTypes", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InlineResult {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final HashMap<String, String> changedTypes;
    private final HashSet<String> classesToRemove;
    private final HashSet<String> notChangedTypes;
    private final ReifiedTypeParametersUsages reifiedTypeParametersUsages;

    private InlineResult() {
        this.notChangedTypes = new HashSet<>();
        this.classesToRemove = new HashSet<>();
        this.changedTypes = new HashMap<>();
        this.reifiedTypeParametersUsages = new ReifiedTypeParametersUsages();
    }

    @JvmStatic
    public static final InlineResult create() {
        return INSTANCE.create();
    }

    public final void addChangedType(String oldClassInternalName, String newClassInternalName) {
        oldClassInternalName.getClass();
        newClassInternalName.getClass();
        this.changedTypes.put(oldClassInternalName, newClassInternalName);
    }

    public final void addClassToRemove(String classInternalName) {
        classInternalName.getClass();
        this.classesToRemove.add(classInternalName);
    }

    public final void addNotChangedClass(String classInternalName) {
        classInternalName.getClass();
        this.notChangedTypes.add(classInternalName);
    }

    public final Set<String> calcClassesToRemove() {
        return SetsKt.minus(this.classesToRemove, this.notChangedTypes);
    }

    public final Map<String, String> getChangedTypes() {
        return this.changedTypes;
    }

    public final ReifiedTypeParametersUsages getReifiedTypeParametersUsages() {
        return this.reifiedTypeParametersUsages;
    }

    public final void merge(InlineResult child) {
        child.getClass();
        this.changedTypes.putAll(child.changedTypes);
        this.classesToRemove.addAll(child.calcClassesToRemove());
    }

    public final void mergeWithNotChangeInfo(InlineResult child) {
        child.getClass();
        this.notChangedTypes.addAll(child.notChangedTypes);
        merge(child);
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00020\u0005H\u0007b\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/InlineResult$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/codegen/inline/InlineResult;", "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final InlineResult create() {
            return new InlineResult(null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ InlineResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
