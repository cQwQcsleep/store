package org.jetbrains.kotlin.incremental.classpathDiff.impl;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b`\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/ClassMultiHashProvider;", "", "searchAndGetFullAbiHashOfUsedClasses", "", "rootClasses", "", "Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;", "initialPrefix", "", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface ClassMultiHashProvider {
    long searchAndGetFullAbiHashOfUsedClasses(Set<? extends JvmClassName> rootClasses, String initialPrefix);
}
