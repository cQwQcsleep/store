package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.application.AccessToken;
import com.intellij.util.containers.FList;
import com.intellij.util.ui.EDT;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class SlowOperations {
    private static int ourAlwaysAllow = -1;
    private static FList<String> ourStack = FList.emptyList();
    private static final Set<String> ourReportedClasses = new HashSet();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 5 || i == 8) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 5 || i == 8) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "computable";
                break;
            case 2:
                objArr[0] = "runnable";
                break;
            case 3:
                objArr[0] = "ytIssueId";
                break;
            case 4:
                objArr[0] = "target";
                break;
            case 5:
            case 8:
                objArr[0] = "com/intellij/util/SlowOperations";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "activityName";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            default:
                objArr[0] = "sectionName";
                break;
        }
        if (i == 5) {
            objArr[1] = "reportOnceIfViolatedFor";
        } else if (i != 8) {
            objArr[1] = "com/intellij/util/SlowOperations";
        } else {
            objArr[1] = "startSection";
        }
        switch (i) {
            case 1:
            case 2:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "allowSlowOperations";
                break;
            case 3:
                objArr[2] = "knownIssue";
                break;
            case 4:
                objArr[2] = "reportOnceIfViolatedFor";
                break;
            case 5:
            case 8:
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "startSection";
                break;
            default:
                objArr[2] = "isInSection";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 5 && i != 8) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static AccessToken knownIssue(String str) {
        if (str == null) {
            $$$reportNull$$$0(3);
        }
        return startSection("known-issues");
    }

    public static AccessToken startSection(String str) {
        if (str == null) {
            $$$reportNull$$$0(7);
        }
        if (EDT.isCurrentThreadEdt()) {
            final FList<String> fList = ourStack;
            ourStack = fList.prepend(str);
            return new AccessToken() { // from class: com.intellij.util.SlowOperations.2
                public void finish() {
                    FList unused = SlowOperations.ourStack = fList;
                }
            };
        }
        AccessToken accessToken = AccessToken.EMPTY_ACCESS_TOKEN;
        if (accessToken == null) {
            $$$reportNull$$$0(8);
        }
        return accessToken;
    }
}
