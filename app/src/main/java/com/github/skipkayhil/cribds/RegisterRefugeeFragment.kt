package com.github.skipkayhil.cribds

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_register_refugee.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RegisterRefugeeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RegisterRefugeeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RegisterRefugeeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val retView = inflater.inflate(R.layout.fragment_register_refugee, container, false)

        val adapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, arrayOf("Camp 1", "Camp 2", "Camp 3"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        retView.campSpinner.adapter = adapter

        retView.submitButton.setOnClickListener { view ->
            AlertDialog.Builder(view.context).setMessage("Are you sure?")
                .setCancelable(false)
                .setPositiveButton("Submit") { dialog, id ->
                    Snackbar.make(view, "NO CONNECTION: Refugee will be added to the database when the connection is reestablished.", 5000).show()
                    activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, RegisterRefugeeFragment.newInstance("", ""), "currentView")
                        .commit()
                }
                .setNegativeButton("Cancel") { dialog, id ->
                    dialog.cancel()
                }
                .create().show()
        }

        return retView
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterRefugeeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterRefugeeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
