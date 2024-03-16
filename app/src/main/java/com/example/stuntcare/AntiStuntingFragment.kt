
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.raon.R
import com.example.stuntcare.AntiStunting2_1
import com.example.stuntcare.ExerciseOutput
import com.example.stuntcare.FoodOutputActivity
import com.example.stuntcare.NutritionOutputActivity
import com.example.stuntcare.PregnancyRestriction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AntiStuntingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anti_stunting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val db = Firebase.database
        val ref = db.getReference(currentUser?.uid!!)
        val flEmpty: FrameLayout = view.findViewById(R.id.flEmpty)
        val flExist: FrameLayout = view.findViewById(R.id.flExist)
        val textKelahiran: TextView = view.findViewById(R.id.birthEstimation)
        val flNutrition: FrameLayout = view.findViewById(R.id.nutrition)
        val flFood: FrameLayout = view.findViewById(R.id.food)
        val flExercise: FrameLayout = view.findViewById(R.id.exercise)
        val flPreg: FrameLayout = view.findViewById(R.id.pregnancy)

        flExercise.setOnClickListener(){
            Intent(requireActivity(),ExerciseOutput::class.java).also{
                startActivity(it)
            }
        }

        flFood.setOnClickListener(){
            Intent(requireActivity(),FoodOutputActivity::class.java).also{
                startActivity(it)
            }
        }

        flNutrition.setOnClickListener(){
            Intent(requireActivity(),NutritionOutputActivity::class.java).also{
                startActivity(it)
            }
        }

        flPreg.setOnClickListener(){
            Intent(requireActivity(),PregnancyRestriction::class.java).also{
                startActivity(it)
            }
        }

        flExist.visibility = View.GONE

        ref.child("lastPeriod").get().addOnSuccessListener {
            if (it.exists()){
                flEmpty.visibility = View.GONE
                flExist.visibility = View.VISIBLE
                textKelahiran.text = tanggalLahir(it.value.toString())
            } else {
                flEmpty.visibility = View.VISIBLE
                flExist.visibility = View.GONE
            }
        }

        val btnInput: AppCompatButton = view.findViewById(R.id.button4)
        btnInput.setOnClickListener(){
            Intent(requireActivity(), AntiStunting2_1::class.java).also{
                startActivity(it)
            }
        }
    }

    private fun tanggalLahir(date: String): String {
        val bulan = arrayOf("January","February","March","April","May","June","July","August","September","October","November","December")
        val tanggal = date
        val tanggalSplit = tanggal.split("/")
        val tanggalInt = IntArray(tanggalSplit.size)

        for (i in tanggalSplit.indices) {
            tanggalInt[i] = tanggalSplit[i].toInt()
        }

        // +7 hari
        tanggalInt[0] += 7
        if (tanggalInt[1] == 2) {
            if (tanggalInt[2] % 4 == 0) {
                if (tanggalInt[0] > 29) {
                    tanggalInt[0] %= 29
                    tanggalInt[1]++
                }
            } else {
                if (tanggalInt[0] > 28) {
                    tanggalInt[0] %= 28
                    tanggalInt[1]++
                }
            }
        } else if (((tanggalInt[1] % 2 == 0) && tanggalInt[1] < 8) || (tanggalInt[1] % 2 != 0 && tanggalInt[1] > 7)) {
            if (tanggalInt[0] > 30) {
                tanggalInt[0] %= 30
                tanggalInt[1]++
            }
        } else {
            if (tanggalInt[0] > 31) {
                tanggalInt[0] %= 31
                tanggalInt[1]++
            }
        }

        if (tanggalInt[1] > 12) {
            tanggalInt[1] %= 12
            tanggalInt[2]++
        }

        // kurang 3 bulan
        tanggalInt[1] -= 3
        if (tanggalInt[1] <= 0) {
            tanggalInt[1] += 12
            tanggalInt[2]--
        }

        // tambah 1 tahun
        tanggalInt[2]++
        return "${tanggalInt[0]} ${bulan[tanggalInt[1] - 1]} ${tanggalInt[2]}"
    }
}
