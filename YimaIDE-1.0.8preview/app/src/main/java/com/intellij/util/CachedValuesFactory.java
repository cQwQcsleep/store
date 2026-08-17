package com.intellij.util;

import com.intellij.openapi.util.UserDataHolder;
import com.intellij.psi.util.CachedValue;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.ParameterizedCachedValue;
import com.intellij.psi.util.ParameterizedCachedValueProvider;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00062\u0006\u0010\u0007\u001a\u00020\bH&J2\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\u0006\u0010\t\u001a\u00020\n2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00062\u0006\u0010\u0007\u001a\u00020\bH&J<\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\r2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\r0\u000e2\u0006\u0010\u0007\u001a\u00020\bH&JD\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\r2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\r0\u000e2\u0006\u0010\u0007\u001a\u00020\bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lcom/intellij/util/CachedValuesFactory;", "", "createCachedValue", "Lcom/intellij/psi/util/CachedValue;", "T", "provider", "Lcom/intellij/psi/util/CachedValueProvider;", "trackValue", "", "userDataHolder", "Lcom/intellij/openapi/util/UserDataHolder;", "createParameterizedCachedValue", "Lcom/intellij/psi/util/ParameterizedCachedValue;", "P", "Lcom/intellij/psi/util/ParameterizedCachedValueProvider;", "intellij.platform.core.impl"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface CachedValuesFactory {
    <T> CachedValue<T> createCachedValue(UserDataHolder userDataHolder, CachedValueProvider<T> provider, boolean trackValue);

    <T> CachedValue<T> createCachedValue(CachedValueProvider<T> provider, boolean trackValue);

    <T, P> ParameterizedCachedValue<T, P> createParameterizedCachedValue(UserDataHolder userDataHolder, ParameterizedCachedValueProvider<T, P> provider, boolean trackValue);

    <T, P> ParameterizedCachedValue<T, P> createParameterizedCachedValue(ParameterizedCachedValueProvider<T, P> provider, boolean trackValue);
}
