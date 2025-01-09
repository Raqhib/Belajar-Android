package id.mondo.myflexiblefragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OptionalDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OptionalDialogFragment : DialogFragment() {

    private lateinit var btnChosee: Button
    private lateinit var btnClose: Button
    private lateinit var rgOption: RadioGroup
    private lateinit var rbSaf: Button
    private lateinit var rbMou: Button
    private lateinit var rbLvg: Button
    private lateinit var rbMoyes: Button
    private var optionalDialogListener: OnOptionalDialogListener? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChosee = view.findViewById(R.id.btn_chosee)
        btnClose = view.findViewById(R.id.btn_close)
        rgOption = view.findViewById(R.id.rg_option)
        rbSaf = view.findViewById(R.id.rb_saf)
        rbMou = view.findViewById(R.id.rb_mou)
        rbLvg = view.findViewById(R.id.rb_lvg)
        rbMoyes = view.findViewById(R.id.rb_moyes)

        btnChosee.setOnClickListener{
            val checkedRadioButtonId = rgOption.checkedRadioButtonId
            if (checkedRadioButtonId != 1){
                var coach: String? = when(checkedRadioButtonId){
                    R.id.rb_saf -> rbSaf.text.toString().trim()
                    R.id.rb_mou -> rbMou.text.toString().trim()
                    R.id.rb_lvg -> rbLvg.text.toString().trim()
                    R.id.rb_moyes -> rbMoyes.text.toString().trim()
                    else -> null
                }
                optionalDialogListener?.onOptionChosen(coach)
                dialog?.dismiss()
            }
        }
        btnClose.setOnClickListener{
            dialog?.cancel()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment

        if (fragment is DetailCategoryFragment){
            this.optionalDialogListener = fragment.optionDialogListener
        }

    }

    override fun onDetach() {
        super.onDetach()
        this.optionalDialogListener = null
    }


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
        return inflater.inflate(R.layout.fragment_optional_dialog, container, false)
    }

    interface OnOptionalDialogListener {
        fun onOptionChosen(text: String?)
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OptionalDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OptionalDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}