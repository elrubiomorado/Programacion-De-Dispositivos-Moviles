package edgar.avila.geoquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

import edgar.avila.geoquizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_bicho, true),
        Question(R.string.question_checo, true),
        Question(R.string.question_tilin, true),
        Question(R.string.question_best_movie, true)
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener{view: View ->
            checkAnswer(false)
        }
        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        updateQuestion()
    }
    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }

}