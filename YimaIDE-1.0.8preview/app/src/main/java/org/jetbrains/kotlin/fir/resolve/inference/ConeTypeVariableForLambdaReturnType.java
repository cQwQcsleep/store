package org.jetbrains.kotlin.fir.resolve.inference;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.types.ConeTypeVariable;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/ConeTypeVariableForLambdaReturnType;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;", "argument", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", ModuleXmlParser.NAME, Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;Ljava/lang/String;)V", "getArgument", "()Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeVariableForLambdaReturnType extends ConeTypeVariable {
    private final FirAnonymousFunction argument;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeTypeVariableForLambdaReturnType(FirAnonymousFunction firAnonymousFunction, String str) {
        super(str, null, 2, null);
        firAnonymousFunction.getClass();
        str.getClass();
        this.argument = firAnonymousFunction;
    }

    public final FirAnonymousFunction getArgument() {
        return this.argument;
    }
}
