package org.jetbrains.kotlin.backend.common.lower;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¨\u0006\u0006"}, d2 = {"primaryConstructorParameterMap", "", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "Lorg/jetbrains/kotlin/backend/common/lower/InnerClassesSupport;", "originalConstructor", "Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "org.jetbrains.kotlin:ir.backend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class InnerClassesLoweringKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<IrValueParameter, IrValueParameter> primaryConstructorParameterMap(InnerClassesSupport innerClassesSupport, IrConstructor irConstructor) {
        HashMap map = new HashMap();
        IrConstructor innerClassConstructorWithOuterThisParameter = innerClassesSupport.getInnerClassConstructorWithOuterThisParameter(irConstructor);
        int i = 0;
        for (Object obj : irConstructor.getParameters()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            map.put((IrValueParameter) obj, innerClassConstructorWithOuterThisParameter.getParameters().get(i));
            i = i2;
        }
        innerClassConstructorWithOuterThisParameter.getParameters().size();
        irConstructor.getParameters().size();
        return map;
    }
}
