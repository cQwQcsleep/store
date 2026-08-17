package org.jetbrains.kotlin.types.expressions;

import kotlin.Metadata;
import org.jetbrains.kotlin.container.DefaultImplementation;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@DefaultImplementation(impl = Disabled.class)
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bg\u0018\u00002\u00020\u0001:\u0002\u0005\u0006R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004Ê\u0001\f\b\b\u0012\b\b\t\u0012\u0004\b\t0\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/types/expressions/GenericArrayClassLiteralSupport;", "", "isEnabled", "", "()Z", "Enabled", "Disabled", "org.jetbrains.kotlin:frontend", "Lorg/jetbrains/kotlin/container/DefaultImplementation;", "impl", "Lorg/jetbrains/kotlin/types/expressions/GenericArrayClassLiteralSupport$Disabled;"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface GenericArrayClassLiteralSupport {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/types/expressions/GenericArrayClassLiteralSupport$Disabled;", "Lorg/jetbrains/kotlin/types/expressions/GenericArrayClassLiteralSupport;", "<init>", "()V", "isEnabled", "", "()Z", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Disabled implements GenericArrayClassLiteralSupport {
        public static final Disabled INSTANCE = new Disabled();
        private static final boolean isEnabled = false;

        private Disabled() {
        }

        @Override // org.jetbrains.kotlin.types.expressions.GenericArrayClassLiteralSupport
        public boolean isEnabled() {
            return isEnabled;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/types/expressions/GenericArrayClassLiteralSupport$Enabled;", "Lorg/jetbrains/kotlin/types/expressions/GenericArrayClassLiteralSupport;", "<init>", "()V", "isEnabled", "", "()Z", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Enabled implements GenericArrayClassLiteralSupport {
        public static final Enabled INSTANCE = new Enabled();
        private static final boolean isEnabled = true;

        private Enabled() {
        }

        @Override // org.jetbrains.kotlin.types.expressions.GenericArrayClassLiteralSupport
        public boolean isEnabled() {
            return isEnabled;
        }
    }

    boolean isEnabled();
}
