package com.example.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.splash.databinding.FragmentIlanDetayBinding
import com.squareup.picasso.Picasso

class ilanDetayFragment : Fragment() {

    private var _binding: FragmentIlanDetayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView( inflater: LayoutInflater,
                               container: ViewGroup?,
                               savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentIlanDetayBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val nameTextView = view.findViewById(R.id.full_name) as TextView
        val emailTextView = view.findViewById(R.id.user_email) as TextView

        var firstName = this@ilanDetayFragment.activity?.intent?.extras?.getString("firstName")
        val lastName = this@ilanDetayFragment.activity?.intent?.extras?.getString("lastName")
        val email = this@ilanDetayFragment.activity?.intent?.extras?.getString("email")

        if(lastName?.length!! > 0) {
            firstName = "$firstName $lastName"
        }
        nameTextView.text = firstName
        emailTextView.text = email

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bundle: ilanDetayFragmentArgs by navArgs()
        val gelenkira = bundle.kirafiyati
        binding.gelenFiyattextView.text = gelenkira.toString()
        val gelenCities = bundle.cities
        binding.gelencitestextView.text = gelenCities
        val gelenBaslik = bundle.baslik
        binding.gelenbasliktextView.text = gelenBaslik
        val gelenPeriyot = bundle.periyot
        binding.gelenperiyottextView.text = gelenPeriyot
        val gelenDistricts = bundle.districts
        binding.gelenilcetextView.text = gelenDistricts
        val gelenquarters = bundle.quarters
        //binding.gelenmahallebirtextView.text = gelenquarters
        val gelenTowns = bundle.towns
        binding.gelenmahalleikitextView.text = gelenTowns
        val gelenAciklama = bundle.aciklama
        binding.gelenaciklamatextView.text = gelenAciklama
        val gelenphoto = bundle.downloadurl
        Picasso.get().load(gelenphoto).resize(2100,1000).into(binding.ilanDetayimage)
        println(gelenBaslik)
        println(gelenBaslik)

        binding.kirala.setOnClickListener {
            val action = ilanDetayFragmentDirections.actionIlanDetayFragmentToKiralaFragment(gelenphoto,gelenBaslik,gelenkira,gelenAciklama,gelenCities,gelenPeriyot)
            Navigation.findNavController(it).navigate(action)
        }

        binding.backbtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }


}

