package androidx.core.view.inputmethod;

import android.os.Build;
import android.os.PersistableBundle;
import android.view.inputmethod.TextAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class TextAttributeCompat {
    private final TextAttributeCompatImpl mImpl;

    public static final class Builder {
        private List<String> mTextConversionSuggestions = new ArrayList();
        private PersistableBundle mExtras = new PersistableBundle();
        private boolean mTextSuggestionSelected = false;

        public TextAttributeCompat build() {
            return new TextAttributeCompat(this.mTextConversionSuggestions, this.mExtras, this.mTextSuggestionSelected);
        }

        public Builder setExtras(PersistableBundle persistableBundle) {
            this.mExtras = persistableBundle;
            return this;
        }

        public Builder setTextConversionSuggestions(List<String> list) {
            this.mTextConversionSuggestions = list;
            return this;
        }

        public Builder setTextSuggestionSelected(boolean z) {
            this.mTextSuggestionSelected = z;
            return this;
        }
    }

    public static final class TextAttributeCompatBaseImpl implements TextAttributeCompatImpl {
        private final PersistableBundle mExtras;
        private final List<String> mTextConversionSuggestions;
        private final boolean mTextSuggestionSelected;

        public TextAttributeCompatBaseImpl(List<String> list, PersistableBundle persistableBundle, boolean z) {
            this.mTextConversionSuggestions = Collections.unmodifiableList(list);
            this.mExtras = persistableBundle;
            this.mTextSuggestionSelected = z;
        }

        @Override // androidx.core.view.inputmethod.TextAttributeCompat.TextAttributeCompatImpl
        public PersistableBundle getExtras() {
            return this.mExtras;
        }

        @Override // androidx.core.view.inputmethod.TextAttributeCompat.TextAttributeCompatImpl
        public Object getTextAttribute() {
            return null;
        }

        @Override // androidx.core.view.inputmethod.TextAttributeCompat.TextAttributeCompatImpl
        public List<String> getTextConversionSuggestions() {
            return this.mTextConversionSuggestions;
        }

        @Override // androidx.core.view.inputmethod.TextAttributeCompat.TextAttributeCompatImpl
        public boolean isTextSuggestionSelected() {
            return this.mTextSuggestionSelected;
        }
    }

    public interface TextAttributeCompatImpl {
        PersistableBundle getExtras();

        Object getTextAttribute();

        List<String> getTextConversionSuggestions();

        boolean isTextSuggestionSelected();
    }

    private TextAttributeCompat(List<String> list, PersistableBundle persistableBundle, boolean z) {
        if (Build.VERSION.SDK_INT >= 37) {
            this.mImpl = new TextAttributeCompatApi37Impl(list, persistableBundle, z);
        } else {
            this.mImpl = new TextAttributeCompatApi33Impl(list, persistableBundle, z);
        }
    }

    public static TextAttributeCompat wrap(Object obj) {
        if (obj == null) {
            return null;
        }
        return Build.VERSION.SDK_INT >= 37 ? new TextAttributeCompat(new TextAttributeCompatApi37Impl(obj)) : new TextAttributeCompat(new TextAttributeCompatApi33Impl(obj));
    }

    public PersistableBundle getExtras() {
        return this.mImpl.getExtras();
    }

    public List<String> getTextConversionSuggestions() {
        return this.mImpl.getTextConversionSuggestions();
    }

    public boolean isTextSuggestionSelected() {
        return this.mImpl.isTextSuggestionSelected();
    }

    public Object unwrap() {
        return this.mImpl.getTextAttribute();
    }

    public static final class TextAttributeCompatApi33Impl implements TextAttributeCompatImpl {
        final TextAttribute mObject;
        private final boolean mTextSuggestionSelected;

        public TextAttributeCompatApi33Impl(List<String> list, PersistableBundle persistableBundle, boolean z) {
            this.mObject = new TextAttribute.Builder().setTextConversionSuggestions(list).setExtras(persistableBundle).build();
            this.mTextSuggestionSelected = z;
        }

        @Override // androidx.core.view.inputmethod.TextAttributeCompat.TextAttributeCompatImpl
        public PersistableBundle getExtras() {
            return this.mObject.getExtras();
        }

        @Override // androidx.core.view.inputmethod.TextAttributeCompat.TextAttributeCompatImpl
        public Object getTextAttribute() {
            return this.mObject;
        }

        @Override // androidx.core.view.inputmethod.TextAttributeCompat.TextAttributeCompatImpl
        public List<String> getTextConversionSuggestions() {
            return this.mObject.getTextConversionSuggestions();
        }

        @Override // androidx.core.view.inputmethod.TextAttributeCompat.TextAttributeCompatImpl
        public boolean isTextSuggestionSelected() {
            return this.mTextSuggestionSelected;
        }

        public TextAttributeCompatApi33Impl(Object obj) {
            this.mObject = (TextAttribute) obj;
            this.mTextSuggestionSelected = false;
        }
    }

    public static final class TextAttributeCompatApi37Impl implements TextAttributeCompatImpl {
        final TextAttribute mObject;

        public TextAttributeCompatApi37Impl(List<String> list, PersistableBundle persistableBundle, boolean z) {
            this.mObject = new TextAttribute.Builder().setTextConversionSuggestions(list).setExtras(persistableBundle).setTextSuggestionSelected(z).build();
        }

        @Override // androidx.core.view.inputmethod.TextAttributeCompat.TextAttributeCompatImpl
        public PersistableBundle getExtras() {
            return this.mObject.getExtras();
        }

        @Override // androidx.core.view.inputmethod.TextAttributeCompat.TextAttributeCompatImpl
        public Object getTextAttribute() {
            return this.mObject;
        }

        @Override // androidx.core.view.inputmethod.TextAttributeCompat.TextAttributeCompatImpl
        public List<String> getTextConversionSuggestions() {
            return this.mObject.getTextConversionSuggestions();
        }

        @Override // androidx.core.view.inputmethod.TextAttributeCompat.TextAttributeCompatImpl
        public boolean isTextSuggestionSelected() {
            return this.mObject.isTextSuggestionSelected();
        }

        public TextAttributeCompatApi37Impl(Object obj) {
            this.mObject = (TextAttribute) obj;
        }
    }

    private TextAttributeCompat(TextAttributeCompatImpl textAttributeCompatImpl) {
        this.mImpl = textAttributeCompatImpl;
    }
}
