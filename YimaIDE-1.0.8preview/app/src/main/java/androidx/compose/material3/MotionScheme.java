package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.material3.tokens.ExpressiveMotionTokens;
import androidx.compose.material3.tokens.StandardMotionTokens;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\ba\u0018\u0000 \n2\u00020\u0001:\u0003\n\u000b\fJ\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004H&J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004H&J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004H&J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004H&J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004H&J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/compose/material3/MotionScheme;", "", "defaultSpatialSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "T", "fastSpatialSpec", "slowSpatialSpec", "defaultEffectsSpec", "fastEffectsSpec", "slowEffectsSpec", "Companion", "StandardMotionSchemeImpl", "ExpressiveMotionSchemeImpl", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface MotionScheme {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0006J\r\u0010\u0007\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\b¨\u0006\t"}, d2 = {"Landroidx/compose/material3/MotionScheme$Companion;", "", "<init>", "()V", "standard", "Landroidx/compose/material3/MotionScheme;", "standard$material3", "expressive", "expressive$material3", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final MotionScheme expressive$material3() {
            return ExpressiveMotionSchemeImpl.INSTANCE;
        }

        public final MotionScheme standard$material3() {
            return StandardMotionSchemeImpl.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\rH\u0016J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\rH\u0016J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\rH\u0016J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\rH\u0016J\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\rH\u0016J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\rH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/compose/material3/MotionScheme$ExpressiveMotionSchemeImpl;", "Landroidx/compose/material3/MotionScheme;", "<init>", "()V", "defaultSpatialSpec", "Landroidx/compose/animation/core/SpringSpec;", "", "fastSpatialSpec", "slowSpatialSpec", "defaultEffectsSpec", "fastEffectsSpec", "slowEffectsSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "T", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class ExpressiveMotionSchemeImpl implements MotionScheme {
        public static final ExpressiveMotionSchemeImpl INSTANCE = new ExpressiveMotionSchemeImpl();
        private static final SpringSpec<Object> defaultEffectsSpec;
        private static final SpringSpec<Object> defaultSpatialSpec;
        private static final SpringSpec<Object> fastEffectsSpec;
        private static final SpringSpec<Object> fastSpatialSpec;
        private static final SpringSpec<Object> slowEffectsSpec;
        private static final SpringSpec<Object> slowSpatialSpec;

        static {
            ExpressiveMotionTokens expressiveMotionTokens = ExpressiveMotionTokens.INSTANCE;
            defaultSpatialSpec = AnimationSpecKt.spring$default(expressiveMotionTokens.getSpringDefaultSpatialDamping(), expressiveMotionTokens.getSpringDefaultSpatialStiffness(), (Object) null, 4, (Object) null);
            fastSpatialSpec = AnimationSpecKt.spring$default(expressiveMotionTokens.getSpringFastSpatialDamping(), expressiveMotionTokens.getSpringFastSpatialStiffness(), (Object) null, 4, (Object) null);
            slowSpatialSpec = AnimationSpecKt.spring$default(expressiveMotionTokens.getSpringSlowSpatialDamping(), expressiveMotionTokens.getSpringSlowSpatialStiffness(), (Object) null, 4, (Object) null);
            defaultEffectsSpec = AnimationSpecKt.spring$default(expressiveMotionTokens.getSpringDefaultEffectsDamping(), expressiveMotionTokens.getSpringDefaultEffectsStiffness(), (Object) null, 4, (Object) null);
            fastEffectsSpec = AnimationSpecKt.spring$default(expressiveMotionTokens.getSpringFastEffectsDamping(), expressiveMotionTokens.getSpringFastEffectsStiffness(), (Object) null, 4, (Object) null);
            slowEffectsSpec = AnimationSpecKt.spring$default(expressiveMotionTokens.getSpringSlowEffectsDamping(), expressiveMotionTokens.getSpringSlowEffectsStiffness(), (Object) null, 4, (Object) null);
        }

        private ExpressiveMotionSchemeImpl() {
        }

        @Override // androidx.compose.material3.MotionScheme
        public <T> FiniteAnimationSpec<T> defaultEffectsSpec() {
            SpringSpec<Object> springSpec = defaultEffectsSpec;
            springSpec.getClass();
            return springSpec;
        }

        @Override // androidx.compose.material3.MotionScheme
        public <T> FiniteAnimationSpec<T> defaultSpatialSpec() {
            SpringSpec<Object> springSpec = defaultSpatialSpec;
            springSpec.getClass();
            return springSpec;
        }

        @Override // androidx.compose.material3.MotionScheme
        public <T> FiniteAnimationSpec<T> fastEffectsSpec() {
            SpringSpec<Object> springSpec = fastEffectsSpec;
            springSpec.getClass();
            return springSpec;
        }

        @Override // androidx.compose.material3.MotionScheme
        public <T> FiniteAnimationSpec<T> fastSpatialSpec() {
            SpringSpec<Object> springSpec = fastSpatialSpec;
            springSpec.getClass();
            return springSpec;
        }

        @Override // androidx.compose.material3.MotionScheme
        public <T> FiniteAnimationSpec<T> slowEffectsSpec() {
            SpringSpec<Object> springSpec = slowEffectsSpec;
            springSpec.getClass();
            return springSpec;
        }

        @Override // androidx.compose.material3.MotionScheme
        public <T> FiniteAnimationSpec<T> slowSpatialSpec() {
            SpringSpec<Object> springSpec = slowSpatialSpec;
            springSpec.getClass();
            return springSpec;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\rH\u0016J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\rH\u0016J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\rH\u0016J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\rH\u0016J\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\rH\u0016J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\rH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/compose/material3/MotionScheme$StandardMotionSchemeImpl;", "Landroidx/compose/material3/MotionScheme;", "<init>", "()V", "defaultSpatialSpec", "Landroidx/compose/animation/core/SpringSpec;", "", "fastSpatialSpec", "slowSpatialSpec", "defaultEffectsSpec", "fastEffectsSpec", "slowEffectsSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "T", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class StandardMotionSchemeImpl implements MotionScheme {
        public static final StandardMotionSchemeImpl INSTANCE = new StandardMotionSchemeImpl();
        private static final SpringSpec<Object> defaultEffectsSpec;
        private static final SpringSpec<Object> defaultSpatialSpec;
        private static final SpringSpec<Object> fastEffectsSpec;
        private static final SpringSpec<Object> fastSpatialSpec;
        private static final SpringSpec<Object> slowEffectsSpec;
        private static final SpringSpec<Object> slowSpatialSpec;

        static {
            StandardMotionTokens standardMotionTokens = StandardMotionTokens.INSTANCE;
            defaultSpatialSpec = AnimationSpecKt.spring$default(standardMotionTokens.getSpringDefaultSpatialDamping(), standardMotionTokens.getSpringDefaultSpatialStiffness(), (Object) null, 4, (Object) null);
            fastSpatialSpec = AnimationSpecKt.spring$default(standardMotionTokens.getSpringFastSpatialDamping(), standardMotionTokens.getSpringFastSpatialStiffness(), (Object) null, 4, (Object) null);
            slowSpatialSpec = AnimationSpecKt.spring$default(standardMotionTokens.getSpringSlowSpatialDamping(), standardMotionTokens.getSpringSlowSpatialStiffness(), (Object) null, 4, (Object) null);
            defaultEffectsSpec = AnimationSpecKt.spring$default(standardMotionTokens.getSpringDefaultEffectsDamping(), standardMotionTokens.getSpringDefaultEffectsStiffness(), (Object) null, 4, (Object) null);
            fastEffectsSpec = AnimationSpecKt.spring$default(standardMotionTokens.getSpringFastEffectsDamping(), standardMotionTokens.getSpringFastEffectsStiffness(), (Object) null, 4, (Object) null);
            slowEffectsSpec = AnimationSpecKt.spring$default(standardMotionTokens.getSpringSlowEffectsDamping(), standardMotionTokens.getSpringSlowEffectsStiffness(), (Object) null, 4, (Object) null);
        }

        private StandardMotionSchemeImpl() {
        }

        @Override // androidx.compose.material3.MotionScheme
        public <T> FiniteAnimationSpec<T> defaultEffectsSpec() {
            SpringSpec<Object> springSpec = defaultEffectsSpec;
            springSpec.getClass();
            return springSpec;
        }

        @Override // androidx.compose.material3.MotionScheme
        public <T> FiniteAnimationSpec<T> defaultSpatialSpec() {
            SpringSpec<Object> springSpec = defaultSpatialSpec;
            springSpec.getClass();
            return springSpec;
        }

        @Override // androidx.compose.material3.MotionScheme
        public <T> FiniteAnimationSpec<T> fastEffectsSpec() {
            SpringSpec<Object> springSpec = fastEffectsSpec;
            springSpec.getClass();
            return springSpec;
        }

        @Override // androidx.compose.material3.MotionScheme
        public <T> FiniteAnimationSpec<T> fastSpatialSpec() {
            SpringSpec<Object> springSpec = fastSpatialSpec;
            springSpec.getClass();
            return springSpec;
        }

        @Override // androidx.compose.material3.MotionScheme
        public <T> FiniteAnimationSpec<T> slowEffectsSpec() {
            SpringSpec<Object> springSpec = slowEffectsSpec;
            springSpec.getClass();
            return springSpec;
        }

        @Override // androidx.compose.material3.MotionScheme
        public <T> FiniteAnimationSpec<T> slowSpatialSpec() {
            SpringSpec<Object> springSpec = slowSpatialSpec;
            springSpec.getClass();
            return springSpec;
        }
    }

    <T> FiniteAnimationSpec<T> defaultEffectsSpec();

    <T> FiniteAnimationSpec<T> defaultSpatialSpec();

    <T> FiniteAnimationSpec<T> fastEffectsSpec();

    <T> FiniteAnimationSpec<T> fastSpatialSpec();

    <T> FiniteAnimationSpec<T> slowEffectsSpec();

    <T> FiniteAnimationSpec<T> slowSpatialSpec();
}
