import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.logros.R
import com.example.logros.SupportActivity

class FirstFragment : Fragment(R.layout.fragment_first) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonSoporte = view.findViewById<Button>(R.id.supportButton)
        buttonSoporte.setOnClickListener {
            val intent = Intent(activity, SupportActivity::class.java)
            startActivity(intent)
        }
    }
}