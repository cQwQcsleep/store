package org.jetbrains.kotlin.fir.expressions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Retention(RetentionPolicy.RUNTIME)
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000Ê\u0001\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005¨\u0006\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", Argument.Delimiters.none, "org.jetbrains.kotlin:tree", "Lkotlin/RequiresOptIn;", "message", "Accessing `coneTypeOrNull` hides potential bugs if at the given point all expression and their types should be resolved. Consider using `resolvedType` instead."}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public @interface UnresolvedExpressionTypeAccess {
}
