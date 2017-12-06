package layout;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.miwok.R;
import com.example.android.miwok.Word;
import com.example.android.miwok.WordAdapter;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;

public class ColorsFragments extends Fragment {
    private MediaPlayer mediaPlayer;

    private AudioManager mAudioManager;

    final AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT){
                mediaPlayer.pause();
            }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener onCompleteListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    public ColorsFragments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        ArrayList<Word> listColors = new ArrayList<>();
        listColors.add(new Word("Red","Wetetti",R.drawable.color_red,R.raw.color_red));
        listColors.add(new Word("Green","Chocokki",R.drawable.color_green,R.raw.color_green));
        listColors.add(new Word("Brown","Takaakki",R.drawable.color_brown,R.raw.color_brown));
        listColors.add(new Word("Gray","Topoppi",R.drawable.color_gray,R.raw.color_gray));
        listColors.add(new Word("Black","Kululli",R.drawable.color_black,R.raw.color_black));
        listColors.add(new Word("White","Kelelli",R.drawable.color_white,R.raw.color_white));
        listColors.add(new Word("Dusty yellow","Topiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        listColors.add(new Word("Mustard yellow","Chiwiitә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        final WordAdapter itemsList = new WordAdapter(getActivity(),listColors,R.color.category_colors);
        ListView viewWord = (ListView) rootView.findViewById(R.id.list);
        viewWord.setAdapter(itemsList);

        viewWord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(afChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getActivity(),itemsList.getItem(i).getmAudio());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompleteListener);
                }
            }
        });

        return rootView;
    }

    public void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            mAudioManager.abandonAudioFocus(afChangeListener);
        }
    }
}
