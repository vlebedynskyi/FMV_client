package com.music.fmv.services;

import com.music.fmv.models.notdbmodels.PlayableSong;

import java.util.List;

/**
 * User: vitaliylebedinskiy
 * Date: 8/12/13
 * Time: 12:16 PM
 */
public interface Player {
    public void pause();

    public void previous();

    public void next();

    public void stop();

    public boolean isPlaying();

    public void setShuffle(boolean value);

    public boolean isShuffle();

    public void setLoop(boolean value);

    public boolean isLoop();

    public void seek(int position);

    public void play(List<PlayableSong> songs, int position);

    public PlayerStatus getStatus();

    public void setPlayerListener(PlayerListener listener);

    public void add(PlayableSong model);

    public interface PlayerListener {
        public void onSongPlaying(PlayableSong song);

        public void onPlayingStopped();

        public void needRefreshControls();
    }

    public class PlayerStatus {
        private int duration;
        private int currentProgress;
        private PlayableSong currentSong;
        private List<PlayableSong> currentQueue;
        private boolean isShuffle;
        private boolean isLoop;
        private boolean isPlaying;

        public PlayerStatus(int duration, int currentProgress, PlayableSong currentSong, List<PlayableSong> currentQueue, boolean shuffle, boolean loop, boolean playing) {
            this.duration = duration;
            this.currentProgress = currentProgress;
            this.currentSong = currentSong;
            this.currentQueue = currentQueue;
            this.isShuffle = shuffle;
            this.isLoop = loop;
            this.isPlaying = playing;
        }

        public int getDuration() {
            return duration;
        }

        public int getCurrentProgress() {
            return currentProgress;
        }

        public PlayableSong getCurrentSong() {
            return currentSong;
        }

        public boolean isShuffle() {
            return isShuffle;
        }

        public boolean isLoop() {
            return isLoop;
        }

        public boolean isPlaying() {
            return isPlaying;
        }

        public List<PlayableSong> getCurrentQueue() {
            return currentQueue;
        }
    }
}
