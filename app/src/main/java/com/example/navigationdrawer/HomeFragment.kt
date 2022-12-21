package com.example.navigationdrawer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationdrawer.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


const val TAG = "HomeFragment"
class HomeFragment : Fragment() {
private lateinit var binding: FragmentHomeBinding

//instance of recyclerview of our adapter
private lateinit var todoAdapter : TodoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setUpRecyclerview()

        //call the api
        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getTodos()
            } catch (e: IOException){
                Log.e(TAG,"IOException, you may nt be having internet connection")
                binding.progressBar.isVisible = false
                return@launchWhenCreated

            } catch (e: HttpException){
                Log.e(TAG,"HTTPExeption, unexpected response ")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                todoAdapter.todos = response.body()!!
            } else{
                Log.e(TAG, "Response not successful")
            }
            binding.progressBar.isVisible = false
        }




        return binding.root
    }

    fun setUpRecyclerview() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }


}