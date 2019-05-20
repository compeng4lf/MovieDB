package com.example.android.popularmovies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

public class GridViewModel extends AndroidViewModel {

    private int PosX, PosY;

    public GridViewModel(@NonNull Application application) {
        super(application);
    }

    public int ScreenPosX(){return PosX; }

    public int ScreenPosY(){return  PosY;}

    public void setPosX(int position){this.PosX = position;}

    public void setPosY(int position){this.PosY = position;}

}
