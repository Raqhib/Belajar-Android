package id.mondo.myflexiblefragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailCategoryFragment : Fragment() {

    private lateinit var tvcategoryName: TextView
    private lateinit var tvcategoryDescription: TextView
    private lateinit var btnProfile: Button
    private lateinit var btnShowDialog: Button

    var description: String? = null




    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvcategoryName = view.findViewById(R.id.tv_category_name)
        tvcategoryDescription = view.findViewById(R.id.tv_category_description)
        btnProfile = view.findViewById(R.id.btn_profile)
        btnShowDialog = view.findViewById(R.id.btn_show_dialog)


        if (savedInstanceState != null){
            val descFromBundle = savedInstanceState.getString(EXTRA_DECRIPTION)
            description = descFromBundle
        }

        if (arguments != null){
            val categoryName = arguments?.getString(EXTRA_NAME)
            tvcategoryName.text = categoryName
            tvcategoryDescription.text = description
        }

        btnShowDialog.setOnClickListener{
            val optionDialogFragment = OptionalDialogFragment()

            val fragmentManager = childFragmentManager
            optionDialogFragment.show(fragmentManager, OptionalDialogFragment::class.java.simpleName)
        }

        btnProfile.setOnClickListener{
            val intent = Intent(requireActivity(), ProfileActivity::class.java)
            startActivity(intent)
        }


    }

    internal var optionDialogListener: OptionalDialogFragment.OnOptionalDialogListener = object : OptionalDialogFragment.OnOptionalDialogListener {
        override fun onOptionChosen(text: String?) {
            Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
        }
    }


    companion object {

        var EXTRA_NAME = "extra_name"
        var EXTRA_DECRIPTION = "extra_description"


        fun newInstance(param1: String, param2: String) =
            DetailCategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}