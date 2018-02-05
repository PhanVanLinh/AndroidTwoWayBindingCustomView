package toong.vn.androidtwowaybindingcustomview;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

@InverseBindingMethods({
        @InverseBindingMethod(type = ColorPicker.class, attribute = "color", method = "getColor")
})
public class ColorPicker extends RelativeLayout {
    String TAG = getClass().getSimpleName();
    private int color;
    private OnColorChangeListener listener;

    public ColorPicker(Context context) {
        this(context, null);
    }

    public ColorPicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_color, this, true);

        Button btnPlus = findViewById(R.id.btn_plus);
        btnPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setColor(++color);
            }
        });
    }

    public void setColor(int color) {
        Log.i(TAG, "setColor:" + color);
        this.color = color;
        if (listener != null) {
            listener.onColorChange(this, color);
        }
    }

    public int getColor() {
        return color;
    }

    public void setListener(OnColorChangeListener listener) {
        this.listener = listener;
    }

    @BindingAdapter(value = { "colorAttrChanged" }, requireAll = false)
    public static void setListeners(ColorPicker view,
            final InverseBindingListener inverseBindingListener) {
        ColorPicker.OnColorChangeListener newListener;
        newListener = new ColorPicker.OnColorChangeListener() {
            @Override
            public void onColorChange(ColorPicker colorPicker, int newColor) {
                inverseBindingListener.onChange();
            }
        };
        view.setListener(newListener);
    }

    public interface OnColorChangeListener {
        void onColorChange(ColorPicker view, int color);
    }
}