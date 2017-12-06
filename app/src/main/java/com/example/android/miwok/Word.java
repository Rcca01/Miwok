package com.example.android.miwok;

/**
 * Created by Opti9020-C2B on 9/8/2017.
 */

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int imageView = this.NO_IMAGE_PROVIDE;
    private int mAudio;

    public static int NO_IMAGE_PROVIDE = -1;

    /**
     * Função sem paramentro de imagem
     * @param defaultTranslation
     * @param miwokTranslation
     */
    public Word(String defaultTranslation, String miwokTranslation, int audio){
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mAudio = audio;
    }

    /**
     * Função com paramentro de imagem
     * @param defaultTranslation
     * @param miwokTranslation
     * @param imageView
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageView, int audio){
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.imageView = imageView;
        this.mAudio = audio;
    }

    public String getDefaultTranslation(){
        return this.mDefaultTranslation;
    }

    public String getMiwokTranslation(){
        return this.mMiwokTranslation;
    }

    public int getImageView(){
        return this.imageView;
    }

    public boolean hasImage(){
        return this.imageView != this.NO_IMAGE_PROVIDE;
    }

    public int getmAudio(){
        return mAudio;
    }
}
