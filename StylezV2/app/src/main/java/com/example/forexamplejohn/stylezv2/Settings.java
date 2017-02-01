package com.example.forexamplejohn.stylezv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.forexamplejohn.stylezv2.Pref_files.*;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Settings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Settings extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SeekBarPreference miles;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Settings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Settings.
     */
    // TODO: Rename and change types and number of parameters
    public static Settings newInstance(String param1, String param2) {
        Settings fragment = new Settings();
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
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preference);

        //Get widgets:
        miles = (SeekBarPreference) this.findPreference("milesTrade");

        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);


        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        onSharedPreferenceChanged(sharedPreferences, "milesTrade");
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String s, String s2) {
        if (mListener != null) {
            mListener.onFragmentInteraction(s,s2);
        }
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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        switch(key) {
            case "milesTrade":
                int radius = PreferenceManager.getDefaultSharedPreferences(this.getActivity()).getInt(key, 1);
                if (radius < 1)
                miles.setSummary(this.getString(R.string.searchingDistance).replace("$1", "" + 1 + " mile"));
                else
                    miles.setSummary(this.getString(R.string.searchingDistance).replace("$1", "" + radius + " miles"));
                break;
        }
    }

    @Override

    public void onResume() {
        super.onResume() ;
        getPreferenceScreen().getSharedPreferences().
                registerOnSharedPreferenceChangeListener(this) ;
    }

    @Override

    public void onPause() {
        super.onPause() ;
        getPreferenceScreen().getSharedPreferences().
                unregisterOnSharedPreferenceChangeListener(this) ;

    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String s,String s2);
    }
}
