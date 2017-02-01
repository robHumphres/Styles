package com.example.forexamplejohn.stylezv2;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Closet extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    public Closet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_closet, container, false);

        //INITALIZE BUTTONS
        Button accessories = (Button) v.findViewById(R.id.accessoriesButton);
        Button tops = (Button) v.findViewById(R.id.topsButton);
        Button bottoms = (Button)v.findViewById(R.id.bottomsButton);
        Button shoes = (Button)v.findViewById(R.id.shoesButton);
        Button dresses = (Button)v.findViewById(R.id.dressesButton);
        Button jewerly = (Button)v.findViewById(R.id.jewelryButton);

        //SET ON CLICK LISTENERS
        tops.setOnClickListener(this);
        accessories.setOnClickListener(this);
        bottoms.setOnClickListener(this);
        shoes.setOnClickListener(this);
        dresses.setOnClickListener(this);
        jewerly.setOnClickListener(this);


        return v;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }//catch
    }


    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.accessoriesButton:
                Toast.makeText(getActivity(), "Accessories Button", Toast.LENGTH_SHORT).show();
                break;
            case R.id.topsButton:
                Toast.makeText(getActivity(), "tops btn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bottomsButton:
                Toast.makeText(getActivity(), "btms btn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.shoesButton:
                Toast.makeText(getActivity(), "shoes btn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dressesButton:
                Toast.makeText(getActivity(), "dresses btn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.jewelryButton:
                Toast.makeText(getActivity(), "jwrly btn", Toast.LENGTH_SHORT).show();
                break;
        }//end of switch
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String key,String uri);
    }

}
