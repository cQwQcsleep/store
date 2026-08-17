package org.jetbrains.kotlin.cli.jvm.compiler;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCliJavaFileManagerImplKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a'\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0002¢\u0006\u0002\u0010\u0005\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\bH\u0002\u001a\u000e\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\bH\u0002¨\u0006\u000b"}, d2 = {"safely", "T", Argument.Delimiters.none, "compute", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "toSafeFqName", "Lorg/jetbrains/kotlin/name/FqName;", Argument.Delimiters.none, "toSafeTopLevelClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinCliJavaFileManagerImplKt {
    public static FqName a(String str) {
        return new FqName(str);
    }

    public static ClassId b(String str) {
        return ClassId.Companion.topLevel(new FqName(str));
    }

    private static final <T> T safely(Function0<? extends T> function0) {
        try {
            return (T) function0.invoke();
        } catch (AssertionError | IllegalArgumentException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FqName toSafeFqName(final String str) {
        return (FqName) safely(new Function0() { // from class: ab8
            public final Object invoke() {
                return KotlinCliJavaFileManagerImplKt.a(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ClassId toSafeTopLevelClassId(final String str) {
        return (ClassId) safely(new Function0() { // from class: bb8
            public final Object invoke() {
                return KotlinCliJavaFileManagerImplKt.b(str);
            }
        });
    }
}
