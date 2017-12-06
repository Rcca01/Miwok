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

public class FamilyFragment extends Fragment {

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

    public FamilyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        ArrayList<Word> familyWord = new ArrayList();
        familyWord.add(new Word("Father","әpә",R.drawable.family_father,R.raw.family_father));
        familyWord.add(new Word("Mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        familyWord.add(new Word("Son","angsi",R.drawable.family_son, R.raw.family_son));
        familyWord.add(new Word("Daughter","Tune",R.drawable.family_daughter,R.raw.family_daughter));
        familyWord.add(new Word("Older brother","Taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        familyWord.add(new Word("Younger brother","Chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        familyWord.add(new Word("Older sister","Tete",R.drawable.family_older_sister, R.raw.family_older_sister));
        familyWord.add(new Word("Younger sister","Kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        familyWord.add(new Word("Grandmother","Ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        familyWord.add(new Word("Granfather","Paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        final WordAdapter itemsFamily = new WordAdapter(getActivity(),familyWord,R.color.category_family);
        ListView listFamily = (ListView) rootView.findViewById(R.id.list);
        listFamily.setAdapter(itemsFamily);

        listFamily.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(afChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getActivity(), itemsFamily.getItem(i).getmAudio());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompleteListener);
                }
            }
        });

        return  rootView;
    }

    public void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
            mAudioManager.abandonAudioFocus(afChangeListener);
        }
    }
}
