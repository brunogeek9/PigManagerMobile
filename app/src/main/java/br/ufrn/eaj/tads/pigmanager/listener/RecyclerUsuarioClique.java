package br.ufrn.eaj.tads.pigmanager.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerUsuarioClique implements RecyclerView.OnItemTouchListener {

    // Instância da interface
    OnItemClickListener mlistener;
    GestureDetector gesture;

    /* Interface que contém os métodos que serão implementados obrigatóriamente */
    public interface OnItemClickListener{
        void onItemClique(View v, int position);
    }


    public RecyclerUsuarioClique(Context context, final RecyclerView view, OnItemClickListener listener) {
        mlistener = listener;
        this.gesture = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                super.onSingleTapUp(e);

                View v = view.findChildViewUnder(e.getX(),e.getY());

                if(v != null && mlistener != null){
                    mlistener.onItemClique(v, view.getChildAdapterPosition(v));
                    //Log.i("CliqueListener","VIEW: "+v);
                }

                return true;
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gesture.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }




}
