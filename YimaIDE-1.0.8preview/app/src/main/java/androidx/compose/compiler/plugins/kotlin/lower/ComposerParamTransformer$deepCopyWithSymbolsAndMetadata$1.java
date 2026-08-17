package androidx.compose.compiler.plugins.kotlin.lower;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.ir.util.DeepCopyTypeRemapper;
import org.jetbrains.kotlin.ir.util.ReferencedSymbolRemapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 176)
public final /* synthetic */ class ComposerParamTransformer$deepCopyWithSymbolsAndMetadata$1 extends FunctionReferenceImpl implements Function1<ReferencedSymbolRemapper, DeepCopyTypeRemapper> {
    public static final ComposerParamTransformer$deepCopyWithSymbolsAndMetadata$1 INSTANCE = new ComposerParamTransformer$deepCopyWithSymbolsAndMetadata$1();

    public ComposerParamTransformer$deepCopyWithSymbolsAndMetadata$1() {
        super(1, DeepCopyTypeRemapper.class, "<init>", "<init>(Lorg/jetbrains/kotlin/ir/util/ReferencedSymbolRemapper;)V", 0);
    }

    public final DeepCopyTypeRemapper invoke(ReferencedSymbolRemapper referencedSymbolRemapper) {
        referencedSymbolRemapper.getClass();
        return new DeepCopyTypeRemapper(referencedSymbolRemapper);
    }
}
