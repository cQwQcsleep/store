package org.jetbrains.kotlin.fir.renderer;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirNoClassMemberRenderer;", "Lorg/jetbrains/kotlin/fir/renderer/FirClassMemberRenderer;", "<init>", "()V", "render", Argument.Delimiters.none, "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "declarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNoClassMemberRenderer extends FirClassMemberRenderer {
    @Override // org.jetbrains.kotlin.fir.renderer.FirClassMemberRenderer
    public void render(List<? extends FirDeclaration> declarations) {
        declarations.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirClassMemberRenderer
    public void render(FirRegularClass regularClass) {
        regularClass.getClass();
    }
}
