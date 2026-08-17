package androidx.compose.material3.carousel;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.pager.PagerScope;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CarouselKt$Carousel$2 implements Function4<PagerScope, Integer, Composer, Integer, Unit> {
    final /* synthetic */ Function4<CarouselItemScope, Integer, Composer, Integer, Unit> $content;
    final /* synthetic */ CarouselPageSize $pageSize;
    final /* synthetic */ CarouselState $state;

    /* JADX WARN: Multi-variable type inference failed */
    public CarouselKt$Carousel$2(CarouselState carouselState, CarouselPageSize carouselPageSize, Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4) {
        this.$state = carouselState;
        this.$pageSize = carouselPageSize;
        this.$content = function4;
    }

    public static Strategy a(CarouselPageSize carouselPageSize) {
        return carouselPageSize.getStrategy();
    }

    public final void invoke(PagerScope pagerScope, int i, Composer composer, int i2) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1817116752, i2, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:401)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new CarouselItemDrawInfoImpl();
            composer.updateRememberedValue(objRememberedValue);
        }
        final CarouselItemDrawInfoImpl carouselItemDrawInfoImpl = (CarouselItemDrawInfoImpl) objRememberedValue;
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new CarouselItemScopeImpl(carouselItemDrawInfoImpl);
            composer.updateRememberedValue(objRememberedValue2);
        }
        CarouselItemScopeImpl carouselItemScopeImpl = (CarouselItemScopeImpl) objRememberedValue2;
        Object objRememberedValue3 = composer.rememberedValue();
        if (objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$clipShape$1$1
                @Override // androidx.compose.ui.graphics.Shape
                /* JADX INFO: renamed from: createOutline-Pq9zytI */
                public Outline mo44createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                    return new Outline.Rectangle(carouselItemDrawInfoImpl.getMaskRect());
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        CarouselKt$Carousel$2$clipShape$1$1 carouselKt$Carousel$2$clipShape$1$1 = (CarouselKt$Carousel$2$clipShape$1$1) objRememberedValue3;
        Modifier.Companion companion2 = Modifier.INSTANCE;
        CarouselState carouselState = this.$state;
        boolean zChanged = composer.changed(this.$pageSize);
        final CarouselPageSize carouselPageSize = this.$pageSize;
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged || objRememberedValue4 == companion.getEmpty()) {
            objRememberedValue4 = new Function0() { // from class: androidx.compose.material3.carousel.a
                public final Object invoke() {
                    return CarouselKt$Carousel$2.a(carouselPageSize);
                }
            };
            composer.updateRememberedValue(objRememberedValue4);
        }
        Modifier modifierCarouselItem = CarouselKt.carouselItem(companion2, i, carouselState, (Function0) objRememberedValue4, carouselItemDrawInfoImpl, carouselKt$Carousel$2$clipShape$1$1);
        Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function4 = this.$content;
        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierCarouselItem);
        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
        Function0<ComposeUiNode> constructor = companion3.getConstructor();
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor);
        } else {
            composer.useNode();
        }
        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer);
        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion3.getSetCompositeKeyHash();
        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
        }
        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        function4.invoke(carouselItemScopeImpl, Integer.valueOf(i), composer, Integer.valueOf(i2 & 112));
        composer.endNode();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((PagerScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return Unit.INSTANCE;
    }
}
