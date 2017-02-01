package com.example.forexamplejohn.stylezv2;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
 * {@link MainPageInside.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainPageInside#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainPageInside extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainPageInside() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainPageInside.
     */
    // TODO: Rename and change types and number of parameters
    public static MainPageInside newInstance(String param1, String param2) {
        MainPageInside fragment = new MainPageInside();
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
        View v =  inflater.inflate(R.layout.fragment_main_page_inside, container, false);

        //INITIALIZE VARIABLES
        Button bioBtn = (Button)v.findViewById(R.id.bioBtn);
        Button questionsBtn = (Button)v.findViewById(R.id.questionsBtn);
        Button settingsBtn = (Button)v.findViewById(R.id.settingsBtn);
        Button signOutBtn = (Button)v.findViewById(R.id.signOutBtn);
        Button reportBtn = (Button)v.findViewById(R.id.reportBtn);
        Button tradeBtn = (Button)v.findViewById(R.id.tradeBtn);


        //SETUP LISTENER
        bioBtn.setOnClickListener(this);
        questionsBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
        signOutBtn.setOnClickListener(this);
        reportBtn.setOnClickListener(this);
        tradeBtn.setOnClickListener(this);


        return v;
    }


    public void onClick(View v){

        switch(v.getId()){
            case R.id.bioBtn:
                Toast.makeText(getActivity(), "Bio button not setup yet", Toast.LENGTH_SHORT).show();
                break;
            case R.id.questionsBtn:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://sites.google.com/site/pschimpf99/home/"));// thought you'd find this funny
                getActivity().startActivity(i);
                break;
            case R.id.settingsBtn:
                //Toast.makeText(getActivity(), "settings btn", Toast.LENGTH_SHORT).show();
                Settings set = new Settings();
                FragmentTransaction fm = getFragmentManager().beginTransaction();
                fm.replace(R.id.frameLayout, set, "settings");
                fm.addToBackStack("settings");
                fm.commit();
                break;
            case R.id.signOutBtn:
                //Toast.makeText(getActivity(), "signout Btn", Toast.LENGTH_SHORT).show();
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent intent = new Intent(getActivity(),LoginPage.class);
                                startActivity(intent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure you want to Sign Out?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                break;
            case R.id.reportBtn:
                Toast.makeText(getActivity(), "Report Button not setup yet", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tradeBtn:
                Toast.makeText(getActivity(), "Trade Btn not setup yet", Toast.LENGTH_SHORT).show();
                break;
        }//end of switch case

    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String key,String uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(key,uri);
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
        void onFragmentInteraction(String key,String uri);
    }
}
