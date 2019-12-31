package com.yu.hu.emoji;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.yu.hu.emoji.entity.Emoji;
import com.yu.hu.emoji.repository.EmojiRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hy on 2019/12/27 19:49
 * <p>
 * 表情管理类
 * <p>
 * 建议application初始化时调用{@link #init()}提前进行初始化操作
 *
 * @see #matcher(String)
 * @see #getEmojiRes(String)  获取表情资源id
 * @see #getEmojiText(int)  获取表情对应文字
 * @see #getAllQQEmoji()  获取qq表情
 * @see #getAllEmoji()  获取emoji表情
 **/
@SuppressWarnings({"unused", "WeakerAccess"})
public class EmojiManager {

    private static final String TAG = "EmojiManager";

    /**
     * EmojiRepository
     */
    private static EmojiRepository sEmojiRepository;

    //匹配表情[**\tr]
    private static final String EMOJI_REGEX = "\\[[qe]_[a-z0-9]+\\\\tr]";

    /**
     * 用于正则匹配
     * <p>
     * 在此处初始化是为了触发静态代码块
     */
    private static Pattern sPATTERN;

    //默认表情 - 微笑
    private static final int DEFAULT_EMOJI = R.drawable.q_wx;

    //由于static代码块发送在初始化阶段 所以需要调用一个init方法静态代码块才会执行
    public static void init() {
        //执行一次查询操作以触发callback回调
        sEmojiRepository.queryAllByType(Emoji.TYPE_EMOJI);
    }

    //静态代码块执行在类初始化阶段，但是数据库操作是异步的，所以需要提前初始化才行
    static {
        Log.d(TAG, "static initializer: ");
        sEmojiRepository = EmojiRepository.getInstance(getApplicationByReflect());
        sPATTERN = Pattern.compile(EMOJI_REGEX);
    }


    /**
     * 获取所有表情
     *
     * @see com.yu.hu.emoji.db.EmojiDatabase.InitTask
     */
    @SuppressWarnings("JavadocReference")
    public static Emoji[] getAllEmojis() {
        return getAllEmojiList().toArray(new Emoji[0]);
    }

    private static List<Emoji> getAllEmojiList() {
        List<Emoji> emojiList = new ArrayList<>();

        //qq表情
        emojiList.add(new Emoji("[q_wx\\tr]", R.drawable.q_wx, "微笑", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_qx\\tr]", R.drawable.q_kx, "苦笑", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_hc\\tr]", R.drawable.q_hc, "花痴", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_dz\\tr]", R.drawable.q_dz, "呆滞", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_cool\\tr]", R.drawable.q_cool, "酷", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_dk\\tr]", R.drawable.q_dk, "大哭", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_hx\\tr]", R.drawable.q_hx, "害羞", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_bz\\tr]", R.drawable.q_bz, "闭嘴", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_dks\\tr]", R.drawable.q_dks, "打瞌睡", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_wq\\tr]", R.drawable.q_wq, "委屈", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_gg\\tr]", R.drawable.q_gg, "尴尬", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_fn\\tr]", R.drawable.q_fn, "愤怒", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_tp\\tr]", R.drawable.q_tp, "调皮", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_zy\\tr]", R.drawable.q_zy, "龇牙", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_jy\\tr]", R.drawable.q_jy, "惊讶", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_bkx\\tr]", R.drawable.q_bkx, "不开心", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_cool2\\tr]", R.drawable.q_cool2, "酷2", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_lh\\tr]", R.drawable.q_lh, "冷汗", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_zk\\tr]", R.drawable.q_zk, "抓狂", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_ot\\tr]", R.drawable.q_ot, "呕吐", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_tx\\tr]", R.drawable.q_tx, "偷笑", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_ka\\tr]", R.drawable.q_ka, "可爱", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_ws\\tr]", R.drawable.q_ws, "无视", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_am\\tr]", R.drawable.q_am, "傲慢", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_tz\\tr]", R.drawable.q_tz, "舔嘴", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_fk\\tr]", R.drawable.q_fk, "犯困", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_jk\\tr]", R.drawable.q_jk, "惊恐", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_liuh\\tr]", R.drawable.q_liuh, "流汗", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_dx\\tr]", R.drawable.q_dx, "大笑", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_jm\\tr]", R.drawable.q_jm, "军帽", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_nl\\tr]", R.drawable.q_nl, "努力", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_cj\\tr]", R.drawable.q_cj, "吵架", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_yw\\tr]", R.drawable.q_yw, "疑问", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_xu\\tr]", R.drawable.q_xu, "嘘", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_yun\\tr]", R.drawable.q_yun, "晕", Emoji.TYPE_QQ));
        emojiList.add(new Emoji("[q_zk2\\tr]", R.drawable.q_zk2, "抓狂2", Emoji.TYPE_QQ));

        //emoji

        return emojiList;
    }

    /**
     * 匹配表情文字
     *
     * @param content content
     */
    public static Matcher matcher(String content) {
        return sPATTERN.matcher(content);
    }

    /**
     * 获取表情res
     * 若搜索不到则返回默认表情{@link #DEFAULT_EMOJI}
     */
    public static int getEmojiRes(String emoji) {
        int res = sEmojiRepository.getEmojiRes(emoji);
        return res == 0 ? DEFAULT_EMOJI : res;
    }

    /**
     * 获取表情对应的Text
     * 若搜索不到则返回空串
     */
    @NonNull
    public static String getEmojiText(int emojiRes) {
        String emojiText = sEmojiRepository.getEmojiText(emojiRes);
        return emojiText == null ? "" : emojiText;
    }

    /**
     * @return 所有qq表情
     */
    public static List<Emoji> getAllQQEmoji() {
        return sEmojiRepository.queryAllByType(Emoji.TYPE_QQ);
    }

    /**
     * @return 所有emoji表情
     */
    public static List<Emoji> getAllEmoji() {
        return sEmojiRepository.queryAllByType(Emoji.TYPE_EMOJI);
    }

    /**
     * 通过反射获取到Application
     *
     * @return Application
     */
    private static Application getApplicationByReflect() {
        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object thread = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(thread);
            if (app == null) {
                throw new NullPointerException("u should init first");
            }
            return (Application) app;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("u should init first");
    }

}