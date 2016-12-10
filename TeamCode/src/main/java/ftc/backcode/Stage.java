package ftc.backcode;


import android.animation.StateListAnimator;
import android.graphics.Path;

import java.util.Objects;

/**
 * Created by union on 11/11/16.
 * Last updated on 11/18/16 by union.
 */

public enum Stage {

    STAGE1(1, new Stageable()
    {
        @Override
        public void execute()
        {



        }
    }),
    STAGE2(2, new Stageable() {
        @Override
        public void execute() {

        }
    }),

    STAGE3(3, new Stageable()
    {
        @Override
        public void execute()
        {

        }
    }),
    STAGE4(4, new Stageable()
    {
        @Override
        public void execute()
        {

        }
    });

    private int _stage;



    private Stageable _stageRun;
    Stage(int a, Stageable b) { _stage = a; _stageRun = b; }

    public Stageable execute()
    {
        return _stageRun;
    }

}
