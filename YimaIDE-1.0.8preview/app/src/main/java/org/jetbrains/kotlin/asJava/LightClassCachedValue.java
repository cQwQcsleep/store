package org.jetbrains.kotlin.asJava;

import com.intellij.openapi.util.ModificationTracker;
import kotlin.Metadata;
import org.jetbrains.kotlin.asJava.classes.KtLightClass;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0019\u0012\b\u0010\u0004\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/asJava/LightClassCachedValue;", "T", "Lorg/jetbrains/kotlin/asJava/classes/KtLightClass;", "", "value", "tracker", "Lcom/intellij/openapi/util/ModificationTracker;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/asJava/classes/KtLightClass;Lcom/intellij/openapi/util/ModificationTracker;)V", "getValue", "()Lorg/jetbrains/kotlin/asJava/classes/KtLightClass;", "Lorg/jetbrains/kotlin/asJava/classes/KtLightClass;", "getTracker", "()Lcom/intellij/openapi/util/ModificationTracker;", "org.jetbrains.kotlin:light-classes-base"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class LightClassCachedValue<T extends KtLightClass> {
    private final ModificationTracker tracker;
    private final T value;

    public LightClassCachedValue(T t, ModificationTracker modificationTracker) {
        modificationTracker.getClass();
        this.value = t;
        this.tracker = modificationTracker;
    }

    public final ModificationTracker getTracker() {
        return this.tracker;
    }

    public final T getValue() {
        return this.value;
    }
}
