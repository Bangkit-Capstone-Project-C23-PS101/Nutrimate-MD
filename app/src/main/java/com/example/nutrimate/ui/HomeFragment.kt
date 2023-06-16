package com.example.nutrimate.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nutrimate.data.FoodData
import com.example.nutrimate.data.FoodDatabase
import com.example.nutrimate.databinding.FragmentHomeBinding
import com.example.nutrimate.ml.ConvertedModel
import com.example.nutrimate.ml.Model
import com.example.nutrimate.tool.rotateFile
import com.example.nutrimate.tool.uriToFile
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var getFile: File? = null
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentHomeBinding.inflate(layoutInflater, container, false)
        homeViewModel = obtainViewModel(requireActivity() as AppCompatActivity)
        binding.buttonAdd.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Select from Gallery or Camera")
                .setNegativeButton("Gallery") { dialog, which ->
                    startGallery()
                }
                .setPositiveButton("Camera") { dialog, which ->
                    startCameraX()
                }
                .show()
        }
        binding.buttonCross.setOnClickListener{
            binding.cardviewFood.isVisible = false
        }
        binding.buttonCheck.setOnClickListener {
            for (food in FoodData.listfood){

                if(food.name == binding.tvHomeFoodname.text.toString()){
                    Log.d("TES",food.name)
                    val image = food.image
                    val food = FoodDatabase(name = binding.tvHomeFoodname.text.toString(), calorie = binding.tvHomeCalorie.text.toString(), image = image)
                    homeViewModel.insert(food)
                }
            }
            binding.cardviewFood.isVisible = false
        }
        if(getFile==null){
            binding.cardviewFood.isVisible = false
        }
        return binding.root
    }

    companion object {

    }

    private fun startCameraX() {
        val intent = Intent(activity, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == PhotoActivity.CAMERA_X_RESULT) {
            val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.data?.getSerializableExtra("picture", File::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.data?.getSerializableExtra("picture")
            } as? File

            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            myFile?.let { file ->
                rotateFile(file, isBackCamera)
                getFile = file
                val bitmap = BitmapFactory.decodeFile(file.path)
                val image = Bitmap.createScaledBitmap(bitmap, 150, 150, false)
                classifyImage(image)
                val picture = Bitmap.createScaledBitmap(bitmap, 320, 225, false)
                binding.cardviewFood.isVisible = true
                binding.imgHomeFood.setImageBitmap(picture)
            }
        }
    }

    private fun classifyImage(image: Bitmap) {
        val model = context?.let { ConvertedModel.newInstance(it) }
        val imageSize = 150

// Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 150, 150, 3), DataType.FLOAT32)
        val byteBuffer = ByteBuffer.allocateDirect(4 * 150 * 150 * 3)
        byteBuffer.order(ByteOrder.nativeOrder())

        val intValues = IntArray(imageSize * imageSize)
        image!!.getPixels(intValues, 0, image!!.width, 0, 0, image!!.width, image!!.height)
        var pixel = 0
        inputFeature0.loadBuffer(byteBuffer)
        for (i in 0 until imageSize) {
            for (j in 0 until imageSize) {
                val `val` = intValues[pixel++] // RGB
                byteBuffer.putFloat((`val` shr 16 and 0xFF) * (1f / 1))
                byteBuffer.putFloat((`val` shr 8 and 0xFF) * (1f / 1))
                byteBuffer.putFloat((`val` and 0xFF) * (1f / 1))
            }
        }
// Runs model inference and gets result.
        val outputs = model?.process(inputFeature0)
        val outputFeature0 = outputs?.outputFeature0AsTensorBuffer

        val confidences = outputFeature0!!.floatArray
        Log.e("Confidences", confidences.toString())
        var maxPos = 0
        var maxConfidence = 0f
        for (i in confidences.indices) {
            if (confidences[i] > maxConfidence) {
                maxConfidence = confidences[i]
                maxPos = i
            }
        }
        val classes = arrayOf("Mie Goreng", "Mie Ayam", "Nasi Goreng", "Bakso", "Sate Ayam", "Ayam Goreng Lalapan", "Nasi Pecel", "Bubur Ayam", "Gado-Gado", "Rendang", "Capcay", "Ikan Goreng Lalapan", "Soto Ayam", "Nasi Rawon", "Telur Goreng")
        binding.tvHomeFoodname.setText(classes[maxPos])
        for(food in FoodData.listfood){
            if(classes[maxPos]==food.name){
                binding.tvHomeCalorie.text = food.calorie.toString()
                binding.tvHomeDescription.text = food.description
            }
        }
// Releases model resources if no longer used.
        model?.close()
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, requireContext())
                val bitmap = BitmapFactory.decodeFile(myFile.path)
                binding.cardviewFood.isVisible = true
                val image = Bitmap.createScaledBitmap(bitmap, 150, 150, false)
                classifyImage(image)
                val pic = Bitmap.createScaledBitmap(bitmap,320,225,false)
                binding.imgHomeFood.setImageBitmap(pic)
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): HomeViewModel {
        val factory = FoodViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(HomeViewModel::class.java)
    }
}