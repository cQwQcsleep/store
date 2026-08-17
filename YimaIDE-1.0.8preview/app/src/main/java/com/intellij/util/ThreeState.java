package com.intellij.util;

import androidx.collection.ScatterMapKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public enum ThreeState {
    YES,
    NO,
    UNSURE;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3 || i == 6) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[(i == 2 || i == 3 || i == 6) ? 3 : 2];
        if (i == 2) {
            objArr[0] = "other";
        } else if (i == 3 || i == 6) {
            objArr[0] = "states";
        } else {
            objArr[0] = "com/intellij/util/ThreeState";
        }
        switch (i) {
            case 1:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
                objArr[1] = "merge";
                break;
            case 2:
            case 3:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[1] = "com/intellij/util/ThreeState";
                break;
            case 4:
            case 5:
                objArr[1] = "mostPositive";
                break;
            default:
                objArr[1] = "fromBoolean";
                break;
        }
        if (i == 2) {
            objArr[2] = "isAtLeast";
        } else if (i == 3) {
            objArr[2] = "mostPositive";
        } else if (i == 6) {
            objArr[2] = "merge";
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3 && i != 6) {
            throw new IllegalStateException(str2);
        }
        throw new IllegalArgumentException(str2);
    }

    public static ThreeState fromBoolean(boolean z) {
        ThreeState threeState = z ? YES : NO;
        if (threeState == null) {
            $$$reportNull$$$0(0);
        }
        return threeState;
    }

    public boolean toBoolean() {
        if (this != UNSURE) {
            return this == YES;
        }
        k2d.a("Must be or YES, or NO");
        return false;
    }
}
