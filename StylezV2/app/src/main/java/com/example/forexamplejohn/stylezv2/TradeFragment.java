package com.example.forexamplejohn.stylezv2;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TradeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TradeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TradeFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TradeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TradeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TradeFragment newInstance(String param1, String param2) {
        TradeFragment fragment = new TradeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_trade, container, false);

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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String key, String uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(key,uri);
        }
    }

    @Override
    public void onClick(View v){
        ScreenSlidePageFragment screenSlidePageFragment = new ScreenSlidePageFragment();
        FragmentTransaction fm = getFragmentManager().beginTransaction();
        fm.replace(R.id.frameLayout,screenSlidePageFragment,"screenSlidePageFragment");
        fm.addToBackStack("screenSlidePageFragment");
        fm.commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String key, String uri);
    }
}
