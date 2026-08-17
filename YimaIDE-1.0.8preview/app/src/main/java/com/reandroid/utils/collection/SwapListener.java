package com.reandroid.utils.collection;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface SwapListener {
    static SwapListener redirectTo(final Swappable swappable) {
        Objects.requireNonNull(swappable);
        return new SwapListener() { // from class: dvd
            @Override // com.reandroid.utils.collection.SwapListener
            public final void onSwap(int i, int i2) {
                swappable.swap(i, i2);
            }
        };
    }

    void onSwap(int i, int i2);
}
