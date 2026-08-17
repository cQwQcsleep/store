package org.jetbrains.kotlin.fir.java;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.JvmTarget;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/FirJvmTargetProvider;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "jvmTarget", "Lorg/jetbrains/kotlin/config/JvmTarget;", "<init>", "(Lorg/jetbrains/kotlin/config/JvmTarget;)V", "getJvmTarget", "()Lorg/jetbrains/kotlin/config/JvmTarget;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmTargetProvider implements FirSessionComponent {
    private final JvmTarget jvmTarget;

    public FirJvmTargetProvider(JvmTarget jvmTarget) {
        jvmTarget.getClass();
        this.jvmTarget = jvmTarget;
    }

    public final JvmTarget getJvmTarget() {
        return this.jvmTarget;
    }
}
