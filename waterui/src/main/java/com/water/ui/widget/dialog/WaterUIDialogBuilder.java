package com.water.ui.widget.dialog;

import android.content.Context;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 创建 {@link QMUIDialog} 的 Builder 基类, 不同的 Builder 子类拥有创建不同类型对话框的能力, 具体见子类。
 * <p>该类产生的 Dialog 分为上中下三个部分:</p>
 * <ul>
 * <li>上部分是 title 区域, 支持显示纯文本标题, 通过 {@link #setTitle(int)} 系列方法设置。
 * 子类也可以通过 override {@link #onCreateTitle(QMUIDialog, ViewGroup, Context)} 方法自定义</li>
 * <li>中间部分的内容由各个子类决定, 子类通过 override {@link #onCreateContent(QMUIDialog, ViewGroup, Context)} 方法自定义。</li>
 * <li>下部分是操作区域, 支持添加操作按钮, 通过 {@link #addAction(int, int, QMUIDialogAction.ActionListener)} 系列方法添加。
 * 子类也可以通过 override {@link #onCreateHandlerBar(QMUIDialog, ViewGroup, Context)} 方法自定义。
 * 其中操作按钮有内联和块级之分, 也有普通、正向、反向之分, 具体见 {@link QMUIDialogAction}
 * </li>
 * </ul>
 *
 * @author cginechen
 * @date 2015-10-20
 */
public abstract class WaterUIDialogBuilder<T extends WaterUIDialogBuilder> {

    @IntDef({HORIZONTAL, VERTICAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Orientation {
    }

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    /**
     * A global theme provider, use to distinguish theme from different builder type
     */
    private static OnProvideDefaultTheme sOnProvideDefaultTheme = null;

    public static void setOnProvideDefaultTheme(OnProvideDefaultTheme onProvideDefaultTheme) {
        WaterUIDialogBuilder.sOnProvideDefaultTheme = onProvideDefaultTheme;
    }

    private Context mContext;
    protected WaterUIDialog mDialog;
    protected String mTitle;
    private boolean mCancelable = true;
    private boolean mCanceledOnTouchOutside = true;



    
    public interface OnProvideDefaultTheme {
        int getThemeForBuilder(WaterUIDialogBuilder builder);
    }
}
