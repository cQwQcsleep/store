package org.jetbrains.kotlin.backend.jvm.lower;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class AdditionalClassAnnotationLowering$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

    static {
        int[] iArr = new int[KotlinTarget.values().length];
        try {
            iArr[KotlinTarget.TYPE_PARAMETER.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[KotlinTarget.TYPE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        $EnumSwitchMapping$0 = iArr;
    }
}
